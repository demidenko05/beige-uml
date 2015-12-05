package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;
import java.util.UUID;

import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.container.IContainerShapesFullVarious;
import org.beigesoft.uml.pojo.ShapeRelationshipVarious;

public class SaxShapeRelationshipVariousFiller<P extends ShapeRelationshipVarious, SHF extends ShapeFullVarious<?>> 
    extends ASaxShapeRelationshipFiller<P> {

  private IContainerShapesFullVarious<SHF> containerShapesFullVarious;
  
  public SaxShapeRelationshipVariousFiller(String namePersistable,
      List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  protected void fillShape(UUID id) {
    getModel().setShapeFull(containerShapesFullVarious.getShapeFullVarById(id));
  }

  //SGS:
  public IContainerShapesFullVarious<SHF> getContainerShapesFullVarious() {
    return containerShapesFullVarious;
  }

  public void setContainerShapesFullVarious(
      IContainerShapesFullVarious<SHF> containerShapesFullVarious) {
    this.containerShapesFullVarious = containerShapesFullVarious;
  }
}
