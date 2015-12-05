package org.beigesoft.uml.diagram.assembly;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.model.ERelationshipKind;

public interface IAsmDiagramUseCase<DUML extends DiagramUml, DRI, SD extends ISettingsDraw, IMG, PRI> 
    extends IAsmDiagramUmlInteractive<DUML, DRI, SD, IMG, PRI>, IContainerShapesForTie {

  public void createUseCaseAt(int screenX, int screenY);

  public void createActorAt(int screenX, int screenY);

  public boolean tryCreateRelationshipBinVarAt(int screenX, int screenY, ERelationshipKind relationshipKind);

  public void createUseCaseExtendedAt(int screenX, int screenY);
}
