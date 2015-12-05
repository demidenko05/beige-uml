package org.beigesoft.uml.service.interactive;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.pojo.UseCase;
import org.beigesoft.uml.service.graphic.ASrvGraphicShapeUml;

public class SrvInteractiveUseCase<UC extends UseCase, DRI, SD extends ISettingsDraw>
    extends SrvInteractiveShapeUml<UC, DRI, SD> {
  
  public SrvInteractiveUseCase(ASrvGraphicShapeUml<UC, DRI, SD> graphicShapeSrv) {
    super(graphicShapeSrv);
  }

  @Override
  public boolean move(UC ge, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenX - screenWasX) >= getSrvGraphicShape().getSettingsGraphic().getDraggingStep() || 
        Math.abs(screenY - screenWasY) >= getSrvGraphicShape().getSettingsGraphic().getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(getSrvGraphicShape().getSettingsGraphic(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(getSrvGraphicShape().getSettingsGraphic(), screenY - screenWasY);
      Point2D pointResize = new Point2D(ge.getPointStart().getX() + ge.getWidth(), 
          ge.getPointStart().getY() + ge.getHeight());
      if(UtilsGraphMath.dragRentangleContainsOf(getSrvGraphicShape().getSettingsGraphic(), pointResize , screenWasX, screenWasY)) {
        ge.setWidth(ge.getWidth() + deltaX);
        ge.setHeight(ge.getHeight() + deltaY);
        return true;
      }
      if(super.move(ge, screenWasX, screenWasY, screenX, screenY)) {
        return true;
      }
    }
    return false;
  }
}
