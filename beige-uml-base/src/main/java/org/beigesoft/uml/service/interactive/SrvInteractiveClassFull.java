package org.beigesoft.uml.service.interactive;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.ClassRelationFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.service.UtilsRectangleRelationship;

public class SrvInteractiveClassFull<DRI, SD extends ISettingsDraw, DLI, CL extends ClassUml> 
    extends SrvInteractiveShapeFull<ClassFull<CL>, DRI, SD, DLI, CL> {
    
  public SrvInteractiveClassFull(IFactoryEditorElementUml<ClassFull<CL>, DLI> editorFactory, 
      SrvInteractiveShapeUml<CL, DRI, SD> interactiveShapeUmlSrv) {
    super(editorFactory, interactiveShapeUmlSrv);
  }

  @Override
  public boolean move(ClassFull<CL> ge, int screenWasX,
      int screenWasY, int screenX, int screenY) {
    if(Math.abs(screenX - screenWasX) >= getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic().getDraggingStep()
        || Math.abs(screenY - screenWasY) >= getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic().getDraggingStep()) {
      if(super.move(ge, screenWasX, screenWasY, screenX, screenY)) {
        double deltaX = UtilsGraphMath.toRealLenghtX(getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic(), screenX - screenWasX);
        double deltaY = UtilsGraphMath.toRealLenghtX(getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic(), screenY - screenWasY);
        for(RelationshipSelf<?, ?, ?> selfRelation : ge.getRelationshipsSelf()) {
          selfRelation.getSharedJoint().setX(selfRelation.getSharedJoint().getX() + deltaX);
          selfRelation.getSharedJoint().setY(selfRelation.getSharedJoint().getY() + deltaY);
          UtilsRectangleRelationship.evalPointsJointAndShared(selfRelation);
        }
        for(ClassRelationFull<CL> clRelFull : ge.getRelationshipsBinary()) {
          UtilsRectangleRelationship.evalPointJoint(clRelFull.getClassRelationship());
        }
        for(RectangleRelationship<ClassFull<CL>,CL> clRel : ge.getClassRelationsPoly()) {
          UtilsRectangleRelationship.evalPointJoint(clRel);
        }
        return true;
      }
    }
    return false;
  }
}
