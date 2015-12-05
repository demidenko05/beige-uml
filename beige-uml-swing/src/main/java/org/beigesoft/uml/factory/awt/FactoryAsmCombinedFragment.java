package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorCombinedFragment;
import org.beigesoft.uml.pojo.CombinedFragment;
import org.beigesoft.uml.service.graphic.SrvGraphicCombinedFragment;
import org.beigesoft.uml.service.interactive.SrvInteractiveCombinedFragment;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlCombinedFragment;

public class FactoryAsmCombinedFragment implements IFactoryAsmElementUml<IAsmElementUmlInteractive<CombinedFragment, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, CombinedFragment> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private final FactoryEditorCombinedFragment factoryEditorCombinedFragment;

  private SrvGraphicCombinedFragment<CombinedFragment, Graphics2D, SettingsDraw> graphicCombinedFragmentumlSrv;

  private SrvPersistLightXmlCombinedFragment<CombinedFragment> persistXmlCombinedFragmentSrv;

  private SrvInteractiveCombinedFragment<CombinedFragment, Graphics2D, SettingsDraw, Frame> interactiveCombinedFragmentSrv;

  public FactoryAsmCombinedFragment(ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv,
      ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv, SettingsGraphicUml graphicSettings,
      Frame frameMain) {
    this.drawSrv = drawSrv;
    this.graphicSettings = graphicSettings;
    this.factoryEditorCombinedFragment = new FactoryEditorCombinedFragment(i18nSrv, dialogSrv, graphicSettings, frameMain);
  }

  @Override
  public synchronized AsmElementUmlInteractive<CombinedFragment, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    CombinedFragment combFrag = new CombinedFragment();
    combFrag.setPointStart(new Point2D(1, 1));
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUmlInteractive<CombinedFragment, Graphics2D, SettingsDraw, FileAndWriter> asmCombinedFragment =
        new AsmElementUmlInteractive<CombinedFragment, Graphics2D, SettingsDraw, FileAndWriter>(combFrag, drawSettings, lazyGetGraphicCombinedFragmentSrv(), lazyGetPersistXmlCombinedFragmentSrv(), 
            lazyGetInteractiveCombinedFragmentSrv());
    return asmCombinedFragment;
  }

  public synchronized SrvInteractiveCombinedFragment<CombinedFragment, Graphics2D, SettingsDraw, Frame> lazyGetInteractiveCombinedFragmentSrv() {
    if(interactiveCombinedFragmentSrv == null) {
      interactiveCombinedFragmentSrv = new SrvInteractiveCombinedFragment<CombinedFragment, Graphics2D, SettingsDraw, Frame>
      (lazyGetGraphicCombinedFragmentSrv(), factoryEditorCombinedFragment);
    }
    return interactiveCombinedFragmentSrv;
  }

  public synchronized SrvPersistLightXmlCombinedFragment<CombinedFragment> lazyGetPersistXmlCombinedFragmentSrv() {
    if(persistXmlCombinedFragmentSrv == null) {
      persistXmlCombinedFragmentSrv = new SrvPersistLightXmlCombinedFragment<CombinedFragment>();
    }
    return persistXmlCombinedFragmentSrv;
  }

  public synchronized SrvGraphicCombinedFragment<CombinedFragment, Graphics2D, SettingsDraw> lazyGetGraphicCombinedFragmentSrv() {
    if(graphicCombinedFragmentumlSrv == null) {
      graphicCombinedFragmentumlSrv = new SrvGraphicCombinedFragment<CombinedFragment, Graphics2D, SettingsDraw>(drawSrv, graphicSettings);
    }
    return graphicCombinedFragmentumlSrv;
  }

  public FactoryEditorCombinedFragment getFactoryEditorCombinedFragment() {
    return factoryEditorCombinedFragment;
  }
}
