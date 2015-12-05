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
import org.beigesoft.uml.model.InstanceUml;
import org.beigesoft.uml.service.edit.SrvEditInstanceFull;
import org.beigesoft.uml.ui.EditorInstanceFull;
import org.beigesoft.uml.ui.swing.AsmEditorInstanceFull;

public class FactoryEditorInstanceFull implements IFactoryEditorElementUml<ShapeFullVarious<InstanceUml>, Frame> {

  private final ISrvI18n srvI18n;

  private final ISrvDialog<Frame> srvDialog;

  private final SettingsGraphicUml settingsGraphic;
  
  private final Frame frameMain;
  
  private SrvEditInstanceFull<InstanceUml, Frame> srvEditInstanceUmlFull;
  
  private AsmEditorInstanceFull<InstanceUml, EditorInstanceFull<InstanceUml, Frame, ActionEvent>> editorInstanceUml;

  private IObserverModelChanged observerInstanceChanged;

  public FactoryEditorInstanceFull(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
    this.frameMain = frameMain;
  }

  @Override
  public AsmEditorInstanceFull<InstanceUml, EditorInstanceFull<InstanceUml, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(editorInstanceUml == null) {
      EditorInstanceFull<InstanceUml, Frame, ActionEvent> editorUcExtFul = new EditorInstanceFull<InstanceUml, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml(), srvI18n.getMsg("instance"));
      SrvEditHasNameEditable<HasNameEditable, Frame> srvEditExtPoint = new SrvEditHasNameEditable<HasNameEditable, Frame>(srvI18n, srvDialog, settingsGraphic);
      EditorHasNameEditable<HasNameEditable, Frame, ActionEvent> editorTextExtPoint = new EditorHasNameEditable<HasNameEditable, Frame, ActionEvent>(frameMain, srvEditExtPoint, srvI18n.getMsg("member"));
      AsmEditorHasName<HasNameEditable> asmEditorStructure = new AsmEditorHasName<HasNameEditable>(frameMain, editorTextExtPoint);
      asmEditorStructure.doPostConstruct();
      editorInstanceUml = new AsmEditorInstanceFull<InstanceUml, EditorInstanceFull<InstanceUml, Frame, ActionEvent>>(frameMain, editorUcExtFul, asmEditorStructure);
      editorInstanceUml.doPostConstruct();
      editorUcExtFul.addObserverModelChanged(observerInstanceChanged);
    }
    return editorInstanceUml;
  }

  @Override
  public SrvEditInstanceFull<InstanceUml, Frame> lazyGetSrvEditElementUml() {
    if(srvEditInstanceUmlFull == null) {
      srvEditInstanceUmlFull = new SrvEditInstanceFull<InstanceUml, Frame>(srvI18n, srvDialog, settingsGraphic);
    }
    return srvEditInstanceUmlFull;
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

  public IObserverModelChanged getObserverInstanceChanged() {
    return observerInstanceChanged;
  }

  public void setObserverInstanceChanged(
      IObserverModelChanged observerInstanceChanged) {
    this.observerInstanceChanged = observerInstanceChanged;
  }
}
