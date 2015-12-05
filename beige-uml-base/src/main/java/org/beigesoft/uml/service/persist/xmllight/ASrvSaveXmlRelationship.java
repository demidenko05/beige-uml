package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.graphic.pojo.Joint2D;
import org.beigesoft.graphic.service.persist.SrvSaveXmlJoint2D;
import org.beigesoft.uml.pojo.RelationshipBase;

public abstract class ASrvSaveXmlRelationship<P extends RelationshipBase> 
    extends ASrvSaveXmlElementUml<P> {

  public static final String NAMEXML_SHAREDJOINT = "jointShared";

  public static final String NAMEXML_KINDRELATIONSHIP = "kindRelationship";
  
  private final SrvSaveXmlJoint2D<Joint2D> srvSaveXmlJoint2D;

  public ASrvSaveXmlRelationship(String namePersistable) {
    super(namePersistable);
    srvSaveXmlJoint2D = new SrvSaveXmlJoint2D<Joint2D>(NAMEXML_SHAREDJOINT);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    bf.write(toStartElement(NAMEXML_KINDRELATIONSHIP) + toEnumNameOrNull(p.getItsKind()) +
        toEndElementAndNewLine(NAMEXML_KINDRELATIONSHIP));
    if(p.getSharedJoint() != null) {
      srvSaveXmlJoint2D.persistModel(p.getSharedJoint(), bf);
    }
  }

  //SGS:
  public SrvSaveXmlJoint2D<Joint2D> getSrvSaveXmlJoint2D() {
    return srvSaveXmlJoint2D;
  }
}
