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
import org.beigesoft.uml.factory.swing.FactoryEditorStateInvContin;
import org.beigesoft.uml.pojo.StateInvContin;
import org.beigesoft.uml.service.graphic.SrvGraphicStateInvContin;
import org.beigesoft.uml.service.interactive.SrvInteractiveShapeUmlWithEditor;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlStateInvContin;

public class FactoryAsmStateInvContin implements IFactoryAsmElementUml<IAsmElementUmlInteractive<StateInvContin, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, StateInvContin> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private final FactoryEditorStateInvContin factoryEditorStateInvContin;

  private SrvGraphicStateInvContin<StateInvContin, Graphics2D, SettingsDraw> graphicStateInvContinumlSrv;

  private SrvPersistLightXmlStateInvContin<StateInvContin> persistXmlStateInvContinSrv;

  private SrvInteractiveShapeUmlWithEditor<StateInvContin, Graphics2D, SettingsDraw, Frame> interactiveStateInvContinSrv;

  public FactoryAsmStateInvContin(ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv,
      ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv, SettingsGraphicUml graphicSettings,
      Frame frameMain) {
    this.drawSrv = drawSrv;
    this.graphicSettings = graphicSettings;
    this.factoryEditorStateInvContin = new FactoryEditorStateInvContin(i18nSrv, dialogSrv, graphicSettings, frameMain);
  }

  @Override
  public synchronized AsmElementUmlInteractive<StateInvContin, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    StateInvContin textUml = new StateInvContin();
    textUml.setPointStart(new Point2D(1, 1));
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUmlInteractive<StateInvContin, Graphics2D, SettingsDraw, FileAndWriter> asmStateInvContin =
        new AsmElementUmlInteractive<StateInvContin, Graphics2D, SettingsDraw, FileAndWriter>(textUml, drawSettings, lazyGetGraphicStateInvContinSrv(), lazyGetPersistXmlStateInvContinSrv(), 
            lazyGetInteractiveStateInvContinSrv());
    return asmStateInvContin;
  }

  public synchronized SrvInteractiveShapeUmlWithEditor<StateInvContin, Graphics2D, SettingsDraw, Frame> lazyGetInteractiveStateInvContinSrv() {
    if(interactiveStateInvContinSrv == null) {
      interactiveStateInvContinSrv = new SrvInteractiveShapeUmlWithEditor<StateInvContin, Graphics2D, SettingsDraw, Frame>
      (lazyGetGraphicStateInvContinSrv(), factoryEditorStateInvContin);
    }
    return interactiveStateInvContinSrv;
  }

  public synchronized SrvPersistLightXmlStateInvContin<StateInvContin> lazyGetPersistXmlStateInvContinSrv() {
    if(persistXmlStateInvContinSrv == null) {
      persistXmlStateInvContinSrv = new SrvPersistLightXmlStateInvContin<StateInvContin>();
    }
    return persistXmlStateInvContinSrv;
  }

  public synchronized SrvGraphicStateInvContin<StateInvContin, Graphics2D, SettingsDraw> lazyGetGraphicStateInvContinSrv() {
    if(graphicStateInvContinumlSrv == null) {
      graphicStateInvContinumlSrv = new SrvGraphicStateInvContin<StateInvContin, Graphics2D, SettingsDraw>(drawSrv, graphicSettings);
    }
    return graphicStateInvContinumlSrv;
  }

  public FactoryEditorStateInvContin getFactoryEditorStateInvContin() {
    return factoryEditorStateInvContin;
  }
}
