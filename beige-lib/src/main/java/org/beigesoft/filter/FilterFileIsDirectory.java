package org.beigesoft.filter;

import java.io.File;

public class FilterFileIsDirectory implements IFilter<File> {

  @Override
  public String toString() {
    return "Directory only";
  }

  @Override
  public boolean isAccepted(File fl) {
    return fl.isDirectory();
  }
}
