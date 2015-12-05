package org.beigesoft.ui.widget.model.swing;

import java.util.List;

import javax.swing.DefaultListModel;

import org.beigesoft.ui.widget.swing.IListModel;

public class ListModel<M> extends DefaultListModel implements IListModel<M> {

  private static final long serialVersionUID = 6095699297757627258L;
  
  /**
   * Editable list MODEL
   */
  private List<M> dataSource;
  
  @SuppressWarnings("unchecked")
  @Override
  public void setDataSource(List<M> dataSource) {
    this.dataSource = dataSource;
    if(getSize() > 0) {
      int sizeWas = getSize();
      removeAllElements();
      fireIntervalRemoved(this, 0, sizeWas - 1);
    }
    for(M item : dataSource) {
      addElement(item);
    }
    if(getSize() > 0) {
      fireIntervalAdded(this, 0, getSize() - 1);
    }   
  }

  @SuppressWarnings("unchecked")
  @Override
  public void add(M raw) {
    addElement(raw);
    dataSource.add(raw);
    fireIntervalAdded(this, getSize() - 1, getSize() - 1);
  }

  @Override
  public void removeItem(int idx) {
    remove(idx);
    dataSource.remove(idx);
    fireIntervalRemoved(this, idx, idx);
  }

  @Override
  public M getItem(int idx) {
    return dataSource.get(idx);
  }

  //SGS:
  public List<M> getDataSource() {
    return dataSource;
  }
}
