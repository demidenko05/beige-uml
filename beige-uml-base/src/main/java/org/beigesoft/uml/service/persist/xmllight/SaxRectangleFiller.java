package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.uml.pojo.RectangleUml;

public class SaxRectangleFiller<P extends RectangleUml> extends ASaxShapeUmlFiller<P> {

  public SaxRectangleFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, ASrvSaveXmlShape.POINT_START, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlRectangle.NAMEXML_ISTRANSPARENT.equals(elementName)) {
        getModel().setIsTransparent(Boolean.valueOf(elementValue));
        return true;
      }
      if(super.fillModel(elementName, elementValue)) {//shape's width height, adjust min size
        return true;
      }
    }
    return false;
  }
}
