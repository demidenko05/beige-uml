package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorInteractionUse;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.service.edit.SrvEditInteractionUse;
import org.beigesoft.uml.ui.EditorInteractionUse;

public class FactoryEditorInteractionUse implements IFactoryEditorElementUml<InteractionUse, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditInteractionUse<InteractionUse, Activity> srvEditInteractionUse;
  
  private AsmEditorInteractionUse<InteractionUse, EditorInteractionUse<InteractionUse, Activity, View>> asmEditorInteractionUse; 
  
  private IObserverModelChanged observerInteractionUseUmlChanged;
  
  public FactoryEditorInteractionUse(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<InteractionUse> lazyGetEditorElementUml() {
    if(asmEditorInteractionUse == null) {
      EditorInteractionUse<InteractionUse, Activity, View> editor = 
          new EditorInteractionUse<InteractionUse, Activity, View>(activity, 
              lazyGetSrvEditElementUml(), srvI18n.getMsg("state_invariant_plus"));
      asmEditorInteractionUse = new AsmEditorInteractionUse<InteractionUse, EditorInteractionUse<InteractionUse, Activity, View>>(activity,
          editor, AsmEditorInteractionUse.class.getSimpleName());
      editor.addObserverModelChanged(observerInteractionUseUmlChanged);
    }
    return asmEditorInteractionUse;
  }

  @Override
  public SrvEditInteractionUse<InteractionUse, Activity> lazyGetSrvEditElementUml() {
    if(srvEditInteractionUse == null) {
      srvEditInteractionUse = new SrvEditInteractionUse<InteractionUse, Activity>(srvI18n, 
          srvDialog, settingsGraphic);
    }
    return srvEditInteractionUse;
  }

  //SGS:
  public void setSrvEditInteractionUse(
      SrvEditInteractionUse<InteractionUse, Activity> srvEditInteractionUse) {
    this.srvEditInteractionUse = srvEditInteractionUse;
  }

  public void setObserverInteractionUseUmlChanged(
      IObserverModelChanged observerInteractionUseUmlChanged) {
    this.observerInteractionUseUmlChanged = observerInteractionUseUmlChanged;
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

  public IObserverModelChanged getObserverInteractionUseUmlChanged() {
    return observerInteractionUseUmlChanged;
  }
}
