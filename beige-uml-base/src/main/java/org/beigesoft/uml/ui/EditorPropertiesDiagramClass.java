package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.AEditor;
import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.uml.diagram.pojo.DiagramClass;
import org.beigesoft.uml.service.edit.SrvEditPropertiesDiagramClass;

public class EditorPropertiesDiagramClass<M extends DiagramClass, DLI, AEI>  extends AEditor<M, DLI, AEI> {

  private ICheckBox cbIsAbleToChangeAutomatically;
  
  public EditorPropertiesDiagramClass(DLI dli, SrvEditPropertiesDiagramClass<M, DLI> srvEdit, String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public void doPostConstruct() {
    cbIsAbleToChangeAutomatically.setLabel(getSrvEdit().getSrvI18n().getMsg("is_able_to_change_automatically"));
    super.doPostConstruct();
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setIsAbleToChangeByDoclet(getModelClone().getIsAbleToChangeByDoclet());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setIsAbleToChangeByDoclet(cbIsAbleToChangeAutomatically.getIsSelected());
  }

  @Override
  public void refreshGui() {
    cbIsAbleToChangeAutomatically.setIsSelected(getModelClone().getIsAbleToChangeByDoclet());
  }

  //SGS:
  public ICheckBox getCbIsAbleToChangeAutomatically() {
    return cbIsAbleToChangeAutomatically;
  }

  public void setCbIsAbleToChangeAutomatically(
      ICheckBox cbIsAbleToChangeAutomatically) {
    this.cbIsAbleToChangeAutomatically = cbIsAbleToChangeAutomatically;
  }
}
