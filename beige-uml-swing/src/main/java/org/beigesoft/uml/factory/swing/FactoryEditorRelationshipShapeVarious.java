package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.container.IContainerShapesFullVariousInteractive;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.service.edit.SrvEditRelationshipBinaryVarious;
import org.beigesoft.uml.ui.EditorRelationshipBinaryVarious;
import org.beigesoft.uml.ui.swing.AsmEditorRelationshipBinaryVarious;

public class FactoryEditorRelationshipShapeVarious implements IFactoryEditorElementUml<RelationshipBinaryVarious, Frame> {

  private final ISrvI18n srvI18n;

  private final ISrvDialog<Frame> srvDialog;

  private final SettingsGraphicUml settingsGraphic;
  
  private final Frame frameMain;
  
  private SrvEditRelationshipBinaryVarious<RelationshipBinaryVarious, Frame> srvEditRelationshipSimpleUml;
  
  private AsmEditorRelationshipBinaryVarious<RelationshipBinaryVarious, EditorRelationshipBinaryVarious<RelationshipBinaryVarious, Frame, ActionEvent>> asmEditorRelationshipBinVar;

  private IObserverModelChanged observerElementChanged;

  private IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> containerShapes;

  public FactoryEditorRelationshipShapeVarious(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
    this.frameMain = frameMain;
  }

  @Override
  public AsmEditorRelationshipBinaryVarious<RelationshipBinaryVarious, EditorRelationshipBinaryVarious<RelationshipBinaryVarious, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(asmEditorRelationshipBinVar == null) {
      EditorRelationshipBinaryVarious<RelationshipBinaryVarious, Frame, ActionEvent> editorRelBinVar = new EditorRelationshipBinaryVarious<RelationshipBinaryVarious, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml(), lazyGetSrvEditElementUml().getSrvI18n().getMsg("relationship_binary"));
      asmEditorRelationshipBinVar = new AsmEditorRelationshipBinaryVarious<RelationshipBinaryVarious, EditorRelationshipBinaryVarious<RelationshipBinaryVarious, Frame, ActionEvent>>(frameMain, editorRelBinVar);
      asmEditorRelationshipBinVar.doPostConstruct();
      editorRelBinVar.addObserverModelChanged(observerElementChanged);
      editorRelBinVar.setContainerShapesFullVarious(containerShapes);
    }
    return asmEditorRelationshipBinVar;
  }

  @Override
  public SrvEditRelationshipBinaryVarious<RelationshipBinaryVarious, Frame> lazyGetSrvEditElementUml() {
    if(srvEditRelationshipSimpleUml == null) {
      srvEditRelationshipSimpleUml = new SrvEditRelationshipBinaryVarious<RelationshipBinaryVarious, Frame>(srvI18n, srvDialog, settingsGraphic);
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

  public IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> getContainerShapes() {
    return containerShapes;
  }

  public void setContainerShapes(
      IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> containerShapes) {
    this.containerShapes = containerShapes;
  }
}
