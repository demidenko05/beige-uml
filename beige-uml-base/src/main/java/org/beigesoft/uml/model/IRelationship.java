package org.beigesoft.uml.model;

import org.beigesoft.graphic.pojo.Joint2D;

public interface IRelationship extends IElementUml {

  public Joint2D getSharedJoint();

  public ERelationshipKind getItsKind();

  public void setSharedJoint(Joint2D jointShared);

  public void setKind(ERelationshipKind kindRelationship);
}
