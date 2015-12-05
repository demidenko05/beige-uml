package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.AEditor;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.pojo.ParameterMethod;
import org.beigesoft.uml.service.edit.SrvEditParameterMethod;
import org.beigesoft.util.UtilsMisc;

public class EditorParameterMethod<M extends ParameterMethod, DLI, AEI> extends AEditor<M, DLI, AEI> {

  private ITextField tfItsName;

  private ITextField tfItsType;

  public EditorParameterMethod(DLI dli, SrvEditParameterMethod<M, DLI> srvEdit, String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setItsName(getModelClone().getItsName());
    getModel().setItsType(getModelClone().getItsType());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setItsName(UtilsMisc.evalTextValue(tfItsName.getText()));
    getModelClone().setItsType(UtilsMisc.evalTextValue(tfItsType.getText()));
  }

  @Override
  public void refreshGui() {
    tfItsName.setText(getModelClone().getItsName());
    tfItsType.setText(getModelClone().getItsType());
  }

  public ITextField getTfItsName() {
    return tfItsName;
  }

  public void setTfItsName(ITextField tfItsName) {
    this.tfItsName = tfItsName;
  }

  public ITextField getTfItsType() {
    return tfItsType;
  }

  public void setTfItsType(ITextField tfItsType) {
    this.tfItsType = tfItsType;
  }
}
