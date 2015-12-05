package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.ShapeUml;

public class SrvSaveXmlRelationshipSelf<P extends RelationshipSelf<SHR, SHF, SH>, SSX extends SrvSaveXmlShapeRelationship<SHR>, SHR extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml>
    extends SrvSaveXmlRelationshipBinary<P, SSX, SHR, SHF, SH>{

  public static final String NAMEXML_RELATIONSHIPSELF = RelationshipSelf.class.getSimpleName();

  public SrvSaveXmlRelationshipSelf(String namePersistable,
      SSX srvSaveXmlShapeRelationshipStart, SSX srvSaveXmlShapeRelationshipEnd) {
    super(namePersistable, srvSaveXmlShapeRelationshipStart,
        srvSaveXmlShapeRelationshipEnd);
  }
}
