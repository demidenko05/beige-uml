package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.uml.pojo.UseCase;

public class SaxUseCaseFiller<P extends UseCase> extends ASaxShapeUmlFiller<P> {

  public SaxUseCaseFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, ASrvSaveXmlShape.POINT_START, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlUseCase.NAMEXML_ISRECTANGLE.equals(elementName)) {
        getModel().setIsRectangle(Boolean.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlClassUml.NAMEXML_ITSNAME.equals(elementName)) {
        getModel().setItsName(elementValue);
        return true;
      }
      if(super.fillModel(elementName, elementValue)) {//shape's width height, adjust min size
        return true;
      }
    }
    return false;
  }
}
