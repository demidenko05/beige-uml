package org.beigesoft.ui.widget.swing;

import javax.swing.JCheckBox;

import org.beigesoft.ui.widget.ICheckBox;

public class CheckBoxSwing implements ICheckBox {

  private final JCheckBox checkBox;
  
  public CheckBoxSwing(JCheckBox checkBox) {
    this.checkBox = checkBox;
  }

  @Override
  public boolean getIsSelected() {
    return checkBox.isSelected();
  }

  @Override
  public void setIsSelected(boolean isSelected) {
    checkBox.setSelected(isSelected);
  }

  @Override
  public void setLabel(String label) {
    checkBox.setText(label);
  }

  @Override
  public void setIsEnabled(boolean isEnabled) {
    checkBox.setEnabled(isEnabled);
  }
  
  //SGS:
  public JCheckBox getCheckBox() {
    return checkBox;
  }
}
