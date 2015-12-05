package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.MultiplicityElement;

public class SrvSaveXmlAttributeClass<P extends AttributeClass> extends ASrvSaveXmlMemberClass<P> {

  public static final String NAMEXML_MULTIPLICITYELEMENT = MultiplicityElement.class.getSimpleName();

  public static final String NAMEXML_DEFAULTVALUE = "defaultValue";

  public static final String NAMEXML_CONSTRAINTSVALUE = "constraintsValue";

  private SrvSaveXmlMultiplicityElement<MultiplicityElement> srvSaveXmlMultiplicityElement;
  
  public SrvSaveXmlAttributeClass(String namePersistable) {
    super(namePersistable);
    srvSaveXmlMultiplicityElement = new SrvSaveXmlMultiplicityElement<MultiplicityElement>(NAMEXML_MULTIPLICITYELEMENT);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(NAMEXML_DEFAULTVALUE) + toStringOrNull(p.getDefaultValue()) +
        toEndElementAndNewLine(NAMEXML_DEFAULTVALUE));
    bf.write(toStartElement(NAMEXML_CONSTRAINTSVALUE) + toStringOrNull(p.getConstraintsValue()) +
        toEndElementAndNewLine(NAMEXML_CONSTRAINTSVALUE));
    if(p.getMultiplicityElement().getLower() != null) {
      srvSaveXmlMultiplicityElement.persistModel(p.getMultiplicityElement(), bf);
    }
  }

  //SGS:
  public SrvSaveXmlMultiplicityElement<MultiplicityElement> getSrvSaveXmlMultiplicityElement() {
    return srvSaveXmlMultiplicityElement;
  }

  public void setSrvSaveXmlMultiplicityElement(
      SrvSaveXmlMultiplicityElement<MultiplicityElement> srvSaveXmlMultiplicityElement) {
    this.srvSaveXmlMultiplicityElement = srvSaveXmlMultiplicityElement;
  }
}
