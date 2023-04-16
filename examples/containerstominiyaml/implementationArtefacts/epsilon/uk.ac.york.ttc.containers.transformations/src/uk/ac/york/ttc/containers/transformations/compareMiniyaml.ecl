import 'mergeUtils.eol';

rule MatchMaps
  match l: Left!`Map`
  with  r: Right!`Map`
{
   compare: l.path() = r.path()
}

rule MatchMapEntriesByPath
  match l: Left!MapEntry
  with  r: Right!MapEntry {
  compare: l.path() = r.path()
}

rule MatchLists
  match l: Left!`List`
  with  r: Right!`List`
{
  compare: l.path() = r.path()
}

rule MatchScalars
  match l: Left!`Scalar`
  with r: Right!`Scalar`
{
  compare: l.path() = r.path() and l.value = r.value
}