package org.beigesoft.uml.factory.awt;

import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUml;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.service.graphic.SrvGraphicFrame;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlFrame;

public class FactoryAsmFrameUninteractive implements IFactoryAsmElementUml<IAsmElementUml<FrameUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, FrameUml> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvGraphicFrame<FrameUml, Graphics2D, SettingsDraw> srvGraphicFrame;
  
  private final SrvPersistLightXmlFrame<FrameUml> srvPersistLightXmlFrame;
    
  public FactoryAsmFrameUninteractive(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw, 
      SettingsGraphicUml settingsGraphic) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    srvGraphicFrame = new SrvGraphicFrame<FrameUml, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistLightXmlFrame = new SrvPersistLightXmlFrame<FrameUml>();
  }

  @Override
  public IAsmElementUml<FrameUml, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    FrameUml frameUml = new FrameUml();
    AsmElementUml<FrameUml, Graphics2D, SettingsDraw, FileAndWriter> asmFrame = new AsmElementUml<FrameUml, Graphics2D, SettingsDraw, FileAndWriter>(frameUml, 
        drawSettings, srvGraphicFrame, srvPersistLightXmlFrame);
    return asmFrame;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvGraphicFrame<FrameUml, Graphics2D, SettingsDraw> getSrvGraphicFrame() {
    return srvGraphicFrame;
  }

  public SrvPersistLightXmlFrame<FrameUml> getSrvPersistLightXmlFrame() {
    return srvPersistLightXmlFrame;
  }
}
