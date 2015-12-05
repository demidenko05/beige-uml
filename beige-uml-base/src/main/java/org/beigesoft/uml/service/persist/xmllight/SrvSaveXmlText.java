package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.graphic.service.persist.SrvSaveXmlPoint2D;
import org.beigesoft.uml.pojo.TextUml;

public class SrvSaveXmlText<P extends TextUml> extends ASrvSaveXmlElementUml<P> {

  public static final String NAMEXML_TEXTUML = TextUml.class.getSimpleName();

  public static final String NAMEXML_ITSTEXT = "itsText";
  
  public static final String NAMEXML_ISBOLD = "isBold";
  
  public static final String NAMEXML_ISTRANSPARENT = "isTransparent";
  
  private final SrvSaveXmlPoint2D<Point2D> srvSaveXmlPoint2D;
  
  public SrvSaveXmlText(String namePersistable) {
    super(namePersistable);
    srvSaveXmlPoint2D = new SrvSaveXmlPoint2D<Point2D>(ASrvSaveXmlShape.POINT_START);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    srvSaveXmlPoint2D.persistModel(p.getPointStart(), bf);
    bf.write(toStartElement(NAMEXML_ITSTEXT) + toStringOrNull(p.getItsText()) +
        toEndElementAndNewLine(NAMEXML_ITSTEXT));
    bf.write(toStartElement(NAMEXML_ISBOLD) + p.getIsBold() +
        toEndElementAndNewLine(NAMEXML_ISBOLD));
    bf.write(toStartElement(NAMEXML_ISTRANSPARENT) + p.getIsTransparent() +
        toEndElementAndNewLine(NAMEXML_ISTRANSPARENT));
    if(p.getTiedShape() != null) {
      bf.write(toStartElementOpened(ASrvSaveXmlShape.TIED_SHAPE) +
          toAttribute(ASrvSaveXmlElementUml.NAMEXML_ID, p.getTiedShape().getId().toString()) +
          closeElementOpenedAndNewLine());
    }
  }

  //SGS:
  public SrvSaveXmlPoint2D<Point2D> getSrvSaveXmlPoint2D() {
    return srvSaveXmlPoint2D;
  }
}
