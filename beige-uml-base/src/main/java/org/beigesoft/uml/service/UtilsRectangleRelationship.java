package org.beigesoft.uml.service;

import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.model.EJointSide;
import org.beigesoft.uml.model.IRectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;

public class UtilsRectangleRelationship {//TODO re-do as non-static

  @Deprecated
  public static final String NAMEXML_SIDEJOINT = "sideJoint";
  
  @Deprecated
  public static final String NAMEXML_SIDELENGTH = "sideLength";
  
  @Deprecated
  public static final String NAMEXML_RECTANGLEID = "rectangleId";//TODO
  
  public static void setPointJointAt(IRectangleRelationship rr, SettingsGraphicUml sg, int screenX, int screenY) {
    double realX = UtilsGraphMath.toRealX(sg, screenX);
    double realY = UtilsGraphMath.toRealY(sg, screenY);
    //all length:
    double lengthX = realX - rr.getShape().getPointStart().getX();
    double restX = rr.getShape().getWidth() - lengthX;
    double lengthY = realY - rr.getShape().getPointStart().getY();
    double restY = rr.getShape().getHeight() - lengthY;
    //1. min length:
    double minX = Math.min(lengthX, restX);
    double minY = Math.min(lengthY, restY);
    double min = Math.min(minX, minY);
    if(min == minX) {//left or right
      if(minX == lengthX) {
        rr.setSideJoint(EJointSide.LEFT);
        rr.setSideLength(restY);
      }  
      else {
        rr.setSideJoint(EJointSide.RIGHT);
        rr.setSideLength(lengthY);
      }
    }
    else {//top or bottom
      if(minY == lengthY) {
        rr.setSideJoint(EJointSide.TOP);
        rr.setSideLength(lengthX);
      }  
      else {
        rr.setSideJoint(EJointSide.BOTTOM);
        rr.setSideLength(restX);
      }
    }
    evalPointJoint(rr);
  }
  
  public static void movePointJoint(IRectangleRelationship rr, SettingsGraphicUml sg, double deltaX, double deltaY) {
    if(deltaX > deltaY) {
      tryToMoveX(rr, sg, deltaX);
      tryToMoveY(rr, sg, deltaY);
      evalPointJoint(rr);
    }
    else {
      tryToMoveY(rr, sg, deltaY);
      tryToMoveX(rr, sg, deltaX);
      evalPointJoint(rr);
    }
  }
  
  public static void movePointJointAlongSide(IRectangleRelationship rr, SettingsGraphicUml sg, double deltaX, double deltaY) {
    if(deltaX > deltaY) {
      tryToMoveXAlongSide(rr, sg, deltaX);
      tryToMoveYAlongSide(rr, sg, deltaY);
      evalPointJoint(rr);
    }
    else {
      tryToMoveYAlongSide(rr, sg, deltaY);
      tryToMoveXAlongSide(rr, sg, deltaX);
      evalPointJoint(rr);
    }
  }
  
  public static void recalculate(IRectangleRelationship rr, double coefficient) {
    rr.setSideLength(rr.getSideLength() * coefficient);
    evalPointJoint(rr);
  }

  protected static void tryToMoveXAlongSide(IRectangleRelationship rr, SettingsGraphicUml sg, double deltaX) {
    if((rr.getPointJoint().getX() <= rr.getShape().getPointStart().getX() && deltaX < 0) ||
        (rr.getPointJoint().getX() >= rr.getShape().getPointStart().getX() + rr.getShape().getWidth() && deltaX > 0)) {
      return;
    }
    if(rr.getSideJoint() == EJointSide.TOP) {
      rr.setSideLength(rr.getSideLength() + deltaX);
    }
    else if(rr.getSideJoint() == EJointSide.BOTTOM) {
      rr.setSideLength(rr.getSideLength() - deltaX);
    }
    //corrections:
    if(rr.getSideLength() < 0) {
      rr.setSideLength(0);
    }
    else if(rr.getSideJoint() == EJointSide.BOTTOM || rr.getSideJoint() == EJointSide.TOP) {
      rr.setSideLength(Math.min(rr.getSideLength(), rr.getShape().getWidth()));
    }
    evalPointJoint(rr);
  }
  
