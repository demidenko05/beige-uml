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
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.service.graphic.SrvGraphicRectangle;
import org.beigesoft.uml.service.interactive.SrvInteractiveRectangle;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRectangle;

public class FactoryAsmRectangle implements IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, RectangleUml> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlRectangle<RectangleUml> srvPersistXmlRectangle;
  
  private final SrvGraphicRectangle<RectangleUml, CanvasWithPaint, SettingsDraw> srvGraphicRectangle;
  
  private final SrvInteractiveRectangle<RectangleUml, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveRectangle;
  
  private final FactoryEditorRectangle factoryEditorRectangle;
  
  public FactoryAsmRectangle(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicRectangle = new SrvGraphicRectangle<RectangleUml, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlRectangle = new SrvPersistLightXmlRectangle<RectangleUml>();
    factoryEditorRectangle = new FactoryEditorRectangle(activity, containerGui);
    srvInteractiveRectangle = new SrvInteractiveRectangle<RectangleUml, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicRectangle, factoryEditorRectangle);
  }

  @Override
  public IAsmElementUmlInteractive<RectangleUml, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    RectangleUml commentUml = new RectangleUml();
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUmlInteractive<RectangleUml, CanvasWithPaint, SettingsDraw, FileAndWriter> asmRectangleUml = 
        new AsmElementUmlInteractive<RectangleUml, CanvasWithPaint, SettingsDraw, FileAndWriter>(commentUml,
            drawSettings, srvGraphicRectangle, srvPersistXmlRectangle, srvInteractiveRectangle);
    return asmRectangleUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvPersistLightXmlRectangle<RectangleUml> getSrvPersistXmlRectangle() {
    return srvPersistXmlRectangle;
  }

  public SrvGraphicRectangle<RectangleUml, CanvasWithPaint, SettingsDraw> getSrvGraphicRectangle() {
    return srvGraphicRectangle;
  }

  public SrvInteractiveRectangle<RectangleUml, CanvasWithPaint, SettingsDraw, Activity> getSrvInteractiveRectangle() {
    return srvInteractiveRectangle;
  }

  public FactoryEditorRectangle getFactoryEditorRectangle() {
    return factoryEditorRectangle;
  }
}
