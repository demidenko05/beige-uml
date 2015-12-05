package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorShapeWithNameFull;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.Actor;
import org.beigesoft.uml.service.edit.SrvEditActorFull;
import org.beigesoft.uml.ui.EditorShapeWithNameFull;

public class FactoryEditorActorFull implements IFactoryEditorElementUml<ShapeFullVarious<Actor>, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditActorFull<Actor, Activity> srvEditActorFull;
  
  private AsmEditorShapeWithNameFull<ShapeFullVarious<Actor>, EditorShapeWithNameFull<ShapeFullVarious<Actor>, Activity, View, Actor>, Actor> asmEditorActorFull; 
  
  private IObserverModelChanged observerActorFullUmlChanged;
  
  public FactoryEditorActorFull(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<ShapeFullVarious<Actor>> lazyGetEditorElementUml() {
    if(asmEditorActorFull == null) {
      EditorShapeWithNameFull<ShapeFullVarious<Actor>, Activity, View, Actor> editor = 
          new EditorShapeWithNameFull<ShapeFullVarious<Actor>, Activity, View, Actor>(activity, 
              lazyGetSrvEditElementUml(), srvI18n.getMsg("actor"));
      asmEditorActorFull = new AsmEditorShapeWithNameFull<ShapeFullVarious<Actor>, EditorShapeWithNameFull<ShapeFullVarious<Actor>, Activity, View, Actor>, Actor>(activity,
          editor, AsmEditorShapeWithNameFull.class.getSimpleName());
      editor.addObserverModelChanged(observerActorFullUmlChanged);
    }
    return asmEditorActorFull;
  }

  @Override
  public SrvEditActorFull<Actor, Activity> lazyGetSrvEditElementUml() {
    if(srvEditActorFull == null) {
      srvEditActorFull = new SrvEditActorFull<Actor, Activity>(srvI18n, 
          srvDialog, settingsGraphic);
    }
    return srvEditActorFull;
  }

  //SGS:
  public void setSrvEditActorFull(
      SrvEditActorFull<Actor, Activity> srvEditActorFull) {
    this.srvEditActorFull = srvEditActorFull;
  }

  public void setObserverActorFullUmlChanged(
      IObserverModelChanged observerActorFullUmlChanged) {
    this.observerActorFullUmlChanged = observerActorFullUmlChanged;
  }

  public ISrvI18n getSrvI18n() {
    return srvI18n;
  }

  public ISrvDialog<Activity> getSrvDialog() {
    return srvDialog;
  }

  public SettingsGraphicUml getGraphicSettings() {
    return settingsGraphic;
  }

  public Activity getActivity() {
    return activity;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public IObserverModelChanged getObserverActorFullUmlChanged() {
    return observerActorFullUmlChanged;
  }
}
