package org.beigesoft.graphic.pojo;

public class ColorRgb {
  
  public static final ColorRgb BLACK = new ColorRgb(0, 0, 0);
  
  public static final ColorRgb BLUE = new ColorRgb(40, 40, 244);

  public static final ColorRgb WHITE = new ColorRgb(255, 255, 255);

  private int red;
  
  private int green;
  
  private int blue;

  public ColorRgb() { }
  
  public ColorRgb(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  
  //SGS:
  public int getRed() {
    return red;
  }

  public void setRed(int red) {
    this.red = red;
  }

  public int getGreen() {
    return green;
  }

  public void setGreen(int green) {
    this.green = green;
  }

  public int getBlue() {
    return blue;
  }

  public void setBlue(int blue) {
    this.blue = blue;
  }  
}
