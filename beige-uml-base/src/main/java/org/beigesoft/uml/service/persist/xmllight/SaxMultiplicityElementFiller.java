package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.service.persist.xml.ASaxModelFiller;
import org.beigesoft.uml.pojo.MultiplicityElement;

public class SaxMultiplicityElementFiller<P extends MultiplicityElement> extends ASaxModelFiller<P> {

  public SaxMultiplicityElementFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlMultiplicityElement.NAMEXML_UPPER.equals(elementName)) {
        getModel().setUpper(toIntegerOrNull(elementValue));
        return true;
      }
      if(SrvSaveXmlMultiplicityElement.NAMEXML_LOWER.equals(elementName)) {
        getModel().setLower(toIntegerOrNull(elementValue));
        return true;
      }
      if(SrvSaveXmlMultiplicityElement.NAMEXML_ISUNIQUE.equals(elementName)) {
        getModel().setIsUnique(Boolean.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlMultiplicityElement.NAMEXML_ISORDERED.equals(elementName)) {
        getModel().setIsOrdered(Boolean.valueOf(elementValue));
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    return false;
  }
}
