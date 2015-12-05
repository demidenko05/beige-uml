package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.model.IShapeUml;
import org.beigesoft.uml.service.graphic.ASrvGraphicShapeUml;

public class SrvInteractiveShapeUml<SH extends IShapeUml, DRI, DS extends ISettingsDraw> 
    implements ISrvInteractiveGraphicElement<SH> {

  private final ASrvGraphicShapeUml<SH, DRI, DS> srvGraphicShape; 

  public SrvInteractiveShapeUml(ASrvGraphicShapeUml<SH, DRI, DS> srvGraphicShape) {
    this.srvGraphicShape = srvGraphicShape;
  }

  @Override
  public void move(SH ge, double deltaX, double deltaY) {
    ge.getPointStart().setX(ge.getPointStart().getX() + deltaX);
    ge.getPointStart().setY(ge.getPointStart().getY() + deltaY);
  }

  @Override
  public boolean move(SH ge, int screenWasX, int screenWasY,
      int screenX, int screenY) {
    if(Math.abs(screenX - screenWasX) >= srvGraphicShape.getSettingsGraphic().getDraggingStep() 
        || Math.abs(screenY - screenWasY) >= srvGraphicShape.getSettingsGraphic().getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(srvGraphicShape.getSettingsGraphic(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(srvGraphicShape.getSettingsGraphic(), screenY - screenWasY);
      if(isContainsScreenPointForManipulate(ge, screenWasX, screenWasY)) {
        ge.getPointStart().setX(ge.getPointStart().getX() + deltaX);
        ge.getPointStart().setY(ge.getPointStart().getY() + deltaY);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean resize(SH eu, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenX - screenWasX) >= srvGraphicShape.getSettingsGraphic().getDraggingStep() 
        || Math.abs(screenY - screenWasY) >= srvGraphicShape.getSettingsGraphic().getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(srvGraphicShape.getSettingsGraphic(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(srvGraphicShape.getSettingsGraphic(), screenY - screenWasY);
      Point2D pointResize = new Point2D(eu.getPointStart().getX() + eu.getWidth(), 
          eu.getPointStart().getY() + eu.getHeight());
      if(UtilsGraphMath.dragRentangleContainsOf(srvGraphicShape.getSettingsGraphic(), pointResize , screenWasX, screenWasY)) {
        eu.setIsAdjustMinimumSize(false);
        eu.setWidth(eu.getWidth() + deltaX);
        eu.setHeight(eu.getHeight() + deltaY);
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean handleStopDraggingAt(SH ge, int mouseWasAtX, int mouseWasAtY) {
    // nothing
    return false;
  }

  @Override
  public void startEdit(SH eu) {
    // to override   
  }

  @Override
  public void validate(SH eu, Set<String> result) {
    // to override   
  }

  @Override
  public boolean isContainsScreenPointForManipulate(SH ge, int screenX, int screenY) {
    // TODO Auto-generated method stub
    return srvGraphicShape.isContainsScreenPoint(ge, screenX, screenY);
  }
  
  //SGS:

  public ASrvGraphicShapeUml<SH, DRI, DS> getSrvGraphicShape() {
    return srvGraphicShape;
  }
}
