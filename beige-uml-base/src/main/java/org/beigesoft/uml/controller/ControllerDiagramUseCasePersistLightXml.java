package org.beigesoft.uml.controller;

import org.beigesoft.ui.IPaletteMenu;
import org.beigesoft.ui.IEventMotion;
import org.beigesoft.uml.diagram.assembly.IAsmDiagramUseCase;
import org.beigesoft.uml.model.ECommands;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.ui.IGuiMainUml;

public class ControllerDiagramUseCasePersistLightXml<DLI> extends 
    AControllerDiagramUmlPersistLightXml<IAsmDiagramUseCase<?, ?, ?, ?, FileAndWriter>, DLI> {
  
  public ControllerDiagramUseCasePersistLightXml(
      IAsmDiagramUseCase<?, ?, ?, ?, FileAndWriter> diagramUml,
      IPaletteMenu paletteDiagram,
      IGuiMainUml<?, ?, ?, FileAndWriter, DLI> guiMainUml) {
    super(diagramUml, paletteDiagram, guiMainUml);
  }

  @Override
  protected String getDiagramFileExtention() {
    return SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_USECASE;
  }

  @Override
  protected boolean makeSelectedCommand(IEventMotion te) throws Exception {
    if(getPaletteDiagram().getSelectedCommand() == ECommands.USE_CASE.toString()) {
      getAsmDiagramUml().createUseCaseAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.USE_CASEEXTENDED.toString()) {
      getAsmDiagramUml().createUseCaseExtendedAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.ACTOR.toString()) {
      getAsmDiagramUml().createActorAt(te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.ASSOCIATION_SIMPLE.toString()) {
      if(getAsmDiagramUml().tryCreateRelationshipBinVarAt(te.getX(), te.getY(), ERelationshipKind.ASSOCIATION)) {
        getPaletteDiagram().clearSelectedCommand();
      }
      else {
        String msg = getGuiSrvs().getSrvI18n().getMsg("click_inside_actor_usecase");
        String title = getGuiSrvs().getSrvI18n().getMsg("warning");
        getGuiSrvs().getSrvDialog().warningMessage(getGuiMainUml().getDialogInstrument(), msg, title);
      }
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.GENERALIZATION_SIMPLE.toString()) {
      if(getAsmDiagramUml().tryCreateRelationshipBinVarAt(te.getX(), te.getY(), ERelationshipKind.GENERALIZATION)) {
        getPaletteDiagram().clearSelectedCommand();
      }
      else {
        String msg = getGuiSrvs().getSrvI18n().getMsg("click_inside_actor_usecase");
        String title = getGuiSrvs().getSrvI18n().getMsg("warning");
        getGuiSrvs().getSrvDialog().warningMessage(getGuiMainUml().getDialogInstrument(), msg, title);
      }
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.EXTEND_SIMPLE.toString()) {
      if(getAsmDiagramUml().tryCreateRelationshipBinVarAt(te.getX(), te.getY(), ERelationshipKind.EXTEND)) {
        getPaletteDiagram().clearSelectedCommand();
      }
      else {
        String msg = getGuiSrvs().getSrvI18n().getMsg("click_inside_actor_usecase");
        String title = getGuiSrvs().getSrvI18n().getMsg("warning");
        getGuiSrvs().getSrvDialog().warningMessage(getGuiMainUml().getDialogInstrument(), msg, title);
      }
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.INCLUDE_SIMPLE.toString()) {
      if(getAsmDiagramUml().tryCreateRelationshipBinVarAt(te.getX(), te.getY(), ERelationshipKind.INCLUDE)) {
        getPaletteDiagram().clearSelectedCommand();
      }
      else {
        String msg = getGuiSrvs().getSrvI18n().getMsg("click_inside_actor_usecase");
        String title = getGuiSrvs().getSrvI18n().getMsg("warning");
        getGuiSrvs().getSrvDialog().warningMessage(getGuiMainUml().getDialogInstrument(), msg, title);
      }
      return true;
    }
    return false;
  }

  @Override
  public void editDiagramProperties() {
    // nothing
  }
}
