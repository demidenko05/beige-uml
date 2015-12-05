package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.service.ISrvSaveModel;
import org.beigesoft.service.persist.xml.ASrvSaveXmlBase;
import org.beigesoft.uml.pojo.ParameterMethod;

public class SrvSaveXmlParameterMethod<P extends ParameterMethod> extends ASrvSaveXmlBase implements ISrvSaveModel<P, BufferedWriter> {

  public static final String NAMEXML_ITSTYPE = "itsType";

  public SrvSaveXmlParameterMethod(String namePersistable) {
    super(namePersistable);
  }

  @Override
  public void persistModel(P p, BufferedWriter bf) throws Exception {
    bf.write(toStartElementOpened(getNamePersistable()) +
        toAttribute(SrvSaveXmlClassUml.NAMEXML_ITSNAME, p.getItsName()) +
        writeOtherAttrs(p) +
        endElementOpenedAndNewLine());
    bf.write(toStartElement(NAMEXML_ITSTYPE) + toStringOrNull(p.getItsType()) +
        toEndElementAndNewLine(NAMEXML_ITSTYPE));
    writeOtherElements(p, bf);
    bf.write(toEndElementAndNewLine(getNamePersistable()));
  }

  //Utils:
  protected String writeOtherAttrs(P p) {
    return "";
  }
  
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    // nothing
  }
}
