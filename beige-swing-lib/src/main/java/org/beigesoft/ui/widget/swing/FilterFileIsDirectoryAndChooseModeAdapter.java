package org.beigesoft.ui.widget.swing;

import org.beigesoft.filter.FilterFileIsDirectory;
import org.beigesoft.model.EChooseFileMode;

public class FilterFileIsDirectoryAndChooseModeAdapter extends AFilterFileChooseModeAdapter {

  public FilterFileIsDirectoryAndChooseModeAdapter() {
    super(EChooseFileMode.DIRECTORY, new FilterFileIsDirectory());
  }

  @Override
  public FilterFileAndChooseModeData toSwingFileFilterAndChooseMode() {
    FilterFileAndChooseModeData result = new FilterFileAndChooseModeData();
    result.setFileSelectionMode(toSwingFileSelectionMode(getChooseFileKind()));
    return result;
  }
}
