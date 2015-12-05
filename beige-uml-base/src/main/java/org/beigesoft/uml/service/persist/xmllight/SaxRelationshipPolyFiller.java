package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.factory.IFactorySimple;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.ShapeRelationship;
import org.beigesoft.uml.pojo.ShapeUml;

public class SaxRelationshipPolyFiller<P extends RelationshipPoly<SHR, SHF, SH>, SAX extends ASaxShapeRelationshipFiller<SHR>, SHR extends ShapeRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends ASaxRelationshipFiller<P> {

  private final SAX saxShapeRelationshipFiller;
  
  private final IFactorySimple<SHR> factoryShapeRelationship;
    
  public SaxRelationshipPolyFiller(String namePersistable,
      List<String> pathCurrent, SAX saxShapeRelationshipFiller,
      IFactorySimple<SHR> factoryShapeRelationship) {
    super(namePersistable, pathCurrent);
    this.saxShapeRelationshipFiller = saxShapeRelationshipFiller;
    this.factoryShapeRelationship = factoryShapeRelationship;
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(super.isConsumableForElement(elementName)) {
      if(super.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxShapeRelationshipFiller.isConsumableForElement(elementName)) {
      if(saxShapeRelationshipFiller.fillModel(elementName, elementValue)) {
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
    else if(saxShapeRelationshipFiller.isConsumableForAttribute(elementName, attrName)) {
      if(saxShapeRelationshipFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean handleStartElement(String localName) {
    if(saxShapeRelationshipFiller.getNamePersistable().equals(localName)) {
      SHR shapeRelationshipCurr = factoryShapeRelationship.create();
      getModel().getShapesRelationship().add(shapeRelationshipCurr);
      saxShapeRelationshipFiller.setModelAndPrepare(shapeRelationshipCurr);
     return true;
    }
    if(super.handleStartElement(localName)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean handleEndElement(String localName) throws Exception {
    if(saxShapeRelationshipFiller.getNamePersistable().equals(localName)) {
      saxShapeRelationshipFiller.setModelAndPrepare(null);
      return true;
    }
    if(super.handleEndElement(localName)) {
      return true;
    }
    return false;
  }

  //SGS:
  public SAX getSaxShapeRelationshipFiller() {
    return saxShapeRelationshipFiller;
  }

  public IFactorySimple<SHR> getFactoryShapeRelationship() {
    return factoryShapeRelationship;
  }
}
