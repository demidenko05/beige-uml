package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.FrameUml;

public class SrvSaveXmlFrame<P extends FrameUml> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_FRAMEUML = FrameUml.class.getSimpleName();

  public static final String NAMEXML_ITSKIND = "itsKind";

  public static final String NAMEXML_PARAMETERS = "itsParameters";

  public SrvSaveXmlFrame() {
    super(NAMEXML_FRAMEUML);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(SrvSaveXmlClassUml.NAMEXML_ITSNAME) + toStringOrNull(p.getItsName()) +
        toEndElementAndNewLine(SrvSaveXmlClassUml.NAMEXML_ITSNAME));
    bf.write(toStartElement(NAMEXML_ITSKIND) + toStringOrNull(p.getItsKind()) +
        toEndElementAndNewLine(NAMEXML_ITSKIND));
    bf.write(toStartElement(NAMEXML_PARAMETERS) + toStringOrNull(p.getParameters()) +
        toEndElementAndNewLine(NAMEXML_PARAMETERS));
  }
}
