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
import org.beigesoft.uml.model.InstanceUml;
import org.beigesoft.uml.service.graphic.SrvGraphicInstance;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeUml;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeVariousFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlInstance;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlShapeFull;

public class FactoryAsmInstanceFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, ShapeFullVarious<InstanceUml>> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlShapeFull<ShapeFullVarious<InstanceUml>, InstanceUml> srvPersistXmlInstanceFull;
  
  private final SrvGraphicShapeFull<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, InstanceUml> srvGraphicInstanceFull;
  
  private final SrvInteractiveShapeVariousFull<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, Activity, InstanceUml> srvInteractiveInstanceFull;
  
  private final FactoryEditorInstanceFull factoryEditorInstanceFull;
  
  public FactoryAsmInstanceFull(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    SrvGraphicInstance<InstanceUml, CanvasWithPaint, SettingsDraw> srvGraphicInstance = new SrvGraphicInstance<InstanceUml, CanvasWithPaint, SettingsDraw>(getSrvDraw(), getSettingsGraphic());
    srvGraphicInstanceFull = new SrvGraphicShapeFull<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, InstanceUml>(srvGraphicInstance);
    SrvPersistLightXmlInstance<InstanceUml> srvPersistXmlInstanceUml = new SrvPersistLightXmlInstance<InstanceUml>();
    srvPersistXmlInstanceFull = new SrvPersistLightXmlShapeFull<ShapeFullVarious<InstanceUml>, InstanceUml>(srvPersistXmlInstanceUml);
    factoryEditorInstanceFull = new FactoryEditorInstanceFull(activity, containerGui);
    SrvInteractiveShapeUml<InstanceUml, CanvasWithPaint, SettingsDraw> srvInteractiveClass = new SrvInteractiveShapeUml<InstanceUml, CanvasWithPaint, SettingsDraw>(srvGraphicInstance);
    srvInteractiveInstanceFull = new SrvInteractiveShapeVariousFull<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, Activity, InstanceUml>(factoryEditorInstanceFull, srvInteractiveClass);
  }

  @Override
  public IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    InstanceUml useCaseUml = new InstanceUml();
    ShapeFullVarious<InstanceUml> useCaseUmlFull = new ShapeFullVarious<InstanceUml>();
    useCaseUmlFull.setShape(useCaseUml);
    AsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> asmInstanceFullUml = 
        new AsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>(useCaseUmlFull,
            drawSettings, srvGraphicInstanceFull, srvPersistXmlInstanceFull, srvInteractiveInstanceFull);
    return asmInstanceFullUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorInstanceFull getFactoryEditorInstanceFull() {
    return factoryEditorInstanceFull;
  }

  public SrvPersistLightXmlShapeFull<ShapeFullVarious<InstanceUml>, InstanceUml> getSrvPersistXmlInstanceFull() {
    return srvPersistXmlInstanceFull;
  }

  public SrvGraphicShapeFull<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, InstanceUml> getSrvGraphicInstanceFull() {
    return srvGraphicInstanceFull;
  }

  public SrvInteractiveShapeVariousFull<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, Activity, InstanceUml> getSrvInteractiveInstanceFull() {
    return srvInteractiveInstanceFull;
  }
}
