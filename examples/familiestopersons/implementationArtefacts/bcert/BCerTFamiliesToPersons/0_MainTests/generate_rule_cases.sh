#!/bin/bash

set -e

OUT_DIR="."

write_persons_empty () {
  local file="$1"
  cat > "$OUT_DIR/$file" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">
</Persons:PersonRegister>
XML
}

write_family_empty () {
  local file="$1"
  cat > "$OUT_DIR/$file" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/resource/Families/model/Families.ecore">
</Families:FamilyRegister>
XML
}

write_family_father () {
  local file="$1"
  cat > "$OUT_DIR/$file" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
  <families name="Simpson">
    <father name="Homer"/>
  </families>
</Families:FamilyRegister>
XML
}

write_family_son () {
  local file="$1"
  cat > "$OUT_DIR/$file" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
  <families name="Simpson">
    <sons name="Bart"/>
  </families>
</Families:FamilyRegister>
XML
}

write_family_daughter () {
  local file="$1"
  cat > "$OUT_DIR/$file" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
  <families name="Simpson">
    <daughters name="Lisa"/>
  </families>
</Families:FamilyRegister>
XML
}

write_family_complete () {
  local file="$1"
  cat > "$OUT_DIR/$file" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
  <families name="Simpson">
    <father name="Homer"/>
    <mother name="Marge"/>
    <sons name="Bart"/>
    <daughters name="Lisa"/>
  </families>
</Families:FamilyRegister>
XML
}

write_pivot_header () {
  local file="$1"
  local name="$2"
  local fam="$3"
  local per="$4"
  local strategy="$5"

  cat > "$OUT_DIR/$file" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<pivot:Pivot xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xmlns:pivot="http://pivot"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore http://pivot ../pivot/model/pivot.ecore"
    name="$name"
    strategie="$strategy"
    FAMILY_TO_NEW="true"
    PARENT_TO_CHILD="true"
    sync="false">
  <familyModel href="$fam#/"/>
  <personModel href="$per#/"/>
XML
}

write_pivot_footer () {
  local file="$1"
  echo '</pivot:Pivot>' >> "$OUT_DIR/$file"
}

# 0. Empty
write_family_empty "0_emptyFam.xmi"
write_persons_empty "0_emptyPer.xmi"
write_pivot_header "0_empty.pivot" "0_empty" "0_emptyFam.xmi" "0_emptyPer.xmi" "FWD"
write_pivot_footer "0_empty.pivot"

# 1. Member2Person
write_family_complete "Member2PersonFam.xmi"
write_persons_empty "Member2PersonPer.xmi"
write_pivot_header "Member2Person.pivot" "Member2Person" "Member2PersonFam.xmi" "Member2PersonPer.xmi" "FWD"
write_pivot_footer "Member2Person.pivot"

# 2. ForwardRename
write_family_father "ForwardRenameFam.xmi"
cat > "$OUT_DIR/ForwardRenamePer.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">
  <persons xsi:type="Persons:Male" name="Simpson, OldHomer" birthday="DEFAULT"/>
</Persons:PersonRegister>
XML
write_pivot_header "ForwardRename.pivot" "ForwardRename" "ForwardRenameFam.xmi" "ForwardRenamePer.xmi" "FWD"
cat >> "$OUT_DIR/ForwardRename.pivot" <<XML
  <mapping>
    <mMap href="ForwardRenameFam.xmi#//@families.0/@father"/>
    <pMap xsi:type="Persons:Male" href="ForwardRenamePer.xmi#//@persons.0"/>
  </mapping>
XML
write_pivot_footer "ForwardRename.pivot"

# 3. ForwardDelete
cat > "$OUT_DIR/ForwardDeleteFam.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
  <families name="Simpson"/>
</Families:FamilyRegister>
XML
cat > "$OUT_DIR/ForwardDeletePer.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">
  <persons xsi:type="Persons:Male" name="Simpson, Homer" birthday="DEFAULT"/>
