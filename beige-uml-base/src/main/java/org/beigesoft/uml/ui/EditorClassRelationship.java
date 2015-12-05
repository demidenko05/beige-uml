package org.beigesoft.uml.ui;

import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.service.UtilsRectangleRelationship;
import org.beigesoft.uml.service.edit.SrvEditRectangleRelationship;

public class EditorClassRelationship<M extends RectangleRelationship<ClassFull<SH>, SH>, DLI, AEI, SH extends ClassUml> 
    extends EditorShapeRelationship<M, DLI, AEI, ClassFull<SH>, SH> {

  public EditorClassRelationship(DLI dli, SrvEditRectangleRelationship<M, DLI, ClassFull<SH>, SH> srvEdit, String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public void refreshModel() throws Exception {
    super.refreshModel();
    getModel().getShapeFull().getClassRelationsPoly().add(getModel());
    UtilsRectangleRelationship.evalPointJoint(getModel());
  }
}
