package org.beigesoft.ui.widget.swing;

import java.util.List;

public interface IListModel<M> {

  /**
   * Editable list-MODEL to change by widget
   */
  public void setDataSource(List<M> dataSource);
  
  public void add(M raw);

  public int getSize();

  public M getItem(int idx);

  public void removeItem(int idx);
}
