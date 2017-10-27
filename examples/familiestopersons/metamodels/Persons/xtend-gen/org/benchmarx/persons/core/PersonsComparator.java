package org.benchmarx.persons.core;

import Persons.Male;
import Persons.Person;
import Persons.PersonRegister;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.benchmarx.emf.Comparator;
import org.benchmarx.persons.core.PersonNormaliser;
import org.benchmarx.persons.core.PersonStructuralNormaliser;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Assert;

@SuppressWarnings("all")
public class PersonsComparator implements Comparator<PersonRegister> {
  private PersonNormaliser comparator;
  
  private PersonStructuralNormaliser structuralComparator;
  
  private boolean checkAttributeValues;
  
  public PersonsComparator() {
    PersonNormaliser _personNormaliser = new PersonNormaliser();
    this.comparator = _personNormaliser;
    PersonStructuralNormaliser _personStructuralNormaliser = new PersonStructuralNormaliser();
    this.structuralComparator = _personStructuralNormaliser;
    this.checkAttributeValues = true;
  }
  
  public PersonsComparator(final boolean checkAttributeValues) {
    PersonNormaliser _personNormaliser = new PersonNormaliser();
    this.comparator = _personNormaliser;
    PersonStructuralNormaliser _personStructuralNormaliser = new PersonStructuralNormaliser();
    this.structuralComparator = _personStructuralNormaliser;
    this.checkAttributeValues = checkAttributeValues;
  }
  
  @Override
  public void assertEquals(final PersonRegister expected, final PersonRegister actual) {
    Assert.assertTrue(this.personsToString(expected).startsWith("PersonRegister"));
    Assert.assertEquals(this.personsToString(expected), this.personsToString(actual));
  }
  
  public String personsToString(final PersonRegister persons) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("PersonRegister {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("persons = [");
    _builder.newLine();
    _builder.append("\t\t");
    EList<Person> _persons = persons.getPersons();
    final List<Person> sortedList = new ArrayList<Person>(_persons);
    _builder.newLineIfNotEmpty();
    {
      if (this.checkAttributeValues) {
        _builder.append("\t\t");
        this.comparator.normalize(sortedList);
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
      } else {
        this.structuralComparator.normalize(sortedList);
      }
    }
    _builder.newLineIfNotEmpty();
    {
      boolean _hasElements = false;
      for(final Person p : sortedList) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "\t\t");
        }
        {
          if ((p instanceof Male)) {
            _builder.append("\t\t");
            _builder.append("Male");
            {
              if (this.checkAttributeValues) {
                _builder.append(" {");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("         ");
                _builder.append("fullName = \"");
                String _name = ((Male)p).getName();
                _builder.append(_name, "\t\t         ");
                _builder.append("\"");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("         ");
                _builder.append(", birthday = \"");
                String _myString = this.toMyString(((Male)p).getBirthday());
                _builder.append(_myString, "\t\t         ");
                _builder.append("\"");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("}");
              } else {
              }
            }
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t\t");
            _builder.append("Female");
            {
              if (this.checkAttributeValues) {
                _builder.append(" {   ");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("         ");
                _builder.append("fullName = \"");
                String _name_1 = p.getName();
                _builder.append(_name_1, "\t\t         ");
                _builder.append("\"");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("       ");
                _builder.append(", birthday = \"");
                String _myString_1 = this.toMyString(p.getBirthday());
                _builder.append(_myString_1, "\t\t       ");
                _builder.append("\"");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("}");
              } else {
              }
            }
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  public String toMyString(final Date d) {
    final SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
    return sm.format(d);
  }
}
