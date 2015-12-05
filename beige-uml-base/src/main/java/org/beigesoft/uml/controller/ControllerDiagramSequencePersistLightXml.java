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
import org.beigesoft.uml.diagram.assembly.AsmDiagramSequence;
import org.beigesoft.uml.diagram.assembly.IAsmDiagramSequence;
import org.beigesoft.uml.model.ECommands;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.ui.IGuiMainUml;

/**
 * Supply interactive making object diagram
 * 
 * @author Yury Demidenko
 */
public class ControllerDiagramSequencePersistLightXml<DLI> extends 
    AControllerDiagramUmlPersistLightXml<IAsmDiagramSequence<?, ?, ?, ?, FileAndWriter>, DLI> {
            
  public ControllerDiagramSequencePersistLightXml(
      AsmDiagramSequence<?, ?, ?, FileAndWriter, DLI> asmDiagramUml,
      IPaletteMenu paletteDiagram, 
      IGuiMainUml<?, ?, ?, FileAndWriter, DLI> guiMainUml) {
    super(asmDiagramUml, paletteDiagram, guiMainUml);
  }
    
  protected boolean makeSelectedCommand(IEventMotion te) throws Exception {
    if(getPaletteDiagram().getSelectedCommand() == ECommands.LIFELINE.toString()) {
      getAsmDiagramUml().tryToCreateLifeLineAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    if(getPaletteDiagram().getSelectedCommand() == ECommands.MESSAGE.toString()) {
      getAsmDiagramUml().tryToCreateMessageAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    if(getPaletteDiagram().getSelectedCommand() == ECommands.COREGION_MESSAGES.toString()) {
      getAsmDiagramUml().tryToCreateCoregionMessagesAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    if(getPaletteDiagram().getSelectedCommand() == ECommands.STATEINVCONTIN.toString()) {
      getAsmDiagramUml().createStateInvContAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    if(getPaletteDiagram().getSelectedCommand() == ECommands.INTERACTIONUSE.toString()) {
      getAsmDiagramUml().createInteractionUseAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    if(getPaletteDiagram().getSelectedCommand() == ECommands.COMBINEDFRAGMENT.toString()) {
      getAsmDiagramUml().createCombinedFragmentAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    return false;
  }

  @Override
  protected String getDiagramFileExtention() {
    return SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_SEQUENCE;
  }

  @Override
  public void editDiagramProperties() {
    // stub
  }
}
