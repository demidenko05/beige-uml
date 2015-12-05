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
import org.beigesoft.uml.factory.swing.FactoryEditorLineUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.service.graphic.SrvGraphicLineUml;
import org.beigesoft.uml.service.interactive.SrvInteractiveLineUml;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlLineUml;

public class FactoryAsmLineUml implements IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, LineUml> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvGraphicLineUml<LineUml, Graphics2D, SettingsDraw> srvGraphicLineUml;
  
  private final SrvPersistLightXmlLineUml<LineUml> srvPersistLightXmlLineUml;
  
  private final SrvInteractiveLineUml<LineUml, Graphics2D, SettingsDraw, Frame> srvInteractiveLineUml;

  private final FactoryEditorLineUml factoryEditorLineUml;
  
  public FactoryAsmLineUml(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    srvGraphicLineUml = new SrvGraphicLineUml<LineUml, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistLightXmlLineUml = new SrvPersistLightXmlLineUml<LineUml>();
    factoryEditorLineUml = new FactoryEditorLineUml(srvI18n, srvDialog, settingsGraphic, frameMain);
    srvInteractiveLineUml = new SrvInteractiveLineUml<LineUml, Graphics2D, SettingsDraw, Frame>(srvGraphicLineUml, factoryEditorLineUml);
  }

  @Override
  public IAsmElementUmlInteractive<LineUml, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    LineUml frameUml = new LineUml();
    AsmElementUmlInteractive<LineUml, Graphics2D, SettingsDraw, FileAndWriter> asmLineUml = new AsmElementUmlInteractive<LineUml, Graphics2D, SettingsDraw, FileAndWriter>(frameUml, 
        drawSettings, srvGraphicLineUml, srvPersistLightXmlLineUml, srvInteractiveLineUml);
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

  public SrvInteractiveLineUml<LineUml, Graphics2D, SettingsDraw, Frame> getSrvInteractiveLineUml() {
    return srvInteractiveLineUml;
  }

  public FactoryEditorLineUml getFactoryEditorLineUml() {
    return factoryEditorLineUml;
  }
}
