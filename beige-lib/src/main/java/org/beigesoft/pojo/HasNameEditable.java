package org.beigesoft.pojo;

import org.beigesoft.model.IEditable;
import org.beigesoft.model.IHasName;

public class HasNameEditable implements IHasName, IEditable, Cloneable {

  private String itsName;
  
  private boolean isNew;
  
  public HasNameEditable() {
    isNew = true;
  }

  public HasNameEditable(String itsName) {
    this.itsName = itsName;
  }

  @Override
  public String toString() {
    return itsName == null ? " ": itsName;//It must be at list single space for SWING list renderer only
  }

  @Override
  public HasNameEditable clone() {
    try {
      HasNameEditable clone = (HasNameEditable) super.clone();
      return clone;
   } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
   }

  @Override
  public void setItsName(String itsName) {
    this.itsName = itsName;
  }

  @Override
  public String getItsName() {
    return itsName;
  }

  @Override
  public boolean getIsNew() {
    return isNew;
  }

  @Override
  public void setIsNew(boolean isNew) {
    this.isNew = isNew;
  }

}
