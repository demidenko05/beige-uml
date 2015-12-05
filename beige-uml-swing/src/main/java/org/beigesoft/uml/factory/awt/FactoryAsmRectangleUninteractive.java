package org.beigesoft.uml.factory.awt;

import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUml;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.service.graphic.SrvGraphicRectangle;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRectangle;

public class FactoryAsmRectangleUninteractive implements IFactoryAsmElementUml<IAsmElementUml<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, RectangleUml> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvGraphicRectangle<RectangleUml, Graphics2D, SettingsDraw> srvGraphicRectangle;
  
  private final SrvPersistLightXmlRectangle<RectangleUml> srvPersistLightXmlRectangle;
    
  public FactoryAsmRectangleUninteractive(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw, SettingsGraphicUml settingsGraphic) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    srvGraphicRectangle = new SrvGraphicRectangle<RectangleUml, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistLightXmlRectangle = new SrvPersistLightXmlRectangle<RectangleUml>();
  }

  @Override
  public IAsmElementUml<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RectangleUml frameUml = new RectangleUml();
    AsmElementUml<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter> asmRectangle = new AsmElementUml<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter>(frameUml, 
        drawSettings, srvGraphicRectangle, srvPersistLightXmlRectangle);
    return asmRectangle;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvGraphicRectangle<RectangleUml, Graphics2D, SettingsDraw> getSrvGraphicRectangle() {
    return srvGraphicRectangle;
  }

  public SrvPersistLightXmlRectangle<RectangleUml> getSrvPersistLightXmlRectangle() {
    return srvPersistLightXmlRectangle;
  }
}
