package FamiliesToPersonsIBeXTGG.co.hipe.engine.actor;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EObject;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.*;
import static akka.pattern.Patterns.ask;

import hipe.engine.util.CollectionUtil;
import hipe.engine.util.IncUtil;
import hipe.engine.message.NewInput;
import hipe.engine.message.NoMoreInput;
import hipe.engine.message.input.ObjectAdded;
import hipe.engine.message.input.ObjectDeleted;
import hipe.engine.message.input.ReferenceAdded;
import hipe.engine.message.input.ReferenceDeleted;		
import hipe.engine.message.input.AttributeChanged;
import hipe.engine.message.input.NotificationContainer;

import hipe.generic.actor.junction.util.HiPEConfig;

public class DispatchActor extends AbstractActor {
	
	private int counter = 0;
	public long time = 0;
				
	private Map<String, ActorRef> name2actor;
	
	private Map<Object, Consumer<Object>> type2addConsumer = CollectionUtil.createMap();
	private Map<Object, Consumer<Notification>> feature2setConsumer = CollectionUtil.createMap();
	private Map<Object, Consumer<Notification>> feature2addEdgeConsumer = CollectionUtil.createMap();
	private Map<Object, Consumer<Notification>> feature2removeEdgeConsumer = CollectionUtil.createMap();
	
	private IncUtil incUtil;
	
	private ActorMaterializer materializer;
	
	public DispatchActor(Map<String, ActorRef> name2actor, IncUtil incUtil) {
		this.name2actor = name2actor;
		this.incUtil = incUtil;
		
		initializeAdd();
		initializeSet();
		initializeAddEdge();
		initializeRemoveEdge();
	
		materializer = ActorMaterializer.create(getContext());
	}
	
