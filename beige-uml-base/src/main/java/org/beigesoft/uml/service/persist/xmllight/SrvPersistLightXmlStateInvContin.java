package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.StateInvContin;

public class SrvPersistLightXmlStateInvContin<P extends StateInvContin> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlStateInvContin<P> srvSaveXmlStateInvContin;
  
  public SrvPersistLightXmlStateInvContin() {
    srvSaveXmlStateInvContin = new SrvSaveXmlStateInvContin<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlStateInvContin.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlStateInvContin<P> getSrvSaveXmlStateInvContin() {
    return srvSaveXmlStateInvContin;
  }

  public void setSrvSaveXmlStateInvContin(SrvSaveXmlStateInvContin<P> srvSaveXmlStateInvContin) {
    this.srvSaveXmlStateInvContin = srvSaveXmlStateInvContin;
  }
}
