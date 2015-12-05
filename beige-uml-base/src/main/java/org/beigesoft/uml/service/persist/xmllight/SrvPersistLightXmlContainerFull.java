package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.model.IContainerUml;

public class SrvPersistLightXmlContainerFull<CNT extends IContainerUml> 
    implements ISrvPersist<ContainerFull<CNT>, FileAndWriter> {

  private final SrvSaveXmlContainerFull<CNT> srvSaveXmlContainerFull;
  
  public SrvPersistLightXmlContainerFull(
      SrvSaveXmlContainerFull<CNT> srvSaveXmlContainerFull) {
    this.srvSaveXmlContainerFull = srvSaveXmlContainerFull;
  }

  @Override
  public void persist(ContainerFull<CNT> persistable,
      FileAndWriter persistInstrument) throws Exception {
    srvSaveXmlContainerFull.persistModel(persistable, persistInstrument.getBufferedWriter());
  }

  @Override
  public void restore(ContainerFull<CNT> persistable,
      FileAndWriter persistInstrument) throws Exception {
    // stub
  }

  //SGS:
  public SrvSaveXmlContainerFull<CNT> getSrvSaveXmlContainerFull() {
    return srvSaveXmlContainerFull;
  }
}
