package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.uml.pojo.UseCaseExtended;

public class SrvSaveXmlUseCaseExtended<P extends UseCaseExtended> extends SrvSaveXmlUseCase<P> {

  public static final String NAMEXML_USECASEEXTENDED = UseCaseExtended.class.getSimpleName();

  public static final String NAMEXML_EXTENTIONPOINT = "extentionPoint"; 

  public SrvSaveXmlUseCaseExtended(String namePersistable) {
    super(namePersistable);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    for(HasNameEditable ep : p.getExtentionPoints()) {
      bf.write(toStartElement(NAMEXML_EXTENTIONPOINT) + toStringOrNull(ep.getItsName()) +
          toEndElementAndNewLine(NAMEXML_EXTENTIONPOINT));
    }
  }
}
