package org.beigesoft.uml.pojo;

public class Execution implements Cloneable {

  private double startY;
  
  private double endY;

  @Override
  public Execution clone() {
    try {
      return (Execution) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return String.format("s %1$.3f, e %2$.3f", startY, endY);
  }

  public double getStartY() {
    return startY;
  }

  public void setStartY(double startY) {
    this.startY = startY;
  }

  public double getEndY() {
    return endY;
  }

  public void setEndY(double endY) {
    this.endY = endY;
  }
}
