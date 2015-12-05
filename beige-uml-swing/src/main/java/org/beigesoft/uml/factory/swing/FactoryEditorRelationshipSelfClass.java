package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.service.edit.SrvEditRectangleRelationship;
import org.beigesoft.uml.service.edit.SrvEditRelationshipSelf;
import org.beigesoft.uml.ui.EditorRelationshipSelf;
import org.beigesoft.uml.ui.swing.AsmEditorRelationshipSelf;

public class FactoryEditorRelationshipSelfClass implements IFactoryEditorElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame> {

  private final ISrvI18n srvI18n;

  private final ISrvDialog<Frame> srvDialog;

  private final SettingsGraphicUml settingsGraphic;
  
  private final Frame frameMain;
  
  private SrvEditRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvEditRelationshipSimpleUml;
  
  private AsmEditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
      EditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
      RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> asmEditorRelationshipBinClass;

  private IObserverModelChanged observerElementChanged;

  public FactoryEditorRelationshipSelfClass(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
    this.frameMain = frameMain;
  }

  @Override
  public AsmEditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  EditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
  RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>  lazyGetEditorElementUml() {
    if(asmEditorRelationshipBinClass == null) {
      EditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> editorRelSelfClass 
      = new EditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,ClassFull<ClassUml>,ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>,ClassUml>, ClassFull<ClassUml>, ClassUml>(frameMain, lazyGetSrvEditElementUml(), getI18nSrv().getMsg("relationship_self"));
      asmEditorRelationshipBinClass = new AsmEditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
          EditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
          RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> (frameMain, editorRelSelfClass);
      asmEditorRelationshipBinClass.doPostConstruct();
      editorRelSelfClass.addObserverModelChanged(observerElementChanged);
    }
    return asmEditorRelationshipBinClass;
  }

  @Override
  public SrvEditRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> lazyGetSrvEditElementUml() {
    if(srvEditRelationshipSimpleUml == null) {
      SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Frame, ClassFull<ClassUml>, ClassUml> srvEditRectRel = new SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, Frame, ClassFull<ClassUml>, ClassUml>(srvI18n, srvDialog, settingsGraphic);
      srvEditRelationshipSimpleUml = new SrvEditRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(srvI18n, srvDialog, settingsGraphic, srvEditRectRel);
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
}
