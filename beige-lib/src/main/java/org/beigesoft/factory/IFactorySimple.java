package org.beigesoft.factory;

public interface IFactorySimple<M> {

  /**
   * Create a bean
   * @return request(or) scoped bean
   */
  public M create();
}
