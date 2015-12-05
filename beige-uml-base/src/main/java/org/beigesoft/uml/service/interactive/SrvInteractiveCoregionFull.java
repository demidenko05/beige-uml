package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.service.graphic.SrvGraphicCoregionFull;

public class SrvInteractiveCoregionFull<MSG extends CoregionFull, DRI, DS extends ISettingsDraw, DLI> 
    implements ISrvInteractiveGraphicElement<MSG> {
  
  private final SrvGraphicCoregionFull<MSG, DRI, DS> graphicCoregionFullSrv;
  
  private final IFactoryEditorElementUml<MSG, DLI> factoryEditorCoregionFull;
  
  public SrvInteractiveCoregionFull(SrvGraphicCoregionFull<MSG, DRI, DS> graphicCoregionFullSrv,
      IFactoryEditorElementUml<MSG, DLI> factoryEditorCoregionFull) {
     this.graphicCoregionFullSrv = graphicCoregionFullSrv;
     this.factoryEditorCoregionFull = factoryEditorCoregionFull;
  }

  @Override
  public void move(MSG ge, double deltaX, double deltaY) {
    //nothing
  }

  @Override
  public boolean move(MSG ge, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    return false;
  }

  @Override
  public void startEdit(MSG ge) {
    factoryEditorCoregionFull.lazyGetEditorElementUml().startEdit(ge);
  }

  @Override
  public void validate(MSG eu, Set<String> result) {
    factoryEditorCoregionFull.lazyGetSrvEditElementUml().validate(eu, result);
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
    return graphicCoregionFullSrv.isContainsScreenPoint(eu, screenX, screenY);
  }

  //SGS:
  public SrvGraphicCoregionFull<MSG, DRI, DS> getGraphicCoregionFullSrv() {
    return graphicCoregionFullSrv;
  }
  
  public IFactoryEditorElementUml<MSG, DLI> getFactoryEditorCoregionFull() {
    return factoryEditorCoregionFull;
  }
}
