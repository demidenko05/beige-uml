/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.pojo;

import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.EJointSide;
import org.beigesoft.uml.model.IRectangleRelationship;

/**
 *  <p>Base abstraction of a relationship for a rectangle UML shape joined on perimeter</p>
 * 
 * 
 * @author Yury Demidenko
 */
public class RectangleRelationship<SHF extends ShapeFull<SH>, SH extends ShapeUml> extends ShapeRelationship<SHF, SH> implements IRectangleRelationship, Cloneable {
  
  private RectangleRelationshipBase rectangleRelationshipBase;
  
  public RectangleRelationship() {
    rectangleRelationshipBase = new RectangleRelationshipBase();
  }

  public RectangleRelationship(SHF shape) {
    super(shape);
    rectangleRelationshipBase = new RectangleRelationshipBase();
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public RectangleRelationship clone() {
    RectangleRelationship clone = (RectangleRelationship) super.clone();
    clone.rectangleRelationshipBase = rectangleRelationshipBase.clone();
    return clone;
  }

  /**
   * clockwise length of the class side
   */
  @Override
  public EJointSide getSideJoint() {
    return rectangleRelationshipBase.getSideJoint();
  }
  
  @Override
  public void setSideJoint(EJointSide sideJoint) {
    rectangleRelationshipBase.setSideJoint(sideJoint);
  }

  /**
   * clockwise length of the class side
   */
  @Override
  public double getSideLength() {
    return rectangleRelationshipBase.getSideLength();
  }

  @Override
  public void setSideLength(double sideLength) {
    rectangleRelationshipBase.setSideLength(sideLength);
  }
}
