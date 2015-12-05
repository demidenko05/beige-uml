package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.ShapeRelationship;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.service.UtilsUml;

public class SrvGraphicRelationshipPoly<RE extends RelationshipPoly<SHR, SHF, SH>, DRI, SD extends ISettingsDraw, SHR extends ShapeRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends ASrvGraphicRelationship<RE, DRI, SD> {
  
  public SrvGraphicRelationshipPoly(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void recalculate(RE re, double coefficient) {
    super.recalculate(re, coefficient);
    for(SHR shr : re.getShapesRelationship()) {
      shr.getPointJoint().setX(shr.getPointJoint().getX()*coefficient);
      shr.getPointJoint().setY(shr.getPointJoint().getY()*coefficient);
    }
   }

  @Override
  public void draw(RE re, DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    re.setMaxBusCoord(-9999);
    re.setMinBusCoord(9999);
    for(SHR shr : re.getShapesRelationship()) {
      Point2D sharedJoint = new Point2D();
      if(re.getSharedJoint().getTypeJoint() == EJoint2DType.POINT) {
        sharedJoint.setX(re.getSharedJoint().getX());
        sharedJoint.setY(re.getSharedJoint().getY());
      }
      else if(re.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_X) {
        sharedJoint.setX(shr.getPointJoint().getX());
        sharedJoint.setY(re.getSharedJoint().getY());
        re.setMaxBusCoord(Math.max(re.getMaxBusCoord(), shr.getPointJoint().getX()));
        re.setMinBusCoord(Math.min(re.getMinBusCoord(), shr.getPointJoint().getX()));
      }
      else if(re.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_Y) {
        sharedJoint.setX(re.getSharedJoint().getX());
        sharedJoint.setY(shr.getPointJoint().getY());
        re.setMaxBusCoord(Math.max(re.getMaxBusCoord(), shr.getPointJoint().getY()));
        re.setMinBusCoord(Math.min(re.getMinBusCoord(), shr.getPointJoint().getY()));
      }
      Point2D jointPointEndChangeable = new Point2D(shr.getPointJoint());
      if(UtilsUml.isRelationDashed(re.getItsKind())) {
        ds.setIsDashed(false);
        getSrvDraw().preparePaint(di, ds);
      }
      drawRelationshipEnd(di, ds, re, sharedJoint, jointPointEndChangeable, shr);
      if(UtilsUml.isRelationDashed(re.getItsKind())) {
        ds.setIsDashed(true);
        getSrvDraw().preparePaint(di, ds);
      }
      getSrvDraw().drawLine(di, ds, sharedJoint.getX(), sharedJoint.getY(), 
          jointPointEndChangeable.getX(), jointPointEndChangeable.getY());
    }
    if(re.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_X) {
      getSrvDraw().drawLine(di, ds, re.getMinBusCoord(), re.getSharedJoint().getY(), 
          re.getMaxBusCoord(), re.getSharedJoint().getY());
    }
    else if(re.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_Y) {
      getSrvDraw().drawLine(di, ds, re.getSharedJoint().getX(), re.getMinBusCoord(), re.getSharedJoint().getX(), 
          re.getMaxBusCoord());
    }
    if(re.getIsSelected()) {
      if(re.getSharedJoint().getTypeJoint() == EJoint2DType.POINT) {
        getSrvDraw().drawRectangle(di, ds,
            re.getSharedJoint().getX() - getSettingsGraphic().getWidthDragRectangle()/2, 
            re.getSharedJoint().getY() - getSettingsGraphic().getWidthDragRectangle()/2, getSettingsGraphic().getWidthDragRectangle(),
            getSettingsGraphic().getWidthDragRectangle(), false);
      }
      else if(re.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_X) {
        getSrvDraw().drawRectangle(di, ds,
            re.getMaxBusCoord() - getSettingsGraphic().getWidthDragRectangle()*2, 
            re.getSharedJoint().getY() - getSettingsGraphic().getWidthDragRectangle()/2, getSettingsGraphic().getWidthDragRectangle(),
            getSettingsGraphic().getWidthDragRectangle(), false);
      }
      else if(re.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_Y) {
        getSrvDraw().drawRectangle(di, ds,
            re.getSharedJoint().getX() - getSettingsGraphic().getWidthDragRectangle()/2, 
            re.getMaxBusCoord() - getSettingsGraphic().getWidthDragRectangle()*2, getSettingsGraphic().getWidthDragRectangle(),
            getSettingsGraphic().getWidthDragRectangle(), false);
      }
      for(SHR shr : re.getShapesRelationship()) {
        getSrvDraw().drawRectangle(di, ds,
            shr.getPointJoint().getX() - getSettingsGraphic().getWidthDragRectangle()/2, 
            shr.getPointJoint().getY() - getSettingsGraphic().getWidthDragRectangle()/2, getSettingsGraphic().getWidthDragRectangle(),
            getSettingsGraphic().getWidthDragRectangle(), false);
      }
    }
  }

  @Override
  public boolean isContainsScreenPoint(RE re, int x, int y) {
    double realX = UtilsGraphMath.toRealX(getSettingsGraphic(), x);
    double realY = UtilsGraphMath.toRealY(getSettingsGraphic(), y);
    for(SHR shr : re.getShapesRelationship()) {
      Point2D sharedJoint = new Point2D();
      if(re.getSharedJoint().getTypeJoint() == EJoint2DType.POINT) {
        sharedJoint.setX(re.getSharedJoint().getX());
        sharedJoint.setY(re.getSharedJoint().getY());
      }
      else if(re.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_X) {
        sharedJoint.setX(shr.getPointJoint().getX());
        sharedJoint.setY(re.getSharedJoint().getY());
      }
      else if(re.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_Y) {
        sharedJoint.setX(re.getSharedJoint().getX());
        sharedJoint.setY(shr.getPointJoint().getY());
      }
      Point2D jointPointEndChangeable = new Point2D(shr.getPointJoint());
      if(UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), sharedJoint.getX(), sharedJoint.getY(), 
          jointPointEndChangeable.getX(), jointPointEndChangeable.getY(), realX, realY)) {
        return true;
      }
      if(re.getIsSelected()) {
        if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), jointPointEndChangeable, x, y)) {
          return true;
        }
        if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), re.getSharedJoint(), x, y)) {
          return true;
        }
      }
    }
    if(re.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_X) {
      if(UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), re.getMinBusCoord(), re.getSharedJoint().getY(), 
          re.getMaxBusCoord(), re.getSharedJoint().getY(), realX, realY)) {
        return true;
      }
      if(re.getIsSelected()) {
        if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), new Point2D(re.getMaxBusCoord() - getSettingsGraphic().getWidthDragRectangle()*1.5,
            re.getSharedJoint().getY()), x, y)) {
          return true;
        }
      }
    }
    else if(re.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_Y) {
      if(UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), re.getSharedJoint().getX(), re.getMinBusCoord(), re.getSharedJoint().getX(), 
          re.getMaxBusCoord(), realX, realY)) {
        return true;
      }
      if(re.getIsSelected()) {
        if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), new Point2D(re.getSharedJoint().getX(), 
            re.getMaxBusCoord() - getSettingsGraphic().getWidthDragRectangle()*1.5), x, y)) {
          return true;
        }
      }
    }
    return false;
  }
}
