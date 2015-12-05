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

import org.beigesoft.handler.IHandlerConfirm;
import org.beigesoft.ui.IPaletteMenu;
import org.beigesoft.ui.IEventMotion;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.diagram.assembly.IAsmDiagramUmlInteractive;
import org.beigesoft.uml.model.ECommands;
import org.beigesoft.uml.ui.IGuiMainUml;

/**
 * Controller of interactive making class diagram
 * Cross platform (Swing, Android...)
 * 
 * @author Yury Demidenko
 */
public abstract class AControllerDiagramUml<ADUML extends IAsmDiagramUmlInteractive<?, ?, ?, ?, PRI>, PRI, DLI> 
    implements IControllerDiagramUml<ADUML, PRI> {
        
  private final IPaletteMenu paletteDiagram;

  private boolean itWasDragging;
  
  private final ADUML asmDiagramUml;
    
  private final IGuiMainUml<?, ?, ?, PRI, DLI> guiMainUml;
  
  public AControllerDiagramUml(ADUML asmDiagramUml, IPaletteMenu paletteDiagram,
      IGuiMainUml<?, ?, ?, PRI, DLI> guiMainUml) {
    this.paletteDiagram = paletteDiagram;
    this.guiMainUml = guiMainUml;
    this.asmDiagramUml = asmDiagramUml;
  }
  
  @Override
  public void clearContent() {
    try {
      asmDiagramUml.clearContent();
    } catch (Exception e) {
      guiMainUml.getGuiSrvs().getSrvDialog().errorMessage(guiMainUml.getDialogInstrument(), e.getMessage(), "Exception!");
      e.printStackTrace();
    }
  }
  
  @Override
  public void deleteSelectedElement() {
    if(asmDiagramUml.getIsEmpty() || asmDiagramUml.getSelectedElementUml() == null)
      return;
    guiMainUml.getGuiSrvs().getSrvDialog().confirm(guiMainUml.getDialogInstrument(), guiMainUml.getGuiSrvs().getSrvI18n().getMsg("delete_selected_element")+"?",
        guiMainUml.getGuiSrvs().getSrvI18n().getMsg("are_you_sure"), new IHandlerConfirm() {
          
          @Override
          public void handleConfirm(boolean isConfirmed) {
            if(isConfirmed) {
              try {
                asmDiagramUml.deleteSelectedElement();
              } catch (Exception e) {
                guiMainUml.getGuiSrvs().getSrvDialog().errorMessage(guiMainUml.getDialogInstrument(), e.getMessage(), "Exception!");
                e.printStackTrace();
              }
            }
          }
        });
  }
  
  @Override
  public void pressedAt(IEventMotion e) {
    asmDiagramUml.registerMousePressedAt(e.getX(), e.getY());
  }

  @Override
  public void releasedAt(IEventMotion e) {
    try {
      if(itWasDragging) {
        itWasDragging = false;
        asmDiagramUml.registerMouseReleasedAfterDragging();
      }
      else if(e.isIntentEdit() && !e.isConsumed()) {
        e.consume();
        asmDiagramUml.tryToEditSelectedElement(e.getX(), e.getY());
      }
      else if(getPaletteDiagram().getSelectedCommand() == ECommands.SELECT.toString()) {
        getAsmDiagramUml().selectAt(e.getX(), e.getY());
      }
      else if(getPaletteDiagram().getSelectedCommand() == ECommands.COMMENT.toString()) {
        getAsmDiagramUml().createCommentUmlAt(e.getX(), e.getY());
        getPaletteDiagram().clearSelectedCommand();
      }
      else if(getPaletteDiagram().getSelectedCommand() == ECommands.TEXT.toString()) {
        getAsmDiagramUml().createTextUmlAt(e.getX(), e.getY());
        getPaletteDiagram().clearSelectedCommand();
      }
      else if(getPaletteDiagram().getSelectedCommand() == ECommands.FRAME.toString()) {
        getAsmDiagramUml().createFrameAt(e.getX(), e.getY());
        getPaletteDiagram().clearSelectedCommand();
      }
      else if(getPaletteDiagram().getSelectedCommand() == ECommands.RECTANGLE.toString()) {
        getAsmDiagramUml().createRectangleAt(e.getX(), e.getY());
        getPaletteDiagram().clearSelectedCommand();
      }
      else if(getPaletteDiagram().getSelectedCommand() == ECommands.LINE.toString()) {
        getAsmDiagramUml().createLineAt(e.getX(), e.getY());
        getPaletteDiagram().clearSelectedCommand();
      }
      else {
        makeSelectedCommand(e);
      }
    } catch (Exception e2) {
      getGuiSrvs().getSrvDialog().errorMessage(guiMainUml.getDialogInstrument(), e2.getMessage(), "Exception!");
      getPaletteDiagram().clearSelectedCommand();
      e2.printStackTrace();
    } 
  }
  
  protected abstract boolean makeSelectedCommand(IEventMotion e) throws Exception;

  /**
   * Move an UML element
   * or change path of a relation
   */
  @Override
  public void dragged(IEventMotion e) {
    if(paletteDiagram.getSelectedCommand() == ECommands.SELECT.toString()) {
      if(!itWasDragging) {
        itWasDragging = asmDiagramUml.tryToDragging(e.getX(), e.getY());
      }
      else {
        asmDiagramUml.tryToDragging(e.getX(), e.getY());
      }
    }
  }

  @Override
  public boolean getIsItWasDragging() {
    return itWasDragging;
  }

  @Override
  public IPaletteMenu getPaletteDiagram() {
    return paletteDiagram;
  }

  public IContainerSrvsGui<DLI> getGuiSrvs() {
    return guiMainUml.getGuiSrvs();
  }

  public ADUML getAsmDiagramUml() {
    return asmDiagramUml;
  }

  public IGuiMainUml<?, ?, ?, PRI, DLI> getGuiMainUml() {
    return guiMainUml;
  }
}
