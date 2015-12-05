/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.beigesoft.graphic.pojo;

import org.beigesoft.graphic.model.EJoint2DType;


/**
 * <p>It joints two or more shapes</p>
 * 
 * @author Yury Demidenko
 */
public class Joint2D extends Point2D {
  
  private EJoint2DType typeJoint = EJoint2DType.POINT;

  public Joint2D() {
    super();
  }

  public Joint2D(double x, double y) {
    super(x, y);
  }

  public Joint2D(double x, double y, EJoint2DType jointType) {
    super(x, y);
    this.typeJoint = jointType;
  }

  public EJoint2DType getTypeJoint() {
    return typeJoint;
  }

  public void setTypeJoint(EJoint2DType typeJoint) {
    this.typeJoint = typeJoint;
  }
}
