package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.pojo.Joint2D;
import org.beigesoft.graphic.service.persist.SaxJoint2DFiller;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.RelationshipBase;

public abstract class ASaxRelationshipFiller<P extends RelationshipBase> extends ASaxElementUml<P> {

  private final SaxJoint2DFiller<Joint2D> saxJointSharedFiller;
  
  public ASaxRelationshipFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
    saxJointSharedFiller = new SaxJoint2DFiller<Joint2D>(ASrvSaveXmlRelationship.NAMEXML_SHAREDJOINT, 
        pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(ASrvSaveXmlRelationship.NAMEXML_KINDRELATIONSHIP.equals(elementName)) {
        getModel().setKind(ERelationshipKind.valueOf(elementValue));
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(super.isConsumableForAttribute(elementName, attrName)) {
      if(super.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(isConsumableForAttributeSharedJoint(elementName, attrName)) {
      if(saxJointSharedFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        isConsumableForAttributeSharedJoint(elementName, attrName);
  }

  public boolean isConsumableForAttributeSharedJoint(String elementName, String attrName) {
    int idx = getPathCurrent().size() - 2;
    return idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) && 
            saxJointSharedFiller.isConsumableForAttribute(elementName, attrName);
  }

  @Override
  public boolean handleStartElement(String localName) {
    if(saxJointSharedFiller.getNamePersistable().equals(localName)) {
      Joint2D jointShared = new Joint2D();
      getModel().setSharedJoint(jointShared);
      saxJointSharedFiller.setModelAndPrepare(getModel().getSharedJoint());
      return true;
    }
    return false;
  }

  //SGS:
  public SaxJoint2DFiller<Joint2D> getSaxJointSharedFiller() {
    return saxJointSharedFiller;
  }
}
