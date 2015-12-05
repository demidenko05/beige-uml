package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.RectangleUml;

public class SrvSaveXmlRectangle<P extends RectangleUml> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_RECTANGLE = "OrdinaryRectangle";

  public static final String NAMEXML_ISTRANSPARENT = "isTransparent";

  public SrvSaveXmlRectangle() {
    super(NAMEXML_RECTANGLE);
  }
  
  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(NAMEXML_ISTRANSPARENT) + p.getIsTransparent() +
        toEndElementAndNewLine(NAMEXML_ISTRANSPARENT));
  }
}
