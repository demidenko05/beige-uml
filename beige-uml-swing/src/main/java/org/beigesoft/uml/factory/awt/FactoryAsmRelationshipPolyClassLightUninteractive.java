package org.beigesoft.uml.factory.awt;

import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.Joint2D;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipPolyRectangle;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRelationshipPolyClass;

public class FactoryAsmRelationshipPolyClassLightUninteractive implements IFactoryAsmElementUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvGraphicRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvGraphicRelationship;

  private final SrvPersistLightXmlRelationshipPolyClass srvPersistRelationship;

  public FactoryAsmRelationshipPolyClassLightUninteractive(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      SettingsGraphicUml settingsGraphic) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    srvPersistRelationship = new SrvPersistLightXmlRelationshipPolyClass();
    srvGraphicRelationship = new SrvGraphicRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> (srvDraw, settingsGraphic);
  }

  @Override
  public IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> rel = new RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>();
    rel.setSharedJoint(new Joint2D());
    AsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> asmRelClassFull = 
        new AsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>(rel, 
            drawSettings, srvGraphicRelationship, srvPersistRelationship);
    return asmRelClassFull;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvGraphicRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvGraphicRelationship() {
    return srvGraphicRelationship;
  }

  public SrvPersistLightXmlRelationshipPolyClass getSrvPersistRelationship() {
    return srvPersistRelationship;
  }
}
