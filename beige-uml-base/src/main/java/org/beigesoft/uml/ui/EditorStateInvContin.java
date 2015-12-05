package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.pojo.StateInvContin;
import org.beigesoft.uml.service.edit.SrvEditStateInvContin;
import org.beigesoft.util.UtilsMisc;

public class EditorStateInvContin<SH extends StateInvContin, DLI, AEI> extends AEditorElementUml<SH, DLI, AEI> {
  
  private ITextField tfItsName;
  
  private ICheckBox cbIsBold;

  public EditorStateInvContin(DLI dli, SrvEditStateInvContin<SH, DLI> srvEdit, String nameModel) {
    super(dli, srvEdit, nameModel);
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setItsName(UtilsMisc.evalTextValue(tfItsName.getText()));
    getModelClone().setIsBold(cbIsBold.getIsSelected());
    super.refreshModelClone();
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setItsName(getModelClone().getItsName());
    getModel().setIsBold(getModelClone().getIsBold());
    super.refreshModel();
  }
  
  @Override
  public void refreshGui() {
    tfItsName.setText(getModelClone().getItsName());
    cbIsBold.setIsSelected(getModelClone().getIsBold());
    super.refreshGui();
  }

  public ITextField getTfItsName() {
    return tfItsName;
  }

  public void setTfItsName(ITextField tfItsName) {
    this.tfItsName = tfItsName;
  }

  public ICheckBox getCbIsBold() {
    return cbIsBold;
  }

  public void setCbIsBold(ICheckBox cbIsBold) {
    this.cbIsBold = cbIsBold;
  }
}
