package org.beigesoft.uml.model;

import org.beigesoft.uml.assembly.IShapeFullUml;
import org.beigesoft.uml.pojo.ShapeUml;

public interface IShapeFullRelationship<SHF extends IShapeFullUml<SH>, SH extends ShapeUml> extends IShapeRelationship {

  public SHF getShapeFull();

  public void setShapeFull(SHF shapeFull);
}
