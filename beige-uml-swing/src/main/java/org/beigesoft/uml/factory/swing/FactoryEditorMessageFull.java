package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.container.IContainerAsmLifeLinesFull;
import org.beigesoft.uml.container.IContainerInteractionUses;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.service.edit.SrvEditMessageFull;
import org.beigesoft.uml.ui.EditorMessageFull;
import org.beigesoft.uml.ui.swing.AsmEditorMessageFull;

public class FactoryEditorMessageFull extends AFactoryEditor
    implements IFactoryEditorElementUml<MessageFull, Frame> {

  private SrvEditMessageFull<MessageFull, Frame> srvEditMessageFull;
  
  private IContainerAsmLifeLinesFull containerAsmLifeLinesFull;

  private AsmEditorMessageFull<MessageFull, EditorMessageFull<MessageFull, Frame, ActionEvent>> asmEditorMessageFull;

  private IContainerInteractionUses containerInteractionUses;
  
  public FactoryEditorMessageFull(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    super(srvI18n, srvDialog, settingsGraphic, frameMain);
  }

  @Override
  public IEditor<MessageFull> lazyGetEditorElementUml() {
    if(asmEditorMessageFull == null) {
      EditorMessageFull<MessageFull, Frame, ActionEvent> editor = new EditorMessageFull<MessageFull, Frame, ActionEvent>(getFrameMain(), 
          lazyGetSrvEditElementUml(), getSrvI18n().getMsg("message"));
      asmEditorMessageFull = new AsmEditorMessageFull<MessageFull, EditorMessageFull<MessageFull,Frame,ActionEvent>>(getFrameMain(), editor);
      asmEditorMessageFull.doPostConstruct();
      editor.addObserverModelChanged(getObserverModelChanged());
      editor.setContainerInteractionUses(containerInteractionUses);
      editor.setContainerAsmLifeLinesFull(containerAsmLifeLinesFull);
    }
    return asmEditorMessageFull;
  }

  @Override
  public SrvEditMessageFull<MessageFull, Frame> lazyGetSrvEditElementUml() {
    if(srvEditMessageFull == null) {
      srvEditMessageFull = new SrvEditMessageFull<MessageFull, Frame>(getSrvI18n(), getSrvDialog(), getSettingsGraphic());
    }
    return srvEditMessageFull;
  }

  public SrvEditMessageFull<MessageFull, Frame> getSrvEditMessageFull() {
    return srvEditMessageFull;
  }

  public void setSrvEditMessageFull(SrvEditMessageFull<MessageFull, Frame> srvEditMessageFull) {
    this.srvEditMessageFull = srvEditMessageFull;
  }

  public AsmEditorMessageFull<MessageFull, EditorMessageFull<MessageFull, Frame, ActionEvent>> getAsmEditorMessageFull() {
    return asmEditorMessageFull;
  }

  public void setAsmEditorMessageFull(
      AsmEditorMessageFull<MessageFull, EditorMessageFull<MessageFull, Frame, ActionEvent>> asmEditorMessageFull) {
    this.asmEditorMessageFull = asmEditorMessageFull;
  }

  public IContainerAsmLifeLinesFull getContainerAsmLifeLinesFull() {
    return containerAsmLifeLinesFull;
  }

  public void setContainerAsmLifeLinesFull(IContainerAsmLifeLinesFull containerAsmLifeLinesFull) {
    this.containerAsmLifeLinesFull = containerAsmLifeLinesFull;
  }

  public IContainerInteractionUses getContainerInteractionUses() {
    return containerInteractionUses;
  }

  public void setContainerInteractionUses(
      IContainerInteractionUses containerInteractionUses) {
    this.containerInteractionUses = containerInteractionUses;
  }
}
