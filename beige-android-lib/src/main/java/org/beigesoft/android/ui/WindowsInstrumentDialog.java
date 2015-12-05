package org.beigesoft.android.ui;

import org.beigesoft.delegate.IDelegate;
import org.beigesoft.ui.IWindowInstrument;

import android.app.Activity;
import android.app.DialogFragment;

public class WindowsInstrumentDialog implements IWindowInstrument {
  
  private final Activity activity;
  
  private final DialogFragment dialog;

  private final String tag;
  
  private final IDelegate<String> delegatorSetTitle;

  public WindowsInstrumentDialog(Activity activity, DialogFragment dialog, String tag, 
      IDelegate<String> delegatorSetTitle) {
    this.activity = activity;
    this.dialog = dialog;
    this.tag = tag;
    this.delegatorSetTitle = delegatorSetTitle;
  }

  @Override
  public void doClose() {
    dialog.dismiss();
  }

  @Override
  public void doShow() {
    dialog.show(activity.getFragmentManager(), tag);
  }

  @Override
  public void setTitle(String title) {
    delegatorSetTitle.makeWith(title);
  }

  public DialogFragment getDialog() {
    return dialog;
  }

  public IDelegate<String> getDelegatorSetTitle() {
    return delegatorSetTitle;
  }
}
