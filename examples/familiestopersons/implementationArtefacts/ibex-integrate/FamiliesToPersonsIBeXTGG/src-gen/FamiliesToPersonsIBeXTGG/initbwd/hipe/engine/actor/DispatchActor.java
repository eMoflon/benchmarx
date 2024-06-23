package FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EObject;

import java.text.DecimalFormat;
import java.util.Collection;
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
				
	private Map<String, Collection<ActorRef>> name2actor;
	
	private Map<Object, Consumer<Object>> type2addConsumer = CollectionUtil.createMap();
	private Map<Object, Consumer<Notification>> feature2setConsumer = CollectionUtil.createMap();
	private Map<Object, Consumer<Notification>> feature2addEdgeConsumer = CollectionUtil.createMap();
	private Map<Object, Consumer<Notification>> feature2removeEdgeConsumer = CollectionUtil.createMap();
	
	private ActorRef family_object;
	private ActorRef female_object;
	private ActorRef daughterOfExistingFamilyToFemale__BWD_1;
	private ActorRef daughterToFemale__BWD_7;
	private ActorRef families2Persons__BWD_12;
	private ActorRef male_object;
	private ActorRef fatherOfExistingFamilyToMale__BWD_14;
	private ActorRef fatherToMale__BWD_20;
	private ActorRef motherOfExistingFamilyToFemale__BWD_25;
	private ActorRef motherToFemale__BWD_31;
	private ActorRef sonOfExistingFamilyToMale__BWD_36;
	private ActorRef sonToMale__BWD_42;
	private ActorRef familyRegister_object_SP0;
	private ActorRef familyRegister_object_SP1;
	private ActorRef registerToRegisterCorr_object_SP0;
	private ActorRef registerToRegisterCorr_object_SP1;
	private ActorRef personRegister_object_SP0;
	private ActorRef personRegister_object_SP1;
	private ActorRef personRegister_object_SP2;
	
	private IncUtil incUtil;
	
	private ActorMaterializer materializer;
	
	public DispatchActor(Map<String, Collection<ActorRef>> name2actor, IncUtil incUtil) {
		this.name2actor = name2actor;
		this.incUtil = incUtil;
		
		initializeFields();
		initializeAdd();
		initializeSet();
		initializeAddEdge();
		initializeRemoveEdge();
	
		materializer = ActorMaterializer.create(getContext());
	}
	
	private void initializeFields() {
		family_object = name2actor.get("Family_object").iterator().next();
		female_object = name2actor.get("Female_object").iterator().next();
		daughterOfExistingFamilyToFemale__BWD_1 = name2actor.get("DaughterOfExistingFamilyToFemale__BWD_1").iterator().next();
		daughterToFemale__BWD_7 = name2actor.get("DaughterToFemale__BWD_7").iterator().next();
		families2Persons__BWD_12 = name2actor.get("Families2Persons__BWD_12").iterator().next();
		male_object = name2actor.get("Male_object").iterator().next();
		fatherOfExistingFamilyToMale__BWD_14 = name2actor.get("FatherOfExistingFamilyToMale__BWD_14").iterator().next();
		fatherToMale__BWD_20 = name2actor.get("FatherToMale__BWD_20").iterator().next();
		motherOfExistingFamilyToFemale__BWD_25 = name2actor.get("MotherOfExistingFamilyToFemale__BWD_25").iterator().next();
		motherToFemale__BWD_31 = name2actor.get("MotherToFemale__BWD_31").iterator().next();
		sonOfExistingFamilyToMale__BWD_36 = name2actor.get("SonOfExistingFamilyToMale__BWD_36").iterator().next();
		sonToMale__BWD_42 = name2actor.get("SonToMale__BWD_42").iterator().next();
		familyRegister_object_SP0 = name2actor.get("FamilyRegister_object_SP0").iterator().next();
		familyRegister_object_SP1 = name2actor.get("FamilyRegister_object_SP1").iterator().next();
		registerToRegisterCorr_object_SP0 = name2actor.get("RegisterToRegisterCorr_object_SP0").iterator().next();
		registerToRegisterCorr_object_SP1 = name2actor.get("RegisterToRegisterCorr_object_SP1").iterator().next();
		personRegister_object_SP0 = name2actor.get("PersonRegister_object_SP0").iterator().next();
		personRegister_object_SP1 = name2actor.get("PersonRegister_object_SP1").iterator().next();
		personRegister_object_SP2 = name2actor.get("PersonRegister_object_SP2").iterator().next();
	}
	
	private void initializeAdd() {
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(), (obj) -> {
			PersonsSmartEMF.PersonRegister _personregister = (PersonsSmartEMF.PersonRegister) obj;
			incUtil.newMessage();
			personRegister_object_SP0.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP1.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP2.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
		});
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getFemale(), (obj) -> {
			PersonsSmartEMF.Female _female = (PersonsSmartEMF.Female) obj;
			incUtil.newMessage();
			female_object.tell(new ObjectAdded<PersonsSmartEMF.Female>(incUtil, _female), getSelf());
		});
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(), (obj) -> {
			FamiliesSmartEMF.FamilyRegister _familyregister = (FamiliesSmartEMF.FamilyRegister) obj;
			incUtil.newMessage();
			familyRegister_object_SP0.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP1.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
		});
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getMale(), (obj) -> {
			PersonsSmartEMF.Male _male = (PersonsSmartEMF.Male) obj;
			incUtil.newMessage();
			male_object.tell(new ObjectAdded<PersonsSmartEMF.Male>(incUtil, _male), getSelf());
		});
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily(), (obj) -> {
			FamiliesSmartEMF.Family _family = (FamiliesSmartEMF.Family) obj;
			incUtil.newMessage();
			family_object.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr(), (obj) -> {
			FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr _registertoregistercorr = (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) obj;
			incUtil.newMessage();
			registerToRegisterCorr_object_SP0.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
			incUtil.newMessage();
			registerToRegisterCorr_object_SP1.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
		});
	}
	
	private void initializeSet() {
	}
	
	private void initializeAddEdge() {
		feature2addEdgeConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_1.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_7.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_14.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_20.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_25.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_31.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_36.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_42.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_1.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_14.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_25.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_36.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_1.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_7.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_14.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_20.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_25.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_31.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_36.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_42.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_1.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_7.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_14.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_20.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_25.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_31.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_36.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_42.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
		});
	}
	
	private void initializeRemoveEdge() {
		feature2removeEdgeConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_1.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_7.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_14.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_20.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_25.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_31.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_36.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_42.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_1.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_14.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_25.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_36.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_1.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_7.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_14.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_20.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_25.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_31.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_36.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_42.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_1.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_7.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_14.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_20.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_25.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_31.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_36.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_42.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
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
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP0.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP1.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Female) {
			incUtil.newMessage();
			female_object.tell(new ObjectDeleted<PersonsSmartEMF.Female>(incUtil, (PersonsSmartEMF.Female) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Male) {
			incUtil.newMessage();
			male_object.tell(new ObjectDeleted<PersonsSmartEMF.Male>(incUtil, (PersonsSmartEMF.Male) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP0.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP1.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP2.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			registerToRegisterCorr_object_SP0.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			registerToRegisterCorr_object_SP1.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
	}
}

