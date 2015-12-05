/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.pojo;

import java.util.UUID;

import org.beigesoft.graphic.pojo.Shape;
import org.beigesoft.uml.model.ElementUml;
import org.beigesoft.uml.model.IShapeUml;

/**
 * <p>Base abstraction of a UML shape element(Class, Comment...)</p>
 * 
 * 
 * @author Yury Demidenko
 */
public class ShapeUml extends Shape implements IShapeUml, Cloneable {
  
  private ElementUml elementUml = new ElementUml();

  private boolean isAdjustMinimumSize = true;
  
  public ShapeUml() {
    setIndexZ(500);
  }
  
  @Override
  public ShapeUml clone() {
    try {
      ShapeUml clone = (ShapeUml) super.clone();
      clone.elementUml = elementUml.clone();
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public UUID getId() {
    return elementUml.getId();
  }

  @Override
  public void setId(UUID id) {
    elementUml.setId(id);
  }

  @Override
  public boolean getIsNew() {
    return elementUml.getIsNew();
  }

  @Override
  public boolean getIsSelected() {
    return elementUml.getIsSelected();
  }

  @Override
  public void setIsSelected(boolean isSelected) {
    elementUml.setIsSelected(isSelected);
  }

  @Override
  public void setIsNew(boolean isNew) {
    elementUml.setIsNew(isNew);
  }  

  @Override
  public boolean getIsAdjustMinimumSize() {
    return isAdjustMinimumSize;
  }

  @Override
  public void setIsAdjustMinimumSize(boolean isAdjustMinimumSize) {
    this.isAdjustMinimumSize = isAdjustMinimumSize;
  }

  @Override
  public Integer getIndexZ() {
    return elementUml.getIndexZ();
  }

  @Override
  public void setIndexZ(Integer indexZ) {
    elementUml.setIndexZ(indexZ);
  }
}
