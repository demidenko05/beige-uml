package org.beigesoft.service.persist.xml;

public interface ISaxModelFiller {

  public boolean fillModel(String elementName, String elementValue);

  public boolean handleStartElement(String elementName);

  public boolean handleEndElement(String elementName) throws Exception;

  public void addElementToPath(String elementName);

  public void removeElementToPath(String elementName) throws Exception;

  public boolean fillModel(String elementName, String attrName, String attrValue);
}
