package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.InteractionUse;

public class SrvSaveXmlInteractionUse<P extends InteractionUse> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_INTERACTIONUSE = InteractionUse.class.getSimpleName();

  public static final String NAMEXML_DESCRIPTION = "itsDescription";

  public SrvSaveXmlInteractionUse() {
    super(NAMEXML_INTERACTIONUSE);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(NAMEXML_DESCRIPTION) + toStringOrNull(p.getDescription()) +
        toEndElementAndNewLine(NAMEXML_DESCRIPTION));
  }
}
