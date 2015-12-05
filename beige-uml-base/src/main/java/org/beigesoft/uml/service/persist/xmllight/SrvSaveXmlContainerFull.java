package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.service.ISrvSaveModel;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.model.IContainerUml;

public class SrvSaveXmlContainerFull<CNT extends IContainerUml> implements ISrvSaveModel<ContainerFull<CNT>, BufferedWriter> {

  public static String NAMEXML_CONTAINEDELEMENT = "containedElement";
  
  private final ASrvSaveXmlShapeUml<CNT> srvSaveXmlContainer;
  
  protected DelegateWriteContainedElements writererContainedElements = new DelegateWriteContainedElements();
  
  public SrvSaveXmlContainerFull(ASrvSaveXmlShapeUml<CNT> srvSaveXmlContainer) {
    this.srvSaveXmlContainer = srvSaveXmlContainer;
  }

  @Override
  public void persistModel(ContainerFull<CNT> m, BufferedWriter bf)
      throws Exception {
    writererContainedElements.setContainerFull(m);
    srvSaveXmlContainer.setDelegateSaveOtherXmlElements(writererContainedElements);
    srvSaveXmlContainer.persistModel(m.getContainer(), bf);
  }

  //SGS:
  public ASrvSaveXmlShapeUml<CNT> getSrvSaveXmlContainer() {
    return srvSaveXmlContainer;
  }
}
