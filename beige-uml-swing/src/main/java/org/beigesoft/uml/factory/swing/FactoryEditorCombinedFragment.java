package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.CombinedFragment;
import org.beigesoft.uml.service.edit.SrvEditCombinedFragment;
import org.beigesoft.uml.ui.EditorCombinedFragment;
import org.beigesoft.uml.ui.swing.AsmEditorCombinedFragment;

public class FactoryEditorCombinedFragment implements IFactoryEditorElementUml<CombinedFragment, Frame> {

  private final ISrvI18n i18nSrv;

  private final ISrvDialog<Frame> dialogSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private final Frame frameMain;

  private SrvEditCombinedFragment<CombinedFragment, Frame> editCombinedFragmentSrv;

  private AsmEditorCombinedFragment<CombinedFragment, EditorCombinedFragment<CombinedFragment, Frame, ActionEvent>> asmEditorCombinedFragment;

  private IObserverModelChanged observerCombinedFragmentChanged;

  private IContainerShapesForTie containerShapesUmlForTie;

  public FactoryEditorCombinedFragment(ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv,
      SettingsGraphicUml graphicSettings, Frame frameMain) {
    super();
    this.i18nSrv = i18nSrv;
    this.dialogSrv = dialogSrv;
    this.graphicSettings = graphicSettings;
    this.frameMain = frameMain;
  }

  @Override
  public synchronized AsmEditorCombinedFragment<CombinedFragment, EditorCombinedFragment<CombinedFragment, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(asmEditorCombinedFragment == null) {
      EditorCombinedFragment<CombinedFragment, Frame, ActionEvent> editorCombinedFragment = 
          new EditorCombinedFragment<CombinedFragment, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml(), lazyGetSrvEditElementUml().getSrvI18n().getMsg("CombinedFragment"));
      asmEditorCombinedFragment = new AsmEditorCombinedFragment<CombinedFragment, EditorCombinedFragment<CombinedFragment, Frame, ActionEvent>>(frameMain, editorCombinedFragment);
      asmEditorCombinedFragment.doPostConstruct();
      editorCombinedFragment.addObserverModelChanged(observerCombinedFragmentChanged);
    }
    return asmEditorCombinedFragment;
  }

  @Override
  public synchronized SrvEditCombinedFragment<CombinedFragment, Frame> lazyGetSrvEditElementUml() {
    if(editCombinedFragmentSrv == null) {
      editCombinedFragmentSrv = new SrvEditCombinedFragment<CombinedFragment, Frame>(i18nSrv, dialogSrv, graphicSettings);
    }
    return editCombinedFragmentSrv;
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

  public IObserverModelChanged getObserverCombinedFragmentChanged() {
    return observerCombinedFragmentChanged;
  }

  public void setObserverCombinedFragmentChanged(
      IObserverModelChanged observerCombinedFragmentChanged) {
    this.observerCombinedFragmentChanged = observerCombinedFragmentChanged;
  }

  public IContainerShapesForTie getContainerShapesUmlForTie() {
    return containerShapesUmlForTie;
  }

  public void setContainerShapesUmlForTie(
      IContainerShapesForTie containerShapesUmlForTie) {
    this.containerShapesUmlForTie = containerShapesUmlForTie;
  }
}
