package FamiliesToPersonsIBeXTGG.initbwd.hipe.engine;

import akka.actor.ActorRef;
import akka.actor.Props;

import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.NotificationActor;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.DispatchActor;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__BWD_1;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.DaughterToFemale__BWD_5;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.Families2Persons__BWD_9;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__BWD_11;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.FatherToMale__BWD_15;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__BWD_19;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.MotherToFemale__BWD_23;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__BWD_27;
import FamiliesToPersonsIBeXTGG.initbwd.hipe.engine.actor.stateless.SonToMale__BWD_31;

import hipe.engine.IHiPEEngine;
import hipe.engine.message.InitGenReferenceActor;

import hipe.generic.actor.GenericObjectActor;
import hipe.generic.actor.GenericReferenceActor;
import hipe.generic.actor.GenericProductionActor;
import hipe.generic.actor.junction.*;
import hipe.engine.config.HiPEOptions;

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
		classes.put("DaughterToFemale__BWD_5", DaughterToFemale__BWD_5.class);
		classes.put("Families2Persons__BWD_9", Families2Persons__BWD_9.class);
		classes.put("FatherOfExistingFamilyToMale__BWD_11", FatherOfExistingFamilyToMale__BWD_11.class);
		classes.put("FatherToMale__BWD_15", FatherToMale__BWD_15.class);
		classes.put("MotherOfExistingFamilyToFemale__BWD_19", MotherOfExistingFamilyToFemale__BWD_19.class);
		classes.put("MotherToFemale__BWD_23", MotherToFemale__BWD_23.class);
		classes.put("SonOfExistingFamilyToMale__BWD_27", SonOfExistingFamilyToMale__BWD_27.class);
		classes.put("SonToMale__BWD_31", SonToMale__BWD_31.class);
	}
	
	@Override
	public void createReferenceNodes() {
		
	}
	
	@Override
	public void createObjectNodes() {
		classes.put("Family_object",Family_object.class);
		classes.put("Female_object",Female_object.class);
		classes.put("FamilyRegister_object",FamilyRegister_object.class);
		classes.put("Male_object",Male_object.class);
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
class FamilyRegister_object extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class Male_object extends GenericObjectActor<PersonsSmartEMF.Male> { }
class PersonRegister_object_SP0 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP1 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP2 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }


