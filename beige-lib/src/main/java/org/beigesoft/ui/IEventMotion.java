package org.beigesoft.ui;

public interface IEventMotion {

  public int getX();
  
  public int getY();

  public boolean isIntentEdit();

  public void consume();

  public boolean isConsumed();
}
