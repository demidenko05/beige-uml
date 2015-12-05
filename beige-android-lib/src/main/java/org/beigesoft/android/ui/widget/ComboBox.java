package org.beigesoft.android.ui.widget;

import org.beigesoft.ui.widget.IComboBox;
import android.widget.Spinner;

public class ComboBox<M> implements IComboBox<M> {

  private final Spinner spinner;
  
  public ComboBox(Spinner spinner) {
    this.spinner = spinner;
  }

  @Override
  public void setIsEnabled(boolean isEnabled) {
    spinner.setEnabled(isEnabled);
  }

  @SuppressWarnings("unchecked")
  @Override
  public M getSelectedItem() {
    return (M) spinner.getSelectedItem();
  }

  @Override
  public void setSelectedItem(M val) {
    for(int i=0; i<spinner.getAdapter().getCount(); i++) {
      if(spinner.getAdapter().getItem(i) == val) {
        spinner.setSelection(i);
      }
    }
  }

  public Spinner getSpinner() {
    return spinner;
  }
}
