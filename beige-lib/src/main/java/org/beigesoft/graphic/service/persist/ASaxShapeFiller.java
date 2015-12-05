package org.beigesoft.graphic.service.persist;

import java.util.List;

import org.beigesoft.graphic.model.IShape;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.service.persist.xml.ASaxModelFiller;

public abstract class ASaxShapeFiller<P extends IShape> extends ASaxModelFiller<P> {

  private final SaxPoint2DFiller<Point2D> saxPointStartFiller;

  public ASaxShapeFiller(String namePersistable, String namePersistableStartPoint, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
    saxPointStartFiller = new SaxPoint2DFiller<Point2D>(namePersistableStartPoint, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(ASrvSaveXmlShape.HEIGHT.equals(elementName)) {
        getModel().setHeight(Double.valueOf(elementValue));
        return true;
      }
      if(ASrvSaveXmlShape.WIDTH.equals(elementName)) {
        getModel().setWidth(Double.valueOf(elementValue));
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttributePointStart(elementName, attrName)) {
      //shape's pointStart - x, y
      if(getSaxPoint2DFiller().fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        isConsumableForAttributePointStart(elementName, attrName);
  }

  public boolean isConsumableForAttributePointStart(String elementName, String attrName) {
    int idx = getPathCurrent().size() - 2;
    return idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        saxPointStartFiller.isConsumableForAttribute(elementName, attrName);
  }

  @Override
  public void setModelAndPrepare(P model) {
    super.setModelAndPrepare(model);
    if(getModel() == null) {
      saxPointStartFiller.setModelAndPrepare(null);
    }
    else {
      saxPointStartFiller.setModelAndPrepare(getModel().getPointStart());
    }
  }

  //SGS:
  public SaxPoint2DFiller<Point2D> getSaxPoint2DFiller() {
    return saxPointStartFiller;
  }
}
