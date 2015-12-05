package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.Actor;
import org.beigesoft.uml.service.edit.SrvEditActorFull;
import org.beigesoft.uml.ui.EditorShapeWithNameFull;
import org.beigesoft.uml.ui.swing.AsmEditorShapeWithNameFull;

public class FactoryEditorActorFull implements IFactoryEditorElementUml<ShapeFullVarious<Actor>, Frame> {

  private final ISrvI18n srvI18n;

  private final ISrvDialog<Frame> srvDialog;

  private final SettingsGraphicUml settingsGraphic;
  
  private final Frame frameMain;
  
  private SrvEditActorFull<Actor, Frame> srvEditActorUmlFull;
  
  private AsmEditorShapeWithNameFull<ShapeFullVarious<Actor>, EditorShapeWithNameFull<ShapeFullVarious<Actor>, Frame, ActionEvent, Actor>, Actor> editorActorUmlFull;

  private IObserverModelChanged observerActorUmlChanged;

  public FactoryEditorActorFull(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
    this.frameMain = frameMain;
  }

  @Override
  public AsmEditorShapeWithNameFull<ShapeFullVarious<Actor>, EditorShapeWithNameFull<ShapeFullVarious<Actor>, Frame, ActionEvent, Actor>, Actor> lazyGetEditorElementUml() {
    if(editorActorUmlFull == null) {
      EditorShapeWithNameFull<ShapeFullVarious<Actor>, Frame, ActionEvent, Actor> editoractorFull = new EditorShapeWithNameFull<ShapeFullVarious<Actor>, Frame, ActionEvent, Actor>(frameMain, lazyGetSrvEditElementUml(), 
          lazyGetSrvEditElementUml().getSrvI18n().getMsg("actor"));
      editorActorUmlFull = new AsmEditorShapeWithNameFull<ShapeFullVarious<Actor>, EditorShapeWithNameFull<ShapeFullVarious<Actor>, Frame, ActionEvent, Actor>, Actor>(frameMain, editoractorFull);
      editorActorUmlFull.doPostConstruct();
      editoractorFull.addObserverModelChanged(observerActorUmlChanged);
    }
    return editorActorUmlFull;
  }

  @Override
  public SrvEditActorFull<Actor, Frame> lazyGetSrvEditElementUml() {
    if(srvEditActorUmlFull == null) {
       srvEditActorUmlFull = new SrvEditActorFull<Actor, Frame>(srvI18n, srvDialog, settingsGraphic);
    }
    return srvEditActorUmlFull;
  }

  //SGS:
  public void setSrvEditActorUmlFull(
      SrvEditActorFull<Actor, Frame> srvEditActorUmlFull) {
    this.srvEditActorUmlFull = srvEditActorUmlFull;
  }

  public AsmEditorShapeWithNameFull<ShapeFullVarious<Actor>, EditorShapeWithNameFull<ShapeFullVarious<Actor>, Frame, ActionEvent, Actor>, Actor> getEditorActorUmlFull() {
    return editorActorUmlFull;
  }

  public void setEditorActorUmlFull(
      AsmEditorShapeWithNameFull<ShapeFullVarious<Actor>, EditorShapeWithNameFull<ShapeFullVarious<Actor>, Frame, ActionEvent, Actor>, Actor> editorActorUmlFull) {
    this.editorActorUmlFull = editorActorUmlFull;
  }

  public IObserverModelChanged getObserverActorUmlChanged() {
    return observerActorUmlChanged;
  }

  public void setObserverActorUmlChanged(
      IObserverModelChanged observerActorUmlChanged) {
    this.observerActorUmlChanged = observerActorUmlChanged;
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
}
