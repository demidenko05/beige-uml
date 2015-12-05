package org.beigesoft.uml.pojo;

public class Actor extends ShapeUmlWithName {

  private String pathImage;
  
  @Override
  public Actor clone() {
    Actor clone;
    clone = (Actor) super.clone();
    return clone;
  }

  //SGS:
  public String getPathImage() {
    return pathImage;
  }

  public void setPathImage(String pathImage) {
    this.pathImage = pathImage;
  }
}
