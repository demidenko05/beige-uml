/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.graphic.model;

import org.beigesoft.graphic.pojo.ColorRgb;


/**
 * <p>Represent a POJO of settings to draw</p>
 * 
 * @author Yury Demidenko
 */
public interface ISettingsDraw {
  
  //POJO:
  public boolean getIsDashed();

  public void setIsDashed(boolean isDashed);
    
  public ColorRgb getColor();
  
  public void setColor(ColorRgb color);

  public float getWidthLine();

  public void setWidthLine(float widthLine);

  public String getPathBackgroundImage();

  public void setPathBackgroundImage(String pathBackgroundImage);
}
