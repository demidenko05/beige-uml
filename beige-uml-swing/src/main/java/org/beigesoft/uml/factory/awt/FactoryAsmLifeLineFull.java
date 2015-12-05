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
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorLifeLineFull;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.service.graphic.SrvGraphicLifeLineFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveLifeLineFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlLifeLineFull;

public class FactoryAsmLifeLineFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, LifeLineFull<ShapeUmlWithName>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorLifeLineFull factoryEditorLifeLineUmlFull;

  private final SrvGraphicLifeLineFull<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw> srvGraphicInstanceUmlFull;

  private final SrvPersistLightXmlLifeLineFull<LifeLineFull<ShapeUmlWithName>> srvPersistInstanceUmlFull;

  private final SrvInteractiveLifeLineFull<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw, Frame> srvInteractiveLifeLineFull;

  public FactoryAsmLifeLineFull(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorLifeLineUmlFull = new FactoryEditorLifeLineFull(srvI18n, srvDialog, settingsGraphic, frameMain);
    srvPersistInstanceUmlFull = new SrvPersistLightXmlLifeLineFull<LifeLineFull<ShapeUmlWithName>>();
    srvGraphicInstanceUmlFull = new SrvGraphicLifeLineFull<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvInteractiveLifeLineFull = new SrvInteractiveLifeLineFull<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw, Frame>(srvGraphicInstanceUmlFull, factoryEditorLifeLineUmlFull);
  }

  @Override
  public IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    LifeLineFull<ShapeUmlWithName> useCaseUmlFull = new LifeLineFull<ShapeUmlWithName>(new ShapeUmlWithName());
    AsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw, FileAndWriter> asmInstance = 
        new AsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw, FileAndWriter>(useCaseUmlFull, 
            drawSettings, srvGraphicInstanceUmlFull, srvPersistInstanceUmlFull, srvInteractiveLifeLineFull);
    return asmInstance;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorLifeLineFull getFactoryEditorLifeLineUmlFull() {
    return factoryEditorLifeLineUmlFull;
  }

  public SrvGraphicLifeLineFull<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw> getSrvGraphicInstanceUmlFull() {
    return srvGraphicInstanceUmlFull;
  }

  public SrvPersistLightXmlLifeLineFull<LifeLineFull<ShapeUmlWithName>> getSrvPersistInstanceUmlFull() {
    return srvPersistInstanceUmlFull;
  }

  public SrvInteractiveLifeLineFull<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw, Frame> getSrvInteractiveLifeLineFull() {
    return srvInteractiveLifeLineFull;
  }
}
