package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.uml.pojo.StateInvContin;

public class SaxStateInvContinFiller<P extends StateInvContin> extends ASaxShapeUmlFiller<P> {

  public SaxStateInvContinFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, ASrvSaveXmlShape.POINT_START, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlStateInvContin.NAMEXML_ISNAMEBOLD.equals(elementName)) {
        getModel().setIsBold(Boolean.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlClassUml.NAMEXML_ITSNAME.equals(elementName)) {
        getModel().setItsName(makeString(getModel().getItsName(), elementValue));
        return true;
      }
      if(super.fillModel(elementName, elementValue)) {//shape's width height, adjust min size
        return true;
      }
    }
    return false;
  }
}
