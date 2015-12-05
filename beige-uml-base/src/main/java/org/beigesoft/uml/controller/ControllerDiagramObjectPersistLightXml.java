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
import org.beigesoft.uml.diagram.assembly.AsmDiagramObject;
import org.beigesoft.uml.diagram.assembly.IAsmDiagramObject;
import org.beigesoft.uml.model.ECommands;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.ui.IGuiMainUml;

/**
 * Supply interactive making object diagram
 * 
 * @author Yury Demidenko
 */
public class ControllerDiagramObjectPersistLightXml<DLI> extends 
    AControllerDiagramUmlPersistLightXml<IAsmDiagramObject<?, ?, ?, ?, FileAndWriter>, DLI> {
            
  public ControllerDiagramObjectPersistLightXml(
      AsmDiagramObject<?, ?, ?, FileAndWriter, DLI> asmDiagramUml,
      IPaletteMenu paletteDiagram, 
      IGuiMainUml<?, ?, ?, FileAndWriter, DLI> guiMainUml) {
    super(asmDiagramUml, paletteDiagram, guiMainUml);
  }
    
  protected boolean makeSelectedCommand(IEventMotion te) throws Exception {
    if(getPaletteDiagram().getSelectedCommand() == ECommands.ASSOCIATION_SIMPLE.toString()) {
      getAsmDiagramUml().tryCreateAssociationAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.INSTANCE.toString()) {
      getAsmDiagramUml().createInstanceAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.SELECT.toString()) {
      getAsmDiagramUml().selectAt(te.getX(), te.getY());
      return true;
    }
    return false;
  }

  @Override
  protected String getDiagramFileExtention() {
    return SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_OBJECT;
  }

  @Override
  public void editDiagramProperties() {
    // stub
  }
}