</Persons:PersonRegister>
XML
write_pivot_header "ForwardDelete.pivot" "ForwardDelete" "ForwardDeleteFam.xmi" "ForwardDeletePer.xmi" "FWD"
cat >> "$OUT_DIR/ForwardDelete.pivot" <<XML
  <mapping>
    <pMap xsi:type="Persons:Male" href="ForwardDeletePer.xmi#//@persons.0"/>
  </mapping>
XML
write_pivot_footer "ForwardDelete.pivot"

# 4. Person2MemberExistingFamily
cat > "$OUT_DIR/Person2MemberExistingFamilyFam.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
  <families name="Simpson"/>
</Families:FamilyRegister>
XML

cat > "$OUT_DIR/Person2MemberExistingFamilyPer.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">

  <persons xsi:type="Persons:Male" name="Simpson, Homer" birthday="DEFAULT"/>
  <persons xsi:type="Persons:Female" name="Simpson, Marge" birthday="DEFAULT"/>
  <persons xsi:type="Persons:Male" name="Simpson, Bart" birthday="DEFAULT"/>
  <persons xsi:type="Persons:Female" name="Simpson, Lisa" birthday="DEFAULT"/>

</Persons:PersonRegister>
XML

write_pivot_header "Person2MemberExistingFamily.pivot" "Person2MemberExistingFamily" "Person2MemberExistingFamilyFam.xmi" "Person2MemberExistingFamilyPer.xmi" "BWD"
write_pivot_footer "Person2MemberExistingFamily.pivot"

# 5. Person2MemberNewFamily
cat > "$OUT_DIR/Person2MemberNewFamilyFam.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
</Families:FamilyRegister>
XML

cat > "$OUT_DIR/Person2MemberNewFamilyPer.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">

  <persons xsi:type="Persons:Male" name="Skinner, Seymour" birthday="DEFAULT"/>
  <persons xsi:type="Persons:Male" name="Simpson, Homer" birthday="DEFAULT"/>
  <persons xsi:type="Persons:Male" name="Simpson, Homer" birthday="DEFAULT"/>

</Persons:PersonRegister>
XML

write_pivot_header "Person2MemberNewFamily.pivot" "Person2MemberNewFamily" "Person2MemberNewFamilyFam.xmi" "Person2MemberNewFamilyPer.xmi" "BWD"
write_pivot_footer "Person2MemberNewFamily.pivot"

# 6. BackwardRenameMemberName
write_family_father "BackwardRenameMemberNameFam.xmi"
cat > "$OUT_DIR/BackwardRenameMemberNamePer.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">
  <persons xsi:type="Persons:Male" name="Simpson, HomerX" birthday="DEFAULT"/>
</Persons:PersonRegister>
XML
write_pivot_header "BackwardRenameMemberName.pivot" "BackwardRenameMemberName" "BackwardRenameMemberNameFam.xmi" "BackwardRenameMemberNamePer.xmi" "BWD"
cat >> "$OUT_DIR/BackwardRenameMemberName.pivot" <<XML
  <mapping>
    <mMap href="BackwardRenameMemberNameFam.xmi#//@families.0/@father"/>
    <pMap xsi:type="Persons:Male" href="BackwardRenameMemberNamePer.xmi#//@persons.0"/>
  </mapping>
XML
write_pivot_footer "BackwardRenameMemberName.pivot"

# 7. BackwardDelete
write_family_father "BackwardDeleteFam.xmi"
write_persons_empty "BackwardDeletePer.xmi"
write_pivot_header "BackwardDelete.pivot" "BackwardDelete" "BackwardDeleteFam.xmi" "BackwardDeletePer.xmi" "BWD"
cat >> "$OUT_DIR/BackwardDelete.pivot" <<XML
  <mapping>
    <mMap href="BackwardDeleteFam.xmi#//@families.0/@father"/>
  </mapping>
XML
write_pivot_footer "BackwardDelete.pivot"

