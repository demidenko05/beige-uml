package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.CommentUml;

public class SrvPersistLightXmlComment<P extends CommentUml> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlComment<P> srvSaveXmlComment;
  
  public SrvPersistLightXmlComment() {
    srvSaveXmlComment = new SrvSaveXmlComment<P>();
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlComment.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlComment<P> getSrvSaveXmlComment() {
    return srvSaveXmlComment;
  }

  public void setSrvSaveXmlComment(SrvSaveXmlComment<P> srvSaveXmlComment) {
    this.srvSaveXmlComment = srvSaveXmlComment;
  }
}
