package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;

public class SrvPersistLightXmlRelationshipBinaryVarious<P extends RelationshipBinaryVarious> implements ISrvPersist<P, FileAndWriter> {

  private SrvSaveXmlRelationshipBinaryVarious<P> srvSaveXmlRelationshipBinaryVar;
  
  public SrvPersistLightXmlRelationshipBinaryVarious() {
    srvSaveXmlRelationshipBinaryVar = new SrvSaveXmlRelationshipBinaryVarious<P>(SrvSaveXmlRelationshipBinaryVarious.NAMEXML_RELATIONSHIPBINARYVARIOUS);
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlRelationshipBinaryVar.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(P p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlRelationshipBinaryVarious<P> getSrvSaveXmlRelationshipBinaryVar() {
    return srvSaveXmlRelationshipBinaryVar;
  }

  public void setSrvSaveXmlRelationshipBinaryVar(
      SrvSaveXmlRelationshipBinaryVarious<P> srvSaveXmlRelationshipBinaryVar) {
    this.srvSaveXmlRelationshipBinaryVar = srvSaveXmlRelationshipBinaryVar;
  }
}
