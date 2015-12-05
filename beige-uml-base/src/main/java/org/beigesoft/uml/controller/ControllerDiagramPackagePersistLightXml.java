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
import org.beigesoft.uml.diagram.assembly.AsmDiagramPackage;
import org.beigesoft.uml.diagram.assembly.IAsmDiagramPackage;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.model.ECommands;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.ui.IGuiMainUml;

/**
 * Supply interactive making package diagram
 * 
 * @author Yury Demidenko
 */
public class ControllerDiagramPackagePersistLightXml<DLI> extends 
    AControllerDiagramUmlPersistLightXml<IAsmDiagramPackage<?, ?, ?, ?, FileAndWriter, ClassUml>, DLI> {
            
  public ControllerDiagramPackagePersistLightXml(
      AsmDiagramPackage<?, ?, ?, FileAndWriter, DLI> asmDiagramUml,
      IPaletteMenu paletteDiagram, 
      IGuiMainUml<?, ?, ?, FileAndWriter, DLI> guiMainUml) {
    super(asmDiagramUml, paletteDiagram, guiMainUml);
  }
    
  protected boolean makeSelectedCommand(IEventMotion te) throws Exception {
    if(getPaletteDiagram().getSelectedCommand() == ECommands.CLASS.toString()) {
      getAsmDiagramUml().createClassAt(EClassKind.CLASS, te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.INTERFACE.toString()) {
      getAsmDiagramUml().createClassAt(EClassKind.INTERFACE, te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.ENUMERATION.toString()) {
      getAsmDiagramUml().createClassAt(EClassKind.ENUM, te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.GENERALIZATION.toString()) {
      getAsmDiagramUml().tryToCreateRelationshipBinaryAt(ERelationshipKind.GENERALIZATION, te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.RELATIONSHIP_SELF.toString()) {
      getAsmDiagramUml().tryToCreateRelationshipSelfAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.RELATIONSHIP_POLY.toString()) {
      getAsmDiagramUml().tryToCreateRelationshipPolyAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.PACKAGE_IMPORT.toString()) {
      getAsmDiagramUml().tryCreatePackageImportAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.PACKAGE.toString()) {
      getAsmDiagramUml().createPackageAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.PACKAGE_MERGE.toString()) {
      getAsmDiagramUml().tryCreatePackageMergeAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.PACKAGE_ACCESS.toString()) {
      getAsmDiagramUml().tryCreatePackageAccessAt(te.getX(), te.getY());
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
    return SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_PACKAGE;
  }

  @Override
  public void editDiagramProperties() {
    // stub
  }
}
