package org.benchmarx.bags1.core;

import bags1.Element;
import bags1.MyBag;
import java.util.ArrayList;
import org.benchmarx.bags1.core.ElementNormaliser;
import org.benchmarx.emf.Comparator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Assert;

@SuppressWarnings("all")
public class Bag1Comparator implements Comparator<MyBag> {
  private ElementNormaliser comparator;
  
  public Bag1Comparator() {
    ElementNormaliser _elementNormaliser = new ElementNormaliser();
    this.comparator = _elementNormaliser;
  }
  
  @Override
  public void assertEquals(final MyBag expected, final MyBag actual) {
    String _bagToString = this.bagToString(expected);
    boolean _startsWith = _bagToString.startsWith("Bag1");
    Assert.assertTrue(_startsWith);
    String _bagToString_1 = this.bagToString(expected);
    String _bagToString_2 = this.bagToString(actual);
    Assert.assertEquals(_bagToString_1, _bagToString_2);
  }
  
  public String bagToString(final MyBag b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Bag1 {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("elements = [");
    _builder.newLine();
    _builder.append("\t\t");
    EList<Element> _elements = b.getElements();
    final ArrayList<Element> sortedList = new ArrayList<Element>(_elements);
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    this.comparator.normalize(sortedList);
    _builder.newLineIfNotEmpty();
    {
      boolean _hasElements = false;
      for(final Element e : sortedList) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "\t\t");
        }
        _builder.append("\t\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("\t  ");
        _builder.append("value = \"");
        String _value = e.getValue();
        _builder.append(_value, "\t\t\t  ");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("\t  ");
        _builder.append("incrementalID = \"");
        String _incrementalID = e.getIncrementalID();
        _builder.append(_incrementalID, "\t\t\t  ");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
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
}
