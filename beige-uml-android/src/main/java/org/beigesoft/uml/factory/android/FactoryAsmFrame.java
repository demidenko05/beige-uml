package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.service.graphic.SrvGraphicFrame;
import org.beigesoft.uml.service.interactive.SrvInteractiveFragment;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlFrame;

public class FactoryAsmFrame implements IFactoryAsmElementUml<IAsmElementUmlInteractive<FrameUml, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, FrameUml> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlFrame<FrameUml> srvPersistXmlFrame;
  
  private final SrvGraphicFrame<FrameUml, CanvasWithPaint, SettingsDraw> srvGraphicFrame;
  
  private final SrvInteractiveFragment<FrameUml, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveFrame;
  
  private final FactoryEditorFrame factoryEditorFrame;
  
  public FactoryAsmFrame(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicFrame = new SrvGraphicFrame<FrameUml, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlFrame = new SrvPersistLightXmlFrame<FrameUml>();
    factoryEditorFrame = new FactoryEditorFrame(activity, containerGui);
    srvInteractiveFrame = new SrvInteractiveFragment<FrameUml, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicFrame, 
        factoryEditorFrame);
  }

  @Override
  public IAsmElementUmlInteractive<FrameUml, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    FrameUml commentUml = new FrameUml();
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUmlInteractive<FrameUml, CanvasWithPaint, SettingsDraw, FileAndWriter> asmFrameUml = 
        new AsmElementUmlInteractive<FrameUml, CanvasWithPaint, SettingsDraw, FileAndWriter>(commentUml,
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

  public SrvPersistLightXmlFrame<FrameUml> getSrvPersistXmlFrame() {
    return srvPersistXmlFrame;
  }

  public SrvGraphicFrame<FrameUml, CanvasWithPaint, SettingsDraw> getSrvGraphicFrame() {
    return srvGraphicFrame;
  }

  public SrvInteractiveFragment<FrameUml, CanvasWithPaint, SettingsDraw, Activity> getSrvInteractiveFrame() {
    return srvInteractiveFrame;
  }

  public FactoryEditorFrame getFactoryEditorFrame() {
    return factoryEditorFrame;
  }
}
