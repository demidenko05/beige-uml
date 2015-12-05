package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.ui.android.AsmEditorShapeWithNameFull;
import org.beigesoft.uml.ui.android.AsmEditorUseCaseFull;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.UseCase;
import org.beigesoft.uml.service.edit.SrvEditUseCaseFull;
import org.beigesoft.uml.ui.EditorUseCaseFull;

public class FactoryEditorUseCaseFull implements IFactoryEditorElementUml<ShapeFullVarious<UseCase>, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditUseCaseFull<UseCase, Activity> srvEditUseCaseFull;
  
  private AsmEditorUseCaseFull<UseCase, EditorUseCaseFull<UseCase, Activity, View>> asmEditorUseCaseFull; 
  
  private IObserverModelChanged observerUseCaseFullUmlChanged;
  
  
  public FactoryEditorUseCaseFull(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public AsmEditorUseCaseFull<UseCase, EditorUseCaseFull<UseCase, Activity, View>> lazyGetEditorElementUml() {
    if(asmEditorUseCaseFull == null) {
      EditorUseCaseFull<UseCase, Activity, View> editor = 
          new EditorUseCaseFull<UseCase, Activity, View>(activity, 
              lazyGetSrvEditElementUml());
      asmEditorUseCaseFull = new AsmEditorUseCaseFull<UseCase, EditorUseCaseFull<UseCase, Activity, View>>(activity,
          editor, AsmEditorShapeWithNameFull.class.getSimpleName());
      editor.addObserverModelChanged(observerUseCaseFullUmlChanged);
    }
    return asmEditorUseCaseFull;
  }

  @Override
  public SrvEditUseCaseFull<UseCase, Activity> lazyGetSrvEditElementUml() {
    if(srvEditUseCaseFull == null) {
      srvEditUseCaseFull = new SrvEditUseCaseFull<UseCase, Activity>(srvI18n, 
          srvDialog, settingsGraphic);
    }
    return srvEditUseCaseFull;
  }

  //SGS:
  public void setObserverUseCaseFullUmlChanged(
      IObserverModelChanged observerUseCaseFullUmlChanged) {
    this.observerUseCaseFullUmlChanged = observerUseCaseFullUmlChanged;
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

  public IObserverModelChanged getObserverUseCaseFullUmlChanged() {
    return observerUseCaseFullUmlChanged;
  }
}
