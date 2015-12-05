package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.assembly.CoregionFull;

public class SrvPersistLightXmlCoregionFull<P extends CoregionFull> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlCoregionFull<P> srvSaveXmlCoregionFull;
  
  public SrvPersistLightXmlCoregionFull() {
    srvSaveXmlCoregionFull = new SrvSaveXmlCoregionFull<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlCoregionFull.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlCoregionFull<P> getSrvSaveXmlCoregionFull() {
    return srvSaveXmlCoregionFull;
  }

  public void setSrvSaveXmlCoregionFull(SrvSaveXmlCoregionFull<P> srvSaveXmlCoregionFull) {
    this.srvSaveXmlCoregionFull = srvSaveXmlCoregionFull;
  }
}
