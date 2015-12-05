package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;
import java.util.UUID;

import org.beigesoft.graphic.service.persist.ASaxShapeFiller;
import org.beigesoft.uml.model.IShapeUml;

public class ASaxShapeUmlFiller<P extends IShapeUml> extends ASaxShapeFiller<P> {
  
  public ASaxShapeUmlFiller(String namePersistable, String namePersistableStartPoint, List<String> pathCurrent) {
    super(namePersistable, namePersistableStartPoint, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(ASrvSaveXmlShapeUml.NAMEXML_ISADJUSTMINIMUMSIZE.equals(elementName)) {
        getModel().setIsAdjustMinimumSize(Boolean.valueOf(elementValue));
        return true;
      }
      if(ASrvSaveXmlElementUml.NAMEXML_INDEXZ.equals(elementName)) {
        getModel().setIndexZ(Integer.valueOf(elementValue));
        return true;
      }
      if(super.fillModel(elementName, elementValue)) {//shape's width height
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttribute(elementName, attrName)) {
      if(super.fillModel(elementName, attrName, attrValue)) {//Point start
        return true;
      }
      if(ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName)) {
        getModel().setId(UUID.fromString(attrValue));
        return true;
      }
    }
    return false;
  }
}
