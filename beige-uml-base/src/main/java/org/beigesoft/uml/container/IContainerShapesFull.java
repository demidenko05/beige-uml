package org.beigesoft.uml.container;

import java.util.UUID;

import org.beigesoft.uml.assembly.IShapeFullUml;
import org.beigesoft.uml.pojo.ShapeUml;

public interface IContainerShapesFull<SHF extends IShapeFullUml<SH>, SH extends ShapeUml> {

  public SHF getShapeFullById(UUID id);
}
