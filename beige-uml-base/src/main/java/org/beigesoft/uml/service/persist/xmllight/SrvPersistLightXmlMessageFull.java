package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.assembly.MessageFull;

public class SrvPersistLightXmlMessageFull<P extends MessageFull> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlMessageFull<P> srvSaveXmlMessage;
  
  public SrvPersistLightXmlMessageFull() {
    srvSaveXmlMessage = new SrvSaveXmlMessageFull<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlMessage.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlMessageFull<P> getSrvSaveXmlMessage() {
    return srvSaveXmlMessage;
  }

  public void setSrvSaveXmlMessage(SrvSaveXmlMessageFull<P> srvSaveXmlMessage) {
    this.srvSaveXmlMessage = srvSaveXmlMessage;
  }
}
