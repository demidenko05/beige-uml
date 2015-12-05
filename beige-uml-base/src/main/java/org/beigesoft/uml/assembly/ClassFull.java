package org.beigesoft.uml.assembly;

import java.util.Collection;
import java.util.HashSet;

import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;

public class ClassFull<CL extends ClassUml> extends ShapeFull<CL> {
  
  /**
   * to move self-relationships shared-joint-point:
   */
  private final Collection<RelationshipSelf<?, ?, CL>> relationshipsSelf;
  
  private final Collection<RectangleRelationship<ClassFull<CL>, CL>> classRelationsPoly;
  
  /**
   * to move and to adjust/rearange class binary relationships without shared-joint:
   */
  private final Collection<ClassRelationFull<CL>> relationshipsBinary;
  
  public ClassFull() {
    relationshipsSelf = new HashSet<RelationshipSelf<?, ?, CL>>();
    relationshipsBinary = new HashSet<ClassRelationFull<CL>>();
    classRelationsPoly = new HashSet<RectangleRelationship<ClassFull<CL>,CL>>();
  }

  public ClassFull(CL classUml) {
    this();
    setShape(classUml);
  }

  public Collection<RelationshipSelf<?, ?, CL>> getRelationshipsSelf() {
    return relationshipsSelf;
  }

  public Collection<ClassRelationFull<CL>> getRelationshipsBinary() {
    return relationshipsBinary;
  }

  public Collection<RectangleRelationship<ClassFull<CL>, CL>> getClassRelationsPoly() {
    return classRelationsPoly;
  }
}
