package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.model.InstanceUml;
import org.beigesoft.uml.service.graphic.SrvGraphicInstance;

public class SrvInteractiveInstance<M extends InstanceUml, DRI, SD extends ISettingsDraw, DLI> 
    extends SrvInteractiveShapeUml<M, DRI, SD> {

  private final IFactoryEditorElementUml<M, DLI> factoryEditorElementUml;
  
  public SrvInteractiveInstance(SrvGraphicInstance<M, DRI, SD> srvGraphicShape, 
      IFactoryEditorElementUml<M, DLI> factoryEditorElementUml) {
    super(srvGraphicShape);
    this.factoryEditorElementUml = factoryEditorElementUml;
  }

  @Override
  public void startEdit(M ge) {
    factoryEditorElementUml.lazyGetEditorElementUml().startEdit(ge);
  }

  @Override
  public void validate(M eu, Set<String> result) {
    factoryEditorElementUml.lazyGetSrvEditElementUml().validate(eu, result);
  }

  //SGS:
  public IFactoryEditorElementUml<M, DLI> getFactoryEditorElementUml() {
    return factoryEditorElementUml;
  }
}
