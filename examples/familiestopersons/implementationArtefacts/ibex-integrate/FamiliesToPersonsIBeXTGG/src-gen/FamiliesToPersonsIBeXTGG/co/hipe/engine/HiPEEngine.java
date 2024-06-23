package FamiliesToPersonsIBeXTGG.co.hipe.engine;

import akka.actor.ActorRef;
import akka.actor.Props;

import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.NotificationActor;
import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.DispatchActor;
import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.stateless.CreateFamily__CO_1;
import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__CO_4;
import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.stateless.DaughterToFemale__CO_12;
import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.stateless.Families2Persons__CO_20;
import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__CO_24;
import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.stateless.FatherToMale__CO_32;
import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__CO_40;
import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.stateless.MotherToFemale__CO_48;
import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__CO_56;
import FamiliesToPersonsIBeXTGG.co.hipe.engine.actor.stateless.SonToMale__CO_64;

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
		classes.put("CreateFamily__CO_production", GenericProductionActor.class);
		productionNodes2pattern.put("CreateFamily__CO_production", "CreateFamily__CO");
		classes.put("DaughterOfExistingFamilyToFemale__CO_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__CO_production", "DaughterOfExistingFamilyToFemale__CO");
		classes.put("DaughterToFemale__CO_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__CO_production", "DaughterToFemale__CO");
		classes.put("Families2Persons__CO_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__CO_production", "Families2Persons__CO");
		classes.put("FatherOfExistingFamilyToMale__CO_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__CO_production", "FatherOfExistingFamilyToMale__CO");
		classes.put("FatherToMale__CO_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__CO_production", "FatherToMale__CO");
		classes.put("MotherOfExistingFamilyToFemale__CO_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__CO_production", "MotherOfExistingFamilyToFemale__CO");
		classes.put("MotherToFemale__CO_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__CO_production", "MotherToFemale__CO");
		classes.put("SonOfExistingFamilyToMale__CO_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__CO_production", "SonOfExistingFamilyToMale__CO");
		classes.put("SonToMale__CO_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__CO_production", "SonToMale__CO");
		
	}
	
	@Override
	public void createJunctionNodes() {
		classes.put("CreateFamily__CO_1", CreateFamily__CO_1.class);
		classes.put("DaughterOfExistingFamilyToFemale__CO_4", DaughterOfExistingFamilyToFemale__CO_4.class);
		classes.put("DaughterToFemale__CO_12", DaughterToFemale__CO_12.class);
		classes.put("Families2Persons__CO_20", Families2Persons__CO_20.class);
		classes.put("FatherOfExistingFamilyToMale__CO_24", FatherOfExistingFamilyToMale__CO_24.class);
		classes.put("FatherToMale__CO_32", FatherToMale__CO_32.class);
		classes.put("MotherOfExistingFamilyToFemale__CO_40", MotherOfExistingFamilyToFemale__CO_40.class);
		classes.put("MotherToFemale__CO_48", MotherToFemale__CO_48.class);
		classes.put("SonOfExistingFamilyToMale__CO_56", SonOfExistingFamilyToMale__CO_56.class);
		classes.put("SonToMale__CO_64", SonToMale__CO_64.class);
	}
	
	@Override
	public void createReferenceNodes() {
		
	}
	
	@Override
	public void createObjectNodes() {
		classes.put("Female_object",Female_object.class);
		classes.put("Male_object",Male_object.class);
		classes.put("FamilyRegister_object_SP0",FamilyRegister_object_SP0.class);
		classes.put("FamilyRegister_object_SP1",FamilyRegister_object_SP1.class);
		classes.put("FamilyRegister_object_SP2",FamilyRegister_object_SP2.class);
		classes.put("Family_object_SP0",Family_object_SP0.class);
		classes.put("Family_object_SP1",Family_object_SP1.class);
		classes.put("Family_object_SP2",Family_object_SP2.class);
		classes.put("FamilyMember_object_SP0",FamilyMember_object_SP0.class);
		classes.put("FamilyMember_object_SP1",FamilyMember_object_SP1.class);
		classes.put("RegisterToRegisterCorr_object_SP0",RegisterToRegisterCorr_object_SP0.class);
		classes.put("RegisterToRegisterCorr_object_SP1",RegisterToRegisterCorr_object_SP1.class);
		classes.put("RegisterToRegisterCorr_object_SP2",RegisterToRegisterCorr_object_SP2.class);
		classes.put("FamilyMemberToPersonCorr_object_SP0",FamilyMemberToPersonCorr_object_SP0.class);
		classes.put("FamilyMemberToPersonCorr_object_SP1",FamilyMemberToPersonCorr_object_SP1.class);
		classes.put("PersonRegister_object_SP0",PersonRegister_object_SP0.class);
		classes.put("PersonRegister_object_SP1",PersonRegister_object_SP1.class);
		classes.put("PersonRegister_object_SP2",PersonRegister_object_SP2.class);
		
	}
	
	@Override
	public void initializeReferenceNodes() {
	}
}

class Female_object extends GenericObjectActor<PersonsSmartEMF.Female> { }
class Male_object extends GenericObjectActor<PersonsSmartEMF.Male> { }
class FamilyRegister_object_SP0 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class FamilyRegister_object_SP1 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class FamilyRegister_object_SP2 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class Family_object_SP0 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP1 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP2 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class FamilyMember_object_SP0 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP1 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class RegisterToRegisterCorr_object_SP0 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class RegisterToRegisterCorr_object_SP1 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class RegisterToRegisterCorr_object_SP2 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class FamilyMemberToPersonCorr_object_SP0 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr> { }
class FamilyMemberToPersonCorr_object_SP1 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr> { }
class PersonRegister_object_SP0 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP1 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP2 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }


