package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;
import java.util.UUID;

import org.beigesoft.service.persist.xml.ASaxModelFiller;
import org.beigesoft.uml.model.IElementUml;

public abstract class ASaxElementUml<P extends IElementUml> extends ASaxModelFiller<P> {

  public ASaxElementUml(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(ASrvSaveXmlElementUml.NAMEXML_INDEXZ.equals(elementName)) {
        getModel().setIndexZ(Integer.valueOf(elementValue));
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttribute(elementName, attrName)) {
      if(ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName)) {
        getModel().setId(UUID.fromString(attrValue));
        return true;
      }
    }
    return false;
  }
}
