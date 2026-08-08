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
'channel'('setupModel','type'('dotUnitType')).
'channel'('propagate','type'('dotUnitType')).
'bindval'('MAIN','prefix'('src_span'(3,8,3,18,38,10),[],'setupModel','val_of'('TRANSFORM','src_span'(3,22,3,31,52,9)),'src_span'(3,19,3,21,48,23)),'src_span'(3,1,3,31,31,30)).
'bindval'('TRANSFORM','[]'('prefix'('src_span'(4,13,4,22,74,9),[],'propagate','val_of'('TRANSFORM','src_span'(4,26,4,35,87,9)),'src_span'(4,23,4,25,83,22)),'skip'('src_span'(4,39,4,43,100,4)),'src_span_operator'('no_loc_info_available','src_span'(4,36,4,38,97,2))),'src_span'(4,1,4,43,62,42)).
'symbol'('setupModel','setupModel','src_span'(1,9,1,19,8,10),'Channel').
'symbol'('propagate','propagate','src_span'(1,21,1,30,20,9),'Channel').
'symbol'('MAIN','MAIN','src_span'(3,1,3,5,31,4),'Ident (Groundrep.)').
'symbol'('TRANSFORM','TRANSFORM','src_span'(4,1,4,10,62,9),'Ident (Groundrep.)').