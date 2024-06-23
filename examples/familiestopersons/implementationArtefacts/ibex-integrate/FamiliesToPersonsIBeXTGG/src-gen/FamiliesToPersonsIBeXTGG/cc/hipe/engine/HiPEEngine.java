package FamiliesToPersonsIBeXTGG.cc.hipe.engine;

import akka.actor.ActorRef;
import akka.actor.Props;

import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.NotificationActor;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.DispatchActor;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.CreateFamily__CC_1;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.CreateFamily__CONSISTENCY_4;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_8;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_11;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_14;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__CC_17;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__CONSISTENCY_24;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_33;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_36;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_39;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.DaughterToFemale__CC_42;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.DaughterToFemale__CONSISTENCY_49;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.Families2Persons__CC_58;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.Families2Persons__CONSISTENCY_61;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_66;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_69;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_72;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__CC_75;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__CONSISTENCY_82;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_91;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_94;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_97;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.FatherToMale__CC_100;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.FatherToMale__CONSISTENCY_107;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_116;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_119;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_122;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__CC_125;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__CONSISTENCY_132;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_141;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_144;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_147;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.MotherToFemale__CC_150;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.MotherToFemale__CONSISTENCY_157;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_166;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_169;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_172;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__CC_175;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__CONSISTENCY_182;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_191;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_194;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_197;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.SonToMale__CC_200;
import FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless.SonToMale__CONSISTENCY_207;

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
		classes.put("CreateFamily__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("CreateFamily__CC_production", "CreateFamily__CC");
		classes.put("CreateFamily__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("CreateFamily__CONSISTENCY_production", "CreateFamily__CONSISTENCY");
		classes.put("DaughterOfExistingFamilyToFemale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__CC_production", "DaughterOfExistingFamilyToFemale__CC");
		classes.put("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterOfExistingFamilyToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__CONSISTENCY_production", "DaughterOfExistingFamilyToFemale__CONSISTENCY");
		classes.put("DaughterToFemale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__CC_production", "DaughterToFemale__CC");
		classes.put("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__CONSISTENCY_production", "DaughterToFemale__CONSISTENCY");
		classes.put("Families2Persons__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__CC_production", "Families2Persons__CC");
		classes.put("Families2Persons__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__CONSISTENCY_production", "Families2Persons__CONSISTENCY");
		classes.put("FatherOfExistingFamilyToMale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__CC_production", "FatherOfExistingFamilyToMale__CC");
		classes.put("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherOfExistingFamilyToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__CONSISTENCY_production", "FatherOfExistingFamilyToMale__CONSISTENCY");
		classes.put("FatherToMale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__CC_production", "FatherToMale__CC");
		classes.put("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__CONSISTENCY_production", "FatherToMale__CONSISTENCY");
		classes.put("MotherOfExistingFamilyToFemale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__CC_production", "MotherOfExistingFamilyToFemale__CC");
		classes.put("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherOfExistingFamilyToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__CONSISTENCY_production", "MotherOfExistingFamilyToFemale__CONSISTENCY");
		classes.put("MotherToFemale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__CC_production", "MotherToFemale__CC");
		classes.put("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__CONSISTENCY_production", "MotherToFemale__CONSISTENCY");
		classes.put("SonOfExistingFamilyToMale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__CC_production", "SonOfExistingFamilyToMale__CC");
		classes.put("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonOfExistingFamilyToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__CONSISTENCY_production", "SonOfExistingFamilyToMale__CONSISTENCY");
		classes.put("SonToMale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__CC_production", "SonToMale__CC");
		classes.put("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__CONSISTENCY_production", "SonToMale__CONSISTENCY");
		
	}
	
	@Override
	public void createJunctionNodes() {
		classes.put("CreateFamily__CC_1", CreateFamily__CC_1.class);
		classes.put("CreateFamily__CONSISTENCY_4", CreateFamily__CONSISTENCY_4.class);
		classes.put("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_8", DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_8.class);
		classes.put("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_11", DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_11.class);
		classes.put("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_14", DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_14.class);
		classes.put("DaughterOfExistingFamilyToFemale__CC_17", DaughterOfExistingFamilyToFemale__CC_17.class);
		classes.put("DaughterOfExistingFamilyToFemale__CONSISTENCY_24", DaughterOfExistingFamilyToFemale__CONSISTENCY_24.class);
		classes.put("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_33", DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_33.class);
		classes.put("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_36", DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_36.class);
		classes.put("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_39", DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_39.class);
		classes.put("DaughterToFemale__CC_42", DaughterToFemale__CC_42.class);
		classes.put("DaughterToFemale__CONSISTENCY_49", DaughterToFemale__CONSISTENCY_49.class);
		classes.put("Families2Persons__CC_58", Families2Persons__CC_58.class);
		classes.put("Families2Persons__CONSISTENCY_61", Families2Persons__CONSISTENCY_61.class);
		classes.put("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_66", FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_66.class);
		classes.put("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_69", FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_69.class);
		classes.put("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_72", FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_72.class);
		classes.put("FatherOfExistingFamilyToMale__CC_75", FatherOfExistingFamilyToMale__CC_75.class);
		classes.put("FatherOfExistingFamilyToMale__CONSISTENCY_82", FatherOfExistingFamilyToMale__CONSISTENCY_82.class);
		classes.put("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_91", FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_91.class);
		classes.put("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_94", FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_94.class);
		classes.put("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_97", FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_97.class);
		classes.put("FatherToMale__CC_100", FatherToMale__CC_100.class);
		classes.put("FatherToMale__CONSISTENCY_107", FatherToMale__CONSISTENCY_107.class);
		classes.put("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_116", MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_116.class);
		classes.put("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_119", MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_119.class);
		classes.put("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_122", MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_122.class);
		classes.put("MotherOfExistingFamilyToFemale__CC_125", MotherOfExistingFamilyToFemale__CC_125.class);
		classes.put("MotherOfExistingFamilyToFemale__CONSISTENCY_132", MotherOfExistingFamilyToFemale__CONSISTENCY_132.class);
		classes.put("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_141", MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_141.class);
		classes.put("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_144", MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_144.class);
		classes.put("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_147", MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_147.class);
		classes.put("MotherToFemale__CC_150", MotherToFemale__CC_150.class);
		classes.put("MotherToFemale__CONSISTENCY_157", MotherToFemale__CONSISTENCY_157.class);
		classes.put("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_166", SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_166.class);
		classes.put("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_169", SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_169.class);
		classes.put("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_172", SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_172.class);
		classes.put("SonOfExistingFamilyToMale__CC_175", SonOfExistingFamilyToMale__CC_175.class);
		classes.put("SonOfExistingFamilyToMale__CONSISTENCY_182", SonOfExistingFamilyToMale__CONSISTENCY_182.class);
		classes.put("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_191", SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_191.class);
		classes.put("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_194", SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_194.class);
		classes.put("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_197", SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_197.class);
		classes.put("SonToMale__CC_200", SonToMale__CC_200.class);
		classes.put("SonToMale__CONSISTENCY_207", SonToMale__CONSISTENCY_207.class);
	}
	
	@Override
	public void createReferenceNodes() {
		
	}
	
	@Override
	public void createObjectNodes() {
		classes.put("ProtocolNode_CreateFamily_object",ProtocolNode_CreateFamily_object.class);
		classes.put("ProtocolNode_DaughterOfExistingFamilyToFemale_object",ProtocolNode_DaughterOfExistingFamilyToFemale_object.class);
		classes.put("ProtocolNode_DaughterToFemale_object",ProtocolNode_DaughterToFemale_object.class);
		classes.put("ProtocolNode_Families2Persons_object",ProtocolNode_Families2Persons_object.class);
		classes.put("ProtocolNode_FatherOfExistingFamilyToMale_object",ProtocolNode_FatherOfExistingFamilyToMale_object.class);
		classes.put("ProtocolNode_FatherToMale_object",ProtocolNode_FatherToMale_object.class);
		classes.put("ProtocolNode_MotherOfExistingFamilyToFemale_object",ProtocolNode_MotherOfExistingFamilyToFemale_object.class);
		classes.put("ProtocolNode_MotherToFemale_object",ProtocolNode_MotherToFemale_object.class);
		classes.put("ProtocolNode_SonOfExistingFamilyToMale_object",ProtocolNode_SonOfExistingFamilyToMale_object.class);
		classes.put("ProtocolNode_SonToMale_object",ProtocolNode_SonToMale_object.class);
		classes.put("FamilyRegister_object_SP0",FamilyRegister_object_SP0.class);
		classes.put("FamilyRegister_object_SP1",FamilyRegister_object_SP1.class);
		classes.put("FamilyRegister_object_SP2",FamilyRegister_object_SP2.class);
		classes.put("FamilyRegister_object_SP3",FamilyRegister_object_SP3.class);
		classes.put("FamilyRegister_object_SP4",FamilyRegister_object_SP4.class);
		classes.put("Family_object_SP0",Family_object_SP0.class);
		classes.put("Family_object_SP1",Family_object_SP1.class);
		classes.put("Family_object_SP2",Family_object_SP2.class);
		classes.put("Family_object_SP3",Family_object_SP3.class);
		classes.put("Family_object_SP4",Family_object_SP4.class);
		classes.put("Family_object_SP5",Family_object_SP5.class);
		classes.put("Family_object_SP6",Family_object_SP6.class);
		classes.put("Family_object_SP7",Family_object_SP7.class);
		classes.put("Family_object_SP8",Family_object_SP8.class);
		classes.put("Family_object_SP9",Family_object_SP9.class);
		classes.put("Family_object_SP10",Family_object_SP10.class);
		classes.put("FamilyMember_object_SP0",FamilyMember_object_SP0.class);
		classes.put("FamilyMember_object_SP1",FamilyMember_object_SP1.class);
		classes.put("FamilyMember_object_SP2",FamilyMember_object_SP2.class);
		classes.put("FamilyMember_object_SP3",FamilyMember_object_SP3.class);
		classes.put("FamilyMember_object_SP4",FamilyMember_object_SP4.class);
		classes.put("FamilyMember_object_SP5",FamilyMember_object_SP5.class);
		classes.put("FamilyMember_object_SP6",FamilyMember_object_SP6.class);
		classes.put("FamilyMember_object_SP7",FamilyMember_object_SP7.class);
		classes.put("FamilyMember_object_SP8",FamilyMember_object_SP8.class);
		classes.put("FamilyMember_object_SP9",FamilyMember_object_SP9.class);
		classes.put("RegisterToRegisterCorr_object_SP0",RegisterToRegisterCorr_object_SP0.class);
		classes.put("RegisterToRegisterCorr_object_SP1",RegisterToRegisterCorr_object_SP1.class);
		classes.put("RegisterToRegisterCorr_object_SP2",RegisterToRegisterCorr_object_SP2.class);
		classes.put("RegisterToRegisterCorr_object_SP3",RegisterToRegisterCorr_object_SP3.class);
		classes.put("RegisterToRegisterCorr_object_SP4",RegisterToRegisterCorr_object_SP4.class);
		classes.put("PersonRegister_object_SP0",PersonRegister_object_SP0.class);
		classes.put("PersonRegister_object_SP1",PersonRegister_object_SP1.class);
		classes.put("PersonRegister_object_SP2",PersonRegister_object_SP2.class);
		classes.put("PersonRegister_object_SP3",PersonRegister_object_SP3.class);
		classes.put("PersonRegister_object_SP4",PersonRegister_object_SP4.class);
		classes.put("Female_object_SP0",Female_object_SP0.class);
		classes.put("Female_object_SP1",Female_object_SP1.class);
		classes.put("FamilyMemberToPersonCorr_object_SP0",FamilyMemberToPersonCorr_object_SP0.class);
		classes.put("FamilyMemberToPersonCorr_object_SP1",FamilyMemberToPersonCorr_object_SP1.class);
		classes.put("Male_object_SP0",Male_object_SP0.class);
		classes.put("Male_object_SP1",Male_object_SP1.class);
		
	}
	
	@Override
	public void initializeReferenceNodes() {
	}
}

