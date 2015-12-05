package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.factory.IFactorySimple;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipPoly;

public class SaxRelationshipPolyClassFiller<SH extends ClassUml>  
    extends SaxRelationshipPolyFiller<RelationshipPoly<RectangleRelationship<ClassFull<SH>, SH>, ClassFull<SH>, SH>,
    SaxRectangleRelationshipFiller<RectangleRelationship<ClassFull<SH>, SH>, ClassFull<SH>, SH>,
    RectangleRelationship<ClassFull<SH>, SH>,
    ClassFull<SH>, SH> {

  public SaxRelationshipPolyClassFiller(
      String namePersistable,
      List<String> pathCurrent,
      SaxRectangleRelationshipFiller<RectangleRelationship<ClassFull<SH>, SH>, ClassFull<SH>, SH> saxShapeRelationshipFiller,
      IFactorySimple<RectangleRelationship<ClassFull<SH>, SH>> factoryShapeRelationship) {
    super(namePersistable, pathCurrent, saxShapeRelationshipFiller,
        factoryShapeRelationship);
  }

  @Override
  public boolean handleEndElement(String localName) throws Exception {
    if(getSaxShapeRelationshipFiller().getNamePersistable().equals(localName)) {
      ClassFull<SH> classFull = getSaxShapeRelationshipFiller().getModel().getShapeFull();
      classFull.getClassRelationsPoly().add(getSaxShapeRelationshipFiller().getModel());
      getSaxShapeRelationshipFiller().setModelAndPrepare(null);
      return true;
    }
    if(super.handleEndElement(localName)) {
      return true;
    }
    return false;
  }
}
