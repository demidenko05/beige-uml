package org.beigesoft.uml.service.comparator;

import java.util.Comparator;

import org.beigesoft.uml.assembly.ClassRelationFull;
import org.beigesoft.uml.pojo.ClassUml;

public class ComparatorClassRelationshipFullLevelX<CL extends ClassUml> 
    implements Comparator<ClassRelationFull<CL>> {

  @Override
  public int compare(ClassRelationFull<CL> o1, ClassRelationFull<CL> o2) {
    CL o1Other = o1.getRelationship().getShapeRelationshipEnd().getShapeFull() == o1.getClassRelationship().getShapeFull() ?
        o1.getRelationship().getShapeRelationshipStart().getShapeFull().getShape() : o1.getRelationship().getShapeRelationshipEnd().getShapeFull().getShape();
    CL o2Other = o2.getRelationship().getShapeRelationshipEnd().getShapeFull() == o2.getClassRelationship().getShapeFull() ?
        o2.getRelationship().getShapeRelationshipStart().getShapeFull().getShape() : o2.getRelationship().getShapeRelationshipEnd().getShapeFull().getShape();
    return Double.compare(o1Other.getLevelXOnDiagram(), o2Other.getLevelXOnDiagram());
  }
}
