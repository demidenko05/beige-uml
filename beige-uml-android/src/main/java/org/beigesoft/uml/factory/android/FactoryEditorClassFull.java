package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorAttributeClass;
import org.beigesoft.uml.ui.android.AsmEditorClassFull;
import org.beigesoft.uml.ui.android.AsmEditorMethodClass;
import org.beigesoft.uml.ui.android.AsmEditorParameterMethod;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.pojo.ParameterMethod;
import org.beigesoft.uml.service.edit.SrvEditAttributeClass;
import org.beigesoft.uml.service.edit.SrvEditClassFull;
import org.beigesoft.uml.service.edit.SrvEditMethodClass;
import org.beigesoft.uml.service.edit.SrvEditParameterMethod;
import org.beigesoft.uml.ui.EditorAttributeClass;
import org.beigesoft.uml.ui.EditorClassFull;
import org.beigesoft.uml.ui.EditorMethodClass;
import org.beigesoft.uml.ui.EditorParameterMethod;

public class FactoryEditorClassFull implements IFactoryEditorElementUml<ClassFull<ClassUml>, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditClassFull<ClassUml, Activity> srvEditClassFull;
  
  private AsmEditorClassFull<ClassUml, EditorClassFull<ClassUml, Activity, View>> asmEditorClassFull; 
  
  private IObserverModelChanged observerClassFullUmlChanged;
  
  public FactoryEditorClassFull(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<ClassFull<ClassUml>> lazyGetEditorElementUml() {
    if(asmEditorClassFull == null) {
      lazyGetSrvEditElementUml();
      EditorClassFull<ClassUml, Activity, View> editor = 
          new EditorClassFull<ClassUml, Activity, View>(activity, lazyGetSrvEditElementUml());
      SrvEditAttributeClass<AttributeClass, Activity> srvEditAttrCl = new SrvEditAttributeClass<AttributeClass, Activity>(srvI18n, srvDialog, settingsGraphic);
      EditorAttributeClass<AttributeClass, Activity, View> editorAttrCl = 
          new EditorAttributeClass<AttributeClass, Activity, View>(activity, srvEditAttrCl, srvI18n.getMsg("attribute"));
      AsmEditorAttributeClass<AttributeClass, ?> asmEditorAttribute = 
          new AsmEditorAttributeClass<AttributeClass, EditorAttributeClass<AttributeClass,Activity,View>>(activity, editorAttrCl, EditorAttributeClass.class.getSimpleName());
      SrvEditParameterMethod<ParameterMethod, Activity> srvEditParameterMethod = new SrvEditParameterMethod<ParameterMethod, Activity>(srvI18n, srvDialog, settingsGraphic);
      EditorParameterMethod<ParameterMethod, Activity, View> editorParMeth = 
          new EditorParameterMethod<ParameterMethod, Activity, View>(activity, srvEditParameterMethod, srvI18n.getMsg("parameter"));
      AsmEditorParameterMethod<ParameterMethod, ?> asmEditorParameterMethod = 
          new AsmEditorParameterMethod<ParameterMethod, EditorParameterMethod<ParameterMethod, Activity, View>>(activity, editorParMeth, EditorParameterMethod.class.getSimpleName());
      SrvEditMethodClass<MethodClass, Activity> srvEditMeth = 
          new SrvEditMethodClass<MethodClass, Activity>(srvI18n, srvDialog, settingsGraphic, srvEditParameterMethod);
      EditorMethodClass<MethodClass, Activity, View> editorMeth = 
          new EditorMethodClass<MethodClass, Activity, View>(activity, srvEditMeth, srvI18n.getMsg("method"));
      AsmEditorMethodClass<MethodClass, ?> AsmEditorMethodClass = 
          new AsmEditorMethodClass<MethodClass, EditorMethodClass<MethodClass, Activity, View>>(activity, editorMeth, EditorMethodClass.class.getSimpleName(), asmEditorAttribute, asmEditorParameterMethod);
      asmEditorClassFull = new AsmEditorClassFull<ClassUml, EditorClassFull<ClassUml,Activity,View>>(activity,
          editor, AsmEditorClassFull.class.getSimpleName(), asmEditorAttribute, AsmEditorMethodClass);
      editor.addObserverModelChanged(observerClassFullUmlChanged);
    }
    return asmEditorClassFull;
  }

  @Override
  public SrvEditClassFull<ClassUml, Activity> lazyGetSrvEditElementUml() {
    if(srvEditClassFull == null) {
      SrvEditParameterMethod<ParameterMethod, Activity> srvEditParameterMethod = 
          new SrvEditParameterMethod<ParameterMethod, Activity>(srvI18n, srvDialog, settingsGraphic);
      SrvEditMethodClass<MethodClass, Activity> srvEditMethodClass = 
          new SrvEditMethodClass<MethodClass, Activity>(srvI18n, srvDialog, settingsGraphic, srvEditParameterMethod);
      SrvEditAttributeClass<AttributeClass, Activity> srvEditAttributeClass = 
          new SrvEditAttributeClass<AttributeClass, Activity>(srvI18n, srvDialog, settingsGraphic);
      srvEditClassFull = new SrvEditClassFull<ClassUml, Activity>(srvI18n, 
          srvDialog, settingsGraphic, srvEditMethodClass, srvEditAttributeClass);
    }
    return srvEditClassFull;
  }

  //SGS:
  public void setSrvEditClassFull(
      SrvEditClassFull<ClassUml, Activity> srvEditClassFull) {
    this.srvEditClassFull = srvEditClassFull;
  }

  public AsmEditorClassFull<ClassUml, EditorClassFull<ClassUml, Activity, View>> getEditorClassFull() {
    return asmEditorClassFull;
  }

  public void setEditorClassFull(
      AsmEditorClassFull<ClassUml, EditorClassFull<ClassUml, Activity, View>> editorClassFull) {
    this.asmEditorClassFull = editorClassFull;
  }

  public IObserverModelChanged getObserverClassFullUmlChanged() {
    return observerClassFullUmlChanged;
  }

  public void setObserverClassFullUmlChanged(
      IObserverModelChanged observerClassFullUmlChanged) {
    this.observerClassFullUmlChanged = observerClassFullUmlChanged;
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
}
