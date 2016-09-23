package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.CombinedFragment;
import org.beigesoft.uml.service.graphic.SrvGraphicCombinedFragment;
import org.beigesoft.uml.service.interactive.SrvInteractiveCombinedFragment;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlCombinedFragment;

public class FactoryAsmCombinedFragment implements IFactoryAsmElementUml<IAsmElementUmlInteractive<CombinedFragment, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, CombinedFragment> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlCombinedFragment<CombinedFragment> srvPersistXmlCombinedFragment;
  
  private final SrvGraphicCombinedFragment<CombinedFragment, CanvasWithPaint, SettingsDraw> srvGraphicCombinedFragment;
  
  private final SrvInteractiveCombinedFragment<CombinedFragment, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveCombinedFragment;
  
  private final FactoryEditorCombinedFragment factoryEditorCombinedFragment;
  
  public FactoryAsmCombinedFragment(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicCombinedFragment = new SrvGraphicCombinedFragment<CombinedFragment, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlCombinedFragment = new SrvPersistLightXmlCombinedFragment<CombinedFragment>();
    factoryEditorCombinedFragment = new FactoryEditorCombinedFragment(activity, containerGui);
    srvInteractiveCombinedFragment = new SrvInteractiveCombinedFragment<CombinedFragment, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicCombinedFragment, factoryEditorCombinedFragment);
  }

  @Override
  public IAsmElementUmlInteractive<CombinedFragment, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    CombinedFragment intUse = new CombinedFragment();
    intUse.setPointStart(new Point2D(1, 1));
    AsmElementUmlInteractive<CombinedFragment, CanvasWithPaint, SettingsDraw, FileAndWriter> asmCombinedFragmentUml = 
        new AsmElementUmlInteractive<CombinedFragment, CanvasWithPaint, SettingsDraw, FileAndWriter>(intUse,
            drawSettings, srvGraphicCombinedFragment, srvPersistXmlCombinedFragment, srvInteractiveCombinedFragment);
    return asmCombinedFragmentUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorCombinedFragment getFactoryEditorCombinedFragment() {
    return factoryEditorCombinedFragment;
  }

  public SrvPersistLightXmlCombinedFragment<CombinedFragment> getSrvPersistXmlCombinedFragment() {
    return srvPersistXmlCombinedFragment;
  }

  public SrvGraphicCombinedFragment<CombinedFragment, CanvasWithPaint, SettingsDraw> getSrvGraphicCombinedFragment() {
    return srvGraphicCombinedFragment;
  }

  public SrvInteractiveCombinedFragment<CombinedFragment, CanvasWithPaint, SettingsDraw, Activity> getSrvInteractiveCombinedFragment() {
    return srvInteractiveCombinedFragment;
  }
}
