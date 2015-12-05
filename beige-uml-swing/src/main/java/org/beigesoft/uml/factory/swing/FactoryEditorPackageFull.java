package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.PackageUml;
import org.beigesoft.uml.service.edit.SrvEditPackageFull;
import org.beigesoft.uml.ui.EditorPackageFull;
import org.beigesoft.uml.ui.swing.AsmEditorPackageFull;

public class FactoryEditorPackageFull extends AFactoryEditor
    implements IFactoryEditorElementUml<ShapeFullVarious<PackageUml>, Frame> {

  private SrvEditPackageFull<PackageUml, Frame> srvEditPackage;
  
  private AsmEditorPackageFull<PackageUml, EditorPackageFull<PackageUml, Frame, ActionEvent>> asmEditorPackage;
  
  public FactoryEditorPackageFull(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    super(srvI18n, srvDialog, settingsGraphic, frameMain);
  }

  @Override
  public IEditor<ShapeFullVarious<PackageUml>> lazyGetEditorElementUml() {
    if(asmEditorPackage == null) {
      EditorPackageFull<PackageUml, Frame, ActionEvent> editor = new EditorPackageFull<PackageUml, Frame, ActionEvent>(getFrameMain(), 
          lazyGetSrvEditElementUml(), getSrvI18n().getMsg("package"));
      asmEditorPackage = new AsmEditorPackageFull<PackageUml, EditorPackageFull<PackageUml, Frame, ActionEvent>>(getFrameMain(), editor);
      asmEditorPackage.doPostConstruct();
      editor.addObserverModelChanged(getObserverModelChanged());
    }
    return asmEditorPackage;
  }

  @Override
  public SrvEditPackageFull<PackageUml, Frame> lazyGetSrvEditElementUml() {
    if(srvEditPackage == null) {
      srvEditPackage = new SrvEditPackageFull<PackageUml, Frame>(getSrvI18n(), getSrvDialog(), getSettingsGraphic());
    }
    return srvEditPackage;
  }
}
