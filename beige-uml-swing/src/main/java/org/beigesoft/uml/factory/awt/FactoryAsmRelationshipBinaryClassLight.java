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
import org.beigesoft.uml.factory.swing.FactoryEditorRelationshipBinaryClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipBinary;
import org.beigesoft.uml.service.interactive.SrvInteractiveRelationshipBinaryClass;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRelationshipBinaryClass;

public class FactoryAsmRelationshipBinaryClassLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorRelationshipBinaryClass factoryEditorRelationship;

  private final SrvGraphicRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw> srvGraphicRelationship;

  private final SrvPersistLightXmlRelationshipBinaryClass srvPersistRelationship;

  private final SrvInteractiveRelationshipBinaryClass<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ClassUml> srvInteractiveRelationship;

  public FactoryAsmRelationshipBinaryClassLight(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorRelationship = new FactoryEditorRelationshipBinaryClass(i18nSrv, dialogSrv, settingsGraphic, frameMain);
    srvPersistRelationship = new SrvPersistLightXmlRelationshipBinaryClass();
    srvGraphicRelationship = new SrvGraphicRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw> (srvDraw, settingsGraphic);
    srvInteractiveRelationship = new SrvInteractiveRelationshipBinaryClass<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ClassUml> (factoryEditorRelationship, settingsGraphic, srvGraphicRelationship);
  }

  @Override
  public IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> relBinary = new RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>();
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> classRelStart = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
    relBinary.setShapeRelationshipStart(classRelStart);
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> classRelEnd = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
    relBinary.setShapeRelationshipEnd(classRelEnd);
    AsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> asmRelBinClassFull = 
        new AsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>(relBinary, 
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

  public FactoryEditorRelationshipBinaryClass getFactoryEditorRelationship() {
    return factoryEditorRelationship;
  }

  public SrvGraphicRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw> getSrvGraphicRelationship() {
    return srvGraphicRelationship;
  }

  public SrvPersistLightXmlRelationshipBinaryClass getSrvPersistRelationship() {
    return srvPersistRelationship;
  }

  public SrvInteractiveRelationshipBinaryClass<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ClassUml> getSrvInteractiveRelationship() {
    return srvInteractiveRelationship;
  }
}
