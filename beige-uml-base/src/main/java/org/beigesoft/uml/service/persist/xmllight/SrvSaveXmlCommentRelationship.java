package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.persist.SrvSaveXmlPoint2D;
import org.beigesoft.service.ISrvSaveModel;
import org.beigesoft.service.persist.xml.ASrvSaveXmlBase;
import org.beigesoft.uml.pojo.CommentRelationship;

public class SrvSaveXmlCommentRelationship extends ASrvSaveXmlBase implements ISrvSaveModel<CommentRelationship, BufferedWriter> {

  public static final String NAMEXML_COMMENTRELATIONSHIP = CommentRelationship.class.getSimpleName();

  public static final String POINT_END = "pointEnd";

  private SrvSaveXmlPoint2D<Point2D> srvSaveXmlPoint2D = new SrvSaveXmlPoint2D<Point2D>(POINT_END);
  
  public SrvSaveXmlCommentRelationship() {
    super(NAMEXML_COMMENTRELATIONSHIP);
  }

  @Override
  public void persistModel(CommentRelationship p, BufferedWriter bf)
      throws Exception {
    bf.write(toStartElementAndNewLine(getNamePersistable()));
    srvSaveXmlPoint2D.persistModel(p.getPointEnd(), bf);
    bf.write(toStartElement(SrvSaveXmlRectangleRelationship.NAMEXML_SIDEJOINT) +
        toEnumNameOrNull(p.getSideJoint()) +
        toEndElementAndNewLine(SrvSaveXmlRectangleRelationship.NAMEXML_SIDEJOINT));
    bf.write(toStartElement(SrvSaveXmlRectangleRelationship.NAMEXML_SIDELENGTH) +
        p.getSideLength() +
        toEndElementAndNewLine(SrvSaveXmlRectangleRelationship.NAMEXML_SIDELENGTH));
    bf.write(toEndElementAndNewLine(getNamePersistable()));
  }

  public SrvSaveXmlPoint2D<Point2D> getSrvSaveXmlPoint2D() {
    return srvSaveXmlPoint2D;
  }

  public void setSrvSaveXmlPoint2D(SrvSaveXmlPoint2D<Point2D> srvSaveXmlPoint2D) {
    this.srvSaveXmlPoint2D = srvSaveXmlPoint2D;
  }
}