  protected static void tryToMoveX(IRectangleRelationship rr, SettingsGraphicUml sg, double deltaX) {
    if((rr.getPointJoint().getX() <= rr.getShape().getPointStart().getX() && deltaX < 0) ||
        (rr.getPointJoint().getX() >= rr.getShape().getPointStart().getX() + rr.getShape().getWidth() && deltaX > 0)) {
      return;
    }
    double approximationCalculation = sg.getApproximationCalculation();
    if(rr.getSideJoint() == EJointSide.TOP) {
      rr.setSideLength(rr.getSideLength() + deltaX);
    }
    else if(rr.getSideJoint() == EJointSide.BOTTOM) {
      rr.setSideLength(rr.getSideLength() - deltaX);
    }
    else if(rr.getSideJoint() == EJointSide.LEFT && deltaX > 0) {
      if(rr.getSideLength() == rr.getShape().getHeight()) {
        rr.setSideJoint(EJointSide.TOP);
        rr.setSideLength(deltaX);
      }
      else if(rr.getSideLength() - approximationCalculation <= 0) {
        rr.setSideJoint(EJointSide.BOTTOM);
        rr.setSideLength(rr.getShape().getWidth() - deltaX);
      }
    }
    else if(rr.getSideJoint() == EJointSide.RIGHT && deltaX < 0) {
      if(rr.getSideLength() - approximationCalculation <= 0) {
        rr.setSideJoint(EJointSide.TOP);
        rr.setSideLength(rr.getShape().getWidth() - deltaX);
      }
      else if(rr.getSideLength() + approximationCalculation >= rr.getShape().getHeight()) {
        rr.setSideJoint(EJointSide.BOTTOM);
        rr.setSideLength(deltaX);
      }
    }
    //corrections:
    if(rr.getSideLength() < 0) {
      rr.setSideLength(0);
    }
    else if(rr.getSideJoint() == EJointSide.BOTTOM || rr.getSideJoint() == EJointSide.TOP) {
      rr.setSideLength(Math.min(rr.getSideLength(), rr.getShape().getWidth()));
    }
    evalPointJoint(rr);
  }
  
  protected static void tryToMoveY(IRectangleRelationship rr, SettingsGraphicUml sg, double deltaY) {
    if((rr.getPointJoint().getY() <= rr.getShape().getPointStart().getY() && deltaY < 0) ||
        (rr.getPointJoint().getY() >= rr.getShape().getPointStart().getY() + rr.getShape().getHeight() && deltaY > 0)) {
      return;
    }
    double approximationCalculation = sg.getApproximationCalculation();
    if(rr.getSideJoint() == EJointSide.LEFT) {
      rr.setSideLength(rr.getSideLength() - deltaY);
    }
    else if(rr.getSideJoint() == EJointSide.RIGHT) {
      rr.setSideLength(rr.getSideLength() + deltaY);
    }
    else if(rr.getSideJoint() == EJointSide.TOP && deltaY > 0) {
      if(Math.abs(rr.getSideLength() - rr.getShape().getWidth()) <= approximationCalculation) {
        rr.setSideJoint(EJointSide.RIGHT);
        rr.setSideLength(deltaY);
      }
      else if(rr.getSideLength() - approximationCalculation <= 0) {
        rr.setSideJoint(EJointSide.LEFT);
        rr.setSideLength(rr.getShape().getHeight() - deltaY);
      }
    }
    else if(rr.getSideJoint() == EJointSide.BOTTOM && deltaY < 0) {
      if(rr.getSideLength() - approximationCalculation <= 0) {
        rr.setSideJoint(EJointSide.RIGHT);
        rr.setSideLength(rr.getShape().getHeight() - deltaY);
      }
      else if(rr.getSideLength() + approximationCalculation >= rr.getShape().getWidth()) {
        rr.setSideJoint(EJointSide.LEFT);
        rr.setSideLength(deltaY);
      }
    }
    //corrections:
    if(rr.getSideLength() < 0) {
      rr.setSideLength(0);
    }
    else if(rr.getSideJoint() == EJointSide.RIGHT || rr.getSideJoint() == EJointSide.LEFT) {
      rr.setSideLength(Math.min(rr.getSideLength(), rr.getShape().getHeight()));
    }
    evalPointJoint(rr);
  }
  
