/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.graphic;

import org.beigesoft.graphic.pojo.Point2D;

/**
 * <p>Represent a drawable element</p>
 * 
 * @author Yury Demidenko
 */
public interface IDrawable<ITD> {
  
  public Integer getIndexZ();
  
  public void setIndexZ(Integer indexZ);
  
  public boolean getIsVisible();
  
  public void setIsVisible(boolean isVisible);
  
  //Delegate to service:
  public void draw(ITD instriumentDraw);

  public void recalculate(double coefficient);

  public Point2D evalMinimumScreenPoint();

  public Point2D evalMaximumScreenPoint();

  public boolean isContainsScreenPoint(int screenX, int screenY);
}