	private void initializeAdd() {
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getFemale(), obj -> {
			PersonsSmartEMF.Female _female = (PersonsSmartEMF.Female) obj;
			incUtil.newMessage();
			name2actor.get("Female_object").tell(new ObjectAdded<PersonsSmartEMF.Female>(incUtil, _female), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr(), obj -> {
			FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr _registertoregistercorr = (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) obj;
			incUtil.newMessage();
			name2actor.get("RegisterToRegisterCorr_object").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
		});
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily(), obj -> {
			FamiliesSmartEMF.Family _family = (FamiliesSmartEMF.Family) obj;
			incUtil.newMessage();
			name2actor.get("Family_object_SP0").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			name2actor.get("Family_object_SP1").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			name2actor.get("Family_object_SP2").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
		});
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(), obj -> {
			PersonsSmartEMF.PersonRegister _personregister = (PersonsSmartEMF.PersonRegister) obj;
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP0").tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP1").tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP2").tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
		});
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getMale(), obj -> {
			PersonsSmartEMF.Male _male = (PersonsSmartEMF.Male) obj;
			incUtil.newMessage();
			name2actor.get("Male_object").tell(new ObjectAdded<PersonsSmartEMF.Male>(incUtil, _male), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr(), obj -> {
			FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr _familymembertopersoncorr = (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) obj;
			incUtil.newMessage();
			name2actor.get("FamilyMemberToPersonCorr_object_SP0").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, _familymembertopersoncorr), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyMemberToPersonCorr_object_SP1").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, _familymembertopersoncorr), getSelf());
		});
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(), obj -> {
			FamiliesSmartEMF.FamilyMember _familymember = (FamiliesSmartEMF.FamilyMember) obj;
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP0").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP1").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
		});
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(), obj -> {
			FamiliesSmartEMF.FamilyRegister _familyregister = (FamiliesSmartEMF.FamilyRegister) obj;
			incUtil.newMessage();
			name2actor.get("FamilyRegister_object_SP0").tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyRegister_object_SP1").tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
		});
	}
	
	private void initializeSet() {
		feature2setConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Name(), notification -> {
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP2").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP1").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP0").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
		});
		
		feature2setConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPerson_Name(), notification -> {
			if(notification.getNotifier() instanceof PersonsSmartEMF.Male) {
				incUtil.newMessage();
				name2actor.get("Male_object").tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof PersonsSmartEMF.Female) {
				incUtil.newMessage();
				name2actor.get("Female_object").tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
		});
		
		feature2setConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember_Name(), notification -> {
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP0").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP1").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
		});
		
	}
	
	private void initializeAddEdge() {
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Source(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CO_4").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CO_10").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CO_21").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CO_27").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CO_34").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CO_40").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CO_47").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CO_53").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Father(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CO_21").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CO_27").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Daughters(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CO_4").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CO_10").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), notification -> {
			incUtil.newMessage();
			name2actor.get("CreateFamily__CO_1").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CO_10").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CO_27").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CO_40").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CO_53").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CO_17").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Sons(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CO_47").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CO_53").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Mother(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CO_34").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CO_40").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CO_4").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CO_10").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CO_21").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CO_27").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CO_34").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CO_40").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CO_47").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CO_53").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CO_17").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Target(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CO_4").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CO_10").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CO_21").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CO_27").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CO_34").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CO_40").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CO_47").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CO_53").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
		});
	}
	
	private void initializeRemoveEdge() {
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Source(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CO_4").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CO_10").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CO_21").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CO_27").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CO_34").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CO_40").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CO_47").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CO_53").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Father(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CO_21").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CO_27").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Daughters(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CO_4").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CO_10").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), notification -> {
			incUtil.newMessage();
			name2actor.get("CreateFamily__CO_1").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CO_10").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CO_27").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CO_40").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CO_53").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CO_17").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Sons(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CO_47").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CO_53").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Mother(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CO_34").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CO_40").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CO_4").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CO_10").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CO_21").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CO_27").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CO_34").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CO_40").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CO_47").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CO_53").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CO_17").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Target(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CO_4").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CO_10").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CO_21").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CO_27").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CO_34").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CO_40").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CO_47").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CO_53").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
		});
	}

	@Override
	public void preStart() throws Exception {
		super.preStart();
	}

	@Override
	public void postStop() throws Exception {
		if(HiPEConfig.logWorkloadActivated) {
			DecimalFormat df = new DecimalFormat("0.#####");
	        df.setMaximumFractionDigits(5);
			System.err.println("DispatchNode" + ";"  + counter + ";" + df.format((double) time / (double) (1000 * 1000 * 1000)));
		}
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder() //
				.match(NotificationContainer.class, this::handleNotificationContainer)
				.match(NoMoreInput.class, this::sendFinished) //
				.build();
	}

	private void sendFinished(NoMoreInput m) {
		incUtil.allMessagesInserted();
	}
	
	private void handleNotificationContainer(NotificationContainer nc) {
		counter++;
		long tic = System.nanoTime();
		nc.notifications.parallelStream().forEach(this::handleNotification);
		time += System.nanoTime() - tic;
	}
	
	private void handleNotification(Notification notification) {
		switch (notification.getEventType()) {
		case Notification.ADD:
			handleAdd(notification);
			break;
		case Notification.REMOVE:
			handleRemove(notification);
			break;
		case Notification.REMOVING_ADAPTER:
			handleRemoveAdapter(notification);
			break;	
		case Notification.SET:
			handleSet(notification);
			break;
		}
	}

	private void handleAdd(Notification notification) {
		if(notification.getFeature() == null) 
			handleAddedNode(notification.getNewValue());
		else
			handleAddedEdge(notification);
	}

	private void handleAddedNode(Object node) {
		if(node == null) 
			return;
			
		EObject obj = (EObject) node;
		if(type2addConsumer.containsKey(obj.eClass())) {
			type2addConsumer.get(obj.eClass()).accept(node);
		}
	}
	
	private void handleSet(Notification notification) {
		Object feature = notification.getFeature();
		if(feature2setConsumer.containsKey(feature)) {
			feature2setConsumer.get(feature).accept(notification);
		}
	}

	private void handleAddedEdge(Notification notification) {
		//check for self-edges
		if(notification.getNotifier().equals(notification.getNewValue()))
			handleAddedNode(notification.getNewValue());
					
		Object feature = notification.getFeature();
		if(feature2addEdgeConsumer.containsKey(feature)) {
			feature2addEdgeConsumer.get(feature).accept(notification);
		}
	}

	private void handleRemove(Notification notification) {
		Object feature = notification.getFeature();
		if(feature2removeEdgeConsumer.containsKey(feature)) {
			feature2removeEdgeConsumer.get(feature).accept(notification);
		}
	}
	
	private void handleRemoveAdapter(Notification notification) {
		Object node = notification.getNotifier();
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			name2actor.get("FamilyRegister_object_SP0").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			name2actor.get("FamilyRegister_object_SP1").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			name2actor.get("Family_object_SP0").tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			name2actor.get("Family_object_SP1").tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			name2actor.get("Family_object_SP2").tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP0").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP1").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Female) {
			incUtil.newMessage();
			name2actor.get("Female_object").tell(new ObjectDeleted<PersonsSmartEMF.Female>(incUtil, (PersonsSmartEMF.Female) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Male) {
			incUtil.newMessage();
			name2actor.get("Male_object").tell(new ObjectDeleted<PersonsSmartEMF.Male>(incUtil, (PersonsSmartEMF.Male) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP0").tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP1").tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP2").tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			name2actor.get("RegisterToRegisterCorr_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) {
			incUtil.newMessage();
			name2actor.get("FamilyMemberToPersonCorr_object_SP0").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) {
			incUtil.newMessage();
			name2actor.get("FamilyMemberToPersonCorr_object_SP1").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) node), getSelf());
		}
	}
}

