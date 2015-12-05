package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.UseCase;
import org.beigesoft.uml.service.edit.SrvEditUseCaseFull;

public class EditorUseCaseFull<UC extends UseCase, DLI, AEI> extends EditorShapeWithNameFull<ShapeFullVarious<UC>, DLI, AEI, UC> {

  private ICheckBox cbIsRectangle;
  
  private ICheckBox cbIsAdjustMinimumSize;
  
  public EditorUseCaseFull(DLI dli, SrvEditUseCaseFull<UC, DLI> srvEdit) {
    super(dli, srvEdit, srvEdit.getSrvI18n().getMsg("use_case"));
  }

  @Override
  public void refreshModelClone() {
    super.refreshModelClone();
    getModelClone().getShape().setIsAdjustMinimumSize(cbIsAdjustMinimumSize.getIsSelected());
    getModelClone().getShape().setIsRectangle(cbIsRectangle.getIsSelected());
  }

  @Override
  public void refreshModel() throws Exception {
    super.refreshModel();
    getModel().getShape().setIsAdjustMinimumSize(getModelClone().getShape().getIsAdjustMinimumSize());
    getModel().getShape().setIsRectangle(getModelClone().getShape().getIsRectangle());
  }
  
  @Override
  public void refreshGui() {
    super.refreshGui();
    cbIsAdjustMinimumSize.setIsSelected(getModelClone().getShape().getIsAdjustMinimumSize());
    cbIsRectangle.setIsSelected(getModelClone().getShape().getIsRectangle());
  }
  
  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    cbIsRectangle.setLabel(getSrvEdit().getSrvI18n().getMsg("isRectangle"));
    cbIsAdjustMinimumSize.setLabel(getSrvEdit().getSrvI18n().getMsg("isAdjustMinimumSize"));
  }

  //SGS:
  public ICheckBox getCbIsRectangle() {
    return cbIsRectangle;
  }

  public void setCbIsRectangle(ICheckBox cbIsRectangle) {
    this.cbIsRectangle = cbIsRectangle;
  }

  public ICheckBox getCbIsAdjustMinimumSize() {
    return cbIsAdjustMinimumSize;
  }

  public void setCbIsAdjustMinimumSize(ICheckBox cbIsAdjustMinimumSize) {
    this.cbIsAdjustMinimumSize = cbIsAdjustMinimumSize;
  }

}
