package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.ShapeUml;

public class SrvPersistLightXmlShapeFull<P extends ShapeFull<SH>, SH extends ShapeUml>  implements ISrvPersist<P, FileAndWriter> {

  private final ISrvPersist<SH, FileAndWriter> srvPersistShape;
   
  public SrvPersistLightXmlShapeFull(
      ISrvPersist<SH, FileAndWriter> srvPersistShape) {
    this.srvPersistShape = srvPersistShape;
  }

  @Override
  public void persist(P p, FileAndWriter pi)
      throws Exception {
    srvPersistShape.persist(p.getShape(), pi);
  }

  @Override
  public void restore(P persistable, FileAndWriter persistInstrument)
      throws Exception {
    // stub   
  }

  //SGS:
  public ISrvPersist<SH, FileAndWriter> getSrvPersistShape() {
    return srvPersistShape;
  }
}
