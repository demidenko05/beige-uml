package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.MethodClass;

public class SaxClassUmlFiller<P extends ClassUml> extends ASaxShapeUmlFiller<P> {

  private final SaxAttributeClassFiller<AttributeClass> saxAttributeClassFiller;
  
  private final SaxMethodClassFiller<MethodClass> saxMethodClassFiller;
  
  public SaxClassUmlFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, ASrvSaveXmlShape.POINT_START, pathCurrent);
    saxAttributeClassFiller = new SaxAttributeClassFiller<AttributeClass>(SrvSaveXmlClassUml.NAMEXML_ATTRIBUTECLASS, pathCurrent);
    saxMethodClassFiller = new SaxMethodClassFiller<MethodClass>(SrvSaveXmlClassUml.NAMEXML_METHODCLASS, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(super.isConsumableForElement(elementName)) {
      if(SrvSaveXmlClassUml.NAMEXML_CLASSKIND.equals(elementName)) {
        getModel().setClassKind(EClassKind.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlClassUml.NAMEXML_ISMAIN.equals(elementName)) {
        getModel().setIsMain(Boolean.valueOf(elementValue));
        return true;
      }
      if(super.fillModel(elementName, elementValue)) {//shape's width height, adjust min size
        return true;
      }
    }
    else if(saxAttributeClassFiller.isConsumableForElement(elementName)) {
      if(saxAttributeClassFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxMethodClassFiller.isConsumableForElement(elementName)) {
      if(saxMethodClassFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(super.isConsumableForAttribute(elementName, attrName)) {
      if(super.fillModel(elementName, attrName, attrValue)) {//Point start, UUID
        return true;
      }
      if(SrvSaveXmlClassUml.NAMEXML_ITSNAME.equals(attrName)) {
        getModel().setItsName(attrValue);
        return true;
      }
      if(SrvSaveXmlClassUml.NAMEXML_NAMEPACKAGE.equals(attrName)) {
        getModel().setNamePackage(attrValue);
        return true;
      }
    }
    else if(saxAttributeClassFiller.isConsumableForAttribute(elementName, attrName)) {
      if(saxAttributeClassFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxMethodClassFiller.isConsumableForAttribute(elementName, attrName)) {
       if(saxMethodClassFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isConsumableForElement(String elementName) {
    return super.isConsumableForElement(elementName) || 
        saxAttributeClassFiller.isConsumableForElement(elementName) ||
        saxMethodClassFiller.isConsumableForElement(elementName);
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        saxAttributeClassFiller.isConsumableForAttribute(elementName, attrName) ||
        saxMethodClassFiller.isConsumableForAttribute(elementName, attrName);
  }

  @Override
  public boolean handleStartElement(String elementName) {
    if(saxAttributeClassFiller.getNamePersistable().equals(elementName)) {
      AttributeClass attributeClassCurr = new AttributeClass();
      getModel().getAttributes().add(attributeClassCurr);
      saxAttributeClassFiller.setModelAndPrepare(attributeClassCurr);
      return true;
    }
    if(saxMethodClassFiller.getNamePersistable().equals(elementName)) {
      MethodClass methodClassCurr = new MethodClass();
      getModel().getMethods().add(methodClassCurr);
      saxMethodClassFiller.setModelAndPrepare(methodClassCurr);
      return true;
    }
    if(saxMethodClassFiller.handleStartElement(elementName)) {
      return true;
    }
    if(saxAttributeClassFiller.handleStartElement(elementName)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean handleEndElement(String elementName) throws Exception {
    if(saxAttributeClassFiller.getNamePersistable().equals(elementName)) {
      saxAttributeClassFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxMethodClassFiller.getNamePersistable().equals(elementName)) {
      saxMethodClassFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxMethodClassFiller.handleEndElement(elementName)) {
      return true;
    }
    return false;
  }

  public SaxAttributeClassFiller<AttributeClass> getSaxAttributeClassFiller() {
    return saxAttributeClassFiller;
  }

  public SaxMethodClassFiller<MethodClass> getSaxMethodClassFiller() {
    return saxMethodClassFiller;
  }
}
