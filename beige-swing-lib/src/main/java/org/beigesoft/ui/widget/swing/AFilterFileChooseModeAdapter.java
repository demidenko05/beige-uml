package org.beigesoft.ui.widget.swing;

import java.io.File;

import javax.swing.JFileChooser;

import org.beigesoft.filter.IFilter;
import org.beigesoft.model.EChooseFileMode;
import org.beigesoft.model.FilterFileWithChooseMode;

public abstract class AFilterFileChooseModeAdapter extends FilterFileWithChooseMode implements IFilterFileWithChooseModeAdapter {

  public AFilterFileChooseModeAdapter(EChooseFileMode chooseFileKind,
      IFilter<File> filter) {
    super(chooseFileKind, filter);
  }

  public int toSwingFileSelectionMode(EChooseFileMode chooseFileKind) {
    if(chooseFileKind == EChooseFileMode.FILE) {
      return JFileChooser.FILES_ONLY;
    }
    if(chooseFileKind == EChooseFileMode.DIRECTORY) {
      return JFileChooser.DIRECTORIES_ONLY;
    }
    return JFileChooser.FILES_AND_DIRECTORIES;
  }
}
