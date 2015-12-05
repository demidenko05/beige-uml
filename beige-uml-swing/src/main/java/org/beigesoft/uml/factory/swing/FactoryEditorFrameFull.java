package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.container.IContainerInteractiveElementsUml;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.service.edit.SrvEditContainerFull;
import org.beigesoft.uml.service.edit.SrvEditFrame;
import org.beigesoft.uml.ui.EditorFrameFull;
import org.beigesoft.uml.ui.swing.AsmEditorFrameFull;

public class FactoryEditorFrameFull extends AFactoryEditor
    implements IFactoryEditorElementUml<ContainerFull<FrameUml>, Frame> {

  private SrvEditContainerFull<FrameUml, Frame> srvEditFrame;
  
  private AsmEditorFrameFull<FrameUml, EditorFrameFull<FrameUml, Frame, ActionEvent>> asmEditorFrame;
  
  private IContainerInteractiveElementsUml containerInteractiveElementsUml;
  
  public FactoryEditorFrameFull(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    super(srvI18n, srvDialog, settingsGraphic, frameMain);
  }

  @Override
  public IEditor<ContainerFull<FrameUml>> lazyGetEditorElementUml() {
    if(asmEditorFrame == null) {
      EditorFrameFull<FrameUml, Frame, ActionEvent> editor = new EditorFrameFull<FrameUml, Frame, ActionEvent>(getFrameMain(), 
          lazyGetSrvEditElementUml(), getSrvI18n().getMsg("frame"));
      asmEditorFrame = new AsmEditorFrameFull<FrameUml, EditorFrameFull<FrameUml,Frame,ActionEvent>>(getFrameMain(), editor);
      asmEditorFrame.doPostConstruct();
      editor.addObserverModelChanged(getObserverModelChanged());
      editor.setContainerInteractiveElementsUml(containerInteractiveElementsUml);
    }
    return asmEditorFrame;
  }

  @Override
  public SrvEditContainerFull<FrameUml, Frame> lazyGetSrvEditElementUml() {
    if(srvEditFrame == null) {
      SrvEditFrame<FrameUml, Frame> srvEditFr = new SrvEditFrame<FrameUml, Frame>(getSrvI18n(), getSrvDialog(), getSettingsGraphic());
      srvEditFrame = new SrvEditContainerFull<FrameUml, Frame>(getSrvI18n(), getSrvDialog(), getSettingsGraphic(), srvEditFr);
    }
    return srvEditFrame;
  }

  public SrvEditContainerFull<FrameUml, Frame> getSrvEditFrame() {
    return srvEditFrame;
  }

  public void setSrvEditFrame(SrvEditContainerFull<FrameUml, Frame> srvEditFrame) {
    this.srvEditFrame = srvEditFrame;
  }

  public AsmEditorFrameFull<FrameUml, EditorFrameFull<FrameUml, Frame, ActionEvent>> getAsmEditorFrame() {
    return asmEditorFrame;
  }

  public void setAsmEditorFrame(
      AsmEditorFrameFull<FrameUml, EditorFrameFull<FrameUml, Frame, ActionEvent>> asmEditorFrame) {
    this.asmEditorFrame = asmEditorFrame;
  }

  public IContainerInteractiveElementsUml getContainerInteractiveElementsUml() {
    return containerInteractiveElementsUml;
  }

  public void setContainerInteractiveElementsUml(
      IContainerInteractiveElementsUml containerInteractiveElementsUml) {
    this.containerInteractiveElementsUml = containerInteractiveElementsUml;
  }
}
