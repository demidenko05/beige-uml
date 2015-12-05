package org.beigesoft.graphic.service.persist;

import java.util.List;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.service.persist.xml.ASaxModelFiller;

public class SaxPoint2DFiller<M extends Point2D> extends ASaxModelFiller<M> {

  public SaxPoint2DFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttribute(elementName, attrName)) {
      if(SrvSaveXmlPoint2D.X.equals(attrName)) {
        getModel().setX(Double.valueOf(attrValue));
        return true;
      }
      if(SrvSaveXmlPoint2D.Y.equals(attrName)) {
        getModel().setY(Double.valueOf(attrValue));
        return true;
      }
    }
    return false;
  }
}
