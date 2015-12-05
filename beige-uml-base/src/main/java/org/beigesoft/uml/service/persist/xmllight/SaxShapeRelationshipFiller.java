package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;
import java.util.UUID;

import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.container.IContainerShapesFull;
import org.beigesoft.uml.pojo.ShapeRelationship;
import org.beigesoft.uml.pojo.ShapeUml;

public class SaxShapeRelationshipFiller<P extends ShapeRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> extends ASaxShapeRelationshipFiller<P> {

  private IContainerShapesFull<SHF, SH> containerShapesFull;
  
  public SaxShapeRelationshipFiller(String namePersistable,
      List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  protected void fillShape(UUID id) {
    getModel().setShapeFull(containerShapesFull.getShapeFullById(id));
  }

  //SGS:
  public IContainerShapesFull<SHF, SH> getContainerShapesFull() {
    return containerShapesFull;
  }

  public void setContainerShapesFull(IContainerShapesFull<SHF, SH> containerShapesFull) {
    this.containerShapesFull = containerShapesFull;
  }
}
