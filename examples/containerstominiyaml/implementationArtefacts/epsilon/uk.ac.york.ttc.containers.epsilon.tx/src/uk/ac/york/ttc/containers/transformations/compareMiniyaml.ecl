import 'mergeUtils.eol';

rule MatchMaps
  match l: Left!`Map`
  with  r: Right!`Map`
{
   guard: l.path() = r.path()
   compare: true
}

rule MatchMapEntriesByPath
  match l: Left!MapEntry
  with  r: Right!MapEntry {
  guard: l.path() = r.path()
  compare: true
}

rule MatchLists
  match l: Left!`List`
  with  r: Right!`List`
{
  guard: l.path() = r.path()
  compare: true
}

rule MatchScalars
  match l: Left!`Scalar`
  with r: Right!`Scalar`
{
  guard: l.path() = r.path() and l.value = r.value
  compare: true
}