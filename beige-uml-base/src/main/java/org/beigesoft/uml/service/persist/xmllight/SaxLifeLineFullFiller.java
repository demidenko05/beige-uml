package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;
import java.util.UUID;

import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.container.IContainerAsmFramesFull;
import org.beigesoft.uml.pojo.Execution;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class SaxLifeLineFullFiller<P extends LifeLineFull<ShapeUmlWithName>> extends ASaxShapeUmlFiller<P> {

  private IContainerAsmFramesFull containerFramesUml;
  
  private IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLineFull;

  private Execution execCurr;
  
  public SaxLifeLineFullFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, ASrvSaveXmlShape.POINT_START, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlClassUml.NAMEXML_ITSNAME.equals(elementName)) {
        getModel().getLifeLine().setItsName(makeString(getModel().getItsName(), elementValue));
        return true;
      }
      if(SrvSaveXmlLifeLineFull.NAMEXML_OCCURENCEENDY.equals(elementName)) {
        getModel().setDestructionOccurenceY(Double.valueOf(elementValue));
        return true;
      }
      if(super.fillModel(elementName, elementValue)) {//shape's width height, adjust min size
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttributeItsFrame(elementName, attrName)) {
      IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> asmFrameFull = containerFramesUml.findFrameFullById(UUID.fromString(attrValue));
      getModel().setAsmFrameFull(asmFrameFull);
      asmFrameFull.getElementUml().getElements().add(asmLifeLineFull);
      return true;
    }
    if(isConsumableForAttributeExecStartY(elementName, attrName)) {
      execCurr = new Execution();
      execCurr.setStartY(Double.valueOf(attrValue));
      getModel().getExecutions().add(execCurr);
      return true;
    }
    if(isConsumableForAttributeExecEndY(elementName, attrName)) {
      execCurr.setEndY(Double.valueOf(attrValue));
      return true;
    }
    return super.fillModel(elementName, attrName, attrValue);
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        isConsumableForAttributeItsFrame(elementName, attrName) ||
        isConsumableForAttributeExecStartY(elementName, attrName) ||
        isConsumableForAttributeExecEndY(elementName, attrName);
  }

  public boolean isConsumableForAttributeItsFrame(String elementName,
      String attrName) {
    return SrvSaveXmlLifeLineFull.NAMEXML_ITSFRAME.equals(elementName) &&
        ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName);
  }

  public boolean isConsumableForAttributeExecStartY(String elementName,
      String attrName) {
    return SrvSaveXmlLifeLineFull.NAMEXML_EXECUTION.equals(elementName) &&
        SrvSaveXmlLifeLineFull.NAMEXML_EXECUTIONYSTART.equals(attrName);
  }

  public boolean isConsumableForAttributeExecEndY(String elementName,
      String attrName) {
    return SrvSaveXmlLifeLineFull.NAMEXML_EXECUTION.equals(elementName) &&
        SrvSaveXmlLifeLineFull.NAMEXML_EXECUTIONYEND.equals(attrName);
  }

  public IContainerAsmFramesFull getContainerFramesUml() {
    return containerFramesUml;
  }

  public void setContainerFramesUml(IContainerAsmFramesFull containerFramesUml) {
    this.containerFramesUml = containerFramesUml;
  }

  public IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> getAsmLifeLineFull() {
    return asmLifeLineFull;
  }

  public void setAsmLifeLineFull(IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLineFull) {
    this.asmLifeLineFull = asmLifeLineFull;
  }
}
