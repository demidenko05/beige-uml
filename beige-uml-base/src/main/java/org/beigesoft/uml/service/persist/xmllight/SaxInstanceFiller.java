package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.uml.model.InstanceUml;

public class SaxInstanceFiller<P extends InstanceUml> extends ASaxShapeUmlFiller<P> {

  String currentMemberValue;
  
  public SaxInstanceFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, ASrvSaveXmlShape.POINT_START, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlInstance.NAMEXML_VALUE.equals(elementName)) {
        getModel().setValue(makeString(getModel().getValue(), elementValue));
        return true;
      }
      if(SrvSaveXmlInstance.NAMEXML_CLASS.equals(elementName)) {
        getModel().setNameClass(makeString(getModel().getNameClass(), elementValue));
        return true;
      }
      if(SrvSaveXmlClassUml.NAMEXML_ITSNAME.equals(elementName)) {
        getModel().setItsName(makeString(getModel().getItsName(), elementValue));
        return true;
      }
      if(SrvSaveXmlInstance.NAMEXML_MEMBER.equals(elementName)) {
        currentMemberValue = makeString(currentMemberValue, elementValue);
      }
      if(super.fillModel(elementName, elementValue)) {//shape's width height, adjust min size isRectangle
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean handleEndElement(String localName) throws Exception {
    if(SrvSaveXmlInstance.NAMEXML_MEMBER.equals(localName)) {
      getModel().getStructure().add(new HasNameEditable(currentMemberValue));
      currentMemberValue = null;
      return true;
    }
    return super.handleEndElement(localName);
  }
}
