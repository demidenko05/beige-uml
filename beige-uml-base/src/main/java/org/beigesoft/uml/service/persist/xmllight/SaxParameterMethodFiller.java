package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.service.persist.xml.ASaxModelFiller;
import org.beigesoft.uml.pojo.ParameterMethod;

public class SaxParameterMethodFiller<P extends ParameterMethod> extends ASaxModelFiller<P> {

  public SaxParameterMethodFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlParameterMethod.NAMEXML_ITSTYPE.equals(elementName)) {
        getModel().setItsType(toStringOrNull(elementValue));
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttribute(elementName, attrName)) {
      if(SrvSaveXmlClassUml.NAMEXML_ITSNAME.equals(attrName)) {
        getModel().setItsName(attrValue);
        return true;
      }
    }
    return false;
  }
}
