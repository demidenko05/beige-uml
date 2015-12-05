package org.beigesoft.uml.service.interactive;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.service.graphic.SrvGraphicRectangle;

public class SrvInteractiveRectangle<M extends RectangleUml, DRI, SD extends ISettingsDraw, DLI> 
    extends SrvInteractiveShapeUmlWithEditor<M, DRI, SD, DLI> {

  public SrvInteractiveRectangle(SrvGraphicRectangle<M, DRI, SD> srvGraphicShape,
      IFactoryEditorElementUml<M, DLI> factoryEditorElementUml) {
    super(srvGraphicShape, factoryEditorElementUml);
  }
  
  @Override
  public boolean isContainsScreenPointForManipulate(M ge, int screenX, int screenY) {
    double realX = UtilsGraphMath.toRealX(getSrvGraphicShape().getSettingsGraphic(), screenX);
    double realY = UtilsGraphMath.toRealY(getSrvGraphicShape().getSettingsGraphic(), screenY);
    if(realX >= ge.getPointStart().getX() && realX <= ge.getPointStart().getX() + ge.getWidth()/2 && 
        realY >= ge.getPointStart().getY() && realY <= ge.getPointStart().getY() + getSrvGraphicShape().getSettingsGraphic().getWidthDragRectangle()*2) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvGraphicShape().getSettingsGraphic(), ge.getPointStart().getX(), 
        ge.getPointStart().getY(), ge.getPointStart().getX() + ge.getWidth(), 
        ge.getPointStart().getY(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvGraphicShape().getSettingsGraphic(), ge.getPointStart().getX(), 
        ge.getPointStart().getY() + ge.getHeight(), ge.getPointStart().getX() + ge.getWidth(), 
        ge.getPointStart().getY() + ge.getHeight(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvGraphicShape().getSettingsGraphic(), ge.getPointStart().getX(), 
        ge.getPointStart().getY(), ge.getPointStart().getX(), 
        ge.getPointStart().getY() + ge.getHeight(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvGraphicShape().getSettingsGraphic(), ge.getPointStart().getX() + ge.getWidth(), 
        ge.getPointStart().getY(), ge.getPointStart().getX() + ge.getWidth(), 
        ge.getPointStart().getY() + ge.getHeight(), realX, realY)) {
      return true;
    }
    return false;
  }
}
