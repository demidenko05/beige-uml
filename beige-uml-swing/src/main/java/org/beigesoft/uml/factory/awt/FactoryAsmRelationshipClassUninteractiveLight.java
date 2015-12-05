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
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipBinaryRectangle;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRelationshipBinaryClass;

public class FactoryAsmRelationshipClassUninteractiveLight implements IFactoryAsmElementUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> {

  private final SrvPersistLightXmlRelationshipBinaryClass srvPersistXmlRelationshipBinaryClass;

  private final SrvGraphicRelationshipBinaryRectangle<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw> srvGraphicRelationshipBinaryClass;

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  public FactoryAsmRelationshipClassUninteractiveLight(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw, 
      SettingsGraphicUml settingsGraphic) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    srvPersistXmlRelationshipBinaryClass = new SrvPersistLightXmlRelationshipBinaryClass();
    srvGraphicRelationshipBinaryClass = new SrvGraphicRelationshipBinaryRectangle<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw> (srvDraw, settingsGraphic);
  }


  @Override
  public AsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> 
      createAsmElementUml() {
    RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> relUml = new RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>();
    relUml.setShapeRelationshipStart(new RectangleRelationship<ClassFull<ClassUml>, ClassUml>());
    relUml.setShapeRelationshipEnd(new RectangleRelationship<ClassFull<ClassUml>, ClassUml>());
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> asmRelUml = 
        new AsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>
      (relUml, drawSettings , srvGraphicRelationshipBinaryClass, srvPersistXmlRelationshipBinaryClass);
    return asmRelUml;
  }
  
  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvPersistLightXmlRelationshipBinaryClass getSrvPersistXmlRelationshipBinaryClass() {
    return srvPersistXmlRelationshipBinaryClass;
  }


  public SrvGraphicRelationshipBinaryRectangle<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw> getSrvGraphicRelationshipBinaryClass() {
    return srvGraphicRelationshipBinaryClass;
  }
}
