package org.beigesoft.ui.widget;

import java.io.File;

import org.beigesoft.model.IFilterFileWithChooseMode;

public interface IChooserFile extends IChooser<File> {

  public void applyFilterFileWithChooseMode(IFilterFileWithChooseMode filterFileWithChooseMode);
}
