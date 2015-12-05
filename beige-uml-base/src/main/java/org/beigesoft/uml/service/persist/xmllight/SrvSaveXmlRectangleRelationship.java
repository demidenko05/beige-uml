package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.ShapeUml;

public class SrvSaveXmlRectangleRelationship<P extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends SrvSaveXmlShapeRelationship<P> {

  public static final String NAMEXML_SIDEJOINT = "sideJoint";
  
  public static final String NAMEXML_SIDELENGTH = "sideLength";

  public SrvSaveXmlRectangleRelationship(String namePersistable) {
    super(namePersistable);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    bf.write(toStartElement(NAMEXML_SIDEJOINT) + toEnumNameOrNull(p.getSideJoint())
        + toEndElementAndNewLine(NAMEXML_SIDEJOINT));
    bf.write(toStartElement(NAMEXML_SIDELENGTH) + p.getSideLength()
        + toEndElementAndNewLine(NAMEXML_SIDELENGTH));
  }
}
