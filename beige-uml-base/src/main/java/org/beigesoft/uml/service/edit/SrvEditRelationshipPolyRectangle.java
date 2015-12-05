package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.RectangleRelationship;

public class SrvEditRelationshipPolyRectangle<RE extends RelationshipPoly<SHR, SHF, SH>, DLI, SHR extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends ASrvEditElementUml<RE, DLI> {

  private final SrvEditRectangleRelationship<SHR, DLI, SHF, SH> srvEditRectangleRelationship;
  
  public SrvEditRelationshipPolyRectangle(ISrvI18n srvI18n,
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
    if(m1.getSharedJoint().getTypeJoint() != m2.getSharedJoint().getTypeJoint()) {
      return false;
    }
    if(m1.getShapesRelationship() != null && m2.getShapesRelationship() != null) {
      if(m1.getShapesRelationship().size() != m2.getShapesRelationship().size()) {
        return false;
      }
      outer:
      for(SHR shr1 : m1.getShapesRelationship()) {
        for(SHR shr2 : m2.getShapesRelationship()) {
          if(srvEditRectangleRelationship.isEquals(shr1, shr2)) {
            continue outer;
          }
        }
        return false;
      }
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
    for(SHR shr : m.getShapesRelationship()) {
      srvEditRectangleRelationship.validate(shr, result);
    }
  }
}
