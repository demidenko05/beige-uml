package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.IComboBox;
import org.beigesoft.uml.model.EVisibilityKind;
import org.beigesoft.uml.pojo.MemberClass;
import org.beigesoft.uml.service.edit.SrvEditMemberClass;

public class EditorMemberClass<M extends MemberClass, DLI, AEI> extends EditorParameterMethod<M, DLI, AEI> {
  
  private IComboBox<EVisibilityKind> cmbVisibilityKind;

  public EditorMemberClass(DLI dli, SrvEditMemberClass<M, DLI> srvEdit,
      String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setVisibilityKind(getModelClone().getVisibilityKind());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setVisibilityKind(cmbVisibilityKind.getSelectedItem());
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    cmbVisibilityKind.setSelectedItem(getModelClone().getVisibilityKind());
    super.refreshGui();
  }

  //SGS:
  public IComboBox<EVisibilityKind> getCmbVisibilityKind() {
    return cmbVisibilityKind;
  }

  public void setCmbVisibilityKind(IComboBox<EVisibilityKind> cbVisibilityKind) {
    this.cmbVisibilityKind = cbVisibilityKind;
  }
}
