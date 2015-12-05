package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.RectangleUml;

public class SrvPersistLightXmlRectangle<P extends RectangleUml> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlRectangle<P> srvSaveXmlRectangle;
  
  public SrvPersistLightXmlRectangle() {
    srvSaveXmlRectangle = new SrvSaveXmlRectangle<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlRectangle.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlRectangle<P> getSrvSaveXmlRectangle() {
    return srvSaveXmlRectangle;
  }

  public void setSrvSaveXmlRectangle(SrvSaveXmlRectangle<P> srvSaveXmlRectangle) {
    this.srvSaveXmlRectangle = srvSaveXmlRectangle;
  }
}
