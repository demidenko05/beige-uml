package org.beigesoft.ui.widget.swing;

import org.beigesoft.model.IFilterFileWithChooseMode;


public interface IFilterFileWithChooseModeAdapter extends IFilterFileWithChooseMode {

  public FilterFileAndChooseModeData toSwingFileFilterAndChooseMode();
}
