package org.beigesoft.graphic.service.persist;

import javax.xml.stream.XMLStreamWriter;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.service.ISrvPersist;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SrvPersistXmlPoint2D<P extends Point2D> implements ISrvPersist<P, InstrumentPersistXml> {

  public static final String X = "x";

  public static final String Y = "y";

  @Override
  public void persist(P p, InstrumentPersistXml pi) throws Exception {
    pi.getWriter().writeStartElement(pi.getNamePersistable());
    pi.getWriter().writeAttribute(X, Double.valueOf(p.getX()).toString());
    pi.getWriter().writeAttribute(Y, Double.valueOf(p.getY()).toString());
    writeOtherElementAttrsToXml(p, pi.getWriter());
    pi.getWriter().writeCharacters("\n");
    writeOtherElementsToXml(p, pi.getWriter());
    pi.getWriter().writeEndElement();
    pi.getWriter().writeCharacters("\n");
  }

  @Override
  public void restore(P p, InstrumentPersistXml pi) throws Exception {
    double x = Double.valueOf(pi.getNode().getAttributes().
        getNamedItem(X).getTextContent());
    double y = Double.valueOf(pi.getNode().getAttributes().
        getNamedItem(Y).getTextContent());
    p.setX(x);
    p.setY(y);
    readOtherElementAttrsFromXml(p, pi.getNode());
    NodeList nodes = pi.getNode().getChildNodes();
    for(int i=0; i<nodes.getLength(); i++) {
      readOtherElementsFromXml(p, nodes.item(i));
    }
  }
  protected void writeOtherElementAttrsToXml(P p, XMLStreamWriter writer) throws Exception {
    //nothing
  }
  
  protected void writeOtherElementsToXml(P p, XMLStreamWriter writer) throws Exception {
  //nothing
  }
  
  protected void readOtherElementAttrsFromXml(P p, Node node){
    //nothing
  }
  
  protected boolean readOtherElementsFromXml(P p, Node node) throws Exception {
    //nothing
    return true;
  }
}
