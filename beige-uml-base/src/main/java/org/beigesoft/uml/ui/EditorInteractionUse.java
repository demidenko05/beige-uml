package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.service.edit.SrvEditInteractionUse;
import org.beigesoft.util.UtilsMisc;

public class EditorInteractionUse<M extends InteractionUse, DLI, AEI> extends AEditorElementUml<M, DLI, AEI> {

  private ITextField tfDescription;

  public EditorInteractionUse(DLI dialogInstrument, SrvEditInteractionUse<M, DLI> srvEdit,
      String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setDescription(getModelClone().getDescription());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setDescription(UtilsMisc.evalTextValue(tfDescription.getText()));
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    tfDescription.setText(getModelClone().getDescription());
    super.refreshGui();
  }

  //SGS:
  public ITextField getTfDescription() {
    return tfDescription;
  }

  public void setTfDescription(ITextField tfDescription) {
    this.tfDescription = tfDescription;
  }
}
