package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.model.IShapeUml;
import org.beigesoft.uml.service.graphic.ASrvGraphicShapeUml;

public class SrvInteractiveShapeUmlWithEditor<SH extends IShapeUml, DRI, DS extends ISettingsDraw, DLI>
    extends SrvInteractiveShapeUml<SH, DRI, DS> {

  private final IFactoryEditorElementUml<SH, DLI> factoryEditorElementUml;
  
  public SrvInteractiveShapeUmlWithEditor(ASrvGraphicShapeUml<SH, DRI, DS> graphicShapeSrv,
      IFactoryEditorElementUml<SH, DLI> factoryEditorElementUml) {
    super(graphicShapeSrv);
    this.factoryEditorElementUml = factoryEditorElementUml;
  }
  
  @Override
  public void startEdit(SH eu) {
    factoryEditorElementUml.lazyGetEditorElementUml().startEdit(eu);
  }

  @Override
  public void validate(SH eu, Set<String> result) {
    factoryEditorElementUml.lazyGetSrvEditElementUml().validate(eu, result);
  }

  public IFactoryEditorElementUml<SH, DLI> getFactoryEditorElementUml() {
    return factoryEditorElementUml;
  }
}
