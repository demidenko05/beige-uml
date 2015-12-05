package org.beigesoft.service.persist.xml;

import java.util.List;


public abstract class ASaxModelFiller<M> implements ISaxModelFiller {

  private M model;
  
  private final List<String> pathCurrent;
  
  private final String namePersistable;
  
  public ASaxModelFiller(String namePersistable, List<String> pathCurrent) {
    this.namePersistable = namePersistable;
    this.pathCurrent = pathCurrent;
  }

  @Override
  public void addElementToPath(String localName) {
    pathCurrent.add(localName);
  }

  @Override
  public void removeElementToPath(String localName) throws Exception {
    if(!pathCurrent.get(pathCurrent.size()-1).equals(localName)) {
      StringBuffer currPath = new StringBuffer();
      for(String nm : pathCurrent) {
        currPath.append("/" + nm);
      }
      throw new Exception("Inconsistent XML or algorithm error, current path is " + currPath +
          ", but ended element is " + localName);
    }
    pathCurrent.remove(pathCurrent.size()-1);
  }

  @Override
  public boolean handleStartElement(String localName) {
    return false;
  }

  @Override
  public boolean handleEndElement(String localName) throws Exception {
    return false;
  }

  public String toStringOrNull(String value) {
    if(value.equals("<Null/>")) {
      return null;
    }
    return value;
  }

  public Integer toIntegerOrNull(String value) {
    if(value.equals("<Null/>")) {
      return null;
    }
    return Integer.valueOf(value);
  }

  public void setModelAndPrepare(M model) {
    this.model = model;
  }

  public boolean isConsumableForElement(String elementName) {
    int idx = getPathCurrent().size() - 2;
    return getModel() != null && idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx));
  }
  

  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return getModel() != null && getNamePersistable().equals(elementName);
  }

  public String makeString(String str, String value) {
    if(str == null) {
      return value;
    }
    else {
      return str + value;
    }
  }
  
  //SGS:
  public M getModel() {
    return model;
  }

  public List<String> getPathCurrent() {
    return pathCurrent;
  }

  public String getNamePersistable() {
    return namePersistable;
  }
}
