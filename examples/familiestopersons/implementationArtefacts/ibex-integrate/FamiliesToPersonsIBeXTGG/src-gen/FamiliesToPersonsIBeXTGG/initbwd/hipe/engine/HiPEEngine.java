package FamiliesToPersonsIBeXTGG.initbwd.hipe.engine;

import akka.actor.ActorRef;
import akka.actor.Props;

import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.NotificationActor;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.DispatchActor;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__BWD_1;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.DaughterToFemale__BWD_7;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.Families2Persons__BWD_12;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__BWD_14;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.FatherToMale__BWD_20;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__BWD_25;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.MotherToFemale__BWD_31;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__BWD_36;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.SonToMale__BWD_42;

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
		classes.put("DaughterOfExistingFamilyToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__BWD_production", "DaughterOfExistingFamilyToFemale__BWD");
		classes.put("DaughterToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__BWD_production", "DaughterToFemale__BWD");
		classes.put("Families2Persons__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__BWD_production", "Families2Persons__BWD");
		classes.put("FatherOfExistingFamilyToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__BWD_production", "FatherOfExistingFamilyToMale__BWD");
		classes.put("FatherToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__BWD_production", "FatherToMale__BWD");
		classes.put("MotherOfExistingFamilyToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__BWD_production", "MotherOfExistingFamilyToFemale__BWD");
		classes.put("MotherToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__BWD_production", "MotherToFemale__BWD");
		classes.put("SonOfExistingFamilyToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__BWD_production", "SonOfExistingFamilyToMale__BWD");
		classes.put("SonToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__BWD_production", "SonToMale__BWD");
		
	}
	
	@Override
	public void createJunctionNodes() {
		classes.put("DaughterOfExistingFamilyToFemale__BWD_1", DaughterOfExistingFamilyToFemale__BWD_1.class);
		classes.put("DaughterToFemale__BWD_7", DaughterToFemale__BWD_7.class);
		classes.put("Families2Persons__BWD_12", Families2Persons__BWD_12.class);
		classes.put("FatherOfExistingFamilyToMale__BWD_14", FatherOfExistingFamilyToMale__BWD_14.class);
		classes.put("FatherToMale__BWD_20", FatherToMale__BWD_20.class);
		classes.put("MotherOfExistingFamilyToFemale__BWD_25", MotherOfExistingFamilyToFemale__BWD_25.class);
		classes.put("MotherToFemale__BWD_31", MotherToFemale__BWD_31.class);
		classes.put("SonOfExistingFamilyToMale__BWD_36", SonOfExistingFamilyToMale__BWD_36.class);
		classes.put("SonToMale__BWD_42", SonToMale__BWD_42.class);
	}
	
	@Override
	public void createReferenceNodes() {
		
	}
	
	@Override
	public void createObjectNodes() {
		classes.put("Family_object",Family_object.class);
		classes.put("Female_object",Female_object.class);
		classes.put("Male_object",Male_object.class);
		classes.put("FamilyRegister_object_SP0",FamilyRegister_object_SP0.class);
		classes.put("FamilyRegister_object_SP1",FamilyRegister_object_SP1.class);
		classes.put("RegisterToRegisterCorr_object_SP0",RegisterToRegisterCorr_object_SP0.class);
		classes.put("RegisterToRegisterCorr_object_SP1",RegisterToRegisterCorr_object_SP1.class);
		classes.put("PersonRegister_object_SP0",PersonRegister_object_SP0.class);
		classes.put("PersonRegister_object_SP1",PersonRegister_object_SP1.class);
		classes.put("PersonRegister_object_SP2",PersonRegister_object_SP2.class);
		
	}
	
	@Override
	public void initializeReferenceNodes() {
	}
}

class Family_object extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Female_object extends GenericObjectActor<PersonsSmartEMF.Female> { }
class Male_object extends GenericObjectActor<PersonsSmartEMF.Male> { }
class FamilyRegister_object_SP0 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class FamilyRegister_object_SP1 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class RegisterToRegisterCorr_object_SP0 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class RegisterToRegisterCorr_object_SP1 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class PersonRegister_object_SP0 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP1 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP2 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }


