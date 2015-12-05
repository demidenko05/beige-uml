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
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.service.graphic.SrvGraphicLineUml;
import org.beigesoft.uml.service.interactive.SrvInteractiveLineUml;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlLineUml;

public class FactoryAsmLine implements IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, LineUml> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlLineUml<LineUml> srvPersistXmlLine;
  
  private final SrvGraphicLineUml<LineUml, CanvasWithPaint, SettingsDraw> srvGraphicLine;
  
  private final SrvInteractiveLineUml<LineUml, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveLine;
  
  private final FactoryEditorLine factoryEditorLine;
  
  public FactoryAsmLine(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicLine = new SrvGraphicLineUml<LineUml, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlLine = new SrvPersistLightXmlLineUml<LineUml>();
    factoryEditorLine = new FactoryEditorLine(activity, containerGui);
    srvInteractiveLine = new SrvInteractiveLineUml<LineUml, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicLine, factoryEditorLine);
  }

  @Override
  public IAsmElementUmlInteractive<LineUml, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    LineUml commentUml = new LineUml();
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUmlInteractive<LineUml, CanvasWithPaint, SettingsDraw, FileAndWriter> asmLineUml = 
        new AsmElementUmlInteractive<LineUml, CanvasWithPaint, SettingsDraw, FileAndWriter>(commentUml,
            drawSettings, srvGraphicLine, srvPersistXmlLine, srvInteractiveLine);
    return asmLineUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvPersistLightXmlLineUml<LineUml> getSrvPersistXmlLine() {
    return srvPersistXmlLine;
  }

  public SrvGraphicLineUml<LineUml, CanvasWithPaint, SettingsDraw> getSrvGraphicLine() {
    return srvGraphicLine;
  }

  public SrvInteractiveLineUml<LineUml, CanvasWithPaint, SettingsDraw, Activity> getSrvInteractiveLine() {
    return srvInteractiveLine;
  }

  public FactoryEditorLine getFactoryEditorLine() {
    return factoryEditorLine;
  }
}
