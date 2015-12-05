package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.PackageUml;

public class SrvPersistLightXmlPackage<P extends PackageUml> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlPackage<P> srvSaveXmlPackage;
  
  public SrvPersistLightXmlPackage() {
    srvSaveXmlPackage = new SrvSaveXmlPackage<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlPackage.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlPackage<P> getSrvSaveXmlPackage() {
    return srvSaveXmlPackage;
  }

  public void setSrvSaveXmlPackage(SrvSaveXmlPackage<P> srvSaveXmlPackage) {
    this.srvSaveXmlPackage = srvSaveXmlPackage;
  }
}
