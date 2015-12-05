package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.TextUml;

public class SrvPersistLightXmlText<P extends TextUml> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlText<P> srvSaveXmlTextUml;
  
  public SrvPersistLightXmlText() {
    srvSaveXmlTextUml = new SrvSaveXmlText<P>(SrvSaveXmlText.NAMEXML_TEXTUML);
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlTextUml.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlText<P> getSrvSaveXmlText() {
    return srvSaveXmlTextUml;
  }

  public void setSrvSaveXmlText(SrvSaveXmlText<P> srvSaveXmlTextUml) {
    this.srvSaveXmlTextUml = srvSaveXmlTextUml;
  }
}
