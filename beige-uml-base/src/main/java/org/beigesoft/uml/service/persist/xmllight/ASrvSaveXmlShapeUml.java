package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.uml.model.IShapeUml;

public class ASrvSaveXmlShapeUml<P extends IShapeUml> extends ASrvSaveXmlShape<P> {

  public static final String NAMEXML_ISADJUSTMINIMUMSIZE = "isAdjustMinimumSize";

  public ASrvSaveXmlShapeUml(String namePersistable) {
    super(namePersistable);
  }

  @Override
  protected String writeOtherAttrs(P p) {
    return toAttribute(ASrvSaveXmlElementUml.NAMEXML_ID, p.getId().toString());
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(ASrvSaveXmlElementUml.NAMEXML_INDEXZ) + p.getIndexZ() +
        toEndElementAndNewLine(ASrvSaveXmlElementUml.NAMEXML_INDEXZ));
    bf.write(toStartElement(NAMEXML_ISADJUSTMINIMUMSIZE) + p.getIsAdjustMinimumSize() +
        toEndElementAndNewLine(NAMEXML_ISADJUSTMINIMUMSIZE));
  }
}
