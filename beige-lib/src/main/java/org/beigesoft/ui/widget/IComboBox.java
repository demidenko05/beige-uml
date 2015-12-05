package org.beigesoft.ui.widget;

public interface IComboBox<M> extends IWidget {
  
  public M getSelectedItem();
  
  public void setSelectedItem(M item);
}