# 8. ConcurrentMatchMemberPerson
write_family_father "ConcurrentMatchMemberPersonFam.xmi"
cat > "$OUT_DIR/ConcurrentMatchMemberPersonPer.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">
  <persons xsi:type="Persons:Male" name="Simpson, Homer" birthday="DEFAULT"/>
</Persons:PersonRegister>
XML
write_pivot_header "ConcurrentMatchMemberPerson.pivot" "ConcurrentMatchMemberPerson" "ConcurrentMatchMemberPersonFam.xmi" "ConcurrentMatchMemberPersonPer.xmi" "FWD"
write_pivot_footer "ConcurrentMatchMemberPerson.pivot"

# 9. ConcurrentTargetRenameWins
write_family_daughter "ConcurrentTargetRenameWinsFam.xmi"
cat > "$OUT_DIR/ConcurrentTargetRenameWinsPer.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">
  <persons xsi:type="Persons:Female" name="Simpson, Marie" birthday="DEFAULT"/>
</Persons:PersonRegister>
XML
write_pivot_header "ConcurrentTargetRenameWins.pivot" "ConcurrentTargetRenameWins" "ConcurrentTargetRenameWinsFam.xmi" "ConcurrentTargetRenameWinsPer.xmi" "BWD"
cat >> "$OUT_DIR/ConcurrentTargetRenameWins.pivot" <<XML
  <mapping>
    <mMap href="ConcurrentTargetRenameWinsFam.xmi#//@families.0/@daughters.0"/>
    <pMap xsi:type="Persons:Female" href="ConcurrentTargetRenameWinsPer.xmi#//@persons.0"/>
  </mapping>
XML
write_pivot_footer "ConcurrentTargetRenameWins.pivot"

# 10. ConcurrentDeleteMatched
cat > "$OUT_DIR/ConcurrentDeleteMatchedFam.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
</Families:FamilyRegister>
XML
write_persons_empty "ConcurrentDeleteMatchedPer.xmi"
write_pivot_header "ConcurrentDeleteMatched.pivot" "ConcurrentDeleteMatched" "ConcurrentDeleteMatchedFam.xmi" "ConcurrentDeleteMatchedPer.xmi" "FWD"
cat >> "$OUT_DIR/ConcurrentDeleteMatched.pivot" <<XML
  <mapping/>
XML
write_pivot_footer "ConcurrentDeleteMatched.pivot"

# 11. BackwardMoveMaleToExistingFamily
cat > "$OUT_DIR/BackwardMoveMaleToExistingFamilyFam.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
  <families name="Simpson">
    <sons name="Bart"/>
  </families>
  <families name="Flanders"/>
</Families:FamilyRegister>
XML
cat > "$OUT_DIR/BackwardMoveMaleToExistingFamilyPer.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">
  <persons xsi:type="Persons:Male" name="Flanders, Bart" birthday="DEFAULT"/>
</Persons:PersonRegister>
XML
write_pivot_header "BackwardMoveMaleToExistingFamily.pivot" "BackwardMoveMaleToExistingFamily" "BackwardMoveMaleToExistingFamilyFam.xmi" "BackwardMoveMaleToExistingFamilyPer.xmi" "BWD"
cat >> "$OUT_DIR/BackwardMoveMaleToExistingFamily.pivot" <<XML
  <mapping>
    <mMap href="BackwardMoveMaleToExistingFamilyFam.xmi#//@families.0/@sons.0"/>
    <pMap xsi:type="Persons:Male" href="BackwardMoveMaleToExistingFamilyPer.xmi#//@persons.0"/>
  </mapping>
XML
write_pivot_footer "BackwardMoveMaleToExistingFamily.pivot"

# 12. BackwardMoveFemaleToExistingFamily
cat > "$OUT_DIR/BackwardMoveFemaleToExistingFamilyFam.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
  <families name="Simpson">
    <daughters name="Lisa"/>
  </families>
  <families name="Flanders"/>
</Families:FamilyRegister>
XML
cat > "$OUT_DIR/BackwardMoveFemaleToExistingFamilyPer.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">
  <persons xsi:type="Persons:Female" name="Flanders, Lisa" birthday="DEFAULT"/>
