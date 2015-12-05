package org.beigesoft.android.layout;

import org.beigesoft.handler.IHandlerEvent;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class LayoutLinearTouchIntercepting extends LinearLayout {
  
  private IHandlerEvent<MotionEvent> handlerMotionEvent;

  public LayoutLinearTouchIntercepting(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean performClick() {
    return super.performClick();
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    performClick();
    if(handlerMotionEvent != null && handlerMotionEvent.handleEvent(ev)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    if(handlerMotionEvent != null && handlerMotionEvent.handleEvent(ev)) {
      return true;
    }
    return false;
  }

  public IHandlerEvent<MotionEvent> getHandlerMotionEvent() {
    return handlerMotionEvent;
  }

  public void setHandlerMotionEvent(IHandlerEvent<MotionEvent> handlerMotionEvent) {
    this.handlerMotionEvent = handlerMotionEvent;
  }

}
