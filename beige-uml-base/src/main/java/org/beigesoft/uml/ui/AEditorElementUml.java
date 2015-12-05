package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.AEditor;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.model.IElementUml;
import org.beigesoft.uml.service.edit.ASrvEditElementUml;
import org.beigesoft.util.UtilsMisc;

public abstract class AEditorElementUml<M extends IElementUml, DLI, AEI> extends AEditor<M, DLI, AEI> {

  private ITextField tfIndexZ;

  public AEditorElementUml(DLI dialogInstrument, ASrvEditElementUml<M, DLI> srvEdit,
      String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setIndexZ(getModelClone().getIndexZ());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setIndexZ(UtilsMisc.evalIntegerValue(tfIndexZ.getText()));
  }

  @Override
  public void refreshGui() {
    tfIndexZ.setText(getModelClone().getIndexZ().toString());
  }

  //SGS:
  public ITextField getTfIndexZ() {
    return tfIndexZ;
  }

  public void setTfIndexZ(ITextField tfIndexZ) {
    this.tfIndexZ = tfIndexZ;
  }
}
