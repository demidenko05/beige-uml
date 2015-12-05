package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.persist.SaxPoint2DFiller;
import org.beigesoft.uml.model.ELineEndShape;
import org.beigesoft.uml.pojo.LineUml;

public class SaxLineUmlFiller<P extends LineUml> extends ASaxElementUml<P> {

  private final SaxPoint2DFiller<Point2D> saxPoint1Filler;

  private final SaxPoint2DFiller<Point2D> saxPoint2Filler;

  public SaxLineUmlFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
    saxPoint1Filler = new SaxPoint2DFiller<Point2D>(SrvSaveXmlLineUml.NAMEXML_POINT1, pathCurrent);
    saxPoint2Filler = new SaxPoint2DFiller<Point2D>(SrvSaveXmlLineUml.NAMEXML_POINT2, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlLineUml.NAMEXML_ISDASHED.equals(elementName)) {
        getModel().setIsDashed(Boolean.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlLineUml.NAMEXML_POINT1END.equals(elementName)) {
        getModel().setLineEnd1Shape(ELineEndShape.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlLineUml.NAMEXML_POINT2END.equals(elementName)) {
        getModel().setLineEnd2Shape(ELineEndShape.valueOf(elementValue));
        return true;
      }
      if(super.fillModel(elementName, elementValue)) {//indexZ
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttribute(elementName, attrName)) {
      if(super.isConsumableForAttribute(elementName, attrName)) {
        if(super.fillModel(elementName, attrName, attrValue)) {//id
          return true;
        }
      }
      if(isConsumableForAttributePoint1(elementName, attrName)) {
        //point1 - x, y
        if(saxPoint1Filler.fillModel(elementName, attrName, attrValue)) {
          return true;
        }
      }
      if(isConsumableForAttributePoint2(elementName, attrName)) {
        //point1 - x, y
        if(saxPoint2Filler.fillModel(elementName, attrName, attrValue)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public void setModelAndPrepare(P model) {
    super.setModelAndPrepare(model);
    if(getModel() == null) {
      saxPoint1Filler.setModelAndPrepare(null);
      saxPoint2Filler.setModelAndPrepare(null);
    }
    else {
      saxPoint1Filler.setModelAndPrepare(getModel().getPoint1());
      saxPoint2Filler.setModelAndPrepare(getModel().getPoint2());
    }
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        isConsumableForAttributePoint1(elementName, attrName) ||
        isConsumableForAttributePoint2(elementName, attrName);
  }

  public boolean isConsumableForAttributePoint1(String elementName, String attrName) {
    int idx = getPathCurrent().size() - 2;
    return idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        saxPoint1Filler.isConsumableForAttribute(elementName, attrName);
  }

  public boolean isConsumableForAttributePoint2(String elementName, String attrName) {
    int idx = getPathCurrent().size() - 2;
    return idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        saxPoint2Filler.isConsumableForAttribute(elementName, attrName);
  }

  //SGS:
  public SaxPoint2DFiller<Point2D> getSaxPoint1Filler() {
    return saxPoint1Filler;
  }

  public SaxPoint2DFiller<Point2D> getSaxPoint2Filler() {
    return saxPoint2Filler;
  }
}
