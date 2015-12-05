package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.Joint2D;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorRelationshipPolyClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipPolyRectangle;
import org.beigesoft.uml.service.interactive.SrvInteractiveRelationshipPoly;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRelationshipPolyClass;

public class FactoryAsmRelationshipPolyClassLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorRelationshipPolyClass factoryEditorRelationship;

  private final SrvGraphicRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvGraphicRelationship;

  private final SrvPersistLightXmlRelationshipPolyClass srvPersistRelationship;

  private final SrvInteractiveRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ClassUml> srvInteractiveRelationship;


  public FactoryAsmRelationshipPolyClassLight(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorRelationship = new FactoryEditorRelationshipPolyClass(i18nSrv, dialogSrv, settingsGraphic, frameMain);
    srvPersistRelationship = new SrvPersistLightXmlRelationshipPolyClass();
    srvGraphicRelationship = new SrvGraphicRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> (srvDraw, settingsGraphic);
    srvInteractiveRelationship = new SrvInteractiveRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ClassUml> (factoryEditorRelationship, settingsGraphic, srvGraphicRelationship);
  }

  @Override
  public IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> rel = new RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>();
    rel.setSharedJoint(new Joint2D());
    AsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> asmRelClassFull = 
        new AsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>(rel, 
            drawSettings, srvGraphicRelationship, srvPersistRelationship, srvInteractiveRelationship);
    return asmRelClassFull;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorRelationshipPolyClass getFactoryEditorRelationship() {
    return factoryEditorRelationship;
  }

  public SrvGraphicRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvGraphicRelationship() {
    return srvGraphicRelationship;
  }

  public SrvPersistLightXmlRelationshipPolyClass getSrvPersistRelationship() {
    return srvPersistRelationship;
  }

  public SrvInteractiveRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ClassUml> getSrvInteractiveRelationship() {
    return srvInteractiveRelationship;
  }
}
