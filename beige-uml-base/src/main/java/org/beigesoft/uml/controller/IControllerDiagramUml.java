/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.controller;

import org.beigesoft.ui.IPaletteMenu;
import org.beigesoft.ui.IEventMotion;
import org.beigesoft.uml.diagram.assembly.IAsmDiagramUmlInteractive;

/**
 * Abstraction of a interactive diagram maker
 * 
 * @author Yury Demidenko
 */
public interface IControllerDiagramUml<ADUML extends IAsmDiagramUmlInteractive<?, ?, ?, ?, PRI>, PRI> {
  
  public void newDiagram();

  public void saveDiagram();

  public void openDiagram(PRI pi);

  public void clearContent();

  public void deleteSelectedElement();
  
  /**
   * use in active diagram
   */
  public ADUML getAsmDiagramUml();

  public void pressedAt(IEventMotion e);
  
  public void releasedAt(IEventMotion e);

  public void dragged(IEventMotion e);

  public boolean getIsItWasDragging();

  public IPaletteMenu getPaletteDiagram();

  public void editDiagramProperties();
}
