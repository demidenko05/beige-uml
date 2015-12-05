package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;
import org.beigesoft.service.ISrvSaveModel;
import org.beigesoft.service.persist.xml.ASrvSaveXmlBase;
import org.beigesoft.uml.pojo.MultiplicityElement;

public class SrvSaveXmlMultiplicityElement<P extends MultiplicityElement> extends ASrvSaveXmlBase  implements ISrvSaveModel<P, BufferedWriter> {

  public static final String NAMEXML_ISORDERED = "isOrdered";

  public static final String NAMEXML_ISUNIQUE = "isUnique";
  
  public static final String NAMEXML_LOWER = "lower";

  public static final String NAMEXML_UPPER = "upper";
  
  public SrvSaveXmlMultiplicityElement(String namePersistable) {
    super(namePersistable);
  }

  @Override
  public void persistModel(P p, BufferedWriter bf) throws Exception {
    bf.write(toStartElementAndNewLine(SrvSaveXmlAttributeClass.NAMEXML_MULTIPLICITYELEMENT));
    bf.write(toStartElement(NAMEXML_ISORDERED) + p.getIsOrdered() +
        toEndElementAndNewLine(NAMEXML_ISORDERED));
    bf.write(toStartElement(NAMEXML_ISUNIQUE) + p.getIsUnique() +
        toEndElementAndNewLine(NAMEXML_ISUNIQUE));
    bf.write(toStartElement(NAMEXML_LOWER) + toStringOrNull(p.getLower()) +
        toEndElementAndNewLine(NAMEXML_LOWER));
    bf.write(toStartElement(NAMEXML_UPPER) + toStringOrNull(p.getUpper()) +
        toEndElementAndNewLine(NAMEXML_UPPER));
    bf.write(toEndElementAndNewLine(SrvSaveXmlAttributeClass.NAMEXML_MULTIPLICITYELEMENT));
  }
}
