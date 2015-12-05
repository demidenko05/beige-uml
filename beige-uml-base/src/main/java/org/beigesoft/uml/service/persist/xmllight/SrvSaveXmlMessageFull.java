package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.assembly.MessageFull;

public class SrvSaveXmlMessageFull<P extends MessageFull> extends ASrvSaveXmlElementUml<P> {

  public static final String NAMEXML_MESSAGEUML = MessageFull.class.getSimpleName();

  public static final String NAMEXML_ISREVERSED = "isReversed";
    
  public static final String NAMEXML_ITSKIND = "itsKind";
  
  public static final String NAMEXML_ITSFRAME = "itsFrame";
  
  public static final String NAMEXML_FRAMEROLE = "frameRole";
  
  public static final String NAMEXML_ISRIGHTSIDEOFFRAME = "isRightSideOfFrame";
  
  public static final String NAMEXML_ITSY = "itsY";
  
  public static final String NAMEXML_INTERACTIONUSESTART = "interactionUseStart";
  
  public static final String NAMEXML_ISLEFTSIDEOFINTERACTIONUSESTART = "isLeftSideOfInteractionUseStart";
  
  public static final String NAMEXML_INTERACTIONUSEEND = "interactionUseEnd";
  
  public static final String NAMEXML_ISRIGHTSIDEOFINTERACTIONUSEEND = "isRightSideOfInteractionUseEnd";

  public static final String NAMEXML_LIFELINESTART = "lifelineStart";
  
  public static final String NAMEXML_LIFELINEEND = "lifelineEnd";
  
  public SrvSaveXmlMessageFull() {
    super(NAMEXML_MESSAGEUML);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(SrvSaveXmlClassUml.NAMEXML_ITSNAME) + toStringOrNull(p.getItsName()) +
        toEndElementAndNewLine(SrvSaveXmlClassUml.NAMEXML_ITSNAME));
    bf.write(toStartElement(NAMEXML_ITSKIND) + p.getItsKind().name() +
        toEndElementAndNewLine(NAMEXML_ITSKIND));
    bf.write(toStartElement(NAMEXML_ITSY) + p.getY() +
        toEndElementAndNewLine(NAMEXML_ITSY));
    bf.write(toStartElement(NAMEXML_ISREVERSED) + p.getIsReversed() +
        toEndElementAndNewLine(NAMEXML_ISREVERSED));    
    if(p.getItsFrame() != null) {
      bf.write(toStartElementOpened(NAMEXML_ITSFRAME) + toAttribute(ASrvSaveXmlElementUml.NAMEXML_ID, p.getItsFrame().getElementUml().getId().toString()) +
          closeElementOpenedAndNewLine());
    }
    if(p.getFrameRole() != null) {
      bf.write(toStartElement(NAMEXML_FRAMEROLE) + p.getFrameRole().name() +
          toEndElementAndNewLine(NAMEXML_FRAMEROLE));
    }
    bf.write(toStartElement(NAMEXML_ISRIGHTSIDEOFFRAME) + p.getIsRightSideOfFrame() +
        toEndElementAndNewLine(NAMEXML_ISRIGHTSIDEOFFRAME));    
    if(p.getAsmInteractionUseStart() != null) {
      bf.write(toStartElementOpened(NAMEXML_INTERACTIONUSESTART) + toAttribute(ASrvSaveXmlElementUml.NAMEXML_ID, p.getAsmInteractionUseStart().getElementUml().getId().toString()) +
          closeElementOpenedAndNewLine());
    }
    bf.write(toStartElement(NAMEXML_ISLEFTSIDEOFINTERACTIONUSESTART) + p.getIsLeftSideOfInteractionUseStart() +
        toEndElementAndNewLine(NAMEXML_ISLEFTSIDEOFINTERACTIONUSESTART));    
    if(p.getAsmInteractionUseEnd() != null) {
      bf.write(toStartElementOpened(NAMEXML_INTERACTIONUSEEND) + toAttribute(ASrvSaveXmlElementUml.NAMEXML_ID, p.getAsmInteractionUseEnd().getElementUml().getId().toString()) +
          closeElementOpenedAndNewLine());
    }
    bf.write(toStartElement(NAMEXML_ISRIGHTSIDEOFINTERACTIONUSEEND) + p.getIsRightSideOfInteractionUseEnd() +
        toEndElementAndNewLine(NAMEXML_ISRIGHTSIDEOFINTERACTIONUSEEND));    
    if(p.getAsmLifeLineFullStart() != null) {
      bf.write(toStartElementOpened(NAMEXML_LIFELINESTART) + toAttribute(ASrvSaveXmlElementUml.NAMEXML_ID, p.getAsmLifeLineFullStart().getElementUml().getId().toString()) +
          closeElementOpenedAndNewLine());
    }
    if(p.getAsmLifeLineFullEnd() != null) {
      bf.write(toStartElementOpened(NAMEXML_LIFELINEEND) + toAttribute(ASrvSaveXmlElementUml.NAMEXML_ID, p.getAsmLifeLineFullEnd().getElementUml().getId().toString()) +
          closeElementOpenedAndNewLine());
    }
  }
}
