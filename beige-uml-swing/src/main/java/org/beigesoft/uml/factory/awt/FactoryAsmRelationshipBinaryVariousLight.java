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
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorRelationshipShapeVarious;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipBinary;
import org.beigesoft.uml.service.interactive.SrvInteractiveRelationshipBinaryVarious;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRelationshipBinaryVarious;

public class FactoryAsmRelationshipBinaryVariousLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, RelationshipBinaryVarious> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorRelationshipShapeVarious factoryEditorRelationship;

  private final SrvGraphicRelationshipBinary<RelationshipBinaryVarious, Graphics2D, SettingsDraw> srvGraphicRelationship;

  private final SrvPersistLightXmlRelationshipBinaryVarious<RelationshipBinaryVarious> srvPersistRelationship;

  private final SrvInteractiveRelationshipBinaryVarious<RelationshipBinaryVarious, Frame> srvInteractiveRelationship;

  public FactoryAsmRelationshipBinaryVariousLight(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorRelationship = new FactoryEditorRelationshipShapeVarious(i18nSrv, dialogSrv, settingsGraphic, frameMain);
    srvPersistRelationship = new SrvPersistLightXmlRelationshipBinaryVarious<RelationshipBinaryVarious>();
    srvGraphicRelationship = new SrvGraphicRelationshipBinary<RelationshipBinaryVarious, Graphics2D, SettingsDraw> (srvDraw, settingsGraphic);
    srvInteractiveRelationship = new SrvInteractiveRelationshipBinaryVarious<RelationshipBinaryVarious, Frame> (factoryEditorRelationship, settingsGraphic, srvGraphicRelationship);
  }

  @Override
  public IAsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RelationshipBinaryVarious relActCaseUml = new RelationshipBinaryVarious();
    AsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter> asmUseCase = 
        new AsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter>(relActCaseUml, 
            drawSettings, srvGraphicRelationship, srvPersistRelationship, srvInteractiveRelationship);
    return asmUseCase;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorRelationshipShapeVarious getFactoryEditorRelationship() {
    return factoryEditorRelationship;
  }

  public SrvGraphicRelationshipBinary<RelationshipBinaryVarious, Graphics2D, SettingsDraw> getSrvGraphicRelationship() {
    return srvGraphicRelationship;
  }

  public SrvPersistLightXmlRelationshipBinaryVarious<RelationshipBinaryVarious> getSrvPersistRelationship() {
    return srvPersistRelationship;
  }

  public SrvInteractiveRelationshipBinaryVarious<RelationshipBinaryVarious, Frame> getSrvInteractiveRelationship() {
    return srvInteractiveRelationship;
  }
}
