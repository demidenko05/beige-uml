package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.model.ITextUml;
import org.beigesoft.uml.pojo.ShapeUml;

public class SrvInteractiveShapeFull<SHF extends ShapeFull<SH>, DRI, SD extends ISettingsDraw, DLI, SH extends ShapeUml> 
    implements ISrvInteractiveGraphicElement<SHF> {

  private final SrvInteractiveShapeUml<SH, DRI, SD> srvInteractiveShapeUml;
  
  private final IFactoryEditorElementUml<SHF, DLI> factoryEditorShapeUmlFull;
  
  public SrvInteractiveShapeFull(IFactoryEditorElementUml<SHF, DLI> factoryEditorShapeUmlFull,
      SrvInteractiveShapeUml<SH, DRI, SD> srvInteractiveShapeUml) {
    this.factoryEditorShapeUmlFull = factoryEditorShapeUmlFull;
    this.srvInteractiveShapeUml = srvInteractiveShapeUml;
  }

  @Override
  public void move(SHF eu, double deltaX, double deltaY) {
    srvInteractiveShapeUml.move(eu.getShape(), deltaX, deltaY);
  }

  @Override
  public boolean move(SHF eu, int screenWasX, int screenWasY,
      int screenX, int screenY) {
    if(srvInteractiveShapeUml.move(eu.getShape(), screenWasX, screenWasY, screenX, screenY)) {
      double deltaX = UtilsGraphMath.toRealLenghtX(srvInteractiveShapeUml.getSrvGraphicShape().getSettingsGraphic(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(srvInteractiveShapeUml.getSrvGraphicShape().getSettingsGraphic(), screenY - screenWasY);
      for(ITextUml text : eu.getTextsTied()) {
        text.getPointStart().setX(text.getPointStart().getX() + deltaX);
        text.getPointStart().setY(text.getPointStart().getY() + deltaY);
      }
      return true;
    }
    return false;
  }

  @Override
  public boolean resize(SHF eu, int screenWasX, int screenWasY,
      int screenX, int screenY) {
    return srvInteractiveShapeUml.resize(eu.getShape(), screenWasX, screenWasY, screenX, screenY);
  }

  @Override
  public void startEdit(SHF eu) {
    factoryEditorShapeUmlFull.lazyGetEditorElementUml().startEdit(eu);
  }

  @Override
  public void validate(SHF eu, Set<String> result) {
    factoryEditorShapeUmlFull.lazyGetSrvEditElementUml().validate(eu, result);
  }

  @Override
  public boolean handleStopDraggingAt(SHF eu, int mouseWasAtX,
      int mouseWasAtY) {
    return srvInteractiveShapeUml.handleStopDraggingAt(eu.getShape(), mouseWasAtX, mouseWasAtY);
  }

  @Override
  public boolean isContainsScreenPointForManipulate(SHF eu, int screenX, int screenY) {
    return srvInteractiveShapeUml.isContainsScreenPointForManipulate(eu.getShape(), screenX, screenY);
  }

  public SrvInteractiveShapeUml<SH, DRI, SD> getSrvInteractiveShapeUml() {
    return srvInteractiveShapeUml;
  }
}
