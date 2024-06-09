package org.benchmarx.examples.familiestopersons.implementations.eneo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.benchmarx.BXTool;
import org.benchmarx.config.Configurator;
import org.benchmarx.edit.ChangeAttribute;
import org.benchmarx.edit.CreateEdge;
import org.benchmarx.edit.CreateNode;
import org.benchmarx.edit.DeleteEdge;
import org.benchmarx.edit.DeleteNode;
import org.benchmarx.edit.Edit;
import org.benchmarx.edit.IEdit;
import org.benchmarx.edit.MoveNode;
import org.benchmarx.eneo.f2p.F2P_GEN_InitiateSyncDialogue;
import org.benchmarx.eneo.f2p.F2P_MI;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.emoflon.neo.api.eneofamiliestopersons.API_Common;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.API_Families;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.API_Persons;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.run.F2P_CC_Run;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.run.F2P_GEN_Run;
import org.emoflon.neo.cypher.models.NeoCoreBuilder;
import org.emoflon.neo.cypher.models.templates.CypherBuilder;
import org.emoflon.neo.engine.modules.ilp.ILPFactory.SupportedILPSolver;
import org.emoflon.neo.neocore.util.NeoCoreConstants;

import Families.FamiliesFactory;
import Families.FamiliesPackage;
import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;
import Persons.Female;
import Persons.Male;
import Persons.PersonRegister;
import Persons.PersonsFactory;
import Persons.PersonsPackage;

public class ENeoFamiliesToPersons implements BXTool<FamilyRegister, PersonRegister, Decisions> {
	private SupportedILPSolver solver = SupportedILPSolver.Gurobi;
	private Configurator<Decisions> configurator;
	private FamilyRegister sourceRegister;
	private PersonRegister targetRegister;
	private boolean preconditionAchieved;

