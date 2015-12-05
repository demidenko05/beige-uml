package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorStateInvContin;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.StateInvContin;
import org.beigesoft.uml.service.edit.SrvEditStateInvContin;
import org.beigesoft.uml.ui.EditorStateInvContin;

public class FactoryEditorStateInvContin implements IFactoryEditorElementUml<StateInvContin, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditStateInvContin<StateInvContin, Activity> srvEditStateInvContin;
  
  private AsmEditorStateInvContin<StateInvContin, EditorStateInvContin<StateInvContin, Activity, View>> asmEditorStateInvContin; 
  
  private IObserverModelChanged observerStateInvContinUmlChanged;
  
  
  public FactoryEditorStateInvContin(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<StateInvContin> lazyGetEditorElementUml() {
    if(asmEditorStateInvContin == null) {
      EditorStateInvContin<StateInvContin, Activity, View> editor = 
          new EditorStateInvContin<StateInvContin, Activity, View>(activity, 
              lazyGetSrvEditElementUml(), srvI18n.getMsg("state_invariant_plus"));
      asmEditorStateInvContin = new AsmEditorStateInvContin<StateInvContin, EditorStateInvContin<StateInvContin, Activity, View>>(activity,
          editor, AsmEditorStateInvContin.class.getSimpleName());
      editor.addObserverModelChanged(observerStateInvContinUmlChanged);
    }
    return asmEditorStateInvContin;
  }

  @Override
  public SrvEditStateInvContin<StateInvContin, Activity> lazyGetSrvEditElementUml() {
    if(srvEditStateInvContin == null) {
      srvEditStateInvContin = new SrvEditStateInvContin<StateInvContin, Activity>(srvI18n, 
          srvDialog, settingsGraphic);
    }
    return srvEditStateInvContin;
  }

  //SGS:
  public void setSrvEditStateInvContin(
      SrvEditStateInvContin<StateInvContin, Activity> srvEditStateInvContin) {
    this.srvEditStateInvContin = srvEditStateInvContin;
  }

  public void setObserverStateInvContinUmlChanged(
      IObserverModelChanged observerStateInvContinUmlChanged) {
    this.observerStateInvContinUmlChanged = observerStateInvContinUmlChanged;
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

  public IObserverModelChanged getObserverStateInvContinUmlChanged() {
    return observerStateInvContinUmlChanged;
  }
}
