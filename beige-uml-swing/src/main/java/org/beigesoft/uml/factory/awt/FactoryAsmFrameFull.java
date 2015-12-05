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
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorFrameFull;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.service.graphic.SrvGraphicContainerFull;
import org.beigesoft.uml.service.graphic.SrvGraphicFrame;
import org.beigesoft.uml.service.interactive.SrvInteractiveContainerFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveFragment;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlContainerFull;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlContainerFull;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlFrame;

public class FactoryAsmFrameFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, ContainerFull<FrameUml>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorFrameFull factoryEditorFrameFull;
  
  private final SrvGraphicContainerFull<FrameUml, Graphics2D, SettingsDraw> srvGraphicFrameFull;
  
  private final SrvPersistLightXmlContainerFull<FrameUml> srvPersistLightXmlFrameFull;
  
  private final SrvInteractiveContainerFull<FrameUml, Graphics2D, SettingsDraw, Frame> srvInteractiveFrameFull;
  
  public FactoryAsmFrameFull(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorFrameFull = new FactoryEditorFrameFull(srvI18n, srvDialog, settingsGraphic, frameMain);
    SrvGraphicFrame<FrameUml, Graphics2D, SettingsDraw> srvGraphicFrame = new SrvGraphicFrame<FrameUml, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvGraphicFrameFull = new SrvGraphicContainerFull<FrameUml, Graphics2D, SettingsDraw>(srvGraphicFrame);
    SrvSaveXmlFrame<FrameUml> srvSaveXmlFrame = new SrvSaveXmlFrame<FrameUml>();
    SrvSaveXmlContainerFull<FrameUml> srvSaveXmlFrameFull = new SrvSaveXmlContainerFull<FrameUml>(srvSaveXmlFrame);
    srvPersistLightXmlFrameFull = new SrvPersistLightXmlContainerFull<FrameUml>(srvSaveXmlFrameFull);
    SrvInteractiveFragment<FrameUml, Graphics2D, SettingsDraw, Frame> srvInteractiveFrame = new SrvInteractiveFragment<FrameUml, Graphics2D, SettingsDraw, Frame>(srvGraphicFrame, null);
    srvInteractiveFrameFull = new SrvInteractiveContainerFull<FrameUml, Graphics2D, SettingsDraw, Frame>(srvInteractiveFrame, factoryEditorFrameFull);
  }

  @Override
  public IAsmElementUmlInteractive<ContainerFull<FrameUml>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    FrameUml frameUml = new FrameUml();
    ContainerFull<FrameUml> frameFull = new ContainerFull<FrameUml>(frameUml);
    AsmElementUmlInteractive<ContainerFull<FrameUml>, Graphics2D, SettingsDraw, FileAndWriter> asmFrame = new AsmElementUmlInteractive<ContainerFull<FrameUml>, Graphics2D, SettingsDraw, FileAndWriter>(frameFull, 
        drawSettings, srvGraphicFrameFull, srvPersistLightXmlFrameFull, srvInteractiveFrameFull);
    return asmFrame;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorFrameFull getFactoryEditorFrameFull() {
    return factoryEditorFrameFull;
  }

  public SrvGraphicContainerFull<FrameUml, Graphics2D, SettingsDraw> getSrvGraphicFrameFull() {
    return srvGraphicFrameFull;
  }

  public SrvPersistLightXmlContainerFull<FrameUml> getSrvPersistLightXmlFrameFull() {
    return srvPersistLightXmlFrameFull;
  }

  public SrvInteractiveContainerFull<FrameUml, Graphics2D, SettingsDraw, Frame> getSrvInteractiveFrameFull() {
    return srvInteractiveFrameFull;
  }
}
