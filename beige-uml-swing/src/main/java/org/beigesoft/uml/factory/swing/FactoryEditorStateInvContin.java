package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.StateInvContin;
import org.beigesoft.uml.service.edit.SrvEditStateInvContin;
import org.beigesoft.uml.ui.EditorStateInvContin;
import org.beigesoft.uml.ui.swing.AsmEditorStateInvContin;

public class FactoryEditorStateInvContin implements IFactoryEditorElementUml<StateInvContin, Frame> {

  private final ISrvI18n i18nSrv;

  private final ISrvDialog<Frame> dialogSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private final Frame frameMain;

  private SrvEditStateInvContin<StateInvContin, Frame> editStateInvContinSrv;

  private AsmEditorStateInvContin<StateInvContin, EditorStateInvContin<StateInvContin, Frame, ActionEvent>> asmEditorStateInvContin;

  private IObserverModelChanged observerStateInvContinChanged;

  private IContainerShapesForTie containerShapesUmlForTie;

  public FactoryEditorStateInvContin(ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv,
      SettingsGraphicUml graphicSettings, Frame frameMain) {
    super();
    this.i18nSrv = i18nSrv;
    this.dialogSrv = dialogSrv;
    this.graphicSettings = graphicSettings;
    this.frameMain = frameMain;
  }

  @Override
  public synchronized AsmEditorStateInvContin<StateInvContin, EditorStateInvContin<StateInvContin, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(asmEditorStateInvContin == null) {
      EditorStateInvContin<StateInvContin, Frame, ActionEvent> editorStateInvContin = 
          new EditorStateInvContin<StateInvContin, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml(), lazyGetSrvEditElementUml().getSrvI18n().getMsg("state_invariant_plus"));
      asmEditorStateInvContin = new AsmEditorStateInvContin<StateInvContin, EditorStateInvContin<StateInvContin, Frame, ActionEvent>>(frameMain, editorStateInvContin);
      asmEditorStateInvContin.doPostConstruct();
      editorStateInvContin.addObserverModelChanged(observerStateInvContinChanged);
    }
    return asmEditorStateInvContin;
  }

  @Override
  public synchronized SrvEditStateInvContin<StateInvContin, Frame> lazyGetSrvEditElementUml() {
    if(editStateInvContinSrv == null) {
      editStateInvContinSrv = new SrvEditStateInvContin<StateInvContin, Frame>(i18nSrv, dialogSrv, graphicSettings);
    }
    return editStateInvContinSrv;
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

  public IObserverModelChanged getObserverStateInvContinChanged() {
    return observerStateInvContinChanged;
  }

  public void setObserverStateInvContinChanged(
      IObserverModelChanged observerStateInvContinChanged) {
    this.observerStateInvContinChanged = observerStateInvContinChanged;
  }

  public IContainerShapesForTie getContainerShapesUmlForTie() {
    return containerShapesUmlForTie;
  }

  public void setContainerShapesUmlForTie(
      IContainerShapesForTie containerShapesUmlForTie) {
    this.containerShapesUmlForTie = containerShapesUmlForTie;
  }
}
