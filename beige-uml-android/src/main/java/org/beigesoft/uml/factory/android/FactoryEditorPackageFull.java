package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorPackageFull;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.PackageUml;
import org.beigesoft.uml.service.edit.SrvEditPackageFull;
import org.beigesoft.uml.ui.EditorPackageFull;

public class FactoryEditorPackageFull implements IFactoryEditorElementUml<ShapeFullVarious<PackageUml>, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditPackageFull<PackageUml, Activity> srvEditPackageFull;
  
  private AsmEditorPackageFull<PackageUml, EditorPackageFull<PackageUml, Activity, View>> asmEditorPackageFull; 
  
  private IObserverModelChanged observerPackageFullUmlChanged;
  
  
  public FactoryEditorPackageFull(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<ShapeFullVarious<PackageUml>> lazyGetEditorElementUml() {
    if(asmEditorPackageFull == null) {
      EditorPackageFull<PackageUml, Activity, View> editor = 
          new EditorPackageFull<PackageUml, Activity, View>(activity, 
              lazyGetSrvEditElementUml(), srvI18n.getMsg("package"));
      asmEditorPackageFull = new AsmEditorPackageFull<PackageUml, EditorPackageFull<PackageUml, Activity, View>>(activity,
          editor, AsmEditorPackageFull.class.getSimpleName());
      editor.addObserverModelChanged(observerPackageFullUmlChanged);
    }
    return asmEditorPackageFull;
  }

  @Override
  public SrvEditPackageFull<PackageUml, Activity> lazyGetSrvEditElementUml() {
    if(srvEditPackageFull == null) {
      srvEditPackageFull = new SrvEditPackageFull<PackageUml, Activity>(srvI18n, 
          srvDialog, settingsGraphic);
    }
    return srvEditPackageFull;
  }

  //SGS:
  public void setSrvEditPackageFull(
      SrvEditPackageFull<PackageUml, Activity> srvEditPackageFull) {
    this.srvEditPackageFull = srvEditPackageFull;
  }

  public void setObserverPackageFullUmlChanged(
      IObserverModelChanged observerPackageFullUmlChanged) {
    this.observerPackageFullUmlChanged = observerPackageFullUmlChanged;
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

  public IObserverModelChanged getObserverPackageFullUmlChanged() {
    return observerPackageFullUmlChanged;
  }
}
