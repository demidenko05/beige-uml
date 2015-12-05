package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.service.edit.SrvEditLifeLineFull;
import org.beigesoft.uml.ui.EditorLifeLineFull;
import org.beigesoft.uml.ui.swing.AsmEditorLifeLineFull;

public class FactoryEditorLifeLineFull extends AFactoryEditor
    implements IFactoryEditorElementUml<LifeLineFull<ShapeUmlWithName>, Frame> {

  private SrvEditLifeLineFull<LifeLineFull<ShapeUmlWithName>, Frame> srvEditLifeLineFull;
  
  private AsmEditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, EditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, Frame, ActionEvent>> asmEditorLifeLineFull;
  
  public FactoryEditorLifeLineFull(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    super(srvI18n, srvDialog, settingsGraphic, frameMain);
  }

  @Override
  public IEditor<LifeLineFull<ShapeUmlWithName>> lazyGetEditorElementUml() {
    if(asmEditorLifeLineFull == null) {
      EditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, Frame, ActionEvent> editor = new EditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, Frame, ActionEvent>(getFrameMain(), 
          lazyGetSrvEditElementUml(), getSrvI18n().getMsg("lifeline"));
      asmEditorLifeLineFull = new AsmEditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, EditorLifeLineFull<LifeLineFull<ShapeUmlWithName>,Frame,ActionEvent>>(getFrameMain(), editor);
      asmEditorLifeLineFull.doPostConstruct();
      editor.addObserverModelChanged(getObserverModelChanged());
    }
    return asmEditorLifeLineFull;
  }

  @Override
  public SrvEditLifeLineFull<LifeLineFull<ShapeUmlWithName>, Frame> lazyGetSrvEditElementUml() {
    if(srvEditLifeLineFull == null) {
      srvEditLifeLineFull = new SrvEditLifeLineFull<LifeLineFull<ShapeUmlWithName>, Frame>(getSrvI18n(), getSrvDialog(), getSettingsGraphic());
    }
    return srvEditLifeLineFull;
  }

  public SrvEditLifeLineFull<LifeLineFull<ShapeUmlWithName>, Frame> getSrvEditLifeLineFull() {
    return srvEditLifeLineFull;
  }

  public void setSrvEditLifeLineFull(SrvEditLifeLineFull<LifeLineFull<ShapeUmlWithName>, Frame> srvEditLifeLineFull) {
    this.srvEditLifeLineFull = srvEditLifeLineFull;
  }

  public AsmEditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, EditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, Frame, ActionEvent>> getAsmEditorLifeLineFull() {
    return asmEditorLifeLineFull;
  }

  public void setAsmEditorLifeLineFull(
      AsmEditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, EditorLifeLineFull<LifeLineFull<ShapeUmlWithName>, Frame, ActionEvent>> asmEditorLifeLineFull) {
    this.asmEditorLifeLineFull = asmEditorLifeLineFull;
  }
}
