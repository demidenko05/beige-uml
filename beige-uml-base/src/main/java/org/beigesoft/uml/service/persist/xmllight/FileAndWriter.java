package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;
import java.io.File;

public class FileAndWriter {

  private File file;
  
  private BufferedWriter bufferedWriter;

  public FileAndWriter() {
  }

  public FileAndWriter(File file) {
    this.file = file;
  }

  public File getFile() {
    return file;
  }

  public BufferedWriter getBufferedWriter() {
    return bufferedWriter;
  }

  public void setBufferedWriter(BufferedWriter bufferedWriter) {
    this.bufferedWriter = bufferedWriter;
  }
  
  public void setFile(File file) {
    this.file = file;
  }
}
