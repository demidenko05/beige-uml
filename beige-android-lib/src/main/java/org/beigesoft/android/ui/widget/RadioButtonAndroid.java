package org.beigesoft.android.ui.widget;

import org.beigesoft.ui.widget.IRadioButton;

import android.view.View;
import android.widget.RadioButton;

public class RadioButtonAndroid implements IRadioButton<View> {
  
  private final RadioButton radioButton;
  
  public RadioButtonAndroid(RadioButton radioButton) {
    this.radioButton = radioButton;
  }

  @Override
  public boolean isPushed(View source) {
    return source.getId() == radioButton.getId() && radioButton.isChecked();
  }

  @Override
  public void setIsEnabled(boolean isEnabled) {
    radioButton.setEnabled(isEnabled);
  }

  @Override
  public void setText(String text) {
    radioButton.setText(text);
  }

  @Override
  public void setIsSelected(boolean isSelected) {
    radioButton.setChecked(isSelected);
  }

  public RadioButton getRadioButton() {
    return radioButton;
  }
}
