package org.beigesoft.android.ui;

import org.beigesoft.ui.IEventMotion;

import android.view.MotionEvent;

public class EventMotion implements IEventMotion {

  private final MotionEvent event;
    
  public EventMotion(MotionEvent event) {
    this.event = event;
  }

  @Override
  public void consume() {
    // nothing
  }

  @Override
  public boolean isIntentEdit() {
    return event.getEventTime() - event.getDownTime() > 800;
  }

  @Override
  public int getX() {
    return (int) event.getX();
  }

  @Override
  public int getY() {
    return (int) event.getY();
  }

  @Override
  public boolean isConsumed() {
    // TODO Auto-generated method stub
    return false;
  }

  public MotionEvent getEvent() {
    return event;
  }
}
