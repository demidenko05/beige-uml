package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.UseCase;

public class SrvPersistLightXmlUseCase<P extends UseCase> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlUseCase<P> srvSaveXmlUseCase;
  
  public SrvPersistLightXmlUseCase() {
    srvSaveXmlUseCase = new SrvSaveXmlUseCase<P>(SrvSaveXmlUseCase.NAMEXML_USECASE);
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlUseCase.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlUseCase<P> getSrvSaveXmlUseCase() {
    return srvSaveXmlUseCase;
  }

  public void setSrvSaveXmlUseCase(SrvSaveXmlUseCase<P> srvSaveXmlUseCase) {
    this.srvSaveXmlUseCase = srvSaveXmlUseCase;
  }
}
