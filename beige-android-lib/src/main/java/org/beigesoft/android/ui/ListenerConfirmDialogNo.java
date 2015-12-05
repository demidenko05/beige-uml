package org.beigesoft.android.ui;

import org.beigesoft.handler.IHandlerConfirm;

import android.content.DialogInterface;

public class ListenerConfirmDialogNo implements DialogInterface.OnClickListener {
  
  protected final IHandlerConfirm handlerConfirm;
  
  public ListenerConfirmDialogNo(IHandlerConfirm handlerConfirm) {
    this.handlerConfirm = handlerConfirm;
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {
    handlerConfirm.handleConfirm(false);
    dialog.cancel();
  }
}
