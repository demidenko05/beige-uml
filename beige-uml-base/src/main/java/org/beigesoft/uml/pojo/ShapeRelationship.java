package org.beigesoft.uml.pojo;

import org.beigesoft.uml.assembly.IShapeFullUml;
import org.beigesoft.uml.model.AShapeRelationshipBase;
import org.beigesoft.uml.model.IShapeFullRelationship;

public class ShapeRelationship<SHF extends IShapeFullUml<SH>, SH extends ShapeUml> extends AShapeRelationshipBase implements IShapeFullRelationship<SHF, SH> {

  private SHF shapeFull;

  public ShapeRelationship() {
  }

  public ShapeRelationship(SHF shape) {
    this.shapeFull = shape;
  }
  
  @Override
  public String toString() {
    return shapeFull == null ? " " : 
      shapeFull.getShape() == null ? " " : shapeFull.getShape().toString();
  }

  @Override
  public ShapeUml getShape() {
    return shapeFull == null ? null : shapeFull.getShape();
  }
  
  //SGS:
  public SHF getShapeFull() {
    return shapeFull;
  }

  public void setShapeFull(SHF shape) {
    this.shapeFull = shape;
  }
}
