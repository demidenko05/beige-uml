package org.beigesoft.uml.service.persist.xmllight;

import java.util.UUID;

import org.beigesoft.service.persist.xml.ASaxModelFiller;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.container.IContainerInteractiveElementsUml;
import org.beigesoft.uml.model.IContainerUml;

public class SaxContainerFullFiller<CNT extends IContainerUml> extends ASaxModelFiller<ContainerFull<CNT>> {

  private final ASaxShapeUmlFiller<CNT> saxContainerFiller;
  
  private IContainerInteractiveElementsUml containerInteractiveElementsUml;
  
  public SaxContainerFullFiller(ASaxShapeUmlFiller<CNT> saxContainerFiller) {
    super(saxContainerFiller.getNamePersistable(), saxContainerFiller.getPathCurrent());
    this.saxContainerFiller = saxContainerFiller;
  }

  @Override
  public void setModelAndPrepare(ContainerFull<CNT> model) {
    super.setModelAndPrepare(model);
    if(model == null) {
      saxContainerFiller.setModelAndPrepare(null);
    }
    else {
      saxContainerFiller.setModelAndPrepare(model.getContainer());
    }
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return saxContainerFiller.isConsumableForAttribute(elementName, attrName) ||
        isConsumableForAttributeContainedElement(elementName, attrName);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    return saxContainerFiller.fillModel(elementName, elementValue);
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttribute(elementName, attrName)) {
      if(isConsumableForAttributeContainedElement(elementName, attrName)) {
        IAsmElementUmlInteractive<?, ?, ?, ?> interactiveAsmElementUml = containerInteractiveElementsUml.getInteractiveAsmElementUml(UUID.fromString(attrValue));
        if(interactiveAsmElementUml != null) {//for cross-referenced elements like LifeLine that do it itself
          getModel().getElements().add(interactiveAsmElementUml);
        }
        return true;
      }
      return saxContainerFiller.fillModel(elementName, attrName, attrValue);
    }
    return false;
  }

  public boolean isConsumableForAttributeContainedElement(String elementName,
      String attrName) {
    return SrvSaveXmlContainerFull.NAMEXML_CONTAINEDELEMENT.equals(elementName) &&
        ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName);
  }

  //SGS:
  public ASaxShapeUmlFiller<CNT> getSaxContainerFiller() {
    return saxContainerFiller;
  }

  public IContainerInteractiveElementsUml getContainerInteractiveElementsUml() {
    return containerInteractiveElementsUml;
  }

  public void setContainerInteractiveElementsUml(
      IContainerInteractiveElementsUml containerInteractiveElementsUml) {
    this.containerInteractiveElementsUml = containerInteractiveElementsUml;
  }
}
