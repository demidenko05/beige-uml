package org.beigesoft.ui.pojo;

public class NodeRootDescriptor {
    
  private String description;

  @Override
  public String toString() {
    return description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
}
