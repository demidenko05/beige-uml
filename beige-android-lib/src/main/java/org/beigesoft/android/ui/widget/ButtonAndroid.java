package org.beigesoft.android.ui.widget;

import org.beigesoft.ui.widget.IButton;
import android.view.View;
import android.widget.Button;

public class ButtonAndroid implements IButton<View> {

  private final Button button;
  
  public ButtonAndroid(Button button) {
    this.button = button;
  }

  @Override
  public boolean isPushed(View view) {
    return button == view;
  }

  @Override
  public void setIsEnabled(boolean isEnabled) {
    button.setEnabled(isEnabled);
  }

  @Override
  public void setText(String text) {
    button.setText(text);
  }

  //SGS:
  public Button getButton() {
    return button;
  }
}
