package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipPoly;

public class SrvPersistLightXmlRelationshipPolyClass implements ISrvPersist<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, FileAndWriter> {

  private SrvSaveXmlRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
  SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
  RectangleRelationship<ClassFull<ClassUml>, ClassUml>,
  ClassFull<ClassUml>, ClassUml> srvSaveXmlRelationshipPoly;
  
  public SrvPersistLightXmlRelationshipPolyClass() {
    SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvSaveXmlRectangleRelationship = 
        new SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipPoly.NAMEXML_SHAPERELATIONSHIP);
    srvSaveXmlRelationshipPoly = new SrvSaveXmlRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
        SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
        RectangleRelationship<ClassFull<ClassUml>, ClassUml>,
        ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipPoly.NAMEXML_RELATIONSHIPPOLY, srvSaveXmlRectangleRelationship);
  }

  @Override
  public void persist(RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> p, FileAndWriter pi)
      throws Exception {
    srvSaveXmlRelationshipPoly.persistModel(p, pi.getBufferedWriter());
  }

  @Override
  public void restore(RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> p, FileAndWriter pi)
      throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvSaveXmlRelationshipPoly() {
    return srvSaveXmlRelationshipPoly;
  }

  public void setSrvSaveXmlRelationshipPoly(
      SrvSaveXmlRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, SrvSaveXmlRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvSaveXmlRelationshipPoly) {
    this.srvSaveXmlRelationshipPoly = srvSaveXmlRelationshipPoly;
  }
}
