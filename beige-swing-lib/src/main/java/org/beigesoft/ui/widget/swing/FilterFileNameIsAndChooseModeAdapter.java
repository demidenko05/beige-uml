package org.beigesoft.ui.widget.swing;

import org.beigesoft.filter.FilterFileNameIs;
import org.beigesoft.model.EChooseFileMode;

public class FilterFileNameIsAndChooseModeAdapter extends AFilterFileChooseModeAdapter {

  public FilterFileNameIsAndChooseModeAdapter(String filterNameIs) {
    super(EChooseFileMode.FILE, new FilterFileNameIs(filterNameIs));
  }

  @Override
  public FilterFileAndChooseModeData toSwingFileFilterAndChooseMode() {
    FilterFileAndChooseModeData result = new FilterFileAndChooseModeData();
    result.setFileSelectionMode(toSwingFileSelectionMode(getChooseFileKind()));
    result.setFileFilter(new FileFilterNameIs(((FilterFileNameIs) getFilter()).getFilterNameIs()));
    return result;
  }
}
