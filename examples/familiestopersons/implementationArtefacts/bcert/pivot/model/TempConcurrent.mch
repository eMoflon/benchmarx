MACHINE TempConcurrent
INCLUDES MetaModel
DEFINITIONS
  SET_PREF_MAX_OPERATIONS == 100000 ;
  "definitions.def" ;
  "LibraryStrings.def" ;
  "match.def" ;
  "BatchBwd.def" ;
  "concurrent.def"
SETS
  RULE = {
    ConcurrentDeleteMatched_,
    ConcurrentMatchMemberPerson_,
    ConcurrentTargetRenameWins_,
    Member2Person_,
    ForwardDelete_,
    ForwardRename_,
    Person2MemberExistingFamily_,
    Person2MemberNewFamily_,
    BackwardDelete_,
    BackwardRenameMemberName_
  }
INITIALISATION
  Reset
OPERATIONS
setupModel = 
  PRE
    Family = {} & Person = {}
  THEN
    SetBWD ||
    SetUnSync ||
    MakeDecision(TRUE, TRUE) ||
    VAR aFamily IN
      aFamily <-- FamilyNEW("Skinner") ;
      aFamily <-- FamilyNEW("Flanders") ;
      AddSon(aFamily, "Rod") ;
      aFamily <-- FamilyNEW("Simpson") ;
      AddFather(aFamily, "Bart") ;
      aFamily <-- FamilyNEW("Simpson") ;
      AddMother(aFamily, "Marge") ;
      AddSon(aFamily, "Bart") ;
      AddSon(aFamily, "Bart") ;
      AddDaughter(aFamily, "Lisa") ;
      AddDaughter(aFamily, "Maggie")
    END ||
    BEGIN
      PersonNEW("Flanders, Rod", TRUE) ;
      SetBirthday("Flanders, Rod", "2013-10-01") ;
      PersonNEW("Simpson, Bart", TRUE) ;
      SetBirthday("Simpson, Bart", "2013-03-09") ;
      PersonNEW("Simpson, Homer", TRUE) ;
      SetBirthday("Simpson, Homer", "2013-01-09") ;
      PersonNEW("Simpson, Marge", FALSE) ;
      SetBirthday("Simpson, Marge", "2013-02-09") ;
      PersonNEW("Simpson, Bart", TRUE) ;
      SetBirthday("Simpson, Bart", "2013-03-10") ;
      PersonNEW("Simpson, Bart", TRUE) ;
      SetBirthday("Simpson, Bart", "2013-03-11") ;
      PersonNEW("Simpson, Marie", FALSE) ;
      SetBirthday("Simpson, Marie", "2013-03-08") ;
      PersonNEW("Simpson, Maggie", FALSE) ;
      SetBirthday("Simpson, Maggie", "2013-03-07")
    END
  END ;
appliedRule <-- propagate =
  SELECT  HasConcurrentDeleteMatched
  THEN    ConcurrentDeleteMatched || appliedRule := ConcurrentDeleteMatched_
  WHEN    HasConcurrentMatchMemberPerson
  THEN    ConcurrentMatchMemberPerson || appliedRule := ConcurrentMatchMemberPerson_
  WHEN    HasConcurrentTargetRenameWins
  THEN    SetBWD ; ConcurrentTargetRenameWins ; appliedRule := ConcurrentTargetRenameWins_
  ELSE
    CHOICE
      SetFWD ;
      SELECT  HasForwardDelete
      THEN    ForwardDelete || appliedRule := ForwardDelete_
      WHEN    HasForwardRename
      THEN    ForwardRename || appliedRule := ForwardRename_
      ELSE    Member2Person || appliedRule := Member2Person_
      END
    OR
      SetBWD ;
      SELECT  HasBackwardDelete
      THEN    BackwardDelete || appliedRule := BackwardDelete_
      WHEN    HasBackwardRename
      THEN    BackwardRenameMemberName || appliedRule := BackwardRenameMemberName_
      ELSE
        CHOICE
          Person2MemberExistingFamily || appliedRule := Person2MemberExistingFamily_
        OR
          Person2MemberNewFamily || appliedRule := Person2MemberNewFamily_
        END
      END
    END
  END;
fwd = BEGIN SetFWD END ;
bwd = BEGIN SetBWD END ;
sync = BEGIN SetSync END;
unsync = BEGIN SetUnSync END
END
