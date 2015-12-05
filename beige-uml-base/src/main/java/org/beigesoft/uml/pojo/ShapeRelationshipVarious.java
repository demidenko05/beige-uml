package org.beigesoft.uml.pojo;

import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.model.AShapeRelationshipBase;

public class ShapeRelationshipVarious extends AShapeRelationshipBase {

  private ShapeFullVarious<?> shapeFull;

  public ShapeFullVarious<?> getShapeFull() {
    return shapeFull;
  }

  public void setShapeFull(ShapeFullVarious<?> shapeFull) {
    this.shapeFull = shapeFull;
  }

  @Override
  public ShapeUml getShape() {
    return shapeFull == null ? null : shapeFull.getShape();
  }
}
