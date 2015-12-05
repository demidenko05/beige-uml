package org.beigesoft.uml.service.interactive;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.Execution;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.service.graphic.ASrvGraphicShapeUml;

public class SrvInteractiveLifeLineFull<M extends LifeLineFull<ShapeUmlWithName>, DRI, SD extends ISettingsDraw, DLI> 
    extends SrvInteractiveShapeUmlWithEditor<M, DRI, SD, DLI>  {

  public SrvInteractiveLifeLineFull(ASrvGraphicShapeUml<M, DRI, SD> graphicShapeSrv,
      IFactoryEditorElementUml<M, DLI> factoryEditorElementUml) {
    super(graphicShapeSrv, factoryEditorElementUml);
  }

  @Override
  public boolean move(M ge, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(super.move(ge, screenWasX, screenWasY, screenX, screenY)) {
      return true;
    }
    if(Math.abs(screenY - screenWasY) >= getSrvGraphicShape().getSettingsGraphic().getDraggingStep()) {
      double deltaY = UtilsGraphMath.toRealLenghtY(getSrvGraphicShape().getSettingsGraphic(), screenY - screenWasY);
      double widthDragRect = getSrvGraphicShape().getSettingsGraphic().getWidthDragRectangle();
      if(ge.getDestructionOccurenceY() != null) {
        Point2D point = new Point2D(ge.getPointStart().getX() + ge.getWidth()/2, 
            ge.getDestructionOccurenceY());
        if(UtilsGraphMath.dragRentangleContainsOf(getSrvGraphicShape().getSettingsGraphic(), point, screenWasX, screenWasY)) {
          double rezY =ge.getDestructionOccurenceY() + deltaY; 
          if(rezY > widthDragRect && rezY < ge.getAsmFrameFull().getElementUml().getContainer().getPointStart().getY() +
              ge.getAsmFrameFull().getElementUml().getContainer().getHeight() - widthDragRect) {
            ge.setDestructionOccurenceY(rezY);
          }
          return true;
        }
      }
      for(Execution exec : ge.getExecutions()) {
        double yStart = ge.getPointStart().getY() + ge.getHeight() + exec.getStartY();
        Point2D pointStart = new Point2D(ge.getPointStart().getX() + ge.getWidth()/2, 
            yStart);
        if(UtilsGraphMath.dragRentangleContainsOf(getSrvGraphicShape().getSettingsGraphic(), pointStart, screenWasX, screenWasY)) {
          double rezY =exec.getStartY() + deltaY; 
          if(rezY > widthDragRect && rezY < exec.getEndY() - widthDragRect) {
            boolean isNotCollisions = true;
            for(Execution exec2 : ge.getExecutions()) {
              if(exec != exec2 && exec2.getStartY() < exec.getStartY() &&
                  exec2.getEndY() + widthDragRect > rezY) {
                isNotCollisions = false;
                break;
              }
            }
            if(isNotCollisions) {
              exec.setStartY(rezY);
            }
          }
          return true;
        }
        double yEnd = ge.getPointStart().getY() + ge.getHeight() + exec.getEndY();
        Point2D pointEnd = new Point2D(ge.getPointStart().getX() + ge.getWidth()/2, 
            yEnd);
        if(UtilsGraphMath.dragRentangleContainsOf(getSrvGraphicShape().getSettingsGraphic(), pointEnd, screenWasX, screenWasY)) {
          double rezY =exec.getEndY() + deltaY; 
          if(rezY > exec.getStartY() + widthDragRect && rezY < ge.getLineEndY() - widthDragRect) {
            boolean isNotCollisions = true;
            for(Execution exec2 : ge.getExecutions()) {
              if(exec != exec2 && exec2.getStartY() > exec.getStartY()
                  && exec2.getStartY() - widthDragRect < rezY) {
                isNotCollisions = false;
                break;
              }
            }
            if(isNotCollisions) {
              exec.setEndY(rezY);
            }
          }
          return true;
        }
      }
    }
    return false;
  }
}
