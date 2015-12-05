package org.beigesoft.ui.widget.swing;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileFilterNameIs extends FileFilter {

  private String fileName;
  
  public FileFilterNameIs(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public boolean accept(File f) {
    return f.isDirectory() || f.getName().equals(fileName);
  }

  @Override
  public String getDescription() {
    return fileName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

}
