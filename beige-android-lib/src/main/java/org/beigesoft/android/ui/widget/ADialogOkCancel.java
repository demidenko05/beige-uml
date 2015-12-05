package org.beigesoft.android.ui.widget;

import android.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.beigesoft.android.R;

public abstract class ADialogOkCancel extends DialogFragment {

  protected Button btnOk;
  
  protected Button btnCancel;

  protected void setupWidgets(View rootView) {
    btnOk = (Button) rootView.findViewById(R.id.btnOkForDialog);
    btnCancel = (Button) rootView.findViewById(R.id.btnCancelForDialog);
    btnOk.setOnClickListener(btnOkClick);
    btnCancel.setOnClickListener(btnCancelClick);
  }

  protected OnClickListener btnCancelClick = new OnClickListener() {
    @Override
    public void onClick(View v) {
      onCancelPress(v);
    }
  };

  protected OnClickListener btnOkClick = new OnClickListener() {  
    @Override
    public void onClick(View v) {
      onOkPress(v);
    }
  };
  
  protected void onCancelPress(View v) {
    getDialog().cancel();
  }

  protected abstract void onOkPress(View v);
}
