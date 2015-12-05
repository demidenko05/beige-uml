package org.beigesoft.service;

public interface ISrvSaveModel<M, PRI> {

  public void persistModel(M m, PRI pri) throws Exception;
}
