package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.diagram.assembly.IAsmListElementsUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.model.IElementUml;

public class SrvPersistLightXmlListElementsUml<AUC extends IAsmElementUml<UC, DRI, SD, FileAndWriter>, 
    DRI, SD extends ISettingsDraw, UC extends IElementUml>
    implements ISrvPersistListElementsUml<AUC, DRI, SD, FileAndWriter, UC> {

  private final IFactoryAsmElementUml<AUC, DRI, SD, FileAndWriter, UC> factoryAsmElementUml;
  
  private final String namePersistableXml;
  
  public SrvPersistLightXmlListElementsUml(
      IFactoryAsmElementUml<AUC, DRI, SD, FileAndWriter, UC> factoryAsmElementUml,
      String namePersistableXml) {
    this.factoryAsmElementUml = factoryAsmElementUml;
    this.namePersistableXml = namePersistableXml;
  }

  @Override
  public void persist(
      IAsmListElementsUml<AUC, DRI, SD, ?, FileAndWriter, UC> lstAsmElementsUml,
      FileAndWriter persistInstrument) throws Exception {
    for(AUC listAsmElementUml : lstAsmElementsUml.getListElementsUml()) {
      listAsmElementUml.persist(persistInstrument);
    }
  }

  @Override
  public void restore(
      IAsmListElementsUml<AUC, DRI, SD, ?, FileAndWriter, UC> lstAsmElementsUml,
      FileAndWriter persistInstrument) throws Exception {
    // stub
  }

  public IFactoryAsmElementUml<AUC, DRI, SD, FileAndWriter, UC> getFactoryAsmElementUml() {
    return factoryAsmElementUml;
  }

  public String getNamePersistableXml() {
    return namePersistableXml;
  }
}
