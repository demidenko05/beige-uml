package org.beigesoft.uml.test;

import static org.junit.Assert.*;

import java.util.UUID;

import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.container.IContainerShapesFullVarious;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.pojo.ShapeRelationshipVarious;
import org.junit.Test;

public class TestRelationshipBinaryVarious {

  @Test
  public void tst1() {
    RelationshipBinaryVarious classRel = new RelationshipBinaryVarious();
    ShapeRelationshipVarious shapeRelationshipStart = new ShapeRelationshipVarious();
    ClassUml sh = new ClassUml();
    ShapeFullVarious<ClassUml> classFull = new ShapeFullVarious<ClassUml>();
    classFull.setShape(sh);
    shapeRelationshipStart.setShapeFull(classFull);
    ShapeRelationshipVarious shapeRelationshipEnd = new ShapeRelationshipVarious();
    ContainerShapesFullVarious containerShapesFullVarious = new ContainerShapesFullVarious();
    shapeRelationshipEnd.setShapeFull(containerShapesFullVarious.getShapeFullVarById(null));
    classRel.setShapeRelationshipStart(shapeRelationshipStart);
    classRel.setShapeRelationshipEnd(shapeRelationshipEnd);
    assertTrue(classRel.getShapeRelationshipStart().getShapeFull().getShape().getClass() == ClassUml.class);
    assertTrue(classRel.getShapeRelationshipEnd().getShapeFull().getShape().getClass() == CommentUml.class);
  }

  private class ContainerShapesFullVarious implements IContainerShapesFullVarious<ShapeFullVarious<?>> {
    @Override
    public ShapeFullVarious<?> getShapeFullVarById(UUID id) {
      ShapeFullVarious<CommentUml> shapeFull = new ShapeFullVarious<CommentUml>();
      shapeFull.setShape(new CommentUml(10, 10));
      return shapeFull;
    }   
  }
}
