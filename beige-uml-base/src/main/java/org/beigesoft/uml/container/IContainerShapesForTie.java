package org.beigesoft.uml.container;

import java.util.Collection;
import java.util.UUID;

import org.beigesoft.uml.assembly.ShapeFull;

public interface IContainerShapesForTie {

  /**
   * Used only UUID id to be tied with a TextUml
   * @param id
   * @return ShapeUml to be tied
   */
  public ShapeFull<?> getTiedShapeById(UUID id);
  
  /**
   * To be tied with a text or comment
   * @return
   */
  public Collection<ShapeFull<?>> getShapesForTie();
  
  public long getVersionShapesForTie();
}