	@Override
	public String getName() {
		return "eNeo";
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public void initiateSynchronisationDialogue() {
		sourceRegister = FamiliesFactory.eINSTANCE.createFamilyRegister();
		targetRegister = PersonsFactory.eINSTANCE.createPersonRegister();
		preconditionAchieved = false;
		
		try (var builder = API_Common.createBuilder()) {
			builder.clearDataBase();
			var gen = new F2P_GEN_InitiateSyncDialogue();
			gen.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void assertPostcondition(FamilyRegister source, PersonRegister target) {
		var actualSource = FamiliesFactory.eINSTANCE.createFamilyRegister();
		var actualTarget = PersonsFactory.eINSTANCE.createPersonRegister();

		try (var builder = API_Common.createBuilder()) {
			var familiesAPI = new API_Families(builder);
			var allFamiliesPattern = familiesAPI.getPattern_FamilyPattern();
			var allFamiliesMatches = allFamiliesPattern.pattern().determineMatches();

			allFamiliesMatches.forEach(fm -> {
				var f = allFamiliesPattern.data(List.of(fm)).findAny().get();

				var fc = FamiliesFactory.eINSTANCE.createFamily();
				fc.setName(f._family._name);
				actualSource.getFamilies().add(fc);

				{
					var pattern = familiesAPI.getPattern_MotherPattern();
					var mask = pattern.mask();
					mask.setFamily(fm.getElement(allFamiliesPattern._family));
					var allMothersMatches = pattern.determineMatches(mask);
					var allMothersData = pattern.data(allMothersMatches);
					allMothersData.forEach(m -> {
						var mc = FamiliesFactory.eINSTANCE.createFamilyMember();
						mc.setName(m._member._name);
						fc.setMother(mc);
					});
				}

				{
					var pattern = familiesAPI.getPattern_FatherPattern();
					var mask = pattern.mask();
					mask.setFamily(fm.getElement(allFamiliesPattern._family));
					var allFathersMatches = pattern.determineMatches(mask);
					var allFathersData = pattern.data(allFathersMatches);
					allFathersData.forEach(fa -> {
						var fac = FamiliesFactory.eINSTANCE.createFamilyMember();
						fac.setName(fa._member._name);
						fc.setFather(fac);
					});
				}

				{
					var pattern = familiesAPI.getPattern_DaughterPattern();
					var mask = pattern.mask();
					mask.setFamily(fm.getElement(allFamiliesPattern._family));
					var allDaughtersMatches = pattern.determineMatches(mask);
					var allDaughtersData = familiesAPI.getPattern_DaughterPattern().data(allDaughtersMatches);
					allDaughtersData.forEach(d -> {
						var dc = FamiliesFactory.eINSTANCE.createFamilyMember();
						dc.setName(d._member._name);
						fc.getDaughters().add(dc);
					});
				}

				{
					var pattern = familiesAPI.getPattern_SonPattern();
					var mask = pattern.mask();
					mask.setFamily(fm.getElement(allFamiliesPattern._family));
					var allSonsMatches = pattern.determineMatches(mask);
					var allSonsData = familiesAPI.getPattern_SonPattern().data(allSonsMatches);
					allSonsData.forEach(s -> {
						var sc = FamiliesFactory.eINSTANCE.createFamilyMember();
						sc.setName(s._member._name);
						fc.getSons().add(sc);
					});
				}
			});

			var formatter = new SimpleDateFormat("yyyy-MM-dd");
			var dateTimeFormatter = DateTimeFormatter.ISO_DATE;
			var personsAPI = new API_Persons(builder);
			var allFemalesMatches = personsAPI.getPattern_FemalePattern().pattern().determineMatches();
			var allFemalesData = personsAPI.getPattern_FemalePattern().data(allFemalesMatches);
			allFemalesData.forEach(fp -> {
				var fpc = PersonsFactory.eINSTANCE.createFemale();
				if (fp._person._birthday != null)
					try {
						fpc.setBirthday(formatter.parse(fp._person._birthday.format(dateTimeFormatter)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				fpc.setName(fp._person._name);
				actualTarget.getPersons().add(fpc);
			});
			var allMalesMatches = personsAPI.getPattern_MalePattern().pattern().determineMatches();
			var allMalesData = personsAPI.getPattern_MalePattern().data(allMalesMatches);
			allMalesData.forEach(mp -> {
				var mpc = PersonsFactory.eINSTANCE.createMale();
				if (mp._person._birthday != null)
					try {
						mpc.setBirthday(formatter.parse(mp._person._birthday.format(dateTimeFormatter)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				mpc.setName(mp._person._name);
				actualTarget.getPersons().add(mpc);
			});
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		new FamiliesComparator().accept(source, actualSource);
		new PersonsComparator().accept(target, actualTarget);
	}

	@Override
	public void noPrecondition() {
		preconditionAchieved = true;
	}

	@Override
	public void assertPrecondition(FamilyRegister source, PersonRegister target) {
		// Create models in database
		IEdit<FamilyRegister> sourceEdit = new Edit<>();
		source.getFamilies().forEach(f -> {
			sourceEdit.getSteps().add(new CreateNode<>(f));
			sourceEdit.getSteps().add(new CreateEdge<>(FamiliesPackage.Literals.FAMILY_REGISTER__FAMILIES, source, f));

			if (f.getFather() != null) {
				sourceEdit.getSteps().add(new CreateNode<>(f.getFather()));
				sourceEdit.getSteps().add(new CreateEdge<>(FamiliesPackage.Literals.FAMILY__FATHER, f, f.getFather()));
			}

			if (f.getMother() != null) {
				sourceEdit.getSteps().add(new CreateNode<>(f.getMother()));
				sourceEdit.getSteps().add(new CreateEdge<>(FamiliesPackage.Literals.FAMILY__MOTHER, f, f.getMother()));
			}

			f.getDaughters().forEach(d -> {
				sourceEdit.getSteps().add(new CreateNode<>(d));
				sourceEdit.getSteps().add(new CreateEdge<>(FamiliesPackage.Literals.FAMILY__DAUGHTERS, f, d));
			});

			f.getSons().forEach(s -> {
				sourceEdit.getSteps().add(new CreateNode<>(s));
				sourceEdit.getSteps().add(new CreateEdge<>(FamiliesPackage.Literals.FAMILY__SONS, f, s));
			});
		});

		IEdit<PersonRegister> targetEdit = new Edit<>();
		target.getPersons().forEach(p -> {
			targetEdit.getSteps().add(new CreateNode<>(p));
			targetEdit.getSteps().add(new CreateEdge<>(PersonsPackage.Literals.PERSON_REGISTER__PERSONS, target, p));
		});

		createDeltasInDatabase(() -> sourceEdit, () -> targetEdit, //
				(builder) -> initiateSynchronisationDialogue(), //
				(builder) -> {
					builder.deleteAllCorrs();
					builder.executeQueryForSideEffect(CypherBuilder
							.removeDeltaAttributeForNodes(F2P_GEN_Run.SRC_MODEL_NAME, NeoCoreConstants._CR_PROP));
					builder.executeQueryForSideEffect(CypherBuilder
							.removeDeltaAttributeForEdges(F2P_GEN_Run.SRC_MODEL_NAME, NeoCoreConstants._CR_PROP));
					builder.executeQueryForSideEffect(CypherBuilder
							.removeDeltaAttributeForNodes(F2P_GEN_Run.TRG_MODEL_NAME, NeoCoreConstants._CR_PROP));
					builder.executeQueryForSideEffect(CypherBuilder
							.removeDeltaAttributeForEdges(F2P_GEN_Run.TRG_MODEL_NAME, NeoCoreConstants._CR_PROP));
				});

		// Perform CC
		F2P_CC_Run cc = new F2P_CC_Run(F2P_GEN_Run.SRC_MODEL_NAME, F2P_GEN_Run.TRG_MODEL_NAME, solver);
		try {
			cc.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertPostcondition(source, target);

		sourceRegister = source;
		targetRegister = target;

		preconditionAchieved = true;
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEdit,
			Supplier<IEdit<PersonRegister>> targetEdit) {
		if (preconditionAchieved) {
			createDeltasInDatabase(sourceEdit, targetEdit, //
					(builder) -> {
					}, //
					(builder) -> {
					});
			try {
				if (configurator != null) {
					var mi = new F2P_MI(Optional.of(configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD)),
							Optional.of(configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW)), solver);
					mi.runModelIntegration();
				} else {
					var mi = new F2P_MI(Optional.empty(), Optional.empty(), solver);
					mi.runModelIntegration();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void createDeltasInDatabase(Supplier<IEdit<FamilyRegister>> sourceEdit,
			Supplier<IEdit<PersonRegister>> targetEdit, Consumer<NeoCoreBuilder> pre, Consumer<NeoCoreBuilder> post) {
		try (var builder = API_Common.createBuilder()) {
			pre.accept(builder);

			var familyAPI = new API_Families(builder);
			var personsAPI = new API_Persons(builder);

			for (var s : sourceEdit.get().getSteps()) {
				if (s instanceof CreateNode) {
					var cn = (CreateNode<FamilyRegister>) s;
					if (cn.getNode() instanceof Family) {
						var family = (Family) cn.getNode();
						var rule = familyAPI.getRule_CreateFamily();
						var mask = rule.mask();
						mask.addParameter(rule._param__name, family.getName());
						mask.addParameter(rule._param__id, family.hashCode());
						mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
						rule.apply(mask, mask);
					} else if (cn.getNode() instanceof FamilyMember) {
						var member = (FamilyMember) cn.getNode();
						var rule = familyAPI.getRule_CreateFamilyMember();
						var mask = rule.mask();
						mask.addParameter(rule._param__name, member.getName());
						mask.addParameter(rule._param__id, member.hashCode());
						mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
						rule.apply(mask, mask);
					} else {
						throw new IllegalArgumentException("Unable to handle created node: " + cn.getNode());
					}
				} else if (s instanceof CreateEdge) {
					var ce = (CreateEdge<FamilyRegister>) s;
					createSourceEdge(familyAPI, ce);
				} else if (s instanceof ChangeAttribute) {
					var ca = (ChangeAttribute<FamilyRegister>) s;
					if (ca.getAttribute() == FamiliesPackage.Literals.FAMILY__NAME) {
						var rule = familyAPI.getRule_ChangeNameOfFamily();
						var mask = rule.mask();
						mask.addParameter(rule._param__name, ca.getNewValue());
						mask.addParameter(rule._param__id, ca.getNode().hashCode());
						rule.apply(mask, mask);
					} else if(ca.getAttribute() == FamiliesPackage.Literals.FAMILY_MEMBER__NAME) {
						var member = (FamilyMember) ca.getNode();
						var family = (Family) member.eContainer();
						if(family.getDaughters().contains(member)) {
							var rule = familyAPI.getRule_ChangeNameOfDaughter();
							var mask = rule.mask();
							mask.addParameter(rule._param__name, ca.getNewValue());
							mask.addParameter(rule._param__id, ca.getNode().hashCode());
							rule.apply(mask, mask);
						} else {
							throw new IllegalArgumentException("Unable to handle change attribute: " + ca.getAttribute());
						}
					}
					else {
						throw new IllegalArgumentException("Unable to handle change attribute: " + ca.getAttribute());
					}
				} else if (s instanceof DeleteNode) {
					var dn = (DeleteNode<FamilyRegister>) s;
					if (dn.getNode() instanceof Family) {
						var rule = familyAPI.getRule_DeleteFamily();
						var mask = rule.mask();
						mask.addParameter(rule._param__id, dn.getNode().hashCode());
						rule.apply(mask, mask);
					} else if (dn.getNode() instanceof FamilyMember) {
						var rule = familyAPI.getRule_DeleteFamilyMember();
						var mask = rule.mask();
						mask.addParameter(rule._param__id, dn.getNode().hashCode());
						rule.apply(mask, mask);
					} else {
						throw new IllegalArgumentException("Unable to delete node: " + dn.getNode());
					}
				} else if (s instanceof MoveNode) {
					var mn = (MoveNode<FamilyRegister>) s;
					deleteSourceEdge(familyAPI, mn.getDeleteEdge());
					createSourceEdge(familyAPI, mn.getCreateEdge());

					if (mn.getNode() instanceof FamilyMember) {
						var rule = familyAPI.getRule_SetFamilyMemberAsToBeCreated();
						var mask = rule.mask();
						mask.addParameter(rule._param__id, mn.getNode().hashCode());
						rule.apply(mask, mask);
					} else {
						throw new IllegalArgumentException("Unable to mark node as created: " + mn.getNode());
					}
				} else if (s instanceof DeleteEdge) {
					var de = (DeleteEdge<FamilyRegister>) s;
					deleteSourceEdge(familyAPI, de);
				}
				else {
					throw new IllegalArgumentException("Unable to handle atomic edit: " + s);
				}
			}
			for (var t : targetEdit.get().getSteps()) {
				if (t instanceof CreateNode) {
					var cn = (CreateNode<PersonRegister>) t;

					if (cn.getNode() instanceof Male) {
						var p = (Male) cn.getNode();
						var rule = personsAPI.getRule_CreateMale();
						var mask = rule.mask();
						mask.addParameter(rule._param__name, p.getName());
						mask.addParameter(rule._param__id, p.hashCode());
						mask.addParameter(rule._param__bday,
								LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthday())));
						mask.addParameter(rule._param__namespace, F2P_GEN_Run.TRG_MODEL_NAME);
						rule.apply(mask, mask);
					} else if (cn.getNode() instanceof Female) {
						var p = (Female) cn.getNode();
						var rule = personsAPI.getRule_CreateFemale();
						var mask = rule.mask();
						mask.addParameter(rule._param__name, p.getName());
						mask.addParameter(rule._param__id, p.hashCode());
						mask.addParameter(rule._param__bday,
								LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthday())));
						mask.addParameter(rule._param__namespace, F2P_GEN_Run.TRG_MODEL_NAME);
						rule.apply(mask, mask);
					} else {
						throw new IllegalArgumentException("Unable to handle created node: " + cn.getNode());
					}
				} else if (t instanceof DeleteNode) {
					var dn = (DeleteNode<PersonRegister>) t;
					var rule = personsAPI.getRule_DeletePerson();
					var mask = rule.mask();
					mask.addParameter(rule._param__id, dn.getNode().hashCode());
					rule.apply(mask, mask);
				} else if (t instanceof CreateEdge) {
					var ce = (CreateEdge<PersonRegister>) t;
					if (ce.getType().equals(PersonsPackage.Literals.PERSON_REGISTER__PERSONS)) {
						var rule = personsAPI.getRule_CreateRegisterPersonEdge();
						var mask = rule.mask();
						mask.addParameter(rule._param__id, ce.getTarget().hashCode());
						rule.apply(mask, mask);
					} else {
						throw new IllegalArgumentException("Unable to handle created edge: " + ce.getType());
					}
				} else if (t instanceof DeleteEdge) {
					var de = (DeleteEdge<PersonRegister>) t;
					if (de.getType().equals(PersonsPackage.Literals.PERSON_REGISTER__PERSONS)) {
						var rule = personsAPI.getRule_DeleteRegisterPersonEdge();
						var mask = rule.mask();
						mask.addParameter(rule._param__id, de.getTarget().hashCode());
						rule.apply(mask, mask);
					}
				} else if (t instanceof ChangeAttribute) {
					var ca = (ChangeAttribute<PersonRegister>) t;
					if (ca.getAttribute().equals(PersonsPackage.Literals.PERSON__BIRTHDAY)) {
						var rule = personsAPI.getRule_ChangeBirthday();
						var mask = rule.mask();
						mask.addParameter(rule._param__bday,
								LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(ca.getNewValue())));
						mask.addParameter(rule._param__id, ca.getNode().hashCode());
						rule.apply(mask, mask);
					} else if (ca.getAttribute().equals(PersonsPackage.Literals.PERSON__NAME)) {
						var rule = personsAPI.getRule_ChangeName();
						var mask = rule.mask();
						mask.addParameter(rule._param__name, ca.getNewValue());
						mask.addParameter(rule._param__id, ca.getNode().hashCode());
						rule.apply(mask, mask);
					} else {
						throw new IllegalArgumentException("Unable to handle changed attribute: " + ca.getAttribute());
					}
				} else {
					throw new IllegalArgumentException("Unable to handle atomic edit: " + t);
				}
			}

			post.accept(builder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createSourceEdge(API_Families familyAPI, CreateEdge<FamilyRegister> ce) {
		if (ce.getType().equals(FamiliesPackage.Literals.FAMILY_REGISTER__FAMILIES)) {
			var rule = familyAPI.getRule_CreateRegisterFamilyEdge();
			var family = (Family) ce.getTarget();
			var mask = rule.mask();
			mask.addParameter(rule._param__name, family.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__id, family.hashCode());
			rule.apply(mask, mask);
		} else if (ce.getType().equals(FamiliesPackage.Literals.FAMILY__SONS)) {
			var rule = familyAPI.getRule_CreateFamilySonEdge();
			var mask = rule.mask();
			var family = (Family) ce.getSource();
			mask.addParameter(rule._param__fname, family.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__fid, family.hashCode());
			var member = (FamilyMember) ce.getTarget();
			mask.addParameter(rule._param__name, member.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__id, member.hashCode());
			rule.apply(mask, mask);
		} else if (ce.getType().equals(FamiliesPackage.Literals.FAMILY__DAUGHTERS)) {
			var rule = familyAPI.getRule_CreateFamilyDaughterEdge();
			var mask = rule.mask();
			var family = (Family) ce.getSource();
			mask.addParameter(rule._param__fname, family.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__fid, family.hashCode());
			var member = (FamilyMember) ce.getTarget();
			mask.addParameter(rule._param__name, member.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__id, member.hashCode());
			rule.apply(mask, mask);
		} else if (ce.getType().equals(FamiliesPackage.Literals.FAMILY__MOTHER)) {
			var rule = familyAPI.getRule_CreateFamilyMotherEdge();
			var mask = rule.mask();
			var family = (Family) ce.getSource();
			mask.addParameter(rule._param__fname, family.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__fid, family.hashCode());
			var member = (FamilyMember) ce.getTarget();
			mask.addParameter(rule._param__name, member.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__id, member.hashCode());
			rule.apply(mask, mask);
		} else if (ce.getType().equals(FamiliesPackage.Literals.FAMILY__FATHER)) {
			var rule = familyAPI.getRule_CreateFamilyFatherEdge();
			var mask = rule.mask();
			var family = (Family) ce.getSource();
			mask.addParameter(rule._param__fname, family.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__fid, family.hashCode());
			var member = (FamilyMember) ce.getTarget();
			mask.addParameter(rule._param__name, member.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__id, member.hashCode());
			rule.apply(mask, mask);
		} else {
			throw new IllegalArgumentException("Unable to handle created edge: " + ce.getType());
		}
	}

	private void deleteSourceEdge(API_Families familyAPI, DeleteEdge<FamilyRegister> de) {
		if (de.getType() == FamiliesPackage.Literals.FAMILY_REGISTER__FAMILIES) {
			var rule = familyAPI.getRule_DeleteRegisterFamilyEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, de.getTarget().hashCode());
			rule.apply(mask, mask);
		} else if (de.getType() == FamiliesPackage.Literals.FAMILY__FATHER) {
			var rule = familyAPI.getRule_DeleteFamilyFatherEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, de.getTarget().hashCode());
			rule.apply(mask, mask);
		} else if (de.getType() == FamiliesPackage.Literals.FAMILY__MOTHER) {
			var rule = familyAPI.getRule_DeleteFamilyMotherEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, de.getTarget().hashCode());
			rule.apply(mask, mask);
		} else if (de.getType() == FamiliesPackage.Literals.FAMILY__SONS) {
			var rule = familyAPI.getRule_DeleteFamilySonEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, de.getTarget().hashCode());
			rule.apply(mask, mask);
		} else if (de.getType() == FamiliesPackage.Literals.FAMILY__DAUGHTERS) {
			var rule = familyAPI.getRule_DeleteFamilyDaughterEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, de.getTarget().hashCode());
			rule.apply(mask, mask);
		} else {
			throw new IllegalArgumentException("Unable to delete edge: " + de.getType());
		}
	}

	@Override
	public void performIdleSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		performAndPropagateSourceEdit(edit);
	}

	@Override
	public void performIdleTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		performAndPropagateTargetEdit(edit);
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		this.configurator = configurator;
	}

	@Override
	public FamilyRegister getSourceModel() {
		return sourceRegister;
	}

	@Override
	public PersonRegister getTargetModel() {
		return targetRegister;
	}
}
