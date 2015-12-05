package org.beigesoft.uml.model;

import org.beigesoft.pojo.PersistableUUID;

public class ElementUml extends PersistableUUID implements IElementUml, Cloneable {

  private Integer indexZ;
  
  private boolean isSelected;
  
  
  @Override
  public ElementUml clone() {
    try {
      ElementUml clone = (ElementUml) super.clone();
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean getIsSelected() {
    return isSelected;
  }

  @Override
  public void setIsSelected(boolean isSelected) {
    this.isSelected = isSelected;
  }

  @Override
  public Integer getIndexZ() {
    return indexZ;
  }

  @Override
  public void setIndexZ(Integer indexZ) {
    this.indexZ = indexZ;
  }
}
