package org.beigesoft.model;

import java.io.File;

import org.beigesoft.filter.IFilter;

public interface IFilterFileWithChooseMode {

  public EChooseFileMode getChooseFileKind();

  public void setChooseFileKind(EChooseFileMode chooseFileKind);

  public IFilter<File> getFilter();
}
