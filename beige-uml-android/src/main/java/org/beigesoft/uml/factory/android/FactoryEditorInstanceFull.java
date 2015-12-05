package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.EditorHasNameEditable;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.SrvEditHasNameEditable;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorHasName;
import org.beigesoft.uml.ui.android.AsmEditorShapeWithNameFull;
import org.beigesoft.uml.ui.android.AsmEditorInstanceFull;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.model.InstanceUml;
import org.beigesoft.uml.service.edit.SrvEditInstanceFull;
import org.beigesoft.uml.ui.EditorInstanceFull;

public class FactoryEditorInstanceFull implements IFactoryEditorElementUml<ShapeFullVarious<InstanceUml>, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditInstanceFull<InstanceUml, Activity> srvEditInstanceFull;
  
  private AsmEditorInstanceFull<InstanceUml, EditorInstanceFull<InstanceUml, Activity, View>> asmEditorInstanceFull; 
  
  private IObserverModelChanged observerInstanceFullUmlChanged;
  
  public FactoryEditorInstanceFull(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<ShapeFullVarious<InstanceUml>> lazyGetEditorElementUml() {
    if(asmEditorInstanceFull == null) {
      EditorInstanceFull<InstanceUml, Activity, View> editor = 
          new EditorInstanceFull<InstanceUml, Activity, View>(activity, 
              lazyGetSrvEditElementUml(), srvI18n.getMsg("instance"));
      SrvEditHasNameEditable<HasNameEditable, Activity> srvEditHasName = new SrvEditHasNameEditable<HasNameEditable, Activity>(srvI18n, srvDialog, settingsGraphic);;
      EditorHasNameEditable<HasNameEditable, Activity, View> editorHasNm = new EditorHasNameEditable<HasNameEditable, Activity, View>(activity, srvEditHasName, srvI18n.getMsg("member"));
      AsmEditorHasName<HasNameEditable, ?> asmEditorExtPoint = new AsmEditorHasName<HasNameEditable, EditorHasNameEditable<HasNameEditable, Activity, View>>(activity, editorHasNm, AsmEditorHasName.class.getSimpleName());
      asmEditorInstanceFull = new AsmEditorInstanceFull<InstanceUml, EditorInstanceFull<InstanceUml, Activity, View>>(activity,
          editor, AsmEditorShapeWithNameFull.class.getSimpleName(), asmEditorExtPoint);
      editor.addObserverModelChanged(observerInstanceFullUmlChanged);
    }
    return asmEditorInstanceFull;
  }

  @Override
  public SrvEditInstanceFull<InstanceUml, Activity> lazyGetSrvEditElementUml() {
    if(srvEditInstanceFull == null) {
      srvEditInstanceFull = new SrvEditInstanceFull<InstanceUml, Activity>(srvI18n, 
          srvDialog, settingsGraphic);
    }
    return srvEditInstanceFull;
  }

  //SGS:
  public void setSrvEditInstanceFull(
      SrvEditInstanceFull<InstanceUml, Activity> srvEditInstanceFull) {
    this.srvEditInstanceFull = srvEditInstanceFull;
  }

  public void setObserverInstanceFullUmlChanged(
      IObserverModelChanged observerInstanceFullUmlChanged) {
    this.observerInstanceFullUmlChanged = observerInstanceFullUmlChanged;
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

  public IObserverModelChanged getObserverInstanceFullUmlChanged() {
    return observerInstanceFullUmlChanged;
  }
}
