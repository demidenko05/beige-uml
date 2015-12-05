package org.beigesoft.factory;

public interface IFactoryAppScoped<M> {

  public M lazyGet();
}
