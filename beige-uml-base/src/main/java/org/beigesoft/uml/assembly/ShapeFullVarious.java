package org.beigesoft.uml.assembly;

import java.util.Collection;
import java.util.HashSet;
import org.beigesoft.uml.pojo.ShapeRelationshipVarious;
import org.beigesoft.uml.pojo.ShapeUml;

/**
 * Model for UML elements like Actor or UseCase
 * @author yury
 *
 * @param <SH>
 */
public class ShapeFullVarious<SH extends ShapeUml> extends ShapeFull<SH> {
      
  /**
   * to move binary-relationships float(non-perimeter) joint-points start:
   */
  private final Collection<ShapeRelationshipVarious> relationshipsVariousStart;
    
  /**
   * to move binary-relationships float(non-perimeter) joint-point end:
   */
  private final Collection<ShapeRelationshipVarious> relationshipsVariousEnd;
        
  public ShapeFullVarious() {
    relationshipsVariousStart = new HashSet<ShapeRelationshipVarious>();
    relationshipsVariousEnd = new HashSet<ShapeRelationshipVarious>();
  }

  //SGS:
  public Collection<ShapeRelationshipVarious> getRelationshipsVariousStart() {
    return relationshipsVariousStart;
  }

  public Collection<ShapeRelationshipVarious> getRelationshipsVariousEnd() {
    return relationshipsVariousEnd;
  }
}
