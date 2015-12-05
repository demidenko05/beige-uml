package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.commons.lang.nonstatic.SrvXml;
import org.beigesoft.delegate.IDelegate;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;

public class DelegateWriteContainedElements implements IDelegate<BufferedWriter> {

  private ContainerFull<?> containerFull;
  
  protected final SrvXml srvXml = new SrvXml();
  
  @Override
  public void makeWith(BufferedWriter bf) {
    for(IAsmElementUmlInteractive<?, ?, ?, ?> asmElmUml : containerFull.getElements()) {
      try {
        bf.write(toStartElementOpened(SrvSaveXmlContainerFull.NAMEXML_CONTAINEDELEMENT) +
            toAttribute(ASrvSaveXmlElementUml.NAMEXML_ID, asmElmUml.getElementUml().getId().toString()) +
            endElementClosedAndNewLine());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  //Utils:  
  public String endElementClosedAndNewLine() {
    return "/>\n";
  }

  public String toAttribute(String attrName, String attrValue) {
    return attrName +"=\"" + escapeXml(attrValue) + "\" ";
  }

  public String toStartElementOpened(String element) {
    return "<" + element + " ";
  }

  public String escapeXml(String origin) {
    return srvXml.escapeXml10(origin);
  }
  
  //SGS:
  public ContainerFull<?> getContainerFull() {
    return containerFull;
  }

  public void setContainerFull(ContainerFull<?> containerFull) {
    this.containerFull = containerFull;
  }
}
