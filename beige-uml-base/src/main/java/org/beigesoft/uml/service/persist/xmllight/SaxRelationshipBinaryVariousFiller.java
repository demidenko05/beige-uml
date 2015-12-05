package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.pojo.ShapeRelationshipVarious;

public class SaxRelationshipBinaryVariousFiller<P extends RelationshipBinaryVarious>
    extends ASaxRelationshipFiller<P> {

  private final SaxShapeRelationshipVariousFiller<ShapeRelationshipVarious, ShapeFullVarious<?>> saxShapeRelationshipStartFiller;
  
  private final SaxShapeRelationshipVariousFiller<ShapeRelationshipVarious, ShapeFullVarious<?>> saxShapeRelationshipEndFiller;
  
  public SaxRelationshipBinaryVariousFiller(String namePersistable,
      List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
    saxShapeRelationshipStartFiller = 
        new SaxShapeRelationshipVariousFiller<ShapeRelationshipVarious, ShapeFullVarious<?>>(SrvSaveXmlRelationshipBinary.NAMEXML_CLASSRELATIONSHIPSTART,
            pathCurrent);
    saxShapeRelationshipEndFiller = 
        new SaxShapeRelationshipVariousFiller<ShapeRelationshipVarious, ShapeFullVarious<?>>(SrvSaveXmlRelationshipBinary.NAMEXML_CLASSRELATIONSHIPEND,
            pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(super.isConsumableForElement(elementName)) {
      if(super.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(isConsumableForElementRelationshipStart(elementName)) {
      if(saxShapeRelationshipStartFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(isConsumableForElementRelationshipEnd(elementName)) {
      if(saxShapeRelationshipEndFiller.fillModel(elementName, elementValue)) {
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
    if(isConsumableForAttributeRelationshipStart(elementName, attrName)) {
      if(saxShapeRelationshipStartFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    if(isConsumableForAttributeRelationshipEnd(elementName, attrName)) {
      if(saxShapeRelationshipEndFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isConsumableForElement(String elementName) {
    return super.isConsumableForElement(elementName) ||
        isConsumableForElementRelationshipStart(elementName) ||
        isConsumableForElementRelationshipEnd(elementName);
  }

  public boolean isConsumableForElementRelationshipStart(String elementName) {
    int idx = getPathCurrent().size() - 3;
    return idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        saxShapeRelationshipStartFiller.isConsumableForElement(elementName);
  }

  public boolean isConsumableForElementRelationshipEnd(String elementName) {
    int idx = getPathCurrent().size() - 3;
    return idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        saxShapeRelationshipEndFiller.isConsumableForElement(elementName);
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        isConsumableForAttributeRelationshipStart(elementName, attrName) ||
        isConsumableForAttributeRelationshipEnd(elementName, attrName);
  }

  public boolean isConsumableForAttributeRelationshipStart(String elementName, String attrName) {
    int idx = getPathCurrent().size() - 2;
    return (idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        saxShapeRelationshipStartFiller.isConsumableForAttribute(elementName, attrName)) ||
        saxShapeRelationshipStartFiller.getSaxPoint2DFiller().isConsumableForAttribute(elementName, attrName);
  }

  public boolean isConsumableForAttributeRelationshipEnd(String elementName, String attrName) {
    int idx = getPathCurrent().size() - 2;
    return (idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        saxShapeRelationshipEndFiller.isConsumableForAttribute(elementName, attrName)) ||
        saxShapeRelationshipEndFiller.getSaxPoint2DFiller().isConsumableForAttribute(elementName, attrName);
  }

  @Override
  public void setModelAndPrepare(P model) {
    super.setModelAndPrepare(model);
    if(getModel() == null) {
      saxShapeRelationshipEndFiller.setModelAndPrepare(null);
      saxShapeRelationshipStartFiller.setModelAndPrepare(null);
    }
    else {
      saxShapeRelationshipEndFiller.setModelAndPrepare(getModel().getShapeRelationshipEnd());
      saxShapeRelationshipStartFiller.setModelAndPrepare(getModel().getShapeRelationshipStart());
    }
  }

  //SGS:
  public SaxShapeRelationshipVariousFiller<ShapeRelationshipVarious, ShapeFullVarious<?>> getSaxShapeRelationshipStartFiller() {
    return saxShapeRelationshipStartFiller;
  }

  public SaxShapeRelationshipVariousFiller<ShapeRelationshipVarious, ShapeFullVarious<?>> getSaxShapeRelationshipEndFiller() {
    return saxShapeRelationshipEndFiller;
  }
}
