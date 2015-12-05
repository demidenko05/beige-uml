package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorLifeLineFull;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.service.edit.SrvEditLifeLineFull;
import org.beigesoft.uml.ui.EditorLifeLineFull;

public class FactoryEditorLifeLineFull implements IFactoryEditorElementUml<LifeLineFull<ShapeUmlWithName>, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditLifeLineFull<LifeLineFull<ShapeUmlWithName>, Activity> srvEditLifeLineFull;
  
  private AsmEditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, EditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, Activity, View>> asmEditorLifeLineFull; 
  
  private IObserverModelChanged observerLifeLineFullUmlChanged;
  
  
  public FactoryEditorLifeLineFull(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<LifeLineFull<ShapeUmlWithName>> lazyGetEditorElementUml() {
    if(asmEditorLifeLineFull == null) {
      EditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, Activity, View> editor = 
          new EditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, Activity, View>(activity, 
              lazyGetSrvEditElementUml(), srvI18n.getMsg("lifeline"));
      asmEditorLifeLineFull = new AsmEditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, EditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, Activity, View>>(activity,
          editor, AsmEditorLifeLineFull.class.getSimpleName());
      editor.addObserverModelChanged(observerLifeLineFullUmlChanged);
    }
    return asmEditorLifeLineFull;
  }

  @Override
  public SrvEditLifeLineFull<LifeLineFull<ShapeUmlWithName>, Activity> lazyGetSrvEditElementUml() {
    if(srvEditLifeLineFull == null) {
      srvEditLifeLineFull = new SrvEditLifeLineFull<LifeLineFull<ShapeUmlWithName>, Activity>(srvI18n, 
          srvDialog, settingsGraphic);
    }
    return srvEditLifeLineFull;
  }

  //SGS:
  public void setSrvEditLifeLineFull(
      SrvEditLifeLineFull<LifeLineFull<ShapeUmlWithName>, Activity> srvEditLifeLineFull) {
    this.srvEditLifeLineFull = srvEditLifeLineFull;
  }

  public void setObserverLifeLineFullUmlChanged(
      IObserverModelChanged observerLifeLineFullUmlChanged) {
    this.observerLifeLineFullUmlChanged = observerLifeLineFullUmlChanged;
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

  public IObserverModelChanged getObserverLifeLineFullUmlChanged() {
    return observerLifeLineFullUmlChanged;
  }
}
