package org.beigesoft.uml.pojo;

import java.util.ArrayList;
import java.util.List;

import org.beigesoft.uml.assembly.ShapeFull;

public class RelationshipPoly<SHR extends ShapeRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> extends RelationshipBase {

  private List<SHR> shapesRelationship;
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public RelationshipPoly clone() {
    RelationshipPoly clone = (RelationshipPoly) super.clone();
    clone.setShapesRelationship(new ArrayList());
    clone.getShapesRelationship().addAll(shapesRelationship);
    return clone;
  }

  public RelationshipPoly() {
    shapesRelationship = new ArrayList<SHR>();
  }

  public List<SHR> getShapesRelationship() {
    return shapesRelationship;
  }

  public void setShapesRelationship(List<SHR> shapesRelationship) {
    this.shapesRelationship = shapesRelationship;
  }
}
