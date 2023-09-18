package org.benchmarx.eneo.f2p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.benchmarx.examples.familiestopersons.implementations.eneo.ENeoEdit;
import org.benchmarx.examples.familiestopersons.implementations.eneo.ENeoInput;
import org.benchmarx.examples.familiestopersons.implementations.eneo.ENeoOutput;
import org.benchmarx.examples.familiestopersons.implementations.eneo.MODE;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Families.FamiliesFactory;
import Persons.PersonsFactory;

public class ENeoRunner {
	private static SupportedILPSolver solver = SupportedILPSolver.Sat4J;

	public static void main(String[] args) {
		var stdout = System.out;
		System.setOut(System.err);

		var runner = new ENeoRunner();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		ENeoInput input = gson.fromJson(args[0], ENeoInput.class);

		if (input.getMode() == MODE.INIT) {
			runner.initialise();
			System.out.println("Initialisation done.");
			System.setOut(stdout);
			System.out.println(gson.toJson(new ENeoOutput()));
		} else if (input.getMode() == MODE.PRECOND) {
			runner.createDeltasInDatabase(input.getSourceEdit(), input.getTargetEdit(), //
					(builder) -> runner.initialise(), //
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

			System.out.println("Precondition set.");
			System.setOut(stdout);
			System.out.println(gson.toJson(new ENeoOutput()));
		} else if (input.getMode() == MODE.PROPAGATE) {
			runner.createDeltasInDatabase(input.getSourceEdit(), input.getTargetEdit(), //
					(builder) -> {
					}, //
					(builder) -> {
					});
			try {
				var mi = new F2P_MI(
						input.getPreferParents() == null ? Optional.empty() : Optional.of(input.getPreferParents()),
						input.getPreferExistingFamilies() == null ? Optional.empty()
								: Optional.of(input.getPreferExistingFamilies()),
						solver);
				mi.runModelIntegration();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Propagation done.");
			System.setOut(stdout);
			System.out.println(gson.toJson(new ENeoOutput()));
		} else if (input.getMode() == MODE.POSTCOND) {
			var output = runner.extractPostCondition();
			System.out.println("Extracted postcondition.");
			System.setOut(stdout);
			System.out.println(gson.toJson(output));
		}
	}

	private ENeoOutput extractPostCondition() {
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

		var output = new ENeoOutput();
		output.setSource(new FamiliesComparator().familyToString(actualSource));
		output.setTarget(new PersonsComparator().personsToString(actualTarget));
		return output;
	}

	private void initialise() {
		try (var builder = API_Common.createBuilder()) {
			builder.clearDataBase();
			var gen = new F2P_GEN_InitiateSyncDialogue();
			gen.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createDeltasInDatabase(List<ENeoEdit> sourceEdit, List<ENeoEdit> targetEdit,
			Consumer<NeoCoreBuilder> pre, Consumer<NeoCoreBuilder> post) {
		try (var builder = API_Common.createBuilder()) {
			pre.accept(builder);

			for (var s : sourceEdit)
				handleSourceEdit(builder, s);

			for (var t : targetEdit)
				handleTargetEdit(builder, t);

			post.accept(builder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleTargetEdit(NeoCoreBuilder builder, ENeoEdit t) {
		var personsAPI = new API_Persons(builder);

		switch (t.getEditType()) {
		case CREATE_MALE: {
			var rule = personsAPI.getRule_CreateMale();
			var mask = rule.mask();
			mask.addParameter(rule._param__name, t.getName());
			mask.addParameter(rule._param__id, t.getId());
			mask.addParameter(rule._param__bday, LocalDate.parse(t.getBirthday()));
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.TRG_MODEL_NAME);
			rule.apply(mask, mask);
			break;
		}
		case CREATE_FEMALE: {
			var rule = personsAPI.getRule_CreateFemale();
			var mask = rule.mask();
			mask.addParameter(rule._param__name, t.getName());
			mask.addParameter(rule._param__id, t.getId());
			mask.addParameter(rule._param__bday, LocalDate.parse(t.getBirthday()));
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.TRG_MODEL_NAME);
			rule.apply(mask, mask);
			break;
		}
		case DELETE_PERSON: {
			var rule = personsAPI.getRule_DeletePerson();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, t.getId());
			rule.apply(mask, mask);
			break;
		}
		case CREATE_PERSON_REGISTER__PERSONS: {
			var rule = personsAPI.getRule_CreateRegisterPersonEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, t.getId());
			rule.apply(mask, mask);
			break;
		}
		case DELETE_PERSON_REGISTER__PERSONS: {
			var rule = personsAPI.getRule_DeleteRegisterPersonEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, t.getId());
			rule.apply(mask, mask);
			break;
		}
		case CHANGE_BIRTHDAY: {
			var rule = personsAPI.getRule_ChangeBirthday();
			var mask = rule.mask();
			mask.addParameter(rule._param__bday, LocalDate.parse((String) t.getNewValue()));
			mask.addParameter(rule._param__id, t.getId());
			rule.apply(mask, mask);
			break;
		}
		case CHANGE_PERSON__NAME: {
			var rule = personsAPI.getRule_ChangeName();
			var mask = rule.mask();
			mask.addParameter(rule._param__name, t.getNewValue());
			mask.addParameter(rule._param__id, t.getId());
			rule.apply(mask, mask);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + t.getEditType());
		}
	}

	private void handleSourceEdit(NeoCoreBuilder builder, ENeoEdit s) {
		var familyAPI = new API_Families(builder);

		switch (s.getEditType()) {
		case CREATE_FAMILY: {
			var rule = familyAPI.getRule_CreateFamily();
			var mask = rule.mask();
			mask.addParameter(rule._param__name, s.getName());
			mask.addParameter(rule._param__id, s.getId());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			rule.apply(mask, mask);
			break;
		}
		case CREATE_FAMILY_MEMBER: {
			var rule = familyAPI.getRule_CreateFamilyMember();
			var mask = rule.mask();
			mask.addParameter(rule._param__name, s.getName());
			mask.addParameter(rule._param__id, s.getId());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			rule.apply(mask, mask);
			break;
		}
		case CREATE_FAMILY_REGISTER__FAMILIES: {
			var rule = familyAPI.getRule_CreateRegisterFamilyEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__name, s.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__id, s.getId());
			rule.apply(mask, mask);
			break;
		}
		case CREATE_FAMILY__SONS: {
			var rule = familyAPI.getRule_CreateFamilySonEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__fname, s.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__fid, s.getId());
			mask.addParameter(rule._param__name, s.getTrgName());
			mask.addParameter(rule._param__id, s.getTrgId());
			rule.apply(mask, mask);
			break;
		}
		case CREATE_FAMILY__DAUGHTERS: {
			var rule = familyAPI.getRule_CreateFamilyDaughterEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__fname, s.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__fid, s.getId());
			mask.addParameter(rule._param__name, s.getTrgName());
			mask.addParameter(rule._param__id, s.getTrgId());
			rule.apply(mask, mask);
			break;
		}
		case CREATE_FAMILY__MOTHER: {
			var rule = familyAPI.getRule_CreateFamilyMotherEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__fname, s.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__fid, s.getId());
			mask.addParameter(rule._param__name, s.getTrgName());
			mask.addParameter(rule._param__id, s.getTrgId());
			rule.apply(mask, mask);
			break;
		}
		case CREATE_FAMILY__FATHER: {
			var rule = familyAPI.getRule_CreateFamilyFatherEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__fname, s.getName());
			mask.addParameter(rule._param__namespace, F2P_GEN_Run.SRC_MODEL_NAME);
			mask.addParameter(rule._param__fid, s.getId());
			mask.addParameter(rule._param__name, s.getTrgName());
			mask.addParameter(rule._param__id, s.getTrgId());
			rule.apply(mask, mask);
			break;
		}
		case CHANGE_FAMILY__NAME: {
			var rule = familyAPI.getRule_ChangeNameOfFamily();
			var mask = rule.mask();
			mask.addParameter(rule._param__name, s.getNewValue());
			mask.addParameter(rule._param__id, s.getId());
			rule.apply(mask, mask);
			break;
		}
		case DELETE_FAMILY: {
			var rule = familyAPI.getRule_DeleteFamily();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, s.getId());
			rule.apply(mask, mask);
			break;
		}
		case DELETE_FAMILY_MEMBER: {
			var rule = familyAPI.getRule_DeleteFamilyMember();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, s.getId());
			rule.apply(mask, mask);
			break;
		}
		case DELETE_FAMILY_REGISTER__FAMILIES: {
			var rule = familyAPI.getRule_DeleteRegisterFamilyEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, s.getId());
			rule.apply(mask, mask);
			break;
		}
		case DELETE_FAMILY__FATHER: {
			var rule = familyAPI.getRule_DeleteFamilyFatherEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, s.getId());
			rule.apply(mask, mask);
			break;
		}
		case DELETE_FAMILY__MOTHER: {
			var rule = familyAPI.getRule_DeleteFamilyMotherEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, s.getId());
			rule.apply(mask, mask);
			break;
		}
		case DELETE_FAMILY__SONS: {
			var rule = familyAPI.getRule_DeleteFamilySonEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, s.getId());
			rule.apply(mask, mask);
			break;
		}
		case DELETE_FAMILY__DAUGHTERS: {
			var rule = familyAPI.getRule_DeleteFamilyDaughterEdge();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, s.getId());
			rule.apply(mask, mask);
			break;
		}
		case MOVE_FAMILY_MEMBER: {
			handleSourceEdit(builder, s.getDeleteEdge());
			handleSourceEdit(builder, s.getCreateEdge());
			var rule = familyAPI.getRule_SetFamilyMemberAsToBeCreated();
			var mask = rule.mask();
			mask.addParameter(rule._param__id, s.getId());
			rule.apply(mask, mask);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + s.getEditType());
		}
	}
}
