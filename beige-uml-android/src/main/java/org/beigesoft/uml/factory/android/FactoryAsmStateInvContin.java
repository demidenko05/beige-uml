package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.StateInvContin;
import org.beigesoft.uml.service.graphic.SrvGraphicStateInvContin;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeUmlWithEditor;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlStateInvContin;

public class FactoryAsmStateInvContin implements IFactoryAsmElementUml<IAsmElementUmlInteractive<StateInvContin, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, StateInvContin> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlStateInvContin<StateInvContin> srvPersistXmlStateInvContin;
  
  private final SrvGraphicStateInvContin<StateInvContin, CanvasWithPaint, SettingsDraw> srvGraphicStateInvContin;
  
  private final SrvInteractiveShapeUmlWithEditor<StateInvContin, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveStateInvContin;
  
  private final FactoryEditorStateInvContin factoryEditorStateInvContin;
  
  public FactoryAsmStateInvContin(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicStateInvContin = new SrvGraphicStateInvContin<StateInvContin, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlStateInvContin = new SrvPersistLightXmlStateInvContin<StateInvContin>();
    factoryEditorStateInvContin = new FactoryEditorStateInvContin(activity, containerGui);
    srvInteractiveStateInvContin = new SrvInteractiveShapeUmlWithEditor<StateInvContin, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicStateInvContin, factoryEditorStateInvContin);
  }

  @Override
  public IAsmElementUmlInteractive<StateInvContin, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    StateInvContin asmLifelineFull = new StateInvContin();
    AsmElementUmlInteractive<StateInvContin, CanvasWithPaint, SettingsDraw, FileAndWriter> asmStateInvContinUml = 
        new AsmElementUmlInteractive<StateInvContin, CanvasWithPaint, SettingsDraw, FileAndWriter>(asmLifelineFull,
            drawSettings, srvGraphicStateInvContin, srvPersistXmlStateInvContin, srvInteractiveStateInvContin);
    return asmStateInvContinUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorStateInvContin getFactoryEditorStateInvContin() {
    return factoryEditorStateInvContin;
  }

  public SrvPersistLightXmlStateInvContin<StateInvContin> getSrvPersistXmlStateInvContin() {
    return srvPersistXmlStateInvContin;
  }

  public SrvGraphicStateInvContin<StateInvContin, CanvasWithPaint, SettingsDraw> getSrvGraphicStateInvContin() {
    return srvGraphicStateInvContin;
  }

  public SrvInteractiveShapeUmlWithEditor<StateInvContin, CanvasWithPaint, SettingsDraw, Activity> getSrvInteractiveStateInvContin() {
    return srvInteractiveStateInvContin;
  }
}
