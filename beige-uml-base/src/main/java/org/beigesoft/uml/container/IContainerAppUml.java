package org.beigesoft.uml.container;

import org.beigesoft.graphic.IPaneDrawing;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.service.ISrvZoom;
import org.beigesoft.uml.app.assembly.AsmProjectUml;
import org.beigesoft.uml.app.model.SettingsGraphicUml;

public interface IContainerAppUml<ITD, SD extends ISettingsDraw, IMG> {

  public AsmProjectUml getAsmProjectUml();
  
  public IPaneDrawing<ITD> getPaneDrawing();

  public SettingsGraphicUml getSettingsGraphicUml();

  public ISrvDraw<ITD, SD, IMG> getSrvDraw();
  
  public ISrvZoom getSrvZoom();
}
