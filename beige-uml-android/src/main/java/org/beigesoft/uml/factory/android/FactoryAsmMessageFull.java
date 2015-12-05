package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.service.graphic.SrvGraphicMessageFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveMessageFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlMessageFull;

public class FactoryAsmMessageFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<MessageFull, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, MessageFull> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlMessageFull<MessageFull> srvPersistXmlMessageFull;
  
  private final SrvGraphicMessageFull<MessageFull, CanvasWithPaint, SettingsDraw> srvGraphicMessageFull;
  
  private final SrvInteractiveMessageFull<MessageFull, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveMessageFull;
  
  private final FactoryEditorMessageFull factoryEditorMessageFull;
  
  public FactoryAsmMessageFull(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicMessageFull = new SrvGraphicMessageFull<MessageFull, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlMessageFull = new SrvPersistLightXmlMessageFull<MessageFull>();
    factoryEditorMessageFull = new FactoryEditorMessageFull(activity, containerGui);
    srvInteractiveMessageFull = new SrvInteractiveMessageFull<MessageFull, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicMessageFull, factoryEditorMessageFull);
  }

  @Override
  public IAsmElementUmlInteractive<MessageFull, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    MessageFull asmMessageFull = new MessageFull();
    AsmElementUmlInteractive<MessageFull, CanvasWithPaint, SettingsDraw, FileAndWriter> asmMessageFullUml = 
        new AsmElementUmlInteractive<MessageFull, CanvasWithPaint, SettingsDraw, FileAndWriter>(asmMessageFull,
            drawSettings, srvGraphicMessageFull, srvPersistXmlMessageFull, srvInteractiveMessageFull);
    return asmMessageFullUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorMessageFull getFactoryEditorMessageFull() {
    return factoryEditorMessageFull;
  }

  public SrvPersistLightXmlMessageFull<MessageFull> getSrvPersistXmlMessageFull() {
    return srvPersistXmlMessageFull;
  }

  public SrvGraphicMessageFull<MessageFull, CanvasWithPaint, SettingsDraw> getSrvGraphicMessageFull() {
    return srvGraphicMessageFull;
  }

  public SrvInteractiveMessageFull<MessageFull, CanvasWithPaint, SettingsDraw, Activity> getSrvInteractiveMessageFull() {
    return srvInteractiveMessageFull;
  }
}
