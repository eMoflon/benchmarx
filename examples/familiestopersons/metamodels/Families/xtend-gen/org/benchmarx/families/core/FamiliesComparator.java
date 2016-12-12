package org.benchmarx.families.core;

import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import org.benchmarx.Comparator;
import org.benchmarx.families.core.FamilyMemberNormaliser;
import org.benchmarx.families.core.FamilyNormaliser;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Assert;

@SuppressWarnings("all")
public class FamiliesComparator implements Comparator<FamilyRegister> {
  private FamilyNormaliser comparator;
  
  private FamilyMemberNormaliser familyMemberComparator;
  
  public FamiliesComparator() {
    FamilyNormaliser _familyNormaliser = new FamilyNormaliser();
    this.comparator = _familyNormaliser;
    FamilyMemberNormaliser _familyMemberNormaliser = new FamilyMemberNormaliser();
    this.familyMemberComparator = _familyMemberNormaliser;
  }
  
  @Override
  public void compare(final FamilyRegister expected, final FamilyRegister actual) {
    String _familyToString = this.familyToString(expected);
    boolean _startsWith = _familyToString.startsWith("FamilyRegister");
    Assert.assertTrue(_startsWith);
    String _familyToString_1 = this.familyToString(expected);
    String _familyToString_2 = this.familyToString(actual);
    Assert.assertEquals(_familyToString_1, _familyToString_2);
  }
  
  public String familyToString(final FamilyRegister families) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("FamilyRegister {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("families = [");
    _builder.newLine();
    _builder.append("    ");
    EList<Family> _families = families.getFamilies();
    final List<Family> sortedList = new ArrayList<Family>(_families);
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    this.comparator.normalize(sortedList);
    _builder.newLineIfNotEmpty();
    {
      boolean _hasElements = false;
      for(final Family f : sortedList) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        _builder.append("\t");
        _builder.append("Family {");
        _builder.newLine();
        _builder.append("\t     ");
        _builder.append("familyName = \"");
        String _name = f.getName();
        _builder.append(_name, "\t     ");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("\t    ");
        _builder.append(",father     = ");
        FamilyMember _father = f.getFather();
        String _maybeFamilyMember = this.maybeFamilyMember(_father);
        _builder.append(_maybeFamilyMember, "\t    ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t    ");
        _builder.append(",mother     = ");
        FamilyMember _mother = f.getMother();
        String _maybeFamilyMember_1 = this.maybeFamilyMember(_mother);
        _builder.append(_maybeFamilyMember_1, "\t    ");
        _builder.newLineIfNotEmpty();
        EList<FamilyMember> _sons = f.getSons();
        final List<FamilyMember> sortedListOfSon = new ArrayList<FamilyMember>(_sons);
        _builder.newLineIfNotEmpty();
        this.familyMemberComparator.normalize(sortedListOfSon);
        _builder.newLineIfNotEmpty();
        _builder.append("\t    ");
        _builder.append(",sons       = [");
        {
          boolean _hasElements_1 = false;
          for(final FamilyMember son : sortedListOfSon) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "\t    ");
            }
            String _familyMember = this.familyMember(son);
            _builder.append(_familyMember, "\t    ");
          }
        }
        _builder.append("]");
        _builder.newLineIfNotEmpty();
        EList<FamilyMember> _daughters = f.getDaughters();
        final List<FamilyMember> sortedListOfDaughter = new ArrayList<FamilyMember>(_daughters);
        _builder.newLineIfNotEmpty();
        this.familyMemberComparator.normalize(sortedListOfDaughter);
        _builder.newLineIfNotEmpty();
        _builder.append("\t    ");
        _builder.append(",daughters  = [");
        {
          boolean _hasElements_2 = false;
          for(final FamilyMember daughter : sortedListOfDaughter) {
            if (!_hasElements_2) {
              _hasElements_2 = true;
            } else {
              _builder.appendImmediate(", ", "\t    ");
            }
            String _familyMember_1 = this.familyMember(daughter);
            _builder.append(_familyMember_1, "\t    ");
          }
        }
        _builder.append("]");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  public String maybeFamilyMember(final FamilyMember fm) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _notEquals = (!Objects.equal(fm, null));
      if (_notEquals) {
        _builder.append("Just (");
        String _familyMember = this.familyMember(fm);
        _builder.append(_familyMember, "");
        _builder.append(")");
      } else {
        _builder.append("Nothing");
      }
    }
    return _builder.toString();
  }
  
  public String familyMember(final FamilyMember fm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("FamilyMember { firstName = \"");
    String _name = fm.getName();
    _builder.append(_name, "");
    _builder.append("\" }");
    return _builder.toString();
  }
}
