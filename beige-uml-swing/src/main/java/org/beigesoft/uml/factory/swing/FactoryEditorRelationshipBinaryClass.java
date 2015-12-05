package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.container.IContainerShapesFullInteractive;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.service.edit.SrvEditRelationshipBinary;
import org.beigesoft.uml.ui.EditorRelationshipBinaryClass;
import org.beigesoft.uml.ui.swing.AsmEditorRelationshipBinaryClass;

public class FactoryEditorRelationshipBinaryClass implements IFactoryEditorElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame> {

  private final ISrvI18n srvI18n;

  private final ISrvDialog<Frame> srvDialog;

  private final SettingsGraphicUml settingsGraphic;
  
  private final Frame frameMain;
  
  private SrvEditRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvEditRelationshipSimpleUml;
  
  private AsmEditorRelationshipBinaryClass<EditorRelationshipBinaryClass<Frame, ActionEvent, ClassUml>, ClassUml> asmEditorRelationshipBinClass;

  private IObserverModelChanged observerElementChanged;

  private IContainerShapesFullInteractive<ClassFull<ClassUml>, ClassUml> containerShapes;

  public FactoryEditorRelationshipBinaryClass(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
    this.frameMain = frameMain;
  }

  @Override
  public AsmEditorRelationshipBinaryClass<EditorRelationshipBinaryClass<Frame, ActionEvent, ClassUml>, ClassUml> lazyGetEditorElementUml() {
    if(asmEditorRelationshipBinClass == null) {
      EditorRelationshipBinaryClass<Frame, ActionEvent, ClassUml> editorRelBinClass = 
          new EditorRelationshipBinaryClass<Frame, ActionEvent, ClassUml>(frameMain, 
              lazyGetSrvEditElementUml(), lazyGetSrvEditElementUml().getSrvI18n().getMsg("relationship_binary_class"));
      asmEditorRelationshipBinClass = new AsmEditorRelationshipBinaryClass<EditorRelationshipBinaryClass<Frame, ActionEvent, ClassUml>, ClassUml>(frameMain, editorRelBinClass);
      asmEditorRelationshipBinClass.doPostConstruct();
      editorRelBinClass.addObserverModelChanged(observerElementChanged);
      editorRelBinClass.setContainerClassesFull(containerShapes);
    }
    return asmEditorRelationshipBinClass;
  }

  @Override
  public SrvEditRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> lazyGetSrvEditElementUml() {
    if(srvEditRelationshipSimpleUml == null) {
      srvEditRelationshipSimpleUml = new SrvEditRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(srvI18n, srvDialog, settingsGraphic);
    }
    return srvEditRelationshipSimpleUml;
  }

  public ISrvI18n getI18nSrv() {
    return srvI18n;
  }

  public ISrvDialog<Frame> getDialogSrv() {
    return srvDialog;
  }

  public SettingsGraphicUml getGraphicSettings() {
    return settingsGraphic;
  }

  public Frame getFrameMain() {
    return frameMain;
  }

  public IObserverModelChanged getObserverElementChanged() {
    return observerElementChanged;
  }

  public void setObserverElementChanged(
      IObserverModelChanged observerElementChanged) {
    this.observerElementChanged = observerElementChanged;
  }

  public IContainerShapesFullInteractive<ClassFull<ClassUml>, ClassUml> getContainerShapes() {
    return containerShapes;
  }

  public void setContainerShapes(
      IContainerShapesFullInteractive<ClassFull<ClassUml>, ClassUml> containerShapes) {
    this.containerShapes = containerShapes;
  }
}
