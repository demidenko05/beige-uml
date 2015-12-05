/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.service.persist.xml;

import org.apache.commons.lang.nonstatic.SrvXml;

/**
 * <p>Light-weight(memory friendly) persist XML service
 * Use simple java File writer to persist
 * and SAX to restore</p>
 * 
 * @author Yury Demidenko
 */
public abstract class ASrvSaveXmlBase {
  
  private final String namePersistable;
  
  protected final SrvXml srvXml = new SrvXml();
  
  public ASrvSaveXmlBase(String namePersistable) {
    this.namePersistable = namePersistable;
  }

  //Utils:
  public String getStartXmlAndNewLine() {
    return "<?xml version=\"1.0\" ?>\n";
  }

  public String toStartElement(String element) {
    return "<" + element + ">";
  }

  public String toStartElementAndNewLine(String element) {
    return "<" + element + ">\n";
  }

  public String toStartElementAndTwoNewLine(String element) {
    return "<" + element + ">\n\n";
  }

  public String toStartElementOpened(String element) {
    return "<" + element + " ";
  }

  public String toAttribute(String attrName, String attrValue) {
    return attrName +"=\"" + escapeXml(attrValue) + "\" ";
  }

  public String endElementOpened() {
    return ">";
  }

  public String endElementOpenedAndNewLine() {
    return ">\n";
  }

  public String closeElementOpened() {
    return "/>";
  }

  public String closeElementOpenedAndNewLine() {
    return "/>\n";
  }

  public String toEndElement(String element) {
    return "</" + element + ">";
  }

  public String toEndElementAndNewLine(String element) {
    return "</" + element + ">\n";
  }

  public String toEndElementAndTwoNewLine(String element) {
    return "</" + element + ">\n\n";
  }

  public String toStringOrNull(Object value) {
    if(value == null) {
      return "<Null/>";
    }
    return escapeXml(value.toString());
  }

  public String toEnumNameOrNull(Enum<?> value) {
    if(value == null) {
      return "<Null/>";
    }
    return value.name();
  }
  
  public String escapeXml(String origin) {
    return srvXml.escapeXml10(origin);
  }
  //SGS:
  public String getNamePersistable() {
    return namePersistable;
  }
}
