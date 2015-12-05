package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.persist.SaxPoint2DFiller;
import org.beigesoft.service.persist.xml.ASaxModelFiller;
import org.beigesoft.uml.model.EJointSide;
import org.beigesoft.uml.pojo.CommentRelationship;

public class SaxCommentRelationshipFiller extends ASaxModelFiller<CommentRelationship> {
  
  private SaxPoint2DFiller<Point2D> saxPoint2DFiller;

  public SaxCommentRelationshipFiller(String namePersistable, String namePersistablePointEnd, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
    saxPoint2DFiller = new SaxPoint2DFiller<Point2D>(namePersistablePointEnd, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlRectangleRelationship.NAMEXML_SIDELENGTH.equals(elementName)) {
        getModel().setSideLength(Double.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlRectangleRelationship.NAMEXML_SIDEJOINT.equals(elementName)) {
        getModel().setSideJoint(EJointSide.valueOf(elementValue));
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttributePointEnd(elementName, attrName)) {
      if(saxPoint2DFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        isConsumableForAttributePointEnd(elementName, attrName);
  }

  public boolean isConsumableForAttributePointEnd(String elementName, String attrName) {
    int idx = getPathCurrent().size() - 2;
    return idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        saxPoint2DFiller.isConsumableForAttribute(elementName, attrName);
  }

  @Override
  public void setModelAndPrepare(CommentRelationship model) {
    super.setModelAndPrepare(model);
    if(getModel() == null) {
      saxPoint2DFiller.setModelAndPrepare(null);
    }
    else {
      saxPoint2DFiller.setModelAndPrepare(getModel().getPointEnd());
    }
  }

  //SGS:
  public SaxPoint2DFiller<Point2D> getSaxPoint2DFiller() {
    return saxPoint2DFiller;
  }

  public void setSaxPoint2DFiller(SaxPoint2DFiller<Point2D> saxPoint2DFiller) {
    this.saxPoint2DFiller = saxPoint2DFiller;
  }
}
