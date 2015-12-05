package org.beigesoft.uml.pojo;

import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.IRelationshipBinary;

public class RelationshipBinary<SHR extends ShapeRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml>
    extends RelationshipBase implements IRelationshipBinary {

  private SHR shapeRelationshipStart;

  private SHR shapeRelationshipEnd;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public RelationshipBinary clone() {
    RelationshipBinary clone = (RelationshipBinary) super.clone();
    clone.setShapeRelationshipStart((ShapeRelationship) shapeRelationshipStart.clone());
    clone.setShapeRelationshipEnd((ShapeRelationship) shapeRelationshipEnd.clone());
    return clone;
  }

  @Override
  public SHR getShapeRelationshipEnd() {
    return shapeRelationshipEnd;
  }

  @Override
  public SHR getShapeRelationshipStart() {
    return shapeRelationshipStart;
  }

  //SGS:
  public void setShapeRelationshipEnd(SHR shapeRelationshipEnd) {
    this.shapeRelationshipEnd = shapeRelationshipEnd;
  }

  public void setShapeRelationshipStart(SHR shapeRelationshipStart) {
    this.shapeRelationshipStart = shapeRelationshipStart;
  }
}
