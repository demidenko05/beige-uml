package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.IComboBox;
import org.beigesoft.uml.model.ELineEndShape;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.service.edit.SrvEditLineUml;

public class EditorLineUml<M extends LineUml, DLI, AEI> extends AEditorElementUml<M, DLI, AEI> {

  private ICheckBox cbIsDashed;
    
  private IComboBox<ELineEndShape> cmbPoint1End;

  private IComboBox<ELineEndShape> cmbPoint2End;

  public EditorLineUml(DLI dialogInstrument, SrvEditLineUml<M, DLI> srvEdit,
      String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setIsDashed(getModelClone().getIsDashed());
    getModel().setLineEnd1Shape(getModelClone().getLineEnd1Shape());
    getModel().setLineEnd2Shape(getModelClone().getLineEnd2Shape());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setIsDashed(cbIsDashed.getIsSelected());
    getModelClone().setLineEnd1Shape(cmbPoint1End.getSelectedItem());
    getModelClone().setLineEnd2Shape(cmbPoint2End.getSelectedItem());
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    cbIsDashed.setIsSelected(getModelClone().getIsDashed());
    cmbPoint1End.setSelectedItem(getModelClone().getLineEnd1Shape());
    cmbPoint2End.setSelectedItem(getModelClone().getLineEnd2Shape());
    super.refreshGui();
  }


  //SGS:
  public ICheckBox getCbIsDashed() {
    return cbIsDashed;
  }

  public void setCbIsDashed(ICheckBox cbIsDashed) {
    this.cbIsDashed = cbIsDashed;
  }

  public IComboBox<ELineEndShape> getCmbPoint1End() {
    return cmbPoint1End;
  }

  public void setCmbPoint1End(IComboBox<ELineEndShape> cmbPoint1End) {
    this.cmbPoint1End = cmbPoint1End;
  }

  public IComboBox<ELineEndShape> getCmbPoint2End() {
    return cmbPoint2End;
  }

  public void setCmbPoint2End(IComboBox<ELineEndShape> cmbPoint2End) {
    this.cmbPoint2End = cmbPoint2End;
  }
}
