package org.beigesoft.ui.pojo;

import java.io.File;

public class NodeJavaClass {
  
  private final String javaClass;
  
  private final boolean isFolder;

  public NodeJavaClass(String javaClass, boolean isFolder) {
    this.javaClass = javaClass;
    this.isFolder = isFolder;
  }

  @Override
  public String toString() {
    if(isFolder) {
      int lastIndexOfSeparator = javaClass.lastIndexOf(File.separator);
      return lastIndexOfSeparator == -1 ? javaClass : javaClass.substring(lastIndexOfSeparator+1);
    }
    int lastIndexOfDot = javaClass.lastIndexOf(".");
    return lastIndexOfDot == -1 ? javaClass+".class" : javaClass.substring(lastIndexOfDot+1)+".class";
  }
  
  public String getJavaClass() {
    return javaClass;
  }

  public boolean getIsFolder() {
    return isFolder;
  }
}
