package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.ShapeRelationship;
import org.beigesoft.uml.pojo.ShapeUml;

public class SrvSaveXmlRelationshipPoly<P extends RelationshipPoly<SHR, SHF, SH>, SSX extends SrvSaveXmlShapeRelationship<SHR>, SHR extends ShapeRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml>
    extends ASrvSaveXmlRelationship<P> {

  public static final String NAMEXML_RELATIONSHIPPOLY = RelationshipPoly.class.getSimpleName();

  public static final String NAMEXML_SHAPERELATIONSHIP = "shapeRelationship";

  private final SSX srvSaveXmlShapeRelationship;
  
  public SrvSaveXmlRelationshipPoly(String namePersistable, SSX srvSaveXmlShapeRelationship) {
    super(namePersistable);
    this.srvSaveXmlShapeRelationship = srvSaveXmlShapeRelationship;
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    for(SHR shr : p.getShapesRelationship()) {
      srvSaveXmlShapeRelationship.persistModel(shr, bf);
    }
  }

  //SGS:
  public SSX getSrvSaveXmlShapeRelationship() {
    return srvSaveXmlShapeRelationship;
  }
}
