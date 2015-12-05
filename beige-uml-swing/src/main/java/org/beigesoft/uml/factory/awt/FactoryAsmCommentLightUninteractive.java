package org.beigesoft.uml.factory.awt;

import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUml;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.service.graphic.SrvGraphicComment;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlComment;

public class FactoryAsmCommentLightUninteractive implements IFactoryAsmElementUml<IAsmElementUml<CommentUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, CommentUml> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv;

  private final SettingsGraphicUml graphicSettings;
  
  private SrvPersistLightXmlComment<CommentUml> srvPersistXmlComment;

  private SrvGraphicComment<CommentUml, Graphics2D, SettingsDraw> srvGraphicComment;

  public FactoryAsmCommentLightUninteractive(ISrvDraw<Graphics2D, SettingsDraw, Image> drawSrv,
      SettingsGraphicUml graphicSettings) {
    this.drawSrv = drawSrv;
    this.graphicSettings = graphicSettings;
  }

  @Override
  public synchronized AsmElementUml<CommentUml, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    CommentUml commentUml = new CommentUml(graphicSettings.getWidthComment(), 
        graphicSettings.getHeightMinComment());
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUml<CommentUml, Graphics2D, SettingsDraw, FileAndWriter> asmCommentUml =
        new AsmElementUml<CommentUml, Graphics2D, SettingsDraw, FileAndWriter>
        (commentUml, drawSettings, lazyGetGraphicCommentUmlSrv(), lazyGetPersistXmlCommentUmlSrv());
    return asmCommentUml;
  }

  public synchronized SrvGraphicComment<CommentUml, Graphics2D, SettingsDraw> lazyGetGraphicCommentUmlSrv() {
    if(srvGraphicComment == null) {
      srvGraphicComment = new SrvGraphicComment<CommentUml, Graphics2D, SettingsDraw>(drawSrv, graphicSettings);
    }
    return srvGraphicComment;
  }

  public synchronized SrvPersistLightXmlComment<CommentUml> lazyGetPersistXmlCommentUmlSrv() {
    if(srvPersistXmlComment == null) {
      srvPersistXmlComment = new SrvPersistLightXmlComment<CommentUml>();
    }
    return srvPersistXmlComment;
  }
}
