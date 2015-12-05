package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.service.edit.SrvEditLineUml;
import org.beigesoft.uml.ui.EditorLineUml;
import org.beigesoft.uml.ui.swing.AsmEditorLineUml;

public class FactoryEditorLineUml implements IFactoryEditorElementUml<LineUml, Frame> {

  private final ISrvI18n i18nSrv;

  private final ISrvDialog<Frame> dialogSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private final Frame frameMain;

  private SrvEditLineUml<LineUml, Frame> editLineUmlSrv;

  private AsmEditorLineUml<LineUml, EditorLineUml<LineUml, Frame, ActionEvent>> asmEditorLineUml;

  private IObserverModelChanged observerLineUmlChanged;

  private IContainerShapesForTie containerShapesUmlForTie;

  public FactoryEditorLineUml(ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv,
      SettingsGraphicUml graphicSettings, Frame frameMain) {
    this.i18nSrv = i18nSrv;
    this.dialogSrv = dialogSrv;
    this.graphicSettings = graphicSettings;
    this.frameMain = frameMain;
  }

  @Override
  public synchronized AsmEditorLineUml<LineUml, EditorLineUml<LineUml, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(asmEditorLineUml == null) {
      EditorLineUml<LineUml, Frame, ActionEvent> editorLineUml = 
          new EditorLineUml<LineUml, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml(), lazyGetSrvEditElementUml().getSrvI18n().getMsg("line"));
      asmEditorLineUml = new AsmEditorLineUml<LineUml, EditorLineUml<LineUml, Frame, ActionEvent>>(frameMain, editorLineUml);
      asmEditorLineUml.doPostConstruct();
      editorLineUml.addObserverModelChanged(observerLineUmlChanged);
     }
    return asmEditorLineUml;
  }

  @Override
  public synchronized SrvEditLineUml<LineUml, Frame> lazyGetSrvEditElementUml() {
    if(editLineUmlSrv == null) {
      editLineUmlSrv = new SrvEditLineUml<LineUml, Frame>(i18nSrv, dialogSrv, graphicSettings);
    }
    return editLineUmlSrv;
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

  public IObserverModelChanged getObserverLineUmlChanged() {
    return observerLineUmlChanged;
  }

  public void setObserverLineUmlChanged(
      IObserverModelChanged observerLineUmlChanged) {
    this.observerLineUmlChanged = observerLineUmlChanged;
  }

  public IContainerShapesForTie getContainerShapesUmlForTie() {
    return containerShapesUmlForTie;
  }

  public void setContainerShapesUmlForTie(
      IContainerShapesForTie containerShapesUmlForTie) {
    this.containerShapesUmlForTie = containerShapesUmlForTie;
  }
}
