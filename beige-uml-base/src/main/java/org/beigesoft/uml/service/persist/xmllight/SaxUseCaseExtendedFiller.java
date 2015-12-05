package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.uml.pojo.UseCaseExtended;

public class SaxUseCaseExtendedFiller<P extends UseCaseExtended> extends SaxUseCaseFiller<P> {

  public SaxUseCaseExtendedFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
       if(super.fillModel(elementName, elementValue)) {//shape's width height, adjust min size isRectangle
         return true;
       }
       if(SrvSaveXmlUseCaseExtended.NAMEXML_EXTENTIONPOINT.equals(elementName)) {
         HasNameEditable extPoint = new HasNameEditable(elementValue);
         getModel().getExtentionPoints().add(extPoint);
       }
     }
     return false;
  }
}
