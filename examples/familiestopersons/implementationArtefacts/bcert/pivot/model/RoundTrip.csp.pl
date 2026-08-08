:- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5.
:- dynamic module/4.
'parserVersionStr'('0.6.2.1').
'parseResult'('ok','',0,0,0).
:- dynamic channel/2, bindval/3, agent/3.
:- dynamic agent_curry/3, symbol/4.
:- dynamic dataTypeDef/2, subTypeDef/2, nameType/2.
:- dynamic cspTransparent/1.
:- dynamic cspPrint/1.
:- dynamic pragma/1.
:- dynamic comment/2.
:- dynamic assertBool/1, assertRef/5, assertTauPrio/6.
:- dynamic assertModelCheckExt/4, assertModelCheck/3.
:- dynamic assertLtl/4, assertCtl/4.
'parserVersionNum'([0,11,1,1]).
'parserVersionStr'('CSPM-Frontent-0.11.1.1').
'dataTypeDef'('RULE',['constructor'('ConcurrentDeleteMatched_'),'constructor'('ConcurrentMatchMemberPerson_'),'constructor'('ConcurrentTargetRenameWins_'),'constructor'('Member2Person_'),'constructor'('ForwardDelete_'),'constructor'('ForwardRename_'),'constructor'('Person2MemberExistingFamily_'),'constructor'('Person2MemberNewFamily_'),'constructor'('BackwardDelete_'),'constructor'('BackwardRenameMemberName_'),'constructor'('BackwardMoveMaleToExistingFamily_'),'constructor'('BackwardMoveFemaleToExistingFamily_'),'constructor'('BackwardMoveMaleToNewFamily_'),'constructor'('BackwardMoveFemaleToNewFamily_')]).
'channel'('testRoundtripEdit','type'('dotTupleType'(['boolType']))).
'channel'('testRoundtripAdd','type'('dotTupleType'(['boolType']))).
'channel'('testRoundtripDelete','type'('dotTupleType'(['boolType']))).
'channel'('assertPrecondition','type'('dotTupleType'(['boolType']))).
'channel'('assertPostcondition','type'('dotTupleType'(['boolType']))).
'channel'('sync','type'('dotUnitType')).
'channel'('unsync','type'('dotUnitType')).
'channel'('fwd','type'('dotUnitType')).
'channel'('bwd','type'('dotUnitType')).
'channel'('makeDecisionENotP','type'('dotUnitType')).
'channel'('createInitialFamilies','type'('dotUnitType')).
'channel'('createNewFamilySimpsonWithMembers','type'('dotUnitType')).
'channel'('createSonBartInCompleteSimpson','type'('dotUnitType')).
'channel'('setBirthdayOfRod','type'('dotUnitType')).
'channel'('setBirthdayOfFatherBart','type'('dotUnitType')).
'channel'('setBirthdayOfYoungerBart','type'('dotUnitType')).
'channel'('changeAllBirthdays','type'('dotUnitType')).
'channel'('firstNameChangeOfHomer','type'('dotUnitType')).
'channel'('renameFlandersFamilyToBouvier','type'('dotUnitType')).
'channel'('createSeymour','type'('dotUnitType')).
'channel'('createSonTodd','type'('dotUnitType')).
'channel'('deleteMarge','type'('dotUnitType')).
'channel'('deleteRodAsSon','type'('dotUnitType')).
'channel'('propagate','type'('dotTupleType'(['RULE']))).
'bindval'('MAIN','[]'('[]'(';'(';'(';'(';'(';'('prefix'('src_span'(45,9,45,31,1092,22),[],'dotTuple'(['testRoundtripEdit','true']),'prefix'('src_span'(46,16,46,37,1130,21),[],'createInitialFamilies','prefix'('src_span'(46,41,46,47,1155,6),[],'unsync','prefix'('src_span'(47,16,47,19,1177,3),[],'fwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(47,23,47,43,1184,20)),'src_span'(47,20,47,22,1180,27)),'src_span'(46,48,47,15,1161,49)),'src_span'(46,38,46,40,1151,74)),'src_span'(45,32,46,15,1114,112)),'prefix'('src_span'(48,13,48,29,1219,16),[],'setBirthdayOfRod','prefix'('src_span'(49,16,49,39,1251,23),[],'setBirthdayOfFatherBart','prefix'('src_span'(50,16,50,49,1290,33),[],'createNewFamilySimpsonWithMembers','prefix'('src_span'(50,53,50,59,1327,6),[],'unsync','prefix'('src_span'(51,16,51,19,1349,3),[],'fwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(51,23,51,43,1356,20)),'src_span'(51,20,51,22,1352,27)),'src_span'(50,60,51,15,1333,49)),'src_span'(50,50,50,52,1323,86)),'src_span'(49,40,50,15,1274,125)),'src_span'(48,30,49,15,1235,157)),'src_span_operator'('no_loc_info_available','src_span'(47,44,47,45,1205,1))),'prefix'('src_span'(52,13,52,31,1391,18),[],'changeAllBirthdays','prefix'('src_span'(53,16,53,46,1425,30),[],'createSonBartInCompleteSimpson','prefix'('src_span'(53,50,53,56,1459,6),[],'unsync','prefix'('src_span'(54,16,54,19,1481,3),[],'fwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(54,23,54,43,1488,20)),'src_span'(54,20,54,22,1484,27)),'src_span'(53,57,54,15,1465,49)),'src_span'(53,47,53,49,1455,83)),'src_span'(52,32,53,15,1409,117)),'src_span_operator'('no_loc_info_available','src_span'(51,44,51,45,1377,1))),'prefix'('src_span'(55,13,55,37,1523,24),[],'setBirthdayOfYoungerBart','val_of'('PRE','src_span'(56,16,56,19,1563,3)),'src_span'(55,38,56,15,1547,43)),'src_span_operator'('no_loc_info_available','src_span'(54,44,54,45,1509,1))),'prefix'('src_span'(57,13,57,35,1581,22),[],'firstNameChangeOfHomer','prefix'('src_span'(57,39,57,45,1607,6),[],'unsync','prefix'('src_span'(58,16,58,19,1629,3),[],'bwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(58,23,58,43,1636,20)),'src_span'(58,20,58,22,1632,27)),'src_span'(57,46,58,15,1613,49)),'src_span'(57,36,57,38,1603,75)),'src_span_operator'('no_loc_info_available','src_span'(56,20,56,21,1567,1))),'prefix'('src_span'(59,13,59,42,1671,29),[],'renameFlandersFamilyToBouvier','prefix'('src_span'(59,46,59,52,1704,6),[],'unsync','prefix'('src_span'(60,16,60,19,1726,3),[],'fwd','val_of'('PROPAGATE_UNTIL_STOP','src_span'(60,23,60,43,1733,20)),'src_span'(60,20,60,22,1729,27)),'src_span'(59,53,60,15,1710,49)),'src_span'(59,43,59,45,1700,82)),'src_span_operator'('no_loc_info_available','src_span'(58,44,58,45,1657,1))),';'(';'(';'(';'(';'('prefix'('src_span'(63,9,63,30,1770,21),[],'dotTuple'(['testRoundtripAdd','true']),'prefix'('src_span'(64,16,64,33,1807,17),[],'makeDecisionENotP','prefix'('src_span'(65,16,65,37,1840,21),[],'createInitialFamilies','prefix'('src_span'(65,41,65,47,1865,6),[],'unsync','prefix'('src_span'(66,16,66,19,1887,3),[],'fwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(66,23,66,43,1894,20)),'src_span'(66,20,66,22,1890,27)),'src_span'(65,48,66,15,1871,49)),'src_span'(65,38,65,40,1861,74)),'src_span'(64,34,65,15,1824,107)),'src_span'(63,31,64,15,1791,144)),'prefix'('src_span'(67,13,67,29,1929,16),[],'setBirthdayOfRod','prefix'('src_span'(68,16,68,39,1961,23),[],'setBirthdayOfFatherBart','prefix'('src_span'(69,16,69,49,2000,33),[],'createNewFamilySimpsonWithMembers','prefix'('src_span'(69,53,69,59,2037,6),[],'unsync','prefix'('src_span'(70,16,70,19,2059,3),[],'fwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(70,23,70,43,2066,20)),'src_span'(70,20,70,22,2062,27)),'src_span'(69,60,70,15,2043,49)),'src_span'(69,50,69,52,2033,86)),'src_span'(68,40,69,15,1984,125)),'src_span'(67,30,68,15,1945,157)),'src_span_operator'('no_loc_info_available','src_span'(66,44,66,45,1915,1))),'prefix'('src_span'(71,13,71,31,2101,18),[],'changeAllBirthdays','prefix'('src_span'(72,16,72,46,2135,30),[],'createSonBartInCompleteSimpson','prefix'('src_span'(72,50,72,56,2169,6),[],'unsync','prefix'('src_span'(73,16,73,19,2191,3),[],'fwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(73,23,73,43,2198,20)),'src_span'(73,20,73,22,2194,27)),'src_span'(72,57,73,15,2175,49)),'src_span'(72,47,72,49,2165,83)),'src_span'(71,32,72,15,2119,117)),'src_span_operator'('no_loc_info_available','src_span'(70,44,70,45,2087,1))),'prefix'('src_span'(74,13,74,37,2233,24),[],'setBirthdayOfYoungerBart','val_of'('PRE','src_span'(75,16,75,19,2273,3)),'src_span'(74,38,75,15,2257,43)),'src_span_operator'('no_loc_info_available','src_span'(73,44,73,45,2219,1))),'prefix'('src_span'(76,13,76,26,2291,13),[],'createSeymour','prefix'('src_span'(76,30,76,36,2308,6),[],'unsync','prefix'('src_span'(77,16,77,19,2330,3),[],'bwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(77,23,77,43,2337,20)),'src_span'(77,20,77,22,2333,27)),'src_span'(76,37,77,15,2314,49)),'src_span'(76,27,76,29,2304,66)),'src_span_operator'('no_loc_info_available','src_span'(75,20,75,21,2277,1))),'prefix'('src_span'(78,13,78,26,2372,13),[],'createSonTodd','prefix'('src_span'(78,30,78,36,2389,6),[],'unsync','prefix'('src_span'(79,16,79,19,2411,3),[],'fwd','val_of'('PROPAGATE_UNTIL_STOP','src_span'(79,23,79,43,2418,20)),'src_span'(79,20,79,22,2414,27)),'src_span'(78,37,79,15,2395,49)),'src_span'(78,27,78,29,2385,66)),'src_span_operator'('no_loc_info_available','src_span'(77,44,77,45,2358,1))),'src_span_operator'('no_loc_info_available','src_span'(62,5,62,7,1759,2))),';'(';'(';'(';'(';'('prefix'('src_span'(82,9,82,33,2455,24),[],'dotTuple'(['testRoundtripDelete','true']),'prefix'('src_span'(83,16,83,37,2495,21),[],'createInitialFamilies','prefix'('src_span'(83,41,83,47,2520,6),[],'unsync','prefix'('src_span'(84,16,84,19,2542,3),[],'fwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(84,23,84,43,2549,20)),'src_span'(84,20,84,22,2545,27)),'src_span'(83,48,84,15,2526,49)),'src_span'(83,38,83,40,2516,74)),'src_span'(82,34,83,15,2479,114)),'prefix'('src_span'(85,13,85,29,2584,16),[],'setBirthdayOfRod','prefix'('src_span'(86,16,86,39,2616,23),[],'setBirthdayOfFatherBart','prefix'('src_span'(87,16,87,49,2655,33),[],'createNewFamilySimpsonWithMembers','prefix'('src_span'(87,53,87,59,2692,6),[],'unsync','prefix'('src_span'(88,16,88,19,2714,3),[],'fwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(88,23,88,43,2721,20)),'src_span'(88,20,88,22,2717,27)),'src_span'(87,60,88,15,2698,49)),'src_span'(87,50,87,52,2688,86)),'src_span'(86,40,87,15,2639,125)),'src_span'(85,30,86,15,2600,157)),'src_span_operator'('no_loc_info_available','src_span'(84,44,84,45,2570,1))),'prefix'('src_span'(89,13,89,31,2756,18),[],'changeAllBirthdays','prefix'('src_span'(90,16,90,46,2790,30),[],'createSonBartInCompleteSimpson','prefix'('src_span'(90,50,90,56,2824,6),[],'unsync','prefix'('src_span'(91,16,91,19,2846,3),[],'fwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(91,23,91,43,2853,20)),'src_span'(91,20,91,22,2849,27)),'src_span'(90,57,91,15,2830,49)),'src_span'(90,47,90,49,2820,83)),'src_span'(89,32,90,15,2774,117)),'src_span_operator'('no_loc_info_available','src_span'(88,44,88,45,2742,1))),'prefix'('src_span'(92,13,92,37,2888,24),[],'setBirthdayOfYoungerBart','val_of'('PRE','src_span'(93,16,93,19,2928,3)),'src_span'(92,38,93,15,2912,43)),'src_span_operator'('no_loc_info_available','src_span'(91,44,91,45,2874,1))),'prefix'('src_span'(94,13,94,24,2946,11),[],'deleteMarge','prefix'('src_span'(94,28,94,34,2961,6),[],'unsync','prefix'('src_span'(95,16,95,19,2983,3),[],'bwd','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(95,23,95,43,2990,20)),'src_span'(95,20,95,22,2986,27)),'src_span'(94,35,95,15,2967,49)),'src_span'(94,25,94,27,2957,64)),'src_span_operator'('no_loc_info_available','src_span'(93,20,93,21,2932,1))),'prefix'('src_span'(96,13,96,27,3025,14),[],'deleteRodAsSon','prefix'('src_span'(96,31,96,37,3043,6),[],'unsync','prefix'('src_span'(97,16,97,19,3065,3),[],'fwd','val_of'('PROPAGATE_UNTIL_STOP','src_span'(97,23,97,43,3072,20)),'src_span'(97,20,97,22,3068,27)),'src_span'(96,38,97,15,3049,49)),'src_span'(96,28,96,30,3039,67)),'src_span_operator'('no_loc_info_available','src_span'(95,44,95,45,3011,1))),'src_span_operator'('no_loc_info_available','src_span'(81,5,81,7,2444,2))),'src_span'(44,1,97,43,1077,2015)).
'bindval'('PRE','[]'('prefix'('src_span'(100,9,100,32,3108,23),[],'dotTuple'(['assertPrecondition','true']),'skip'('src_span'(100,36,100,40,3135,4)),'src_span'(100,33,100,35,3131,31)),'prefix'('src_span'(102,9,102,33,3155,24),[],'dotTuple'(['assertPrecondition','false']),'stop'('src_span'(102,37,102,41,3183,4)),'src_span'(102,34,102,36,3179,32)),'src_span_operator'('no_loc_info_available','src_span'(101,5,101,7,3144,2))),'src_span'(99,1,102,41,3094,93)).
'bindval'('POST','[]'('prefix'('src_span'(105,9,105,33,3204,24),[],'dotTuple'(['assertPostcondition','true']),'stop'('src_span'(105,37,105,41,3232,4)),'src_span'(105,34,105,36,3228,32)),'prefix'('src_span'(107,9,107,34,3252,25),[],'dotTuple'(['assertPostcondition','false']),'stop'('src_span'(107,38,107,42,3281,4)),'src_span'(107,35,107,37,3277,33)),'src_span_operator'('no_loc_info_available','src_span'(106,5,106,7,3241,2))),'src_span'(104,1,107,42,3189,96)).
'bindval'('PROPAGATE_UNTIL_SYNC','repChoice'(['comprehensionGenerator'(_d,'RULE')],'[]'('prefix'('src_span'(110,19,110,28,3328,9),['out'(_d)],'propagate','val_of'('PROPAGATE_UNTIL_SYNC','src_span'(110,34,110,54,3343,20)),'src_span'(110,31,110,33,3339,26)),'prefix'('src_span'(112,5,112,9,3373,4),[],'sync','skip'('src_span'(112,13,112,17,3381,4)),'src_span'(112,10,112,12,3377,12)),'src_span_operator'('no_loc_info_available','src_span'(111,3,111,5,3366,2))),'src_span'(110,8,110,18,3317,10)),'src_span'(109,1,112,17,3287,98)).
'bindval'('PROPAGATE_UNTIL_STOP','repChoice'(['comprehensionGenerator'(_d2,'RULE')],'[]'('prefix'('src_span'(115,19,115,28,3428,9),['out'(_d2)],'propagate','val_of'('PROPAGATE_UNTIL_STOP','src_span'(115,34,115,54,3443,20)),'src_span'(115,31,115,33,3439,26)),'prefix'('src_span'(117,5,117,9,3473,4),[],'sync','val_of'('POST','src_span'(117,13,117,17,3481,4)),'src_span'(117,10,117,12,3477,12)),'src_span_operator'('no_loc_info_available','src_span'(116,3,116,5,3466,2))),'src_span'(115,8,115,18,3417,10)),'src_span'(114,1,117,17,3387,98)).
'symbol'('RULE','RULE','src_span'(1,10,1,14,9,4),'Datatype').
'symbol'('ConcurrentDeleteMatched_','ConcurrentDeleteMatched_','src_span'(2,5,2,29,20,24),'Constructor of Datatype').
'symbol'('ConcurrentMatchMemberPerson_','ConcurrentMatchMemberPerson_','src_span'(3,5,3,33,49,28),'Constructor of Datatype').
'symbol'('ConcurrentTargetRenameWins_','ConcurrentTargetRenameWins_','src_span'(4,5,4,32,82,27),'Constructor of Datatype').
'symbol'('Member2Person_','Member2Person_','src_span'(5,5,5,19,114,14),'Constructor of Datatype').
'symbol'('ForwardDelete_','ForwardDelete_','src_span'(6,5,6,19,133,14),'Constructor of Datatype').
'symbol'('ForwardRename_','ForwardRename_','src_span'(7,5,7,19,152,14),'Constructor of Datatype').
'symbol'('Person2MemberExistingFamily_','Person2MemberExistingFamily_','src_span'(8,5,8,33,171,28),'Constructor of Datatype').
'symbol'('Person2MemberNewFamily_','Person2MemberNewFamily_','src_span'(9,5,9,28,204,23),'Constructor of Datatype').
'symbol'('BackwardDelete_','BackwardDelete_','src_span'(10,5,10,20,232,15),'Constructor of Datatype').
'symbol'('BackwardRenameMemberName_','BackwardRenameMemberName_','src_span'(11,5,11,30,252,25),'Constructor of Datatype').
'symbol'('BackwardMoveMaleToExistingFamily_','BackwardMoveMaleToExistingFamily_','src_span'(12,5,12,38,282,33),'Constructor of Datatype').
'symbol'('BackwardMoveFemaleToExistingFamily_','BackwardMoveFemaleToExistingFamily_','src_span'(13,5,13,40,320,35),'Constructor of Datatype').
'symbol'('BackwardMoveMaleToNewFamily_','BackwardMoveMaleToNewFamily_','src_span'(14,5,14,33,360,28),'Constructor of Datatype').
'symbol'('BackwardMoveFemaleToNewFamily_','BackwardMoveFemaleToNewFamily_','src_span'(15,5,15,35,393,30),'Constructor of Datatype').
'symbol'('testRoundtripEdit','testRoundtripEdit','src_span'(17,9,17,26,433,17),'Channel').
'symbol'('testRoundtripAdd','testRoundtripAdd','src_span'(18,9,18,25,460,16),'Channel').
'symbol'('testRoundtripDelete','testRoundtripDelete','src_span'(19,9,19,28,486,19),'Channel').
'symbol'('assertPrecondition','assertPrecondition','src_span'(20,9,20,27,515,18),'Channel').
'symbol'('assertPostcondition','assertPostcondition','src_span'(21,9,21,28,543,19),'Channel').
'symbol'('sync','sync','src_span'(23,9,23,13,579,4),'Channel').
'symbol'('unsync','unsync','src_span'(24,9,24,15,593,6),'Channel').
'symbol'('fwd','fwd','src_span'(25,9,25,12,609,3),'Channel').
'symbol'('bwd','bwd','src_span'(26,9,26,12,622,3),'Channel').
'symbol'('makeDecisionENotP','makeDecisionENotP','src_span'(27,9,27,26,635,17),'Channel').
'symbol'('createInitialFamilies','createInitialFamilies','src_span'(28,9,28,30,662,21),'Channel').
'symbol'('createNewFamilySimpsonWithMembers','createNewFamilySimpsonWithMembers','src_span'(29,9,29,42,693,33),'Channel').
'symbol'('createSonBartInCompleteSimpson','createSonBartInCompleteSimpson','src_span'(30,9,30,39,736,30),'Channel').
'symbol'('setBirthdayOfRod','setBirthdayOfRod','src_span'(31,9,31,25,776,16),'Channel').
'symbol'('setBirthdayOfFatherBart','setBirthdayOfFatherBart','src_span'(32,9,32,32,802,23),'Channel').
'symbol'('setBirthdayOfYoungerBart','setBirthdayOfYoungerBart','src_span'(33,9,33,33,835,24),'Channel').
'symbol'('changeAllBirthdays','changeAllBirthdays','src_span'(34,9,34,27,869,18),'Channel').
'symbol'('firstNameChangeOfHomer','firstNameChangeOfHomer','src_span'(35,9,35,31,897,22),'Channel').
'symbol'('renameFlandersFamilyToBouvier','renameFlandersFamilyToBouvier','src_span'(36,9,36,38,929,29),'Channel').
'symbol'('createSeymour','createSeymour','src_span'(37,9,37,22,968,13),'Channel').
'symbol'('createSonTodd','createSonTodd','src_span'(38,9,38,22,991,13),'Channel').
'symbol'('deleteMarge','deleteMarge','src_span'(39,9,39,20,1014,11),'Channel').
'symbol'('deleteRodAsSon','deleteRodAsSon','src_span'(40,9,40,23,1035,14),'Channel').
'symbol'('propagate','propagate','src_span'(42,9,42,18,1059,9),'Channel').
'symbol'('MAIN','MAIN','src_span'(44,1,44,5,1077,4),'Ident (Groundrep.)').
'symbol'('PRE','PRE','src_span'(99,1,99,4,3094,3),'Ident (Groundrep.)').
'symbol'('POST','POST','src_span'(104,1,104,5,3189,4),'Ident (Groundrep.)').
'symbol'('PROPAGATE_UNTIL_SYNC','PROPAGATE_UNTIL_SYNC','src_span'(109,1,109,21,3287,20),'Ident (Groundrep.)').
'symbol'('d','d','src_span'(110,8,110,9,3317,1),'Ident (Prolog Variable)').
'symbol'('PROPAGATE_UNTIL_STOP','PROPAGATE_UNTIL_STOP','src_span'(114,1,114,21,3387,20),'Ident (Groundrep.)').
'symbol'('d2','d','src_span'(115,8,115,9,3417,1),'Ident (Prolog Variable)').