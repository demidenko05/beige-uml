package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.MessageFull;

public class SrvSaveXmlCoregionFull<P extends CoregionFull> extends ASrvSaveXmlElementUml<P> {

  public static final String NAMEXML_COREGIONUML = CoregionFull.class.getSimpleName();

  public static final String NAMEXML_ITSLIFELINE = "itsLifeLine";
  
  public static final String NAMEXML_MESSAGE = "messageUml";
  
  public SrvSaveXmlCoregionFull() {
    super(NAMEXML_COREGIONUML);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    if(p.getAsmLifeLineFull() != null) {
      bf.write(toStartElementOpened(NAMEXML_ITSLIFELINE) + toAttribute(ASrvSaveXmlElementUml.NAMEXML_ID, p.getAsmLifeLineFull().getElementUml().getId().toString()) +
          closeElementOpenedAndNewLine());
    }
    for(IAsmElementUmlInteractive<MessageFull, ?, ?, ?> asmMsg : p.getAsmMessages()) {
      bf.write(toStartElementOpened(NAMEXML_MESSAGE) + toAttribute(ASrvSaveXmlElementUml.NAMEXML_ID, asmMsg.getElementUml().getId().toString()) +
          closeElementOpenedAndNewLine());
    }
  }
}
