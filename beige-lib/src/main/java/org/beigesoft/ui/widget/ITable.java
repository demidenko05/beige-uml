package org.beigesoft.ui.widget;

import java.util.List;

public interface ITable<M> {

  public void repaint();
  
  public void prepareExitEditTable();
  
  public void removeSelectedRow();
  
  public void add(M row);
  
  public void setDataSource(List<M> dataSource);
}
