package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.ShapeRelationship;
import org.beigesoft.uml.pojo.ShapeUml;

public class SrvSaveXmlRelationshipBinary<P extends RelationshipBinary<SHR, SHF, SH>, SSX extends SrvSaveXmlShapeRelationship<SHR>, SHR extends ShapeRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends ASrvSaveXmlRelationship<P> {

  public static final String NAMEXML_RELATIONSHIPBINARY = RelationshipBinary.class.getSimpleName();

  public static final String NAMEXML_CLASSRELATIONSHIPSTART = "shapeRelationshipStart";

  public static final String NAMEXML_CLASSRELATIONSHIPEND = "shapeRelationshipEnd";

  private final SSX srvSaveXmlShapeRelationshipStart;
  
  private final SSX srvSaveXmlShapeRelationshipEnd;
  
  public SrvSaveXmlRelationshipBinary(String namePersistable, 
      SSX srvSaveXmlShapeRelationshipStart, SSX srvSaveXmlShapeRelationshipEnd) {
    super(namePersistable);
    this.srvSaveXmlShapeRelationshipStart = srvSaveXmlShapeRelationshipStart;
    this.srvSaveXmlShapeRelationshipEnd = srvSaveXmlShapeRelationshipEnd;
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    srvSaveXmlShapeRelationshipStart.persistModel(p.getShapeRelationshipStart(), bf);
    srvSaveXmlShapeRelationshipEnd.persistModel(p.getShapeRelationshipEnd(), bf);
  }

  //SGS:
  public SSX getSrvSaveXmlShapeRelationshipStart() {
    return srvSaveXmlShapeRelationshipStart;
  }

  public SSX getSrvSaveXmlShapeRelationshipEnd() {
    return srvSaveXmlShapeRelationshipEnd;
  }
}
