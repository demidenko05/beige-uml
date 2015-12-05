package org.beigesoft.uml.factory.android;

import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorFrame;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.service.edit.SrvEditFrame;
import org.beigesoft.uml.ui.EditorFrame;

import android.app.Activity;
import android.view.View;

public class FactoryEditorFrame extends AFactoryEditor implements IFactoryEditorElementUml<FrameUml, Activity> {

  private SrvEditFrame<FrameUml, Activity> srvEditFrame;
  
  private AsmEditorFrame<FrameUml, EditorFrame<FrameUml, Activity, View>> asmEditorFrame;
  
  public FactoryEditorFrame(Activity activity,
      IContainerSrvsGui<Activity> containerGui) {
    super(activity, containerGui);
  }

  @Override
  public IEditor<FrameUml> lazyGetEditorElementUml() {
    if(asmEditorFrame == null) {
      EditorFrame<FrameUml, Activity, View> editor = new EditorFrame<FrameUml, Activity, View>(getActivity(), 
          lazyGetSrvEditElementUml(), getSrvI18n().getMsg("frame"));
      asmEditorFrame = new AsmEditorFrame<FrameUml, EditorFrame<FrameUml,Activity,View>>(getActivity(), 
          editor, AsmEditorFrame.class.getSimpleName());
      editor.addObserverModelChanged(getObserverModelChanged());
    }
    return asmEditorFrame;
  }

  @Override
  public SrvEditFrame<FrameUml, Activity> lazyGetSrvEditElementUml() {
    if(srvEditFrame == null) {
      srvEditFrame = new SrvEditFrame<FrameUml, Activity>(getSrvI18n(), getSrvDialog(), getSettingsGraphic());
    }
    return srvEditFrame;
  }
}
