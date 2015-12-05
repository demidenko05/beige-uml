package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.ClassUml;

public class SrvPersistLightXmlClassFull<P extends ClassFull<CL>, CL extends ClassUml> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlClassUml<CL> srvSaveXmlClassUml;
  
  public SrvPersistLightXmlClassFull() {
    srvSaveXmlClassUml = new SrvSaveXmlClassUml<CL>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlClassUml.persistModel(p.getShape(), pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlClassUml<CL> getSrvSaveXmlComment() {
    return srvSaveXmlClassUml;
  }

  public void setSrvSaveXmlComment(SrvSaveXmlClassUml<CL> srvSaveXmlClassUml) {
    this.srvSaveXmlClassUml = srvSaveXmlClassUml;
  }
}
