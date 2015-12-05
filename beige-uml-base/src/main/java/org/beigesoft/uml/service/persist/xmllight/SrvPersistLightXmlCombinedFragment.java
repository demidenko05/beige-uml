package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.CombinedFragment;

public class SrvPersistLightXmlCombinedFragment<P extends CombinedFragment> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlCombinedFragment<P> srvSaveXmlCombinedFragment;
  
  public SrvPersistLightXmlCombinedFragment() {
    srvSaveXmlCombinedFragment = new SrvSaveXmlCombinedFragment<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlCombinedFragment.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlCombinedFragment<P> getSrvSaveXmlCombinedFragment() {
    return srvSaveXmlCombinedFragment;
  }

  public void setSrvSaveXmlCombinedFragment(SrvSaveXmlCombinedFragment<P> srvSaveXmlCombinedFragment) {
    this.srvSaveXmlCombinedFragment = srvSaveXmlCombinedFragment;
  }
}
