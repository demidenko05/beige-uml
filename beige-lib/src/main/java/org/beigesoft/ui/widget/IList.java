package org.beigesoft.ui.widget;

import java.util.List;

public interface IList<M> {

  public void repaint();
  
  public void add(M row);
  
  public void removeSelectedRow();
  
  public void replaceDataSource(List<M> dataSource);
}
