package FamiliesToPersonsIBeXTGG.modelgen.hipe.engine;

import akka.actor.ActorRef;
import akka.actor.Props;

import FamiliesToPersonsIBeXTGG.modelgen.hipe.engine.actor.NotificationActor;
import FamiliesToPersonsIBeXTGG.modelgen.hipe.engine.actor.DispatchActor;
import FamiliesToPersonsIBeXTGG.modelgen.hipe.engine.actor.stateless.CreateFamily__GEN_1;
import FamiliesToPersonsIBeXTGG.modelgen.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__GEN_3;
import FamiliesToPersonsIBeXTGG.modelgen.hipe.engine.actor.stateless.DaughterToFemale__GEN_8;
import FamiliesToPersonsIBeXTGG.modelgen.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__GEN_12;
import FamiliesToPersonsIBeXTGG.modelgen.hipe.engine.actor.stateless.FatherToMale__GEN_17;
import FamiliesToPersonsIBeXTGG.modelgen.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__GEN_21;
import FamiliesToPersonsIBeXTGG.modelgen.hipe.engine.actor.stateless.MotherToFemale__GEN_26;
import FamiliesToPersonsIBeXTGG.modelgen.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__GEN_30;
import FamiliesToPersonsIBeXTGG.modelgen.hipe.engine.actor.stateless.SonToMale__GEN_35;

import hipe.engine.IHiPEEngine;
import hipe.engine.message.InitGenReferenceActor;

import hipe.generic.actor.GenericObjectActor;
import hipe.generic.actor.GenericReferenceActor;
import hipe.generic.actor.GenericProductionActor;
import hipe.generic.actor.junction.*;
import hipe.engine.HiPEOptions;

import hipe.network.*;

public class HiPEEngine extends IHiPEEngine{
	
	public HiPEEngine(HiPENetwork network) {
		super(network);
	}
	
	public HiPEEngine() {
		super();
	}
	
	@Override
	public String getClassLocation() {
		return getClass().getProtectionDomain().getCodeSource().getLocation().getPath().toString();
	}
	
	@Override
	public String getPackageName() {
		return getClass().getPackageName();
	}
	
	@Override
	protected ActorRef getDispatchActor() {
		return system.actorOf(
			Props.create(DispatchActor.class, () -> new DispatchActor(name2actor, incUtil)),
			"DispatchActor");
	}
	
	@Override
	protected ActorRef getNotificationActor(HiPEOptions options) {
		return system.actorOf(
			Props.create(NotificationActor.class, () -> new NotificationActor(dispatcher, incUtil, notificationIndex, options)), 
			"NotificationActor");
	}
	
	@Override
	public void createProductionNodes() {
		classes.put("CreateFamily__GEN_production", GenericProductionActor.class);
		productionNodes2pattern.put("CreateFamily__GEN_production", "CreateFamily__GEN");
		classes.put("DaughterOfExistingFamilyToFemale__GEN_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__GEN_production", "DaughterOfExistingFamilyToFemale__GEN");
		classes.put("DaughterToFemale__GEN_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__GEN_production", "DaughterToFemale__GEN");
		classes.put("FatherOfExistingFamilyToMale__GEN_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__GEN_production", "FatherOfExistingFamilyToMale__GEN");
		classes.put("FatherToMale__GEN_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__GEN_production", "FatherToMale__GEN");
		classes.put("MotherOfExistingFamilyToFemale__GEN_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__GEN_production", "MotherOfExistingFamilyToFemale__GEN");
		classes.put("MotherToFemale__GEN_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__GEN_production", "MotherToFemale__GEN");
		classes.put("SonOfExistingFamilyToMale__GEN_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__GEN_production", "SonOfExistingFamilyToMale__GEN");
		classes.put("SonToMale__GEN_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__GEN_production", "SonToMale__GEN");
		
	}
	
	@Override
	public void createJunctionNodes() {
		classes.put("CreateFamily__GEN_1", CreateFamily__GEN_1.class);
		classes.put("DaughterOfExistingFamilyToFemale__GEN_3", DaughterOfExistingFamilyToFemale__GEN_3.class);
		classes.put("DaughterToFemale__GEN_8", DaughterToFemale__GEN_8.class);
		classes.put("FatherOfExistingFamilyToMale__GEN_12", FatherOfExistingFamilyToMale__GEN_12.class);
		classes.put("FatherToMale__GEN_17", FatherToMale__GEN_17.class);
		classes.put("MotherOfExistingFamilyToFemale__GEN_21", MotherOfExistingFamilyToFemale__GEN_21.class);
		classes.put("MotherToFemale__GEN_26", MotherToFemale__GEN_26.class);
		classes.put("SonOfExistingFamilyToMale__GEN_30", SonOfExistingFamilyToMale__GEN_30.class);
		classes.put("SonToMale__GEN_35", SonToMale__GEN_35.class);
	}
	
	@Override
	public void createReferenceNodes() {
		
	}
	
	@Override
	public void createObjectNodes() {
		classes.put("Family_object",Family_object.class);
		classes.put("FamilyRegister_object_SP0",FamilyRegister_object_SP0.class);
		classes.put("FamilyRegister_object_SP1",FamilyRegister_object_SP1.class);
		classes.put("FamilyRegister_object_SP2",FamilyRegister_object_SP2.class);
		classes.put("RegisterToRegisterCorr_object_SP0",RegisterToRegisterCorr_object_SP0.class);
		classes.put("RegisterToRegisterCorr_object_SP1",RegisterToRegisterCorr_object_SP1.class);
		classes.put("PersonRegister_object_SP0",PersonRegister_object_SP0.class);
		classes.put("PersonRegister_object_SP1",PersonRegister_object_SP1.class);
		
	}
	
	@Override
	public void initializeReferenceNodes() {
	}
}

class Family_object extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class FamilyRegister_object_SP0 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class FamilyRegister_object_SP1 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class FamilyRegister_object_SP2 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class RegisterToRegisterCorr_object_SP0 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class RegisterToRegisterCorr_object_SP1 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class PersonRegister_object_SP0 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP1 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }


