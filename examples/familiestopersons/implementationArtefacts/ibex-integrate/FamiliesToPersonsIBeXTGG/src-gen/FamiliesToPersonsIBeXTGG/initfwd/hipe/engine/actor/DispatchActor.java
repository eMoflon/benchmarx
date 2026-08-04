package FamiliesToPersonsIBeXTGG.initfwd.hipe.engine.actor;

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
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily(), obj -> {
			FamiliesSmartEMF.Family _family = (FamiliesSmartEMF.Family) obj;
			incUtil.newMessage();
			name2actor.get("Family_object_SP0").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			name2actor.get("Family_object_SP1").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			name2actor.get("Family_object_SP2").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			name2actor.get("Family_object_SP3").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			name2actor.get("Family_object_SP4").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			name2actor.get("Family_object_SP5").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			name2actor.get("Family_object_SP6").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			name2actor.get("Family_object_SP7").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			name2actor.get("Family_object_SP8").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
		});
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(), obj -> {
			FamiliesSmartEMF.FamilyMember _familymember = (FamiliesSmartEMF.FamilyMember) obj;
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP0").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP1").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP2").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP3").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP4").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP5").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP6").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP7").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
		});
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(), obj -> {
			PersonsSmartEMF.PersonRegister _personregister = (PersonsSmartEMF.PersonRegister) obj;
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP0").tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP1").tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
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
	}
	
	private void initializeAddEdge() {
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Father(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_10").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_20").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__FWD_42").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__FWD_55").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_60").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_79").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_93").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_100").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Daughters(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__FWD_13").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__FWD_26").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_39").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_46").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_66").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_73").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_90").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_103").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), notification -> {
			incUtil.newMessage();
			name2actor.get("CreateFamily__FWD_1").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__FWD_26").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__FWD_55").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__FWD_82").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__FWD_109").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Sons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_4").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_23").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_36").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_52").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_63").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_76").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__FWD_96").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__FWD_109").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Mother(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_7").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_17").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_33").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_49").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__FWD_69").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__FWD_82").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_87").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_106").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
		});
	}
	
	private void initializeRemoveEdge() {
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Father(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_10").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_20").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__FWD_42").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__FWD_55").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_60").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_79").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_93").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_100").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Daughters(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__FWD_13").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__FWD_26").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_39").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_46").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_66").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_73").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_90").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_103").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), notification -> {
			incUtil.newMessage();
			name2actor.get("CreateFamily__FWD_1").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__FWD_26").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__FWD_55").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__FWD_82").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__FWD_109").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Sons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_4").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_23").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_36").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_52").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_63").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_76").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__FWD_96").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__FWD_109").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Mother(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_7").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_17").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_33").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_49").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__FWD_69").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__FWD_82").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_87").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_106").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
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
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			name2actor.get("Family_object_SP3").tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			name2actor.get("Family_object_SP4").tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			name2actor.get("Family_object_SP5").tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			name2actor.get("Family_object_SP6").tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			name2actor.get("Family_object_SP7").tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			name2actor.get("Family_object_SP8").tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP0").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP1").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP2").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP3").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP4").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP5").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP6").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP7").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP0").tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP1").tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
	}
}

