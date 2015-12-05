package org.beigesoft.service.persist.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxReader extends DefaultHandler {

  protected String elementCurrent;
  
  private final ISaxModelFiller saxModelFiller;
  
  public SaxReader(ISaxModelFiller saxModelFiller) {
    super();
    this.saxModelFiller = saxModelFiller;
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    StringBuffer value = new StringBuffer();
    for (int i = start; i < start + length; i++) {
      switch (ch[i]) {
      case '\\':
        value.append("\\");
        break;
        
      case '"':
        value.append("\"");
        break;
                
      case '\n':
        value.append("\n");
        break;
        
      case '\r':
        value.append("\r");
        break;
        
      case '\t':
        value.append("\t");
        break;
        
      default:
        value.append(ch[i]);
        break;
      }
    }
    saxModelFiller.fillModel(elementCurrent, value.toString());
  }

  @Override
  public void startElement(String uri, String localName, String qName,
      Attributes attributes) throws SAXException {
    elementCurrent = localName;
    saxModelFiller.addElementToPath(elementCurrent);
    saxModelFiller.handleStartElement(elementCurrent);
    if(attributes != null && attributes.getLength() > 0) {
      for(int i=0; i<attributes.getLength(); i++) {
        String attrName = attributes.getLocalName(i);
        String attrValue = attributes.getValue(i);
        saxModelFiller.fillModel(localName, attrName, attrValue);
      }
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName)
      throws SAXException {
    elementCurrent = null;
    try {
      saxModelFiller.removeElementToPath(localName);
      saxModelFiller.handleEndElement(localName);
    } catch (Exception e) {
      throw new SAXException(e);
    }
  }
  
  public String toStringOrNull(String value) {
    if(value.equals("Null")) {
      return null;
    }
    return value;
  }

  //SGS:
  public ISaxModelFiller getSaxModelFiller() {
    return saxModelFiller;
  }
}
