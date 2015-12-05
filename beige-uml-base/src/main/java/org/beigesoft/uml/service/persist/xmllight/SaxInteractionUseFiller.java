package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.uml.pojo.InteractionUse;

public class SaxInteractionUseFiller<P extends InteractionUse> extends ASaxShapeUmlFiller<P> {

  public SaxInteractionUseFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, ASrvSaveXmlShape.POINT_START, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlInteractionUse.NAMEXML_DESCRIPTION.equals(elementName)) {
        getModel().setDescription(makeString(getModel().getDescription(), elementValue));
        return true;
      }
      if(super.fillModel(elementName, elementValue)) {//shape's width height, adjust min size
        return true;
      }
    }
    return false;
  }
}
