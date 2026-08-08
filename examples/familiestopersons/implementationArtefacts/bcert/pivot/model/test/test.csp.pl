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
'channel'('tesOP','type'('dotUnitType')).
'channel'('ok','type'('dotTupleType'(['boolType']))).
'channel'('nothing','type'('dotUnitType')).
'bindval'('MAIN','[]'('prefix'('src_span'(6,2,6,9,58,7),[],'dotTuple'(['ok','true']),'prefix'('src_span'(6,13,6,18,69,5),[],'tesOP','skip'('src_span'(6,22,6,26,78,4)),'src_span'(6,19,6,21,74,13)),'src_span'(6,10,6,12,65,24)),'prefix'('src_span'(8,2,8,10,88,8),[],'dotTuple'(['ok','false']),'prefix'('src_span'(8,14,8,21,100,7),[],'nothing','skip'('src_span'(8,25,8,29,111,4)),'src_span'(8,22,8,24,107,15)),'src_span'(8,11,8,13,96,27)),'src_span_operator'('no_loc_info_available','src_span'(7,2,7,4,84,2))),'src_span'(5,1,8,29,49,66)).
'symbol'('tesOP','tesOP','src_span'(1,9,1,14,8,5),'Channel').
'symbol'('ok','ok','src_span'(2,9,2,11,22,2),'Channel').
'symbol'('nothing','nothing','src_span'(3,9,3,16,40,7),'Channel').
'symbol'('MAIN','MAIN','src_span'(5,1,5,5,49,4),'Ident (Groundrep.)').