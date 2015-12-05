package org.beigesoft.uml.pojo;

public class UseCase extends ShapeUmlWithName {
  
  private boolean isRectangle;
  
  @Override
  public UseCase clone() {
    UseCase clone;
    clone = (UseCase) super.clone();
    return clone;
  }

  public boolean getIsRectangle() {
    return isRectangle;
  }

  public void setIsRectangle(boolean isRectangle) {
    this.isRectangle = isRectangle;
  }
}
