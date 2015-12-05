package org.beigesoft.filter;

import java.io.File;

public class FilterFileNameIs implements IFilter<File>{

  private String filterNameIs;
  
  public FilterFileNameIs(String filterNameIs) {
    this.filterNameIs = filterNameIs;
  }

  @Override
  public boolean isAccepted(File fl) {
    boolean isAllow = fl.isDirectory() || fl.getName().equals(filterNameIs);
    return isAllow;
  }

  public String getFilterNameIs() {
    return filterNameIs;
  }

  public void setFilterNameIs(String filterNameIs) {
    this.filterNameIs = filterNameIs;
  }

}
