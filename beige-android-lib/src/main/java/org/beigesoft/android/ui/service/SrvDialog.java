package org.beigesoft.android.ui.service;

import org.beigesoft.android.R;
import org.beigesoft.android.ui.ListenerConfirmDialogNo;
import org.beigesoft.android.ui.ListenerConfirmDialogYes;
import org.beigesoft.android.ui.widget.DialogInputString;
import org.beigesoft.handler.IConsumer;
import org.beigesoft.handler.IHandlerConfirm;
import org.beigesoft.ui.service.ISrvDialog;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class SrvDialog implements ISrvDialog<Activity> {

  @Override
  public void confirm(Activity activity, String msg, String title, IHandlerConfirm handlerConfirm) {
    AlertDialog.Builder builder = new Builder(activity);
    builder.setCancelable(false);
    builder.setMessage(msg).setTitle(title);
    builder.setPositiveButton(R.string.yes, new ListenerConfirmDialogYes(handlerConfirm));
    builder.setNegativeButton(R.string.no, new ListenerConfirmDialogNo(handlerConfirm));
    builder.show();
  }

  @Override
  public void errorMessage(Activity activity, String msg, String title) {
    showAlertDialog(activity, msg, title);
  }

  @Override
  public void showAndGetString(Activity activity, String msg, String title, IConsumer<String> consumerString) {
    DialogInputString dialogInputString = new DialogInputString(consumerString);
    dialogInputString.setTitle(title);
    dialogInputString.setMessage(msg);
    dialogInputString.show(activity.getFragmentManager(), DialogInputString.class.getSimpleName());
  }

  @Override
  public void warningMessage(Activity activity, String msg, String title) {
    showAlertDialog(activity, msg, title);
  }

  //UTILITIES:
  protected void showAlertDialog(Activity activity, String msg, String title) {
    AlertDialog.Builder builder = new Builder(activity);
    builder.setMessage(msg).setTitle(title)
    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }});
    builder.show();
  }
}
