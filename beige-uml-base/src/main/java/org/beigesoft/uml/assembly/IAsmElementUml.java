package org.beigesoft.uml.assembly;

import org.beigesoft.graphic.IDrawable;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.model.IElementUml;

public interface IAsmElementUml<EU extends IElementUml, DRI, SD extends ISettingsDraw, PRI> extends IDrawable<DRI> {

  public EU getElementUml();

  public SD getSettingsDraw();
  
  public void persist(PRI persistInstrument) throws Exception;

  public void restore(PRI persistInstrument) throws Exception;
}
