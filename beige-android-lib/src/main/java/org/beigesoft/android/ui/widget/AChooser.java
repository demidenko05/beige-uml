package org.beigesoft.android.ui.widget;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.ui.widget.IChooserWithDataSource;

import android.app.Activity;

public abstract class AChooser<O> extends ADialogOkCancel implements IChooserWithDataSource<O> {

  protected IConsumer<O> consumer;
  
  private Activity instrumentWindow;
  
  public AChooser(Activity instrumentWindow) {
    this.instrumentWindow = instrumentWindow;
  }

  @Override
  public void showAndChoose(IConsumer<O> consumer) {
    this.consumer = consumer;
    show(instrumentWindow.getFragmentManager(), AChooser.class.getSimpleName() + consumer.getClass().getSimpleName());
  }

  public Activity getInstrumentWindow() {
    return instrumentWindow;
  }

  public void setInstrumentWindow(Activity instrumentWindow) {
    this.instrumentWindow = instrumentWindow;
  }
}
