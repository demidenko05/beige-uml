package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.graphics.Bitmap;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.Actor;
import org.beigesoft.uml.service.graphic.SrvGraphicActor;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeUml;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeVariousFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlActor;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlShapeFull;

public class FactoryAsmActorFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, ShapeFullVarious<Actor>> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlShapeFull<ShapeFullVarious<Actor>, Actor> srvPersistXmlActorFull;
  
  private final SrvGraphicShapeFull<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, Actor> srvGraphicActorFull;
  
  private final SrvInteractiveShapeVariousFull<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, Activity, Actor> srvInteractiveActorFull;
  
  private final FactoryEditorActorFull factoryEditorActorFull;
  
  public FactoryAsmActorFull(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    SrvGraphicActor<Actor, CanvasWithPaint, SettingsDraw, Bitmap> srvGraphicActor = new SrvGraphicActor<Actor, CanvasWithPaint, SettingsDraw, Bitmap>(getSrvDraw(), getSettingsGraphic());
    srvGraphicActorFull = new SrvGraphicShapeFull<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, Actor>(srvGraphicActor);
    SrvPersistLightXmlActor<Actor> srvPersistXmlActorUml = new SrvPersistLightXmlActor<Actor>();
    srvPersistXmlActorFull = new SrvPersistLightXmlShapeFull<ShapeFullVarious<Actor>, Actor>(srvPersistXmlActorUml);
    factoryEditorActorFull = new FactoryEditorActorFull(activity, containerGui);
    SrvInteractiveShapeUml<Actor, CanvasWithPaint, SettingsDraw> srvInteractiveClass = new SrvInteractiveShapeUml<Actor, CanvasWithPaint, SettingsDraw>(srvGraphicActor);
    srvInteractiveActorFull = new SrvInteractiveShapeVariousFull<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, Activity, Actor>(factoryEditorActorFull, srvInteractiveClass);
  }

  @Override
  public IAsmElementUmlInteractive<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    Actor actorUml = new Actor();
    ShapeFullVarious<Actor> actorUmlFull = new ShapeFullVarious<Actor>();
    actorUmlFull.setShape(actorUml);
    AsmElementUmlInteractive<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, FileAndWriter> asmActorFullUml = 
        new AsmElementUmlInteractive<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, FileAndWriter>(actorUmlFull,
            drawSettings, srvGraphicActorFull, srvPersistXmlActorFull, srvInteractiveActorFull);
    return asmActorFullUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorActorFull getFactoryEditorActorFull() {
    return factoryEditorActorFull;
  }

  public SrvPersistLightXmlShapeFull<ShapeFullVarious<Actor>, Actor> getSrvPersistXmlActorFull() {
    return srvPersistXmlActorFull;
  }

  public SrvGraphicShapeFull<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, Actor> getSrvGraphicActorFull() {
    return srvGraphicActorFull;
  }

  public SrvInteractiveShapeVariousFull<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, Activity, Actor> getSrvInteractiveActorFull() {
    return srvInteractiveActorFull;
  }
}
