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
import org.beigesoft.uml.factory.swing.FactoryEditorUseCaseFull;
import org.beigesoft.uml.pojo.UseCase;
import org.beigesoft.uml.service.graphic.SrvGraphicUseCase;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeVariousFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveUseCase;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlShapeFull;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlUseCase;

public class FactoryAsmUseCaseFullLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, ShapeFullVarious<UseCase>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorUseCaseFull factoryEditorUseCaseUmlFull;

  private final SrvGraphicShapeFull<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, UseCase> srvGraphicUseCaseUmlFull;

  private final SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCase>, UseCase> srvPersistUseCaseUmlFull;

  private final SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, Frame, UseCase> srvInteractiveUseCaseUmlFull;

  public FactoryAsmUseCaseFullLight(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorUseCaseUmlFull = new FactoryEditorUseCaseFull(srvI18n, srvDialog, settingsGraphic, frameMain);
    SrvPersistLightXmlUseCase<UseCase> srvPersistUseCaseUml = new SrvPersistLightXmlUseCase<UseCase>();
    srvPersistUseCaseUmlFull = new SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCase>, UseCase>(srvPersistUseCaseUml);
    SrvGraphicUseCase<UseCase, Graphics2D, SettingsDraw> srvGraphicUseCaseUml = new SrvGraphicUseCase<UseCase, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvGraphicUseCaseUmlFull = new SrvGraphicShapeFull<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, UseCase>(srvGraphicUseCaseUml);
    SrvInteractiveUseCase<UseCase, Graphics2D, SettingsDraw> srvInteractiveUseCaseUml = new SrvInteractiveUseCase<UseCase, Graphics2D, SettingsDraw>(srvGraphicUseCaseUml);
    srvInteractiveUseCaseUmlFull = new SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, Frame, UseCase>(factoryEditorUseCaseUmlFull, srvInteractiveUseCaseUml);
  }

  @Override
  public IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    UseCase useCaseUml = new UseCase();
    ShapeFullVarious<UseCase> useCaseUmlFull = new ShapeFullVarious<UseCase>();
    useCaseUmlFull.setShape(useCaseUml);
    AsmElementUmlInteractive<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, FileAndWriter> asmUseCase = 
        new AsmElementUmlInteractive<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, FileAndWriter>(useCaseUmlFull, 
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

  public FactoryEditorUseCaseFull getFactoryEditorUseCaseUmlFull() {
    return factoryEditorUseCaseUmlFull;
  }

  public SrvGraphicShapeFull<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, UseCase> getSrvGraphicUseCaseUmlFull() {
    return srvGraphicUseCaseUmlFull;
  }

  public SrvPersistLightXmlShapeFull<ShapeFullVarious<UseCase>, UseCase> getSrvPersistUseCaseUmlFull() {
    return srvPersistUseCaseUmlFull;
  }

  public SrvInteractiveShapeVariousFull<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, Frame, UseCase> getSrvInteractiveUseCaseUmlFull() {
    return srvInteractiveUseCaseUmlFull;
  }
}
