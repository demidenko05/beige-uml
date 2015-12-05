package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.ui.android.AsmEditorFrameFull;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.container.IContainerInteractiveElementsUml;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.service.edit.ASrvEditElementUml;
import org.beigesoft.uml.service.edit.SrvEditContainerFull;
import org.beigesoft.uml.service.edit.SrvEditFrame;
import org.beigesoft.uml.ui.EditorFrameFull;

public class FactoryEditorFrameFull extends AFactoryEditor implements IFactoryEditorElementUml<ContainerFull<FrameUml>, Activity> {

  private SrvEditContainerFull<FrameUml, Activity> srvEditFrame;
  
  private AsmEditorFrameFull<FrameUml, EditorFrameFull<FrameUml, Activity, View>> asmEditorFrame;
  
  private IContainerInteractiveElementsUml containerInteractiveElementsUml;

  public FactoryEditorFrameFull(Activity activity,
      IContainerSrvsGui<Activity> containerGui) {
    super(activity, containerGui);
  }

  @Override
  public AsmEditorFrameFull<FrameUml, EditorFrameFull<FrameUml, Activity, View>> lazyGetEditorElementUml() {
    if(asmEditorFrame == null) {
      EditorFrameFull<FrameUml, Activity, View> editor = new EditorFrameFull<FrameUml, Activity, View>(getActivity(), 
          lazyGetSrvEditElementUml(), getSrvI18n().getMsg("frame"));
      asmEditorFrame = new AsmEditorFrameFull<FrameUml, EditorFrameFull<FrameUml,Activity,View>>(getActivity(), 
          editor, AsmEditorFrameFull.class.getSimpleName());
      editor.addObserverModelChanged(getObserverModelChanged());
      editor.setContainerInteractiveElementsUml(containerInteractiveElementsUml);
    }
    return asmEditorFrame;
  }

  @Override
  public SrvEditContainerFull<FrameUml, Activity> lazyGetSrvEditElementUml() {
    if(srvEditFrame == null) {
      ASrvEditElementUml<FrameUml, Activity> srvEditFr = new SrvEditFrame<FrameUml, Activity>(getSrvI18n(), getSrvDialog(), getSettingsGraphic());
      srvEditFrame = new SrvEditContainerFull<FrameUml, Activity>(getSrvI18n(), getSrvDialog(), getSettingsGraphic(), srvEditFr);
    }
    return srvEditFrame;
  }

  public IContainerInteractiveElementsUml getContainerInteractiveElementsUml() {
    return containerInteractiveElementsUml;
  }

  public void setContainerInteractiveElementsUml(
      IContainerInteractiveElementsUml containerInteractiveElementsUml) {
    this.containerInteractiveElementsUml = containerInteractiveElementsUml;
  }
}
