package org.beigesoft.model;


public interface IPersistable<ID> extends IEditable {

  public ID getId();
  
  public void setId(ID id);
}
