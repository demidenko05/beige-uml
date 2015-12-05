package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.UseCaseExtended;

public class SrvPersistLightXmlUseCaseExtended<P extends UseCaseExtended> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlUseCaseExtended<P> srvSaveXmlUseCase;
  
  public SrvPersistLightXmlUseCaseExtended() {
    srvSaveXmlUseCase = new SrvSaveXmlUseCaseExtended<P>(SrvSaveXmlUseCaseExtended.NAMEXML_USECASEEXTENDED);
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

  public void setSrvSaveXmlUseCase(SrvSaveXmlUseCaseExtended<P> srvSaveXmlUseCase) {
    this.srvSaveXmlUseCase = srvSaveXmlUseCase;
  }
}
