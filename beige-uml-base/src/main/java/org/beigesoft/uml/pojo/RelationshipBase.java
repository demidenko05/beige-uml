package org.beigesoft.uml.pojo;

import org.beigesoft.graphic.pojo.Joint2D;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.model.ElementUml;
import org.beigesoft.uml.model.IRelationship;

public class RelationshipBase extends ElementUml implements Cloneable, IRelationship {
  
  private Joint2D jointShared;
  
  private ERelationshipKind kindRelationship;
  
  //unpersistable
  private double minBusCoord = 9999;
  
  private double maxBusCoord = -9999;

  public RelationshipBase() {
    setIndexZ(700);
  }
  
  @Override
  public String toString() {
    return getClass().getSimpleName() + " " + kindRelationship;
  }

  @Override
  public RelationshipBase clone() {
    RelationshipBase clone = (RelationshipBase) super.clone();
    if(jointShared != null) {
      clone.setSharedJoint((Joint2D) jointShared.clone());
    }
    return clone;
  }

  @Override
  public Joint2D getSharedJoint() {
    return jointShared;
  }

  @Override
  public void setSharedJoint(Joint2D jointShared) {
    this.jointShared = jointShared;
  }

  @Override
  public ERelationshipKind getItsKind() {
    return kindRelationship;
  }

  @Override
  public void setKind(ERelationshipKind kindRelationship) {
    this.kindRelationship = kindRelationship;
  }

  public double getMinBusCoord() {
    return minBusCoord;
  }

  public void setMinBusCoord(double minBusCoord) {
    this.minBusCoord = minBusCoord;
  }

  public double getMaxBusCoord() {
    return maxBusCoord;
  }

  public void setMaxBusCoord(double maxBusCoord) {
    this.maxBusCoord = maxBusCoord;
  }
}
