package FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor;

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
			name2actor.get("Female_object_SP0").tell(new ObjectAdded<PersonsSmartEMF.Female>(incUtil, _female), getSelf());
			incUtil.newMessage();
			name2actor.get("Female_object_SP1").tell(new ObjectAdded<PersonsSmartEMF.Female>(incUtil, _female), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons(), obj -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons _protocolnode_families2persons = (FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) obj;
			incUtil.newMessage();
			name2actor.get("ProtocolNode_Families2Persons_object").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons>(incUtil, _protocolnode_families2persons), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale(), obj -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale _protocolnode_fatherofexistingfamilytomale = (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) obj;
			incUtil.newMessage();
			name2actor.get("ProtocolNode_FatherOfExistingFamilyToMale_object").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale>(incUtil, _protocolnode_fatherofexistingfamilytomale), getSelf());
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
			incUtil.newMessage();
			name2actor.get("Family_object_SP9").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			name2actor.get("Family_object_SP10").tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale(), obj -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale _protocolnode_daughtertofemale = (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) obj;
			incUtil.newMessage();
			name2actor.get("ProtocolNode_DaughterToFemale_object").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale>(incUtil, _protocolnode_daughtertofemale), getSelf());
		});
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(), obj -> {
			PersonsSmartEMF.PersonRegister _personregister = (PersonsSmartEMF.PersonRegister) obj;
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP0").tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP1").tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP2").tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP3").tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP4").tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale(), obj -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale _protocolnode_mothertofemale = (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) obj;
			incUtil.newMessage();
			name2actor.get("ProtocolNode_MotherToFemale_object").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale>(incUtil, _protocolnode_mothertofemale), getSelf());
		});
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getMale(), obj -> {
			PersonsSmartEMF.Male _male = (PersonsSmartEMF.Male) obj;
			incUtil.newMessage();
			name2actor.get("Male_object_SP0").tell(new ObjectAdded<PersonsSmartEMF.Male>(incUtil, _male), getSelf());
			incUtil.newMessage();
			name2actor.get("Male_object_SP1").tell(new ObjectAdded<PersonsSmartEMF.Male>(incUtil, _male), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr(), obj -> {
			FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr _familymembertopersoncorr = (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) obj;
			incUtil.newMessage();
			name2actor.get("FamilyMemberToPersonCorr_object_SP0").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, _familymembertopersoncorr), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyMemberToPersonCorr_object_SP1").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, _familymembertopersoncorr), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale(), obj -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale _protocolnode_sontomale = (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) obj;
			incUtil.newMessage();
			name2actor.get("ProtocolNode_SonToMale_object").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale>(incUtil, _protocolnode_sontomale), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale(), obj -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale _protocolnode_sonofexistingfamilytomale = (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) obj;
			incUtil.newMessage();
			name2actor.get("ProtocolNode_SonOfExistingFamilyToMale_object").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale>(incUtil, _protocolnode_sonofexistingfamilytomale), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale(), obj -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale _protocolnode_daughterofexistingfamilytofemale = (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) obj;
			incUtil.newMessage();
			name2actor.get("ProtocolNode_DaughterOfExistingFamilyToFemale_object").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale>(incUtil, _protocolnode_daughterofexistingfamilytofemale), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale(), obj -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale _protocolnode_motherofexistingfamilytofemale = (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) obj;
			incUtil.newMessage();
			name2actor.get("ProtocolNode_MotherOfExistingFamilyToFemale_object").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale>(incUtil, _protocolnode_motherofexistingfamilytofemale), getSelf());
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
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP8").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP9").tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_CreateFamily(), obj -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily _protocolnode_createfamily = (FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) obj;
			incUtil.newMessage();
			name2actor.get("ProtocolNode_CreateFamily_object").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily>(incUtil, _protocolnode_createfamily), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale(), obj -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale _protocolnode_fathertomale = (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) obj;
			incUtil.newMessage();
			name2actor.get("ProtocolNode_FatherToMale_object").tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale>(incUtil, _protocolnode_fathertomale), getSelf());
		});
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(), obj -> {
			FamiliesSmartEMF.FamilyRegister _familyregister = (FamiliesSmartEMF.FamilyRegister) obj;
			incUtil.newMessage();
			name2actor.get("FamilyRegister_object_SP0").tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyRegister_object_SP1").tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			name2actor.get("FamilyRegister_object_SP2").tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
		});
	}
	
	private void initializeSet() {
		feature2setConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Name(), notification -> {
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP9").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP3").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP1").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP8").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP2").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP6").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP5").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP4").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP10").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP0").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				name2actor.get("Family_object_SP7").tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
		});
		
		feature2setConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPerson_Name(), notification -> {
			if(notification.getNotifier() instanceof PersonsSmartEMF.Male) {
				incUtil.newMessage();
				name2actor.get("Male_object_SP1").tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof PersonsSmartEMF.Female) {
				incUtil.newMessage();
				name2actor.get("Female_object_SP1").tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof PersonsSmartEMF.Male) {
				incUtil.newMessage();
				name2actor.get("Male_object_SP0").tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof PersonsSmartEMF.Female) {
				incUtil.newMessage();
				name2actor.get("Female_object_SP0").tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
		});
		
		feature2setConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember_Name(), notification -> {
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP5").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP2").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP8").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP7").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP1").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP0").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP3").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP9").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP4").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				name2actor.get("FamilyMember_object_SP6").tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
		});
		
	}
	
	private void initializeAddEdge() {
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Source(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, PersonsSmartEMF.Male>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), notification -> {
			incUtil.newMessage();
			name2actor.get("CreateFamily__CC_1").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("CreateFamily__CONSISTENCY_4").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CC_38").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CC_90").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CC_134").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CC_178").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__CORRESPONDENCE__families2persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CONSISTENCY_55").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CONTEXT__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Father(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_14").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_32").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CC_69").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CC_90").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_104").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_131").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_154").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_169").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CONSISTENCY_55").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, PersonsSmartEMF.Female>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, PersonsSmartEMF.Male>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, PersonsSmartEMF.Female>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CONTEXT__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_CreateFamily_CREATE__SOURCE__family(), notification -> {
			incUtil.newMessage();
			name2actor.get("CreateFamily__CONSISTENCY_4").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily_CREATE__SOURCE__family_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, PersonsSmartEMF.Female>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, PersonsSmartEMF.Female>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Mother(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_11").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_29").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_60").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_84").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CC_113").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CC_134").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_148").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_175").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CC_17").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CC_38").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CC_69").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CC_90").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CC_113").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CC_134").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CC_157").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CC_178").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Target(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CONSISTENCY_55").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Sons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_8").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_35").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_63").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_87").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_107").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_128").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CC_157").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CC_178").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CONSISTENCY_55").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Daughters(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CC_17").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CC_38").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_66").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_81").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_110").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_125").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_151").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_172").tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_CreateFamily_CONTEXT__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("CreateFamily__CONSISTENCY_4").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, PersonsSmartEMF.Male>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, PersonsSmartEMF.Male>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CONSISTENCY_55").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CONTEXT__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__SOURCE__f_Family"), getSelf());
		});
	}
	
	private void initializeRemoveEdge() {
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Source(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, PersonsSmartEMF.Male>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), notification -> {
			incUtil.newMessage();
			name2actor.get("CreateFamily__CC_1").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("CreateFamily__CONSISTENCY_4").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CC_38").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CC_90").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CC_134").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CC_178").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__CORRESPONDENCE__families2persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CONSISTENCY_55").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CONTEXT__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Father(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_14").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_32").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CC_69").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CC_90").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_104").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_131").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_154").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_169").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CONSISTENCY_55").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, PersonsSmartEMF.Female>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, PersonsSmartEMF.Male>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, PersonsSmartEMF.Female>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CONTEXT__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_CreateFamily_CREATE__SOURCE__family(), notification -> {
			incUtil.newMessage();
			name2actor.get("CreateFamily__CONSISTENCY_4").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily_CREATE__SOURCE__family_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, PersonsSmartEMF.Female>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, PersonsSmartEMF.Female>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Mother(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_11").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_29").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_60").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_84").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CC_113").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CC_134").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_148").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_175").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CC_17").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CC_38").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CC_69").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CC_90").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CC_113").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CC_134").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CC_157").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CC_178").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Target(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CONSISTENCY_55").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Sons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_8").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_35").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_63").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_87").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_107").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_128").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CC_157").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CC_178").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CONSISTENCY_55").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_162").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Daughters(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CC_17").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CC_38").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_66").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_81").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_110").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_125").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_151").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			name2actor.get("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_172").tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_22").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_118").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherToMale__CONSISTENCY_96").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_CreateFamily_CONTEXT__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("CreateFamily__CONSISTENCY_4").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_74").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, PersonsSmartEMF.Male>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__TARGET__p(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, PersonsSmartEMF.Male>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("DaughterToFemale__CONSISTENCY_44").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__SOURCE__fm(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("Families2Persons__CONSISTENCY_55").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CONTEXT__SOURCE__families(), notification -> {
			incUtil.newMessage();
			name2actor.get("MotherToFemale__CONSISTENCY_140").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__SOURCE__f(), notification -> {
			incUtil.newMessage();
			name2actor.get("SonToMale__CONSISTENCY_184").tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__SOURCE__f_Family"), getSelf());
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
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			name2actor.get("FamilyRegister_object_SP2").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
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
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			name2actor.get("Family_object_SP9").tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			name2actor.get("Family_object_SP10").tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
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
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP8").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			name2actor.get("FamilyMember_object_SP9").tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
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
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP3").tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			name2actor.get("PersonRegister_object_SP4").tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Female) {
			incUtil.newMessage();
			name2actor.get("Female_object_SP0").tell(new ObjectDeleted<PersonsSmartEMF.Female>(incUtil, (PersonsSmartEMF.Female) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Female) {
			incUtil.newMessage();
			name2actor.get("Female_object_SP1").tell(new ObjectDeleted<PersonsSmartEMF.Female>(incUtil, (PersonsSmartEMF.Female) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Male) {
			incUtil.newMessage();
			name2actor.get("Male_object_SP0").tell(new ObjectDeleted<PersonsSmartEMF.Male>(incUtil, (PersonsSmartEMF.Male) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Male) {
			incUtil.newMessage();
			name2actor.get("Male_object_SP1").tell(new ObjectDeleted<PersonsSmartEMF.Male>(incUtil, (PersonsSmartEMF.Male) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) {
			incUtil.newMessage();
			name2actor.get("ProtocolNode_CreateFamily_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) {
			incUtil.newMessage();
			name2actor.get("ProtocolNode_DaughterOfExistingFamilyToFemale_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) {
			incUtil.newMessage();
			name2actor.get("ProtocolNode_DaughterToFemale_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			name2actor.get("RegisterToRegisterCorr_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) {
			incUtil.newMessage();
			name2actor.get("ProtocolNode_Families2Persons_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) {
			incUtil.newMessage();
			name2actor.get("ProtocolNode_FatherOfExistingFamilyToMale_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) {
			incUtil.newMessage();
			name2actor.get("ProtocolNode_FatherToMale_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) {
			incUtil.newMessage();
			name2actor.get("ProtocolNode_MotherOfExistingFamilyToFemale_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) {
			incUtil.newMessage();
			name2actor.get("ProtocolNode_MotherToFemale_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) {
			incUtil.newMessage();
			name2actor.get("ProtocolNode_SonOfExistingFamilyToMale_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) {
			incUtil.newMessage();
			name2actor.get("ProtocolNode_SonToMale_object").tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) node), getSelf());
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

