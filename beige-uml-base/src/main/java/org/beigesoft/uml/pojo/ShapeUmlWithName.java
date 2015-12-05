package org.beigesoft.uml.pojo;

import org.beigesoft.uml.model.IShapeUmlWithName;


public class ShapeUmlWithName extends ShapeUml implements IShapeUmlWithName {

  private String itsName;

  @Override
  public String toString() {
    String str = itsName == null ? getClass().getSimpleName() + hashCode() : 
      itsName + " " + hashCode() + " " + getClass().getSimpleName();
    return str;
  }

  @Override
  public String getItsName() {
    return itsName;
  }

  @Override
  public void setItsName(String name) {
    this.itsName = name;
  }
}
