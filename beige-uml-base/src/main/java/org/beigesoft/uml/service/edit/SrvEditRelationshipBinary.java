package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.ShapeRelationship;

public class SrvEditRelationshipBinary<RE extends RelationshipBinary<SHR, SHF, SH>, DLI, SHR extends ShapeRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends ASrvEditElementUml<RE, DLI> {

  public SrvEditRelationshipBinary(ISrvI18n srvI18n,
      ISrvDialog<DLI> srvDialog, SettingsGraphicUml graphicSettings) {
    super(srvI18n, srvDialog, graphicSettings);
  }
  
  @Override
  public boolean isEquals(RE m1, RE m2) {
    if(!super.isEquals(m1, m2)) {
      return false;
    }
    if(m1.getItsKind() != m2.getItsKind()) {
      return false;
    }
    if(m1.getShapeRelationshipStart().getEndType() != m2.getShapeRelationshipStart().getEndType()) {
      return false;
    }
    if(m1.getShapeRelationshipEnd().getEndType() != m2.getShapeRelationshipEnd().getEndType()) {
      return false;
    }
    if(m1.getShapeRelationshipStart().getShapeFull() == null ? m2.getShapeRelationshipStart().getShapeFull() != null : 
      !m1.getShapeRelationshipStart().getShapeFull().getId().equals(m2.getShapeRelationshipStart().getShapeFull().getId())) {
      return false;
    }
    if(m1.getShapeRelationshipEnd().getShapeFull() == null ? m2.getShapeRelationshipEnd().getShapeFull() != null : 
      !m1.getShapeRelationshipEnd().getShapeFull().getId().equals(m2.getShapeRelationshipEnd().getShapeFull().getId())) {
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
    if(m.getShapeRelationshipStart().getShapeFull() == null) {
      result.add(getSrvI18n().getMsg("complete_shape1"));
    }
    if(m.getShapeRelationshipEnd().getShapeFull() == null) {
      result.add(getSrvI18n().getMsg("complete_shape2"));
    }
  }
}
