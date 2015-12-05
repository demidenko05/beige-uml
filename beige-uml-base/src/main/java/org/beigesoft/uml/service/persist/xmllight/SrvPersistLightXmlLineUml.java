package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.LineUml;

public class SrvPersistLightXmlLineUml<P extends LineUml> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlLineUml<P> srvSaveXmlLineUml;
  
  public SrvPersistLightXmlLineUml() {
    srvSaveXmlLineUml = new SrvSaveXmlLineUml<P>(SrvSaveXmlLineUml.NAMEXML_LINEUML);
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlLineUml.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlLineUml<P> getSrvSaveXmlLineUml() {
    return srvSaveXmlLineUml;
  }

  public void setSrvSaveXmlLineUml(SrvSaveXmlLineUml<P> srvSaveXmlLineUml) {
    this.srvSaveXmlLineUml = srvSaveXmlLineUml;
  }
}
