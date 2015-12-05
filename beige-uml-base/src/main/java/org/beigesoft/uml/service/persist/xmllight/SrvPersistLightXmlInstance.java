package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.model.InstanceUml;

public class SrvPersistLightXmlInstance<P extends InstanceUml> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlInstance<P> srvSaveXmlInstance;
  
  public SrvPersistLightXmlInstance() {
    srvSaveXmlInstance = new SrvSaveXmlInstance<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlInstance.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlInstance<P> getSrvSaveXmlInstance() {
    return srvSaveXmlInstance;
  }

  public void setSrvSaveXmlInstance(SrvSaveXmlInstance<P> srvSaveXmlInstance) {
    this.srvSaveXmlInstance = srvSaveXmlInstance;
  }
}
