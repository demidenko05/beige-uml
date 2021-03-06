package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.StateInvContin;

public class SrvSaveXmlStateInvContin<P extends StateInvContin> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_STATEINVCONTIN = StateInvContin.class.getSimpleName();

  public static final String NAMEXML_ISNAMEBOLD = "isNameBold";

  public SrvSaveXmlStateInvContin() {
    super(NAMEXML_STATEINVCONTIN);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(SrvSaveXmlClassUml.NAMEXML_ITSNAME) + toStringOrNull(p.getItsName()) +
        toEndElementAndNewLine(SrvSaveXmlClassUml.NAMEXML_ITSNAME));
    bf.write(toStartElement(NAMEXML_ISNAMEBOLD) + p.getIsBold() +
        toEndElementAndNewLine(NAMEXML_ISNAMEBOLD));
  }
}
