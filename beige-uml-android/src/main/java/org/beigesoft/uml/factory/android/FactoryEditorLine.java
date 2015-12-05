package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorLine;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.service.edit.SrvEditLineUml;
import org.beigesoft.uml.ui.EditorLineUml;

public class FactoryEditorLine implements IFactoryEditorElementUml<LineUml, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditLineUml<LineUml, Activity> srvEditLineUml;
  
  private AsmEditorLine<LineUml, EditorLineUml<LineUml, Activity, View>> editorLineUml; 
  
  private IObserverModelChanged observerLineUmlChanged;
  
  private IContainerShapesForTie containerShapesUmlForTie;
  
  public FactoryEditorLine(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<LineUml> lazyGetEditorElementUml() {
    if(editorLineUml == null) {
      lazyGetSrvEditElementUml();
      EditorLineUml<LineUml, Activity, View> editor = new EditorLineUml<LineUml, Activity, View>(activity,
          srvEditLineUml, srvI18n.getMsg("line"));
      editorLineUml = new AsmEditorLine<LineUml, EditorLineUml<LineUml,Activity,View>>(activity,
          editor, AsmEditorLine.class.getSimpleName());
      editor.addObserverModelChanged(observerLineUmlChanged);
    }
    return editorLineUml;
  }

  @Override
  public ISrvEdit<LineUml, Activity> lazyGetSrvEditElementUml() {
    if(srvEditLineUml == null) {
      srvEditLineUml = new SrvEditLineUml<LineUml, Activity>(srvI18n, srvDialog, settingsGraphic);
    }
    return srvEditLineUml;
  }

  //SGS:
  public void setSrvEditLineUml(
      SrvEditLineUml<LineUml, Activity> srvEditLineUml) {
    this.srvEditLineUml = srvEditLineUml;
  }

  public AsmEditorLine<LineUml, EditorLineUml<LineUml, Activity, View>> getEditorLineUml() {
    return editorLineUml;
  }

  public void setEditorLineUml(
      AsmEditorLine<LineUml, EditorLineUml<LineUml, Activity, View>> editorLineUml) {
    this.editorLineUml = editorLineUml;
  }

  public IObserverModelChanged getObserverLineUmlChanged() {
    return observerLineUmlChanged;
  }

  public void setObserverLineUmlChanged(
      IObserverModelChanged observerLineUmlChanged) {
    this.observerLineUmlChanged = observerLineUmlChanged;
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
