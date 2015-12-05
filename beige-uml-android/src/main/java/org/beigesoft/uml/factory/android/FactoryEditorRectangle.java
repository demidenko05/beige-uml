package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorRectangle;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.service.edit.SrvEditRectangleUml;
import org.beigesoft.uml.ui.EditorRectangleUml;

public class FactoryEditorRectangle implements IFactoryEditorElementUml<RectangleUml, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditRectangleUml<RectangleUml, Activity> srvEditRectangleUml;
  
  private AsmEditorRectangle<RectangleUml, EditorRectangleUml<RectangleUml, Activity, View>> editorRectangleUml; 
  
  private IObserverModelChanged observerRectangleUmlChanged;
  
  private IContainerShapesForTie containerShapesUmlForTie;
  
  public FactoryEditorRectangle(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<RectangleUml> lazyGetEditorElementUml() {
    if(editorRectangleUml == null) {
      lazyGetSrvEditElementUml();
      EditorRectangleUml<RectangleUml, Activity, View> editor = new EditorRectangleUml<RectangleUml, Activity, View>(activity,
          srvEditRectangleUml, srvI18n.getMsg("rectangle"));
      editorRectangleUml = new AsmEditorRectangle<RectangleUml, EditorRectangleUml<RectangleUml,Activity,View>>(activity,
          editor, AsmEditorRectangle.class.getSimpleName());
      editor.addObserverModelChanged(observerRectangleUmlChanged);
    }
    return editorRectangleUml;
  }

  @Override
  public ISrvEdit<RectangleUml, Activity> lazyGetSrvEditElementUml() {
    if(srvEditRectangleUml == null) {
      srvEditRectangleUml = new SrvEditRectangleUml<RectangleUml, Activity>(srvI18n, srvDialog, settingsGraphic);
    }
    return srvEditRectangleUml;
  }

  //SGS:
  public void setSrvEditRectangleUml(
      SrvEditRectangleUml<RectangleUml, Activity> srvEditRectangleUml) {
    this.srvEditRectangleUml = srvEditRectangleUml;
  }

  public AsmEditorRectangle<RectangleUml, EditorRectangleUml<RectangleUml, Activity, View>> getEditorRectangleUml() {
    return editorRectangleUml;
  }

  public void setEditorRectangleUml(
      AsmEditorRectangle<RectangleUml, EditorRectangleUml<RectangleUml, Activity, View>> editorRectangleUml) {
    this.editorRectangleUml = editorRectangleUml;
  }

  public IObserverModelChanged getObserverRectangleUmlChanged() {
    return observerRectangleUmlChanged;
  }

  public void setObserverRectangleUmlChanged(
      IObserverModelChanged observerRectangleUmlChanged) {
    this.observerRectangleUmlChanged = observerRectangleUmlChanged;
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

  public IContainerShapesForTie getContainerShapesUmlForTie() {
    return containerShapesUmlForTie;
  }

  public void setContainerShapesUmlForTie(IContainerShapesForTie containerShapesUmlForTie) {
    this.containerShapesUmlForTie = containerShapesUmlForTie;
  }
}
