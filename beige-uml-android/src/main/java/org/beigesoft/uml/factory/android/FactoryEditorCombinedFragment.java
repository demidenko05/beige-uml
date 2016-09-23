package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorCombinedFragment;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.CombinedFragment;
import org.beigesoft.uml.service.edit.SrvEditCombinedFragment;
import org.beigesoft.uml.ui.EditorCombinedFragment;

public class FactoryEditorCombinedFragment implements IFactoryEditorElementUml<CombinedFragment, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditCombinedFragment<CombinedFragment, Activity> srvEditCombinedFragment;
  
  private AsmEditorCombinedFragment<CombinedFragment, EditorCombinedFragment<CombinedFragment, Activity, View>> asmEditorCombinedFragment; 
  
  private IObserverModelChanged observerCombinedFragmentUmlChanged;
  
  public FactoryEditorCombinedFragment(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<CombinedFragment> lazyGetEditorElementUml() {
    if(asmEditorCombinedFragment == null) {
      EditorCombinedFragment<CombinedFragment, Activity, View> editor = 
          new EditorCombinedFragment<CombinedFragment, Activity, View>(activity, 
              lazyGetSrvEditElementUml(), srvI18n.getMsg("CombinedFragment"));
      asmEditorCombinedFragment = new AsmEditorCombinedFragment<CombinedFragment, EditorCombinedFragment<CombinedFragment, Activity, View>>(activity,
          editor, AsmEditorCombinedFragment.class.getSimpleName());
      editor.addObserverModelChanged(observerCombinedFragmentUmlChanged);
    }
    return asmEditorCombinedFragment;
  }

  @Override
  public SrvEditCombinedFragment<CombinedFragment, Activity> lazyGetSrvEditElementUml() {
    if(srvEditCombinedFragment == null) {
      srvEditCombinedFragment = new SrvEditCombinedFragment<CombinedFragment, Activity>(srvI18n, 
          srvDialog, settingsGraphic);
    }
    return srvEditCombinedFragment;
  }

  //SGS:
  public void setSrvEditCombinedFragment(
      SrvEditCombinedFragment<CombinedFragment, Activity> srvEditCombinedFragment) {
    this.srvEditCombinedFragment = srvEditCombinedFragment;
  }

  public void setObserverCombinedFragmentUmlChanged(
      IObserverModelChanged observerCombinedFragmentUmlChanged) {
    this.observerCombinedFragmentUmlChanged = observerCombinedFragmentUmlChanged;
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

  public IObserverModelChanged getObserverCombinedFragmentUmlChanged() {
    return observerCombinedFragmentUmlChanged;
  }
}
