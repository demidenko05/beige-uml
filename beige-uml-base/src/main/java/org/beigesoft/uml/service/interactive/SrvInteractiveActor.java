package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.Actor;
import org.beigesoft.uml.service.graphic.ASrvGraphicShapeUml;

public class SrvInteractiveActor<AC extends Actor, DRI, DS extends ISettingsDraw, DLI>
    extends SrvInteractiveShapeUml<AC, DRI, DS> {

  private final IFactoryEditorElementUml<AC, DLI> factoryEditorActorUml;
  
  public SrvInteractiveActor(ASrvGraphicShapeUml<AC, DRI, DS> graphicShapeSrv,
      IFactoryEditorElementUml<AC, DLI> factoryEditorActorUml) {
    super(graphicShapeSrv);
    this.factoryEditorActorUml = factoryEditorActorUml;
  }
  
  @Override
  public void startEdit(AC eu) {
    factoryEditorActorUml.lazyGetEditorElementUml().startEdit(eu);
  }

  @Override
  public void validate(AC eu, Set<String> result) {
    factoryEditorActorUml.lazyGetSrvEditElementUml().validate(eu, result);
  }

  public IFactoryEditorElementUml<AC, DLI> getFactoryEditorActorUml() {
    return factoryEditorActorUml;
  }
}
