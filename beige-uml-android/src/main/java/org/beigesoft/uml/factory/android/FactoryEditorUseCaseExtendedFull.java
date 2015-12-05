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
import org.beigesoft.uml.ui.android.AsmEditorUseCaseExtendedFull;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.UseCaseExtended;
import org.beigesoft.uml.service.edit.SrvEditUseCaseExtendedFull;
import org.beigesoft.uml.ui.EditorUseCaseExtendedFull;

public class FactoryEditorUseCaseExtendedFull implements IFactoryEditorElementUml<ShapeFullVarious<UseCaseExtended>, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditUseCaseExtendedFull<UseCaseExtended, Activity> srvEditUseCaseExtendedFull;
  
  private AsmEditorUseCaseExtendedFull<UseCaseExtended, EditorUseCaseExtendedFull<UseCaseExtended, Activity, View>> asmEditorUseCaseExtendedFull; 
  
  private IObserverModelChanged observerUseCaseExtendedFullUmlChanged;
  
  
  public FactoryEditorUseCaseExtendedFull(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<ShapeFullVarious<UseCaseExtended>> lazyGetEditorElementUml() {
    if(asmEditorUseCaseExtendedFull == null) {
      EditorUseCaseExtendedFull<UseCaseExtended, Activity, View> editor = 
          new EditorUseCaseExtendedFull<UseCaseExtended, Activity, View>(activity, 
              lazyGetSrvEditElementUml());
      SrvEditHasNameEditable<HasNameEditable, Activity> srvEditHasName = new SrvEditHasNameEditable<HasNameEditable, Activity>(srvI18n, srvDialog, settingsGraphic);;
      EditorHasNameEditable<HasNameEditable, Activity, View> editorHasNm = new EditorHasNameEditable<HasNameEditable, Activity, View>(activity, srvEditHasName, srvI18n.getMsg("extension_point"));
      AsmEditorHasName<HasNameEditable, ?> asmEditorExtPoint = new AsmEditorHasName<HasNameEditable, EditorHasNameEditable<HasNameEditable, Activity, View>>(activity, editorHasNm, AsmEditorHasName.class.getSimpleName());
      asmEditorUseCaseExtendedFull = new AsmEditorUseCaseExtendedFull<UseCaseExtended, EditorUseCaseExtendedFull<UseCaseExtended, Activity, View>>(activity,
          editor, AsmEditorShapeWithNameFull.class.getSimpleName(), asmEditorExtPoint);
      editor.addObserverModelChanged(observerUseCaseExtendedFullUmlChanged);
    }
    return asmEditorUseCaseExtendedFull;
  }

  @Override
  public SrvEditUseCaseExtendedFull<UseCaseExtended, Activity> lazyGetSrvEditElementUml() {
    if(srvEditUseCaseExtendedFull == null) {
      srvEditUseCaseExtendedFull = new SrvEditUseCaseExtendedFull<UseCaseExtended, Activity>(srvI18n, 
          srvDialog, settingsGraphic);
    }
    return srvEditUseCaseExtendedFull;
  }

  //SGS:
  public void setSrvEditUseCaseExtendedFull(
      SrvEditUseCaseExtendedFull<UseCaseExtended, Activity> srvEditUseCaseExtendedFull) {
    this.srvEditUseCaseExtendedFull = srvEditUseCaseExtendedFull;
  }

  public void setObserverUseCaseExtendedFullUmlChanged(
      IObserverModelChanged observerUseCaseExtendedFullUmlChanged) {
    this.observerUseCaseExtendedFullUmlChanged = observerUseCaseExtendedFullUmlChanged;
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

  public IObserverModelChanged getObserverUseCaseExtendedFullUmlChanged() {
    return observerUseCaseExtendedFullUmlChanged;
  }
}
