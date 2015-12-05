package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.service.edit.SrvEditAttributeClass;
import org.beigesoft.util.UtilsMisc;

public class EditorAttributeClass<M extends AttributeClass, DLI, AEI> extends EditorMemberClass<M, DLI, AEI> {
  
  private ITextField tfDefaultValue;

  private ITextField tfConstraintsValue;
  
  private ICheckBox cbIsOrdered;

  private ICheckBox cbIsUnique;

  private ITextField tfLower;
  
  private ITextField tfUpper;
  
  public EditorAttributeClass(DLI dli, SrvEditAttributeClass<M, DLI> srvEdit,
      String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public void doPostConstruct() {
    cbIsOrdered.setLabel(getSrvEdit().getSrvI18n().getMsg("isOrdered"));
    cbIsUnique.setLabel(getSrvEdit().getSrvI18n().getMsg("isUnique"));
    super.doPostConstruct();
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setDefaultValue(getModelClone().getDefaultValue());
    getModel().setConstraintsValue(getModelClone().getConstraintsValue());
    getModel().setMultiplicityElement(getModelClone().getMultiplicityElement().clone());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setDefaultValue(UtilsMisc.evalTextValue(tfDefaultValue.getText()));
    getModelClone().setConstraintsValue(UtilsMisc.evalTextValue(tfConstraintsValue.getText()));
    getModelClone().getMultiplicityElement().setIsOrdered(cbIsOrdered.getIsSelected());
    getModelClone().getMultiplicityElement().setIsUnique(cbIsUnique.getIsSelected());
    getModelClone().getMultiplicityElement().setLower(UtilsMisc.evalIntegerValue(tfLower.getText()));
    getModelClone().getMultiplicityElement().setUpper(UtilsMisc.evalIntegerValue(tfUpper.getText()));
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    tfDefaultValue.setText(getModelClone().getDefaultValue());
    tfConstraintsValue.setText(getModelClone().getConstraintsValue());
    cbIsOrdered.setIsSelected(getModelClone().getMultiplicityElement().getIsOrdered());
    cbIsUnique.setIsSelected(getModelClone().getMultiplicityElement().getIsUnique());
    tfLower.setText(UtilsMisc.integerToString(getModelClone().getMultiplicityElement().getLower()));
    tfUpper.setText(UtilsMisc.integerToString(getModelClone().getMultiplicityElement().getUpper()));
    super.refreshGui();
  }

  //SGS:
  public ITextField getTfDefaultValue() {
    return tfDefaultValue;
  }

  public void setTfDefaultValue(ITextField tfDefaultValue) {
    this.tfDefaultValue = tfDefaultValue;
  }

  public ITextField getTfConstraintsValue() {
    return tfConstraintsValue;
  }

  public void setTfConstraintsValue(ITextField tfConstraintsValue) {
    this.tfConstraintsValue = tfConstraintsValue;
  }

  public ICheckBox getCbIsOrdered() {
    return cbIsOrdered;
  }

  public void setCbIsOrdered(ICheckBox cbIsOrdered) {
    this.cbIsOrdered = cbIsOrdered;
  }

  public ICheckBox getCbIsUnique() {
    return cbIsUnique;
  }

  public void setCbIsUnique(ICheckBox cbIsUnique) {
    this.cbIsUnique = cbIsUnique;
  }

  public ITextField getTfLower() {
    return tfLower;
  }

  public void setTfLower(ITextField tfLower) {
    this.tfLower = tfLower;
  }

  public ITextField getTfUpper() {
    return tfUpper;
  }

  public void setTfUpper(ITextField tfUpper) {
    this.tfUpper = tfUpper;
  }
}
