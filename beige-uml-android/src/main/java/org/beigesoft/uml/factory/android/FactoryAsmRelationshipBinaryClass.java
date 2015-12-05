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
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipBinary;
import org.beigesoft.uml.service.interactive.SrvInteractiveRelationshipBinaryClass;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRelationshipBinaryClass;

public class FactoryAsmRelationshipBinaryClass implements IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlRelationshipBinaryClass srvPersistXmlRelationshipBinaryClass;
  
  private final SrvGraphicRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw> srvGraphicRelationshipBinaryClass;
  
  private final SrvInteractiveRelationshipBinaryClass<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, ClassUml> srvInteractiveRelationshipBinaryClass;
  
  private final FactoryEditorRelationshipBinaryClass factoryEditorRelationshipBinaryClass;
  
  public FactoryAsmRelationshipBinaryClass(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicRelationshipBinaryClass = new SrvGraphicRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlRelationshipBinaryClass = new SrvPersistLightXmlRelationshipBinaryClass();
    factoryEditorRelationshipBinaryClass = new FactoryEditorRelationshipBinaryClass(activity, containerGui);
    srvInteractiveRelationshipBinaryClass = new SrvInteractiveRelationshipBinaryClass<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, ClassUml>(factoryEditorRelationshipBinaryClass, 
        settingsGraphic, srvGraphicRelationshipBinaryClass);
  }

  @Override
  public IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> relBinary = new RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>();
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> classRelStart = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
    relBinary.setShapeRelationshipStart(classRelStart);
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> classRelEnd = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
    relBinary.setShapeRelationshipEnd(classRelEnd);
    AsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> asmRelationshipBinary = 
        new AsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>(relBinary,
            drawSettings, srvGraphicRelationshipBinaryClass, srvPersistXmlRelationshipBinaryClass, srvInteractiveRelationshipBinaryClass);
    return asmRelationshipBinary;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvPersistLightXmlRelationshipBinaryClass getSrvPersistXmlRelationshipBinaryClass() {
    return srvPersistXmlRelationshipBinaryClass;
  }

  public SrvGraphicRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw> getSrvGraphicRelationshipBinaryClass() {
    return srvGraphicRelationshipBinaryClass;
  }

  public SrvInteractiveRelationshipBinaryClass<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, ClassUml> getSrvInteractiveRelationshipBinaryClass() {
    return srvInteractiveRelationshipBinaryClass;
  }

  public FactoryEditorRelationshipBinaryClass getFactoryEditorRelationshipBinaryClass() {
    return factoryEditorRelationshipBinaryClass;
  }
}
