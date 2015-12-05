package org.beigesoft.uml.app.model;

import org.beigesoft.ui.pojo.IOpenable;

public class NodeFolderProject implements IOpenable {
  
  private String path;
  
  private String name;

  public NodeFolderProject() {
    
  }
  
  public NodeFolderProject(String path, String name) {
    this.name = name;
    this.path = path;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public void open() {
    //nothing   
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
