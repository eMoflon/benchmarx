package FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor;

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
	
	private ActorRef createFamily__SOURCE_1;
	private ActorRef createFamily__FWD_4;
	private ActorRef createFamily__CC_7;
	private ActorRef protocolNode_CreateFamily_object;
	private ActorRef createFamily__CONSISTENCY_10;
	private ActorRef daughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_14;
	private ActorRef daughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_17;
	private ActorRef daughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_20;
	private ActorRef daughterOfExistingFamilyToFemale__SOURCE_23;
	private ActorRef daughterOfExistingFamilyToFemale__TARGET_27;
	private ActorRef daughterOfExistingFamilyToFemale__FWD_30;
	private ActorRef daughterOfExistingFamilyToFemale__BWD_36;
	private ActorRef daughterOfExistingFamilyToFemale__CC_42;
	private ActorRef protocolNode_DaughterOfExistingFamilyToFemale_object;
	private ActorRef daughterOfExistingFamilyToFemale__CONSISTENCY_49;
	private ActorRef daughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_58;
	private ActorRef daughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_61;
	private ActorRef daughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_64;
	private ActorRef daughterToFemale__SOURCE_67;
	private ActorRef daughterToFemale__TARGET_71;
	private ActorRef daughterToFemale__FWD_74;
	private ActorRef daughterToFemale__BWD_80;
	private ActorRef daughterToFemale__CC_85;
	private ActorRef protocolNode_DaughterToFemale_object;
	private ActorRef daughterToFemale__CONSISTENCY_92;
	private ActorRef families2Persons__SOURCE_101;
	private ActorRef families2Persons__TARGET_103;
	private ActorRef families2Persons__FWD_105;
	private ActorRef families2Persons__BWD_107;
	private ActorRef families2Persons__CC_109;
	private ActorRef protocolNode_Families2Persons_object;
	private ActorRef families2Persons__CONSISTENCY_112;
	private ActorRef fatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_117;
	private ActorRef fatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_120;
	private ActorRef fatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_123;
	private ActorRef fatherOfExistingFamilyToMale__SOURCE_126;
	private ActorRef fatherOfExistingFamilyToMale__TARGET_130;
	private ActorRef fatherOfExistingFamilyToMale__FWD_133;
	private ActorRef fatherOfExistingFamilyToMale__BWD_139;
	private ActorRef fatherOfExistingFamilyToMale__CC_145;
	private ActorRef protocolNode_FatherOfExistingFamilyToMale_object;
	private ActorRef fatherOfExistingFamilyToMale__CONSISTENCY_152;
	private ActorRef fatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_161;
	private ActorRef fatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_164;
	private ActorRef fatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_167;
	private ActorRef fatherToMale__SOURCE_170;
	private ActorRef fatherToMale__TARGET_174;
	private ActorRef fatherToMale__FWD_177;
	private ActorRef fatherToMale__BWD_183;
	private ActorRef fatherToMale__CC_188;
	private ActorRef protocolNode_FatherToMale_object;
	private ActorRef fatherToMale__CONSISTENCY_195;
	private ActorRef motherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_204;
	private ActorRef motherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_207;
	private ActorRef motherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_210;
	private ActorRef motherOfExistingFamilyToFemale__SOURCE_213;
	private ActorRef motherOfExistingFamilyToFemale__TARGET_217;
	private ActorRef motherOfExistingFamilyToFemale__FWD_220;
	private ActorRef motherOfExistingFamilyToFemale__BWD_226;
	private ActorRef motherOfExistingFamilyToFemale__CC_232;
	private ActorRef protocolNode_MotherOfExistingFamilyToFemale_object;
	private ActorRef motherOfExistingFamilyToFemale__CONSISTENCY_239;
	private ActorRef motherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_248;
	private ActorRef motherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_251;
	private ActorRef motherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_254;
	private ActorRef motherToFemale__SOURCE_257;
	private ActorRef motherToFemale__TARGET_261;
	private ActorRef motherToFemale__FWD_264;
	private ActorRef motherToFemale__BWD_270;
	private ActorRef motherToFemale__CC_275;
	private ActorRef protocolNode_MotherToFemale_object;
	private ActorRef motherToFemale__CONSISTENCY_282;
	private ActorRef sonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_291;
	private ActorRef sonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_294;
	private ActorRef sonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_297;
	private ActorRef sonOfExistingFamilyToMale__SOURCE_300;
	private ActorRef sonOfExistingFamilyToMale__TARGET_304;
	private ActorRef sonOfExistingFamilyToMale__FWD_307;
	private ActorRef sonOfExistingFamilyToMale__BWD_313;
	private ActorRef sonOfExistingFamilyToMale__CC_319;
	private ActorRef protocolNode_SonOfExistingFamilyToMale_object;
	private ActorRef sonOfExistingFamilyToMale__CONSISTENCY_326;
	private ActorRef sonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_335;
	private ActorRef sonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_338;
	private ActorRef sonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_341;
	private ActorRef sonToMale__SOURCE_344;
	private ActorRef sonToMale__TARGET_348;
	private ActorRef sonToMale__FWD_351;
	private ActorRef sonToMale__BWD_357;
	private ActorRef sonToMale__CC_362;
	private ActorRef protocolNode_SonToMale_object;
	private ActorRef sonToMale__CONSISTENCY_369;
	private ActorRef familyRegister_object_SP0;
	private ActorRef familyRegister_object_SP1;
	private ActorRef familyRegister_object_SP2;
	private ActorRef familyRegister_object_SP3;
	private ActorRef familyRegister_object_SP4;
	private ActorRef familyRegister_object_SP5;
	private ActorRef familyRegister_object_SP6;
	private ActorRef familyRegister_object_SP7;
	private ActorRef familyRegister_object_SP8;
	private ActorRef familyRegister_object_SP9;
	private ActorRef familyRegister_object_SP10;
	private ActorRef familyRegister_object_SP11;
	private ActorRef family_object_SP0;
	private ActorRef family_object_SP1;
	private ActorRef family_object_SP2;
	private ActorRef family_object_SP3;
	private ActorRef family_object_SP4;
	private ActorRef family_object_SP5;
	private ActorRef family_object_SP6;
	private ActorRef family_object_SP7;
	private ActorRef family_object_SP8;
	private ActorRef family_object_SP9;
	private ActorRef family_object_SP10;
	private ActorRef family_object_SP11;
	private ActorRef family_object_SP12;
	private ActorRef family_object_SP13;
	private ActorRef family_object_SP14;
	private ActorRef family_object_SP15;
	private ActorRef familyMember_object_SP0;
	private ActorRef familyMember_object_SP1;
	private ActorRef familyMember_object_SP2;
	private ActorRef familyMember_object_SP3;
	private ActorRef familyMember_object_SP4;
	private ActorRef familyMember_object_SP5;
	private ActorRef familyMember_object_SP6;
	private ActorRef familyMember_object_SP7;
	private ActorRef familyMember_object_SP8;
	private ActorRef familyMember_object_SP9;
	private ActorRef familyMember_object_SP10;
	private ActorRef familyMember_object_SP11;
	private ActorRef familyMember_object_SP12;
	private ActorRef familyMember_object_SP13;
	private ActorRef personRegister_object_SP0;
	private ActorRef personRegister_object_SP1;
	private ActorRef personRegister_object_SP2;
	private ActorRef personRegister_object_SP3;
	private ActorRef personRegister_object_SP4;
	private ActorRef personRegister_object_SP5;
	private ActorRef personRegister_object_SP6;
	private ActorRef personRegister_object_SP7;
	private ActorRef personRegister_object_SP8;
	private ActorRef personRegister_object_SP9;
	private ActorRef personRegister_object_SP10;
	private ActorRef female_object_SP0;
	private ActorRef female_object_SP1;
	private ActorRef female_object_SP2;
	private ActorRef female_object_SP3;
	private ActorRef registerToRegisterCorr_object_SP0;
	private ActorRef registerToRegisterCorr_object_SP1;
	private ActorRef registerToRegisterCorr_object_SP2;
	private ActorRef registerToRegisterCorr_object_SP3;
	private ActorRef registerToRegisterCorr_object_SP4;
	private ActorRef registerToRegisterCorr_object_SP5;
	private ActorRef registerToRegisterCorr_object_SP6;
	private ActorRef registerToRegisterCorr_object_SP7;
	private ActorRef registerToRegisterCorr_object_SP8;
	private ActorRef familyMemberToPersonCorr_object_SP0;
	private ActorRef familyMemberToPersonCorr_object_SP1;
	private ActorRef male_object_SP0;
	private ActorRef male_object_SP1;
	private ActorRef male_object_SP2;
	private ActorRef male_object_SP3;
	
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
		createFamily__SOURCE_1 = name2actor.get("CreateFamily__SOURCE_1").iterator().next();
		createFamily__FWD_4 = name2actor.get("CreateFamily__FWD_4").iterator().next();
		createFamily__CC_7 = name2actor.get("CreateFamily__CC_7").iterator().next();
		protocolNode_CreateFamily_object = name2actor.get("ProtocolNode_CreateFamily_object").iterator().next();
		createFamily__CONSISTENCY_10 = name2actor.get("CreateFamily__CONSISTENCY_10").iterator().next();
		daughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_14 = name2actor.get("DaughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_14").iterator().next();
		daughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_17 = name2actor.get("DaughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_17").iterator().next();
		daughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_20 = name2actor.get("DaughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_20").iterator().next();
		daughterOfExistingFamilyToFemale__SOURCE_23 = name2actor.get("DaughterOfExistingFamilyToFemale__SOURCE_23").iterator().next();
		daughterOfExistingFamilyToFemale__TARGET_27 = name2actor.get("DaughterOfExistingFamilyToFemale__TARGET_27").iterator().next();
		daughterOfExistingFamilyToFemale__FWD_30 = name2actor.get("DaughterOfExistingFamilyToFemale__FWD_30").iterator().next();
		daughterOfExistingFamilyToFemale__BWD_36 = name2actor.get("DaughterOfExistingFamilyToFemale__BWD_36").iterator().next();
		daughterOfExistingFamilyToFemale__CC_42 = name2actor.get("DaughterOfExistingFamilyToFemale__CC_42").iterator().next();
		protocolNode_DaughterOfExistingFamilyToFemale_object = name2actor.get("ProtocolNode_DaughterOfExistingFamilyToFemale_object").iterator().next();
		daughterOfExistingFamilyToFemale__CONSISTENCY_49 = name2actor.get("DaughterOfExistingFamilyToFemale__CONSISTENCY_49").iterator().next();
		daughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_58 = name2actor.get("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_58").iterator().next();
		daughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_61 = name2actor.get("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_61").iterator().next();
		daughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_64 = name2actor.get("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_64").iterator().next();
		daughterToFemale__SOURCE_67 = name2actor.get("DaughterToFemale__SOURCE_67").iterator().next();
		daughterToFemale__TARGET_71 = name2actor.get("DaughterToFemale__TARGET_71").iterator().next();
		daughterToFemale__FWD_74 = name2actor.get("DaughterToFemale__FWD_74").iterator().next();
		daughterToFemale__BWD_80 = name2actor.get("DaughterToFemale__BWD_80").iterator().next();
		daughterToFemale__CC_85 = name2actor.get("DaughterToFemale__CC_85").iterator().next();
		protocolNode_DaughterToFemale_object = name2actor.get("ProtocolNode_DaughterToFemale_object").iterator().next();
		daughterToFemale__CONSISTENCY_92 = name2actor.get("DaughterToFemale__CONSISTENCY_92").iterator().next();
		families2Persons__SOURCE_101 = name2actor.get("Families2Persons__SOURCE_101").iterator().next();
		families2Persons__TARGET_103 = name2actor.get("Families2Persons__TARGET_103").iterator().next();
		families2Persons__FWD_105 = name2actor.get("Families2Persons__FWD_105").iterator().next();
		families2Persons__BWD_107 = name2actor.get("Families2Persons__BWD_107").iterator().next();
		families2Persons__CC_109 = name2actor.get("Families2Persons__CC_109").iterator().next();
		protocolNode_Families2Persons_object = name2actor.get("ProtocolNode_Families2Persons_object").iterator().next();
		families2Persons__CONSISTENCY_112 = name2actor.get("Families2Persons__CONSISTENCY_112").iterator().next();
		fatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_117 = name2actor.get("FatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_117").iterator().next();
		fatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_120 = name2actor.get("FatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_120").iterator().next();
		fatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_123 = name2actor.get("FatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_123").iterator().next();
		fatherOfExistingFamilyToMale__SOURCE_126 = name2actor.get("FatherOfExistingFamilyToMale__SOURCE_126").iterator().next();
		fatherOfExistingFamilyToMale__TARGET_130 = name2actor.get("FatherOfExistingFamilyToMale__TARGET_130").iterator().next();
		fatherOfExistingFamilyToMale__FWD_133 = name2actor.get("FatherOfExistingFamilyToMale__FWD_133").iterator().next();
		fatherOfExistingFamilyToMale__BWD_139 = name2actor.get("FatherOfExistingFamilyToMale__BWD_139").iterator().next();
		fatherOfExistingFamilyToMale__CC_145 = name2actor.get("FatherOfExistingFamilyToMale__CC_145").iterator().next();
		protocolNode_FatherOfExistingFamilyToMale_object = name2actor.get("ProtocolNode_FatherOfExistingFamilyToMale_object").iterator().next();
		fatherOfExistingFamilyToMale__CONSISTENCY_152 = name2actor.get("FatherOfExistingFamilyToMale__CONSISTENCY_152").iterator().next();
		fatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_161 = name2actor.get("FatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_161").iterator().next();
		fatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_164 = name2actor.get("FatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_164").iterator().next();
		fatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_167 = name2actor.get("FatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_167").iterator().next();
		fatherToMale__SOURCE_170 = name2actor.get("FatherToMale__SOURCE_170").iterator().next();
		fatherToMale__TARGET_174 = name2actor.get("FatherToMale__TARGET_174").iterator().next();
		fatherToMale__FWD_177 = name2actor.get("FatherToMale__FWD_177").iterator().next();
		fatherToMale__BWD_183 = name2actor.get("FatherToMale__BWD_183").iterator().next();
		fatherToMale__CC_188 = name2actor.get("FatherToMale__CC_188").iterator().next();
		protocolNode_FatherToMale_object = name2actor.get("ProtocolNode_FatherToMale_object").iterator().next();
		fatherToMale__CONSISTENCY_195 = name2actor.get("FatherToMale__CONSISTENCY_195").iterator().next();
		motherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_204 = name2actor.get("MotherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_204").iterator().next();
		motherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_207 = name2actor.get("MotherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_207").iterator().next();
		motherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_210 = name2actor.get("MotherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_210").iterator().next();
		motherOfExistingFamilyToFemale__SOURCE_213 = name2actor.get("MotherOfExistingFamilyToFemale__SOURCE_213").iterator().next();
		motherOfExistingFamilyToFemale__TARGET_217 = name2actor.get("MotherOfExistingFamilyToFemale__TARGET_217").iterator().next();
		motherOfExistingFamilyToFemale__FWD_220 = name2actor.get("MotherOfExistingFamilyToFemale__FWD_220").iterator().next();
		motherOfExistingFamilyToFemale__BWD_226 = name2actor.get("MotherOfExistingFamilyToFemale__BWD_226").iterator().next();
		motherOfExistingFamilyToFemale__CC_232 = name2actor.get("MotherOfExistingFamilyToFemale__CC_232").iterator().next();
		protocolNode_MotherOfExistingFamilyToFemale_object = name2actor.get("ProtocolNode_MotherOfExistingFamilyToFemale_object").iterator().next();
		motherOfExistingFamilyToFemale__CONSISTENCY_239 = name2actor.get("MotherOfExistingFamilyToFemale__CONSISTENCY_239").iterator().next();
		motherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_248 = name2actor.get("MotherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_248").iterator().next();
		motherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_251 = name2actor.get("MotherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_251").iterator().next();
		motherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_254 = name2actor.get("MotherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_254").iterator().next();
		motherToFemale__SOURCE_257 = name2actor.get("MotherToFemale__SOURCE_257").iterator().next();
		motherToFemale__TARGET_261 = name2actor.get("MotherToFemale__TARGET_261").iterator().next();
		motherToFemale__FWD_264 = name2actor.get("MotherToFemale__FWD_264").iterator().next();
		motherToFemale__BWD_270 = name2actor.get("MotherToFemale__BWD_270").iterator().next();
		motherToFemale__CC_275 = name2actor.get("MotherToFemale__CC_275").iterator().next();
		protocolNode_MotherToFemale_object = name2actor.get("ProtocolNode_MotherToFemale_object").iterator().next();
		motherToFemale__CONSISTENCY_282 = name2actor.get("MotherToFemale__CONSISTENCY_282").iterator().next();
		sonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_291 = name2actor.get("SonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_291").iterator().next();
		sonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_294 = name2actor.get("SonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_294").iterator().next();
		sonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_297 = name2actor.get("SonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_297").iterator().next();
		sonOfExistingFamilyToMale__SOURCE_300 = name2actor.get("SonOfExistingFamilyToMale__SOURCE_300").iterator().next();
		sonOfExistingFamilyToMale__TARGET_304 = name2actor.get("SonOfExistingFamilyToMale__TARGET_304").iterator().next();
		sonOfExistingFamilyToMale__FWD_307 = name2actor.get("SonOfExistingFamilyToMale__FWD_307").iterator().next();
		sonOfExistingFamilyToMale__BWD_313 = name2actor.get("SonOfExistingFamilyToMale__BWD_313").iterator().next();
		sonOfExistingFamilyToMale__CC_319 = name2actor.get("SonOfExistingFamilyToMale__CC_319").iterator().next();
		protocolNode_SonOfExistingFamilyToMale_object = name2actor.get("ProtocolNode_SonOfExistingFamilyToMale_object").iterator().next();
		sonOfExistingFamilyToMale__CONSISTENCY_326 = name2actor.get("SonOfExistingFamilyToMale__CONSISTENCY_326").iterator().next();
		sonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_335 = name2actor.get("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_335").iterator().next();
		sonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_338 = name2actor.get("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_338").iterator().next();
		sonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_341 = name2actor.get("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_341").iterator().next();
		sonToMale__SOURCE_344 = name2actor.get("SonToMale__SOURCE_344").iterator().next();
		sonToMale__TARGET_348 = name2actor.get("SonToMale__TARGET_348").iterator().next();
		sonToMale__FWD_351 = name2actor.get("SonToMale__FWD_351").iterator().next();
		sonToMale__BWD_357 = name2actor.get("SonToMale__BWD_357").iterator().next();
		sonToMale__CC_362 = name2actor.get("SonToMale__CC_362").iterator().next();
		protocolNode_SonToMale_object = name2actor.get("ProtocolNode_SonToMale_object").iterator().next();
		sonToMale__CONSISTENCY_369 = name2actor.get("SonToMale__CONSISTENCY_369").iterator().next();
		familyRegister_object_SP0 = name2actor.get("FamilyRegister_object_SP0").iterator().next();
		familyRegister_object_SP1 = name2actor.get("FamilyRegister_object_SP1").iterator().next();
		familyRegister_object_SP2 = name2actor.get("FamilyRegister_object_SP2").iterator().next();
		familyRegister_object_SP3 = name2actor.get("FamilyRegister_object_SP3").iterator().next();
		familyRegister_object_SP4 = name2actor.get("FamilyRegister_object_SP4").iterator().next();
		familyRegister_object_SP5 = name2actor.get("FamilyRegister_object_SP5").iterator().next();
		familyRegister_object_SP6 = name2actor.get("FamilyRegister_object_SP6").iterator().next();
		familyRegister_object_SP7 = name2actor.get("FamilyRegister_object_SP7").iterator().next();
		familyRegister_object_SP8 = name2actor.get("FamilyRegister_object_SP8").iterator().next();
		familyRegister_object_SP9 = name2actor.get("FamilyRegister_object_SP9").iterator().next();
		familyRegister_object_SP10 = name2actor.get("FamilyRegister_object_SP10").iterator().next();
		familyRegister_object_SP11 = name2actor.get("FamilyRegister_object_SP11").iterator().next();
		family_object_SP0 = name2actor.get("Family_object_SP0").iterator().next();
		family_object_SP1 = name2actor.get("Family_object_SP1").iterator().next();
		family_object_SP2 = name2actor.get("Family_object_SP2").iterator().next();
		family_object_SP3 = name2actor.get("Family_object_SP3").iterator().next();
		family_object_SP4 = name2actor.get("Family_object_SP4").iterator().next();
		family_object_SP5 = name2actor.get("Family_object_SP5").iterator().next();
		family_object_SP6 = name2actor.get("Family_object_SP6").iterator().next();
		family_object_SP7 = name2actor.get("Family_object_SP7").iterator().next();
		family_object_SP8 = name2actor.get("Family_object_SP8").iterator().next();
		family_object_SP9 = name2actor.get("Family_object_SP9").iterator().next();
		family_object_SP10 = name2actor.get("Family_object_SP10").iterator().next();
		family_object_SP11 = name2actor.get("Family_object_SP11").iterator().next();
		family_object_SP12 = name2actor.get("Family_object_SP12").iterator().next();
		family_object_SP13 = name2actor.get("Family_object_SP13").iterator().next();
		family_object_SP14 = name2actor.get("Family_object_SP14").iterator().next();
		family_object_SP15 = name2actor.get("Family_object_SP15").iterator().next();
		familyMember_object_SP0 = name2actor.get("FamilyMember_object_SP0").iterator().next();
		familyMember_object_SP1 = name2actor.get("FamilyMember_object_SP1").iterator().next();
		familyMember_object_SP2 = name2actor.get("FamilyMember_object_SP2").iterator().next();
		familyMember_object_SP3 = name2actor.get("FamilyMember_object_SP3").iterator().next();
		familyMember_object_SP4 = name2actor.get("FamilyMember_object_SP4").iterator().next();
		familyMember_object_SP5 = name2actor.get("FamilyMember_object_SP5").iterator().next();
		familyMember_object_SP6 = name2actor.get("FamilyMember_object_SP6").iterator().next();
		familyMember_object_SP7 = name2actor.get("FamilyMember_object_SP7").iterator().next();
		familyMember_object_SP8 = name2actor.get("FamilyMember_object_SP8").iterator().next();
		familyMember_object_SP9 = name2actor.get("FamilyMember_object_SP9").iterator().next();
		familyMember_object_SP10 = name2actor.get("FamilyMember_object_SP10").iterator().next();
		familyMember_object_SP11 = name2actor.get("FamilyMember_object_SP11").iterator().next();
		familyMember_object_SP12 = name2actor.get("FamilyMember_object_SP12").iterator().next();
		familyMember_object_SP13 = name2actor.get("FamilyMember_object_SP13").iterator().next();
		personRegister_object_SP0 = name2actor.get("PersonRegister_object_SP0").iterator().next();
		personRegister_object_SP1 = name2actor.get("PersonRegister_object_SP1").iterator().next();
		personRegister_object_SP2 = name2actor.get("PersonRegister_object_SP2").iterator().next();
		personRegister_object_SP3 = name2actor.get("PersonRegister_object_SP3").iterator().next();
		personRegister_object_SP4 = name2actor.get("PersonRegister_object_SP4").iterator().next();
		personRegister_object_SP5 = name2actor.get("PersonRegister_object_SP5").iterator().next();
		personRegister_object_SP6 = name2actor.get("PersonRegister_object_SP6").iterator().next();
		personRegister_object_SP7 = name2actor.get("PersonRegister_object_SP7").iterator().next();
		personRegister_object_SP8 = name2actor.get("PersonRegister_object_SP8").iterator().next();
		personRegister_object_SP9 = name2actor.get("PersonRegister_object_SP9").iterator().next();
		personRegister_object_SP10 = name2actor.get("PersonRegister_object_SP10").iterator().next();
		female_object_SP0 = name2actor.get("Female_object_SP0").iterator().next();
		female_object_SP1 = name2actor.get("Female_object_SP1").iterator().next();
		female_object_SP2 = name2actor.get("Female_object_SP2").iterator().next();
		female_object_SP3 = name2actor.get("Female_object_SP3").iterator().next();
		registerToRegisterCorr_object_SP0 = name2actor.get("RegisterToRegisterCorr_object_SP0").iterator().next();
		registerToRegisterCorr_object_SP1 = name2actor.get("RegisterToRegisterCorr_object_SP1").iterator().next();
		registerToRegisterCorr_object_SP2 = name2actor.get("RegisterToRegisterCorr_object_SP2").iterator().next();
		registerToRegisterCorr_object_SP3 = name2actor.get("RegisterToRegisterCorr_object_SP3").iterator().next();
		registerToRegisterCorr_object_SP4 = name2actor.get("RegisterToRegisterCorr_object_SP4").iterator().next();
		registerToRegisterCorr_object_SP5 = name2actor.get("RegisterToRegisterCorr_object_SP5").iterator().next();
		registerToRegisterCorr_object_SP6 = name2actor.get("RegisterToRegisterCorr_object_SP6").iterator().next();
		registerToRegisterCorr_object_SP7 = name2actor.get("RegisterToRegisterCorr_object_SP7").iterator().next();
		registerToRegisterCorr_object_SP8 = name2actor.get("RegisterToRegisterCorr_object_SP8").iterator().next();
		familyMemberToPersonCorr_object_SP0 = name2actor.get("FamilyMemberToPersonCorr_object_SP0").iterator().next();
		familyMemberToPersonCorr_object_SP1 = name2actor.get("FamilyMemberToPersonCorr_object_SP1").iterator().next();
		male_object_SP0 = name2actor.get("Male_object_SP0").iterator().next();
		male_object_SP1 = name2actor.get("Male_object_SP1").iterator().next();
		male_object_SP2 = name2actor.get("Male_object_SP2").iterator().next();
		male_object_SP3 = name2actor.get("Male_object_SP3").iterator().next();
	}
	
	private void initializeAdd() {
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale(), (obj) -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale _protocolnode_sonofexistingfamilytomale = (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) obj;
			incUtil.newMessage();
			protocolNode_SonOfExistingFamilyToMale_object.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale>(incUtil, _protocolnode_sonofexistingfamilytomale), getSelf());
		});
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(), (obj) -> {
			PersonsSmartEMF.PersonRegister _personregister = (PersonsSmartEMF.PersonRegister) obj;
			incUtil.newMessage();
			personRegister_object_SP0.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP1.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP2.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP3.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP4.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP5.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP6.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP7.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP8.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP9.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
			incUtil.newMessage();
			personRegister_object_SP10.tell(new ObjectAdded<PersonsSmartEMF.PersonRegister>(incUtil, _personregister), getSelf());
		});
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getFemale(), (obj) -> {
			PersonsSmartEMF.Female _female = (PersonsSmartEMF.Female) obj;
			incUtil.newMessage();
			female_object_SP0.tell(new ObjectAdded<PersonsSmartEMF.Female>(incUtil, _female), getSelf());
			incUtil.newMessage();
			female_object_SP1.tell(new ObjectAdded<PersonsSmartEMF.Female>(incUtil, _female), getSelf());
			incUtil.newMessage();
			female_object_SP2.tell(new ObjectAdded<PersonsSmartEMF.Female>(incUtil, _female), getSelf());
			incUtil.newMessage();
			female_object_SP3.tell(new ObjectAdded<PersonsSmartEMF.Female>(incUtil, _female), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale(), (obj) -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale _protocolnode_mothertofemale = (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) obj;
			incUtil.newMessage();
			protocolNode_MotherToFemale_object.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale>(incUtil, _protocolnode_mothertofemale), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr(), (obj) -> {
			FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr _familymembertopersoncorr = (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) obj;
			incUtil.newMessage();
			familyMemberToPersonCorr_object_SP0.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, _familymembertopersoncorr), getSelf());
			incUtil.newMessage();
			familyMemberToPersonCorr_object_SP1.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, _familymembertopersoncorr), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_CreateFamily(), (obj) -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily _protocolnode_createfamily = (FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) obj;
			incUtil.newMessage();
			protocolNode_CreateFamily_object.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily>(incUtil, _protocolnode_createfamily), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons(), (obj) -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons _protocolnode_families2persons = (FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) obj;
			incUtil.newMessage();
			protocolNode_Families2Persons_object.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons>(incUtil, _protocolnode_families2persons), getSelf());
		});
		type2addConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(), (obj) -> {
			FamiliesSmartEMF.FamilyRegister _familyregister = (FamiliesSmartEMF.FamilyRegister) obj;
			incUtil.newMessage();
			familyRegister_object_SP0.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP1.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP2.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP3.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP4.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP5.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP6.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP7.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP8.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP9.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP10.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
			incUtil.newMessage();
			familyRegister_object_SP11.tell(new ObjectAdded<FamiliesSmartEMF.FamilyRegister>(incUtil, _familyregister), getSelf());
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
			incUtil.newMessage();
			familyMember_object_SP8.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP9.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP10.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP11.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP12.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
			incUtil.newMessage();
			familyMember_object_SP13.tell(new ObjectAdded<FamiliesSmartEMF.FamilyMember>(incUtil, _familymember), getSelf());
		});
		type2addConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getMale(), (obj) -> {
			PersonsSmartEMF.Male _male = (PersonsSmartEMF.Male) obj;
			incUtil.newMessage();
			male_object_SP0.tell(new ObjectAdded<PersonsSmartEMF.Male>(incUtil, _male), getSelf());
			incUtil.newMessage();
			male_object_SP1.tell(new ObjectAdded<PersonsSmartEMF.Male>(incUtil, _male), getSelf());
			incUtil.newMessage();
			male_object_SP2.tell(new ObjectAdded<PersonsSmartEMF.Male>(incUtil, _male), getSelf());
			incUtil.newMessage();
			male_object_SP3.tell(new ObjectAdded<PersonsSmartEMF.Male>(incUtil, _male), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale(), (obj) -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale _protocolnode_fathertomale = (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) obj;
			incUtil.newMessage();
			protocolNode_FatherToMale_object.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale>(incUtil, _protocolnode_fathertomale), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale(), (obj) -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale _protocolnode_daughterofexistingfamilytofemale = (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) obj;
			incUtil.newMessage();
			protocolNode_DaughterOfExistingFamilyToFemale_object.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale>(incUtil, _protocolnode_daughterofexistingfamilytofemale), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale(), (obj) -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale _protocolnode_fatherofexistingfamilytomale = (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) obj;
			incUtil.newMessage();
			protocolNode_FatherOfExistingFamilyToMale_object.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale>(incUtil, _protocolnode_fatherofexistingfamilytomale), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale(), (obj) -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale _protocolnode_daughtertofemale = (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) obj;
			incUtil.newMessage();
			protocolNode_DaughterToFemale_object.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale>(incUtil, _protocolnode_daughtertofemale), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale(), (obj) -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale _protocolnode_motherofexistingfamilytofemale = (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) obj;
			incUtil.newMessage();
			protocolNode_MotherOfExistingFamilyToFemale_object.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale>(incUtil, _protocolnode_motherofexistingfamilytofemale), getSelf());
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
			incUtil.newMessage();
			family_object_SP9.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP10.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP11.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP12.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP13.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP14.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
			incUtil.newMessage();
			family_object_SP15.tell(new ObjectAdded<FamiliesSmartEMF.Family>(incUtil, _family), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr(), (obj) -> {
			FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr _registertoregistercorr = (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) obj;
			incUtil.newMessage();
			registerToRegisterCorr_object_SP0.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
			incUtil.newMessage();
			registerToRegisterCorr_object_SP1.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
			incUtil.newMessage();
			registerToRegisterCorr_object_SP2.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
			incUtil.newMessage();
			registerToRegisterCorr_object_SP3.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
			incUtil.newMessage();
			registerToRegisterCorr_object_SP4.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
			incUtil.newMessage();
			registerToRegisterCorr_object_SP5.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
			incUtil.newMessage();
			registerToRegisterCorr_object_SP6.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
			incUtil.newMessage();
			registerToRegisterCorr_object_SP7.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
			incUtil.newMessage();
			registerToRegisterCorr_object_SP8.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, _registertoregistercorr), getSelf());
		});
		type2addConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale(), (obj) -> {
			FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale _protocolnode_sontomale = (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) obj;
			incUtil.newMessage();
			protocolNode_SonToMale_object.tell(new ObjectAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale>(incUtil, _protocolnode_sontomale), getSelf());
		});
	}
	
	private void initializeSet() {
		feature2setConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPerson_Name(), (notification) -> {
			if(notification.getNotifier() instanceof PersonsSmartEMF.Female) {
				incUtil.newMessage();
				female_object_SP0.tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof PersonsSmartEMF.Female) {
				incUtil.newMessage();
				female_object_SP2.tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof PersonsSmartEMF.Male) {
				incUtil.newMessage();
				male_object_SP0.tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof PersonsSmartEMF.Male) {
				incUtil.newMessage();
				male_object_SP1.tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof PersonsSmartEMF.Female) {
				incUtil.newMessage();
				female_object_SP3.tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof PersonsSmartEMF.Female) {
				incUtil.newMessage();
				female_object_SP1.tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof PersonsSmartEMF.Male) {
				incUtil.newMessage();
				male_object_SP3.tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof PersonsSmartEMF.Male) {
				incUtil.newMessage();
				male_object_SP2.tell(new AttributeChanged<PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.Person) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
		});
		
		feature2setConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Name(), (notification) -> {
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP1.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP7.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP6.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP11.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP14.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP5.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP9.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP3.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP8.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP0.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP4.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP15.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP13.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP10.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP2.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.Family) {
				incUtil.newMessage();
				family_object_SP12.tell(new AttributeChanged<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
		});
		
		feature2setConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember_Name(), (notification) -> {
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP2.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP11.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP4.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP9.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP3.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP8.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP13.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP0.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP12.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP6.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP1.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP5.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP7.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
			if(notification.getNotifier() instanceof FamiliesSmartEMF.FamilyMember) {
				incUtil.newMessage();
				familyMember_object_SP10.tell(new AttributeChanged<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) notification.getNotifier(), notification.getOldValue()), getSelf());
			}
		});
		
	}
	
	private void initializeAddEdge() {
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, PersonsSmartEMF.Female>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			families2Persons__CONSISTENCY_112.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			families2Persons__CONSISTENCY_112.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_30.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_36.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CC_42.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_74.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_80.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CC_85.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			families2Persons__CONSISTENCY_112.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_133.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_139.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CC_145.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_177.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_183.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__CC_188.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_220.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_226.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CC_232.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_264.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_270.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__CC_275.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_307.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_313.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CC_319.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_351.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_357.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__CC_362.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Mother(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_20.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_61.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_120.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_161.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__SOURCE_213.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_220.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CC_232.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__SOURCE_257.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_264.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__CC_275.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_294.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_341.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Father(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_17.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_64.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__SOURCE_126.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_133.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CC_145.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__SOURCE_170.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_177.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__CC_188.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_204.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_248.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_291.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_338.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, PersonsSmartEMF.Female>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_30.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_36.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CC_42.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_74.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_80.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CC_85.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			families2Persons__CONSISTENCY_112.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_133.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_139.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CC_145.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_177.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_183.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__CC_188.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_220.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_226.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CC_232.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_264.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_270.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__CC_275.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_307.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_313.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CC_319.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_351.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_357.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__CC_362.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_CreateFamily_CREATE__SOURCE__family(), (notification) -> {
			incUtil.newMessage();
			createFamily__CONSISTENCY_10.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily_CREATE__SOURCE__family_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__TARGET_27.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_36.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CC_42.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__TARGET_71.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_80.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CC_85.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__TARGET_130.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_139.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CC_145.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__TARGET_174.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_183.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__CC_188.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__TARGET_217.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_226.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CC_232.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__TARGET_261.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_270.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__CC_275.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__TARGET_304.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_313.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CC_319.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__TARGET_348.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_357.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__CC_362.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil,(PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Target(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, PersonsSmartEMF.Male>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Daughters(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__SOURCE_23.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_30.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CC_42.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__SOURCE_67.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_74.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CC_85.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_123.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_167.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_210.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_254.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_297.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_335.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, PersonsSmartEMF.Male>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), (notification) -> {
			incUtil.newMessage();
			createFamily__SOURCE_1.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			createFamily__FWD_4.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			createFamily__CC_7.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			createFamily__CONSISTENCY_10.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__SOURCE_23.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_30.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_36.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CC_42.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterToFemale__SOURCE_67.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_74.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CC_85.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__SOURCE_126.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_133.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_139.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CC_145.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherToMale__SOURCE_170.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_177.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherToMale__CC_188.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__SOURCE_213.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_220.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_226.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CC_232.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherToFemale__SOURCE_257.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_264.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherToFemale__CC_275.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__SOURCE_300.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_307.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_313.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CC_319.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonToMale__SOURCE_344.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_351.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonToMale__CC_362.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil,(FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesSmartEMF.Family>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, PersonsSmartEMF.PersonRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, PersonsSmartEMF.Female>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Source(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, PersonsSmartEMF.Male>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_CreateFamily_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			createFamily__CONSISTENCY_10.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, PersonsSmartEMF.Male>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, PersonsSmartEMF.Female>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesSmartEMF.FamilyRegister>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			families2Persons__CONSISTENCY_112.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2addEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Sons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_14.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_58.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_117.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_164.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_207.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_251.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__SOURCE_300.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_307.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CC_319.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__SOURCE_344.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_351.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__CC_362.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceAdded<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil,(FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getNewValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
		});
	}
	
	private void initializeRemoveEdge() {
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, PersonsSmartEMF.Female>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			families2Persons__CONSISTENCY_112.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			families2Persons__CONSISTENCY_112.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_30.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_36.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CC_42.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_74.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_80.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CC_85.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			families2Persons__CONSISTENCY_112.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_133.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_139.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CC_145.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_177.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_183.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__CC_188.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_220.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_226.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CC_232.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_264.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_270.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__CC_275.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_307.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_313.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CC_319.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_351.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_357.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__CC_362.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Mother(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_20.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_61.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_120.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_161.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__SOURCE_213.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_220.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CC_232.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__SOURCE_257.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_264.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__CC_275.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_294.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_341.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_mother_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Father(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_17.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_64.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__SOURCE_126.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_133.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CC_145.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__SOURCE_170.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_177.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__CC_188.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_204.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_248.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_291.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_338.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_father_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, PersonsSmartEMF.Female>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_30.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_36.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CC_42.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_74.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_80.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CC_85.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			families2Persons__CONSISTENCY_112.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_133.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_139.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CC_145.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_177.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_183.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__CC_188.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_220.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_226.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CC_232.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_264.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_270.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__CC_275.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_307.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_313.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CC_319.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_351.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_357.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__CC_362.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_CreateFamily_CREATE__SOURCE__family(), (notification) -> {
			incUtil.newMessage();
			createFamily__CONSISTENCY_10.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily_CREATE__SOURCE__family_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__TARGET_27.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_36.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CC_42.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__TARGET_71.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__BWD_80.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CC_85.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__TARGET_130.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_139.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CC_145.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__TARGET_174.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__BWD_183.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__CC_188.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__TARGET_217.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_226.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CC_232.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__TARGET_261.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__BWD_270.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__CC_275.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__TARGET_304.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_313.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CC_319.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__TARGET_348.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__BWD_357.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__CC_362.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<PersonsSmartEMF.PersonRegister, PersonsSmartEMF.Person>(incUtil, (PersonsSmartEMF.PersonRegister) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "PersonsSmartEMF.PersonRegister_persons_Person"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Target(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, PersonsSmartEMF.Person>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (PersonsSmartEMF.Person) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherToMale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale, PersonsSmartEMF.Male>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Daughters(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__SOURCE_23.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_30.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CC_42.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__SOURCE_67.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_74.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CC_85.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_123.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_167.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_210.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_254.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_297.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_335.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_daughters_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, PersonsSmartEMF.Male>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families(), (notification) -> {
			incUtil.newMessage();
			createFamily__SOURCE_1.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			createFamily__FWD_4.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			createFamily__CC_7.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			createFamily__CONSISTENCY_10.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__SOURCE_23.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__FWD_30.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__BWD_36.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CC_42.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterToFemale__SOURCE_67.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterToFemale__FWD_74.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CC_85.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__SOURCE_126.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__FWD_133.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__BWD_139.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CC_145.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherToMale__SOURCE_170.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherToMale__FWD_177.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherToMale__CC_188.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__SOURCE_213.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__FWD_220.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__BWD_226.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CC_232.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherToFemale__SOURCE_257.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherToFemale__FWD_264.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherToFemale__CC_275.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__SOURCE_300.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_307.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__BWD_313.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CC_319.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonToMale__SOURCE_344.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_351.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonToMale__CC_362.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesSmartEMF.FamilyRegister, FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.FamilyRegister) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesSmartEMF.FamilyRegister_families_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__f(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesSmartEMF.Family>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.Family) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__f_Family"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CONTEXT__TARGET__persons(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, PersonsSmartEMF.PersonRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (PersonsSmartEMF.PersonRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CONTEXT__TARGET__persons_PersonRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, PersonsSmartEMF.Female>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Source(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale__CONSISTENCY_49.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale__CONSISTENCY_92.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale__CONSISTENCY_195.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale__CONSISTENCY_239.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonOfExistingFamilyToMale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale, PersonsSmartEMF.Male>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_CreateFamily_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			createFamily__CONSISTENCY_10.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, PersonsSmartEMF.Male>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (PersonsSmartEMF.Male) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CREATE__TARGET__p_Male"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_MotherToFemale_CREATE__TARGET__p(), (notification) -> {
			incUtil.newMessage();
			motherToFemale__CONSISTENCY_282.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale, PersonsSmartEMF.Female>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) notification.getNotifier(), (PersonsSmartEMF.Female) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale_CREATE__TARGET__p_Female"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__families(), (notification) -> {
			incUtil.newMessage();
			fatherOfExistingFamilyToMale__CONSISTENCY_152.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale, FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyRegister) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__families_FamilyRegister"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_Families2Persons_CREATE__CORRESPONDENCE__families2persons(), (notification) -> {
			incUtil.newMessage();
			families2Persons__CONSISTENCY_112.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons, FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) notification.getNotifier(), (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons_CREATE__CORRESPONDENCE__families2persons_RegisterToRegisterCorr"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_SonToMale_CREATE__SOURCE__fm(), (notification) -> {
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale_CREATE__SOURCE__fm_FamilyMember"), getSelf());
		});
		feature2removeEdgeConsumer.put(FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Sons(), (notification) -> {
			incUtil.newMessage();
			daughterOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_14.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			daughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_58.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherOfExistingFamilyToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_117.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			fatherToMale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_164.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherOfExistingFamilyToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_207.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			motherToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_251.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__SOURCE_300.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__FWD_307.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CC_319.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonOfExistingFamilyToMale__CONSISTENCY_326.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__SOURCE_344.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__FWD_351.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__CC_362.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
			incUtil.newMessage();
			sonToMale__CONSISTENCY_369.tell(new ReferenceDeleted<FamiliesSmartEMF.Family, FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.Family) notification.getNotifier(), (FamiliesSmartEMF.FamilyMember) notification.getOldValue(), "FamiliesSmartEMF.Family_sons_FamilyMember"), getSelf());
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
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP3.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP4.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP5.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP6.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP7.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP8.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP9.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP10.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyRegister) {
			incUtil.newMessage();
			familyRegister_object_SP11.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyRegister>(incUtil, (FamiliesSmartEMF.FamilyRegister) node), getSelf());
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
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP9.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP10.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP11.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP12.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP13.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP14.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.Family) {
			incUtil.newMessage();
			family_object_SP15.tell(new ObjectDeleted<FamiliesSmartEMF.Family>(incUtil, (FamiliesSmartEMF.Family) node), getSelf());
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
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP8.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP9.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP10.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP11.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP12.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof FamiliesSmartEMF.FamilyMember) {
			incUtil.newMessage();
			familyMember_object_SP13.tell(new ObjectDeleted<FamiliesSmartEMF.FamilyMember>(incUtil, (FamiliesSmartEMF.FamilyMember) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP0.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP1.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP2.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP3.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP4.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP5.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP6.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP7.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP8.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP9.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.PersonRegister) {
			incUtil.newMessage();
			personRegister_object_SP10.tell(new ObjectDeleted<PersonsSmartEMF.PersonRegister>(incUtil, (PersonsSmartEMF.PersonRegister) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Female) {
			incUtil.newMessage();
			female_object_SP0.tell(new ObjectDeleted<PersonsSmartEMF.Female>(incUtil, (PersonsSmartEMF.Female) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Female) {
			incUtil.newMessage();
			female_object_SP1.tell(new ObjectDeleted<PersonsSmartEMF.Female>(incUtil, (PersonsSmartEMF.Female) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Female) {
			incUtil.newMessage();
			female_object_SP2.tell(new ObjectDeleted<PersonsSmartEMF.Female>(incUtil, (PersonsSmartEMF.Female) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Female) {
			incUtil.newMessage();
			female_object_SP3.tell(new ObjectDeleted<PersonsSmartEMF.Female>(incUtil, (PersonsSmartEMF.Female) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Male) {
			incUtil.newMessage();
			male_object_SP0.tell(new ObjectDeleted<PersonsSmartEMF.Male>(incUtil, (PersonsSmartEMF.Male) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Male) {
			incUtil.newMessage();
			male_object_SP1.tell(new ObjectDeleted<PersonsSmartEMF.Male>(incUtil, (PersonsSmartEMF.Male) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Male) {
			incUtil.newMessage();
			male_object_SP2.tell(new ObjectDeleted<PersonsSmartEMF.Male>(incUtil, (PersonsSmartEMF.Male) node), getSelf());
		}
		if (node instanceof PersonsSmartEMF.Male) {
			incUtil.newMessage();
			male_object_SP3.tell(new ObjectDeleted<PersonsSmartEMF.Male>(incUtil, (PersonsSmartEMF.Male) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) {
			incUtil.newMessage();
			protocolNode_CreateFamily_object.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) {
			incUtil.newMessage();
			protocolNode_DaughterOfExistingFamilyToFemale_object.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) {
			incUtil.newMessage();
			protocolNode_DaughterToFemale_object.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) {
			incUtil.newMessage();
			protocolNode_Families2Persons_object.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) {
			incUtil.newMessage();
			protocolNode_FatherOfExistingFamilyToMale_object.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) {
			incUtil.newMessage();
			protocolNode_FatherToMale_object.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) {
			incUtil.newMessage();
			protocolNode_MotherOfExistingFamilyToFemale_object.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) {
			incUtil.newMessage();
			protocolNode_MotherToFemale_object.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) {
			incUtil.newMessage();
			protocolNode_SonOfExistingFamilyToMale_object.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) {
			incUtil.newMessage();
			protocolNode_SonToMale_object.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale>(incUtil, (FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			registerToRegisterCorr_object_SP0.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			registerToRegisterCorr_object_SP1.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			registerToRegisterCorr_object_SP2.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			registerToRegisterCorr_object_SP3.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			registerToRegisterCorr_object_SP4.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			registerToRegisterCorr_object_SP5.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			registerToRegisterCorr_object_SP6.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			registerToRegisterCorr_object_SP7.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) {
			incUtil.newMessage();
			registerToRegisterCorr_object_SP8.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr>(incUtil, (FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) {
			incUtil.newMessage();
			familyMemberToPersonCorr_object_SP0.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) node), getSelf());
		}
		if (node instanceof FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) {
			incUtil.newMessage();
			familyMemberToPersonCorr_object_SP1.tell(new ObjectDeleted<FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr>(incUtil, (FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) node), getSelf());
		}
	}
}

