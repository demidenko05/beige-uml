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


/**
 * <p>Represent a interactive drawable</p>
 * 
 * @author Yury Demidenko
 */
public interface IDrawableInteractive<DI> extends IDrawable<DI>{
  
  public void makeIsSelected(boolean selected);

  public void move(double deltaX, double deltaY);
  
  public boolean move(int screenWasX, int screenWasY, int screenX, int screenY);
  
  public void startEdit();

  public boolean getIsSelected();

  public boolean handleStopDraggingAt(int mouseWasAtX, int mouseWasAtY);
  
  public boolean resize(int screenWasX, int screenWasY, int screenX, int screenY);
  
  public boolean isContainsScreenForManipulate(int screenX, int screenY);
}
