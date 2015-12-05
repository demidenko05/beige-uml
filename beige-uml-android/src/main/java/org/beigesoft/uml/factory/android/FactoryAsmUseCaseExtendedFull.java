package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.UseCaseExtended;
import org.beigesoft.uml.service.graphic.SrvGraphicUseCaseExtended;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeUml;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeVariousFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlUseCaseExtended;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlShapeFull;

public class FactoryAsmUseCaseExtendedFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, ShapeFullVarious<UseCaseExtended>> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCaseExtended>, UseCaseExtended> srvPersistXmlUseCaseExtendedFull;
  
  private final SrvGraphicShapeFull<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, UseCaseExtended> srvGraphicUseCaseExtendedFull;
  
  private final SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, Activity, UseCaseExtended> srvInteractiveUseCaseExtendedFull;
  
  private final FactoryEditorUseCaseExtendedFull factoryEditorUseCaseExtendedFull;
  
  public FactoryAsmUseCaseExtendedFull(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    SrvGraphicUseCaseExtended<UseCaseExtended, CanvasWithPaint, SettingsDraw> srvGraphicUseCaseExtended = new SrvGraphicUseCaseExtended<UseCaseExtended, CanvasWithPaint, SettingsDraw>(getSrvDraw(), getSettingsGraphic());
    srvGraphicUseCaseExtendedFull = new SrvGraphicShapeFull<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, UseCaseExtended>(srvGraphicUseCaseExtended);
    SrvPersistLightXmlUseCaseExtended<UseCaseExtended> srvPersistXmlUseCaseExtendedUml = new SrvPersistLightXmlUseCaseExtended<UseCaseExtended>();
    srvPersistXmlUseCaseExtendedFull = new SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCaseExtended>, UseCaseExtended>(srvPersistXmlUseCaseExtendedUml);
    factoryEditorUseCaseExtendedFull = new FactoryEditorUseCaseExtendedFull(activity, containerGui);
    SrvInteractiveShapeUml<UseCaseExtended, CanvasWithPaint, SettingsDraw> srvInteractiveClass = new SrvInteractiveShapeUml<UseCaseExtended, CanvasWithPaint, SettingsDraw>(srvGraphicUseCaseExtended);
    srvInteractiveUseCaseExtendedFull = new SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, Activity, UseCaseExtended>(factoryEditorUseCaseExtendedFull, srvInteractiveClass);
  }

  @Override
  public IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    UseCaseExtended useCaseUml = new UseCaseExtended();
    ShapeFullVarious<UseCaseExtended> useCaseUmlFull = new ShapeFullVarious<UseCaseExtended>();
    useCaseUmlFull.setShape(useCaseUml);
    AsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, FileAndWriter> asmUseCaseExtendedFullUml = 
        new AsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, FileAndWriter>(useCaseUmlFull,
            drawSettings, srvGraphicUseCaseExtendedFull, srvPersistXmlUseCaseExtendedFull, srvInteractiveUseCaseExtendedFull);
    return asmUseCaseExtendedFullUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorUseCaseExtendedFull getFactoryEditorUseCaseExtendedFull() {
    return factoryEditorUseCaseExtendedFull;
  }

  public SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCaseExtended>, UseCaseExtended> getSrvPersistXmlUseCaseExtendedFull() {
    return srvPersistXmlUseCaseExtendedFull;
  }

  public SrvGraphicShapeFull<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, UseCaseExtended> getSrvGraphicUseCaseExtendedFull() {
    return srvGraphicUseCaseExtendedFull;
  }

  public SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, Activity, UseCaseExtended> getSrvInteractiveUseCaseExtendedFull() {
    return srvInteractiveUseCaseExtendedFull;
  }
}
