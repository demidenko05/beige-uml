package org.beigesoft.uml.model;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.model.IEditable;

public interface IShapeRelationship extends IEditable {

  public IShapeUml getShape();
  
  public Point2D getPointJoint();

  public void setPointJoint(Point2D pointJoint);

  public ERelationshipEndType getEndType();

  public void setEndType(ERelationshipEndType endType);

  public boolean getIsOwned();

  public void setIsOwned(boolean isOwned);
}
