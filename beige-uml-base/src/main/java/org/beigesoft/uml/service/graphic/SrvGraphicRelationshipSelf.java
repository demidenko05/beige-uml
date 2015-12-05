package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.ShapeUml;

public class SrvGraphicRelationshipSelf<RE extends RelationshipSelf<RectangleRelationship<SHF, SH>, SHF, SH>, DRI, SD extends ISettingsDraw, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends ASrvGraphicRelationship<RE, DRI, SD> {
  
  public SrvGraphicRelationshipSelf(ISrvDraw<DRI, SD, ?> srvDraw,
      SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void recalculate(RE re, double coefficient) {
    re.getShapeRelationshipStart().setSideLength(re.getShapeRelationshipStart().getSideLength() * coefficient);
    re.getShapeRelationshipEnd().setSideLength(re.getShapeRelationshipEnd().getSideLength() * coefficient);
    re.getShapeRelationshipStart().getPointJoint().setX(re.getShapeRelationshipStart().getPointJoint().getX() * coefficient);
    re.getShapeRelationshipStart().getPointJoint().setY(re.getShapeRelationshipStart().getPointJoint().getY() * coefficient);
    re.getShapeRelationshipEnd().getPointJoint().setX(re.getShapeRelationshipEnd().getPointJoint().getX() * coefficient);
    re.getShapeRelationshipEnd().getPointJoint().setY(re.getShapeRelationshipEnd().getPointJoint().getY() * coefficient);
    re.getPointSharedJointStart().setX(re.getPointSharedJointStart().getX() * coefficient);
    re.getPointSharedJointStart().setY(re.getPointSharedJointStart().getY() * coefficient);
    re.getPointSharedJointEnd().setX(re.getPointSharedJointEnd().getX() * coefficient);
    re.getPointSharedJointEnd().setY(re.getPointSharedJointEnd().getY() * coefficient);
    re.getSharedJoint().setX(re.getSharedJoint().getX() * coefficient);
    re.getSharedJoint().setY(re.getSharedJoint().getY() * coefficient);
  }

  @Override
  public void draw(RE re, DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    Point2D jointPointStartChangeable = new Point2D(re.getShapeRelationshipStart().getPointJoint());
    Point2D jointPointEndChangeable = new Point2D(re.getShapeRelationshipEnd().getPointJoint());
    drawRelationshipEnd(di, ds, re, re.getPointSharedJointStart(), jointPointStartChangeable, re.getShapeRelationshipStart());
    drawRelationshipEnd(di, ds, re, re.getPointSharedJointEnd(), jointPointEndChangeable, re.getShapeRelationshipEnd());
    getSrvDraw().drawLine(di, ds, jointPointStartChangeable.getX(), jointPointStartChangeable.getY(), 
        re.getPointSharedJointStart().getX(), re.getPointSharedJointStart().getY());
    getSrvDraw().drawLine(di, ds, re.getSharedJoint().getX(), re.getSharedJoint().getY(), 
        re.getPointSharedJointStart().getX(), re.getPointSharedJointStart().getY());
    getSrvDraw().drawLine(di, ds, jointPointEndChangeable.getX(), jointPointEndChangeable.getY(), 
        re.getPointSharedJointEnd().getX(), re.getPointSharedJointEnd().getY());
    getSrvDraw().drawLine(di, ds, re.getSharedJoint().getX(), re.getSharedJoint().getY(), 
        re.getPointSharedJointEnd().getX(), re.getPointSharedJointEnd().getY());
    if(re.getIsSelected()) {
      getSrvDraw().drawRectangle(di, ds, 
          re.getSharedJoint().getX() - getSettingsGraphic().getWidthDragRectangle()/2, 
          re.getSharedJoint().getY() - getSettingsGraphic().getWidthDragRectangle()/2, getSettingsGraphic().getWidthDragRectangle(),
          getSettingsGraphic().getWidthDragRectangle(), false);
      getSrvDraw().drawRectangle(di, ds,
          re.getShapeRelationshipStart().getPointJoint().getX() - getSettingsGraphic().getWidthDragRectangle()/2, 
          re.getShapeRelationshipStart().getPointJoint().getY() - getSettingsGraphic().getWidthDragRectangle()/2, getSettingsGraphic().getWidthDragRectangle(),
          getSettingsGraphic().getWidthDragRectangle(), false);
      getSrvDraw().drawRectangle(di, ds,
          re.getShapeRelationshipEnd().getPointJoint().getX() - getSettingsGraphic().getWidthDragRectangle()/2, 
          re.getShapeRelationshipEnd().getPointJoint().getY() - getSettingsGraphic().getWidthDragRectangle()/2, getSettingsGraphic().getWidthDragRectangle(),
          getSettingsGraphic().getWidthDragRectangle(), false);
    }
  }

  @Override
  public boolean isContainsScreenPoint(RE re, int x, int y) {
    double realX = UtilsGraphMath.toRealX(getSettingsGraphic(), x);
    double realY = UtilsGraphMath.toRealY(getSettingsGraphic(), y);
    if(re.getIsSelected()) {
      if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), re.getShapeRelationshipStart().getPointJoint(), x, y)) {
        return true;
      }
      if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), re.getShapeRelationshipEnd().getPointJoint(), x, y)) {
        return true;
      }
      if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), re.getSharedJoint(), x, y)) {
        return true;
      }
    }
    if(UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), re.getShapeRelationshipStart().getPointJoint().getX(), 
        re.getShapeRelationshipStart().getPointJoint().getY(), re.getPointSharedJointStart().getX(), re.getPointSharedJointStart().getY(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), re.getPointSharedJointStart().getX(), 
        re.getPointSharedJointStart().getY(), re.getSharedJoint().getX(), re.getSharedJoint().getY(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), re.getShapeRelationshipEnd().getPointJoint().getX(), 
        re.getShapeRelationshipEnd().getPointJoint().getY(), re.getPointSharedJointEnd().getX(), re.getPointSharedJointEnd().getY(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), re.getPointSharedJointEnd().getX(), 
        re.getPointSharedJointEnd().getY(), re.getSharedJoint().getX(), re.getSharedJoint().getY(), realX, realY)) {
      return true;
    }
    return false;
  }
}
