/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.pojo;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.uml.model.AShapeRelationshipBase;
import org.beigesoft.uml.model.EJointSide;
import org.beigesoft.uml.model.IRectangleRelationship;
import org.beigesoft.uml.service.UtilsRectangleRelationship;

/**
 * <p>Represent UML relation of comment model</p>
 * 
 * @author Yury Demidenko
 */
public class CommentRelationship extends AShapeRelationshipBase implements IRectangleRelationship, Cloneable {

  private final RectangleRelationshipBase rectangleRelationshipBase;
  
  private CommentUml comment;

  private Point2D pointEnd;
    
  @Override
  public String toString() {
    return String.format("X1=%1$.3f, Y1=%2$.3f, X2=%3$.3f, Y2=%4$.3f", getPointJoint().getX(), getPointJoint().getY(), pointEnd.getX(), pointEnd.getY());
  }

  public CommentRelationship() {
    pointEnd = new Point2D(0, 0);
    rectangleRelationshipBase = new RectangleRelationshipBase();
  }
  
  public CommentRelationship(CommentUml comment) {
    setShape(comment);
    rectangleRelationshipBase = new RectangleRelationshipBase();
    pointEnd = new Point2D(comment.getPointStart().getX() + comment.getWidth() + 1, 
        comment.getPointStart().getY() + 1);
    UtilsRectangleRelationship.evalPointJoint(this);
  }
  
  @Override
  public CommentRelationship clone() {
    CommentRelationship clone = (CommentRelationship) super.clone();
    return clone;
  }
  
  @Override
  public EJointSide getSideJoint() {
    return rectangleRelationshipBase.getSideJoint();
  }

  @Override
  public void setSideJoint(EJointSide sideJoint) {
    rectangleRelationshipBase.setSideJoint(sideJoint);
  }

  @Override
  public double getSideLength() {
    return rectangleRelationshipBase.getSideLength();
  }

  @Override
  public void setSideLength(double sideLength) {
    rectangleRelationshipBase.setSideLength(sideLength);
  }
  
  public Point2D getPointEnd() {
    return pointEnd;
  }

  public void setPointEnd(Point2D pointEnd) {
    this.pointEnd = pointEnd;
  }

  public CommentUml getShape() {
    return comment;
  }

  public void setShape(CommentUml comment) {
    this.comment = comment;
  }
}
