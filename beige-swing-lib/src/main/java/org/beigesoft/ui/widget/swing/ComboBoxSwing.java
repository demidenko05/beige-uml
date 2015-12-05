package org.beigesoft.ui.widget.swing;

import javax.swing.JComboBox;

import org.beigesoft.ui.widget.IComboBox;

public class ComboBoxSwing<M> implements IComboBox<M> {
  
  //Java6 non-generic 
  private final JComboBox jcomboBox;

  public ComboBoxSwing(JComboBox jcomboBox) {
    this.jcomboBox = jcomboBox;
  }

  @SuppressWarnings("unchecked")
  @Override
  public M getSelectedItem() {
    // TODO Auto-generated method stub
    return (M) jcomboBox.getSelectedItem();
  }

  @Override
  public void setSelectedItem(M item) {
    jcomboBox.setSelectedItem(item);
  }

  @Override
  public void setIsEnabled(boolean isEnabled) {
    jcomboBox.setEnabled(isEnabled);
  }
  
  public JComboBox getJcomboBox() {
    return jcomboBox;
  }
}
