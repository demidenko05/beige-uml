package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.container.IContainerAsmMessagesFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.service.edit.SrvEditCoregionFull;
import org.beigesoft.uml.ui.EditorCoregionFull;
import org.beigesoft.uml.ui.swing.AsmEditorCoregionFull;

public class FactoryEditorCoregionFull extends AFactoryEditor
    implements IFactoryEditorElementUml<CoregionFull, Frame> {

  private SrvEditCoregionFull<CoregionFull, Frame> srvEditCoregionFull;
  
  private AsmEditorCoregionFull<CoregionFull, EditorCoregionFull<CoregionFull, Frame, ActionEvent>> asmEditorCoregionFull;

  private IContainerAsmMessagesFull containerAsmMessagesFull;
  
  public FactoryEditorCoregionFull(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    super(srvI18n, srvDialog, settingsGraphic, frameMain);
  }

  @Override
  public IEditor<CoregionFull> lazyGetEditorElementUml() {
    if(asmEditorCoregionFull == null) {
      EditorCoregionFull<CoregionFull, Frame, ActionEvent> editor = new EditorCoregionFull<CoregionFull, Frame, ActionEvent>(getFrameMain(), 
          lazyGetSrvEditElementUml(), getSrvI18n().getMsg("coregion"));
      asmEditorCoregionFull = new AsmEditorCoregionFull<CoregionFull, EditorCoregionFull<CoregionFull,Frame,ActionEvent>>(getFrameMain(), editor);
      asmEditorCoregionFull.doPostConstruct();
      editor.addObserverModelChanged(getObserverModelChanged());
      editor.setContainerAsmMessagesFull(containerAsmMessagesFull);
     }
    return asmEditorCoregionFull;
  }

  @Override
  public SrvEditCoregionFull<CoregionFull, Frame> lazyGetSrvEditElementUml() {
    if(srvEditCoregionFull == null) {
      srvEditCoregionFull = new SrvEditCoregionFull<CoregionFull, Frame>(getSrvI18n(), getSrvDialog(), getSettingsGraphic());
    }
    return srvEditCoregionFull;
  }

  public SrvEditCoregionFull<CoregionFull, Frame> getSrvEditCoregionFull() {
    return srvEditCoregionFull;
  }

  public void setSrvEditCoregionFull(SrvEditCoregionFull<CoregionFull, Frame> srvEditCoregionFull) {
    this.srvEditCoregionFull = srvEditCoregionFull;
  }

  public AsmEditorCoregionFull<CoregionFull, EditorCoregionFull<CoregionFull, Frame, ActionEvent>> getAsmEditorCoregionFull() {
    return asmEditorCoregionFull;
  }

  public void setAsmEditorCoregionFull(
      AsmEditorCoregionFull<CoregionFull, EditorCoregionFull<CoregionFull, Frame, ActionEvent>> asmEditorCoregionFull) {
    this.asmEditorCoregionFull = asmEditorCoregionFull;
  }

  public IContainerAsmMessagesFull getContainerAsmMessagesFull() {
    return containerAsmMessagesFull;
  }

  public void setContainerAsmMessagesFull(IContainerAsmMessagesFull containerAsmMessagesFull) {
    this.containerAsmMessagesFull = containerAsmMessagesFull;
  }
}
