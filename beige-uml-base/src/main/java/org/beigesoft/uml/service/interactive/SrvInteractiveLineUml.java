package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.service.graphic.SrvGraphicLineUml;

public class SrvInteractiveLineUml<LN extends LineUml, DRI, DS extends ISettingsDraw, DLI> 
    implements ISrvInteractiveGraphicElement<LN> {
  
  private final SrvGraphicLineUml<LN, DRI, DS> srvGraphicLineUml;
  
  private final IFactoryEditorElementUml<LN, DLI> factoryEditorLineUml;
  
  public SrvInteractiveLineUml(SrvGraphicLineUml<LN, DRI, DS> srvGraphicLineUml,
      IFactoryEditorElementUml<LN, DLI> factoryEditorLineUml) {
     this.srvGraphicLineUml = srvGraphicLineUml;
     this.factoryEditorLineUml = factoryEditorLineUml;
  }

  @Override
  public void move(LN ge, double deltaX, double deltaY) {
    ge.getPoint1().setX(ge.getPoint1().getX() + deltaX);
    ge.getPoint1().setY(ge.getPoint1().getY() + deltaY);
    ge.getPoint2().setX(ge.getPoint2().getX() + deltaX);
    ge.getPoint2().setY(ge.getPoint2().getY() + deltaY);
  }

  @Override
  public boolean move(LN ge, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenX - screenWasX) >= srvGraphicLineUml.getSettingsGraphic().getDraggingStep() 
        || Math.abs(screenY - screenWasY) >= srvGraphicLineUml.getSettingsGraphic().getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(srvGraphicLineUml.getSettingsGraphic(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(srvGraphicLineUml.getSettingsGraphic(), screenY - screenWasY);
      if(UtilsGraphMath.dragRentangleContainsOf(srvGraphicLineUml.getSettingsGraphic(), ge.getPoint1(), screenWasX, screenWasY)) {
        ge.getPoint1().setX(ge.getPoint1().getX() + deltaX);
        ge.getPoint1().setY(ge.getPoint1().getY() + deltaY);
        return true;
      }
      if(UtilsGraphMath.dragRentangleContainsOf(srvGraphicLineUml.getSettingsGraphic(), ge.getPoint2(), screenWasX, screenWasY)) {
        ge.getPoint2().setX(ge.getPoint2().getX() + deltaX);
        ge.getPoint2().setY(ge.getPoint2().getY() + deltaY);
        return true;
      }
    }
    return false;
  }

  @Override
  public void startEdit(LN ge) {
    factoryEditorLineUml.lazyGetEditorElementUml().startEdit(ge);
  }

  @Override
  public void validate(LN eu, Set<String> result) {
    factoryEditorLineUml.lazyGetSrvEditElementUml().validate(eu, result);
  }

  @Override
  public boolean handleStopDraggingAt(LN ge, int mouseWasAtX, int mouseWasAtY) {
    // nothing
    return false;
  }

  @Override
  public boolean resize(LN eu, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    // nothing
    return false;
  }

  @Override
  public boolean isContainsScreenPointForManipulate(LN eu, int screenX, int screenY) {
    return srvGraphicLineUml.isContainsScreenPoint(eu, screenX, screenY);
  }

  //SGS:
  public SrvGraphicLineUml<LN, DRI, DS> getSrvGraphicLineUml() {
    return srvGraphicLineUml;
  }
  
  public IFactoryEditorElementUml<LN, DLI> getFactoryEditorLineUml() {
    return factoryEditorLineUml;
  }
}
