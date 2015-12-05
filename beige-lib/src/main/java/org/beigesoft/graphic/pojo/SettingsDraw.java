package org.beigesoft.graphic.pojo;

import org.beigesoft.graphic.model.ISettingsDraw;

public class SettingsDraw implements ISettingsDraw {

  private boolean isDashed;
  
  private float widthLine;

  private ColorRgb color = ColorRgb.BLACK;
  
  private String pathBackgroundImage;
    
  @Override
  public boolean getIsDashed() {
    return isDashed;
  }

  @Override
  public void setIsDashed(boolean isDashed) {
    this.isDashed = isDashed;
  }

  @Override
  public ColorRgb getColor() {
    return color;
  }

  @Override
  public void setColor(ColorRgb color) {
    this.color = color;
  }

  @Override
  public float getWidthLine() {
    return widthLine;
  }

  @Override
  public void setWidthLine(float widthLine) {
    this.widthLine = widthLine;
  }

  @Override
  public String getPathBackgroundImage() {
    return pathBackgroundImage;
  }

  @Override
  public void setPathBackgroundImage(String pathBackgroundImage) {
    this.pathBackgroundImage = pathBackgroundImage;
  }
}
