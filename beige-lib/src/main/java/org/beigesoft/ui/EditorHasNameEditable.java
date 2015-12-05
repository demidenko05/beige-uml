package org.beigesoft.ui;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.ui.service.edit.SrvEditHasNameEditable;
import org.beigesoft.ui.widget.AEditor;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.util.UtilsMisc;

public class EditorHasNameEditable<M extends HasNameEditable, DLI, AEI> extends AEditor<M, DLI, AEI> {
  
  private ITextField tfItsName;

  public EditorHasNameEditable(DLI dialogInstrument, SrvEditHasNameEditable<M, DLI> srvEdit, String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setItsName(getModelClone().getItsName());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setItsName(UtilsMisc.evalTextValue(tfItsName.getText()));
  }

  @Override
  public void refreshGui() {
    tfItsName.setText(getModelClone().getItsName());
  }

  public ITextField getTfValue() {
    return tfItsName;
  }

  public void setTfValue(ITextField tfValue) {
    this.tfItsName = tfValue;
  }
}
