package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.service.EMinMax;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.model.IRelationshipBinary;
import org.beigesoft.uml.service.UtilsUml;

public class SrvGraphicRelationshipBinary<RE extends IRelationshipBinary, DRI, SD extends ISettingsDraw> extends ASrvGraphicRelationship<RE, DRI, SD> {

  public SrvGraphicRelationshipBinary(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(RE ge, DRI di, SD ds) {
    if(ge.getShapeRelationshipStart().getPointJoint().getX() == ge.getShapeRelationshipEnd().getPointJoint().getX()
        && ge.getShapeRelationshipStart().getPointJoint().getY() == ge.getShapeRelationshipEnd().getPointJoint().getY()) {
      return;
    }
    getSrvDraw().preparePaint(di, ds);
    Point2D pointJointStartChangeable = new Point2D(ge.getShapeRelationshipStart().getPointJoint());
    Point2D pointJointEndChangeable = new Point2D(ge.getShapeRelationshipEnd().getPointJoint());
    Point2D[] pointsJointShared = null;
    if(ge.getSharedJoint() == null) {//simple line
      drawRelationshipEnd(di, ds, ge, pointJointEndChangeable, pointJointStartChangeable, ge.getShapeRelationshipStart());
      drawRelationshipEnd(di, ds, ge, pointJointStartChangeable, pointJointEndChangeable, ge.getShapeRelationshipEnd());
    }
    else if(ge.getSharedJoint().getTypeJoint() == EJoint2DType.POINT) {//shared joint point - 2 lines
      pointsJointShared = new Point2D[]{ge.getSharedJoint()};
      drawRelationshipEnd(di, ds, ge, pointsJointShared[0], pointJointStartChangeable, ge.getShapeRelationshipStart());
      drawRelationshipEnd(di, ds, ge, pointsJointShared[0], pointJointEndChangeable, ge.getShapeRelationshipEnd());
    }
    else {//shared joint bus X/Y - 3 lines
      pointsJointShared = UtilsGraphMath.evalPointsBus(ge.getSharedJoint(), pointJointStartChangeable, pointJointEndChangeable);
      drawRelationshipEnd(di, ds, ge, pointsJointShared[0], pointJointStartChangeable, ge.getShapeRelationshipStart());
      drawRelationshipEnd(di, ds, ge, pointsJointShared[1], pointJointEndChangeable, ge.getShapeRelationshipEnd());
    }
    if(UtilsUml.isRelationDashed(ge.getItsKind())) {
      ds.setIsDashed(true);
      getSrvDraw().preparePaint(di, ds);
    }
    if(pointsJointShared == null) {
      getSrvDraw().drawLine(di, ds, pointJointStartChangeable.getX(), pointJointStartChangeable.getY(), 
          pointJointEndChangeable.getX(), pointJointEndChangeable.getY());
    }
    else if(pointsJointShared.length == 1) {
      getSrvDraw().drawLine(di, ds, pointJointStartChangeable.getX(), pointJointStartChangeable.getY(), 
          pointsJointShared[0].getX(), pointsJointShared[0].getY());
      getSrvDraw().drawLine(di, ds, pointsJointShared[0].getX(), pointsJointShared[0].getY(), 
          pointJointEndChangeable.getX(), pointJointEndChangeable.getY());
    }
    else if(pointsJointShared.length == 2) {
      getSrvDraw().drawLine(di, ds, pointJointStartChangeable.getX(), pointJointStartChangeable.getY(), 
          pointsJointShared[0].getX(), pointsJointShared[0].getY());
      getSrvDraw().drawLine(di, ds, pointsJointShared[0].getX(), pointsJointShared[0].getY(),
          pointsJointShared[1].getX(), pointsJointShared[1].getY());
      getSrvDraw().drawLine(di, ds, pointsJointShared[1].getX(), pointsJointShared[1].getY(), 
          pointJointEndChangeable.getX(), pointJointEndChangeable.getY());
    }
    if(ds.getIsDashed()) {
      ds.setIsDashed(false);
      getSrvDraw().preparePaint(di, ds);
    }
    if(ge.getIsSelected()) {
      getSrvDraw().drawRectangle(di, ds,
          ge.getShapeRelationshipStart().getPointJoint().getX() - getSettingsGraphic().getWidthDragRectangle()/2, 
          ge.getShapeRelationshipStart().getPointJoint().getY() - getSettingsGraphic().getWidthDragRectangle()/2, getSettingsGraphic().getWidthDragRectangle(),
          getSettingsGraphic().getWidthDragRectangle(), false);
      getSrvDraw().drawRectangle(di, ds,
          ge.getShapeRelationshipEnd().getPointJoint().getX() - getSettingsGraphic().getWidthDragRectangle()/2, 
          ge.getShapeRelationshipEnd().getPointJoint().getY() - getSettingsGraphic().getWidthDragRectangle()/2, getSettingsGraphic().getWidthDragRectangle(),
          getSettingsGraphic().getWidthDragRectangle(), false);
      if(pointsJointShared != null) {
        for(Point2D p : pointsJointShared) {
          getSrvDraw().drawRectangle(di, ds,
              p.getX() - getSettingsGraphic().getWidthDragRectangle()/2, 
              p.getY() - getSettingsGraphic().getWidthDragRectangle()/2, getSettingsGraphic().getWidthDragRectangle(),
              getSettingsGraphic().getWidthDragRectangle(), false);
        }
      }
    }
  }

  @Override
  public void recalculate(RE re, double coefficient) {
    super.recalculate(re, coefficient);
    re.getShapeRelationshipStart().getPointJoint().setX(re.getShapeRelationshipStart().getPointJoint().getX() * coefficient);
    re.getShapeRelationshipStart().getPointJoint().setY(re.getShapeRelationshipStart().getPointJoint().getY() * coefficient);
    re.getShapeRelationshipEnd().getPointJoint().setX(re.getShapeRelationshipEnd().getPointJoint().getX() * coefficient);
    re.getShapeRelationshipEnd().getPointJoint().setY(re.getShapeRelationshipEnd().getPointJoint().getY() * coefficient);
  }
  
  @Override
  public Point2D evalMinimumScreenPoint(RE ge) {
    return evalMinMaxScreenPoint(ge, EMinMax.MINIMUM);
  }
  
  protected Point2D evalMinMaxScreenPoint(IRelationshipBinary ge, EMinMax minMax) {
    Point2D result = new Point2D(UtilsGraphMath.evalMinMax(ge.getShapeRelationshipStart().getPointJoint().getX(), 
        ge.getShapeRelationshipEnd().getPointJoint().getX(), minMax),
        UtilsGraphMath.evalMinMax(ge.getShapeRelationshipStart().getPointJoint().getY(), 
            ge.getShapeRelationshipEnd().getPointJoint().getY(), minMax));
    if(ge.getSharedJoint() != null) {
      if(ge.getSharedJoint().getTypeJoint() == EJoint2DType.POINT) {//shared joint point - 2 lines
        result.setX(UtilsGraphMath.evalMinMax(result.getX(), 
            ge.getSharedJoint().getX(), minMax));
        result.setX(UtilsGraphMath.evalMinMax(result.getY(), 
            ge.getSharedJoint().getY(), minMax));
      }
      else {//shared joint bus X/Y - 3 lines
        Point2D[] pointsJointShared = UtilsGraphMath.evalPointsBus(ge.getSharedJoint(), ge.getShapeRelationshipStart().getPointJoint(), ge.getShapeRelationshipEnd().getPointJoint());
        for(Point2D p : pointsJointShared) {
          result.setX(UtilsGraphMath.evalMinMax(result.getX(), 
              p.getX(), minMax));
          result.setX(UtilsGraphMath.evalMinMax(result.getY(), 
              p.getY(), minMax));
        }
      }
   }
    return result;
  }
  
  @Override
  public Point2D evalMaximumScreenPoint(RE ge) {
    return evalMinMaxScreenPoint(ge, EMinMax.MAXIMUM);
  }

  @Override
  public boolean isContainsScreenPoint(RE ge, int x, int y) {
    double realX = UtilsGraphMath.toRealX(getSettingsGraphic(), x);
    double realY = UtilsGraphMath.toRealY(getSettingsGraphic(), y);
    if(ge.getIsSelected()) {
      if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), ge.getShapeRelationshipStart().getPointJoint(), x, y)) {
        return true;
      }
      if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), ge.getShapeRelationshipEnd().getPointJoint(), x, y)) {
        return true;
      }
    }
    if(ge.getSharedJoint() == null) {//simple line
      return UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), ge.getShapeRelationshipStart().getPointJoint().getX(), 
          ge.getShapeRelationshipStart().getPointJoint().getY(), ge.getShapeRelationshipEnd().getPointJoint().getX(), 
          ge.getShapeRelationshipEnd().getPointJoint().getY(), realX, realY);
     }
    else if(ge.getSharedJoint().getTypeJoint() == EJoint2DType.POINT) {//shared joint point - 2 lines
      if(UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), ge.getShapeRelationshipStart().getPointJoint().getX(), 
          ge.getShapeRelationshipStart().getPointJoint().getY(), ge.getSharedJoint().getX(), 
          ge.getSharedJoint().getY(), realX, realY)) {
        return true;
      }
      if(ge.getIsSelected()) {
        if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), ge.getSharedJoint(), x, y)) {
          return true;
        }
      }
      return UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), ge.getSharedJoint().getX(), 
          ge.getSharedJoint().getY(), ge.getShapeRelationshipEnd().getPointJoint().getX(), 
          ge.getShapeRelationshipEnd().getPointJoint().getY(), realX, realY);
    }
    else {//shared joint bus X/Y - 3 lines
      Point2D[] pointsJointShared = UtilsGraphMath.evalPointsBus(ge.getSharedJoint(), ge.getShapeRelationshipStart().getPointJoint(), ge.getShapeRelationshipEnd().getPointJoint());
      if(ge.getIsSelected()) {
        if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), pointsJointShared[0], x, y)) {
          return true;
        }
        if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), pointsJointShared[1], x, y)) {
          return true;
        }
      }
      if(UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), ge.getShapeRelationshipStart().getPointJoint().getX(), 
          ge.getShapeRelationshipStart().getPointJoint().getY(), pointsJointShared[0].getX(), 
          pointsJointShared[0].getY(), realX, realY)) {
        return true;
      }
      if(UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), pointsJointShared[0].getX(), 
          pointsJointShared[0].getY(), pointsJointShared[1].getX(), 
          pointsJointShared[1].getY(), realX, realY)) {
        return true;
      }
      return UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), ge.getShapeRelationshipEnd().getPointJoint().getX(), 
          ge.getShapeRelationshipEnd().getPointJoint().getY(), pointsJointShared[1].getX(), 
          pointsJointShared[1].getY(), realX, realY);
    }
  }
}
