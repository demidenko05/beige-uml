package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.service.edit.SrvEditFrame;
import org.beigesoft.uml.ui.EditorFrame;
import org.beigesoft.uml.ui.swing.AsmEditorFrame;

public class FactoryEditorFrame extends AFactoryEditor
    implements IFactoryEditorElementUml<FrameUml, Frame> {

  private SrvEditFrame<FrameUml, Frame> srvEditFrame;
  
  private AsmEditorFrame<FrameUml, EditorFrame<FrameUml, Frame, ActionEvent>> asmEditorFrame;
  
  public FactoryEditorFrame(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    super(srvI18n, srvDialog, settingsGraphic, frameMain);
  }

  @Override
  public IEditor<FrameUml> lazyGetEditorElementUml() {
    if(asmEditorFrame == null) {
      EditorFrame<FrameUml, Frame, ActionEvent> editor = new EditorFrame<FrameUml, Frame, ActionEvent>(getFrameMain(), 
          lazyGetSrvEditElementUml(), getSrvI18n().getMsg("frame"));
      asmEditorFrame = new AsmEditorFrame<FrameUml, EditorFrame<FrameUml,Frame,ActionEvent>>(getFrameMain(), editor);
      asmEditorFrame.doPostConstruct();
      editor.addObserverModelChanged(getObserverModelChanged());
    }
    return asmEditorFrame;
  }

  @Override
  public SrvEditFrame<FrameUml, Frame> lazyGetSrvEditElementUml() {
    if(srvEditFrame == null) {
      srvEditFrame = new SrvEditFrame<FrameUml, Frame>(getSrvI18n(), getSrvDialog(), getSettingsGraphic());
    }
    return srvEditFrame;
  }

}
