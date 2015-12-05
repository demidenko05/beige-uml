package org.beigesoft.ui.widget.swing;

import java.awt.Frame;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.model.IFilterFileWithChooseMode;
import org.beigesoft.ui.widget.IChooserFile;

public class FileChooser implements IChooserFile {
  
  private final JFileChooser jfileChooser;
  
  private final Frame frame;
  
  private final Map<Integer, FileFilter> filtersMap;

  public FileChooser(JFileChooser jfileChooser, Frame frame) {
    this.jfileChooser = jfileChooser;
    this.frame = frame;
    filtersMap = new HashMap<Integer, FileFilter>();
  }

  @Override
  public void applyFilterFileWithChooseMode(IFilterFileWithChooseMode filterFileWithChooseMode) {
    FilterFileAndChooseModeData swingFileFilter = ((IFilterFileWithChooseModeAdapter) filterFileWithChooseMode).toSwingFileFilterAndChooseMode();
    jfileChooser.setFileSelectionMode(swingFileFilter.getFileSelectionMode());
    jfileChooser.setFileFilter(swingFileFilter.getFileFilter());
  }

  public JFileChooser getJfileChooser() {
    return jfileChooser;
  }

  public Map<Integer, FileFilter> getFiltersMap() {
    return filtersMap;
  }

  @Override
  public void showAndChoose(IConsumer<File> consumer) {
    int result = jfileChooser.showOpenDialog(frame);
    if(result == JFileChooser.APPROVE_OPTION) {
      consumer.consume(jfileChooser.getSelectedFile());
    }
  }

  @Override
  public void setSelectedValue(File file) {
    jfileChooser.setSelectedFile(file);
  }
}
