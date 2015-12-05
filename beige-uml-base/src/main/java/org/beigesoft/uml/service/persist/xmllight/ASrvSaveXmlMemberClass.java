package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.MemberClass;

public abstract class ASrvSaveXmlMemberClass<P extends MemberClass> extends SrvSaveXmlParameterMethod<P> {

  public ASrvSaveXmlMemberClass(String namePersistable) {
    super(namePersistable);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    bf.write(toStartElement(SrvSaveXmlClassUml.NAMEXML_VISIBILITYKIND) + toEnumNameOrNull(p.getVisibilityKind()) +
        toEndElementAndNewLine(SrvSaveXmlClassUml.NAMEXML_VISIBILITYKIND));
  }
}
