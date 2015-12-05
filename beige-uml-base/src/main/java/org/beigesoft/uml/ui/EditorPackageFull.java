package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.PackageUml;
import org.beigesoft.uml.service.edit.SrvEditPackageFull;
import org.beigesoft.util.UtilsMisc;

public class EditorPackageFull<M extends PackageUml, DLI, AEI> extends AEditorElementUml<ShapeFullVarious<M>, DLI, AEI> {

  private ITextField tfItsName;

  private ICheckBox cbIsNameInHead;

  private ITextField tfComment;

  public EditorPackageFull(DLI dialogInstrument, SrvEditPackageFull<M, DLI> srvEdit,
      String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().getShape().setItsName(getModelClone().getShape().getItsName());
    getModel().getShape().setComment(getModelClone().getShape().getComment());
    getModel().getShape().setIsNameInHead(getModelClone().getShape().getIsNameInHead());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().getShape().setComment(UtilsMisc.evalTextValue(tfComment.getText()));
    getModelClone().getShape().setItsName(UtilsMisc.evalTextValue(tfItsName.getText()));
    getModelClone().getShape().setIsNameInHead(cbIsNameInHead.getIsSelected());
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    tfComment.setText(getModelClone().getShape().getComment());
    tfItsName.setText(getModelClone().getShape().getItsName());
    cbIsNameInHead.setIsSelected(getModelClone().getShape().getIsNameInHead());
    super.refreshGui();
  }

  //SGS:
  public ITextField getTfItsName() {
    return tfItsName;
  }

  public void setTfItsName(ITextField tfItsName) {
    this.tfItsName = tfItsName;
  }

  public ICheckBox getCbIsNameInHead() {
    return cbIsNameInHead;
  }

  public void setCbIsNameInHead(ICheckBox cbIsNameInHead) {
    this.cbIsNameInHead = cbIsNameInHead;
  }

  public ITextField getTfComment() {
    return tfComment;
  }

  public void setTfComment(ITextField tfComment) {
    this.tfComment = tfComment;
  }
}
