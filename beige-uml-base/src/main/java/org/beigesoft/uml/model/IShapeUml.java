package org.beigesoft.uml.model;

import org.beigesoft.graphic.model.IShape;

public interface IShapeUml extends IElementUml, IShape {

  public boolean getIsAdjustMinimumSize();
  
  public void setIsAdjustMinimumSize(boolean isAdjustMinimumSize);
}
