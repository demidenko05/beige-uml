package org.beigesoft.uml.factory;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.model.IElementUml;

@Deprecated //use standard IFactorySimple instead
public interface IFactoryAsmElementUml <AEU extends IAsmElementUml<EU, DRI, SD, PRI>,
    DRI, SD extends ISettingsDraw, PRI, EU extends IElementUml> {

  public AEU createAsmElementUml();
}
