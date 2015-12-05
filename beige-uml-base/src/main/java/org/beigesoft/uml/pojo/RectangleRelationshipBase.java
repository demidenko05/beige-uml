package org.beigesoft.uml.pojo;

import org.beigesoft.uml.model.EJointSide;

public class RectangleRelationshipBase implements Cloneable {

  private EJointSide sideJoint = EJointSide.RIGHT;
  
  private double sideLength;

  public EJointSide getSideJoint() {
    return sideJoint;
  }

  @Override
  public RectangleRelationshipBase clone() {
    RectangleRelationshipBase clone;
    try {
      clone = (RectangleRelationshipBase) super.clone();
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public void setSideJoint(EJointSide sideJoint) {
    this.sideJoint = sideJoint;
  }

  public double getSideLength() {
    return sideLength;
  }

  public void setSideLength(double sideLength) {
    this.sideLength = sideLength;
  }
}
