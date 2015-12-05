package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.MultiplicityElement;

public class SaxAttributeClassFiller<P extends AttributeClass> extends ASaxMemberClassFiller<P> {

  private final SaxMultiplicityElementFiller<MultiplicityElement> saxMultiplicityElementFiller;
  
  public SaxAttributeClassFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
    saxMultiplicityElementFiller = new SaxMultiplicityElementFiller<MultiplicityElement>(SrvSaveXmlAttributeClass.NAMEXML_MULTIPLICITYELEMENT, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(super.isConsumableForElement(elementName)) {
      if(super.fillModel(elementName, elementValue)) {
        return true;
      }
      if(SrvSaveXmlAttributeClass.NAMEXML_CONSTRAINTSVALUE.equals(elementName)) {
        getModel().setConstraintsValue(elementValue);
        return true;
      }
      if(SrvSaveXmlAttributeClass.NAMEXML_DEFAULTVALUE.equals(elementName)) {
        getModel().setDefaultValue(elementValue);
        return true;
      }
    }
    else if(saxMultiplicityElementFiller.isConsumableForElement(elementName)) {
      if(saxMultiplicityElementFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    return super.fillModel(elementName, elementValue);
  }

  @Override
  public boolean isConsumableForElement(String elementName) {
    return super.isConsumableForElement(elementName) ||
        saxMultiplicityElementFiller.isConsumableForElement(elementName);
  }
  
  @Override
  public boolean handleStartElement(String localName) {
    if(saxMultiplicityElementFiller.getNamePersistable().equals(localName)) {
      getModel().setMultiplicityElement(new MultiplicityElement());
      saxMultiplicityElementFiller.setModelAndPrepare(getModel().getMultiplicityElement());
      return true;
    }
    return false;
  }

  //SGS:
  public SaxMultiplicityElementFiller<MultiplicityElement> getSaxMultiplicityElementFiller() {
    return saxMultiplicityElementFiller;
  }
}
