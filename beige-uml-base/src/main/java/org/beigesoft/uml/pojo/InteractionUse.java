package org.beigesoft.uml.pojo;

import org.beigesoft.uml.model.IFragment;

public class InteractionUse extends ShapeUml implements IFragment {

  private String description;
  
  //unpersistable:
  private double widthHead;
  
  private double heightHead;
  
  public InteractionUse() {
    setIndexZ(1010);
  }
  
  @Override
  public String toString() {
    String str = description == null ? getClass().getSimpleName() + hashCode() : 
      description + " " + hashCode() + " " + getClass().getSimpleName();
    return str;
  }

  @Override
  public double getWidthHead() {
    return widthHead;
  }

  @Override
  public void setWidthHead(double widthHead) {
    this.widthHead = widthHead;
  }

  @Override
  public double getHeightHead() {
    return heightHead;
  }

  @Override
  public void setHeightHead(double heightHead) {
    this.heightHead = heightHead;
  }

  //SGS:
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
