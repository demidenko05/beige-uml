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
import org.beigesoft.uml.factory.swing.FactoryEditorClassFullInteractive;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.service.graphic.SrvGraphicClass;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveClassFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeUml;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlClassFull;

public class FactoryAsmClassFullLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter,
    ClassFull<ClassUml>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlClassFull<ClassFull<ClassUml>, ClassUml> srvPersistClassFull;

  private final SrvGraphicClass<ClassUml, Graphics2D, SettingsDraw> srvGraphicClass;
  
  private final SrvGraphicShapeFull<ClassFull<ClassUml>, Graphics2D, SettingsDraw, ClassUml> srvGraphicClassFull;
  
  private final SrvInteractiveShapeUml<ClassUml, Graphics2D, SettingsDraw> srvInteractiveClass;
  
  private final SrvInteractiveClassFull<Graphics2D, SettingsDraw, Frame, ClassUml> srvInteractiveClassFull;
  
  private final FactoryEditorClassFullInteractive factoryEditorClassFull;

  public FactoryAsmClassFullLight(ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv,
      ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    this.factoryEditorClassFull = 
      new FactoryEditorClassFullInteractive(i18nSrv, dialogSrv, settingsGraphic, frameMain);
    srvGraphicClass = new SrvGraphicClass<ClassUml, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvGraphicClassFull =
      new SrvGraphicShapeFull<ClassFull<ClassUml>, Graphics2D, SettingsDraw, ClassUml>(srvGraphicClass);
    srvInteractiveClass = 
      new SrvInteractiveShapeUml<ClassUml, Graphics2D, SettingsDraw>(srvGraphicClass);
    srvInteractiveClassFull = 
      new SrvInteractiveClassFull<Graphics2D, SettingsDraw, Frame, ClassUml> (factoryEditorClassFull, srvInteractiveClass);
    srvPersistClassFull = new SrvPersistLightXmlClassFull<ClassFull<ClassUml>, ClassUml>();
  }
  
  @Override
  public synchronized AsmElementUmlInteractive<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    ClassUml classUml = new ClassUml();
    ClassFull<ClassUml> classUmlFull = new ClassFull<ClassUml>(classUml);
    AsmElementUmlInteractive<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> asmClassFull = 
        new AsmElementUmlInteractive<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>
        (classUmlFull, drawSettings, srvGraphicClassFull, srvPersistClassFull, srvInteractiveClassFull);
    return asmClassFull;
  }
  
  //SGS:
  public FactoryEditorClassFullInteractive getFactoryEditorClassUml() {
    return factoryEditorClassFull;
  }

  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvPersistLightXmlClassFull<ClassFull<ClassUml>, ClassUml> getSrvPersistClassFull() {
    return srvPersistClassFull;
  }

  public SrvGraphicClass<ClassUml, Graphics2D, SettingsDraw> getSrvGraphicClass() {
    return srvGraphicClass;
  }

  public SrvGraphicShapeFull<ClassFull<ClassUml>, Graphics2D, SettingsDraw, ClassUml> getSrvGraphicClassFull() {
    return srvGraphicClassFull;
  }

  public SrvInteractiveShapeUml<ClassUml, Graphics2D, SettingsDraw> getSrvInteractiveClass() {
    return srvInteractiveClass;
  }

  public SrvInteractiveClassFull<Graphics2D, SettingsDraw, Frame, ClassUml> getSrvInteractiveClassFull() {
    return srvInteractiveClassFull;
  }

  public FactoryEditorClassFullInteractive getFactoryEditorClassFull() {
    return factoryEditorClassFull;
  }
}