  protected static void tryToMoveYAlongSide(IRectangleRelationship rr, SettingsGraphicUml sg, double deltaY) {
    if((rr.getPointJoint().getY() <= rr.getShape().getPointStart().getY() && deltaY < 0) ||
        (rr.getPointJoint().getY() >= rr.getShape().getPointStart().getY() + rr.getShape().getHeight() && deltaY > 0)) {
      return;
    }
    if(rr.getSideJoint() == EJointSide.LEFT) {
      rr.setSideLength(rr.getSideLength() - deltaY);
    }
    else if(rr.getSideJoint() == EJointSide.RIGHT) {
      rr.setSideLength(rr.getSideLength() + deltaY);
    }
    //corrections:
    if(rr.getSideLength() < 0) {
      rr.setSideLength(0);
    }
    else if(rr.getSideJoint() == EJointSide.RIGHT || rr.getSideJoint() == EJointSide.LEFT) {
      rr.setSideLength(Math.min(rr.getSideLength(), rr.getShape().getHeight()));
    }
    evalPointJoint(rr);
  }
  
  public static void evalPointJoint(IRectangleRelationship rr) {
    if(rr.getSideJoint() == EJointSide.TOP) {
      rr.getPointJoint().setX(rr.getShape().getPointStart().getX() + rr.getSideLength());
      rr.getPointJoint().setY(rr.getShape().getPointStart().getY());
    }
    else if(rr.getSideJoint() == EJointSide.RIGHT) {
      rr.getPointJoint().setX(rr.getShape().getPointStart().getX() + rr.getShape().getWidth());
      rr.getPointJoint().setY(rr.getShape().getPointStart().getY() + rr.getSideLength());
    }
    else if(rr.getSideJoint() == EJointSide.BOTTOM) {
      rr.getPointJoint().setX(rr.getShape().getPointStart().getX() + rr.getShape().getWidth() - rr.getSideLength());
      rr.getPointJoint().setY(rr.getShape().getPointStart().getY() + rr.getShape().getHeight());
    }
    else if(rr.getSideJoint() == EJointSide.LEFT) {
      rr.getPointJoint().setX(rr.getShape().getPointStart().getX());
      rr.getPointJoint().setY(rr.getShape().getPointStart().getY() + rr.getShape().getHeight() - rr.getSideLength());
    }
  }
  
  public static void evalPointsJointAndShared(RelationshipSelf<?, ?, ?> rel) {
    evalPointJoint(rel.getShapeRelationshipStart());
    evalPointJoint(rel.getShapeRelationshipEnd());
    //clockwise:
    if(rel.getShapeRelationshipStart().getSideJoint() == EJointSide.TOP ||
        rel.getShapeRelationshipStart().getSideJoint() == EJointSide.BOTTOM) {//end - right || left
      rel.getPointSharedJointStart().setX(rel.getShapeRelationshipStart().getPointJoint().getX());
      rel.getPointSharedJointStart().setY(rel.getSharedJoint().getY());
      rel.getPointSharedJointEnd().setX(rel.getSharedJoint().getX());
      rel.getPointSharedJointEnd().setY(rel.getShapeRelationshipEnd().getPointJoint().getY());
    }
    else {
      rel.getPointSharedJointStart().setX(rel.getSharedJoint().getX());
      rel.getPointSharedJointStart().setY(rel.getShapeRelationshipStart().getPointJoint().getY());
      rel.getPointSharedJointEnd().setX(rel.getShapeRelationshipEnd().getPointJoint().getX());
      rel.getPointSharedJointEnd().setY(rel.getSharedJoint().getY());
    }
  }
}
