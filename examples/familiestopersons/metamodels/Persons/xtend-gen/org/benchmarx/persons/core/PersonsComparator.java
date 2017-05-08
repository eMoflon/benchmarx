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
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Assert;

@SuppressWarnings("all")
public class PersonsComparator implements Comparator<PersonRegister> {
  private PersonNormaliser comparator;
  
  public PersonsComparator() {
    PersonNormaliser _personNormaliser = new PersonNormaliser();
    this.comparator = _personNormaliser;
  }
  
  @Override
  public void assertEquals(final PersonRegister expected, final PersonRegister actual) {
    String _personsToString = this.personsToString(expected);
    boolean _startsWith = _personsToString.startsWith("PersonRegister");
    Assert.assertTrue(_startsWith);
    String _personsToString_1 = this.personsToString(expected);
    String _personsToString_2 = this.personsToString(actual);
    Assert.assertEquals(_personsToString_1, _personsToString_2);
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
    _builder.append("\t\t");
    this.comparator.normalize(sortedList);
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
            _builder.append("Male  {   ");
            _builder.newLine();
            _builder.append("\t\t");
            _builder.append("         ");
            _builder.append("fullName = \"");
            String _name = ((Male)p).getName();
            _builder.append(_name, "\t\t         ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("       ");
            _builder.append(", birthday = \"");
            Date _birthday = ((Male)p).getBirthday();
            String _myString = this.toMyString(_birthday);
            _builder.append(_myString, "\t\t       ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
          } else {
            _builder.append("\t\t");
            _builder.append("Female  {   ");
            _builder.newLine();
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
            Date _birthday_1 = p.getBirthday();
            String _myString_1 = this.toMyString(_birthday_1);
            _builder.append(_myString_1, "\t\t       ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
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
    final SimpleDateFormat sm = new SimpleDateFormat("YYYY-MM-dd");
    return sm.format(d);
  }
}
