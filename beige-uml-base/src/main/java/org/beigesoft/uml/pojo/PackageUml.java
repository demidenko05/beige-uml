package org.beigesoft.uml.pojo;

import org.beigesoft.uml.model.IFragment;

public class PackageUml extends ShapeUmlWithName implements IFragment {
  
  private boolean isNameInHead;
  
  private String comment;
  
  //unpersistable:
  private double widthHead;
  
  private double heightHead;
  
  public PackageUml() {
    setIndexZ(200);
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
  public boolean getIsNameInHead() {
    return isNameInHead;
  }

  public void setIsNameInHead(boolean isNameInHead) {
    this.isNameInHead = isNameInHead;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
