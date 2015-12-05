package org.beigesoft.android.ui;

import org.beigesoft.handler.IHandlerConfirm;

import android.content.DialogInterface;

public class ListenerConfirmDialogYes implements DialogInterface.OnClickListener {
  
  protected final IHandlerConfirm handlerConfirm;
  
  public ListenerConfirmDialogYes(IHandlerConfirm handlerConfirm) {
    this.handlerConfirm = handlerConfirm;
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {
    handlerConfirm.handleConfirm(true);
    dialog.cancel();
  }
}
