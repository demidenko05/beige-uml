package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.UseCase;
import org.beigesoft.uml.service.edit.SrvEditUseCaseFull;
import org.beigesoft.uml.ui.EditorUseCaseFull;
import org.beigesoft.uml.ui.swing.AsmEditorUseCaseFull;

public class FactoryEditorUseCaseFull implements IFactoryEditorElementUml<ShapeFullVarious<UseCase>, Frame> {

  private final ISrvI18n srvI18n;

  private final ISrvDialog<Frame> srvDialog;

  private final SettingsGraphicUml settingsGraphic;
  
  private final Frame frameMain;
  
  private SrvEditUseCaseFull<UseCase, Frame> srvEditUseCaseUmlFull;
  
  private AsmEditorUseCaseFull<UseCase, EditorUseCaseFull<UseCase, Frame, ActionEvent>> editorUseCaseUml;

  private IObserverModelChanged observerUseCaseChanged;

  public FactoryEditorUseCaseFull(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
    this.frameMain = frameMain;
  }

  @Override
  public AsmEditorUseCaseFull<UseCase, EditorUseCaseFull<UseCase, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(editorUseCaseUml == null) {
      EditorUseCaseFull<UseCase, Frame, ActionEvent> editorUseCaseFull = new EditorUseCaseFull<UseCase, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml());
      editorUseCaseUml = new AsmEditorUseCaseFull<UseCase, EditorUseCaseFull<UseCase, Frame, ActionEvent>>(frameMain, editorUseCaseFull);
      editorUseCaseUml.doPostConstruct();
      editorUseCaseFull.addObserverModelChanged(observerUseCaseChanged);
    }
    return editorUseCaseUml;
  }

  @Override
  public SrvEditUseCaseFull<UseCase, Frame> lazyGetSrvEditElementUml() {
    if(srvEditUseCaseUmlFull == null) {
      srvEditUseCaseUmlFull = new SrvEditUseCaseFull<UseCase, Frame>(srvI18n, srvDialog, settingsGraphic);
    }
    return srvEditUseCaseUmlFull;
  }

  public ISrvI18n getSrvI18n() {
    return srvI18n;
  }

  public ISrvDialog<Frame> getSrvDialog() {
    return srvDialog;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public Frame getFrameMain() {
    return frameMain;
  }

  public IObserverModelChanged getObserverUseCaseChanged() {
    return observerUseCaseChanged;
  }

  public void setObserverUseCaseChanged(
      IObserverModelChanged observerUseCaseChanged) {
    this.observerUseCaseChanged = observerUseCaseChanged;
  }
}
