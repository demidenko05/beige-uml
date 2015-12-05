package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.uml.pojo.FrameUml;

public class SaxFrameFiller<P extends FrameUml> extends ASaxShapeUmlFiller<P> {

  public SaxFrameFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, ASrvSaveXmlShape.POINT_START, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlFrame.NAMEXML_ITSKIND.equals(elementName)) {
        getModel().setItsKind(toStringOrNull(elementValue));
        return true;
      }
      if(SrvSaveXmlFrame.NAMEXML_PARAMETERS.equals(elementName)) {
        getModel().setParameters(toStringOrNull(elementValue));
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
