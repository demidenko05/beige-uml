package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.pojo.ParameterMethod;

public class SrvSaveXmlMethodClass<P extends MethodClass> extends ASrvSaveXmlMemberClass<P> {

  public static final String NAMEXML_PARAMETERMETHOD = ParameterMethod.class.getSimpleName();

  private final SrvSaveXmlParameterMethod<ParameterMethod> srvSaveXmlParameterMethod;
  
  public SrvSaveXmlMethodClass(String namePersistable) {
    super(namePersistable);
    srvSaveXmlParameterMethod = new SrvSaveXmlParameterMethod<ParameterMethod>(NAMEXML_PARAMETERMETHOD);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    for(ParameterMethod pm : p.getParameters()) {
      srvSaveXmlParameterMethod.persistModel(pm, bf);
    }
  }

  //SGS:
  public SrvSaveXmlParameterMethod<ParameterMethod> getSrvSaveXmlParameterMethod() {
    return srvSaveXmlParameterMethod;
  }
}