</Persons:PersonRegister>
XML
write_pivot_header "BackwardMoveFemaleToExistingFamily.pivot" "BackwardMoveFemaleToExistingFamily" "BackwardMoveFemaleToExistingFamilyFam.xmi" "BackwardMoveFemaleToExistingFamilyPer.xmi" "BWD"
cat >> "$OUT_DIR/BackwardMoveFemaleToExistingFamily.pivot" <<XML
  <mapping>
    <mMap href="BackwardMoveFemaleToExistingFamilyFam.xmi#//@families.0/@daughters.0"/>
    <pMap xsi:type="Persons:Female" href="BackwardMoveFemaleToExistingFamilyPer.xmi#//@persons.0"/>
  </mapping>
XML
write_pivot_footer "BackwardMoveFemaleToExistingFamily.pivot"

# 13. BackwardMoveMaleToNewFamily
cat > "$OUT_DIR/BackwardMoveMaleToNewFamilyFam.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
  <families name="Simpson">
    <sons name="Bart"/>
  </families>
</Families:FamilyRegister>
XML
cat > "$OUT_DIR/BackwardMoveMaleToNewFamilyPer.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">
  <persons xsi:type="Persons:Male" name="Flanders, Bart" birthday="DEFAULT"/>
</Persons:PersonRegister>
XML
write_pivot_header "BackwardMoveMaleToNewFamily.pivot" "BackwardMoveMaleToNewFamily" "BackwardMoveMaleToNewFamilyFam.xmi" "BackwardMoveMaleToNewFamilyPer.xmi" "BWD"
cat >> "$OUT_DIR/BackwardMoveMaleToNewFamily.pivot" <<XML
  <mapping>
    <mMap href="BackwardMoveMaleToNewFamilyFam.xmi#//@families.0/@sons.0"/>
    <pMap xsi:type="Persons:Male" href="BackwardMoveMaleToNewFamilyPer.xmi#//@persons.0"/>
  </mapping>
XML
write_pivot_footer "BackwardMoveMaleToNewFamily.pivot"

# 14. BackwardMoveFemaleToNewFamily
cat > "$OUT_DIR/BackwardMoveFemaleToNewFamilyFam.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Families:FamilyRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Families="platform:/plugin/Families/model/Families.ecore"
    xsi:schemaLocation="platform:/plugin/Families/model/Families.ecore ../Families/model/Families.ecore">
  <families name="Simpson">
    <daughters name="Lisa"/>
  </families>
</Families:FamilyRegister>
XML
cat > "$OUT_DIR/BackwardMoveFemaleToNewFamilyPer.xmi" <<XML
<?xml version="1.0" encoding="UTF-8"?>
<Persons:PersonRegister xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:Persons="platform:/plugin/Persons/model/Persons.ecore"
    xsi:schemaLocation="platform:/plugin/Persons/model/Persons.ecore ../Persons/model/Persons.ecore">
  <persons xsi:type="Persons:Female" name="Flanders, Lisa" birthday="DEFAULT"/>
</Persons:PersonRegister>
XML
write_pivot_header "BackwardMoveFemaleToNewFamily.pivot" "BackwardMoveFemaleToNewFamily" "BackwardMoveFemaleToNewFamilyFam.xmi" "BackwardMoveFemaleToNewFamilyPer.xmi" "BWD"
cat >> "$OUT_DIR/BackwardMoveFemaleToNewFamily.pivot" <<XML
  <mapping>
    <mMap href="BackwardMoveFemaleToNewFamilyFam.xmi#//@families.0/@daughters.0"/>
    <pMap xsi:type="Persons:Female" href="BackwardMoveFemaleToNewFamilyPer.xmi#//@persons.0"/>
  </mapping>
XML
write_pivot_footer "BackwardMoveFemaleToNewFamily.pivot"

echo "Generated files in $OUT_DIR"
ls -1 "$OUT_DIR" | grep -E '\.(xmi|pivot)$'