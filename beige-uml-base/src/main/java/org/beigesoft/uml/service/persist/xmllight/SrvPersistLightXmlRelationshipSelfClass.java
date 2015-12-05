package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;

public class SrvPersistLightXmlRelationshipSelfClass implements ISrvPersist<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, FileAndWriter> {

  private SrvSaveXmlRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
  SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
  RectangleRelationship<ClassFull<ClassUml>, ClassUml>,
  ClassFull<ClassUml>, ClassUml> srvSaveXmlRelationshipSelf;
  
  public SrvPersistLightXmlRelationshipSelfClass() {
    SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvSaveXmlRectangleRelationshipStart = new SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipSelf.NAMEXML_CLASSRELATIONSHIPSTART);
    SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvSaveXmlRectangleRelationshipEnd = new SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipSelf.NAMEXML_CLASSRELATIONSHIPEND);
    srvSaveXmlRelationshipSelf = new SrvSaveXmlRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
        SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
        RectangleRelationship<ClassFull<ClassUml>, ClassUml>,
        ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipSelf.NAMEXML_RELATIONSHIPSELF, srvSaveXmlRectangleRelationshipStart, srvSaveXmlRectangleRelationshipEnd);
  }

  @Override
  public void persist(RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlRelationshipSelf.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvSaveXmlRelationshipSelf() {
    return srvSaveXmlRelationshipSelf;
  }

  public void setSrvSaveXmlRelationshipSelf(
      SrvSaveXmlRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvSaveXmlRelationshipSelf) {
    this.srvSaveXmlRelationshipSelf = srvSaveXmlRelationshipSelf;
  }
}
