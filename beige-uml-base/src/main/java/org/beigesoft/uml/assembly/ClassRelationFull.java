package org.beigesoft.uml.assembly;

import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;

public class ClassRelationFull<CL extends ClassUml> {

  private RectangleRelationship<ClassFull<CL>, CL> classRelationship;
  
  private RelationshipBinary<RectangleRelationship<ClassFull<CL>, CL>, ClassFull<CL>, CL> relationship;

  public ClassRelationFull(RectangleRelationship<ClassFull<CL>, CL> classRelationship,
      RelationshipBinary<RectangleRelationship<ClassFull<CL>, CL>, ClassFull<CL>, CL> relationship) {
    this.classRelationship = classRelationship;
    this.relationship = relationship;
  }

  public RectangleRelationship<ClassFull<CL>, CL> getClassRelationship() {
    return classRelationship;
  }

  public void setClassRelationship(RectangleRelationship<ClassFull<CL>, CL> classRelationship) {
    this.classRelationship = classRelationship;
  }

  public RelationshipBinary<RectangleRelationship<ClassFull<CL>, CL>, ClassFull<CL>, CL> getRelationship() {
    return relationship;
  }

  public void setRelationship(RelationshipBinary<RectangleRelationship<ClassFull<CL>, CL>, ClassFull<CL>, CL> relationship) {
    this.relationship = relationship;
  }
}
