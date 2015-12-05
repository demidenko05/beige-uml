package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.PackageUml;

public class SrvSaveXmlPackage<P extends PackageUml> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_PACKAGEUML = PackageUml.class.getSimpleName();

  public static final String NAMEXML_COMMENT = "itsComment";

  public static final String NAMEXML_ISNAMEINHEAD = "isNameInHead";

  public SrvSaveXmlPackage() {
    super(NAMEXML_PACKAGEUML);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(SrvSaveXmlClassUml.NAMEXML_ITSNAME) + toStringOrNull(p.getItsName()) +
        toEndElementAndNewLine(SrvSaveXmlClassUml.NAMEXML_ITSNAME));
    bf.write(toStartElement(NAMEXML_COMMENT) + toStringOrNull(p.getComment()) +
        toEndElementAndNewLine(NAMEXML_COMMENT));
    bf.write(toStartElement(NAMEXML_ISNAMEINHEAD) + p.getIsNameInHead() +
        toEndElementAndNewLine(NAMEXML_ISNAMEINHEAD));
  }
}
