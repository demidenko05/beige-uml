package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.edit.SrvEditTextUml;
import org.beigesoft.uml.ui.EditorText;
import org.beigesoft.uml.ui.swing.AsmEditorText;

public class FactoryEditorText implements IFactoryEditorElementUml<TextUml, Frame> {

  private final ISrvI18n i18nSrv;

  private final ISrvDialog<Frame> dialogSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private final Frame frameMain;

  private SrvEditTextUml<TextUml, Frame> editTextUmlSrv;

  private AsmEditorText<TextUml, EditorText<TextUml, Frame, ActionEvent>> asmEditorText;

  private IObserverModelChanged observerTextUmlChanged;

  private IContainerShapesForTie containerShapesUmlForTie;

  public FactoryEditorText(ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv,
      SettingsGraphicUml graphicSettings, Frame frameMain) {
    super();
    this.i18nSrv = i18nSrv;
    this.dialogSrv = dialogSrv;
    this.graphicSettings = graphicSettings;
    this.frameMain = frameMain;
  }

  @Override
  public synchronized AsmEditorText<TextUml, EditorText<TextUml, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(asmEditorText == null) {
      EditorText<TextUml, Frame, ActionEvent> editorText = 
          new EditorText<TextUml, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml(), lazyGetSrvEditElementUml().getSrvI18n().getMsg("text"));
      asmEditorText = new AsmEditorText<TextUml, EditorText<TextUml, Frame, ActionEvent>>(frameMain, editorText);
      asmEditorText.doPostConstruct();
      editorText.addObserverModelChanged(observerTextUmlChanged);
      editorText.setContainerShapesUmlForTie(containerShapesUmlForTie);
    }
    return asmEditorText;
  }

  @Override
  public synchronized SrvEditTextUml<TextUml, Frame> lazyGetSrvEditElementUml() {
    if(editTextUmlSrv == null) {
      editTextUmlSrv = new SrvEditTextUml<TextUml, Frame>(i18nSrv, dialogSrv, graphicSettings);
    }
    return editTextUmlSrv;
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

  public IObserverModelChanged getObserverTextUmlChanged() {
    return observerTextUmlChanged;
  }

  public void setObserverTextUmlChanged(
      IObserverModelChanged observerTextUmlChanged) {
    this.observerTextUmlChanged = observerTextUmlChanged;
  }

  public IContainerShapesForTie getContainerShapesUmlForTie() {
    return containerShapesUmlForTie;
  }

  public void setContainerShapesUmlForTie(
      IContainerShapesForTie containerShapesUmlForTie) {
    this.containerShapesUmlForTie = containerShapesUmlForTie;
  }
}
