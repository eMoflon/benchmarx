package FamiliesToPersonsIBeXTGG.initfwd.hipe.engine.actor;

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
	
	private ActorRef createFamily__FWD_1;
	private ActorRef daughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_4;
	private ActorRef daughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_7;
	private ActorRef daughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_10;
	private ActorRef daughterOfExistingFamilyToFemale__FWD_13;
	private ActorRef daughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_19;
	private ActorRef daughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_22;
	private ActorRef daughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_25;
	private ActorRef daughterToFemale__FWD_28;
	private ActorRef families2Persons__FWD_34;
	private ActorRef fatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_36;
	private ActorRef fatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_39;
	private ActorRef fatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_42;
	private ActorRef fatherOfExistingFamilyToMale__FWD_45;
	private ActorRef fatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_51;
	private ActorRef fatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_54;
	private ActorRef fatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_57;
	private ActorRef fatherToMale__FWD_60;
	private ActorRef motherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_66;
	private ActorRef motherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_69;
	private ActorRef motherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_72;
	private ActorRef motherOfExistingFamilyToFemale__FWD_75;
	private ActorRef motherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_81;
	private ActorRef motherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_84;
	private ActorRef motherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_87;
	private ActorRef motherToFemale__FWD_90;
	private ActorRef sonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_96;
	private ActorRef sonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_99;
	private ActorRef sonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_102;
	private ActorRef sonOfExistingFamilyToMale__FWD_105;
	private ActorRef sonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_111;
	private ActorRef sonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_114;
	private ActorRef sonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_117;
	private ActorRef sonToMale__FWD_120;
	private ActorRef familyRegister_object_SP0;
	private ActorRef familyRegister_object_SP1;
	private ActorRef familyRegister_object_SP2;
	private ActorRef family_object_SP0;
	private ActorRef family_object_SP1;
	private ActorRef family_object_SP2;
	private ActorRef family_object_SP3;
	private ActorRef family_object_SP4;
	private ActorRef family_object_SP5;
	private ActorRef family_object_SP6;
	private ActorRef family_object_SP7;
	private ActorRef family_object_SP8;
	private ActorRef familyMember_object_SP0;
	private ActorRef familyMember_object_SP1;
	private ActorRef familyMember_object_SP2;
	private ActorRef familyMember_object_SP3;
	private ActorRef familyMember_object_SP4;
	private ActorRef familyMember_object_SP5;
	private ActorRef familyMember_object_SP6;
	private ActorRef familyMember_object_SP7;
	private ActorRef registerToRegisterCorr_object_SP0;
	private ActorRef registerToRegisterCorr_object_SP1;
	private ActorRef personRegister_object_SP0;
	private ActorRef personRegister_object_SP1;
	
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
		createFamily__FWD_1 = name2actor.get("CreateFamily__FWD_1").iterator().next();
		daughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_4 = name2actor.get("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_4").iterator().next();
		daughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_7 = name2actor.get("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_7").iterator().next();
		daughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_10 = name2actor.get("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_10").iterator().next();
		daughterOfExistingFamilyToFemale__FWD_13 = name2actor.get("DaughterOfExistingFamilyToFemale__FWD_13").iterator().next();
		daughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_19 = name2actor.get("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_19").iterator().next();
		daughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_22 = name2actor.get("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_22").iterator().next();
		daughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_25 = name2actor.get("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_25").iterator().next();
		daughterToFemale__FWD_28 = name2actor.get("DaughterToFemale__FWD_28").iterator().next();
		families2Persons__FWD_34 = name2actor.get("Families2Persons__FWD_34").iterator().next();
		fatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_36 = name2actor.get("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_36").iterator().next();
		fatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_39 = name2actor.get("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_39").iterator().next();
		fatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_42 = name2actor.get("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_42").iterator().next();
		fatherOfExistingFamilyToMale__FWD_45 = name2actor.get("FatherOfExistingFamilyToMale__FWD_45").iterator().next();
		fatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_51 = name2actor.get("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_51").iterator().next();
		fatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_54 = name2actor.get("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_54").iterator().next();
		fatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_57 = name2actor.get("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_57").iterator().next();
		fatherToMale__FWD_60 = name2actor.get("FatherToMale__FWD_60").iterator().next();
		motherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_66 = name2actor.get("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_66").iterator().next();
		motherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_69 = name2actor.get("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_69").iterator().next();
		motherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_72 = name2actor.get("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_72").iterator().next();
		motherOfExistingFamilyToFemale__FWD_75 = name2actor.get("MotherOfExistingFamilyToFemale__FWD_75").iterator().next();
		motherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_81 = name2actor.get("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_81").iterator().next();
		motherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_84 = name2actor.get("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_84").iterator().next();
		motherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_87 = name2actor.get("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_87").iterator().next();
		motherToFemale__FWD_90 = name2actor.get("MotherToFemale__FWD_90").iterator().next();
		sonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_96 = name2actor.get("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_96").iterator().next();
		sonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_99 = name2actor.get("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_99").iterator().next();
		sonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_102 = name2actor.get("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_102").iterator().next();
		sonOfExistingFamilyToMale__FWD_105 = name2actor.get("SonOfExistingFamilyToMale__FWD_105").iterator().next();
		sonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_111 = name2actor.get("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_111").iterator().next();
		sonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_114 = name2actor.get("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_114").iterator().next();
		sonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_117 = name2actor.get("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_117").iterator().next();
		sonToMale__FWD_120 = name2actor.get("SonToMale__FWD_120").iterator().next();
		familyRegister_object_SP0 = name2actor.get("FamilyRegister_object_SP0").iterator().next();
		familyRegister_object_SP1 = name2actor.get("FamilyRegister_object_SP1").iterator().next();
		familyRegister_object_SP2 = name2actor.get("FamilyRegister_object_SP2").iterator().next();
		family_object_SP0 = name2actor.get("Family_object_SP0").iterator().next();
		family_object_SP1 = name2actor.get("Family_object_SP1").iterator().next();
		family_object_SP2 = name2actor.get("Family_object_SP2").iterator().next();
		family_object_SP3 = name2actor.get("Family_object_SP3").iterator().next();
		family_object_SP4 = name2actor.get("Family_object_SP4").iterator().next();
		family_object_SP5 = name2actor.get("Family_object_SP5").iterator().next();
		family_object_SP6 = name2actor.get("Family_object_SP6").iterator().next();
		family_object_SP7 = name2actor.get("Family_object_SP7").iterator().next();
		family_object_SP8 = name2actor.get("Family_object_SP8").iterator().next();
		familyMember_object_SP0 = name2actor.get("FamilyMember_object_SP0").iterator().next();
		familyMember_object_SP1 = name2actor.get("FamilyMember_object_SP1").iterator().next();
		familyMember_object_SP2 = name2actor.get("FamilyMember_object_SP2").iterator().next();
		familyMember_object_SP3 = name2actor.get("FamilyMember_object_SP3").iterator().next();
		familyMember_object_SP4 = name2actor.get("FamilyMember_object_SP4").iterator().next();
		familyMember_object_SP5 = name2actor.get("FamilyMember_object_SP5").iterator().next();
		familyMember_object_SP6 = name2actor.get("FamilyMember_object_SP6").iterator().next();
		familyMember_object_SP7 = name2actor.get("FamilyMember_object_SP7").iterator().next();
		registerToRegisterCorr_object_SP0 = name2actor.get("RegisterToRegisterCorr_object_SP0").iterator().next();
		registerToRegisterCorr_object_SP1 = name2actor.get("RegisterToRegisterCorr_object_SP1").iterator().next();
		personRegister_object_SP0 = name2actor.get("PersonRegister_object_SP0").iterator().next();
		personRegister_object_SP1 = name2actor.get("PersonRegister_object_SP1").iterator().next();
	}
	
	private void initializeAdd() {
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(), (obj) -> {
			PersonsSmartEMF.PersonRegister _personregister = (PersonsSmartEMF.PersonRegister) obj;
			incUtil.newMessage();
			personRegister_object_SP0.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP1.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
		});
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(), (obj) -> {
			FamiliesSmartEMF.FamilyRegister _familyregister = (FamiliesSmartEMF.FamilyRegister) obj;
			incUtil.newMessage();
			familyRegister_object_SP0.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP1.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP2.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
		});
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(), (obj) -> {
			FamiliesSmartEMF.FamilyMember _familymember = (FamiliesSmartEMF.FamilyMember) obj;
			incUtil.newMessage();
			familyMember_object_SP0.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP1.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP2.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP3.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP4.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP5.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP6.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP7.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
		});
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily(), (obj) -> {
			FamiliesSmartEMF.Family _family = (FamiliesSmartEMF.Family) obj;
			incUtil.newMessage();
			family_object_SP0.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP1.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP2.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP3.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP4.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP5.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP6.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP7.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP8.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
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
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Daughters(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_13.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_28.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_42.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_57.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_72.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_87.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_102.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_111.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Father(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_7.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_25.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_45.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_60.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_66.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_81.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_96.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_114.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), (notification) -> {
			incUtil.newMessage();
			createFamily__FWD_1.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_13.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_28.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_45.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_60.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_75.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_90.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_105.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_120.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Mother(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_10.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_22.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_39.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_51.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_75.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_90.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_99.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_117.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_13.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_28.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_45.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_60.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_75.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_90.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_105.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_120.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_13.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_28.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_45.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_60.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_75.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_90.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_105.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_120.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Sons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_4.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_19.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_36.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_54.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_69.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_84.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_105.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_120.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
		});
	}
	
	private void initializeRemoveEdge() {
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Daughters(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_13.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_28.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_42.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_57.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_72.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_87.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_102.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_111.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Father(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_7.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_25.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_45.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_60.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_66.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_81.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_96.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_114.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), (notification) -> {
			incUtil.newMessage();
			createFamily__FWD_1.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_13.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_28.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_45.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_60.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_75.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_90.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_105.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_120.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Mother(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_10.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_22.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_39.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_51.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_75.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_90.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_99.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_117.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_13.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_28.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_45.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_60.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_75.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_90.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_105.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_120.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_13.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_28.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_45.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_60.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_75.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_90.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_105.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_120.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Sons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_4.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_19.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_36.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_54.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_69.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_84.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_105.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_120.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
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
			familyRegister_object_SP0.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP1.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP2.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP0.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP1.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP2.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP3.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP4.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP5.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP6.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP7.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP8.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP0.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP1.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP2.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP3.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP4.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP5.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP6.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP7.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP0.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP1.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
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

