package org.beigesoft.uml.diagram.assembly;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.container.IContainerAsmFramesFull;
import org.beigesoft.uml.diagram.pojo.DiagramUml;

public interface IAsmDiagramUmlInteractive<DUML extends DiagramUml, DRI, SD extends ISettingsDraw, IMG, PRI>
    extends IAsmDiagramUml<DUML, IAsmElementUmlInteractive<?, DRI, ?, PRI>, DRI, SD, IMG, PRI>,
    IObserverModelChanged, IContainerAsmFramesFull {

  public void tryToEditSelectedElement(int screenX, int screenY);

  public boolean tryToDragging(int screenX, int screenY);

  public void unselectElementIfNeed();

  public void makeElementSelected(IAsmElementUmlInteractive<?, ?, ?, ?> umlElement);

  public void refreshGui();

  public void setIsChanged(boolean isChanged);

  public boolean getIsChanged();

  public void deleteSelectedElement();

  public void registerMouseReleasedAfterDragging();

  public void registerMousePressedAt(int screenX, int screenY);

  public void selectAt(int screenX, int screenY);

  public void createTextUmlAt(int screenX, int screenY);

  public void createCommentUmlAt(int screenX, int screenY);
  
  public void createFrameAt(int screenX, int screenY);

  public void createRectangleAt(int screenX, int screenY);

  public void createLineAt(int screenX, int screenY);

  public IAsmElementUmlInteractive<?, ?, ?, ?> getSelectedElementUml();
}
