package org.beigesoft.android.ui.widget;

import java.io.File;

import android.app.Activity;
import android.content.Intent;

import org.beigesoft.android.app.ApplicationPlus;
import org.beigesoft.android.treechooser.ActivityTreeChooser;
import org.beigesoft.android.treechooser.FragmentNodes;
import org.beigesoft.filter.IFilter;
import org.beigesoft.handler.IConsumer;
import org.beigesoft.model.EChooseFileMode;
import org.beigesoft.model.IFilterFileWithChooseMode;
import org.beigesoft.service.SrvGetNodeFile;
import org.beigesoft.ui.widget.IChooserFile;

/**
 * You must provide fileFilter, idFolderStart and title before invoke showAndChoose(IConsumer/File/ consumer)! 
 * 
 * @author Yury Demidenko
 *
 */
public class FileChooserAdaptor implements IChooserFile {

  public static final int REQUEST_NODE_FILE = 712362;

  private final Activity activity;
  
  protected String idSrvGetNodeFile = SrvGetNodeFile.class.getSimpleName();
  
  protected SrvGetNodeFile srvGetNodeFile;
    
  private IConsumer<File> consumer;

  private String idFolderStart;
  
  private String title;

  private String idCommand;

  private IFilter<File> filter;

  public FileChooserAdaptor(Activity activity) {
    this.activity = activity;
  }

  @Override
  public void setSelectedValue(File file) {
    // TODO
  }

  /**
   * You must provide fileFilter, idFolderStart and title to invoke it! 
   */
  @Override
  public void showAndChoose(IConsumer<File> consumer) {
    this.consumer = consumer;
    initSrvNodeFile();
    Intent activityTreeIntent = new Intent(activity, ActivityTreeChooser.class);
    activityTreeIntent.putExtra(FragmentNodes.ARG_ID_NODE_SRVNODES, new String[]{idFolderStart, idSrvGetNodeFile, idCommand, title});
    activity.startActivityForResult(activityTreeIntent, REQUEST_NODE_FILE);
  }
  
  public void consumeFile(String idFile) {
    File file = new File(idFile);
    consumer.consume(file);
  }

  @SuppressWarnings("unchecked")
  protected void initSrvNodeFile() {
    srvGetNodeFile = (SrvGetNodeFile) ((ApplicationPlus) activity.getApplicationContext()).getServicesMap().get(idSrvGetNodeFile);
    if(srvGetNodeFile == null) {
      srvGetNodeFile = new SrvGetNodeFile();
      ((ApplicationPlus) activity.getApplicationContext()).getServicesMap().put(idSrvGetNodeFile, srvGetNodeFile);
    }
    srvGetNodeFile.setFilter(filter);
  }
  
  @Override
  public void applyFilterFileWithChooseMode(
      IFilterFileWithChooseMode filterFileWithChooseMode) {
    this.filter = filterFileWithChooseMode.getFilter();
    idCommand = filterFileWithChooseMode.getChooseFileKind() == EChooseFileMode.DIRECTORY ?
        ActivityTreeChooser.CMD_CHOOSE_FOLDER : ActivityTreeChooser.CMD_CHOOSE_ITEM;
  }
  //SGS:
  public Activity getActivity() {
    return activity;
  }

  public IConsumer<File> getConsumer() {
    return consumer;
  }

  public void setConsumer(IConsumer<File> consumer) {
    this.consumer = consumer;
  }

  public String getIdFolderStart() {
    return idFolderStart;
  }

  public void setIdFolderStart(String idFolderStart) {
    this.idFolderStart = idFolderStart;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIdSrvGetNodeFile() {
    return idSrvGetNodeFile;
  }

  public void setIdSrvGetNodeFile(String idSrvGetNodeFile) {
    this.idSrvGetNodeFile = idSrvGetNodeFile;
  }

  public SrvGetNodeFile getSrvGetNodeFile() {
    return srvGetNodeFile;
  }

  public void setSrvGetNodeFile(SrvGetNodeFile srvGetNodeFile) {
    this.srvGetNodeFile = srvGetNodeFile;
  }

  public String getIdCommand() {
    return idCommand;
  }

  public void setIdCommand(String idCommand) {
    this.idCommand = idCommand;
  }
}
