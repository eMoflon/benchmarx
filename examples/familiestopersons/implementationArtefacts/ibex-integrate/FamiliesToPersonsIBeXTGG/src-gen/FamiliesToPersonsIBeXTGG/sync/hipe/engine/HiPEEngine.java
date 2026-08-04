package FamiliesToPersonsIBeXTGG.sync.hipe.engine;

import akka.actor.ActorRef;
import akka.actor.Props;

import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.NotificationActor;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.DispatchActor;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.CreateFamily__FWD_1;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.CreateFamily__CONSISTENCY_4;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_8;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_11;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_14;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__FWD_17;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__BWD_21;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__CONSISTENCY_25;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_32;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_35;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_38;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterToFemale__FWD_41;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterToFemale__BWD_46;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.DaughterToFemale__CONSISTENCY_50;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.Families2Persons__FWD_58;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.Families2Persons__BWD_60;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.Families2Persons__CONSISTENCY_62;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_67;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_70;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_73;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__FWD_76;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__BWD_80;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__CONSISTENCY_84;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_91;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_94;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_97;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherToMale__FWD_100;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherToMale__BWD_105;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.FatherToMale__CONSISTENCY_109;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_117;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_120;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_123;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__FWD_126;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__BWD_130;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__CONSISTENCY_134;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_141;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_144;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_147;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherToFemale__FWD_150;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherToFemale__BWD_155;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.MotherToFemale__CONSISTENCY_159;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_167;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_170;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_173;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__FWD_176;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__BWD_180;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__CONSISTENCY_184;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_191;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_194;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_197;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonToMale__FWD_200;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonToMale__BWD_205;
import FamiliesToPersonsIBeXTGG.sync.hipe.engine.actor.stateless.SonToMale__CONSISTENCY_209;

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
		classes.put("CreateFamily__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("CreateFamily__FWD_production", "CreateFamily__FWD");
		classes.put("CreateFamily__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("CreateFamily__CONSISTENCY_production", "CreateFamily__CONSISTENCY");
		classes.put("DaughterOfExistingFamilyToFemale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__FWD_production", "DaughterOfExistingFamilyToFemale__FWD");
		classes.put("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterOfExistingFamilyToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__BWD_production", "DaughterOfExistingFamilyToFemale__BWD");
		classes.put("DaughterOfExistingFamilyToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__CONSISTENCY_production", "DaughterOfExistingFamilyToFemale__CONSISTENCY");
		classes.put("DaughterToFemale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__FWD_production", "DaughterToFemale__FWD");
		classes.put("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__BWD_production", "DaughterToFemale__BWD");
		classes.put("DaughterToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__CONSISTENCY_production", "DaughterToFemale__CONSISTENCY");
		classes.put("Families2Persons__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__FWD_production", "Families2Persons__FWD");
		classes.put("Families2Persons__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__BWD_production", "Families2Persons__BWD");
		classes.put("Families2Persons__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__CONSISTENCY_production", "Families2Persons__CONSISTENCY");
		classes.put("FatherOfExistingFamilyToMale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__FWD_production", "FatherOfExistingFamilyToMale__FWD");
		classes.put("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherOfExistingFamilyToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__BWD_production", "FatherOfExistingFamilyToMale__BWD");
		classes.put("FatherOfExistingFamilyToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__CONSISTENCY_production", "FatherOfExistingFamilyToMale__CONSISTENCY");
		classes.put("FatherToMale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__FWD_production", "FatherToMale__FWD");
		classes.put("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__BWD_production", "FatherToMale__BWD");
		classes.put("FatherToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__CONSISTENCY_production", "FatherToMale__CONSISTENCY");
		classes.put("MotherOfExistingFamilyToFemale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__FWD_production", "MotherOfExistingFamilyToFemale__FWD");
		classes.put("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherOfExistingFamilyToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__BWD_production", "MotherOfExistingFamilyToFemale__BWD");
		classes.put("MotherOfExistingFamilyToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__CONSISTENCY_production", "MotherOfExistingFamilyToFemale__CONSISTENCY");
		classes.put("MotherToFemale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__FWD_production", "MotherToFemale__FWD");
		classes.put("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__BWD_production", "MotherToFemale__BWD");
		classes.put("MotherToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__CONSISTENCY_production", "MotherToFemale__CONSISTENCY");
		classes.put("SonOfExistingFamilyToMale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__FWD_production", "SonOfExistingFamilyToMale__FWD");
		classes.put("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonOfExistingFamilyToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__BWD_production", "SonOfExistingFamilyToMale__BWD");
		classes.put("SonOfExistingFamilyToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__CONSISTENCY_production", "SonOfExistingFamilyToMale__CONSISTENCY");
		classes.put("SonToMale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__FWD_production", "SonToMale__FWD");
		classes.put("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__BWD_production", "SonToMale__BWD");
		classes.put("SonToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__CONSISTENCY_production", "SonToMale__CONSISTENCY");
		
	}
	
	@Override
	public void createJunctionNodes() {
		classes.put("CreateFamily__FWD_1", CreateFamily__FWD_1.class);
		classes.put("CreateFamily__CONSISTENCY_4", CreateFamily__CONSISTENCY_4.class);
		classes.put("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_8", DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_8.class);
		classes.put("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_11", DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_11.class);
		classes.put("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_14", DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_14.class);
		classes.put("DaughterOfExistingFamilyToFemale__FWD_17", DaughterOfExistingFamilyToFemale__FWD_17.class);
		classes.put("DaughterOfExistingFamilyToFemale__BWD_21", DaughterOfExistingFamilyToFemale__BWD_21.class);
		classes.put("DaughterOfExistingFamilyToFemale__CONSISTENCY_25", DaughterOfExistingFamilyToFemale__CONSISTENCY_25.class);
		classes.put("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_32", DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_32.class);
		classes.put("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_35", DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_35.class);
		classes.put("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_38", DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_38.class);
		classes.put("DaughterToFemale__FWD_41", DaughterToFemale__FWD_41.class);
		classes.put("DaughterToFemale__BWD_46", DaughterToFemale__BWD_46.class);
		classes.put("DaughterToFemale__CONSISTENCY_50", DaughterToFemale__CONSISTENCY_50.class);
		classes.put("Families2Persons__FWD_58", Families2Persons__FWD_58.class);
		classes.put("Families2Persons__BWD_60", Families2Persons__BWD_60.class);
		classes.put("Families2Persons__CONSISTENCY_62", Families2Persons__CONSISTENCY_62.class);
		classes.put("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_67", FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_67.class);
		classes.put("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_70", FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_70.class);
		classes.put("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_73", FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_73.class);
		classes.put("FatherOfExistingFamilyToMale__FWD_76", FatherOfExistingFamilyToMale__FWD_76.class);
		classes.put("FatherOfExistingFamilyToMale__BWD_80", FatherOfExistingFamilyToMale__BWD_80.class);
		classes.put("FatherOfExistingFamilyToMale__CONSISTENCY_84", FatherOfExistingFamilyToMale__CONSISTENCY_84.class);
		classes.put("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_91", FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_91.class);
		classes.put("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_94", FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_94.class);
		classes.put("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_97", FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_97.class);
		classes.put("FatherToMale__FWD_100", FatherToMale__FWD_100.class);
		classes.put("FatherToMale__BWD_105", FatherToMale__BWD_105.class);
		classes.put("FatherToMale__CONSISTENCY_109", FatherToMale__CONSISTENCY_109.class);
		classes.put("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_117", MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_117.class);
		classes.put("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_120", MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_120.class);
		classes.put("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_123", MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_123.class);
		classes.put("MotherOfExistingFamilyToFemale__FWD_126", MotherOfExistingFamilyToFemale__FWD_126.class);
		classes.put("MotherOfExistingFamilyToFemale__BWD_130", MotherOfExistingFamilyToFemale__BWD_130.class);
		classes.put("MotherOfExistingFamilyToFemale__CONSISTENCY_134", MotherOfExistingFamilyToFemale__CONSISTENCY_134.class);
		classes.put("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_141", MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_141.class);
		classes.put("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_144", MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_144.class);
		classes.put("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_147", MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_147.class);
		classes.put("MotherToFemale__FWD_150", MotherToFemale__FWD_150.class);
		classes.put("MotherToFemale__BWD_155", MotherToFemale__BWD_155.class);
		classes.put("MotherToFemale__CONSISTENCY_159", MotherToFemale__CONSISTENCY_159.class);
		classes.put("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_167", SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_167.class);
		classes.put("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_170", SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_170.class);
		classes.put("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_173", SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_173.class);
		classes.put("SonOfExistingFamilyToMale__FWD_176", SonOfExistingFamilyToMale__FWD_176.class);
		classes.put("SonOfExistingFamilyToMale__BWD_180", SonOfExistingFamilyToMale__BWD_180.class);
		classes.put("SonOfExistingFamilyToMale__CONSISTENCY_184", SonOfExistingFamilyToMale__CONSISTENCY_184.class);
		classes.put("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_191", SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_191.class);
		classes.put("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_194", SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_194.class);
		classes.put("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_197", SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_197.class);
		classes.put("SonToMale__FWD_200", SonToMale__FWD_200.class);
		classes.put("SonToMale__BWD_205", SonToMale__BWD_205.class);
		classes.put("SonToMale__CONSISTENCY_209", SonToMale__CONSISTENCY_209.class);
	}
	
	@Override
	public void createReferenceNodes() {
		
	}
	
	@Override
	public void createObjectNodes() {
		classes.put("ProtocolNode_CreateFamily_object",ProtocolNode_CreateFamily_object.class);
		classes.put("ProtocolNode_DaughterOfExistingFamilyToFemale_object",ProtocolNode_DaughterOfExistingFamilyToFemale_object.class);
		classes.put("ProtocolNode_DaughterToFemale_object",ProtocolNode_DaughterToFemale_object.class);
		classes.put("RegisterToRegisterCorr_object",RegisterToRegisterCorr_object.class);
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
		classes.put("Family_object_SP11",Family_object_SP11.class);
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
		classes.put("PersonRegister_object_SP0",PersonRegister_object_SP0.class);
		classes.put("PersonRegister_object_SP1",PersonRegister_object_SP1.class);
		classes.put("PersonRegister_object_SP2",PersonRegister_object_SP2.class);
		classes.put("PersonRegister_object_SP3",PersonRegister_object_SP3.class);
		classes.put("PersonRegister_object_SP4",PersonRegister_object_SP4.class);
		classes.put("PersonRegister_object_SP5",PersonRegister_object_SP5.class);
		classes.put("PersonRegister_object_SP6",PersonRegister_object_SP6.class);
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
class RegisterToRegisterCorr_object extends GenericObjectActor<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr> { }
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
class Family_object_SP11 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
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
class PersonRegister_object_SP0 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP1 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP2 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP3 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP4 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP5 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP6 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class Female_object_SP0 extends GenericObjectActor<PersonsSmartEMF.Female> { }
class Female_object_SP1 extends GenericObjectActor<PersonsSmartEMF.Female> { }
class FamilyMemberToPersonCorr_object_SP0 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr> { }
class FamilyMemberToPersonCorr_object_SP1 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr> { }
class Male_object_SP0 extends GenericObjectActor<PersonsSmartEMF.Male> { }
class Male_object_SP1 extends GenericObjectActor<PersonsSmartEMF.Male> { }


