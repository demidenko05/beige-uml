package org.beigesoft.model;

import java.io.File;

public class NodeFile extends NodeTree<String, File> {

  public NodeFile(File file) {
    super(null, file);
    setIsFolder(file.isDirectory());
  }

  @Override
  public String toString() {
    return getValue().getName();
  }

  @Override
  public String getId() {
    return getValue().getAbsolutePath();
  }
}
