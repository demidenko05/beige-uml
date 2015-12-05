package org.beigesoft.ui.swing;

import javax.swing.JDialog;

import org.beigesoft.ui.IWindowInstrument;

public class WindowsInstrumentSwing implements IWindowInstrument {
  
  private final JDialog windowInstrument;

  
  public WindowsInstrumentSwing(JDialog windowInstrument) {
    this.windowInstrument = windowInstrument;
  }

  @Override
  public void doShow() {
    windowInstrument.setVisible(true);
  }

  @Override
  public void doClose() {
    windowInstrument.dispose();
    //windowInstrument.setVisible(false); 
    //windowInstrument.dispatchEvent(new WindowEvent(windowInstrument, WindowEvent.COMPONENT_HIDDEN));
    //windowInstrument.invalidate();
  }

  @Override
  public void setTitle(String title) {
    windowInstrument.setTitle(title);
  }

  public JDialog getWindowInstrument() {
    return windowInstrument;
  }
}
