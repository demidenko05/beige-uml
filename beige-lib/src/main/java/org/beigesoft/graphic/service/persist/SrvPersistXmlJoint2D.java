package org.beigesoft.graphic.service.persist;

import javax.xml.stream.XMLStreamWriter;

import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.graphic.pojo.Joint2D;
import org.w3c.dom.Node;

public class SrvPersistXmlJoint2D<P extends Joint2D> extends SrvPersistXmlPoint2D<P> {

  public static final String JOINT_TYPE = "typeJoint";

  @Override
  protected void writeOtherElementAttrsToXml(P p, XMLStreamWriter writer)
      throws Exception {
    writer.writeAttribute(JOINT_TYPE, p.getTypeJoint().name());
  }

  @Override
  protected void readOtherElementAttrsFromXml(P p, Node node) {
    EJoint2DType jType = EJoint2DType.valueOf(node.getAttributes().
        getNamedItem(JOINT_TYPE).getTextContent());
    p.setTypeJoint(jType);
  }
}
