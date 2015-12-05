package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.service.graphic.SrvGraphicLifeLineFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveLifeLineFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlLifeLineFull;

public class FactoryAsmLifeLineFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, LifeLineFull<ShapeUmlWithName>> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlLifeLineFull<LifeLineFull<ShapeUmlWithName>> srvPersistXmlLifeLineFull;
  
  private final SrvGraphicLifeLineFull<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw> srvGraphicLifeLineFull;
  
  private final SrvInteractiveLifeLineFull<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveLifeLineFull;
  
  private final FactoryEditorLifeLineFull factoryEditorLifeLineFull;
  
  public FactoryAsmLifeLineFull(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicLifeLineFull = new SrvGraphicLifeLineFull<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlLifeLineFull = new SrvPersistLightXmlLifeLineFull<LifeLineFull<ShapeUmlWithName>>();
    factoryEditorLifeLineFull = new FactoryEditorLifeLineFull(activity, containerGui);
    srvInteractiveLifeLineFull = new SrvInteractiveLifeLineFull<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicLifeLineFull, factoryEditorLifeLineFull);
  }

  @Override
  public IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    LifeLineFull<ShapeUmlWithName> asmLifelineFull = new LifeLineFull<ShapeUmlWithName>(new ShapeUmlWithName());
    AsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw, FileAndWriter> asmLifeLineFullUml = 
        new AsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw, FileAndWriter>(asmLifelineFull,
            drawSettings, srvGraphicLifeLineFull, srvPersistXmlLifeLineFull, srvInteractiveLifeLineFull);
    return asmLifeLineFullUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorLifeLineFull getFactoryEditorLifeLineFull() {
    return factoryEditorLifeLineFull;
  }

  public SrvPersistLightXmlLifeLineFull<LifeLineFull<ShapeUmlWithName>> getSrvPersistXmlLifeLineFull() {
    return srvPersistXmlLifeLineFull;
  }

  public SrvGraphicLifeLineFull<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw> getSrvGraphicLifeLineFull() {
    return srvGraphicLifeLineFull;
  }

  public SrvInteractiveLifeLineFull<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw, Activity> getSrvInteractiveLifeLineFull() {
    return srvInteractiveLifeLineFull;
  }
}
