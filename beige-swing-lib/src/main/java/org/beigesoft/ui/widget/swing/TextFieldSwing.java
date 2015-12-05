package org.beigesoft.ui.widget.swing;

import javax.swing.JTextField;

import org.beigesoft.ui.widget.ITextField;

public class TextFieldSwing implements ITextField {
  
  private final JTextField textField;
  
  public TextFieldSwing(JTextField textField) {
    this.textField = textField;
  }

  @Override
  public void setText(String text) {
    textField.setText(text);
  }

  @Override
  public String getText() {
    return textField.getText();
  }

  @Override
  public void setIsEnabled(boolean isEnabled) {
    textField.setEnabled(isEnabled);
  }
  
  public JTextField getTextField() {
    return textField;
  }
}
