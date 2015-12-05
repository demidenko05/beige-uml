package org.beigesoft.uml.model;

import org.beigesoft.graphic.pojo.Point2D;

public interface IRectangleRelationship extends IShapeRelationship {
  
  public Point2D getPointJoint();

  public EJointSide getSideJoint();

  public void setSideJoint(EJointSide sideJoint);

  public double getSideLength();

  public void setSideLength(double sideLength); 
}
