package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.IComboBox;
import org.beigesoft.ui.widget.IListWithEditor;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.service.edit.SrvEditClassFull;
import org.beigesoft.util.UtilsMisc;

public class EditorClassFull<CL extends ClassUml, DLI, AEI> extends EditorShapeWithNameFull<ClassFull<CL>, DLI, AEI, CL> {

  private ITextField tfPackageName;
  
  private IComboBox<EClassKind> cmbClasskind;
    
  private ICheckBox cbIsAdjustMinimumSize;
  
  private IListWithEditor<AttributeClass> listAttributes;
    
  private IButton<AEI> btAddAttribute;
  
  private IButton<AEI> btEditAttribute;
  
  private IButton<AEI> btDelAttribute;
  
  private IListWithEditor<MethodClass> listMethods;
    
  private IButton<AEI> btAddMethod;

  private IButton<AEI> btEditMethod;
  
  private IButton<AEI> btDelMethod;
  
  public EditorClassFull(DLI dli, SrvEditClassFull<CL, DLI> srvEdit) {
    super(dli, srvEdit, srvEdit.getSrvI18n().getMsg("class"));
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    cbIsAdjustMinimumSize.setLabel(getSrvEdit().getSrvI18n().getMsg("isAdjustMinimumSize"));
    btAddAttribute.setText(getSrvEdit().getSrvI18n().getMsg("add"));
    btEditAttribute.setText(getSrvEdit().getSrvI18n().getMsg("edit"));
    btDelAttribute.setText(getSrvEdit().getSrvI18n().getMsg("delete"));
    btAddMethod.setText(getSrvEdit().getSrvI18n().getMsg("add"));
    btEditMethod.setText(getSrvEdit().getSrvI18n().getMsg("edit"));
    btDelMethod.setText(getSrvEdit().getSrvI18n().getMsg("delete"));
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btAddAttribute.isPushed(eventInstrument)) {
      listAttributes.add(new AttributeClass());
      return true;
    }
    if(btEditAttribute.isPushed(eventInstrument)) {
      listAttributes.editSelectedRow();
      return true;
    }
    if(btDelAttribute.isPushed(eventInstrument)) {
      listAttributes.removeSelectedRow();
      return true;
    }
    if(btAddMethod.isPushed(eventInstrument)) {
      listMethods.add(new MethodClass());
      return true;
    }
    if(btEditMethod.isPushed(eventInstrument)) {
      listMethods.editSelectedRow();
      return true;
    }
    if(btDelMethod.isPushed(eventInstrument)) {
      listMethods.removeSelectedRow();
      return true;
    }
    return false;
  }

  @Override
  public void refreshModelClone() {
    super.refreshModelClone();
    getModelClone().getShape().setIsAdjustMinimumSize(cbIsAdjustMinimumSize.getIsSelected());
    getModelClone().getShape().setNamePackage(UtilsMisc.evalTextValue(tfPackageName.getText()));
    getModelClone().getShape().setClassKind(cmbClasskind.getSelectedItem());
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().getShape().setIsAdjustMinimumSize(getModelClone().getShape().getIsAdjustMinimumSize());
    getModel().getShape().setNamePackage(getModelClone().getShape().getNamePackage());
    getModel().getShape().setClassKind(getModelClone().getShape().getClassKind());
    getModel().getShape().getAttributes().clear();
    for(AttributeClass attrClass : getModelClone().getShape().getAttributes()) {
      getModel().getShape().getAttributes().add(attrClass.clone());
    }
    getModel().getShape().getMethods().clear();
    for(MethodClass operClass : getModelClone().getShape().getMethods()) {
      getModel().getShape().getMethods().add(operClass.clone());
    }
    super.refreshModel();
  }
  
  @Override
  public void refreshGui() {
    super.refreshGui();
    cbIsAdjustMinimumSize.setIsSelected(getModelClone().getShape().getIsAdjustMinimumSize());
    tfPackageName.setText(getModelClone().getShape().getNamePackage());
    cmbClasskind.setSelectedItem(getModelClone().getShape().getClassKind());
    listAttributes.replaceDataSource(getModelClone().getShape().getAttributes());
    listMethods.replaceDataSource(getModelClone().getShape().getMethods());
  }
  
  //SGS:
  public ICheckBox getCbIsAdjustMinimumSize() {
    return cbIsAdjustMinimumSize;
  }

  public void setCbIsAdjustMinimumSize(ICheckBox cbIsAdjustMinimumSize) {
    this.cbIsAdjustMinimumSize = cbIsAdjustMinimumSize;
  }

  public ITextField getTfPackageName() {
    return tfPackageName;
  }

  public void setTfPackageName(ITextField tfPackageName) {
    this.tfPackageName = tfPackageName;
  }

  public IComboBox<EClassKind> getCmbClasskind() {
    return cmbClasskind;
  }

  public void setCmbClasskind(IComboBox<EClassKind> cmbClasskind) {
    this.cmbClasskind = cmbClasskind;
  }

  public IButton<AEI> getBtAddAttribute() {
    return btAddAttribute;
  }

  public void setBtAddAttribute(IButton<AEI> btAddAttribute) {
    this.btAddAttribute = btAddAttribute;
  }

  public IButton<AEI> getBtDelAttribute() {
    return btDelAttribute;
  }

  public void setBtDelAttribute(IButton<AEI> btDelAttribute) {
    this.btDelAttribute = btDelAttribute;
  }

  public IButton<AEI> getBtAddMethod() {
    return btAddMethod;
  }

  public void setBtAddMethod(IButton<AEI> btAddMethod) {
    this.btAddMethod = btAddMethod;
  }

  public IButton<AEI> getBtDelMethod() {
    return btDelMethod;
  }

  public void setBtDelMethod(IButton<AEI> btDelMethod) {
    this.btDelMethod = btDelMethod;
  }

  public IListWithEditor<AttributeClass> getListAttributes() {
    return listAttributes;
  }

  public void setListAttributes(IListWithEditor<AttributeClass> listAttributes) {
    this.listAttributes = listAttributes;
  }

  public IListWithEditor<MethodClass> getListMethods() {
    return listMethods;
  }

  public void setListMethods(IListWithEditor<MethodClass> listMethods) {
    this.listMethods = listMethods;
  }

  public IButton<AEI> getBtEditAttribute() {
    return btEditAttribute;
  }

  public void setBtEditAttribute(IButton<AEI> btEditAttribute) {
    this.btEditAttribute = btEditAttribute;
  }

  public IButton<AEI> getBtEditMethod() {
    return btEditMethod;
  }

  public void setBtEditMethod(IButton<AEI> btEditMethod) {
    this.btEditMethod = btEditMethod;
  }
}
