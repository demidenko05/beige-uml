package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.pojo.ShapeRelationshipVarious;

public class SrvSaveXmlRelationshipBinaryVarious<P extends RelationshipBinaryVarious> 
    extends ASrvSaveXmlRelationship<P> {

  public static final String NAMEXML_RELATIONSHIPBINARYVARIOUS = RelationshipBinaryVarious.class.getSimpleName();

  private final SrvSaveXmlShapeRelationship<ShapeRelationshipVarious> srvSaveXmlShapeRelationshipStart;
  
  private final SrvSaveXmlShapeRelationship<ShapeRelationshipVarious> srvSaveXmlShapeRelationshipEnd;
  
  public SrvSaveXmlRelationshipBinaryVarious(String namePersistable) {
    super(namePersistable);
    srvSaveXmlShapeRelationshipStart = new SrvSaveXmlShapeRelationship<ShapeRelationshipVarious>(SrvSaveXmlRelationshipBinary.NAMEXML_CLASSRELATIONSHIPSTART);
    srvSaveXmlShapeRelationshipEnd = new SrvSaveXmlShapeRelationship<ShapeRelationshipVarious>(SrvSaveXmlRelationshipBinary.NAMEXML_CLASSRELATIONSHIPEND);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    srvSaveXmlShapeRelationshipStart.persistModel(p.getShapeRelationshipStart(), bf);
    srvSaveXmlShapeRelationshipEnd.persistModel(p.getShapeRelationshipEnd(), bf);
  }

  //SGS:
  public SrvSaveXmlShapeRelationship<ShapeRelationshipVarious> getSrvSaveXmlShapeRelationshipStart() {
    return srvSaveXmlShapeRelationshipStart;
  }

  public SrvSaveXmlShapeRelationship<ShapeRelationshipVarious> getSrvSaveXmlShapeRelationshipEnd() {
    return srvSaveXmlShapeRelationshipEnd;
  }
}
