package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.graphic.SrvGraphicText;
import org.beigesoft.uml.service.interactive.SrvInteractiveText;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlText;

public class FactoryAsmText implements IFactoryAsmElementUml<IAsmElementUmlInteractive<TextUml, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, TextUml> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlText<TextUml> srvPersistXmlText;
  
  private final SrvGraphicText<TextUml, CanvasWithPaint, SettingsDraw> srvGraphicText;
  
  private final SrvInteractiveText<TextUml, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveText;
  
  private final FactoryEditorText factoryEditorText;
  
  public FactoryAsmText(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicText = new SrvGraphicText<TextUml, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlText = new SrvPersistLightXmlText<TextUml>();
    factoryEditorText = new FactoryEditorText(activity, containerGui);
    srvInteractiveText = new SrvInteractiveText<TextUml, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicText, 
        factoryEditorText);
  }

  @Override
  public IAsmElementUmlInteractive<TextUml, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    TextUml textUml = new TextUml();
    textUml.setPointStart(new Point2D(1, 1));
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUmlInteractive<TextUml, CanvasWithPaint, SettingsDraw, FileAndWriter> asmTextUml = 
        new AsmElementUmlInteractive<TextUml, CanvasWithPaint, SettingsDraw, FileAndWriter>(textUml,
            drawSettings, srvGraphicText, srvPersistXmlText, srvInteractiveText);
    return asmTextUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }


  public SrvPersistLightXmlText<TextUml> getSrvPersistXmlText() {
    return srvPersistXmlText;
  }


  public SrvGraphicText<TextUml, CanvasWithPaint, SettingsDraw> getSrvGraphicText() {
    return srvGraphicText;
  }


  public SrvInteractiveText<TextUml, CanvasWithPaint, SettingsDraw, Activity> getSrvInteractiveText() {
    return srvInteractiveText;
  }


  public FactoryEditorText getFactoryEditorText() {
    return factoryEditorText;
  }
}
