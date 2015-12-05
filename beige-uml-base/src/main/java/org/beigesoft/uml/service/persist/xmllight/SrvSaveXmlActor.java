package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.Actor;

public class SrvSaveXmlActor<P extends Actor> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_ACTORUML = Actor.class.getSimpleName();

  public static final String NAMEXML_PATHIMAGE = "pathImage";

  public SrvSaveXmlActor() {
    super(NAMEXML_ACTORUML);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(SrvSaveXmlClassUml.NAMEXML_ITSNAME) + toStringOrNull(p.getItsName()) +
        toEndElementAndNewLine(SrvSaveXmlClassUml.NAMEXML_ITSNAME));
    bf.write(toStartElement(NAMEXML_PATHIMAGE) + toStringOrNull(p.getPathImage()) +
        toEndElementAndNewLine(NAMEXML_PATHIMAGE));
  }
}
