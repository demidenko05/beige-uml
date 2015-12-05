package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorPackageFull;
import org.beigesoft.uml.pojo.PackageUml;
import org.beigesoft.uml.service.graphic.SrvGraphicPackage;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveFragment;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeVariousFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlPackage;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlShapeFull;

public class FactoryAsmPackageFullLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, ShapeFullVarious<PackageUml>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorPackageFull factoryEditorPackageUmlFull;

  private final SrvGraphicShapeFull<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, PackageUml> srvGraphicPackageUmlFull;

  private final SrvPersistLightXmlShapeFull<ShapeFullVarious<PackageUml>, PackageUml> srvPersistPackageUmlFull;

  private final SrvInteractiveShapeVariousFull<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, Frame, PackageUml> srvInteractivePackageUmlFull;

  public FactoryAsmPackageFullLight(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorPackageUmlFull = new FactoryEditorPackageFull(i18nSrv, dialogSrv, settingsGraphic, frameMain);
    SrvPersistLightXmlPackage<PackageUml> srvPersistXmlPackageUml = new SrvPersistLightXmlPackage<PackageUml>();
    srvPersistPackageUmlFull = new SrvPersistLightXmlShapeFull<ShapeFullVarious<PackageUml>, PackageUml>(srvPersistXmlPackageUml);
    SrvGraphicPackage<PackageUml, Graphics2D, SettingsDraw> srvGraphicPackageUml = new SrvGraphicPackage<PackageUml, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvGraphicPackageUmlFull = new SrvGraphicShapeFull<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, PackageUml>(srvGraphicPackageUml);
    SrvInteractiveFragment<PackageUml, Graphics2D, SettingsDraw, Frame> srvInteractivePackageUml = new SrvInteractiveFragment<PackageUml, Graphics2D, SettingsDraw, Frame>(srvGraphicPackageUml, null);
    srvInteractivePackageUmlFull = new SrvInteractiveShapeVariousFull<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, Frame, PackageUml>(factoryEditorPackageUmlFull, srvInteractivePackageUml);
  }

  @Override
  public IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    PackageUml actorUml = new PackageUml();
    ShapeFullVarious<PackageUml> actorUmlFull = new ShapeFullVarious<PackageUml>();
    actorUmlFull.setShape(actorUml);
    AsmElementUmlInteractive<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, FileAndWriter> asmPackageUmlFull = 
        new AsmElementUmlInteractive<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, FileAndWriter>(actorUmlFull, drawSettings, srvGraphicPackageUmlFull, srvPersistPackageUmlFull, srvInteractivePackageUmlFull);
    return asmPackageUmlFull;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorPackageFull getFactoryEditorPackageUmlFull() {
    return factoryEditorPackageUmlFull;
  }

  public SrvGraphicShapeFull<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, PackageUml> getSrvGraphicPackageUmlFull() {
    return srvGraphicPackageUmlFull;
  }

  public SrvPersistLightXmlShapeFull<ShapeFullVarious<PackageUml>, PackageUml> getSrvPersistPackageUmlFull() {
    return srvPersistPackageUmlFull;
  }

  public SrvInteractiveShapeVariousFull<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, Frame, PackageUml> getSrvInteractivePackageUmlFull() {
    return srvInteractivePackageUmlFull;
  }
}
