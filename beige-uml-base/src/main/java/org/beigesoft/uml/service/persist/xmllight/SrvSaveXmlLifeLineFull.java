package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.pojo.Execution;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class SrvSaveXmlLifeLineFull<P extends LifeLineFull<ShapeUmlWithName>> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_LIFELINEUML = LifeLineFull.class.getSimpleName();

  public static final String NAMEXML_ITSFRAME = "itsFrame";
  
  public static final String NAMEXML_OCCURENCEENDY = "occurenceEndY";
  
  public static final String NAMEXML_EXECUTION = "execution";
  
  public static final String NAMEXML_EXECUTIONYSTART = "startY";

  public static final String NAMEXML_EXECUTIONYEND = "endY";

  public SrvSaveXmlLifeLineFull() {
    super(NAMEXML_LIFELINEUML);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(SrvSaveXmlClassUml.NAMEXML_ITSNAME) + toStringOrNull(p.getLifeLine().getItsName()) +
        toEndElementAndNewLine(SrvSaveXmlClassUml.NAMEXML_ITSNAME));
    if(p.getAsmFrameFull() != null) {
      bf.write(toStartElementOpened(NAMEXML_ITSFRAME) + toAttribute(ASrvSaveXmlElementUml.NAMEXML_ID, p.getAsmFrameFull().getElementUml().getId().toString()) +
          closeElementOpenedAndNewLine());
    }
    if(p.getDestructionOccurenceY() != null) {
      bf.write(toStartElement(NAMEXML_OCCURENCEENDY) + p.getDestructionOccurenceY() +
          toEndElementAndNewLine(NAMEXML_OCCURENCEENDY));
    }
    for(Execution exec : p.getExecutions()) {
      bf.write(toStartElementOpened(NAMEXML_EXECUTION) + toAttribute(NAMEXML_EXECUTIONYSTART, String.valueOf(exec.getStartY())) +
          toAttribute(NAMEXML_EXECUTIONYEND, String.valueOf(exec.getEndY())) +
          closeElementOpenedAndNewLine());
    }
  }
}
