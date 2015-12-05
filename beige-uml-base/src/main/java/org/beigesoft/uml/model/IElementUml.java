package org.beigesoft.uml.model;

import java.util.UUID;

import org.beigesoft.model.IPersistable;

public interface IElementUml extends IPersistable<UUID> {

  public Integer getIndexZ();
  
  public void setIndexZ(Integer indexZ);
  
  public boolean getIsSelected();
  
  public void setIsSelected(boolean isSelected);
}
