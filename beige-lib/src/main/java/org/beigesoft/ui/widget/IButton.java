package org.beigesoft.ui.widget;

public interface IButton<AEI> extends IWidget {

  public boolean isPushed(AEI eventInstrument);
  
  public void setText(String text);

  public void setIsEnabled(boolean isEnabled);
}
