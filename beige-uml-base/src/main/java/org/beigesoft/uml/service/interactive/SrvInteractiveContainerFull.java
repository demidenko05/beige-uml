package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.model.IContainerUml;

public class SrvInteractiveContainerFull<CNT extends IContainerUml, DRI, SD extends ISettingsDraw, DLI> 
    implements ISrvInteractiveGraphicElement<ContainerFull<CNT>>{

  private final SrvInteractiveShapeUml<CNT, DRI, SD> srvInteractiveContainer;
  
  private final IFactoryEditorElementUml<ContainerFull<CNT>, DLI> factoryEditorContainerFull;
  
  public SrvInteractiveContainerFull(SrvInteractiveShapeUml<CNT, DRI, SD> srvInteractiveContainer, 
      IFactoryEditorElementUml<ContainerFull<CNT>, DLI> factoryEditorContainerFull) {
    this.srvInteractiveContainer = srvInteractiveContainer;
    this.factoryEditorContainerFull = factoryEditorContainerFull;
  }

  @Override
  public void move(ContainerFull<CNT> eu, double deltaX, double deltaY) {
    srvInteractiveContainer.move(eu.getContainer(), deltaX, deltaY);
  }

  @Override
  public boolean move(ContainerFull<CNT> eu, int screenWasX, int screenWasY,
      int screenX, int screenY) {
    if(srvInteractiveContainer.move(eu.getContainer(), screenWasX, screenWasY, screenX, screenY)) {
      double deltaX = UtilsGraphMath.toRealLenghtX(srvInteractiveContainer.getSrvGraphicShape().getSettingsGraphic(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(srvInteractiveContainer.getSrvGraphicShape().getSettingsGraphic(), screenY - screenWasY);
      for(IAsmElementUmlInteractive<?, ?, ?, ?> elm : eu.getElements()) {
        elm.move(deltaX, deltaY);
      }
      return true;
    }
    return false;
  }

  @Override
  public boolean resize(ContainerFull<CNT> eu, int screenWasX, int screenWasY,
      int screenX, int screenY) {
    return srvInteractiveContainer.resize(eu.getContainer(), screenWasX, screenWasY, screenX, screenY);
  }

  @Override
  public void startEdit(ContainerFull<CNT> eu) {
    factoryEditorContainerFull.lazyGetEditorElementUml().startEdit(eu);
  }

  @Override
  public void validate(ContainerFull<CNT> eu, Set<String> result) {
    factoryEditorContainerFull.lazyGetSrvEditElementUml().validate(eu, result);
  }

  @Override
  public boolean handleStopDraggingAt(ContainerFull<CNT> eu, int mouseWasAtX,
      int mouseWasAtY) {
    return srvInteractiveContainer.handleStopDraggingAt(eu.getContainer(), mouseWasAtX, mouseWasAtY);
  }

  @Override
  public boolean isContainsScreenPointForManipulate(ContainerFull<CNT> eu,
      int screenX, int screenY) {
    return srvInteractiveContainer.isContainsScreenPointForManipulate(eu.getContainer(), screenX, screenY);
  }

  //SGS:
  public SrvInteractiveShapeUml<CNT, DRI, SD> getSrvInteractiveContainer() {
    return srvInteractiveContainer;
  }

  public IFactoryEditorElementUml<ContainerFull<CNT>, DLI> getFactoryEditorContainerFull() {
    return factoryEditorContainerFull;
  }
}
