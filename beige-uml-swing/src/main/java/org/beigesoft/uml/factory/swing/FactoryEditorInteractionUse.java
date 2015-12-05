package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.service.edit.SrvEditInteractionUse;
import org.beigesoft.uml.ui.EditorInteractionUse;
import org.beigesoft.uml.ui.swing.AsmEditorInteractionUse;

public class FactoryEditorInteractionUse implements IFactoryEditorElementUml<InteractionUse, Frame> {

  private final ISrvI18n i18nSrv;

  private final ISrvDialog<Frame> dialogSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private final Frame frameMain;

  private SrvEditInteractionUse<InteractionUse, Frame> editInteractionUseSrv;

  private AsmEditorInteractionUse<InteractionUse, EditorInteractionUse<InteractionUse, Frame, ActionEvent>> asmEditorInteractionUse;

  private IObserverModelChanged observerInteractionUseChanged;

  private IContainerShapesForTie containerShapesUmlForTie;

  public FactoryEditorInteractionUse(ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv,
      SettingsGraphicUml graphicSettings, Frame frameMain) {
    super();
    this.i18nSrv = i18nSrv;
    this.dialogSrv = dialogSrv;
    this.graphicSettings = graphicSettings;
    this.frameMain = frameMain;
  }

  @Override
  public synchronized AsmEditorInteractionUse<InteractionUse, EditorInteractionUse<InteractionUse, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(asmEditorInteractionUse == null) {
      EditorInteractionUse<InteractionUse, Frame, ActionEvent> editorInteractionUse = 
          new EditorInteractionUse<InteractionUse, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml(), lazyGetSrvEditElementUml().getSrvI18n().getMsg("InteractionUse"));
      asmEditorInteractionUse = new AsmEditorInteractionUse<InteractionUse, EditorInteractionUse<InteractionUse, Frame, ActionEvent>>(frameMain, editorInteractionUse);
      asmEditorInteractionUse.doPostConstruct();
      editorInteractionUse.addObserverModelChanged(observerInteractionUseChanged);
    }
    return asmEditorInteractionUse;
  }

  @Override
  public synchronized SrvEditInteractionUse<InteractionUse, Frame> lazyGetSrvEditElementUml() {
    if(editInteractionUseSrv == null) {
      editInteractionUseSrv = new SrvEditInteractionUse<InteractionUse, Frame>(i18nSrv, dialogSrv, graphicSettings);
    }
    return editInteractionUseSrv;
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

  public IObserverModelChanged getObserverInteractionUseChanged() {
    return observerInteractionUseChanged;
  }

  public void setObserverInteractionUseChanged(
      IObserverModelChanged observerInteractionUseChanged) {
    this.observerInteractionUseChanged = observerInteractionUseChanged;
  }

  public IContainerShapesForTie getContainerShapesUmlForTie() {
    return containerShapesUmlForTie;
  }

  public void setContainerShapesUmlForTie(
      IContainerShapesForTie containerShapesUmlForTie) {
    this.containerShapesUmlForTie = containerShapesUmlForTie;
  }
}
