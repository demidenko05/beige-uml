package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.service.graphic.SrvGraphicContainerFull;
import org.beigesoft.uml.service.graphic.SrvGraphicFrame;
import org.beigesoft.uml.service.interactive.SrvInteractiveContainerFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveFragment;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlContainerFull;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlContainerFull;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlFrame;

public class FactoryAsmFrameFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, ContainerFull<FrameUml>> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlContainerFull<FrameUml> srvPersistXmlFrame;
  
  private final SrvGraphicContainerFull<FrameUml, CanvasWithPaint, SettingsDraw> srvGraphicFrame;
  
  private final SrvInteractiveContainerFull<FrameUml, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveFrame;
  
  private final FactoryEditorFrameFull factoryEditorFrame;
  
  public FactoryAsmFrameFull(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    SrvGraphicFrame<FrameUml, CanvasWithPaint, SettingsDraw> srvGraphicFr = new SrvGraphicFrame<FrameUml, CanvasWithPaint, SettingsDraw>(srvDraw, getSettingsGraphic());
    srvGraphicFrame = new SrvGraphicContainerFull<FrameUml, CanvasWithPaint, SettingsDraw>(srvGraphicFr);
    SrvSaveXmlFrame<FrameUml> srvSaveXmlContainer = new SrvSaveXmlFrame<FrameUml>();
    SrvSaveXmlContainerFull<FrameUml> srvSaveXmlFrameFull = new SrvSaveXmlContainerFull<FrameUml>(srvSaveXmlContainer);
    srvPersistXmlFrame = new SrvPersistLightXmlContainerFull<FrameUml>(srvSaveXmlFrameFull);
    factoryEditorFrame = new FactoryEditorFrameFull(activity, containerGui);
    SrvInteractiveFragment<FrameUml, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveFr = new SrvInteractiveFragment<FrameUml, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicFr, null);
    srvInteractiveFrame = new SrvInteractiveContainerFull<FrameUml, CanvasWithPaint, SettingsDraw, Activity>(srvInteractiveFr, 
        factoryEditorFrame);
  }

  @Override
  public IAsmElementUmlInteractive<ContainerFull<FrameUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    FrameUml frameUml = new FrameUml();
    SettingsDraw drawSettings = new SettingsDraw();
    ContainerFull<FrameUml> frameFull = new ContainerFull<FrameUml>(frameUml);
    AsmElementUmlInteractive<ContainerFull<FrameUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> asmFrameUml = 
        new AsmElementUmlInteractive<ContainerFull<FrameUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>(frameFull,
            drawSettings, srvGraphicFrame, srvPersistXmlFrame, srvInteractiveFrame);
    return asmFrameUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvPersistLightXmlContainerFull<FrameUml> getSrvPersistXmlFrame() {
    return srvPersistXmlFrame;
  }
  
  public FactoryEditorFrameFull getFactoryEditorFrame() {
    return factoryEditorFrame;
  }
}
