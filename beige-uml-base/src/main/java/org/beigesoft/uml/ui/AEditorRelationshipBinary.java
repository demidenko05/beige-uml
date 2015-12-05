package org.beigesoft.uml.ui;

import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.AEditor;
import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.IComboBox;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.model.IRelationshipBinary;

public abstract class AEditorRelationshipBinary<M extends IRelationshipBinary, DLI, AEI> extends AEditor<M, DLI, AEI> {

  private IComboBox<ERelationshipKind> cmbRelationshipKind;
  
  private ITextField tfShape1;
  
  private IButton<AEI> btShape1;

  private ITextField tfShape2;
  
  private IButton<AEI> btShape2;

  private ICheckBox cbIsOwned1;

  private IComboBox<ERelationshipEndType> cmbRelationshipEnd1;
  
  private ICheckBox cbIsOwned2;

  private IComboBox<ERelationshipEndType> cmbRelationshipEnd2;
  
  public AEditorRelationshipBinary(DLI dli, ISrvEdit<M, DLI> srvEdit, String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setKind(getModelClone().getItsKind());
    getModel().getShapeRelationshipStart().setEndType(getModelClone().getShapeRelationshipStart().getEndType());
    getModel().getShapeRelationshipStart().setIsOwned(getModelClone().getShapeRelationshipStart().getIsOwned());
    getModel().getShapeRelationshipEnd().setEndType(getModelClone().getShapeRelationshipEnd().getEndType());
    getModel().getShapeRelationshipEnd().setIsOwned(getModelClone().getShapeRelationshipEnd().getIsOwned());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setKind((ERelationshipKind) cmbRelationshipKind.getSelectedItem());
    getModelClone().getShapeRelationshipStart().setEndType(cmbRelationshipEnd1.getSelectedItem());
    getModelClone().getShapeRelationshipStart().setIsOwned(cbIsOwned1.getIsSelected());
    getModelClone().getShapeRelationshipEnd().setEndType((ERelationshipEndType) cmbRelationshipEnd2.getSelectedItem());
    getModelClone().getShapeRelationshipEnd().setIsOwned(cbIsOwned2.getIsSelected());
  }

  @Override
  public void refreshGui() {
    tfShape1.setText(getModelClone().getShapeRelationshipStart().getShape() == null ? "" :
      getModelClone().getShapeRelationshipStart().getShape().toString());
    tfShape2.setText(getModelClone().getShapeRelationshipEnd().getShape() == null ? "" :
      getModelClone().getShapeRelationshipEnd().getShape().toString());
    cmbRelationshipKind.setSelectedItem(getModelClone().getItsKind());
    cmbRelationshipEnd1.setSelectedItem(getModelClone().getShapeRelationshipStart().getEndType());
    cbIsOwned1.setIsSelected(getModelClone().getShapeRelationshipStart().getIsOwned());
    cmbRelationshipEnd2.setSelectedItem(getModelClone().getShapeRelationshipEnd().getEndType());
    cbIsOwned2.setIsSelected(getModelClone().getShapeRelationshipEnd().getIsOwned());
  }

  //SGS:
  public IComboBox<ERelationshipKind> getCmbRelationshipKind() {
    return cmbRelationshipKind;
  }

  public void setCmbRelationshipKind(
      IComboBox<ERelationshipKind> cmbRelationshipKind) {
    this.cmbRelationshipKind = cmbRelationshipKind;
  }

  public ITextField getTfShape1() {
    return tfShape1;
  }

  public void setTfShape1(ITextField tfShape1) {
    this.tfShape1 = tfShape1;
  }

  public IButton<AEI> getBtShape1() {
    return btShape1;
  }

  public void setBtShape1(IButton<AEI> btShape1) {
    this.btShape1 = btShape1;
  }

  public ITextField getTfShape2() {
    return tfShape2;
  }

  public void setTfShape2(ITextField tfShape2) {
    this.tfShape2 = tfShape2;
  }

  public IButton<AEI> getBtShape2() {
    return btShape2;
  }

  public void setBtShape2(IButton<AEI> btShape2) {
    this.btShape2 = btShape2;
  }

  public IComboBox<ERelationshipEndType> getCmbRelationshipEnd1() {
    return cmbRelationshipEnd1;
  }

  public void setCmbRelationshipEnd1(
      IComboBox<ERelationshipEndType> cmbRelationshipEnd1) {
    this.cmbRelationshipEnd1 = cmbRelationshipEnd1;
  }

  public IComboBox<ERelationshipEndType> getCmbRelationshipEnd2() {
    return cmbRelationshipEnd2;
  }

  public void setCmbRelationshipEnd2(
      IComboBox<ERelationshipEndType> cmbRelationshipEnd2) {
    this.cmbRelationshipEnd2 = cmbRelationshipEnd2;
  }

  public ICheckBox getCbIsOwned1() {
    return cbIsOwned1;
  }

  public void setCbIsOwned1(ICheckBox cbIsOwned1) {
    this.cbIsOwned1 = cbIsOwned1;
  }

  public ICheckBox getCbIsOwned2() {
    return cbIsOwned2;
  }

  public void setCbIsOwned2(ICheckBox cbIsOwned2) {
    this.cbIsOwned2 = cbIsOwned2;
  }
}
