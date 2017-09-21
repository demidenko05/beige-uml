package org.beigesoft.uml.service.interactive;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.CombinedFragment;
import org.beigesoft.uml.service.graphic.ASrvGraphicShapeUml;

public class SrvInteractiveCombinedFragment<SH extends CombinedFragment, DRI, SD extends ISettingsDraw, DLI> 
    extends SrvInteractiveFragment<SH, DRI, SD, DLI> {

  public SrvInteractiveCombinedFragment(
      ASrvGraphicShapeUml<SH, DRI, SD> srvGraphicShape, IFactoryEditorElementUml<SH, DLI> factoryEditorElementUml) {
    super(srvGraphicShape, factoryEditorElementUml);
  }
  
  @Override
  public boolean move(SH ge, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenY - screenWasY) >= getSrvGraphicShape().getSettingsGraphic().getDraggingStep() 
        || Math.abs(screenY - screenWasY) >= getSrvGraphicShape().getSettingsGraphic().getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(getSrvGraphicShape().getSettingsGraphic(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(getSrvGraphicShape().getSettingsGraphic(), screenY - screenWasY);
      double widthDragRectangle = getSrvGraphicShape().getSettingsGraphic().getWidthDragRectangle();
      for(int i=0; i<ge.getTracesY().size(); i++) {
        double y = ge.getPointStart().getY() + ge.getTracesY().get(i);
        if(UtilsGraphMath.dragRentangleContainsOf(getSrvGraphicShape().getSettingsGraphic(), 
            new Point2D(ge.getPointStart().getX() + ge.getWidth() - widthDragRectangle*1.5, 
                y), screenWasX, screenWasY)) {
          if(ge.getTracesY().get(i) + deltaY > 0 &&
              ge.getTracesY().get(i) + deltaY < ge.getHeight()) {
            ge.getTracesY().set(i, ge.getTracesY().get(i) + deltaY);
            
          }
          return true;
        }
      }
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
