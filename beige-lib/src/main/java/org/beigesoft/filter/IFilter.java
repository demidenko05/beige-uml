package org.beigesoft.filter;

public interface IFilter<M> {

  public boolean isAccepted(M model);
}
