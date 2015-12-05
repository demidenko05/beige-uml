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
import org.beigesoft.uml.service.graphic.SrvGraphicClassFull;
import org.beigesoft.uml.service.graphic.SrvGraphicClass;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlClassFull;

public class FactoryAsmClassFullUninteractiveLight implements IFactoryAsmElementUml<IAsmElementUml<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, ClassFull<ClassUml>> {

  private SrvPersistLightXmlClassFull<ClassFull<ClassUml>, ClassUml> persistXmlClassUmlSrv;

  private SrvGraphicClassFull<ClassUml, Graphics2D, SettingsDraw> graphicClassUmlSrv;
  
  private final ISrvDraw<Graphics2D, SettingsDraw, Image> drawService;

  private final SettingsGraphicUml graphicSettings;

  public FactoryAsmClassFullUninteractiveLight(ISrvDraw<Graphics2D, SettingsDraw, Image> drawService,
      SettingsGraphicUml graphicSettings) {
    this.drawService = drawService;
    this.graphicSettings = graphicSettings;
  }

  @Override
  public synchronized AsmElementUml<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    ClassUml classUml = new ClassUml();
    ClassFull<ClassUml> classUmlFull = 
         new ClassFull<ClassUml>(); 
    classUmlFull.setShape(classUml);
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUml<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> asmClassUml = new AsmElementUml<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> 
          (classUmlFull, drawSettings, lazyGetGraphicClassUmlSrv(), lazyGetPersistXmlClassUmlSrv());
    return asmClassUml;
  }
  
  public synchronized SrvPersistLightXmlClassFull<ClassFull<ClassUml>, ClassUml> lazyGetPersistXmlClassUmlSrv() {
    if(persistXmlClassUmlSrv == null) {
       persistXmlClassUmlSrv = new SrvPersistLightXmlClassFull<ClassFull<ClassUml>, ClassUml>();
    }
    return persistXmlClassUmlSrv;
  }

  public synchronized SrvGraphicClassFull<ClassUml, Graphics2D, SettingsDraw>
      lazyGetGraphicClassUmlSrv() {
    if(graphicClassUmlSrv == null) {
      SrvGraphicClass<ClassUml, Graphics2D, SettingsDraw> grClSrv = 
          new SrvGraphicClass<ClassUml, Graphics2D, SettingsDraw>(getDrawService(), getGraphicSettings());
      graphicClassUmlSrv = new SrvGraphicClassFull<ClassUml, Graphics2D, SettingsDraw>(grClSrv);
    }
    return graphicClassUmlSrv;
  }

  public ISrvDraw<Graphics2D, SettingsDraw, Image> getDrawService() {
    return drawService;
  }

  public SettingsGraphicUml getGraphicSettings() {
    return graphicSettings;
  }
}
