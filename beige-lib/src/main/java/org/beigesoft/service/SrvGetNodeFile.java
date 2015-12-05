package org.beigesoft.service;

import java.io.File;

import org.beigesoft.filter.IFilter;
import org.beigesoft.model.NodeFile;

public class SrvGetNodeFile implements ISrvGetData<String, NodeFile> {

  private IFilter<File> filter;
  
  @Override
  public NodeFile getData(String id) {
    NodeFile result = null;
    File file = new File(id);
    if(file.exists() && (filter == null || filter.isAccepted(file))) {
      result = new NodeFile(file);
      if(file.isDirectory()) {
        File[] files = file.listFiles();
        if(files == null || files.length == 0) {
          result.createEmptyChildren();
        }
        else {
          for(File fl : files) {
            if(filter  == null ||filter.isAccepted(fl)) {
              NodeFile nfl = new NodeFile(fl);
              result.addChild(nfl);
            }
          }
        }
      }
    }
    return result;
  }

  public IFilter<File> getFilter() {
    return filter;
  }

  public void setFilter(IFilter<File> filter) {
    this.filter = filter;
  }
}
