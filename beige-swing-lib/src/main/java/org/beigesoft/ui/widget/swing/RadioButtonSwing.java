package org.beigesoft.ui.widget.swing;

import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;

import org.beigesoft.ui.widget.IRadioButton;

public class RadioButtonSwing implements IRadioButton<ActionEvent> {
  
  private final JRadioButton button;
  
  public RadioButtonSwing(JRadioButton button) {
    this.button = button;
  }
  
  @Override
  public boolean isPushed(ActionEvent ae) {
    return ae.getSource() == button;
  }
  
  @Override
  public void setText(String text) {
    button.setText(text);
  } 

  @Override
  public void setIsEnabled(boolean isEnabled) {
    button.setEnabled(isEnabled);
  }

  @Override
  public void setIsSelected(boolean isSelected) {
    button.setSelected(isSelected);
  }
  
  public JRadioButton getButton() {
  	return button;
  }
}
