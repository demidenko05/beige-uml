package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.uml.model.EVisibilityKind;
import org.beigesoft.uml.pojo.MemberClass;

public abstract class ASaxMemberClassFiller<P extends MemberClass> extends SaxParameterMethodFiller<P> {

  public ASaxMemberClassFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(super.fillModel(elementName, elementValue)) {
        return true;
      }
      if(SrvSaveXmlClassUml.NAMEXML_VISIBILITYKIND.equals(elementName)) {
        getModel().setVisibilityKind(EVisibilityKind.valueOf(elementValue));
        return true;
      }
    }
    return false;
  }
}
