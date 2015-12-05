package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;
import java.util.UUID;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.persist.SaxPoint2DFiller;
import org.beigesoft.service.persist.xml.ASaxModelFiller;
import org.beigesoft.uml.model.AShapeRelationshipBase;
import org.beigesoft.uml.model.ERelationshipEndType;

public abstract class ASaxShapeRelationshipFiller<P extends AShapeRelationshipBase> extends ASaxModelFiller<P> {

  private final SaxPoint2DFiller<Point2D> saxPointJointFiller;
  
  public ASaxShapeRelationshipFiller(String namePersistable,
      List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
    saxPointJointFiller = new SaxPoint2DFiller<Point2D>(SrvSaveXmlShapeRelationship.NAMEXML_POINTJOINT, 
        pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlShapeRelationship.NAMEXML_RELATIONSHIPEND.equals(elementName)) {
        getModel().setEndType(ERelationshipEndType.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlShapeRelationship.NAMEXML_ISOWNED.equals(elementName)) {
        getModel().setIsOwned(Boolean.valueOf(elementValue));
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(super.isConsumableForAttribute(elementName, attrName)) {
      if(SrvSaveXmlShapeRelationship.NAMEXML_SHAPEID.equals(attrName)) {
        fillShape(UUID.fromString(attrValue));
        return true;
      }
    }
    else if(isConsumableForAttributePointJoint(elementName, attrName)) {
      if(saxPointJointFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        isConsumableForAttributePointJoint(elementName, attrName);
  }

  public boolean isConsumableForAttributePointJoint(String elementName, String attrName) {
    int idx = getPathCurrent().size() - 2;
    return idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) && 
            saxPointJointFiller.isConsumableForAttribute(elementName, attrName);
  }

  @Override
  public void setModelAndPrepare(P model) {
    super.setModelAndPrepare(model);
    if(getModel() == null) {
      saxPointJointFiller.setModelAndPrepare(null);
    }
    else {
      saxPointJointFiller.setModelAndPrepare(getModel().getPointJoint());
    }
  }

  protected abstract void fillShape(UUID id);
  
  //SGS:
  public SaxPoint2DFiller<Point2D> getSaxPoint2DFiller() {
    return saxPointJointFiller;
  }
}
