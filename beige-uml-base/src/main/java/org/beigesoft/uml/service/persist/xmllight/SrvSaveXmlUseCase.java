package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.UseCase;

public class SrvSaveXmlUseCase<P extends UseCase> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_USECASE = UseCase.class.getSimpleName();

  public static final String NAMEXML_ISRECTANGLE = "isRectangle";
  
  public SrvSaveXmlUseCase(String namePersistable) {
    super(namePersistable);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(SrvSaveXmlClassUml.NAMEXML_ITSNAME) + toStringOrNull(p.getItsName()) +
        toEndElementAndNewLine(SrvSaveXmlClassUml.NAMEXML_ITSNAME));
    bf.write(toStartElement(NAMEXML_ISRECTANGLE) + p.getIsRectangle() +
        toEndElementAndNewLine(NAMEXML_ISRECTANGLE));
  }
}
