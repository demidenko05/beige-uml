package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorCoregionFull;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.container.IContainerAsmMessagesFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.service.edit.SrvEditCoregionFull;
import org.beigesoft.uml.ui.EditorCoregionFull;

public class FactoryEditorCoregionFull implements IFactoryEditorElementUml<CoregionFull, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditCoregionFull<CoregionFull, Activity> srvEditCoregionFull;
  
  private AsmEditorCoregionFull<CoregionFull, EditorCoregionFull<CoregionFull, Activity, View>> editorCoregionFull; 
  
  private IObserverModelChanged observerCoregionFullChanged;

  private IContainerAsmMessagesFull containerAsmMessagesFull;
  
  public FactoryEditorCoregionFull(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<CoregionFull> lazyGetEditorElementUml() {
    if(editorCoregionFull == null) {
      lazyGetSrvEditElementUml();
      EditorCoregionFull<CoregionFull, Activity, View> editor = new EditorCoregionFull<CoregionFull, Activity, View>(activity,
          srvEditCoregionFull, srvI18n.getMsg("coregion"));
      editorCoregionFull = new AsmEditorCoregionFull<CoregionFull, EditorCoregionFull<CoregionFull,Activity,View>>(activity,
          editor, AsmEditorCoregionFull.class.getSimpleName());
      editor.setContainerAsmMessagesFull(containerAsmMessagesFull);
      editor.addObserverModelChanged(observerCoregionFullChanged);
    }
    return editorCoregionFull;
  }

  @Override
  public ISrvEdit<CoregionFull, Activity> lazyGetSrvEditElementUml() {
    if(srvEditCoregionFull == null) {
      srvEditCoregionFull = new SrvEditCoregionFull<CoregionFull, Activity>(srvI18n, srvDialog, settingsGraphic);
    }
    return srvEditCoregionFull;
  }

  //SGS:
  public void setSrvEditCoregionFull(
      SrvEditCoregionFull<CoregionFull, Activity> srvEditCoregionFull) {
    this.srvEditCoregionFull = srvEditCoregionFull;
  }

  public AsmEditorCoregionFull<CoregionFull, EditorCoregionFull<CoregionFull, Activity, View>> getEditorCoregionFull() {
    return editorCoregionFull;
  }

  public void setEditorCoregionFull(
      AsmEditorCoregionFull<CoregionFull, EditorCoregionFull<CoregionFull, Activity, View>> editorCoregionFull) {
    this.editorCoregionFull = editorCoregionFull;
  }

  public IObserverModelChanged getObserverCoregionFullChanged() {
    return observerCoregionFullChanged;
  }

  public void setObserverCoregionFullChanged(
      IObserverModelChanged observerCoregionFullChanged) {
    this.observerCoregionFullChanged = observerCoregionFullChanged;
  }

  public ISrvI18n getSrvI18n() {
    return srvI18n;
  }

  public ISrvDialog<Activity> getSrvDialog() {
    return srvDialog;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public Activity getActivity() {
    return activity;
  }

  public IContainerAsmMessagesFull getContainerAsmMessagesFull() {
    return containerAsmMessagesFull;
  }

  public void setContainerAsmMessagesFull(IContainerAsmMessagesFull containerAsmMessagesFull) {
    this.containerAsmMessagesFull = containerAsmMessagesFull;
  }
}
