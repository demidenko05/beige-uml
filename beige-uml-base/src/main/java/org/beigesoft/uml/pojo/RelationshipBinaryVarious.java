package org.beigesoft.uml.pojo;

import org.beigesoft.uml.model.IRelationshipBinary;


/**
 * Relationship binary between UseCace -Actor, Actor -Actor, etc.
 * @author yury
 *
 * @param <SHR>
 */
public class RelationshipBinaryVarious extends RelationshipBase implements IRelationshipBinary {
  
  private ShapeRelationshipVarious shapeRelationshipStart = new ShapeRelationshipVarious();

  private ShapeRelationshipVarious shapeRelationshipEnd = new ShapeRelationshipVarious();

  @Override
  public RelationshipBinaryVarious clone() {
    RelationshipBinaryVarious clone = (RelationshipBinaryVarious) super.clone();
    clone.setShapeRelationshipEnd((ShapeRelationshipVarious) shapeRelationshipEnd.clone());
    clone.setShapeRelationshipStart((ShapeRelationshipVarious) shapeRelationshipStart.clone());
    return clone;
  }

  @Override
  public ShapeRelationshipVarious getShapeRelationshipEnd() {
    return shapeRelationshipEnd;
  }

  @Override
  public ShapeRelationshipVarious getShapeRelationshipStart() {
    return shapeRelationshipStart;
  }

  //SGS:
  public void setShapeRelationshipStart(ShapeRelationshipVarious shapeRelationshipStart) {
    this.shapeRelationshipStart = shapeRelationshipStart;
  }
  
  public void setShapeRelationshipEnd(ShapeRelationshipVarious shapeRelationshipEnd) {
    this.shapeRelationshipEnd = shapeRelationshipEnd;
  }
}
