package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorComment;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.service.graphic.SrvGraphicComment;
import org.beigesoft.uml.service.interactive.SrvInteractiveComment;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlComment;

public class FactoryAsmCommentLight implements IFactoryAsmElementUml<IAsmElementUmlInteractive<CommentUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, CommentUml> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorComment factoryEditorCommentUml;

  private SrvPersistLightXmlComment<CommentUml> srvPersistXmlComment;

  private SrvGraphicComment<CommentUml, Graphics2D, SettingsDraw> srvGraphicComment;

  private SrvInteractiveComment<CommentUml, Graphics2D, SettingsDraw, Frame> srvInteractiveComment;

  public FactoryAsmCommentLight(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorCommentUml = new FactoryEditorComment(srvI18n, srvDialog, settingsGraphic, frameMain);
  }

  @Override
  public synchronized AsmElementUmlInteractive<CommentUml, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    CommentUml commentUml = new CommentUml(settingsGraphic.getWidthComment(), 
        settingsGraphic.getHeightMinComment());
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUmlInteractive<CommentUml, Graphics2D, SettingsDraw, FileAndWriter> asmCommentUml =
        new AsmElementUmlInteractive<CommentUml, Graphics2D, SettingsDraw, FileAndWriter>
        (commentUml, drawSettings, lazyGetGraphicCommentUmlSrv(), lazyGetPersistXmlCommentUmlSrv(), 
            lazyGetInteractiveCommentUmlSrv());
    return asmCommentUml;
  }

  public synchronized SrvInteractiveComment<CommentUml, Graphics2D, SettingsDraw, Frame> lazyGetInteractiveCommentUmlSrv() {
    if(srvInteractiveComment == null) {
      srvInteractiveComment = new SrvInteractiveComment<CommentUml, Graphics2D, SettingsDraw, Frame>(lazyGetGraphicCommentUmlSrv(),
          factoryEditorCommentUml);
    }
    return srvInteractiveComment;
  }

  public synchronized SrvGraphicComment<CommentUml, Graphics2D, SettingsDraw> lazyGetGraphicCommentUmlSrv() {
    if(srvGraphicComment == null) {
      srvGraphicComment = new SrvGraphicComment<CommentUml, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    }
    return srvGraphicComment;
  }

  public synchronized SrvPersistLightXmlComment<CommentUml> lazyGetPersistXmlCommentUmlSrv() {
    if(srvPersistXmlComment == null) {
      srvPersistXmlComment = new SrvPersistLightXmlComment<CommentUml>();
    }
    return srvPersistXmlComment;
  }

  public FactoryEditorComment getFactoryEditorCommentUml() {
    return factoryEditorCommentUml;
  }
}
