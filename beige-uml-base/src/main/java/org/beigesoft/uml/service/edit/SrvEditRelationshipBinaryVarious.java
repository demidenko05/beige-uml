package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;

public class SrvEditRelationshipBinaryVarious<M extends RelationshipBinaryVarious, DLI> extends ASrvEditElementUml<M, DLI> {

  public SrvEditRelationshipBinaryVarious(ISrvI18n srvI18n,
      ISrvDialog<DLI> srvDialog, SettingsGraphicUml graphicSettings) {
    super(srvI18n, srvDialog, graphicSettings);
  }
  
  @Override
  public boolean isEquals(M m1, M m2) {
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
    if(m1.getShapeRelationshipEnd().getIsOwned() != m2.getShapeRelationshipEnd().getIsOwned()) {
      return false;
    }
    if(m1.getShapeRelationshipStart().getIsOwned() != m2.getShapeRelationshipStart().getIsOwned()) {
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
  public M createClone(M m) {
    return (M) m.clone();
  }

  @Override
  public void validate(M m, Set<String> result) {
    if(m.getShapeRelationshipStart().getShapeFull() == null) {
      result.add(getSrvI18n().getMsg("complete_shape1"));
    }
    if(m.getShapeRelationshipEnd().getShapeFull() == null) {
      result.add(getSrvI18n().getMsg("complete_shape2"));
    }
  }
}
