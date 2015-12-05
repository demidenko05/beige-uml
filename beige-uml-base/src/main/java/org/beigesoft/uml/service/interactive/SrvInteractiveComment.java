package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.CommentRelationship;
import org.beigesoft.uml.service.UtilsRectangleRelationship;
import org.beigesoft.uml.service.graphic.SrvGraphicComment;

public class SrvInteractiveComment<CM extends CommentUml, DRI, SD extends ISettingsDraw, DLI> 
    extends SrvInteractiveShapeUml<CM, DRI, SD> {
  
  private final IFactoryEditorElementUml<CM, DLI> factoryEditorElementUml;
  
  public SrvInteractiveComment(SrvGraphicComment<CM, DRI, SD> graphicSrv,
      IFactoryEditorElementUml<CM, DLI> factoryEditorElementUml) {
    super(graphicSrv);
    this.factoryEditorElementUml = factoryEditorElementUml;
  }
  
  @Override
  public void move(CM ge, double deltaX, double deltaY) {
    super.move(ge, deltaX, deltaY);
    for(CommentRelationship rel : ge.getRelationships()) {
      rel.getPointEnd().setX(rel.getPointEnd().getX() + deltaX);
      rel.getPointEnd().setY(rel.getPointEnd().getY() + deltaY);
      rel.getPointJoint().setX(rel.getPointJoint().getX() + deltaX);
      rel.getPointJoint().setY(rel.getPointJoint().getY() + deltaY);
    }
  }


  @Override
  public boolean move(CM ge, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenX - screenWasX) >= getSrvGraphicShape().getSettingsGraphic().getDraggingStep() 
        || Math.abs(screenY - screenWasY) >= getSrvGraphicShape().getSettingsGraphic().getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(getSrvGraphicShape().getSettingsGraphic(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(getSrvGraphicShape().getSettingsGraphic(), screenY - screenWasY);
      for(CommentRelationship relation : ge.getRelationships()) {
        Point2D pointEnd = relation.getPointEnd();
        if(UtilsGraphMath.dragRentangleContainsOf(getSrvGraphicShape().getSettingsGraphic(), pointEnd, screenWasX, screenWasY)) {
          pointEnd.setX(pointEnd.getX() + deltaX);
          pointEnd.setY(pointEnd.getY() + deltaY);
          return true;
        }
        if(UtilsGraphMath.dragRentangleContainsOf(getSrvGraphicShape().getSettingsGraphic(), relation.getPointJoint(),
            screenWasX, screenWasY)) {
          UtilsRectangleRelationship.movePointJoint(relation, (SettingsGraphicUml) getSrvGraphicShape().getSettingsGraphic(), deltaX, deltaY);
          return true;
        }
      }
      Point2D pointResize = new Point2D(ge.getPointStart().getX() + ge.getWidth(), 
          ge.getPointStart().getY() + ge.getHeight());
      if(UtilsGraphMath.dragRentangleContainsOf(getSrvGraphicShape().getSettingsGraphic(), pointResize , screenWasX, screenWasY)) {
        ge.setWidth(ge.getWidth() + deltaX);
        ge.setHeight(ge.getHeight() + deltaY);
        for(CommentRelationship relation : ge.getRelationships()) {
          UtilsRectangleRelationship.evalPointJoint(relation);
        }
        return true;
      }
      if(super.move(ge, screenWasX, screenWasY, screenX, screenY)) {
        for(CommentRelationship relation : ge.getRelationships()) {
          UtilsRectangleRelationship.evalPointJoint(relation);
        }
        return true;
      }
    }
    return false;
   }

  @Override
  public void startEdit(CM ge) {
    factoryEditorElementUml.lazyGetEditorElementUml().startEdit(ge);
  }

  @Override
  public void validate(CM eu, Set<String> result) {
    factoryEditorElementUml.lazyGetSrvEditElementUml().validate(eu, result);
  }

  public IFactoryEditorElementUml<CM, DLI> getFactoryEditorElementUml() {
    return factoryEditorElementUml;
  }
}
