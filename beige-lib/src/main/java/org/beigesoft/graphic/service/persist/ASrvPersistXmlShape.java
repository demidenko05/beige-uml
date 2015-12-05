package org.beigesoft.graphic.service.persist;

import javax.xml.stream.XMLStreamWriter;

import org.beigesoft.graphic.model.IShape;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.service.ISrvPersist;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class ASrvPersistXmlShape<P extends IShape> implements ISrvPersist<P, InstrumentPersistXml> {

  public static final String POINT_START = "pointStart";

  public static final String WIDTH = "width";

  public static final String HEIGHT = "height";

  public static final String TIED_SHAPE = "tiedShape";

  private final SrvPersistXmlPoint2D<Point2D> persistXmlPoint2dSrv = new SrvPersistXmlPoint2D<Point2D>();

  @Override
  public void persist(P p, InstrumentPersistXml pi) throws Exception {
    pi.getWriter().writeStartElement(pi.getNamePersistable());
    writeOtherElementAttrsToXml(p, pi.getWriter());
    pi.getWriter().writeCharacters("\n");
    pi.setNamePersistable(POINT_START);
    persistXmlPoint2dSrv.persist(p.getPointStart(), pi);
    pi.getWriter().writeStartElement(WIDTH);
    pi.getWriter().writeCharacters(Double.valueOf(p.getWidth()).toString());
    pi.getWriter().writeEndElement();
    pi.getWriter().writeCharacters("\n");
    pi.getWriter().writeStartElement(HEIGHT);
    pi.getWriter().writeCharacters(Double.valueOf(p.getHeight()).toString());
    pi.getWriter().writeEndElement();
    pi.getWriter().writeCharacters("\n");
    writeOtherElementsToXml(p, pi.getWriter());
    pi.getWriter().writeEndElement();
    pi.getWriter().writeCharacters("\n");
    pi.getWriter().writeCharacters("\n");
  }

  @Override
  public void restore(P p, InstrumentPersistXml pi) throws Exception {
    readOtherElementAttrsFromXml(p, pi.getNode());
    NodeList nodes = pi.getNode().getChildNodes();
    for(int i=0; i<nodes.getLength(); i++) {
      if(nodes.item(i).getNodeName().equals(POINT_START)) {
        pi.setNamePersistable(POINT_START);
        pi.setNode(nodes.item(i));
        persistXmlPoint2dSrv.restore(p.getPointStart(), pi);
      }
      else if(nodes.item(i).getNodeName().equals(WIDTH)) {
        double width = Double.valueOf(nodes.item(i).getFirstChild().getNodeValue());
        p.setWidth(width);
      }
      else if(nodes.item(i).getNodeName().equals(HEIGHT)) {
        double height = Double.valueOf(nodes.item(i).getFirstChild().getNodeValue());
        p.setHeight(height);
      }
      else {
        readOtherElementsFromXml(p, nodes.item(i));
      }
    }
  }
    
  //To override:
  
  protected abstract void writeOtherElementAttrsToXml(P p, XMLStreamWriter writer) throws Exception;
  
  protected abstract void writeOtherElementsToXml(P p, XMLStreamWriter writer) throws Exception;

  protected abstract void readOtherElementAttrsFromXml(P p, Node node) throws Exception;
  
  protected abstract boolean readOtherElementsFromXml(P p, Node node) throws Exception;

  //SGS:
  
  public SrvPersistXmlPoint2D<Point2D> getPersistXmlPoint2dSrv() {
    return persistXmlPoint2dSrv;
  }
}
