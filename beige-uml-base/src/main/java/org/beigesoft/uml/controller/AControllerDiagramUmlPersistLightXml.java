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

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.beigesoft.ui.IPaletteMenu;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.diagram.assembly.IAsmDiagramUmlInteractive;
import org.beigesoft.uml.diagram.assembly.IAsmListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.ui.IGuiMainUml;
import org.beigesoft.util.UtilsMisc;
import org.beigesoft.handler.IConsumer;

/**
 * Controller of interactive making class diagram
 * Cross platform (Swing, Android...)
 * 
 * @author Yury Demidenko
 */
public abstract class AControllerDiagramUmlPersistLightXml<ADUML extends IAsmDiagramUmlInteractive<?, ?, ?, ?, FileAndWriter>, DLI> 
    extends AControllerDiagramUml<ADUML, FileAndWriter, DLI> {//TODO PRI
        
  public AControllerDiagramUmlPersistLightXml(ADUML asmDiagramUml,
      IPaletteMenu paletteDiagram, 
      IGuiMainUml<?, ?, ?, FileAndWriter, DLI> guiMainUml) {
    super(asmDiagramUml, paletteDiagram, guiMainUml);
  }

  @Override
  public void openDiagram(FileAndWriter pi) {
    try {
      getAsmDiagramUml().openDiagram(pi);
      getAsmDiagramUml().getDiagramUml().setDescription(getAsmDiagramUml().getPersistInstrument().getFile().getName());
    } catch (Exception e) {
      getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), e.getMessage(), "Exception!");
      e.printStackTrace();
    }
  }
  
  @Override
  public void clearContent() {
    super.clearContent();
    if(getAsmDiagramUml().getPersistInstrument() != null) {
      getAsmDiagramUml().getPersistInstrument().setFile(null);
      getAsmDiagramUml().getDiagramUml().setDescription("?");
    }
  }

  @Override
  public void newDiagram() {
    try {
      FileAndWriter pi = new FileAndWriter();
      getAsmDiagramUml().newDiagramUml(pi);
    } catch (Exception e) {
      getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), e.getMessage(), "Exception!");
      e.printStackTrace();
    }
  }
  
  @Override
  public void saveDiagram() {
    if(getAsmDiagramUml().getIsEmpty() || !getAsmDiagramUml().getIsChanged()) {
      return;
    }
    try {
      Set<String> result = new HashSet<String>();
      for(IAsmListElementsUml<? extends IAsmElementUmlInteractive<?, ?, ?, ?>, ?, ?, ?, ?, ?> aleu : getAsmDiagramUml().getAssembliesListElementsUml()) {
        for(IAsmElementUmlInteractive<?, ?, ?, ?> aeu :aleu.getListElementsUml()) {
          aeu.validate(result);
          if(result.size() > 0) {
            String msg = "";
            for(String errStr : result) {
              msg += errStr + "\n";
            }
            getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), msg, "Error!");
            getAsmDiagramUml().makeElementSelected(aeu);
            aeu.startEdit();
            return;
          }
        }
      }
      if(getAsmDiagramUml().getPersistInstrument().getFile() == null) {
        final String path = getGuiMainUml().getSelectedFolderPathProjectTree();
        if(path == null) {
          getGuiSrvs().getSrvDialog().warningMessage(getGuiMainUml().getDialogInstrument(), getGuiSrvs().getSrvI18n().getMsg("please_choose_folder_first_msg"),
              getGuiSrvs().getSrvI18n().getMsg("warning")+"!");
          return;
        }
        getGuiSrvs().getSrvDialog().showAndGetString(getGuiMainUml().getDialogInstrument(),  
            getGuiSrvs().getSrvI18n().getMsg("enter_file_name"),"", new IConsumer<String>() {
              
              @Override
              public void consume(String fileName) {
                if(fileName != null && fileName.trim().length() > 0) {
                  File file = new File(path + File.separator + fileName.trim()+"."+getDiagramFileExtention());
                  if(!file.exists()) {
                    try {
                      UtilsMisc.createFile(file);
                      getAsmDiagramUml().getDiagramUml().setDescription(file.getName());
                      getAsmDiagramUml().getPersistInstrument().setFile(file);
                      getGuiMainUml().addFileIntoSelectedFolderProjectTree(file);
                      getAsmDiagramUml().saveDiagram();
                    } catch (Exception e) {
                      e.printStackTrace();
                      getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), e.getMessage(), "Error!");
                      return;
                    }
                  }
                  else {
                    getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), getGuiSrvs().getSrvI18n().getMsg("file_exist_already"), "Error!");
                  }
                }
              }
            });
        return;
      }
      if(!getAsmDiagramUml().getPersistInstrument().getFile().exists()) {
        try {
          UtilsMisc.createFile(getAsmDiagramUml().getPersistInstrument().getFile());          
        } catch (Exception e) {
          e.printStackTrace();
          getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), e.getMessage(), "Error!");
          return;
        }
        getGuiMainUml().addFileIntoSelectedFolderProjectTree(getAsmDiagramUml().getPersistInstrument().getFile());
      }
      getAsmDiagramUml().saveDiagram();
      return;
    } catch (Exception e) {
      getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), e.getMessage(), "Error!");
      e.printStackTrace();
    }
  }
  
  /**
   * e.g. dcl.xml for diagram class
   * @return diagram file extension
   */
  protected abstract String getDiagramFileExtention();
}
