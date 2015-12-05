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
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorRelationshipSelfClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipSelf;
import org.beigesoft.uml.service.interactive.SrvInteractiveRelationshipSelf;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRelationshipSelfClass;

public class FactoryAsmRelationshipSelfClassLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorRelationshipSelfClass factoryEditorRelationship;

  private final SrvGraphicRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, ClassFull<ClassUml>, ClassUml> srvGraphicRelationship;

  private final SrvPersistLightXmlRelationshipSelfClass srvPersistRelationship;

  private final SrvInteractiveRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvInteractiveRelationship;

  public FactoryAsmRelationshipSelfClassLight(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorRelationship = new FactoryEditorRelationshipSelfClass(i18nSrv, dialogSrv, settingsGraphic, frameMain);
    srvPersistRelationship = new SrvPersistLightXmlRelationshipSelfClass();
    srvGraphicRelationship = new SrvGraphicRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, ClassFull<ClassUml>, ClassUml> (srvDraw, settingsGraphic);
    srvInteractiveRelationship = new SrvInteractiveRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> (factoryEditorRelationship, settingsGraphic, srvGraphicRelationship);
  }

  @Override
  public IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> relBinary = new RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>();
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> classRelStart = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
    relBinary.setShapeRelationshipStart(classRelStart);
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> classRelEnd = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
    relBinary.setShapeRelationshipEnd(classRelEnd);
    AsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> asmRelBinClassFull = 
        new AsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>(relBinary, 
            drawSettings, srvGraphicRelationship, srvPersistRelationship, srvInteractiveRelationship);
    return asmRelBinClassFull;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorRelationshipSelfClass getFactoryEditorRelationship() {
    return factoryEditorRelationship;
  }

  public SrvGraphicRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, ClassFull<ClassUml>, ClassUml> getSrvGraphicRelationship() {
    return srvGraphicRelationship;
  }

  public SrvPersistLightXmlRelationshipSelfClass getSrvPersistRelationship() {
    return srvPersistRelationship;
  }

  public SrvInteractiveRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvInteractiveRelationship() {
    return srvInteractiveRelationship;
  }
}
