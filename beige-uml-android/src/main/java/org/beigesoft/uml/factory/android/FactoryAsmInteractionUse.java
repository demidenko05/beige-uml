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
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.service.graphic.SrvGraphicInteractionUse;
import org.beigesoft.uml.service.interactive.SrvInteractiveFragment;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlInteractionUse;

public class FactoryAsmInteractionUse implements IFactoryAsmElementUml<IAsmElementUmlInteractive<InteractionUse, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, InteractionUse> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlInteractionUse<InteractionUse> srvPersistXmlInteractionUse;
  
  private final SrvGraphicInteractionUse<InteractionUse, CanvasWithPaint, SettingsDraw> srvGraphicInteractionUse;
  
  private final SrvInteractiveFragment<InteractionUse, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveInteractionUse;
  
  private final FactoryEditorInteractionUse factoryEditorInteractionUse;
  
  public FactoryAsmInteractionUse(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicInteractionUse = new SrvGraphicInteractionUse<InteractionUse, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlInteractionUse = new SrvPersistLightXmlInteractionUse<InteractionUse>();
    factoryEditorInteractionUse = new FactoryEditorInteractionUse(activity, containerGui);
    srvInteractiveInteractionUse = new SrvInteractiveFragment<InteractionUse, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicInteractionUse, factoryEditorInteractionUse);
  }

  @Override
  public IAsmElementUmlInteractive<InteractionUse, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    InteractionUse intUse = new InteractionUse();
    intUse.setPointStart(new Point2D(1, 1));
    AsmElementUmlInteractive<InteractionUse, CanvasWithPaint, SettingsDraw, FileAndWriter> asmInteractionUseUml = 
        new AsmElementUmlInteractive<InteractionUse, CanvasWithPaint, SettingsDraw, FileAndWriter>(intUse,
            drawSettings, srvGraphicInteractionUse, srvPersistXmlInteractionUse, srvInteractiveInteractionUse);
    return asmInteractionUseUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorInteractionUse getFactoryEditorInteractionUse() {
    return factoryEditorInteractionUse;
  }

  public SrvPersistLightXmlInteractionUse<InteractionUse> getSrvPersistXmlInteractionUse() {
    return srvPersistXmlInteractionUse;
  }

  public SrvGraphicInteractionUse<InteractionUse, CanvasWithPaint, SettingsDraw> getSrvGraphicInteractionUse() {
    return srvGraphicInteractionUse;
  }

  public SrvInteractiveFragment<InteractionUse, CanvasWithPaint, SettingsDraw, Activity> getSrvInteractiveInteractionUse() {
    return srvInteractiveInteractionUse;
  }
}
