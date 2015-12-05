package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.service.graphic.SrvGraphicCoregionFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveCoregionFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlCoregionFull;

public class FactoryAsmCoregionFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<CoregionFull, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, CoregionFull> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlCoregionFull<CoregionFull> srvPersistXmlCoregionFull;
  
  private final SrvGraphicCoregionFull<CoregionFull, CanvasWithPaint, SettingsDraw> srvGraphicCoregionFull;
  
  private final SrvInteractiveCoregionFull<CoregionFull, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveCoregionFull;
  
  private final FactoryEditorCoregionFull factoryEditorCoregionFull;
  
  public FactoryAsmCoregionFull(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicCoregionFull = new SrvGraphicCoregionFull<CoregionFull, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlCoregionFull = new SrvPersistLightXmlCoregionFull<CoregionFull>();
    factoryEditorCoregionFull = new FactoryEditorCoregionFull(activity, containerGui);
    srvInteractiveCoregionFull = new SrvInteractiveCoregionFull<CoregionFull, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicCoregionFull, factoryEditorCoregionFull);
  }

  @Override
  public IAsmElementUmlInteractive<CoregionFull, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    CoregionFull commentUml = new CoregionFull();
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUmlInteractive<CoregionFull, CanvasWithPaint, SettingsDraw, FileAndWriter> asmCoregionFull = 
        new AsmElementUmlInteractive<CoregionFull, CanvasWithPaint, SettingsDraw, FileAndWriter>(commentUml,
            drawSettings, srvGraphicCoregionFull, srvPersistXmlCoregionFull, srvInteractiveCoregionFull);
    return asmCoregionFull;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvPersistLightXmlCoregionFull<CoregionFull> getSrvPersistXmlCoregionFull() {
    return srvPersistXmlCoregionFull;
  }

  public SrvGraphicCoregionFull<CoregionFull, CanvasWithPaint, SettingsDraw> getSrvGraphicCoregionFull() {
    return srvGraphicCoregionFull;
  }

  public SrvInteractiveCoregionFull<CoregionFull, CanvasWithPaint, SettingsDraw, Activity> getSrvInteractiveCoregionFull() {
    return srvInteractiveCoregionFull;
  }

  public FactoryEditorCoregionFull getFactoryEditorCoregionFull() {
    return factoryEditorCoregionFull;
  }
}
