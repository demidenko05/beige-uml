package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.ClassUml;

public class SrvPersistLightXmlClassUml<P extends ClassUml> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlClassUml<P> srvSaveXmlClassUml;
  
  public SrvPersistLightXmlClassUml() {
    srvSaveXmlClassUml = new SrvSaveXmlClassUml<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlClassUml.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlClassUml<P> getSrvSaveXmlClassUml() {
    return srvSaveXmlClassUml;
  }

  public void setSrvSaveXmlClassUml(SrvSaveXmlClassUml<P> srvSaveXmlClassUml) {
    this.srvSaveXmlClassUml = srvSaveXmlClassUml;
  }
}
