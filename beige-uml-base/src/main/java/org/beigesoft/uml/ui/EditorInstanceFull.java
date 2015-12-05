package org.beigesoft.uml.ui;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.IListWithEditor;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.model.InstanceUml;
import org.beigesoft.uml.service.edit.SrvEditInstanceFull;
import org.beigesoft.util.UtilsMisc;

public class EditorInstanceFull<M extends InstanceUml, DLI, AEI> extends AEditorElementUml<ShapeFullVarious<M>, DLI, AEI> {

  private ITextField tfItsName;

  private ITextField tfNameClass;

  private ITextField tfValue;

  private ICheckBox cbIsAdjustMinimumSize;
  
  private IButton<AEI> btAddMember;
  
  private IButton<AEI> btEditMember;
  
  private IButton<AEI> btDelMember;
  
  private IListWithEditor<HasNameEditable> listStructure;

  public EditorInstanceFull(DLI dialogInstrument, SrvEditInstanceFull<M, DLI> srvEdit,
      String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    cbIsAdjustMinimumSize.setLabel(getSrvEdit().getSrvI18n().getMsg("isAdjustMinimumSize"));
    btAddMember.setText(getSrvEdit().getSrvI18n().getMsg("add"));
    btEditMember.setText(getSrvEdit().getSrvI18n().getMsg("edit"));
    btDelMember.setText(getSrvEdit().getSrvI18n().getMsg("delete"));
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btAddMember.isPushed(eventInstrument)) {
      listStructure.add(new HasNameEditable());
      return true;
    }
    if(btEditMember.isPushed(eventInstrument)) {
      listStructure.editSelectedRow();
      return true;
    }
    if(btDelMember.isPushed(eventInstrument)) {
      listStructure.removeSelectedRow();
      return true;
    }
    return false;
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().getShape().setIsAdjustMinimumSize(getModelClone().getShape().getIsAdjustMinimumSize());
    getModel().getShape().setItsName(getModelClone().getShape().getItsName());
    getModel().getShape().setNameClass(getModelClone().getShape().getNameClass());
    getModel().getShape().setValue(getModelClone().getShape().getValue());
    getModel().getShape().getStructure().clear();
    for(HasNameEditable ep : getModelClone().getShape().getStructure()) {
      getModel().getShape().getStructure().add(ep.clone());
    }
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().getShape().setIsAdjustMinimumSize(cbIsAdjustMinimumSize.getIsSelected());
    getModelClone().getShape().setValue(UtilsMisc.evalTextValue(tfValue.getText()));
    getModelClone().getShape().setNameClass(UtilsMisc.evalTextValue(tfNameClass.getText()));
    getModelClone().getShape().setItsName(UtilsMisc.evalTextValue(tfItsName.getText()));
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    cbIsAdjustMinimumSize.setIsSelected(getModelClone().getShape().getIsAdjustMinimumSize());
    tfValue.setText(getModelClone().getShape().getValue());
    tfNameClass.setText(getModelClone().getShape().getNameClass());
    tfItsName.setText(getModelClone().getShape().getItsName());
    listStructure.replaceDataSource(getModelClone().getShape().getStructure());
    super.refreshGui();
  }

  //SGS:
  public ITextField getTfItsName() {
    return tfItsName;
  }

  public void setTfItsName(ITextField tfItsName) {
    this.tfItsName = tfItsName;
  }

  public ITextField getTfValue() {
    return tfValue;
  }

  public void setTfValue(ITextField tfValue) {
    this.tfValue = tfValue;
  }

  public IButton<AEI> getBtAddMember() {
    return btAddMember;
  }

  public void setBtAddMember(IButton<AEI> btAddMember) {
    this.btAddMember = btAddMember;
  }

  public IButton<AEI> getBtEditMember() {
    return btEditMember;
  }

  public void setBtEditMember(IButton<AEI> btEditMember) {
    this.btEditMember = btEditMember;
  }

  public IButton<AEI> getBtDelMember() {
    return btDelMember;
  }

  public void setBtDelMember(IButton<AEI> btDelMember) {
    this.btDelMember = btDelMember;
  }

  public IListWithEditor<HasNameEditable> getListStructure() {
    return listStructure;
  }

  public void setListStructure(IListWithEditor<HasNameEditable> listStructure) {
    this.listStructure = listStructure;
  }

  public ITextField getTfNameClass() {
    return tfNameClass;
  }

  public void setTfNameClass(ITextField tfNameClass) {
    this.tfNameClass = tfNameClass;
  }

  public ICheckBox getCbIsAdjustMinimumSize() {
    return cbIsAdjustMinimumSize;
  }

  public void setCbIsAdjustMinimumSize(ICheckBox cbIsAdjustMinimumSize) {
    this.cbIsAdjustMinimumSize = cbIsAdjustMinimumSize;
  }
}
