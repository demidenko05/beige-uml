package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.service.edit.SrvEditRectangleUml;

public class EditorRectangleUml<M extends RectangleUml, DLI, AEI>  extends AEditorElementUml<M, DLI, AEI> {

  protected ICheckBox cbIsTransparent;
  
  public EditorRectangleUml(DLI dli, SrvEditRectangleUml<M, DLI> srvEdit, String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setIsTransparent(getModelClone().getIsTransparent());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setIsTransparent(cbIsTransparent.getIsSelected());
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    cbIsTransparent.setIsSelected(getModelClone().getIsTransparent());
    super.refreshGui();
  }

  //SGS:
  public ICheckBox getCbIsTransparent() {
    return cbIsTransparent;
  }

  public void setCbIsTransparent(ICheckBox cbIsTransparent) {
    this.cbIsTransparent = cbIsTransparent;
  }
}
