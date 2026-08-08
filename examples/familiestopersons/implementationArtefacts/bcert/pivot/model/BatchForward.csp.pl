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
'channel'('testInitialiseSynchronisation','type'('dotTupleType'(['boolType']))).
'channel'('testFamilyNameChangeOfEmpty','type'('dotTupleType'(['boolType']))).
'channel'('testCreateFamily','type'('dotTupleType'(['boolType']))).
'channel'('testCreateFamilyMember','type'('dotTupleType'(['boolType']))).
'channel'('testNewFamilyWithMultiMembers','type'('dotTupleType'(['boolType']))).
'channel'('testNewDuplicateFamilyNames','type'('dotTupleType'(['boolType']))).
'channel'('testDuplicateFamilyMemberNames','type'('dotTupleType'(['boolType']))).
'channel'('assertPrecondition','type'('dotTupleType'(['boolType']))).
'channel'('assertPostcondition','type'('dotTupleType'(['boolType']))).
'channel'('noPrecondition','type'('dotUnitType')).
'channel'('propagate','type'('dotUnitType')).
'channel'('createSimpsonFamily','type'('dotUnitType')).
'channel'('renameEmptySimpsonToBouvier','type'('dotUnitType')).
'channel'('createSkinnerFamily','type'('dotUnitType')).
'channel'('createFlandersFamilySonRod','type'('dotUnitType')).
'channel'('createNewFamilySimpsonWithMembers','type'('dotUnitType')).
'channel'('createFatherBart','type'('dotUnitType')).
'channel'('createSonBart','type'('dotUnitType')).
'bindval'('MAIN','[]'('[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(22,7,22,41,529,34),[],'dotTuple'(['testInitialiseSynchronisation','true']),'prefix'('src_span'(23,14,23,28,577,14),[],'noPrecondition','val_of'('TRANSFORM','src_span'(24,14,24,23,605,9)),'src_span'(23,29,24,13,591,37)),'src_span'(22,42,23,13,563,85)),';'('prefix'('src_span'(27,7,27,39,627,32),[],'dotTuple'(['testFamilyNameChangeOfEmpty','true']),'prefix'('src_span'(28,14,28,33,673,19),[],'createSimpsonFamily','val_of'('PRE','src_span'(29,14,29,17,706,3)),'src_span'(28,34,29,13,692,36)),'src_span'(27,40,28,13,659,82)),'prefix'('src_span'(29,20,29,47,712,27),[],'renameEmptySimpsonToBouvier','val_of'('TRANSFORM','src_span'(30,14,30,23,753,9)),'src_span'(29,48,30,13,739,50)),'src_span_operator'('no_loc_info_available','src_span'(29,18,29,19,710,1))),'src_span_operator'('no_loc_info_available','src_span'(26,3,26,5,618,2))),'prefix'('src_span'(33,7,33,28,775,21),[],'dotTuple'(['testCreateFamily','true']),'prefix'('src_span'(34,14,34,28,810,14),[],'noPrecondition','prefix'('src_span'(35,14,35,33,838,19),[],'createSkinnerFamily','val_of'('TRANSFORM','src_span'(36,14,36,23,871,9)),'src_span'(35,34,36,13,857,42)),'src_span'(34,29,35,13,824,70)),'src_span'(33,29,34,13,796,105)),'src_span_operator'('no_loc_info_available','src_span'(32,3,32,5,766,2))),'prefix'('src_span'(39,7,39,34,893,27),[],'dotTuple'(['testCreateFamilyMember','true']),'prefix'('src_span'(40,14,40,28,934,14),[],'noPrecondition','prefix'('src_span'(41,14,41,40,962,26),[],'createFlandersFamilySonRod','val_of'('TRANSFORM','src_span'(42,14,42,23,1002,9)),'src_span'(41,41,42,13,988,49)),'src_span'(40,29,41,13,948,77)),'src_span'(39,35,40,13,920,118)),'src_span_operator'('no_loc_info_available','src_span'(38,3,38,5,884,2))),'prefix'('src_span'(45,7,45,41,1024,34),[],'dotTuple'(['testNewFamilyWithMultiMembers','true']),'prefix'('src_span'(46,14,46,28,1072,14),[],'noPrecondition','prefix'('src_span'(47,14,47,40,1100,26),[],'createFlandersFamilySonRod','prefix'('src_span'(48,14,48,47,1140,33),[],'createNewFamilySimpsonWithMembers','val_of'('TRANSFORM','src_span'(49,14,49,23,1187,9)),'src_span'(48,48,49,13,1173,56)),'src_span'(47,41,48,13,1126,96)),'src_span'(46,29,47,13,1086,124)),'src_span'(45,42,46,13,1058,172)),'src_span_operator'('no_loc_info_available','src_span'(44,3,44,5,1015,2))),'prefix'('src_span'(52,7,52,39,1209,32),[],'dotTuple'(['testNewDuplicateFamilyNames','true']),'prefix'('src_span'(53,14,53,28,1255,14),[],'noPrecondition','prefix'('src_span'(54,14,54,47,1283,33),[],'createNewFamilySimpsonWithMembers','prefix'('src_span'(55,14,55,33,1330,19),[],'createSimpsonFamily','prefix'('src_span'(56,14,56,30,1363,16),[],'createFatherBart','val_of'('TRANSFORM','src_span'(57,14,57,23,1393,9)),'src_span'(56,31,57,13,1379,39)),'src_span'(55,34,56,13,1349,72)),'src_span'(54,48,55,13,1316,119)),'src_span'(53,29,54,13,1269,147)),'src_span'(52,40,53,13,1241,193)),'src_span_operator'('no_loc_info_available','src_span'(51,3,51,5,1200,2))),'prefix'('src_span'(60,7,60,42,1415,35),[],'dotTuple'(['testDuplicateFamilyMemberNames','true']),'prefix'('src_span'(61,14,61,28,1464,14),[],'noPrecondition','prefix'('src_span'(62,14,62,47,1492,33),[],'createNewFamilySimpsonWithMembers','prefix'('src_span'(63,14,63,27,1539,13),[],'createSonBart','val_of'('TRANSFORM','src_span'(64,14,64,23,1566,9)),'src_span'(63,28,64,13,1552,36)),'src_span'(62,48,63,13,1525,83)),'src_span'(61,29,62,13,1478,111)),'src_span'(60,43,61,13,1450,160)),'src_span_operator'('no_loc_info_available','src_span'(59,3,59,5,1406,2))),'src_span'(21,1,64,23,516,1059)).
'bindval'('TRANSFORM','[]'('prefix'('src_span'(68,7,68,16,1596,9),[],'propagate','val_of'('TRANSFORM','src_span'(68,20,68,29,1609,9)),'src_span'(68,17,68,19,1605,22)),'val_of'('POST','src_span'(70,7,70,11,1630,4)),'src_span_operator'('no_loc_info_available','src_span'(69,3,69,5,1621,2))),'src_span'(67,1,70,11,1578,56)).
'bindval'('PRE','[]'('prefix'('src_span'(74,7,74,30,1649,23),[],'dotTuple'(['assertPrecondition','true']),'skip'('src_span'(74,35,74,39,1677,4)),'src_span'(74,31,74,34,1672,32)),'prefix'('src_span'(76,7,76,31,1693,24),[],'dotTuple'(['assertPrecondition','false']),'stop'('src_span'(76,35,76,39,1721,4)),'src_span'(76,32,76,34,1717,32)),'src_span_operator'('no_loc_info_available','src_span'(75,3,75,5,1684,2))),'src_span'(73,1,76,39,1637,88)).
'bindval'('POST','[]'('prefix'('src_span'(80,7,80,31,1741,24),[],'dotTuple'(['assertPostcondition','true']),'stop'('src_span'(80,36,80,40,1770,4)),'src_span'(80,32,80,35,1765,33)),'prefix'('src_span'(82,7,82,32,1786,25),[],'dotTuple'(['assertPostcondition','false']),'stop'('src_span'(82,36,82,40,1815,4)),'src_span'(82,33,82,35,1811,33)),'src_span_operator'('no_loc_info_available','src_span'(81,3,81,5,1777,2))),'src_span'(79,1,82,40,1728,91)).
'symbol'('testInitialiseSynchronisation','testInitialiseSynchronisation','src_span'(1,9,1,38,8,29),'Channel').
'symbol'('testFamilyNameChangeOfEmpty','testFamilyNameChangeOfEmpty','src_span'(2,5,2,32,43,27),'Channel').
'symbol'('testCreateFamily','testCreateFamily','src_span'(3,5,3,21,76,16),'Channel').
'symbol'('testCreateFamilyMember','testCreateFamilyMember','src_span'(4,5,4,27,98,22),'Channel').
'symbol'('testNewFamilyWithMultiMembers','testNewFamilyWithMultiMembers','src_span'(5,5,5,34,126,29),'Channel').
'symbol'('testNewDuplicateFamilyNames','testNewDuplicateFamilyNames','src_span'(6,5,6,32,161,27),'Channel').
'symbol'('testDuplicateFamilyMemberNames','testDuplicateFamilyMemberNames','src_span'(7,5,7,35,194,30),'Channel').
'symbol'('assertPrecondition','assertPrecondition','src_span'(8,5,8,23,230,18),'Channel').
'symbol'('assertPostcondition','assertPostcondition','src_span'(9,5,9,24,254,19),'Channel').
'symbol'('noPrecondition','noPrecondition','src_span'(11,9,11,23,290,14),'Channel').
'symbol'('propagate','propagate','src_span'(12,5,12,14,310,9),'Channel').
'symbol'('createSimpsonFamily','createSimpsonFamily','src_span'(13,5,13,24,325,19),'Channel').
'symbol'('renameEmptySimpsonToBouvier','renameEmptySimpsonToBouvier','src_span'(14,5,14,32,350,27),'Channel').
'symbol'('createSkinnerFamily','createSkinnerFamily','src_span'(15,5,15,24,383,19),'Channel').
'symbol'('createFlandersFamilySonRod','createFlandersFamilySonRod','src_span'(16,5,16,31,408,26),'Channel').
'symbol'('createNewFamilySimpsonWithMembers','createNewFamilySimpsonWithMembers','src_span'(17,5,17,38,440,33),'Channel').
'symbol'('createFatherBart','createFatherBart','src_span'(18,5,18,21,479,16),'Channel').
'symbol'('createSonBart','createSonBart','src_span'(19,5,19,18,501,13),'Channel').
'symbol'('MAIN','MAIN','src_span'(21,1,21,5,516,4),'Ident (Groundrep.)').
'symbol'('TRANSFORM','TRANSFORM','src_span'(67,1,67,10,1578,9),'Ident (Groundrep.)').
'symbol'('PRE','PRE','src_span'(73,1,73,4,1637,3),'Ident (Groundrep.)').
'symbol'('POST','POST','src_span'(79,1,79,5,1728,4),'Ident (Groundrep.)').