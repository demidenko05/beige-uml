package org.beigesoft.uml.diagram.assembly;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.container.IContainerAsmLifeLinesFull;
import org.beigesoft.uml.container.IContainerAsmMessagesFull;
import org.beigesoft.uml.container.IContainerInteractionUses;
import org.beigesoft.uml.diagram.pojo.DiagramUml;

public interface IAsmDiagramSequence<DUML extends DiagramUml, DRI, SD extends ISettingsDraw, IMG, PRI> 
    extends IAsmDiagramUmlInteractive<DUML, DRI, SD, IMG, PRI>, IContainerAsmLifeLinesFull, IContainerInteractionUses,
    IContainerAsmMessagesFull {

  public void tryToCreateLifeLineAt(int screenX, int screenY)
      throws Exception;

  public void tryToCreateMessageAt(int screenX, int screenY)
      throws Exception;

  public void tryToCreateCoregionMessagesAt(int screenX, int screenY)
      throws Exception;

  public void createStateInvContAt(int screenX, int screenY)
      throws Exception;

  public void createInteractionUseAt(int screenX, int screenY);

  public void createCombinedFragmentAt(int screenX, int screenY);
}
