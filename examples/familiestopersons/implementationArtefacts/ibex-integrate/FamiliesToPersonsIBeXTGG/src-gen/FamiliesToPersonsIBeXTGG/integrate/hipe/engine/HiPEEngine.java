package FamiliesToPersonsIBeXTGG.integrate.hipe.engine;

import akka.actor.ActorRef;
import akka.actor.Props;

import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.NotificationActor;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.DispatchActor;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.CreateFamily__SOURCE_1;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.CreateFamily__FWD_4;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.CreateFamily__CC_7;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.CreateFamily__CONSISTENCY_10;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_14;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_17;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_20;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__SOURCE_23;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__TARGET_26;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__FWD_29;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__BWD_33;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__CC_37;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterOfExistingFamilyToFemale__CONSISTENCY_42;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_49;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_52;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_55;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterToFemale__SOURCE_58;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterToFemale__TARGET_62;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterToFemale__FWD_65;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterToFemale__BWD_70;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterToFemale__CC_74;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.DaughterToFemale__CONSISTENCY_80;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.Families2Persons__SOURCE_88;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.Families2Persons__TARGET_90;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.Families2Persons__FWD_92;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.Families2Persons__BWD_94;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.Families2Persons__CC_96;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.Families2Persons__CONSISTENCY_99;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_104;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_107;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_110;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__SOURCE_113;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__TARGET_116;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__FWD_119;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__BWD_123;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__CC_127;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherOfExistingFamilyToMale__CONSISTENCY_132;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_139;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_142;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_145;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherToMale__SOURCE_148;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherToMale__TARGET_152;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherToMale__FWD_155;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherToMale__BWD_160;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherToMale__CC_164;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.FatherToMale__CONSISTENCY_170;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_178;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_181;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_184;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__SOURCE_187;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__TARGET_190;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__FWD_193;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__BWD_197;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__CC_201;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherOfExistingFamilyToFemale__CONSISTENCY_206;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_213;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_216;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_219;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherToFemale__SOURCE_222;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherToFemale__TARGET_226;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherToFemale__FWD_229;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherToFemale__BWD_234;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherToFemale__CC_238;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.MotherToFemale__CONSISTENCY_244;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_252;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_255;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_258;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__SOURCE_261;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__TARGET_264;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__FWD_267;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__BWD_271;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__CC_275;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonOfExistingFamilyToMale__CONSISTENCY_280;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_287;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_290;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_293;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonToMale__SOURCE_296;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonToMale__TARGET_300;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonToMale__FWD_303;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonToMale__BWD_308;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonToMale__CC_312;
import FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless.SonToMale__CONSISTENCY_318;

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
		classes.put("CreateFamily__SOURCE_production", GenericProductionActor.class);
		productionNodes2pattern.put("CreateFamily__SOURCE_production", "CreateFamily__SOURCE");
		classes.put("CreateFamily__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("CreateFamily__FWD_production", "CreateFamily__FWD");
		classes.put("CreateFamily__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("CreateFamily__CC_production", "CreateFamily__CC");
		classes.put("CreateFamily__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("CreateFamily__CONSISTENCY_production", "CreateFamily__CONSISTENCY");
		classes.put("DaughterOfExistingFamilyToFemale__SOURCE_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__SOURCE_production", "DaughterOfExistingFamilyToFemale__SOURCE");
		classes.put("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterOfExistingFamilyToFemale__TARGET_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__TARGET_production", "DaughterOfExistingFamilyToFemale__TARGET");
		classes.put("DaughterOfExistingFamilyToFemale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__FWD_production", "DaughterOfExistingFamilyToFemale__FWD");
		classes.put("DaughterOfExistingFamilyToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__BWD_production", "DaughterOfExistingFamilyToFemale__BWD");
		classes.put("DaughterOfExistingFamilyToFemale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__CC_production", "DaughterOfExistingFamilyToFemale__CC");
		classes.put("DaughterOfExistingFamilyToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterOfExistingFamilyToFemale__CONSISTENCY_production", "DaughterOfExistingFamilyToFemale__CONSISTENCY");
		classes.put("DaughterToFemale__SOURCE_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__SOURCE_production", "DaughterToFemale__SOURCE");
		classes.put("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("DaughterToFemale__TARGET_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__TARGET_production", "DaughterToFemale__TARGET");
		classes.put("DaughterToFemale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__FWD_production", "DaughterToFemale__FWD");
		classes.put("DaughterToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__BWD_production", "DaughterToFemale__BWD");
		classes.put("DaughterToFemale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__CC_production", "DaughterToFemale__CC");
		classes.put("DaughterToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("DaughterToFemale__CONSISTENCY_production", "DaughterToFemale__CONSISTENCY");
		classes.put("Families2Persons__SOURCE_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__SOURCE_production", "Families2Persons__SOURCE");
		classes.put("Families2Persons__TARGET_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__TARGET_production", "Families2Persons__TARGET");
		classes.put("Families2Persons__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__FWD_production", "Families2Persons__FWD");
		classes.put("Families2Persons__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__BWD_production", "Families2Persons__BWD");
		classes.put("Families2Persons__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__CC_production", "Families2Persons__CC");
		classes.put("Families2Persons__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("Families2Persons__CONSISTENCY_production", "Families2Persons__CONSISTENCY");
		classes.put("FatherOfExistingFamilyToMale__SOURCE_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__SOURCE_production", "FatherOfExistingFamilyToMale__SOURCE");
		classes.put("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherOfExistingFamilyToMale__TARGET_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__TARGET_production", "FatherOfExistingFamilyToMale__TARGET");
		classes.put("FatherOfExistingFamilyToMale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__FWD_production", "FatherOfExistingFamilyToMale__FWD");
		classes.put("FatherOfExistingFamilyToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__BWD_production", "FatherOfExistingFamilyToMale__BWD");
		classes.put("FatherOfExistingFamilyToMale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__CC_production", "FatherOfExistingFamilyToMale__CC");
		classes.put("FatherOfExistingFamilyToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherOfExistingFamilyToMale__CONSISTENCY_production", "FatherOfExistingFamilyToMale__CONSISTENCY");
		classes.put("FatherToMale__SOURCE_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__SOURCE_production", "FatherToMale__SOURCE");
		classes.put("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("FatherToMale__TARGET_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__TARGET_production", "FatherToMale__TARGET");
		classes.put("FatherToMale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__FWD_production", "FatherToMale__FWD");
		classes.put("FatherToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__BWD_production", "FatherToMale__BWD");
		classes.put("FatherToMale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__CC_production", "FatherToMale__CC");
		classes.put("FatherToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("FatherToMale__CONSISTENCY_production", "FatherToMale__CONSISTENCY");
		classes.put("MotherOfExistingFamilyToFemale__SOURCE_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__SOURCE_production", "MotherOfExistingFamilyToFemale__SOURCE");
		classes.put("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherOfExistingFamilyToFemale__TARGET_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__TARGET_production", "MotherOfExistingFamilyToFemale__TARGET");
		classes.put("MotherOfExistingFamilyToFemale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__FWD_production", "MotherOfExistingFamilyToFemale__FWD");
		classes.put("MotherOfExistingFamilyToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__BWD_production", "MotherOfExistingFamilyToFemale__BWD");
		classes.put("MotherOfExistingFamilyToFemale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__CC_production", "MotherOfExistingFamilyToFemale__CC");
		classes.put("MotherOfExistingFamilyToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherOfExistingFamilyToFemale__CONSISTENCY_production", "MotherOfExistingFamilyToFemale__CONSISTENCY");
		classes.put("MotherToFemale__SOURCE_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__SOURCE_production", "MotherToFemale__SOURCE");
		classes.put("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("MotherToFemale__TARGET_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__TARGET_production", "MotherToFemale__TARGET");
		classes.put("MotherToFemale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__FWD_production", "MotherToFemale__FWD");
		classes.put("MotherToFemale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__BWD_production", "MotherToFemale__BWD");
		classes.put("MotherToFemale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__CC_production", "MotherToFemale__CC");
		classes.put("MotherToFemale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("MotherToFemale__CONSISTENCY_production", "MotherToFemale__CONSISTENCY");
		classes.put("SonOfExistingFamilyToMale__SOURCE_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__SOURCE_production", "SonOfExistingFamilyToMale__SOURCE");
		classes.put("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonOfExistingFamilyToMale__TARGET_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__TARGET_production", "SonOfExistingFamilyToMale__TARGET");
		classes.put("SonOfExistingFamilyToMale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__FWD_production", "SonOfExistingFamilyToMale__FWD");
		classes.put("SonOfExistingFamilyToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__BWD_production", "SonOfExistingFamilyToMale__BWD");
		classes.put("SonOfExistingFamilyToMale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__CC_production", "SonOfExistingFamilyToMale__CC");
		classes.put("SonOfExistingFamilyToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonOfExistingFamilyToMale__CONSISTENCY_production", "SonOfExistingFamilyToMale__CONSISTENCY");
		classes.put("SonToMale__SOURCE_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__SOURCE_production", "SonToMale__SOURCE");
		classes.put("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_production", "SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_production", "SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_production", "SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC");
		classes.put("SonToMale__TARGET_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__TARGET_production", "SonToMale__TARGET");
		classes.put("SonToMale__FWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__FWD_production", "SonToMale__FWD");
		classes.put("SonToMale__BWD_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__BWD_production", "SonToMale__BWD");
		classes.put("SonToMale__CC_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__CC_production", "SonToMale__CC");
		classes.put("SonToMale__CONSISTENCY_production", GenericProductionActor.class);
		productionNodes2pattern.put("SonToMale__CONSISTENCY_production", "SonToMale__CONSISTENCY");
		
	}
	
	@Override
	public void createJunctionNodes() {
		classes.put("CreateFamily__SOURCE_1", CreateFamily__SOURCE_1.class);
		classes.put("CreateFamily__FWD_4", CreateFamily__FWD_4.class);
		classes.put("CreateFamily__CC_7", CreateFamily__CC_7.class);
		classes.put("CreateFamily__CONSISTENCY_10", CreateFamily__CONSISTENCY_10.class);
		classes.put("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_14", DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_14.class);
		classes.put("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_17", DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_17.class);
		classes.put("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_20", DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_20.class);
		classes.put("DaughterOfExistingFamilyToFemale__SOURCE_23", DaughterOfExistingFamilyToFemale__SOURCE_23.class);
		classes.put("DaughterOfExistingFamilyToFemale__TARGET_26", DaughterOfExistingFamilyToFemale__TARGET_26.class);
		classes.put("DaughterOfExistingFamilyToFemale__FWD_29", DaughterOfExistingFamilyToFemale__FWD_29.class);
		classes.put("DaughterOfExistingFamilyToFemale__BWD_33", DaughterOfExistingFamilyToFemale__BWD_33.class);
		classes.put("DaughterOfExistingFamilyToFemale__CC_37", DaughterOfExistingFamilyToFemale__CC_37.class);
		classes.put("DaughterOfExistingFamilyToFemale__CONSISTENCY_42", DaughterOfExistingFamilyToFemale__CONSISTENCY_42.class);
		classes.put("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_49", DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_49.class);
		classes.put("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_52", DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_52.class);
		classes.put("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_55", DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_55.class);
		classes.put("DaughterToFemale__SOURCE_58", DaughterToFemale__SOURCE_58.class);
		classes.put("DaughterToFemale__TARGET_62", DaughterToFemale__TARGET_62.class);
		classes.put("DaughterToFemale__FWD_65", DaughterToFemale__FWD_65.class);
		classes.put("DaughterToFemale__BWD_70", DaughterToFemale__BWD_70.class);
		classes.put("DaughterToFemale__CC_74", DaughterToFemale__CC_74.class);
		classes.put("DaughterToFemale__CONSISTENCY_80", DaughterToFemale__CONSISTENCY_80.class);
		classes.put("Families2Persons__SOURCE_88", Families2Persons__SOURCE_88.class);
		classes.put("Families2Persons__TARGET_90", Families2Persons__TARGET_90.class);
		classes.put("Families2Persons__FWD_92", Families2Persons__FWD_92.class);
		classes.put("Families2Persons__BWD_94", Families2Persons__BWD_94.class);
		classes.put("Families2Persons__CC_96", Families2Persons__CC_96.class);
		classes.put("Families2Persons__CONSISTENCY_99", Families2Persons__CONSISTENCY_99.class);
		classes.put("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_104", FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_104.class);
		classes.put("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_107", FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_107.class);
		classes.put("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_110", FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_110.class);
		classes.put("FatherOfExistingFamilyToMale__SOURCE_113", FatherOfExistingFamilyToMale__SOURCE_113.class);
		classes.put("FatherOfExistingFamilyToMale__TARGET_116", FatherOfExistingFamilyToMale__TARGET_116.class);
		classes.put("FatherOfExistingFamilyToMale__FWD_119", FatherOfExistingFamilyToMale__FWD_119.class);
		classes.put("FatherOfExistingFamilyToMale__BWD_123", FatherOfExistingFamilyToMale__BWD_123.class);
		classes.put("FatherOfExistingFamilyToMale__CC_127", FatherOfExistingFamilyToMale__CC_127.class);
		classes.put("FatherOfExistingFamilyToMale__CONSISTENCY_132", FatherOfExistingFamilyToMale__CONSISTENCY_132.class);
		classes.put("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_139", FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_139.class);
		classes.put("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_142", FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_142.class);
		classes.put("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_145", FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_145.class);
		classes.put("FatherToMale__SOURCE_148", FatherToMale__SOURCE_148.class);
		classes.put("FatherToMale__TARGET_152", FatherToMale__TARGET_152.class);
		classes.put("FatherToMale__FWD_155", FatherToMale__FWD_155.class);
		classes.put("FatherToMale__BWD_160", FatherToMale__BWD_160.class);
		classes.put("FatherToMale__CC_164", FatherToMale__CC_164.class);
		classes.put("FatherToMale__CONSISTENCY_170", FatherToMale__CONSISTENCY_170.class);
		classes.put("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_178", MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_178.class);
		classes.put("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_181", MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_181.class);
		classes.put("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_184", MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_184.class);
		classes.put("MotherOfExistingFamilyToFemale__SOURCE_187", MotherOfExistingFamilyToFemale__SOURCE_187.class);
		classes.put("MotherOfExistingFamilyToFemale__TARGET_190", MotherOfExistingFamilyToFemale__TARGET_190.class);
		classes.put("MotherOfExistingFamilyToFemale__FWD_193", MotherOfExistingFamilyToFemale__FWD_193.class);
		classes.put("MotherOfExistingFamilyToFemale__BWD_197", MotherOfExistingFamilyToFemale__BWD_197.class);
		classes.put("MotherOfExistingFamilyToFemale__CC_201", MotherOfExistingFamilyToFemale__CC_201.class);
		classes.put("MotherOfExistingFamilyToFemale__CONSISTENCY_206", MotherOfExistingFamilyToFemale__CONSISTENCY_206.class);
		classes.put("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_213", MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_213.class);
		classes.put("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_216", MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_216.class);
		classes.put("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_219", MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_219.class);
		classes.put("MotherToFemale__SOURCE_222", MotherToFemale__SOURCE_222.class);
		classes.put("MotherToFemale__TARGET_226", MotherToFemale__TARGET_226.class);
		classes.put("MotherToFemale__FWD_229", MotherToFemale__FWD_229.class);
		classes.put("MotherToFemale__BWD_234", MotherToFemale__BWD_234.class);
		classes.put("MotherToFemale__CC_238", MotherToFemale__CC_238.class);
		classes.put("MotherToFemale__CONSISTENCY_244", MotherToFemale__CONSISTENCY_244.class);
		classes.put("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_252", SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_252.class);
		classes.put("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_255", SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_255.class);
		classes.put("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_258", SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_258.class);
		classes.put("SonOfExistingFamilyToMale__SOURCE_261", SonOfExistingFamilyToMale__SOURCE_261.class);
		classes.put("SonOfExistingFamilyToMale__TARGET_264", SonOfExistingFamilyToMale__TARGET_264.class);
		classes.put("SonOfExistingFamilyToMale__FWD_267", SonOfExistingFamilyToMale__FWD_267.class);
		classes.put("SonOfExistingFamilyToMale__BWD_271", SonOfExistingFamilyToMale__BWD_271.class);
		classes.put("SonOfExistingFamilyToMale__CC_275", SonOfExistingFamilyToMale__CC_275.class);
		classes.put("SonOfExistingFamilyToMale__CONSISTENCY_280", SonOfExistingFamilyToMale__CONSISTENCY_280.class);
		classes.put("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_287", SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_287.class);
		classes.put("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_290", SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_290.class);
		classes.put("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_293", SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_293.class);
		classes.put("SonToMale__SOURCE_296", SonToMale__SOURCE_296.class);
		classes.put("SonToMale__TARGET_300", SonToMale__TARGET_300.class);
		classes.put("SonToMale__FWD_303", SonToMale__FWD_303.class);
		classes.put("SonToMale__BWD_308", SonToMale__BWD_308.class);
		classes.put("SonToMale__CC_312", SonToMale__CC_312.class);
		classes.put("SonToMale__CONSISTENCY_318", SonToMale__CONSISTENCY_318.class);
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
		classes.put("FamilyRegister_object_SP4",FamilyRegister_object_SP4.class);
		classes.put("FamilyRegister_object_SP5",FamilyRegister_object_SP5.class);
		classes.put("FamilyRegister_object_SP6",FamilyRegister_object_SP6.class);
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
		classes.put("Family_object_SP12",Family_object_SP12.class);
		classes.put("Family_object_SP13",Family_object_SP13.class);
		classes.put("Family_object_SP14",Family_object_SP14.class);
		classes.put("Family_object_SP15",Family_object_SP15.class);
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
		classes.put("FamilyMember_object_SP10",FamilyMember_object_SP10.class);
		classes.put("FamilyMember_object_SP11",FamilyMember_object_SP11.class);
		classes.put("FamilyMember_object_SP12",FamilyMember_object_SP12.class);
		classes.put("FamilyMember_object_SP13",FamilyMember_object_SP13.class);
		classes.put("PersonRegister_object_SP0",PersonRegister_object_SP0.class);
		classes.put("PersonRegister_object_SP1",PersonRegister_object_SP1.class);
		classes.put("PersonRegister_object_SP2",PersonRegister_object_SP2.class);
		classes.put("PersonRegister_object_SP3",PersonRegister_object_SP3.class);
		classes.put("PersonRegister_object_SP4",PersonRegister_object_SP4.class);
		classes.put("PersonRegister_object_SP5",PersonRegister_object_SP5.class);
		classes.put("PersonRegister_object_SP6",PersonRegister_object_SP6.class);
		classes.put("PersonRegister_object_SP7",PersonRegister_object_SP7.class);
		classes.put("PersonRegister_object_SP8",PersonRegister_object_SP8.class);
		classes.put("PersonRegister_object_SP9",PersonRegister_object_SP9.class);
		classes.put("PersonRegister_object_SP10",PersonRegister_object_SP10.class);
		classes.put("Female_object_SP0",Female_object_SP0.class);
		classes.put("Female_object_SP1",Female_object_SP1.class);
		classes.put("Female_object_SP2",Female_object_SP2.class);
		classes.put("Female_object_SP3",Female_object_SP3.class);
		classes.put("FamilyMemberToPersonCorr_object_SP0",FamilyMemberToPersonCorr_object_SP0.class);
		classes.put("FamilyMemberToPersonCorr_object_SP1",FamilyMemberToPersonCorr_object_SP1.class);
		classes.put("Male_object_SP0",Male_object_SP0.class);
		classes.put("Male_object_SP1",Male_object_SP1.class);
		classes.put("Male_object_SP2",Male_object_SP2.class);
		classes.put("Male_object_SP3",Male_object_SP3.class);
		
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
class FamilyRegister_object_SP4 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class FamilyRegister_object_SP5 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
class FamilyRegister_object_SP6 extends GenericObjectActor<FamiliesSmartEMF.FamilyRegister> { }
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
class Family_object_SP12 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP13 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP14 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
class Family_object_SP15 extends GenericObjectActor<FamiliesSmartEMF.Family> { }
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
class FamilyMember_object_SP10 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP11 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP12 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class FamilyMember_object_SP13 extends GenericObjectActor<FamiliesSmartEMF.FamilyMember> { }
class PersonRegister_object_SP0 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP1 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP2 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP3 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP4 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP5 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP6 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP7 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP8 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP9 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class PersonRegister_object_SP10 extends GenericObjectActor<PersonsSmartEMF.PersonRegister> { }
class Female_object_SP0 extends GenericObjectActor<PersonsSmartEMF.Female> { }
class Female_object_SP1 extends GenericObjectActor<PersonsSmartEMF.Female> { }
class Female_object_SP2 extends GenericObjectActor<PersonsSmartEMF.Female> { }
class Female_object_SP3 extends GenericObjectActor<PersonsSmartEMF.Female> { }
class FamilyMemberToPersonCorr_object_SP0 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr> { }
class FamilyMemberToPersonCorr_object_SP1 extends GenericObjectActor<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr> { }
class Male_object_SP0 extends GenericObjectActor<PersonsSmartEMF.Male> { }
class Male_object_SP1 extends GenericObjectActor<PersonsSmartEMF.Male> { }
class Male_object_SP2 extends GenericObjectActor<PersonsSmartEMF.Male> { }
class Male_object_SP3 extends GenericObjectActor<PersonsSmartEMF.Male> { }


