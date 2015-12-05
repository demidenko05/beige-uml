package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.InteractionUse;

public class SrvPersistLightXmlInteractionUse<P extends InteractionUse> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlInteractionUse<P> srvSaveXmlInteractionUse;
  
  public SrvPersistLightXmlInteractionUse() {
    srvSaveXmlInteractionUse = new SrvSaveXmlInteractionUse<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlInteractionUse.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlInteractionUse<P> getSrvSaveXmlInteractionUse() {
    return srvSaveXmlInteractionUse;
  }

  public void setSrvSaveXmlInteractionUse(SrvSaveXmlInteractionUse<P> srvSaveXmlInteractionUse) {
    this.srvSaveXmlInteractionUse = srvSaveXmlInteractionUse;
  }
}
