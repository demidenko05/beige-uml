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
import org.beigesoft.uml.factory.swing.FactoryEditorUseCaseExtendedFull;
import org.beigesoft.uml.pojo.UseCaseExtended;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.graphic.SrvGraphicUseCaseExtended;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeVariousFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveUseCase;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlShapeFull;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlUseCaseExtended;

public class FactoryAsmUseCaseExtendedFullLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, ShapeFullVarious<UseCaseExtended>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorUseCaseExtendedFull factoryEditorUseCaseUmlFull;

  private final SrvGraphicShapeFull<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, UseCaseExtended> srvGraphicUseCaseUmlFull;

  private final SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCaseExtended>, UseCaseExtended> srvPersistUseCaseUmlFull;

  private final SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, Frame, UseCaseExtended> srvInteractiveUseCaseUmlFull;

  public FactoryAsmUseCaseExtendedFullLight(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorUseCaseUmlFull = new FactoryEditorUseCaseExtendedFull(srvI18n, srvDialog, settingsGraphic, frameMain);
    SrvPersistLightXmlUseCaseExtended<UseCaseExtended> srvPersistUseCaseUml = new SrvPersistLightXmlUseCaseExtended<UseCaseExtended>();
    srvPersistUseCaseUmlFull = new SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCaseExtended>, UseCaseExtended>(srvPersistUseCaseUml);
    SrvGraphicUseCaseExtended<UseCaseExtended, Graphics2D, SettingsDraw> srvGraphicUseCaseUml = new SrvGraphicUseCaseExtended<UseCaseExtended, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvGraphicUseCaseUmlFull = new SrvGraphicShapeFull<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, UseCaseExtended>(srvGraphicUseCaseUml);
    SrvInteractiveUseCase<UseCaseExtended, Graphics2D, SettingsDraw> srvInteractiveUseCaseUml = new SrvInteractiveUseCase<UseCaseExtended, Graphics2D, SettingsDraw>(srvGraphicUseCaseUml);
    srvInteractiveUseCaseUmlFull = new SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, Frame, UseCaseExtended>(factoryEditorUseCaseUmlFull, srvInteractiveUseCaseUml);
  }

  @Override
  public IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    UseCaseExtended useCaseUml = new UseCaseExtended();
    ShapeFullVarious<UseCaseExtended> useCaseUmlFull = new ShapeFullVarious<UseCaseExtended>();
    useCaseUmlFull.setShape(useCaseUml);
    AsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, FileAndWriter> asmUseCase = 
        new AsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, FileAndWriter>(useCaseUmlFull, 
            drawSettings, srvGraphicUseCaseUmlFull, srvPersistUseCaseUmlFull, srvInteractiveUseCaseUmlFull);
    return asmUseCase;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorUseCaseExtendedFull getFactoryEditorUseCaseUmlFull() {
    return factoryEditorUseCaseUmlFull;
  }

  public SrvGraphicShapeFull<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, UseCaseExtended> getSrvGraphicUseCaseUmlFull() {
    return srvGraphicUseCaseUmlFull;
  }

  public SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCaseExtended>, UseCaseExtended> getSrvPersistUseCaseUmlFull() {
    return srvPersistUseCaseUmlFull;
  }

  public SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, Frame, UseCaseExtended> getSrvInteractiveUseCaseUmlFull() {
    return srvInteractiveUseCaseUmlFull;
  }
}
