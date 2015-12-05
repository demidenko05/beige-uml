package org.beigesoft.uml.service.interactive;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.ShapeRelationshipVarious;
import org.beigesoft.uml.pojo.ShapeUml;

public class SrvInteractiveShapeVariousFull<SHF extends ShapeFullVarious<SH>, DRI, SD extends ISettingsDraw, DLI, SH extends ShapeUml> 
    extends SrvInteractiveShapeFull<SHF, DRI, SD, DLI, SH> {
  
  public SrvInteractiveShapeVariousFull(IFactoryEditorElementUml<SHF, DLI> factoryEditorShapeUmlFull,
      SrvInteractiveShapeUml<SH, DRI, SD> srvInteractiveShapeUml) {
    super(factoryEditorShapeUmlFull, srvInteractiveShapeUml);
  }

  @Override
  public boolean move(SHF eu, int screenWasX, int screenWasY,
      int screenX, int screenY) {
    if(super.move(eu, screenWasX, screenWasY, screenX, screenY)) {
      double deltaX = UtilsGraphMath.toRealLenghtX(getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic(), screenY - screenWasY);
      for(ShapeRelationshipVarious relAcSt : eu.getRelationshipsVariousStart()) {
        relAcSt.getPointJoint().setX(relAcSt.getPointJoint().getX() + deltaX);
        relAcSt.getPointJoint().setY(relAcSt.getPointJoint().getY() + deltaY);
      }
      for(ShapeRelationshipVarious relAcSt : eu.getRelationshipsVariousEnd()) {
        relAcSt.getPointJoint().setX(relAcSt.getPointJoint().getX() + deltaX);
        relAcSt.getPointJoint().setY(relAcSt.getPointJoint().getY() + deltaY);
      }
      return true;
    }
    return false;
  }
}
