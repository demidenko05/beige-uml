package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.CombinedFragment;

public class SrvSaveXmlCombinedFragment<P extends CombinedFragment> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_COMBINEDFRAGMENT = CombinedFragment.class.getSimpleName();

  public static final String NAMEXML_DESCRIPTION = "itsDescription";

  public static final String NAMEXML_INTERACTIONOPERATOR = "interactionOperator";

  public static final String NAMEXML_TRACEY = "traceY";

  public SrvSaveXmlCombinedFragment() {
    super(NAMEXML_COMBINEDFRAGMENT);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(NAMEXML_DESCRIPTION) + toStringOrNull(p.getDescription()) +
        toEndElementAndNewLine(NAMEXML_DESCRIPTION));
    bf.write(toStartElement(NAMEXML_INTERACTIONOPERATOR) + p.getInteractionOperator().name() +
        toEndElementAndNewLine(NAMEXML_INTERACTIONOPERATOR));
    for(Double traceY : p.getTracesY()) {
      bf.write(toStartElement(NAMEXML_TRACEY) + traceY +
          toEndElementAndNewLine(NAMEXML_TRACEY));
    }
  }
}
