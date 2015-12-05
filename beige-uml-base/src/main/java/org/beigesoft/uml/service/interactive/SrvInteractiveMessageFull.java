package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.service.graphic.SrvGraphicMessageFull;

public class SrvInteractiveMessageFull<MSG extends MessageFull, DRI, DS extends ISettingsDraw, DLI> 
    implements ISrvInteractiveGraphicElement<MSG> {
  
  private final SrvGraphicMessageFull<MSG, DRI, DS> graphicMessageFullSrv;
  
  private final IFactoryEditorElementUml<MSG, DLI> factoryEditorMessageFull;
  
  public SrvInteractiveMessageFull(SrvGraphicMessageFull<MSG, DRI, DS> graphicMessageFullSrv,
      IFactoryEditorElementUml<MSG, DLI> factoryEditorMessageFull) {
     this.graphicMessageFullSrv = graphicMessageFullSrv;
     this.factoryEditorMessageFull = factoryEditorMessageFull;
  }

  @Override
  public void move(MSG ge, double deltaX, double deltaY) {
    ge.setY(ge.getY() + deltaY);
  }

  @Override
  public boolean move(MSG ge, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenY - screenWasY) >= graphicMessageFullSrv.getSettingsGraphic().getDraggingStep()) {
      double deltaY = UtilsGraphMath.toRealLenghtY(graphicMessageFullSrv.getSettingsGraphic(), screenY - screenWasY);
      if(graphicMessageFullSrv.isContainsScreenPoint(ge, screenWasX, screenWasY)) {
        ge.setY(ge.getY() + deltaY);
        return true;
      }
    }
    return false;
  }

  @Override
  public void startEdit(MSG ge) {
    factoryEditorMessageFull.lazyGetEditorElementUml().startEdit(ge);
  }

  @Override
  public void validate(MSG eu, Set<String> result) {
    factoryEditorMessageFull.lazyGetSrvEditElementUml().validate(eu, result);
  }

  @Override
  public boolean handleStopDraggingAt(MSG ge, int mouseWasAtX, int mouseWasAtY) {
    // nothing
    return false;
  }

  @Override
  public boolean resize(MSG eu, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    // nothing
    return false;
  }

  @Override
  public boolean isContainsScreenPointForManipulate(MSG eu, int screenX, int screenY) {
    return graphicMessageFullSrv.isContainsScreenPoint(eu, screenX, screenY);
  }

  //SGS:
  public SrvGraphicMessageFull<MSG, DRI, DS> getGraphicMessageFullSrv() {
    return graphicMessageFullSrv;
  }
  
  public IFactoryEditorElementUml<MSG, DLI> getFactoryEditorMessageFull() {
    return factoryEditorMessageFull;
  }
}
