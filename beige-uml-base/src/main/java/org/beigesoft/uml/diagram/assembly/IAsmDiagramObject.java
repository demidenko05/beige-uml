package org.beigesoft.uml.diagram.assembly;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.diagram.pojo.DiagramUml;

public interface IAsmDiagramObject<DUML extends DiagramUml, DRI, SD extends ISettingsDraw, IMG, PRI> 
    extends IAsmDiagramUmlInteractive<DUML, DRI, SD, IMG, PRI>,
    IContainerShapesForTie {

  public void createInstanceAt(int screenX, int screenY)
      throws Exception;

  public boolean tryCreateAssociationAt(int screenX, int screenY);
}
