package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.persist.SrvSaveXmlPoint2D;
import org.beigesoft.service.ISrvSaveModel;
import org.beigesoft.service.persist.xml.ASrvSaveXmlBase;
import org.beigesoft.uml.model.AShapeRelationshipBase;

public class SrvSaveXmlShapeRelationship<P extends AShapeRelationshipBase> extends ASrvSaveXmlBase  
    implements ISrvSaveModel<P, BufferedWriter> {

  public static final String NAMEXML_RELATIONSHIPEND = "typeEndRelationship";

  public static final String NAMEXML_SHAPEID = "shapeId";
  
  public static final String NAMEXML_ISOWNED = "isOwned";

  public static final String NAMEXML_POINTJOINT = "pointJoint";
  
  private final SrvSaveXmlPoint2D<Point2D> srvSaveXmlPoint2D;
  
  public SrvSaveXmlShapeRelationship(String namePersistable) {
    super(namePersistable);
    srvSaveXmlPoint2D = new SrvSaveXmlPoint2D<Point2D>(NAMEXML_POINTJOINT);
  }

  @Override
  public void persistModel(P p, BufferedWriter bf) throws Exception {
    bf.write(toStartElementOpened(getNamePersistable()) +
        toAttribute(NAMEXML_SHAPEID, p.getShape().getId().toString()) +
        writeOtherAttrs(p) +
        endElementOpenedAndNewLine());
    srvSaveXmlPoint2D.persistModel(p.getPointJoint(), bf);
    bf.write(toStartElement(NAMEXML_RELATIONSHIPEND) + toEnumNameOrNull(p.getEndType())
        + toEndElementAndNewLine(NAMEXML_RELATIONSHIPEND));
    bf.write(toStartElement(NAMEXML_ISOWNED) + p.getIsOwned()
        + toEndElementAndNewLine(NAMEXML_ISOWNED));
    writeOtherElements(p, bf);
    bf.write(toEndElementAndNewLine(getNamePersistable()));
  }

  //Utils:
  protected String writeOtherAttrs(P p) {
    return "";
  }

  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    // nothing
  }

  public SrvSaveXmlPoint2D<Point2D> getSrvSaveXmlPoint2D() {
    return srvSaveXmlPoint2D;
  }
}
