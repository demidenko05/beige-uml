package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.pojo.ParameterMethod;

public class SaxMethodClassFiller<P extends MethodClass> extends ASaxMemberClassFiller<P> {

  private final SaxParameterMethodFiller<ParameterMethod> saxParameterMethodFiller;
    
  public SaxMethodClassFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
    saxParameterMethodFiller = 
        new SaxParameterMethodFiller<ParameterMethod>(SrvSaveXmlMethodClass.NAMEXML_PARAMETERMETHOD, 
            pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(super.isConsumableForElement(elementName)) {
      if(super.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxParameterMethodFiller.isConsumableForElement(elementName)) {
      if(saxParameterMethodFiller.fillModel(elementName, elementValue)) {
        return false;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(super.isConsumableForAttribute(elementName, attrName)) {
      if(super.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxParameterMethodFiller.isConsumableForAttribute(elementName, attrName)) {
      if(saxParameterMethodFiller.fillModel(elementName, attrName, attrValue)) {
        return false;
      }
    }
    return false;
  }

  @Override
  public boolean isConsumableForElement(String elementName) {
    return super.isConsumableForElement(elementName) ||
        saxParameterMethodFiller.isConsumableForElement(elementName);
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        saxParameterMethodFiller.isConsumableForAttribute(elementName, attrName);
  }

  @Override
  public boolean handleStartElement(String localName) {
    if(saxParameterMethodFiller.getNamePersistable().equals(localName)) {
      ParameterMethod parameterMethodCurr = new ParameterMethod();
      getModel().getParameters().add(parameterMethodCurr);
      saxParameterMethodFiller.setModelAndPrepare(parameterMethodCurr);
      return true;
    }
    return false;
  }

  @Override
  public boolean handleEndElement(String localName) throws Exception {
    if(saxParameterMethodFiller.getNamePersistable().equals(localName)) {
      saxParameterMethodFiller.setModelAndPrepare(null);
      return true;
    }
    return false;
  }

  //SGS:
  public SaxParameterMethodFiller<ParameterMethod> getSaxParameterMethodFiller() {
    return saxParameterMethodFiller;
  }
}
