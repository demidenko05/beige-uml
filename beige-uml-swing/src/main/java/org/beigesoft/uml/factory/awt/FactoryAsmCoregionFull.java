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
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorCoregionFull;
import org.beigesoft.uml.service.graphic.SrvGraphicCoregionFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveCoregionFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlCoregionFull;

public class FactoryAsmCoregionFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<CoregionFull, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, CoregionFull> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorCoregionFull factoryEditorCoregionFull;

  private final SrvGraphicCoregionFull<CoregionFull, Graphics2D, SettingsDraw> srvGraphicCoregionFull;

  private final SrvPersistLightXmlCoregionFull<CoregionFull> srvPersistCoregionFull;

  private final SrvInteractiveCoregionFull<CoregionFull, Graphics2D, SettingsDraw, Frame> srvInteractiveCoregionFull;

  public FactoryAsmCoregionFull(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorCoregionFull = new FactoryEditorCoregionFull(srvI18n, srvDialog, settingsGraphic, frameMain);
    srvPersistCoregionFull = new SrvPersistLightXmlCoregionFull<CoregionFull>();
    srvGraphicCoregionFull = new SrvGraphicCoregionFull<CoregionFull, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvInteractiveCoregionFull = new SrvInteractiveCoregionFull<CoregionFull, Graphics2D, SettingsDraw, Frame>(srvGraphicCoregionFull, factoryEditorCoregionFull);
  }

  @Override
  public IAsmElementUmlInteractive<CoregionFull, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    CoregionFull messageFull = new CoregionFull();
    AsmElementUmlInteractive<CoregionFull, Graphics2D, SettingsDraw, FileAndWriter> asmInstance = 
        new AsmElementUmlInteractive<CoregionFull, Graphics2D, SettingsDraw, FileAndWriter>(messageFull, 
            drawSettings, srvGraphicCoregionFull, srvPersistCoregionFull, srvInteractiveCoregionFull);
    return asmInstance;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorCoregionFull getFactoryEditorCoregionFull() {
    return factoryEditorCoregionFull;
  }

  public SrvGraphicCoregionFull<CoregionFull, Graphics2D, SettingsDraw> getSrvGraphicCoregionFull() {
    return srvGraphicCoregionFull;
  }

  public SrvPersistLightXmlCoregionFull<CoregionFull> getSrvPersistCoregionFull() {
    return srvPersistCoregionFull;
  }

  public SrvInteractiveCoregionFull<CoregionFull, Graphics2D, SettingsDraw, Frame> getSrvInteractiveCoregionFull() {
    return srvInteractiveCoregionFull;
  }
}
