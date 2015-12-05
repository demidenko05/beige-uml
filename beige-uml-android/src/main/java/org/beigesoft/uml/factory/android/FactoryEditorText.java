package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorText;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.edit.SrvEditTextUml;
import org.beigesoft.uml.ui.EditorText;

public class FactoryEditorText implements IFactoryEditorElementUml<TextUml, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditTextUml<TextUml, Activity> srvEditText;
  
  private AsmEditorText<TextUml, EditorText<TextUml, Activity, View>> editorText; 
  
  private IObserverModelChanged observerTextUmlChanged;
  
  private IContainerShapesForTie containerShapesUmlForTie;
  
  public FactoryEditorText(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<TextUml> lazyGetEditorElementUml() {
    if(editorText == null) {
      lazyGetSrvEditElementUml();
      EditorText<TextUml, Activity, View> editor = new EditorText<TextUml, Activity, View>(activity,
          srvEditText, srvI18n.getMsg("text"));
      editorText = new AsmEditorText<TextUml, EditorText<TextUml,Activity,View>>(activity,
          editor, AsmEditorText.class.getSimpleName());
      editor.setContainerShapesUmlForTie(containerShapesUmlForTie);
      editor.addObserverModelChanged(observerTextUmlChanged);
    }
    return editorText;
  }

  @Override
  public ISrvEdit<TextUml, Activity> lazyGetSrvEditElementUml() {
    if(srvEditText == null) {
      srvEditText = new SrvEditTextUml<TextUml, Activity>(srvI18n, srvDialog, settingsGraphic);
    }
    return srvEditText;
  }

  //SGS:
  public void setSrvEditText(
      SrvEditTextUml<TextUml, Activity> srvEditText) {
    this.srvEditText = srvEditText;
  }

  public AsmEditorText<TextUml, EditorText<TextUml, Activity, View>> getEditorText() {
    return editorText;
  }

  public void setEditorText(
      AsmEditorText<TextUml, EditorText<TextUml, Activity, View>> editorText) {
    this.editorText = editorText;
  }

  public IObserverModelChanged getObserverTextUmlChanged() {
    return observerTextUmlChanged;
  }

  public void setObserverTextUmlChanged(
      IObserverModelChanged observerTextUmlChanged) {
    this.observerTextUmlChanged = observerTextUmlChanged;
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
