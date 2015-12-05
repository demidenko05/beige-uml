package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.Joint2D;
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
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipPoly;
import org.beigesoft.uml.service.interactive.SrvInteractiveRelationshipPoly;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRelationshipPolyClass;

public class FactoryAsmRelationshipPolyClass implements IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlRelationshipPolyClass srvPersistXmlRelationshipPolyClass;
  
  private final SrvGraphicRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  CanvasWithPaint, SettingsDraw, 
  RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvGraphicRelationshipPolyClass;
  
  private final SrvInteractiveRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  Activity,
  ClassUml> srvInteractiveRelationshipPolyClass;
  
  private final FactoryEditorRelationshipPolyClass factoryEditorRelationshipPolyClass;
  
  public FactoryAsmRelationshipPolyClass(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicRelationshipPolyClass = new SrvGraphicRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
        CanvasWithPaint, SettingsDraw, 
        RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(srvDraw, settingsGraphic);
    srvPersistXmlRelationshipPolyClass = new SrvPersistLightXmlRelationshipPolyClass();
    factoryEditorRelationshipPolyClass = new FactoryEditorRelationshipPolyClass(activity, containerGui);
    srvInteractiveRelationshipPolyClass = new SrvInteractiveRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
        Activity, ClassUml>(factoryEditorRelationshipPolyClass, settingsGraphic, srvGraphicRelationshipPolyClass);
  }

  @Override
  public IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> rel = new RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>();
    rel.setSharedJoint(new Joint2D());
    AsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter> asmRelationshipPoly = 
        new AsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>(rel,
            drawSettings, srvGraphicRelationshipPolyClass, srvPersistXmlRelationshipPolyClass, srvInteractiveRelationshipPolyClass);
    return asmRelationshipPoly;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvPersistLightXmlRelationshipPolyClass getSrvPersistXmlRelationshipPolyClass() {
    return srvPersistXmlRelationshipPolyClass;
  }

  public FactoryEditorRelationshipPolyClass getFactoryEditorRelationshipPolyClass() {
    return factoryEditorRelationshipPolyClass;
  }

  public SrvGraphicRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  CanvasWithPaint, SettingsDraw, 
  RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvGraphicRelationshipPolyClass() {
    return srvGraphicRelationshipPolyClass;
  }

  public SrvInteractiveRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, ClassUml> getSrvInteractiveRelationshipPolyClass() {
    return srvInteractiveRelationshipPolyClass;
  }
}
