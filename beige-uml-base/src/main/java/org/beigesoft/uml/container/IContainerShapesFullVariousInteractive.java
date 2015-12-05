package org.beigesoft.uml.container;

import java.util.Collection;

import org.beigesoft.uml.assembly.ShapeFullVarious;

public interface IContainerShapesFullVariousInteractive<SHF extends ShapeFullVarious<?>> extends IContainerShapesFullVarious<SHF> {
  
  public SHF getShapeFullAt(int screenX, int screenY);
  
  public Collection<SHF> getShapesFull();
  
  public long getVersionShapesFull();
}
