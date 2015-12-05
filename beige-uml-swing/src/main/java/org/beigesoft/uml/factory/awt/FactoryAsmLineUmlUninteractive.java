package org.beigesoft.uml.factory.awt;

import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUml;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.service.graphic.SrvGraphicLineUml;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlLineUml;

public class FactoryAsmLineUmlUninteractive implements IFactoryAsmElementUml<IAsmElementUml<LineUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, LineUml> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvGraphicLineUml<LineUml, Graphics2D, SettingsDraw> srvGraphicLineUml;
  
  private final SrvPersistLightXmlLineUml<LineUml> srvPersistLightXmlLineUml;
  
  public FactoryAsmLineUmlUninteractive(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      SettingsGraphicUml settingsGraphic) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    srvGraphicLineUml = new SrvGraphicLineUml<LineUml, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistLightXmlLineUml = new SrvPersistLightXmlLineUml<LineUml>();
  }

  @Override
  public IAsmElementUml<LineUml, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    LineUml frameUml = new LineUml();
    AsmElementUml<LineUml, Graphics2D, SettingsDraw, FileAndWriter> asmLineUml = new AsmElementUml<LineUml, Graphics2D, SettingsDraw, FileAndWriter>(frameUml, 
        drawSettings, srvGraphicLineUml, srvPersistLightXmlLineUml);
    return asmLineUml;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvGraphicLineUml<LineUml, Graphics2D, SettingsDraw> getSrvGraphicLineUml() {
    return srvGraphicLineUml;
  }

  public SrvPersistLightXmlLineUml<LineUml> getSrvPersistLightXmlLineUml() {
    return srvPersistLightXmlLineUml;
  }
}
