package org.beigesoft.graphic.service.persist;

import java.io.File;

import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class InstrumentPersistXml {

  private File file;
  
  private String namePersistable;
  
  private XMLStreamWriter writer;
  
  private Node node;
  
  private Document doc;

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public XMLStreamWriter getWriter() {
    return writer;
  }

  public void setWriter(XMLStreamWriter writer) {
    this.writer = writer;
  }

  public Node getNode() {
    return node;
  }

  public void setNode(Node node) {
    this.node = node;
  }

  public Document getDoc() {
    return doc;
  }

  public void setDoc(Document doc) {
    this.doc = doc;
  }

  public String getNamePersistable() {
    return namePersistable;
  }

  public void setNamePersistable(String namePersistable) {
    this.namePersistable = namePersistable;
  }
}
