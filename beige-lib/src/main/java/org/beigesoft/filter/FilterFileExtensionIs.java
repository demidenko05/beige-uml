package org.beigesoft.filter;

import java.io.File;

public class FilterFileExtensionIs implements IFilter<File> {

  private String[] filterExtentions;

  public FilterFileExtensionIs(String[] filterExtentions) {
    this.filterExtentions = filterExtentions;
  }

  @Override
  public boolean isAccepted(File fl) {
    if(fl.isDirectory()) {
      return true;
    }
    boolean isAllow = false;
    for(String ext : filterExtentions) {
      if(fl.getName().endsWith("." + ext)) {
        isAllow = true;
        break;
      }
    }
    return isAllow;
  }

  @Override
  public String toString() {
    String descr = getFilterExtentions()[0];
    for(int i = 1; i<getFilterExtentions().length; i++) {
      descr += "/" + getFilterExtentions()[i];
    }
    return descr;
  }

  public String[] getFilterExtentions() {
    return filterExtentions;
  }

  public void setFilterExtentions(String[] filterExtentions) {
    this.filterExtentions = filterExtentions;
  }
}
