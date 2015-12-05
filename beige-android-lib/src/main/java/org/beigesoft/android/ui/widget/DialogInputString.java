package org.beigesoft.android.ui.widget;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.android.R;
import org.beigesoft.util.UtilsMisc;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DialogInputString extends ADialogOkCancel {
  
  private String string;
  
  protected EditText etString;
    
  protected TextView tvMessage;
  
  protected final IConsumer<String> consumerString;

  private String title;

  private String message;
  
  public DialogInputString(IConsumer<String> consumerString) {
    this.consumerString = consumerString;
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_input_string, null);
    builder.setView(rootView);
    setupWidgets(rootView);
    Dialog dialog = builder.create();
    dialog.setTitle(title);
    return dialog;
  }

  @Override
  public void onStart() {
    super.onStart();
    tvMessage.setText(message);
    etString.setText(string);
  }

  @Override
  protected void setupWidgets(View rootView) {
    super.setupWidgets(rootView);
    etString = (EditText) rootView.findViewById(R.id.etString);
    tvMessage = (TextView) rootView.findViewById(R.id.tvMessage);
  }

  @Override
  protected void onCancelPress(View v) {
    string = null;
    getDialog().cancel();
  }

  @Override
  protected void onOkPress(View v) {
    string = UtilsMisc.evalTextValue(etString.getText().toString());
    consumerString.consume(string);
    dismiss();
  }

  protected void refreshView() {
    etString.setText(string);
  }

  public TextView getTvMessage() {
    return tvMessage;
  }

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setMessage(String msg) {
    this.message = msg;
  }
}