class ProtocolNode_CreateFamily_object extends GenericObjectActor<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily> { }
class ProtocolNode_DaughterOfExistingFamilyToFemale_object extends GenericObjectActor<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale> { }
class ProtocolNode_DaughterToFemale_object extends GenericObjectActor<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale> { }
class ProtocolNode_Families2Persons_object extends GenericObjectActor<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons> { }
class ProtocolNode_FatherOfExistingFamilyToMale_object extends GenericObjectActor<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale> { }
class ProtocolNode_FatherToMale_object extends GenericObjectActor<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale> { }
class ProtocolNode_MotherOfExistingFamilyToFemale_object extends GenericObjectActor<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale> { }
class ProtocolNode_MotherToFemale_object extends GenericObjectActor<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale> { }
class ProtocolNode_SonOfExistingFamilyToMale_object extends GenericObjectActor<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale> { }
class ProtocolNode_SonToMale_object extends GenericObjectActor<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale> { }
class FamilyRegister_object_SP0 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class FamilyRegister_object_SP1 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class FamilyRegister_object_SP2 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class FamilyRegister_object_SP3 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class FamilyRegister_object_SP4 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class Family_object_SP0 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP1 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP2 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP3 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP4 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP5 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP6 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP7 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP8 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP9 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP10 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class FamilyMember_object_SP0 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP1 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP2 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP3 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP4 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP5 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP6 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP7 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP8 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP9 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class RegisterToRegisterCorr_object_SP0 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class RegisterToRegisterCorr_object_SP1 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class RegisterToRegisterCorr_object_SP2 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class RegisterToRegisterCorr_object_SP3 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class RegisterToRegisterCorr_object_SP4 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
class PersonRegister_object_SP0 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP1 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP2 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP3 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP4 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class Female_object_SP0 extends GenericObjectActor<PersonsSmartEMF.Female> { }
class Female_object_SP1 extends GenericObjectActor<PersonsSmartEMF.Female> { }
class FamilyMemberToPersonCorr_object_SP0 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr> { }
class FamilyMemberToPersonCorr_object_SP1 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr> { }
class Male_object_SP0 extends GenericObjectActor<PersonsSmartEMF.Male> { }
class Male_object_SP1 extends GenericObjectActor<PersonsSmartEMF.Male> { }


