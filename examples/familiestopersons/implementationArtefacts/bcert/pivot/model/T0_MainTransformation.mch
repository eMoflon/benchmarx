MACHINE   T0_MainTransformation

INCLUDES  MetaModel

PROMOTES
  ConcurrentDeleteMatched,
  ConcurrentMatchMemberPerson,
  ConcurrentTargetRenameWins,
  Member2Person,
  ForwardDelete,
  ForwardRename,
  Person2MemberExistingFamily,
  Person2MemberNewFamily,
  BackwardDelete,
  BackwardRenameMemberName,
  BackwardMoveMaleToExistingFamily,
  BackwardMoveFemaleToExistingFamily,
  BackwardMoveMaleToNewFamily,
  BackwardMoveFemaleToNewFamily,
  ForwardCreateMissingPerson

DEFINITIONS
  SET_PREF_MAX_OPERATIONS == 1

END