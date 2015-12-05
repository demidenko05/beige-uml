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
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipSelf;
import org.beigesoft.uml.service.interactive.SrvInteractiveRelationshipSelf;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRelationshipSelfClass;

public class FactoryAsmRelationshipSelfClass implements IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlRelationshipSelfClass srvPersistXmlRelationshipSelfClass;
  
  private final SrvGraphicRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, ClassFull<ClassUml>, ClassUml> srvGraphicRelationshipSelfClass;
  
  private final SrvInteractiveRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  Activity,
  RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvInteractiveRelationshipSelfClass;
  
  private final FactoryEditorRelationshipSelfClass factoryEditorRelationshipSelfClass;
  
  public FactoryAsmRelationshipSelfClass(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicRelationshipSelfClass = new SrvGraphicRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, ClassFull<ClassUml>, ClassUml>(srvDraw, settingsGraphic);
    srvPersistXmlRelationshipSelfClass = new SrvPersistLightXmlRelationshipSelfClass();
    factoryEditorRelationshipSelfClass = new FactoryEditorRelationshipSelfClass(activity, containerGui);
    srvInteractiveRelationshipSelfClass = new SrvInteractiveRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
        Activity,
        RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(factoryEditorRelationshipSelfClass, 
        settingsGraphic, srvGraphicRelationshipSelfClass);
  }

  @Override
  public IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> relSelf = new RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>();
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> classRelStart = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
    relSelf.setShapeRelationshipStart(classRelStart);
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> classRelEnd = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
    relSelf.setShapeRelationshipEnd(classRelEnd);
    AsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> asmRelationshipSelf = 
        new AsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>(relSelf,
            drawSettings, srvGraphicRelationshipSelfClass, srvPersistXmlRelationshipSelfClass, srvInteractiveRelationshipSelfClass);
    return asmRelationshipSelf;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvPersistLightXmlRelationshipSelfClass getSrvPersistXmlRelationshipSelfClass() {
    return srvPersistXmlRelationshipSelfClass;
  }

  public FactoryEditorRelationshipSelfClass getFactoryEditorRelationshipSelfClass() {
    return factoryEditorRelationshipSelfClass;
  }

  public SrvGraphicRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, ClassFull<ClassUml>, ClassUml> getSrvGraphicRelationshipSelfClass() {
    return srvGraphicRelationshipSelfClass;
  }

  public SrvInteractiveRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvInteractiveRelationshipSelfClass() {
    return srvInteractiveRelationshipSelfClass;
  }
}
