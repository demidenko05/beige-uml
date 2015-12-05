package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.EditorHasNameEditable;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.SrvEditHasNameEditable;
import org.beigesoft.ui.widget.swing.AsmEditorHasName;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.UseCaseExtended;
import org.beigesoft.uml.service.edit.SrvEditUseCaseExtendedFull;
import org.beigesoft.uml.ui.EditorUseCaseExtendedFull;
import org.beigesoft.uml.ui.swing.AsmEditorUseCaseExtendedFull;

public class FactoryEditorUseCaseExtendedFull implements IFactoryEditorElementUml<ShapeFullVarious<UseCaseExtended>, Frame> {

  private final ISrvI18n srvI18n;

  private final ISrvDialog<Frame> srvDialog;

  private final SettingsGraphicUml settingsGraphic;
  
  private final Frame frameMain;
  
  private SrvEditUseCaseExtendedFull<UseCaseExtended, Frame> srvEditUseCaseUmlFull;
  
  private AsmEditorUseCaseExtendedFull<UseCaseExtended, EditorUseCaseExtendedFull<UseCaseExtended, Frame, ActionEvent>> editorUseCaseUml;

  private IObserverModelChanged observerUseCaseChanged;

  public FactoryEditorUseCaseExtendedFull(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
    this.frameMain = frameMain;
  }

  @Override
  public AsmEditorUseCaseExtendedFull<UseCaseExtended, EditorUseCaseExtendedFull<UseCaseExtended, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(editorUseCaseUml == null) {
      EditorUseCaseExtendedFull<UseCaseExtended, Frame, ActionEvent> editorUcExtFul = new EditorUseCaseExtendedFull<UseCaseExtended, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml());
      SrvEditHasNameEditable<HasNameEditable, Frame> srvEditExtPoint = new SrvEditHasNameEditable<HasNameEditable, Frame>(srvI18n, srvDialog, settingsGraphic);
      EditorHasNameEditable<HasNameEditable, Frame, ActionEvent> editorTextExtPoint = new EditorHasNameEditable<HasNameEditable, Frame, ActionEvent>(frameMain, srvEditExtPoint, srvI18n.getMsg("extension_point"));
      AsmEditorHasName<HasNameEditable> asmEditorExtPoint = new AsmEditorHasName<HasNameEditable>(frameMain, editorTextExtPoint);
      asmEditorExtPoint.doPostConstruct();
      editorUseCaseUml = new AsmEditorUseCaseExtendedFull<UseCaseExtended, EditorUseCaseExtendedFull<UseCaseExtended, Frame, ActionEvent>>(frameMain, editorUcExtFul, asmEditorExtPoint);
      editorUseCaseUml.doPostConstruct();
      editorUcExtFul.addObserverModelChanged(observerUseCaseChanged);
    }
    return editorUseCaseUml;
  }

  @Override
  public SrvEditUseCaseExtendedFull<UseCaseExtended, Frame> lazyGetSrvEditElementUml() {
    if(srvEditUseCaseUmlFull == null) {
      srvEditUseCaseUmlFull = new SrvEditUseCaseExtendedFull<UseCaseExtended, Frame>(srvI18n, srvDialog, settingsGraphic);
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
