package org.beigesoft.uml.model;

import org.beigesoft.graphic.pojo.Point2D;

public abstract class AShapeRelationshipBase implements IShapeRelationship, Cloneable {

  private Point2D pointJoint;
  
  private ERelationshipEndType endType;
  
  /**
   * used only for UML-classes
   */
  private boolean isOwned;

  private boolean isNew;

  public AShapeRelationshipBase() {
    pointJoint = new Point2D(1, 1);
  }
    
  @Override
  public Object clone() {
     try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean getIsNew() {
    return isNew;
  }

  @Override
  public void setIsNew(boolean isNew) {
    this.isNew = isNew;
  }

  @Override
  public Point2D getPointJoint() {
    return pointJoint;
  }

  @Override
  public void setPointJoint(Point2D pointJoint) {
    this.pointJoint = pointJoint;
  }

  @Override
  public ERelationshipEndType getEndType() {
    return endType;
  }

  @Override
  public void setEndType(ERelationshipEndType endType) {
    this.endType = endType;
  }

  @Override
  public boolean getIsOwned() {
    return isOwned;
  }

  @Override
  public void setIsOwned(boolean isOwned) {
    this.isOwned = isOwned;
  }
}
