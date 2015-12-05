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
import org.beigesoft.uml.factory.swing.FactoryEditorFrame;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.service.graphic.SrvGraphicFrame;
import org.beigesoft.uml.service.interactive.SrvInteractiveFragment;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlFrame;

public class FactoryAsmFrame implements IFactoryAsmElementUml<IAsmElementUmlInteractive<FrameUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, FrameUml> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorFrame factoryEditorFrame;
  
  private final SrvGraphicFrame<FrameUml, Graphics2D, SettingsDraw> srvGraphicFrame;
  
  private final SrvPersistLightXmlFrame<FrameUml> srvPersistLightXmlFrame;
  
  private final SrvInteractiveFragment<FrameUml, Graphics2D, SettingsDraw, Frame> srvInteractiveFrame;
  
  public FactoryAsmFrame(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorFrame = new FactoryEditorFrame(srvI18n, srvDialog, settingsGraphic, frameMain);
    srvGraphicFrame = new SrvGraphicFrame<FrameUml, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistLightXmlFrame = new SrvPersistLightXmlFrame<FrameUml>();
    srvInteractiveFrame = new SrvInteractiveFragment<FrameUml, Graphics2D, SettingsDraw, Frame>(srvGraphicFrame, factoryEditorFrame);
  }

  @Override
  public IAsmElementUmlInteractive<FrameUml, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    FrameUml frameUml = new FrameUml();
    AsmElementUmlInteractive<FrameUml, Graphics2D, SettingsDraw, FileAndWriter> asmFrame = new AsmElementUmlInteractive<FrameUml, Graphics2D, SettingsDraw, FileAndWriter>(frameUml, 
        drawSettings, srvGraphicFrame, srvPersistLightXmlFrame, srvInteractiveFrame);
    return asmFrame;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorFrame getFactoryEditorFrame() {
    return factoryEditorFrame;
  }

  public SrvGraphicFrame<FrameUml, Graphics2D, SettingsDraw> getSrvGraphicFrame() {
    return srvGraphicFrame;
  }

  public SrvPersistLightXmlFrame<FrameUml> getSrvPersistLightXmlFrame() {
    return srvPersistLightXmlFrame;
  }

  public SrvInteractiveFragment<FrameUml, Graphics2D, SettingsDraw, Frame> getSrvInteractiveFrame() {
    return srvInteractiveFrame;
  }
}
