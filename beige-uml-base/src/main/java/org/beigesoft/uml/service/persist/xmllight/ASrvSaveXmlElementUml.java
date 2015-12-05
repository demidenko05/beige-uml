package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.service.ISrvSaveModel;
import org.beigesoft.service.persist.xml.ASrvSaveXmlBase;
import org.beigesoft.uml.model.IElementUml;

public abstract class ASrvSaveXmlElementUml<P extends IElementUml> extends ASrvSaveXmlBase  implements ISrvSaveModel<P, BufferedWriter> {

  public static final String NAMEXML_ID = "itsId";

  public static final String NAMEXML_INDEXZ = "indexZ";

  public ASrvSaveXmlElementUml(String namePersistable) {
    super(namePersistable);
  }

  @Override
  public void persistModel(P m, BufferedWriter bf) throws Exception {
    bf.write(toStartElementOpened(getNamePersistable()) +
        toAttribute(NAMEXML_ID, m.getId().toString()) +
        writeOtherAttrs(m) +
        endElementOpenedAndNewLine());
    bf.write(toStartElement(NAMEXML_INDEXZ) + m.getIndexZ() +
        toEndElementAndNewLine(NAMEXML_INDEXZ));
    writeOtherElements(m, bf);
    bf.write(toEndElementAndTwoNewLine(getNamePersistable()));
  }

  //Utils:
  protected String writeOtherAttrs(P p) {
    return "";
  }

  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    // nothing
  }
}
