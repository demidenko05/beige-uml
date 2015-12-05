package org.beigesoft.ui.awt;

import java.awt.event.MouseEvent;

import org.beigesoft.ui.IEventMotion;

public class EventClick implements IEventMotion {

  private final MouseEvent e;
  
  public EventClick(MouseEvent e) {
    this.e = e;
  }

  @Override
  public int getX() {
    return e.getX();
  }

  @Override
  public int getY() {
    return e.getY();
  }

  @Override
  public boolean isIntentEdit() {
    return e.getClickCount() == 2;
  }

  @Override
  public void consume() {
    e.consume();
  }

  @Override
  public boolean isConsumed() {
    return e.isConsumed();
  }

  public MouseEvent getE() {
    return e;
  }
}
