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
import org.beigesoft.uml.factory.swing.FactoryEditorInstanceFull;
import org.beigesoft.uml.model.InstanceUml;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.graphic.SrvGraphicInstance;
import org.beigesoft.uml.service.interactive.SrvInteractiveInstance;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeVariousFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlShapeFull;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlInstance;

public class FactoryAsmInstanceFullLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, ShapeFullVarious<InstanceUml>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorInstanceFull factoryEditorInstanceUmlFull;

  private final SrvGraphicShapeFull<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, InstanceUml> srvGraphicInstanceUmlFull;

  private final SrvPersistLightXmlShapeFull<ShapeFullVarious<InstanceUml>, InstanceUml> srvPersistInstanceUmlFull;

  private final SrvInteractiveShapeVariousFull<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, Frame, InstanceUml> srvInteractiveInstanceUmlFull;

  public FactoryAsmInstanceFullLight(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorInstanceUmlFull = new FactoryEditorInstanceFull(srvI18n, srvDialog, settingsGraphic, frameMain);
    SrvPersistLightXmlInstance<InstanceUml> srvPersistInstanceUml = new SrvPersistLightXmlInstance<InstanceUml>();
    srvPersistInstanceUmlFull = new SrvPersistLightXmlShapeFull<ShapeFullVarious<InstanceUml>, InstanceUml>(srvPersistInstanceUml);
    SrvGraphicInstance<InstanceUml, Graphics2D, SettingsDraw> srvGraphicInstanceUml = new SrvGraphicInstance<InstanceUml, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvGraphicInstanceUmlFull = new SrvGraphicShapeFull<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, InstanceUml>(srvGraphicInstanceUml);
    SrvInteractiveInstance<InstanceUml, Graphics2D, SettingsDraw, Frame> srvInteractiveInstanceUml = new SrvInteractiveInstance<InstanceUml, Graphics2D, SettingsDraw, Frame>(srvGraphicInstanceUml, null);
    srvInteractiveInstanceUmlFull = new SrvInteractiveShapeVariousFull<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, Frame, InstanceUml>(factoryEditorInstanceUmlFull, srvInteractiveInstanceUml);
  }

  @Override
  public IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    InstanceUml useCaseUml = new InstanceUml();
    ShapeFullVarious<InstanceUml> useCaseUmlFull = new ShapeFullVarious<InstanceUml>();
    useCaseUmlFull.setShape(useCaseUml);
    AsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, FileAndWriter> asmInstance = 
        new AsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, FileAndWriter>(useCaseUmlFull, 
            drawSettings, srvGraphicInstanceUmlFull, srvPersistInstanceUmlFull, srvInteractiveInstanceUmlFull);
    return asmInstance;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorInstanceFull getFactoryEditorInstanceUmlFull() {
    return factoryEditorInstanceUmlFull;
  }

  public SrvGraphicShapeFull<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, InstanceUml> getSrvGraphicInstanceUmlFull() {
    return srvGraphicInstanceUmlFull;
  }

  public SrvPersistLightXmlShapeFull<ShapeFullVarious<InstanceUml>, InstanceUml> getSrvPersistInstanceUmlFull() {
    return srvPersistInstanceUmlFull;
  }

  public SrvInteractiveShapeVariousFull<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, Frame, InstanceUml> getSrvInteractiveInstanceUmlFull() {
    return srvInteractiveInstanceUmlFull;
  }
}
