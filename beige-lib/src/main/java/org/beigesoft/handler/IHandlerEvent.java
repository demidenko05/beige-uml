package org.beigesoft.handler;

public interface IHandlerEvent<EV> {

  public boolean handleEvent(EV event);
}
