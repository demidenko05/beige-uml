package org.beigesoft.android.ui.widget;

import org.beigesoft.ui.widget.ICheckBox;

import android.widget.CheckBox;

public class CheckBoxAndroid implements ICheckBox {

  private final CheckBox checkBox;
  
  public CheckBoxAndroid(CheckBox checkBox) {
    this.checkBox = checkBox;
  }

  @Override
  public void setIsEnabled(boolean isEnabled) {
    checkBox.setEnabled(isEnabled);
  }

  @Override
  public boolean getIsSelected() {
    return checkBox.isChecked();
  }

  @Override
  public void setIsSelected(boolean isChecked) {
    checkBox.setChecked(isChecked);
  }

  @Override
  public void setLabel(String label) {
    // TODO
  }

  public CheckBox getCheckBox() {
    return checkBox;
  }

}
