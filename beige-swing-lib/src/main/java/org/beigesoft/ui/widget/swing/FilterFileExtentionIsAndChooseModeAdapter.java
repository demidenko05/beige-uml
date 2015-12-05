package org.beigesoft.ui.widget.swing;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.beigesoft.filter.FilterFileExtensionIs;
import org.beigesoft.model.EChooseFileMode;

public class FilterFileExtentionIsAndChooseModeAdapter extends AFilterFileChooseModeAdapter {

  public FilterFileExtentionIsAndChooseModeAdapter(String[] filterExtentions) {
    super(EChooseFileMode.FILE, new FilterFileExtensionIs(filterExtentions));
  }

  @Override
  public FilterFileAndChooseModeData toSwingFileFilterAndChooseMode() {
    FilterFileAndChooseModeData result = new FilterFileAndChooseModeData();
    result.setFileSelectionMode(toSwingFileSelectionMode(getChooseFileKind()));
    result.setFileFilter(new FileNameExtensionFilter(getFilter().toString(), 
        ((FilterFileExtensionIs) getFilter()).getFilterExtentions()));
    return result;
  }
}
