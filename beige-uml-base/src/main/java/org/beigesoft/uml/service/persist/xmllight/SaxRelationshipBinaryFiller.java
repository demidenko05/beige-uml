package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.ShapeUml;

public class SaxRelationshipBinaryFiller<P extends RelationshipBinary<RectangleRelationship<SHF, SH>, SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml>
    extends ASaxRelationshipFiller<P> {

  private final SaxRectangleRelationshipFiller<RectangleRelationship<SHF, SH>, SHF, SH> saxRectangleRelationshipStartFiller;
  
  private final SaxRectangleRelationshipFiller<RectangleRelationship<SHF, SH>, SHF, SH> saxRectangleRelationshipEndFiller;
  
  public SaxRelationshipBinaryFiller(String namePersistable,
      List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
    saxRectangleRelationshipStartFiller = 
        new SaxRectangleRelationshipFiller<RectangleRelationship<SHF, SH>, SHF, SH>(SrvSaveXmlRelationshipBinary.NAMEXML_CLASSRELATIONSHIPSTART,
            pathCurrent);
    saxRectangleRelationshipEndFiller = 
        new SaxRectangleRelationshipFiller<RectangleRelationship<SHF, SH>, SHF, SH>(SrvSaveXmlRelationshipBinary.NAMEXML_CLASSRELATIONSHIPEND,
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
      if(saxRectangleRelationshipStartFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(isConsumableForElementRelationshipEnd(elementName)) {
      if(saxRectangleRelationshipEndFiller.fillModel(elementName, elementValue)) {
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
      if(saxRectangleRelationshipStartFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    if(isConsumableForAttributeRelationshipEnd(elementName, attrName)) {
      if(saxRectangleRelationshipEndFiller.fillModel(elementName, attrName, attrValue)) {
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
        saxRectangleRelationshipStartFiller.isConsumableForElement(elementName);
  }

  public boolean isConsumableForElementRelationshipEnd(String elementName) {
    int idx = getPathCurrent().size() - 3;
    return idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        saxRectangleRelationshipEndFiller.isConsumableForElement(elementName);
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
        saxRectangleRelationshipStartFiller.isConsumableForAttribute(elementName, attrName)) ||
        saxRectangleRelationshipStartFiller.getSaxPoint2DFiller().isConsumableForAttribute(elementName, attrName);
  }

  public boolean isConsumableForAttributeRelationshipEnd(String elementName, String attrName) {
    int idx = getPathCurrent().size() - 2;
    return (idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        saxRectangleRelationshipEndFiller.isConsumableForAttribute(elementName, attrName)) ||
        saxRectangleRelationshipEndFiller.getSaxPoint2DFiller().isConsumableForAttribute(elementName, attrName);
  }

  @Override
  public void setModelAndPrepare(P model) {
    super.setModelAndPrepare(model);
    if(getModel() == null) {
      saxRectangleRelationshipEndFiller.setModelAndPrepare(null);
      saxRectangleRelationshipStartFiller.setModelAndPrepare(null);
    }
    else {
      saxRectangleRelationshipEndFiller.setModelAndPrepare(getModel().getShapeRelationshipEnd());
      saxRectangleRelationshipStartFiller.setModelAndPrepare(getModel().getShapeRelationshipStart());
    }
  }

  //SGS:
  public SaxRectangleRelationshipFiller<RectangleRelationship<SHF, SH>, SHF, SH> getSaxRectangleRelationshipStartFiller() {
    return saxRectangleRelationshipStartFiller;
  }

  public SaxRectangleRelationshipFiller<RectangleRelationship<SHF, SH>, SHF, SH> getSaxRectangleRelationshipEndFiller() {
    return saxRectangleRelationshipEndFiller;
  }
}
