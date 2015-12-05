package org.beigesoft.model;

import java.io.File;

import org.beigesoft.filter.IFilter;

public class FilterFileWithChooseMode implements IFilterFileWithChooseMode {

  private EChooseFileMode chooseFileKind = EChooseFileMode.ANY;
  
  private final IFilter<File> filter;

  public FilterFileWithChooseMode(EChooseFileMode chooseFileKind,
      IFilter<File> filter) {
    this.chooseFileKind = chooseFileKind;
    this.filter = filter;
  }

  @Override
  public EChooseFileMode getChooseFileKind() {
    return chooseFileKind;
  }

  @Override
  public void setChooseFileKind(EChooseFileMode chooseFileKind) {
    this.chooseFileKind = chooseFileKind;
  }

  @Override
  public IFilter<File> getFilter() {
    return filter;
  }
}
