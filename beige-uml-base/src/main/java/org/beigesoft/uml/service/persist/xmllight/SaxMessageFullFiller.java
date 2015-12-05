package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;
import java.util.UUID;

import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.container.IContainerAsmFramesFull;
import org.beigesoft.uml.container.IContainerAsmLifeLinesFull;
import org.beigesoft.uml.container.IContainerInteractionUses;
import org.beigesoft.uml.model.EFrameRoleForMessage;
import org.beigesoft.uml.model.EMessageKind;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class SaxMessageFullFiller<P extends MessageFull> extends ASaxElementUml<P> {

  private IContainerAsmFramesFull containerFramesUml;
  
  private IContainerInteractionUses containerInteractionUses;
  
  private IContainerAsmLifeLinesFull containerAsmLifeLinesFull;
  
  private IAsmElementUmlInteractive<MessageFull, ?, ?, ?> asmMessageFull;
  
  public SaxMessageFullFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlClassUml.NAMEXML_ITSNAME.equals(elementName)) {
        getModel().setItsName(makeString(getModel().getItsName(), elementValue));
        return true;
      }
      if(SrvSaveXmlMessageFull.NAMEXML_ITSY.equals(elementName)) {
        getModel().setY(Double.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlMessageFull.NAMEXML_ITSKIND.equals(elementName)) {
        getModel().setItsKind(EMessageKind.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlMessageFull.NAMEXML_FRAMEROLE.equals(elementName)) {
        getModel().setFrameRole(EFrameRoleForMessage.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlMessageFull.NAMEXML_ISREVERSED.equals(elementName)) {
        getModel().setIsReversed(Boolean.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlMessageFull.NAMEXML_ISRIGHTSIDEOFFRAME.equals(elementName)) {
        getModel().setIsRightSideOfFrame(Boolean.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlMessageFull.NAMEXML_ISLEFTSIDEOFINTERACTIONUSESTART.equals(elementName)) {
        getModel().setIsLeftSideOfInteractionUseStart(Boolean.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlMessageFull.NAMEXML_ISRIGHTSIDEOFINTERACTIONUSEEND.equals(elementName)) {
        getModel().setIsRightSideOfInteractionUseEnd(Boolean.valueOf(elementValue));
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttributeItsFrame(elementName, attrName)) {
      IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> asmFrameFull = containerFramesUml.findFrameFullById(UUID.fromString(attrValue));
      asmFrameFull.getElementUml().getElements().add(asmMessageFull);
      getModel().setItsFrame(asmFrameFull);
      return true;
    }
    if(isConsumableForAttributeInteractionUseStart(elementName, attrName)) {
      IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> asmFrameFull = containerInteractionUses.findAsmInteractionUseById(UUID.fromString(attrValue));
      getModel().setAsmInteractionUseStart(asmFrameFull);
      return true;
    }
    if(isConsumableForAttributeInteractionUseEnd(elementName, attrName)) {
      IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> asmFrameFull = containerInteractionUses.findAsmInteractionUseById(UUID.fromString(attrValue));
      getModel().setAsmInteractionUseEnd(asmFrameFull);
      return true;
    }
    if(isConsumableForAttributeLifeLineStart(elementName, attrName)) {
      IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLineFull = containerAsmLifeLinesFull.findLifeLineFullById(UUID.fromString(attrValue));
      getModel().setAsmLifeLineFullStart(asmLifeLineFull);
      return true;
    }
    if(isConsumableForAttributeLifeLineEnd(elementName, attrName)) {
      IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLineFull = containerAsmLifeLinesFull.findLifeLineFullById(UUID.fromString(attrValue));
      getModel().setAsmLifeLineFullEnd(asmLifeLineFull);
      return true;
    }
    return super.fillModel(elementName, attrName, attrValue);
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        isConsumableForAttributeItsFrame(elementName, attrName) ||
        isConsumableForAttributeInteractionUseStart(elementName, attrName) ||
        isConsumableForAttributeInteractionUseEnd(elementName, attrName) ||
        isConsumableForAttributeLifeLineStart(elementName, attrName) ||
        isConsumableForAttributeLifeLineEnd(elementName, attrName);
  }

  public boolean isConsumableForAttributeLifeLineStart(String elementName,
      String attrName) {
    return SrvSaveXmlMessageFull.NAMEXML_LIFELINESTART.equals(elementName) &&
        ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName);
  }

  public boolean isConsumableForAttributeLifeLineEnd(String elementName,
      String attrName) {
    return SrvSaveXmlMessageFull.NAMEXML_LIFELINEEND.equals(elementName) &&
        ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName);
  }

  public boolean isConsumableForAttributeInteractionUseStart(String elementName,
      String attrName) {
    return SrvSaveXmlMessageFull.NAMEXML_INTERACTIONUSESTART.equals(elementName) &&
        ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName);
  }

  public boolean isConsumableForAttributeItsFrame(String elementName,
      String attrName) {
    return SrvSaveXmlMessageFull.NAMEXML_ITSFRAME.equals(elementName) &&
        ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName);
  }

  public boolean isConsumableForAttributeInteractionUseEnd(String elementName,
      String attrName) {
    return SrvSaveXmlMessageFull.NAMEXML_INTERACTIONUSEEND.equals(elementName) &&
        ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName);
  }

  public IContainerAsmFramesFull getContainerFramesUml() {
    return containerFramesUml;
  }

  public void setContainerFramesUml(IContainerAsmFramesFull containerFramesUml) {
    this.containerFramesUml = containerFramesUml;
  }

  public IAsmElementUmlInteractive<MessageFull, ?, ?, ?> getAsmMessageFull() {
    return asmMessageFull;
  }

  public void setAsmMessageFull(IAsmElementUmlInteractive<MessageFull, ?, ?, ?> asmMessageFull) {
    this.asmMessageFull = asmMessageFull;
  }

  public IContainerAsmLifeLinesFull getContainerAsmLifeLinesFull() {
    return containerAsmLifeLinesFull;
  }

  public void setContainerAsmLifeLinesFull(IContainerAsmLifeLinesFull containerAsmLifeLinesFull) {
    this.containerAsmLifeLinesFull = containerAsmLifeLinesFull;
  }

  public IContainerInteractionUses getContainerInteractionUses() {
    return containerInteractionUses;
  }

  public void setContainerInteractionUses(IContainerInteractionUses containerInteractionUses) {
    this.containerInteractionUses = containerInteractionUses;
  }
}
