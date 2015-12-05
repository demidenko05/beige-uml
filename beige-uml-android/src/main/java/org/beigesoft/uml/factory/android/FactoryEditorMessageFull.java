package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorMessageFull;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.container.IContainerAsmLifeLinesFull;
import org.beigesoft.uml.container.IContainerInteractionUses;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.service.edit.SrvEditMessageFull;
import org.beigesoft.uml.ui.EditorMessageFull;

public class FactoryEditorMessageFull implements IFactoryEditorElementUml<MessageFull, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditMessageFull<MessageFull, Activity> srvEditMessageFull;
  
  private AsmEditorMessageFull<MessageFull, EditorMessageFull<MessageFull, Activity, View>> asmEditorMessageFull; 
  
  private IObserverModelChanged observerMessageFullUmlChanged;
  
  private IContainerInteractionUses containerInteractionUses;

  private IContainerAsmLifeLinesFull containerAsmLifeLinesFull;

  public FactoryEditorMessageFull(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<MessageFull> lazyGetEditorElementUml() {
    if(asmEditorMessageFull == null) {
      EditorMessageFull<MessageFull, Activity, View> editor = 
          new EditorMessageFull<MessageFull, Activity, View>(activity, 
              lazyGetSrvEditElementUml(), srvI18n.getMsg("message"));
      asmEditorMessageFull = new AsmEditorMessageFull<MessageFull, EditorMessageFull<MessageFull, Activity, View>>(activity,
          editor, AsmEditorMessageFull.class.getSimpleName());
      editor.addObserverModelChanged(observerMessageFullUmlChanged);
      editor.setContainerInteractionUses(containerInteractionUses);
      editor.setContainerAsmLifeLinesFull(containerAsmLifeLinesFull);
    }
    return asmEditorMessageFull;
  }

  @Override
  public SrvEditMessageFull<MessageFull, Activity> lazyGetSrvEditElementUml() {
    if(srvEditMessageFull == null) {
      srvEditMessageFull = new SrvEditMessageFull<MessageFull, Activity>(srvI18n, 
          srvDialog, settingsGraphic);
    }
    return srvEditMessageFull;
  }

  //SGS:
  public void setSrvEditMessageFull(
      SrvEditMessageFull<MessageFull, Activity> srvEditMessageFull) {
    this.srvEditMessageFull = srvEditMessageFull;
  }

  public void setObserverMessageFullUmlChanged(
      IObserverModelChanged observerMessageFullUmlChanged) {
    this.observerMessageFullUmlChanged = observerMessageFullUmlChanged;
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

  public IObserverModelChanged getObserverMessageFullUmlChanged() {
    return observerMessageFullUmlChanged;
  }

  public IContainerAsmLifeLinesFull getContainerAsmLifeLinesFull() {
    return containerAsmLifeLinesFull;
  }

  public void setContainerAsmLifeLinesFull(IContainerAsmLifeLinesFull containerAsmLifeLinesFull) {
    this.containerAsmLifeLinesFull = containerAsmLifeLinesFull;
  }

  public IContainerInteractionUses getContainerInteractionUses() {
    return containerInteractionUses;
  }

  public void setContainerInteractionUses(IContainerInteractionUses containerInteractionUses) {
    this.containerInteractionUses = containerInteractionUses;
  }
}
