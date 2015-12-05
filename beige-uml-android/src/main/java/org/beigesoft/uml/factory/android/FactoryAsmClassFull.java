package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.service.graphic.SrvGraphicClass;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveClassFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeUml;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlClassFull;

public class FactoryAsmClassFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, ClassFull<ClassUml>> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlClassFull<ClassFull<ClassUml>, ClassUml> srvPersistXmlClassFull;
  
  private final SrvGraphicClass<ClassUml, CanvasWithPaint, SettingsDraw> srvGraphicClass;
  
  private final SrvGraphicShapeFull<ClassFull<ClassUml>, CanvasWithPaint, SettingsDraw, ClassUml> srvGraphicClassFull;
  
  private final SrvInteractiveClassFull<CanvasWithPaint, SettingsDraw, Activity, ClassUml> srvInteractiveClassFull;
  
  private final FactoryEditorClassFull factoryEditorClassFull;
  
  public FactoryAsmClassFull(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicClass = new SrvGraphicClass<ClassUml, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvGraphicClassFull = new SrvGraphicShapeFull<ClassFull<ClassUml>, CanvasWithPaint, SettingsDraw, ClassUml>(srvGraphicClass);
    srvPersistXmlClassFull = new SrvPersistLightXmlClassFull<ClassFull<ClassUml>, ClassUml>();
    factoryEditorClassFull = new FactoryEditorClassFull(activity, containerGui);
    SrvInteractiveShapeUml<ClassUml, CanvasWithPaint, SettingsDraw> srvInteractiveClass = new SrvInteractiveShapeUml<ClassUml, CanvasWithPaint, SettingsDraw>(srvGraphicClass);
    srvInteractiveClassFull = new SrvInteractiveClassFull<CanvasWithPaint, SettingsDraw, Activity, ClassUml>(factoryEditorClassFull, srvInteractiveClass);
  }

  @Override
  public IAsmElementUmlInteractive<ClassFull<ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    ClassUml classUml = new ClassUml();
    ClassFull<ClassUml> classUmlFull = new ClassFull<ClassUml>(classUml);
    AsmElementUmlInteractive<ClassFull<ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> asmClassFullUml = 
        new AsmElementUmlInteractive<ClassFull<ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>(classUmlFull,
            drawSettings, srvGraphicClassFull, srvPersistXmlClassFull, srvInteractiveClassFull);
    return asmClassFullUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvPersistLightXmlClassFull<ClassFull<ClassUml>, ClassUml> getSrvPersistXmlClassFull() {
    return srvPersistXmlClassFull;
  }

  public SrvGraphicClass<ClassUml, CanvasWithPaint, SettingsDraw> getSrvGraphicClass() {
    return srvGraphicClass;
  }

  public SrvGraphicShapeFull<ClassFull<ClassUml>, CanvasWithPaint, SettingsDraw, ClassUml> getSrvGraphicClassFull() {
    return srvGraphicClassFull;
  }

  public SrvInteractiveClassFull<CanvasWithPaint, SettingsDraw, Activity, ClassUml> getSrvInteractiveClassFull() {
    return srvInteractiveClassFull;
  }

  public FactoryEditorClassFull getFactoryEditorClassFull() {
    return factoryEditorClassFull;
  }
}
