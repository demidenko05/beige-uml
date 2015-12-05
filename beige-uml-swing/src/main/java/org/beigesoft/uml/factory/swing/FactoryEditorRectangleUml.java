package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.service.edit.SrvEditRectangleUml;
import org.beigesoft.uml.ui.EditorRectangleUml;
import org.beigesoft.uml.ui.swing.AsmEditorRectangleUml;

public class FactoryEditorRectangleUml implements IFactoryEditorElementUml<RectangleUml, Frame> {

  private final ISrvI18n i18nSrv;

  private final ISrvDialog<Frame> dialogSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private final Frame frameMain;

  private SrvEditRectangleUml<RectangleUml, Frame> editRectangleUmlSrv;

  private AsmEditorRectangleUml<RectangleUml, EditorRectangleUml<RectangleUml, Frame, ActionEvent>> asmEditorRectangleUml;

  private IObserverModelChanged observerRectangleUmlChanged;

  private IContainerShapesForTie containerShapesUmlForTie;

  public FactoryEditorRectangleUml(ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv,
      SettingsGraphicUml graphicSettings, Frame frameMain) {
    this.i18nSrv = i18nSrv;
    this.dialogSrv = dialogSrv;
    this.graphicSettings = graphicSettings;
    this.frameMain = frameMain;
  }

  @Override
  public synchronized AsmEditorRectangleUml<RectangleUml, EditorRectangleUml<RectangleUml, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(asmEditorRectangleUml == null) {
      EditorRectangleUml<RectangleUml, Frame, ActionEvent> editorRectangleUml = 
          new EditorRectangleUml<RectangleUml, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml(), lazyGetSrvEditElementUml().getSrvI18n().getMsg("Rectangle"));
      asmEditorRectangleUml = new AsmEditorRectangleUml<RectangleUml, EditorRectangleUml<RectangleUml, Frame, ActionEvent>>(frameMain, editorRectangleUml);
      asmEditorRectangleUml.doPostConstruct();
      editorRectangleUml.addObserverModelChanged(observerRectangleUmlChanged);
     }
    return asmEditorRectangleUml;
  }

  @Override
  public synchronized SrvEditRectangleUml<RectangleUml, Frame> lazyGetSrvEditElementUml() {
    if(editRectangleUmlSrv == null) {
      editRectangleUmlSrv = new SrvEditRectangleUml<RectangleUml, Frame>(i18nSrv, dialogSrv, graphicSettings);
    }
    return editRectangleUmlSrv;
  }

  public ISrvI18n getI18nSrv() {
    return i18nSrv;
  }

  public ISrvDialog<Frame> getDialogSrv() {
    return dialogSrv;
  }

  public SettingsGraphicUml getGraphicSettings() {
    return graphicSettings;
  }

  public Frame getFrameMain() {
    return frameMain;
  }

  public IObserverModelChanged getObserverRectangleUmlChanged() {
    return observerRectangleUmlChanged;
  }

  public void setObserverRectangleUmlChanged(
      IObserverModelChanged observerRectangleUmlChanged) {
    this.observerRectangleUmlChanged = observerRectangleUmlChanged;
  }

  public IContainerShapesForTie getContainerShapesUmlForTie() {
    return containerShapesUmlForTie;
  }

  public void setContainerShapesUmlForTie(
      IContainerShapesForTie containerShapesUmlForTie) {
    this.containerShapesUmlForTie = containerShapesUmlForTie;
  }
}
