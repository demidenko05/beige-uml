package org.beigesoft.uml.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.ClassRelationFull;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.service.UtilsRectangleRelationship;
import org.beigesoft.uml.service.UtilsUml;
import org.junit.Test;

public class TestRearrangeClasses {

  @Test
  public void tstRearrange() {
    SettingsGraphicUml gp = new SettingsGraphicUml();
    List<ClassFull<ClassUml>> classList = new ArrayList<ClassFull<ClassUml>>();
    ClassUml class1 = new ClassUml(EClassKind.CLASS);
    class1.setIsMain(true);
    class1.setItsName("class1");
    ClassFull<ClassUml> classFull1 = new ClassFull<ClassUml>(class1);
    ClassUml classExt1 = new ClassUml(EClassKind.CLASS);
    class1.setItsName("classExt1");
    ClassFull<ClassUml> classFullExt1 = new ClassFull<ClassUml>(classExt1);
    RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> relation = new RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, ClassFull<ClassUml>, ClassUml>();
    relation.setKind(ERelationshipKind.GENERALIZATION);
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> class1Rel = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>(classFull1);
    class1Rel.setEndType(ERelationshipEndType.END);
    UtilsRectangleRelationship.evalPointJoint(class1Rel);
    relation.setShapeRelationshipStart(class1Rel);
    classFull1.getRelationshipsBinary().add(new ClassRelationFull<ClassUml>(class1Rel, relation));
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> classExt1Rel = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>(classFullExt1);
    UtilsRectangleRelationship.evalPointJoint(classExt1Rel);
    relation.setShapeRelationshipEnd(classExt1Rel);
    classFullExt1.getRelationshipsBinary().add(new ClassRelationFull<ClassUml>(classExt1Rel, relation));
    classList.add(classFull1);
    classList.add(classFullExt1);
    Point2D pointStartCl1 = new Point2D(class1.getPointStart());
    Point2D pointStartClExt1 = new Point2D(classExt1.getPointStart());
    Point2D pointJointCl1 = new Point2D(class1Rel.getPointJoint());
    Point2D pointJointClExt1 = new Point2D(classExt1Rel.getPointJoint());
    assertTrue(class1.getLevelXOnDiagram() == 0);
    assertTrue(class1.getLevelYOnDiagram() == 0);
    assertTrue(classExt1.getLevelXOnDiagram() == 0);
    assertTrue(classExt1.getLevelYOnDiagram() == 0);
    UtilsUml.arrangeClassDiagram(classList, gp, 1);
    assertTrue(class1.getLevelXOnDiagram() == 0);
    assertTrue(class1.getLevelYOnDiagram() == 0);
    assertTrue(classExt1.getLevelXOnDiagram() == 0);
    assertTrue(classExt1.getLevelYOnDiagram() == 1);
    assertTrue(pointStartCl1.getY() != class1.getPointStart().getY());
    assertTrue(pointStartClExt1.getY() != classExt1.getPointStart().getY());
    assertTrue(pointJointCl1.getY() != class1Rel.getPointJoint().getY());
    assertTrue(pointJointClExt1.getY() != classExt1Rel.getPointJoint().getY());
  }
}
