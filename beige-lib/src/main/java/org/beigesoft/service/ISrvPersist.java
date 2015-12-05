package org.beigesoft.service;

public interface ISrvPersist <P, ITP> {
  
  public void persist(P persistable, ITP persistInstrument) throws Exception;
  
  public void restore(P persistable, ITP persistInstrument) throws Exception;
}
