package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;

public class SrvPersistLightXmlRelationshipBinaryClass implements ISrvPersist<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, FileAndWriter> {

  private SrvSaveXmlRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
  SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
  RectangleRelationship<ClassFull<ClassUml>, ClassUml>,
  ClassFull<ClassUml>, ClassUml> srvSaveXmlRelationshipBinary;
  
  public SrvPersistLightXmlRelationshipBinaryClass() {
    SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvSaveXmlRectangleRelationshipStart = new SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipBinary.NAMEXML_CLASSRELATIONSHIPSTART);
    SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvSaveXmlRectangleRelationshipEnd = new SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipBinary.NAMEXML_CLASSRELATIONSHIPEND);
    srvSaveXmlRelationshipBinary = new SrvSaveXmlRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
        SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
        RectangleRelationship<ClassFull<ClassUml>, ClassUml>,
        ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipBinary.NAMEXML_RELATIONSHIPBINARY, srvSaveXmlRectangleRelationshipStart, srvSaveXmlRectangleRelationshipEnd);
  }

  @Override
  public void persist(RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlRelationshipBinary.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvSaveXmlRelationshipBinary() {
    return srvSaveXmlRelationshipBinary;
  }

  public void setSrvSaveXmlRelationshipBinary(
      SrvSaveXmlRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvSaveXmlRelationshipBinary) {
    this.srvSaveXmlRelationshipBinary = srvSaveXmlRelationshipBinary;
  }
}
