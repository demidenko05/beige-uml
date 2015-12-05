package org.beigesoft.ui.widget.swing;

import javax.swing.JTextArea;

import org.beigesoft.ui.widget.ITextField;

public class TextAreaSwing implements ITextField {
  
  private final JTextArea textArea;
  
  public TextAreaSwing(JTextArea textArea) {
    this.textArea = textArea;
  }

  @Override
  public void setText(String text) {
    textArea.setText(text);
  }

  @Override
  public String getText() {
    return textArea.getText();
  }

  @Override
  public void setIsEnabled(boolean isEnabled) {
    textArea.setEnabled(isEnabled);
  }
  
  public JTextArea getTextArea() {
    return textArea;
  }
}
