package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.service.edit.SrvEditFrame;
import org.beigesoft.util.UtilsMisc;

public class EditorFrame<M extends FrameUml, DLI, AEI> extends AEditorElementUml<M, DLI, AEI> {

  private ITextField tfItsName;

  private ITextField tfItsKind;

  private ITextField tfParameters;

  public EditorFrame(DLI dialogInstrument, SrvEditFrame<M, DLI> srvEdit,
      String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setItsName(getModelClone().getItsName());
    getModel().setItsKind(getModelClone().getItsKind());
    getModel().setParameters(getModelClone().getParameters());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setParameters(UtilsMisc.evalTextValue(tfParameters.getText()));
    getModelClone().setItsName(UtilsMisc.evalTextValue(tfItsName.getText()));
    getModelClone().setItsKind(UtilsMisc.evalTextValue(tfItsKind.getText()));
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    tfParameters.setText(getModelClone().getParameters());
    tfItsName.setText(getModelClone().getItsName());
    tfItsKind.setText(getModelClone().getItsKind());
    super.refreshGui();
  }

  //SGS:
  public ITextField getTfItsName() {
    return tfItsName;
  }

  public void setTfItsName(ITextField tfItsName) {
    this.tfItsName = tfItsName;
  }

  public ITextField getTfItsKind() {
    return tfItsKind;
  }

  public void setTfItsKind(ITextField tfItsKind) {
    this.tfItsKind = tfItsKind;
  }

  public ITextField getTfParameters() {
    return tfParameters;
  }

  public void setTfParameters(ITextField tfParameters) {
    this.tfParameters = tfParameters;
  }
}
