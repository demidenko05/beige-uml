package org.beigesoft.uml.container;

import java.util.UUID;

import org.beigesoft.uml.assembly.ShapeFull;

public interface IContainerShapesFullVarious<SHF extends ShapeFull<?>> {

  public SHF getShapeFullVarById(UUID id);
}
