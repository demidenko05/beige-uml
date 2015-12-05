package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.uml.model.InstanceUml;

public class SrvSaveXmlInstance<P extends InstanceUml> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_INSTANCEUML = InstanceUml.class.getSimpleName();

  public static final String NAMEXML_CLASS = "itsClass";

  public static final String NAMEXML_VALUE = "itsValue";

  public static final String NAMEXML_MEMBER = "itsMember";

  public SrvSaveXmlInstance() {
    super(NAMEXML_INSTANCEUML);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(SrvSaveXmlClassUml.NAMEXML_ITSNAME) + toStringOrNull(p.getItsName()) +
        toEndElementAndNewLine(SrvSaveXmlClassUml.NAMEXML_ITSNAME));
    bf.write(toStartElement(NAMEXML_CLASS) + toStringOrNull(p.getNameClass()) +
        toEndElementAndNewLine(NAMEXML_CLASS));
    bf.write(toStartElement(NAMEXML_VALUE) + toStringOrNull(p.getValue()) +
        toEndElementAndNewLine(NAMEXML_VALUE));
    for(HasNameEditable ep : p.getStructure()) {
      bf.write(toStartElement(NAMEXML_MEMBER) + toStringOrNull(ep.getItsName()) +
          toEndElementAndNewLine(NAMEXML_MEMBER));
    }
  }
}
