package org.beigesoft.android.ui.widget;

import org.beigesoft.ui.widget.ITextField;

import android.widget.EditText;

public class TextField implements ITextField {
  
  private final EditText textView;

  public TextField(EditText textView) {
    this.textView = textView;
  }

  @Override
  public void setIsEnabled(boolean isEnable) {
    textView.setEnabled(isEnable);
  }

  @Override
  public String getText() {
    return textView.getText().toString();
  }

  @Override
  public void setText(String text) {
    textView.setText(text);
  }

  public EditText getTextView() {
    return textView;
  }

}
