package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.FrameUml;

public class SrvPersistLightXmlFrame<P extends FrameUml> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlFrame<P> srvSaveXmlFrame;
  
  public SrvPersistLightXmlFrame() {
    srvSaveXmlFrame = new SrvSaveXmlFrame<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlFrame.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlFrame<P> getSrvSaveXmlFrame() {
    return srvSaveXmlFrame;
  }

  public void setSrvSaveXmlFrame(SrvSaveXmlFrame<P> srvSaveXmlFrame) {
    this.srvSaveXmlFrame = srvSaveXmlFrame;
  }
}
