package org.beigesoft.service;

public interface ISrvPersistSimple<P> {
  
  public void persist(P persistable) throws Exception;
  
  public void restore(P persistable) throws Exception;
}
