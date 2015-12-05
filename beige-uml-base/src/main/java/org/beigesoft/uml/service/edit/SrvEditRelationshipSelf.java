package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.RectangleRelationship;

public class SrvEditRelationshipSelf<RE extends RelationshipSelf<SHR, SHF, SH>, DLI, SHR extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends ASrvEditElementUml<RE, DLI> {

  private final SrvEditRectangleRelationship<SHR, DLI, SHF, SH> srvEditRectangleRelationship;
  
  public SrvEditRelationshipSelf(ISrvI18n srvI18n,
      ISrvDialog<DLI> srvDialog, SettingsGraphicUml graphicSettings, SrvEditRectangleRelationship<SHR, DLI, SHF, SH> srvEditRectangleRelationship) {
    super(srvI18n, srvDialog, graphicSettings);
    this.srvEditRectangleRelationship = srvEditRectangleRelationship;
  }
  
  @Override
  public boolean isEquals(RE m1, RE m2) {
    if(!super.isEquals(m1, m2)) {
      return false;
    }
    if(m1.getItsKind() != m2.getItsKind()) {
      return false;
    }
    if(!srvEditRectangleRelationship.isEquals(m1.getShapeRelationshipStart(), m2.getShapeRelationshipStart())) {
      return false;
    }
    if(!srvEditRectangleRelationship.isEquals(m1.getShapeRelationshipEnd(), m2.getShapeRelationshipEnd())) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public RE createClone(RE m) {
    return (RE) m.clone();
  }

  @Override
  public void validate(RE m, Set<String> result) {
    srvEditRectangleRelationship.validate(m.getShapeRelationshipStart(), result);
    srvEditRectangleRelationship.validate(m.getShapeRelationshipEnd(), result);
  }
}
