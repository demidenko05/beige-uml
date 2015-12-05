package org.beigesoft.uml.pojo;

public class RectangleUml extends ShapeUml {

  private boolean isTransparent = true;

  public RectangleUml() {
    setIndexZ(1300);
  }
  
  public boolean getIsTransparent() {
    return isTransparent;
  }

  public void setIsTransparent(boolean isTransparent) {
    this.isTransparent = isTransparent;
  }
}
