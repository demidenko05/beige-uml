package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.Actor;

public class SrvPersistLightXmlActor<P extends Actor> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlActor<P> srvSaveXmlActor;
  
  public SrvPersistLightXmlActor() {
    srvSaveXmlActor = new SrvSaveXmlActor<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlActor.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlActor<P> getSrvSaveXmlActor() {
    return srvSaveXmlActor;
  }

  public void setSrvSaveXmlActor(SrvSaveXmlActor<P> srvSaveXmlActor) {
    this.srvSaveXmlActor = srvSaveXmlActor;
  }
}
