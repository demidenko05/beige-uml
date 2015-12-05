package org.beigesoft.uml.factory.awt;

import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipSelf;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRelationshipSelfClass;

public class FactoryAsmRelationshipSelfClassLightUninteractive implements IFactoryAsmElementUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvGraphicRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, ClassFull<ClassUml>, ClassUml> srvGraphicRelationship;

  private final SrvPersistLightXmlRelationshipSelfClass srvPersistRelationship;

  public FactoryAsmRelationshipSelfClassLightUninteractive(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      SettingsGraphicUml settingsGraphic) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    srvPersistRelationship = new SrvPersistLightXmlRelationshipSelfClass();
    srvGraphicRelationship = new SrvGraphicRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, ClassFull<ClassUml>, ClassUml> (srvDraw, settingsGraphic);
  }

  @Override
  public IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> relBinary = new RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>();
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> classRelStart = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
    relBinary.setShapeRelationshipStart(classRelStart);
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> classRelEnd = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
    relBinary.setShapeRelationshipEnd(classRelEnd);
    AsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> asmRelBinClassFull = 
        new AsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>(relBinary, 
            drawSettings, srvGraphicRelationship, srvPersistRelationship);
    return asmRelBinClassFull;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvGraphicRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, ClassFull<ClassUml>, ClassUml> getSrvGraphicRelationship() {
    return srvGraphicRelationship;
  }

  public SrvPersistLightXmlRelationshipSelfClass getSrvPersistRelationship() {
    return srvPersistRelationship;
  }
}
