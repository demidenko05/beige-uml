package org.beigesoft.ui.widget.swing;

import java.awt.event.ActionEvent;

import org.beigesoft.ui.widget.IButton;

public class ButtonSwing implements IButton<ActionEvent> {
  
  private final javax.swing.JButton button;
  
  public ButtonSwing(javax.swing.JButton button) {
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
  
  public javax.swing.JButton getButton() {
  	return button;
  }
}
