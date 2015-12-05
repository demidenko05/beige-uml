package org.beigesoft.handler;

public interface IConsumer<M> {

  public void consume(M model);
}
