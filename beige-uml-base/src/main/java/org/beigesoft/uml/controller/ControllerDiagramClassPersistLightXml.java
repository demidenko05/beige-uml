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
import java.io.IOException;
import java.util.UUID;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.handler.IHandlerConfirm;
import org.beigesoft.model.NodeTree;
import org.beigesoft.ui.IPaletteMenu;
import org.beigesoft.ui.IEventMotion;
import org.beigesoft.ui.pojo.NodeJavaClass;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.diagram.assembly.AsmDiagramClassInteractive;
import org.beigesoft.uml.diagram.pojo.DiagramClass;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.model.ECommands;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramClass;
import org.beigesoft.uml.ui.IGuiMainUml;

/**
 * Supply interactive making class diagram
 * 
 * @author Yury Demidenko
 */
public class ControllerDiagramClassPersistLightXml<DLI> 
    extends AControllerDiagramUmlPersistLightXml<AsmDiagramClassInteractive<?, ?, ?, FileAndWriter, DLI>, DLI> 
    implements IControllerDiagramClass<AsmDiagramClassInteractive<?, ?, ?, FileAndWriter, DLI>, FileAndWriter> {
            
  private final IEditor<DiagramClass> editorPropertiesDiagram;
  
  public ControllerDiagramClassPersistLightXml(
      AsmDiagramClassInteractive<?, ?, ?, FileAndWriter, DLI> asmDiagramUml,
      IPaletteMenu paletteDiagram, 
      IGuiMainUml<?, ?, ?, FileAndWriter, DLI> guiMainUml, IEditor<DiagramClass> asmEditorPropertiesDiagram) {
    super(asmDiagramUml, paletteDiagram, guiMainUml);
    this.editorPropertiesDiagram = asmEditorPropertiesDiagram;
  }
  
  @Override
  public void newDiagramFromJavaSource() {
    getGuiMainUml().lazyGetAndPrepareJavaClassChooser().showAndChoose(new IConsumer<NodeTree<UUID,NodeJavaClass>>() {
      
      @Override
      public void consume(NodeTree<UUID, NodeJavaClass> javaClassNode) {
        if(javaClassNode == null || javaClassNode.getValue().getIsFolder()) {
          return;
        }
        final String className = javaClassNode.getValue().getJavaClass();
        if(className != null) {
          final File diagramFile = new File(getGuiMainUml().getAsmProjectUml().getProjectUml().getProjectPath()+ File.separator +
              className.replace(".", File.separator)+"."+getDiagramFileExtention());
          if(diagramFile.exists()) {
            getGuiSrvs().getSrvDialog().confirm(getGuiMainUml().getDialogInstrument(), getGuiSrvs().getSrvI18n().getMsg("diagram_exist_overwrite"), "?", 
                new IHandlerConfirm() {
                  
                  @Override
                  public void handleConfirm(boolean isConfirmed) {
                    if(isConfirmed) {
                      makeNewDiagram(className, diagramFile);
                    }
                  }
                });
          }
          else {
            try {
              if(diagramFile.getParentFile() != null) {
                diagramFile.getParentFile().mkdirs();
              }
              if(!diagramFile.createNewFile()) {
                getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), getGuiSrvs().getSrvI18n().getMsg("can_not_create_file"), "Error!");
              }
            } catch (IOException e) {
              getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), e.getMessage(), "Error!");
              e.printStackTrace();
            }
          }
          makeNewDiagram(className, diagramFile);
        }
      }
    });
  }

  protected void makeNewDiagram(String className, File diagramFile) {
    FileAndWriter pi = new FileAndWriter();
    pi.setFile(diagramFile);
    getAsmDiagramUml().newDiagramUml(pi);
    getAsmDiagramUml().getDiagramUml().setDescription(diagramFile.getName());
    try {
      Class<?> clazz = getGuiMainUml().getAsmProjectUml().getClassLoader().loadClass(className);
      getAsmDiagramUml().addClassesUmlForClass(clazz);
      getAsmDiagramUml().saveDiagram();
      getGuiMainUml().refreshGui();
      getGuiMainUml().refreshProjectTreeAndShowFile(diagramFile);
    } catch (Exception e) {
      getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), e.getMessage(), "Exception!");
      e.printStackTrace();
    }
  }
  
  @Override
  public void addClassFromJavaSource() {
    if(getAsmDiagramUml().getIsEmpty() ||
        getGuiMainUml().getAsmProjectUml().getProjectUml().getJavaSourceFilePath() == null) {
      return;
    }
    getGuiMainUml().lazyGetAndPrepareJavaClassChooser().showAndChoose(new IConsumer<NodeTree<UUID,NodeJavaClass>>() {
      
      @Override
      public void consume(NodeTree<UUID, NodeJavaClass> javaClassNode) {
        if(javaClassNode == null || javaClassNode.getValue().getIsFolder()) {
          return;
        }
        String className = javaClassNode.getValue().getJavaClass();
        if(className != null) {
          try {
            Class<?> clazz = getGuiMainUml().getAsmProjectUml().getClassLoader().loadClass(className);
            getAsmDiagramUml().addClassUmlForClass(clazz);
          } catch (Exception e) {
            getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), e.getMessage(), "Exception!");
            e.printStackTrace();
          }     
        }
      }
    });
  }
  
  @Override
  public void rearrange() {
    getGuiSrvs().getSrvDialog().confirm(getGuiMainUml().getDialogInstrument(), getGuiSrvs().getSrvI18n().getMsg("rearrange") + "?", 
        getGuiSrvs().getSrvI18n().getMsg("warning"), new IHandlerConfirm() {
          
          @Override
          public void handleConfirm(boolean isConfirmed) {
            try {
              getAsmDiagramUml().rearrange();
            } catch (Exception e) {
              getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), e.getMessage(), "Exception!");
              e.printStackTrace();
            }
          }
        });
  }

  @Override
  public void adjustRelationsFor90Degree() {
    getGuiSrvs().getSrvDialog().confirm(getGuiMainUml().getDialogInstrument(), getGuiSrvs().getSrvI18n().getMsg("adjust_relations_90") + "?", 
        getGuiSrvs().getSrvI18n().getMsg("warning"), new IHandlerConfirm() {
          
          @Override
          public void handleConfirm(boolean isConfirmed) {
            if(isConfirmed) {
              try {
                getAsmDiagramUml().adjustRelationsFor90Degree();
              } catch (Exception e) {
                getGuiSrvs().getSrvDialog().errorMessage(getGuiMainUml().getDialogInstrument(), e.getMessage(), "Exception!");
                e.printStackTrace();
              }
            }
          }
        }); 
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
      getAsmDiagramUml().tryToCreateRelationshipAt(ERelationshipKind.GENERALIZATION, te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.AGGREGATION.toString()) {
      getAsmDiagramUml().tryToCreateRelationshipAt(ERelationshipKind.AGGREGATION, te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.ASSOCIATION.toString()) {
      getAsmDiagramUml().tryToCreateRelationshipAt(ERelationshipKind.ASSOCIATION, te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.COMPOSITION.toString()) {
      getAsmDiagramUml().tryToCreateRelationshipAt(ERelationshipKind.COMPOSITION, te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.REALIZATION.toString()) {
      getAsmDiagramUml().tryToCreateRelationshipAt(ERelationshipKind.INTERFACE_REALIZATION, te.getX(), te.getY());
      getPaletteDiagram().clearSelectedCommand();
      return true;
    }
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.DEPENDENCY.toString()) {
      getAsmDiagramUml().tryToCreateRelationshipAt(ERelationshipKind.USAGE, te.getX(), te.getY());
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
    else if(getPaletteDiagram().getSelectedCommand() == ECommands.SELECT.toString()) {
      getAsmDiagramUml().selectAt(te.getX(), te.getY());
      return true;
    }
    return false;
  }

  @Override
  protected String getDiagramFileExtention() {
    return SrvSaveXmlDiagramClass.DIAGRAM_FILE_EXTENSION;
  }

  @Override
  public void editDiagramProperties() {
    editorPropertiesDiagram.startEdit(getAsmDiagramUml().getDiagramUml());
  }

  public IEditor<DiagramClass> getAsmEditorPropertiesDiagram() {
    return editorPropertiesDiagram;
  }

  @Override
  public int evalCountClasses() {
    return getAsmDiagramUml().getAsmListAsmClassesFull().getListElementsUml().size();
  }

  @Override
  public int evalCountRelationshipsBinaryClass() {
    return getAsmDiagramUml().getAsmListAsmRelationshipsBinaryClass().getListElementsUml().size();
  }
}
