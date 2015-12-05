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
import org.beigesoft.uml.pojo.UseCase;
import org.beigesoft.uml.service.graphic.SrvGraphicUseCase;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeUml;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeVariousFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlUseCase;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlShapeFull;

public class FactoryAsmUseCaseFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, ShapeFullVarious<UseCase>> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCase>, UseCase> srvPersistXmlUseCaseFull;
  
  private final SrvGraphicShapeFull<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, UseCase> srvGraphicUseCaseFull;
  
  private final SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, Activity, UseCase> srvInteractiveUseCaseFull;
  
  private final FactoryEditorUseCaseFull factoryEditorUseCaseFull;
  
  public FactoryAsmUseCaseFull(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    SrvGraphicUseCase<UseCase, CanvasWithPaint, SettingsDraw> srvGraphicUseCase = new SrvGraphicUseCase<UseCase, CanvasWithPaint, SettingsDraw>(getSrvDraw(), getSettingsGraphic());
    srvGraphicUseCaseFull = new SrvGraphicShapeFull<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, UseCase>(srvGraphicUseCase);
    SrvPersistLightXmlUseCase<UseCase> srvPersistXmlUseCaseUml = new SrvPersistLightXmlUseCase<UseCase>();
    srvPersistXmlUseCaseFull = new SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCase>, UseCase>(srvPersistXmlUseCaseUml);
    factoryEditorUseCaseFull = new FactoryEditorUseCaseFull(activity, containerGui);
    SrvInteractiveShapeUml<UseCase, CanvasWithPaint, SettingsDraw> srvInteractiveClass = new SrvInteractiveShapeUml<UseCase, CanvasWithPaint, SettingsDraw>(srvGraphicUseCase);
    srvInteractiveUseCaseFull = new SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, Activity, UseCase>(factoryEditorUseCaseFull, srvInteractiveClass);
  }

  @Override
  public IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    UseCase actorUml = new UseCase();
    ShapeFullVarious<UseCase> actorUmlFull = new ShapeFullVarious<UseCase>();
    actorUmlFull.setShape(actorUml);
    AsmElementUmlInteractive<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, FileAndWriter> asmUseCaseFullUml = 
        new AsmElementUmlInteractive<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, FileAndWriter>(actorUmlFull,
            drawSettings, srvGraphicUseCaseFull, srvPersistXmlUseCaseFull, srvInteractiveUseCaseFull);
    return asmUseCaseFullUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorUseCaseFull getFactoryEditorUseCaseFull() {
    return factoryEditorUseCaseFull;
  }

  public SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCase>, UseCase> getSrvPersistXmlUseCaseFull() {
    return srvPersistXmlUseCaseFull;
  }

  public SrvGraphicShapeFull<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, UseCase> getSrvGraphicUseCaseFull() {
    return srvGraphicUseCaseFull;
  }

  public SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, Activity, UseCase> getSrvInteractiveUseCaseFull() {
    return srvInteractiveUseCaseFull;
  }
}
