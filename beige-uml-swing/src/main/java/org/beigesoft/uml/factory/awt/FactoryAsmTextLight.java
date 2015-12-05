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
import org.beigesoft.uml.factory.swing.FactoryEditorText;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.graphic.SrvGraphicText;
import org.beigesoft.uml.service.interactive.SrvInteractiveText;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlText;

public class FactoryAsmTextLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<TextUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, TextUml> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private final FactoryEditorText factoryEditorTextUml;

  private SrvGraphicText<TextUml, Graphics2D, SettingsDraw> graphicTextumlSrv;

  private SrvPersistLightXmlText<TextUml> persistXmlTextUmlSrv;

  private SrvInteractiveText<TextUml, Graphics2D, SettingsDraw, Frame> interactiveTextUmlSrv;

  public FactoryAsmTextLight(ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv,
      ISrvI18n i18nSrv, ISrvDialog<Frame> dialogSrv, SettingsGraphicUml graphicSettings,
      Frame frameMain) {
    this.drawSrv = drawSrv;
    this.graphicSettings = graphicSettings;
    this.factoryEditorTextUml = new FactoryEditorText(i18nSrv, dialogSrv, graphicSettings, frameMain);
  }

  @Override
  public synchronized AsmElementUmlInteractive<TextUml, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    TextUml textUml = new TextUml();
    textUml.setPointStart(new Point2D(1, 1));
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUmlInteractive<TextUml, Graphics2D, SettingsDraw, FileAndWriter> asmTextUml =
        new AsmElementUmlInteractive<TextUml, Graphics2D, SettingsDraw, FileAndWriter>(textUml, drawSettings, lazyGetGraphicTextUmlSrv(), lazyGetPersistXmlTextUmlSrv(), 
            lazyGetInteractiveTextUmlSrv());
    return asmTextUml;
  }

  public synchronized SrvInteractiveText<TextUml, Graphics2D, SettingsDraw, Frame> lazyGetInteractiveTextUmlSrv() {
    if(interactiveTextUmlSrv == null) {
      interactiveTextUmlSrv = new SrvInteractiveText<TextUml, Graphics2D, SettingsDraw, Frame>
      (lazyGetGraphicTextUmlSrv(), factoryEditorTextUml);
    }
    return interactiveTextUmlSrv;
  }

  public synchronized SrvPersistLightXmlText<TextUml> lazyGetPersistXmlTextUmlSrv() {
    if(persistXmlTextUmlSrv == null) {
      persistXmlTextUmlSrv = new SrvPersistLightXmlText<TextUml>();
    }
    return persistXmlTextUmlSrv;
  }

  public synchronized SrvGraphicText<TextUml, Graphics2D, SettingsDraw> lazyGetGraphicTextUmlSrv() {
    if(graphicTextumlSrv == null) {
      graphicTextumlSrv = new SrvGraphicText<TextUml, Graphics2D, SettingsDraw>(drawSrv, graphicSettings);
    }
    return graphicTextumlSrv;
  }

  public FactoryEditorText getFactoryEditorTextUml() {
    return factoryEditorTextUml;
  }
}
