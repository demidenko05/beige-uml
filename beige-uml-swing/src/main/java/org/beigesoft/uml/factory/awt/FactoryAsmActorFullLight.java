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
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorActorFull;
import org.beigesoft.uml.pojo.Actor;
import org.beigesoft.uml.service.graphic.SrvGraphicActor;
import org.beigesoft.uml.service.graphic.SrvGraphicShapeFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveActor;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeVariousFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlActor;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlShapeFull;

public class FactoryAsmActorFullLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, ShapeFullVarious<Actor>> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorActorFull factoryEditorActorUmlFull;

  private final SrvGraphicShapeFull<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, Actor> srvGraphicActorUmlFull;

  private final SrvPersistLightXmlShapeFull<ShapeFullVarious<Actor>, Actor> srvPersistActorUmlFull;

  private final SrvInteractiveShapeVariousFull<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, Frame, Actor> srvInteractiveActorUmlFull;

  public FactoryAsmActorFullLight(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorActorUmlFull = new FactoryEditorActorFull(i18nSrv, dialogSrv, settingsGraphic, frameMain);
    SrvPersistLightXmlActor<Actor> srvPersistXmlActorUml = new SrvPersistLightXmlActor<Actor>();
    srvPersistActorUmlFull = new SrvPersistLightXmlShapeFull<ShapeFullVarious<Actor>, Actor>(srvPersistXmlActorUml);
    SrvGraphicActor<Actor, Graphics2D, SettingsDraw, Image> srvGraphicActorUml = new SrvGraphicActor<Actor, Graphics2D, SettingsDraw, Image>(srvDraw, settingsGraphic);
    srvGraphicActorUmlFull = new SrvGraphicShapeFull<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, Actor>(srvGraphicActorUml);
    SrvInteractiveActor<Actor, Graphics2D, SettingsDraw, Frame> srvInteractiveActorUml = new SrvInteractiveActor<Actor, Graphics2D, SettingsDraw, Frame>(srvGraphicActorUml, null);
    srvInteractiveActorUmlFull = new SrvInteractiveShapeVariousFull<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, Frame, Actor>(factoryEditorActorUmlFull, srvInteractiveActorUml);
  }

  @Override
  public IAsmElementUmlInteractive<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    Actor actorUml = new Actor();
    ShapeFullVarious<Actor> actorUmlFull = new ShapeFullVarious<Actor>();
    actorUmlFull.setShape(actorUml);
    AsmElementUmlInteractive<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, FileAndWriter> asmActorUmlFull = 
        new AsmElementUmlInteractive<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, FileAndWriter>(actorUmlFull, drawSettings, srvGraphicActorUmlFull, srvPersistActorUmlFull, srvInteractiveActorUmlFull);
    return asmActorUmlFull;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorActorFull getFactoryEditorActorUmlFull() {
    return factoryEditorActorUmlFull;
  }

  public SrvGraphicShapeFull<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, Actor> getSrvGraphicActorUmlFull() {
    return srvGraphicActorUmlFull;
  }

  public SrvPersistLightXmlShapeFull<ShapeFullVarious<Actor>, Actor> getSrvPersistActorUmlFull() {
    return srvPersistActorUmlFull;
  }

  public SrvInteractiveShapeVariousFull<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, Frame, Actor> getSrvInteractiveActorUmlFull() {
    return srvInteractiveActorUmlFull;
  }
}
