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
import org.beigesoft.uml.factory.swing.FactoryEditorInteractionUse;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.service.graphic.SrvGraphicInteractionUse;
import org.beigesoft.uml.service.interactive.SrvInteractiveFragment;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlInteractionUse;

public class FactoryAsmInteractionUse implements IFactoryAsmElementUml<IAsmElementUmlInteractive<InteractionUse, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, InteractionUse> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private final FactoryEditorInteractionUse factoryEditorInteractionUse;

  private SrvGraphicInteractionUse<InteractionUse, Graphics2D, SettingsDraw> graphicInteractionUseumlSrv;

  private SrvPersistLightXmlInteractionUse<InteractionUse> persistXmlInteractionUseSrv;

  private SrvInteractiveFragment<InteractionUse, Graphics2D, SettingsDraw, Frame> interactiveInteractionUseSrv;

  public FactoryAsmInteractionUse(ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv,
      ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv, SettingsGraphicUml graphicSettings,
      Frame frameMain) {
    this.drawSrv = drawSrv;
    this.graphicSettings = graphicSettings;
    this.factoryEditorInteractionUse = new FactoryEditorInteractionUse(i18nSrv, dialogSrv, graphicSettings, frameMain);
  }

  @Override
  public synchronized AsmElementUmlInteractive<InteractionUse, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    InteractionUse intUse = new InteractionUse();
    intUse.setPointStart(new Point2D(1, 1));
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUmlInteractive<InteractionUse, Graphics2D, SettingsDraw, FileAndWriter> asmInteractionUse =
        new AsmElementUmlInteractive<InteractionUse, Graphics2D, SettingsDraw, FileAndWriter>(intUse, drawSettings, lazyGetGraphicInteractionUseSrv(), lazyGetPersistXmlInteractionUseSrv(), 
            lazyGetInteractiveInteractionUseSrv());
    return asmInteractionUse;
  }

  public synchronized SrvInteractiveFragment<InteractionUse, Graphics2D, SettingsDraw, Frame> lazyGetInteractiveInteractionUseSrv() {
    if(interactiveInteractionUseSrv == null) {
      interactiveInteractionUseSrv = new SrvInteractiveFragment<InteractionUse, Graphics2D, SettingsDraw, Frame>
      (lazyGetGraphicInteractionUseSrv(), factoryEditorInteractionUse);
    }
    return interactiveInteractionUseSrv;
  }

  public synchronized SrvPersistLightXmlInteractionUse<InteractionUse> lazyGetPersistXmlInteractionUseSrv() {
    if(persistXmlInteractionUseSrv == null) {
      persistXmlInteractionUseSrv = new SrvPersistLightXmlInteractionUse<InteractionUse>();
    }
    return persistXmlInteractionUseSrv;
  }

  public synchronized SrvGraphicInteractionUse<InteractionUse, Graphics2D, SettingsDraw> lazyGetGraphicInteractionUseSrv() {
    if(graphicInteractionUseumlSrv == null) {
      graphicInteractionUseumlSrv = new SrvGraphicInteractionUse<InteractionUse, Graphics2D, SettingsDraw>(drawSrv, graphicSettings);
    }
    return graphicInteractionUseumlSrv;
  }

  public FactoryEditorInteractionUse getFactoryEditorInteractionUse() {
    return factoryEditorInteractionUse;
  }
}
