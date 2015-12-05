package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;
import java.util.UUID;

import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.container.IContainerAsmLifeLinesFull;
import org.beigesoft.uml.container.IContainerAsmMessagesFull;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class SaxCoregionFullFiller<P extends CoregionFull> extends ASaxElementUml<P> {

  private IContainerAsmLifeLinesFull containerAsmLifeLinesFull;
  
  private IContainerAsmMessagesFull containerAsmMessagesFull;
    
  private IAsmElementUmlInteractive<CoregionFull, ?, ?, ?> asmCoregionFull;
  
  public SaxCoregionFullFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }
  
  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttributeItsLifeLine(elementName, attrName)) {
      IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLine = containerAsmLifeLinesFull.findLifeLineFullById(UUID.fromString(attrValue));
      getModel().setAsmLifeLineFull(asmLifeLine);
      return true;
    }
    if(isConsumableForAttributeMessageUml(elementName, attrName)) {
      IAsmElementUmlInteractive<MessageFull, ?, ?, ?> asmMessageFull = containerAsmMessagesFull.findMessageFullById(UUID.fromString(attrValue));
      getModel().getAsmMessages().add(asmMessageFull);
      return true;
    }
    return super.fillModel(elementName, attrName, attrValue);
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        isConsumableForAttributeItsLifeLine(elementName, attrName) ||
        isConsumableForAttributeMessageUml(elementName, attrName);
  }

  public boolean isConsumableForAttributeItsLifeLine(String elementName,
      String attrName) {
    return SrvSaveXmlCoregionFull.NAMEXML_ITSLIFELINE.equals(elementName) &&
        ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName);
  }

  public boolean isConsumableForAttributeMessageUml(String elementName,
      String attrName) {
    return SrvSaveXmlCoregionFull.NAMEXML_MESSAGE.equals(elementName) &&
        ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName);
  }

  public IAsmElementUmlInteractive<CoregionFull, ?, ?, ?> getAsmCoregionFull() {
    return asmCoregionFull;
  }

  public void setAsmCoregionFull(IAsmElementUmlInteractive<CoregionFull, ?, ?, ?> asmCoregionFull) {
    this.asmCoregionFull = asmCoregionFull;
  }

  public IContainerAsmMessagesFull getContainerAsmMessagesFull() {
    return containerAsmMessagesFull;
  }

  public void setContainerAsmMessagesFull(IContainerAsmMessagesFull containerAsmMessagesFull) {
    this.containerAsmMessagesFull = containerAsmMessagesFull;
  }

  public IContainerAsmLifeLinesFull getContainerAsmLifeLinesFull() {
    return containerAsmLifeLinesFull;
  }

  public void setContainerAsmLifeLinesFull(IContainerAsmLifeLinesFull containerAsmLifeLinesFull) {
    this.containerAsmLifeLinesFull = containerAsmLifeLinesFull;
  }
}
