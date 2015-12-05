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
import org.beigesoft.uml.pojo.PackageUml;
import org.beigesoft.uml.service.graphic.SrvGraphicPackage;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveFragment;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeVariousFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlPackage;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlShapeFull;

public class FactoryAsmPackageFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, ShapeFullVarious<PackageUml>> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlShapeFull<ShapeFullVarious<PackageUml>, PackageUml> srvPersistXmlPackageFull;
  
  private final SrvGraphicShapeFull<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, PackageUml> srvGraphicPackageFull;
  
  private final SrvInteractiveShapeVariousFull<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, Activity, PackageUml> srvInteractivePackageFull;
  
  private final FactoryEditorPackageFull factoryEditorPackageFull;
  
  public FactoryAsmPackageFull(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    SrvGraphicPackage<PackageUml, CanvasWithPaint, SettingsDraw> srvGraphicPackage = new SrvGraphicPackage<PackageUml, CanvasWithPaint, SettingsDraw>(getSrvDraw(), getSettingsGraphic());
    srvGraphicPackageFull = new SrvGraphicShapeFull<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, PackageUml>(srvGraphicPackage);
    SrvPersistLightXmlPackage<PackageUml> srvPersistXmlPackageUml = new SrvPersistLightXmlPackage<PackageUml>();
    srvPersistXmlPackageFull = new SrvPersistLightXmlShapeFull<ShapeFullVarious<PackageUml>, PackageUml>(srvPersistXmlPackageUml);
    factoryEditorPackageFull = new FactoryEditorPackageFull(activity, containerGui);
    SrvInteractiveFragment<PackageUml, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveClass = new SrvInteractiveFragment<PackageUml, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicPackage, null);
    srvInteractivePackageFull = new SrvInteractiveShapeVariousFull<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, Activity, PackageUml>(factoryEditorPackageFull, srvInteractiveClass);
  }

  @Override
  public IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    PackageUml actorUml = new PackageUml();
    ShapeFullVarious<PackageUml> actorUmlFull = new ShapeFullVarious<PackageUml>();
    actorUmlFull.setShape(actorUml);
    AsmElementUmlInteractive<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> asmPackageFullUml = 
        new AsmElementUmlInteractive<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>(actorUmlFull,
            drawSettings, srvGraphicPackageFull, srvPersistXmlPackageFull, srvInteractivePackageFull);
    return asmPackageFullUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorPackageFull getFactoryEditorPackageFull() {
    return factoryEditorPackageFull;
  }

  public SrvPersistLightXmlShapeFull<ShapeFullVarious<PackageUml>, PackageUml> getSrvPersistXmlPackageFull() {
    return srvPersistXmlPackageFull;
  }

  public SrvGraphicShapeFull<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, PackageUml> getSrvGraphicPackageFull() {
    return srvGraphicPackageFull;
  }

  public SrvInteractiveShapeVariousFull<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, Activity, PackageUml> getSrvInteractivePackageFull() {
    return srvInteractivePackageFull;
  }
}
