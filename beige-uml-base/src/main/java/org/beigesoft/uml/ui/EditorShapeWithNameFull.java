package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.service.edit.SrvEditShapeWithNameFull;
import org.beigesoft.util.UtilsMisc;

public class EditorShapeWithNameFull<SHF extends ShapeFull<SH>, DLI, AEI, SH extends ShapeUmlWithName> 
    extends AEditorElementUml<SHF, DLI, AEI> {
  
  private ITextField taName;
  
  public EditorShapeWithNameFull(DLI dli, SrvEditShapeWithNameFull<SHF, DLI, SH> srvEdit, String nameModel) {
    super(dli, srvEdit, nameModel);
  }

  @Override
  public void refreshModelClone() {
    getModelClone().getShape().setItsName(UtilsMisc.evalTextValue(taName.getText()));
    super.refreshModelClone();
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().getShape().setItsName(getModelClone().getShape().getItsName());
    super.refreshModel();
  }
  
  @Override
  public void refreshGui() {
    taName.setText(getModelClone().getShape().getItsName());
    super.refreshGui();
  }

  public ITextField getTaName() {
    return taName;
  }

  public void setTaName(ITextField taName) {
    this.taName = taName;
  }
}
