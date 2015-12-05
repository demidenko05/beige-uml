package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.uml.pojo.PackageUml;

public class SaxPackageFiller<P extends PackageUml> extends ASaxShapeUmlFiller<P> {

  public SaxPackageFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, ASrvSaveXmlShape.POINT_START, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlPackage.NAMEXML_COMMENT.equals(elementName)) {
        getModel().setComment(toStringOrNull(elementValue));
        return true;
      }
      if(SrvSaveXmlPackage.NAMEXML_ISNAMEINHEAD.equals(elementName)) {
        getModel().setIsNameInHead(Boolean.valueOf(elementValue));
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
