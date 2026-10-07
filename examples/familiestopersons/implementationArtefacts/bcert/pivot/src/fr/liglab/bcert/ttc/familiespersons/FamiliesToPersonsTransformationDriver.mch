MACHINE   FamiliesToPersonsTransformationDriver

INCLUDES  FamiliesToPersonsTransformation

DEFINITIONS
  
  "definitions.def";
  "match.def";
  "LibraryStrings.def";
  "BatchBwd.def";
  "concurrent.def"

SETS

  RULE = {
    ConcurrentDeleteMatched_,
    ConcurrentMatchMemberPerson_,
    ConcurrentTargetRenameWins_,

    Member2Person_,
    ForwardDelete_,
    ForwardRename_,
    ForwardRepairGender_,

    Person2MemberExistingFamily_,
    Person2MemberNewFamily_,
    BackwardDelete_,
    BackwardRenameMemberName_,
    BackwardMoveMaleToExistingFamily_,
    BackwardMoveFemaleToExistingFamily_,
    BackwardMoveMaleToNewFamily_,
    BackwardMoveFemaleToNewFamily_
  }

OPERATIONS

appliedRule <-- propagate_forward = 
    PRE strategie(pvtRoot) = FWD THEN
      CHOICE
      	  ForwardDelete || appliedRule := ForwardDelete_
      OR    
          ForwardRename || appliedRule := ForwardRename_
      OR    
      	  ForwardRepairGender || appliedRule := ForwardRepairGender_
      OR    
      	  Member2Person || appliedRule := Member2Person_
      END
    END;

appliedRule <-- propagate_backward = 
    PRE strategie(pvtRoot) = BWD THEN
      CHOICE
      	  BackwardDelete || appliedRule := BackwardDelete_
      OR
          BackwardRenameMemberName || appliedRule := BackwardRenameMemberName_
      OR    
          BackwardMoveMaleToExistingFamily || appliedRule := BackwardMoveMaleToExistingFamily_
      OR
          BackwardMoveFemaleToExistingFamily || appliedRule := BackwardMoveFemaleToExistingFamily_
      OR
          BackwardMoveMaleToNewFamily || appliedRule := BackwardMoveMaleToNewFamily_
      OR
          BackwardMoveFemaleToNewFamily || appliedRule := BackwardMoveFemaleToNewFamily_
      OR        
          Person2MemberExistingFamily || appliedRule := Person2MemberExistingFamily_
      OR
          Person2MemberNewFamily || appliedRule := Person2MemberNewFamily_
      END
   END;

/*
 * Priority-based rule selection for concurrent propagation.
 *
 * The strategy enforces a deterministic resolution of inconsistencies:
 *
 * 1. ConcurrentDeleteMatched:
 *    Cleans obsolete mappings when both source and target elements disappeared.
 *
 * 2. ConcurrentMatchMemberPerson:
 *    Reuses existing elements by creating missing mappings when names match.
 *
 * 3. ConcurrentTargetRenameWins:
 *    Resolves rename conflicts by privileging the target (Person) side
 *    and propagating the change backward.
 *
 * If no concurrent rule applies, the system falls back to standard
 * forward or backward transformations:
 *
 * - FWD: delete > rename > create (Member2Person)
 * - BWD: delete > rename > create (Person2Member*)
 *
 * The direction is explicitly set (SetFWD / SetBWD), and one applicable
 * rule is selected non-deterministically.
 *
 * This ordering guarantees that inconsistencies are resolved before
 * applying structural transformations.
 */
 
appliedRule <-- propagate_both =
  PRE strategie(pvtRoot) = BWD_FWD THEN

    SELECT HasConcurrentDeleteMatched THEN
      ConcurrentDeleteMatched || appliedRule := ConcurrentDeleteMatched_
    WHEN HasConcurrentMatchMemberPerson THEN  
      ConcurrentMatchMemberPerson || appliedRule := ConcurrentMatchMemberPerson_
    WHEN HasConcurrentTargetRenameWins THEN   
      BEGIN SetBWD ; ConcurrentTargetRenameWins ; SetBWD_FWD END || appliedRule := ConcurrentTargetRenameWins_
    ELSE
    CHOICE
      SetFWD;
      CHOICE
      	  ForwardDelete || appliedRule := ForwardDelete_
      OR    
          ForwardRename || appliedRule := ForwardRename_
      OR    
      	  ForwardRepairGender || appliedRule := ForwardRepairGender_
      OR    
      	  Member2Person || appliedRule := Member2Person_
      END;
      SetBWD_FWD
    OR
      SetBWD;
      CHOICE
      	  BackwardDelete || appliedRule := BackwardDelete_
      OR
          BackwardRenameMemberName || appliedRule := BackwardRenameMemberName_
      OR    
          BackwardMoveMaleToExistingFamily || appliedRule := BackwardMoveMaleToExistingFamily_
      OR
          BackwardMoveFemaleToExistingFamily || appliedRule := BackwardMoveFemaleToExistingFamily_
      OR
          BackwardMoveMaleToNewFamily || appliedRule := BackwardMoveMaleToNewFamily_
      OR
          BackwardMoveFemaleToNewFamily || appliedRule := BackwardMoveFemaleToNewFamily_
      OR        
          Person2MemberExistingFamily || appliedRule := Person2MemberExistingFamily_
      OR
          Person2MemberNewFamily || appliedRule := Person2MemberNewFamily_
      END;
      SetBWD_FWD
    END
  END
  END

END