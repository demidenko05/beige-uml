package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorRectangleUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.service.graphic.SrvGraphicRectangle;
import org.beigesoft.uml.service.interactive.SrvInteractiveRectangle;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRectangle;

public class FactoryAsmRectangle implements IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, RectangleUml> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvGraphicRectangle<RectangleUml, Graphics2D, SettingsDraw> srvGraphicRectangle;
  
  private final SrvPersistLightXmlRectangle<RectangleUml> srvPersistLightXmlRectangle;
  
  private final SrvInteractiveRectangle<RectangleUml, Graphics2D, SettingsDraw, Frame> srvInteractiveRectangle;

  private final FactoryEditorRectangleUml factoryEditorRectangleUml;
  
  public FactoryAsmRectangle(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    srvGraphicRectangle = new SrvGraphicRectangle<RectangleUml, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistLightXmlRectangle = new SrvPersistLightXmlRectangle<RectangleUml>();
    factoryEditorRectangleUml = new FactoryEditorRectangleUml(srvI18n, srvDialog, settingsGraphic, frameMain);
    srvInteractiveRectangle = new SrvInteractiveRectangle<RectangleUml, Graphics2D, SettingsDraw, Frame>(srvGraphicRectangle, factoryEditorRectangleUml);
  }

  @Override
  public IAsmElementUmlInteractive<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RectangleUml frameUml = new RectangleUml();
    AsmElementUmlInteractive<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter> asmRectangle = new AsmElementUmlInteractive<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter>(frameUml, 
        drawSettings, srvGraphicRectangle, srvPersistLightXmlRectangle, srvInteractiveRectangle);
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

  public SrvInteractiveRectangle<RectangleUml, Graphics2D, SettingsDraw, Frame> getSrvInteractiveRectangle() {
    return srvInteractiveRectangle;
  }

  public FactoryEditorRectangleUml getFactoryEditorRectangleUml() {
    return factoryEditorRectangleUml;
  }
}
