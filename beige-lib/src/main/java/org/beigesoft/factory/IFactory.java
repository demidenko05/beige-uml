package org.beigesoft.factory;

public interface IFactory<M> extends IFactorySimple<M> {

  /**
   * Create bean based on original
   * @param original
   * @return request(or) scoped bean
   */
  public M createClone(M original);
}
