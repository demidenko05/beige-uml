package org.beigesoft.uml.factory.awt;

import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUml;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.graphic.SrvGraphicText;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlText;

public class FactoryAsmTextLightUninteractive implements IFactoryAsmElementUml<IAsmElementUml<TextUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, TextUml> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private SrvGraphicText<TextUml, Graphics2D, SettingsDraw> graphicTextumlSrv;

  private SrvPersistLightXmlText<TextUml> persistXmlTextUmlSrv;

  public FactoryAsmTextLightUninteractive(ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv,
      SettingsGraphicUml graphicSettings) {
    this.drawSrv = drawSrv;
    this.graphicSettings = graphicSettings;
  }

  @Override
  public synchronized AsmElementUml<TextUml, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    TextUml textUml = new TextUml();
    textUml.setPointStart(new Point2D(1, 1));
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUml<TextUml, Graphics2D, SettingsDraw, FileAndWriter> asmTextUml =
        new AsmElementUml<TextUml, Graphics2D, SettingsDraw, FileAndWriter>(textUml, drawSettings, lazyGetGraphicTextUmlSrv(), lazyGetPersistXmlTextUmlSrv());
    return asmTextUml;
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
}
