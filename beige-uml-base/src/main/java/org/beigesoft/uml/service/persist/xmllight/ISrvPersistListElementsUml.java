package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.diagram.assembly.IAsmListElementsUml;
import org.beigesoft.uml.model.IElementUml;

public interface ISrvPersistListElementsUml<AEU extends IAsmElementUml<EU, DRI, SD, PRI>, DRI, SD extends ISettingsDraw, PRI, EU extends IElementUml> {

  public void persist(IAsmListElementsUml<AEU, DRI, SD, ?, PRI, EU> asmLstElementsUml, 
      PRI persistInstrument) throws Exception;
  
  public void restore(IAsmListElementsUml<AEU, DRI, SD, ?, PRI, EU> asmLstElementsUml, 
      PRI persistInstrument) throws Exception;
}
