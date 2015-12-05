package org.beigesoft.graphic.service.persist;

import java.util.List;

import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.graphic.pojo.Joint2D;

public class SaxJoint2DFiller<M extends Joint2D> extends SaxPoint2DFiller<M> {

  public SaxJoint2DFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttribute(elementName, attrName)) {
      if(super.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
      if(SrvPersistXmlJoint2D.JOINT_TYPE.equals(attrName)) {
        getModel().setTypeJoint(EJoint2DType.valueOf(attrValue));
        return true;
      }
    }
    return false;
  }
}
