package org.beigesoft.graphic.service.persist;

import org.beigesoft.graphic.pojo.Joint2D;

public class SrvSaveXmlJoint2D<P extends Joint2D> extends SrvSaveXmlPoint2D<P> {

  public static final String JOINT_TYPE = "typeJoint";

  public SrvSaveXmlJoint2D(String namePersistable) {
    super(namePersistable);
  }

  @Override
  protected String writeOtherAttrs(P p) {
    return toAttribute(JOINT_TYPE, p.getTypeJoint().name());
  }
}
