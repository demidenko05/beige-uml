package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class SrvPersistLightXmlLifeLineFull<P extends LifeLineFull<ShapeUmlWithName>> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlLifeLineFull<P> srvSaveXmlLifeLine;
  
  public SrvPersistLightXmlLifeLineFull() {
    srvSaveXmlLifeLine = new SrvSaveXmlLifeLineFull<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlLifeLine.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlLifeLineFull<P> getSrvSaveXmlLifeLine() {
    return srvSaveXmlLifeLine;
  }

  public void setSrvSaveXmlLifeLine(SrvSaveXmlLifeLineFull<P> srvSaveXmlLifeLine) {
    this.srvSaveXmlLifeLine = srvSaveXmlLifeLine;
  }
}
