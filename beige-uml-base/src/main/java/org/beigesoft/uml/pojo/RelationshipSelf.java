package org.beigesoft.uml.pojo;

import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.graphic.pojo.Joint2D;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.uml.assembly.ShapeFull;

public class RelationshipSelf<SHR extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> extends RelationshipBinary<SHR, SHF, SH> {
 
  private Point2D pointSharedJointStart;
  
  private Point2D pointSharedJointEnd;

  public RelationshipSelf() {
    pointSharedJointStart = new Point2D(2, 2);
    pointSharedJointEnd = new Point2D(2, 2);
    setSharedJoint(new Joint2D(3, 3, EJoint2DType.POINT));
  }

  //SGS:
  public Point2D getPointSharedJointStart() {
    return pointSharedJointStart;
  }

  public void setPointSharedJointStart(Point2D pointSharedJointStart) {
    this.pointSharedJointStart = pointSharedJointStart;
  }

  public Point2D getPointSharedJointEnd() {
    return pointSharedJointEnd;
  }

  public void setPointSharedJointEnd(Point2D pointSharedJointEnd) {
    this.pointSharedJointEnd = pointSharedJointEnd;
  }
}
