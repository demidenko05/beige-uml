package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
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
import org.beigesoft.uml.ui.swing.AsmEditorAttributeClass;
import org.beigesoft.uml.ui.swing.AsmEditorClassFull;
import org.beigesoft.uml.ui.swing.AsmEditorMethodClass;
import org.beigesoft.uml.ui.swing.AsmEditorParameterMethod;

public class FactoryEditorClassFullInteractive implements IFactoryEditorElementUml<ClassFull<ClassUml>, Frame> {

  private SrvEditClassFull<ClassUml, Frame> editClassUmlSrv;

  private AsmEditorClassFull<ClassUml, EditorClassFull<ClassUml, Frame, ActionEvent>> editorClassUml;

  private final ISrvI18n srvI18n;

  private final ISrvDialog<Frame> srvDialog;

  private final SettingsGraphicUml settingsGraphic;
  
  private final Frame frameMain;

  private IObserverModelChanged observerClassUmlChanged;

  public FactoryEditorClassFullInteractive(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
    this.frameMain = frameMain;
  }

  @Override
  public synchronized AsmEditorClassFull<ClassUml, EditorClassFull<ClassUml, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(editorClassUml == null) {
      EditorClassFull<ClassUml, Frame, ActionEvent> editorClassfull = 
          new EditorClassFull<ClassUml, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml());
      SrvEditAttributeClass<AttributeClass, Frame> srvEditAttrCl = new SrvEditAttributeClass<AttributeClass, Frame>(srvI18n, srvDialog, settingsGraphic);
      EditorAttributeClass<AttributeClass, Frame, ActionEvent> editorAttributeClass = new EditorAttributeClass<AttributeClass, Frame, ActionEvent>(frameMain, srvEditAttrCl, srvI18n.getMsg("attribute"));
      AsmEditorAttributeClass<AttributeClass, EditorAttributeClass<AttributeClass, Frame, ActionEvent>> asmEditorAttributeClass = new AsmEditorAttributeClass<AttributeClass, EditorAttributeClass<AttributeClass, Frame, ActionEvent>>(frameMain, editorAttributeClass );
      asmEditorAttributeClass.doPostConstruct();
      SrvEditParameterMethod<ParameterMethod, Frame> srvEditParameterMethod = new SrvEditParameterMethod<ParameterMethod, Frame>(srvI18n, srvDialog, settingsGraphic);
      SrvEditMethodClass<MethodClass, Frame> srvEditMethClass = new SrvEditMethodClass<MethodClass, Frame>(srvI18n, srvDialog, settingsGraphic, srvEditParameterMethod);
      EditorMethodClass<MethodClass, Frame, ActionEvent> editorMethodClass = new EditorMethodClass<MethodClass, Frame, ActionEvent>(frameMain, srvEditMethClass, srvI18n.getMsg("method"));
      EditorParameterMethod<ParameterMethod, Frame, ActionEvent> editorParameterMethod = new EditorParameterMethod<ParameterMethod, Frame, ActionEvent>(frameMain, srvEditParameterMethod, srvI18n.getMsg("parameter"));
      AsmEditorParameterMethod<ParameterMethod, EditorParameterMethod<ParameterMethod, Frame, ActionEvent>> asmEditorParameterMethod = new AsmEditorParameterMethod<ParameterMethod, EditorParameterMethod<ParameterMethod, Frame, ActionEvent>>(frameMain, editorParameterMethod);
      asmEditorParameterMethod.doPostConstruct();
      AsmEditorMethodClass<MethodClass, EditorMethodClass<MethodClass, Frame, ActionEvent>> asmEditorMethodClass = new AsmEditorMethodClass<MethodClass, EditorMethodClass<MethodClass, Frame, ActionEvent>>(frameMain, editorMethodClass, asmEditorParameterMethod);
      asmEditorMethodClass.doPostConstruct();
      editorClassUml = new AsmEditorClassFull<ClassUml, EditorClassFull<ClassUml, Frame, ActionEvent>>(frameMain, editorClassfull, asmEditorAttributeClass, asmEditorMethodClass);
      editorClassUml.doPostConstruct();
      editorClassfull.addObserverModelChanged(observerClassUmlChanged);
    }
    return editorClassUml;
  }


  @Override
  public SrvEditClassFull<ClassUml, Frame> lazyGetSrvEditElementUml() {
    if(editClassUmlSrv == null) {
      SrvEditParameterMethod<ParameterMethod, Frame> srvEditParameterOperation = new SrvEditParameterMethod<ParameterMethod, Frame>(srvI18n, srvDialog, settingsGraphic);
      SrvEditMethodClass<MethodClass, Frame> editOperationClassSrv = 
          new SrvEditMethodClass<MethodClass, Frame>(srvI18n, srvDialog, settingsGraphic, srvEditParameterOperation);
      SrvEditAttributeClass<AttributeClass, Frame> srvEditAttributeClass = new SrvEditAttributeClass<AttributeClass, Frame>(srvI18n, srvDialog, settingsGraphic);
      editClassUmlSrv = new SrvEditClassFull<ClassUml, Frame> (srvI18n, srvDialog, 
          settingsGraphic, editOperationClassSrv, srvEditAttributeClass);
    }
    return editClassUmlSrv;
  }

  public IObserverModelChanged getObserverClassUmlChanged() {
    return observerClassUmlChanged;
  }

  public void setObserverClassUmlChanged(
      IObserverModelChanged observerClassUmlChanged) {
    this.observerClassUmlChanged = observerClassUmlChanged;
  }
}
