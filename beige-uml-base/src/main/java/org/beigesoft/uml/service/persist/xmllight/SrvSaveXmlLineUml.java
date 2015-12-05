package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.persist.SrvSaveXmlPoint2D;
import org.beigesoft.uml.pojo.LineUml;

public class SrvSaveXmlLineUml<P extends LineUml> extends ASrvSaveXmlElementUml<P> {

  public static final String NAMEXML_LINEUML = LineUml.class.getSimpleName();

  public static final String NAMEXML_POINT1 = "point1";
  
  public static final String NAMEXML_POINT1END = "point1End";

  public static final String NAMEXML_POINT2 = "point2";
  
  public static final String NAMEXML_POINT2END = "point2End";

  public static final String NAMEXML_ISDASHED = "isDashed";
  
  private final SrvSaveXmlPoint2D<Point2D> srvSaveXmlPoint1;
  
  private final SrvSaveXmlPoint2D<Point2D> srvSaveXmlPoint2;
  
  public SrvSaveXmlLineUml(String namePersistable) {
    super(namePersistable);
    srvSaveXmlPoint1 = new SrvSaveXmlPoint2D<Point2D>(NAMEXML_POINT1);
    srvSaveXmlPoint2 = new SrvSaveXmlPoint2D<Point2D>(NAMEXML_POINT2);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    srvSaveXmlPoint1.persistModel(p.getPoint1(), bf);
    srvSaveXmlPoint2.persistModel(p.getPoint2(), bf);
    bf.write(toStartElement(NAMEXML_ISDASHED) + p.getIsDashed()+
        toEndElementAndNewLine(NAMEXML_ISDASHED));
    if(p.getLineEnd1Shape() != null) {
      bf.write(toStartElement(NAMEXML_POINT1END) + p.getLineEnd1Shape().name() +
          toEndElementAndNewLine(NAMEXML_POINT1END));
    }
    if(p.getLineEnd2Shape() != null) {
      bf.write(toStartElement(NAMEXML_POINT2END) + p.getLineEnd2Shape().name() +
          toEndElementAndNewLine(NAMEXML_POINT2END));
    }
  }

  //SGS:
  public SrvSaveXmlPoint2D<Point2D> getSrvSaveXmlPoint1() {
    return srvSaveXmlPoint1;
  }

  public SrvSaveXmlPoint2D<Point2D> getSrvSaveXmlPoint2() {
    return srvSaveXmlPoint2;
  }
}
