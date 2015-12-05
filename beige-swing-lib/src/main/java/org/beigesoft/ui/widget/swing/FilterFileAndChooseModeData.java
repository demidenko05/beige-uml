package org.beigesoft.ui.widget.swing;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FilterFileAndChooseModeData {

  private int fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES;
  
  private FileFilter fileFilter;

  public int getFileSelectionMode() {
    return fileSelectionMode;
  }

  public void setFileSelectionMode(int fileSelectionMode) {
    this.fileSelectionMode = fileSelectionMode;
  }

  public FileFilter getFileFilter() {
    return fileFilter;
  }

  public void setFileFilter(FileFilter fileFilter) {
    this.fileFilter = fileFilter;
  }
}
