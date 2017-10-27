package org.benchmarx.families.core;

import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;
import java.util.ArrayList;
import java.util.List;
import org.benchmarx.emf.Comparator;
import org.benchmarx.families.core.FamilyMemberNormaliser;
import org.benchmarx.families.core.FamilyNormaliser;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Assert;

@SuppressWarnings("all")
public class FamiliesComparator implements Comparator<FamilyRegister> {
  private FamilyNormaliser comparator;
  
  private FamilyMemberNormaliser familyMemberComparator;
  
  private boolean checkAttributeValues;
  
  public FamiliesComparator() {
    FamilyNormaliser _familyNormaliser = new FamilyNormaliser();
    this.comparator = _familyNormaliser;
    FamilyMemberNormaliser _familyMemberNormaliser = new FamilyMemberNormaliser();
    this.familyMemberComparator = _familyMemberNormaliser;
    this.checkAttributeValues = true;
  }
  
  public FamiliesComparator(final boolean checkAttributeValues) {
    FamilyNormaliser _familyNormaliser = new FamilyNormaliser();
    this.comparator = _familyNormaliser;
    FamilyMemberNormaliser _familyMemberNormaliser = new FamilyMemberNormaliser();
    this.familyMemberComparator = _familyMemberNormaliser;
    this.checkAttributeValues = checkAttributeValues;
  }
  
  @Override
  public void assertEquals(final FamilyRegister expected, final FamilyRegister actual) {
    Assert.assertTrue(this.familyToString(expected).startsWith("FamilyRegister"));
    Assert.assertEquals(this.familyToString(expected), this.familyToString(actual));
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
          _builder.appendImmediate(", ", "\t");
        }
        _builder.append("\t");
        _builder.append("Family {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t     ");
        _builder.append("familyName = \"");
        String _name = f.getName();
        _builder.append(_name, "\t\t     ");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t    ");
        _builder.append(",father     = ");
        {
          if (this.checkAttributeValues) {
            String _maybeFamilyMember = this.maybeFamilyMember(f.getFather());
            _builder.append(_maybeFamilyMember, "\t\t    ");
          } else {
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t    ");
        _builder.append(",mother     = ");
        {
          if (this.checkAttributeValues) {
            String _maybeFamilyMember_1 = this.maybeFamilyMember(f.getMother());
            _builder.append(_maybeFamilyMember_1, "\t\t    ");
          } else {
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        EList<FamilyMember> _sons = f.getSons();
        final List<FamilyMember> sortedListOfSon = new ArrayList<FamilyMember>(_sons);
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        {
          if (this.checkAttributeValues) {
            this.familyMemberComparator.normalize(sortedListOfSon);
          } else {
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t    ");
        _builder.append(",sons       = [");
        {
          boolean _hasElements_1 = false;
          for(final FamilyMember son : sortedListOfSon) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "\t\t    ");
            }
            {
              if (this.checkAttributeValues) {
                String _familyMember = this.familyMember(son);
                _builder.append(_familyMember, "\t\t    ");
              } else {
                _builder.append("son");
              }
            }
          }
        }
        _builder.append("]");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        EList<FamilyMember> _daughters = f.getDaughters();
        final List<FamilyMember> sortedListOfDaughter = new ArrayList<FamilyMember>(_daughters);
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        {
          if (this.checkAttributeValues) {
            this.familyMemberComparator.normalize(sortedListOfDaughter);
          } else {
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t    ");
        _builder.append(",daughters  = [");
        {
          boolean _hasElements_2 = false;
          for(final FamilyMember daughter : sortedListOfDaughter) {
            if (!_hasElements_2) {
              _hasElements_2 = true;
            } else {
              _builder.appendImmediate(", ", "\t\t    ");
            }
            {
              if (this.checkAttributeValues) {
                String _familyMember_1 = this.familyMember(daughter);
                _builder.append(_familyMember_1, "\t\t    ");
              } else {
                _builder.append("daughter");
              }
            }
          }
        }
        _builder.append("]");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
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
      if ((fm != null)) {
        _builder.append("Just (");
        String _familyMember = this.familyMember(fm);
        _builder.append(_familyMember);
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
    _builder.append(_name);
    _builder.append("\" }");
    return _builder.toString();
  }
}
