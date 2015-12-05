package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlComment;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlFrame;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlLineUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRectangle;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlText;
import org.beigesoft.uml.ui.IGuiMainUml;

public abstract class AFactoryDiagramGeneral {

  private final FactoryAsmCommentLight factoryAsmComment;

  private final FactoryAsmTextLight factoryAsmText;
  
  private final FactoryAsmFrameFull factoryAsmFrame;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ContainerFull<FrameUml>> srvPersistListAsmFrames;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CommentUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CommentUml> srvPersistListAsmComments;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<TextUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, TextUml> srvPersistListAsmTexts;

  private final FactoryAsmRectangle factoryAsmRectangle;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RectangleUml> srvPersistListAsmRectangles;
  
  private final FactoryAsmLineUml factoryAsmLineUml;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LineUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, LineUml> srvPersistListAsmLineUmls;
  
  public AFactoryDiagramGeneral(Frame frameMain,
      IGuiMainUml<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> guiMain) {
    factoryAsmLineUml = new FactoryAsmLineUml(guiMain.getSrvDraw(), guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), 
        guiMain.getSettingsGraphicUml(), frameMain);
    factoryAsmRectangle = new FactoryAsmRectangle(guiMain.getSrvDraw(), guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), 
        guiMain.getSettingsGraphicUml(), frameMain);
    factoryAsmFrame = new FactoryAsmFrameFull(guiMain.getSrvDraw(), guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), 
        guiMain.getSettingsGraphicUml(), frameMain);
    factoryAsmComment = new FactoryAsmCommentLight(guiMain.getSrvDraw(), guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), 
        guiMain.getSettingsGraphicUml(), frameMain);
    factoryAsmText = new FactoryAsmTextLight(guiMain.getSrvDraw(), guiMain.getGuiSrvs().getSrvI18n(),
        guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    srvPersistListAsmTexts = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<TextUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, TextUml>(factoryAsmText, SrvSaveXmlText.NAMEXML_TEXTUML);
    srvPersistListAsmFrames = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ContainerFull<FrameUml>>(factoryAsmFrame, SrvSaveXmlFrame.NAMEXML_FRAMEUML);
    srvPersistListAsmRectangles = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RectangleUml>(factoryAsmRectangle, SrvSaveXmlRectangle.NAMEXML_RECTANGLE);
    srvPersistListAsmLineUmls = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LineUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, LineUml>(factoryAsmLineUml, SrvSaveXmlLineUml.NAMEXML_LINEUML);
    srvPersistListAsmComments = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CommentUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CommentUml>(factoryAsmComment, SrvSaveXmlComment.NAMEXML_COMMENTUML);
  }

  //SGS:
  public FactoryAsmCommentLight getFactoryAsmComment() {
    return factoryAsmComment;
  }

  public FactoryAsmTextLight getFactoryAsmText() {
    return factoryAsmText;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CommentUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CommentUml> getSrvPersistListAsmComments() {
    return srvPersistListAsmComments;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<TextUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, TextUml> getSrvPersistListAsmTexts() {
    return srvPersistListAsmTexts;
  }

  public FactoryAsmFrameFull getFactoryAsmFrame() {
    return factoryAsmFrame;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ContainerFull<FrameUml>> getSrvPersistListAsmFrames() {
    return srvPersistListAsmFrames;
  }

  public FactoryAsmRectangle getFactoryAsmRectangle() {
    return factoryAsmRectangle;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RectangleUml> getSrvPersistListAsmRectangles() {
    return srvPersistListAsmRectangles;
  }

  public FactoryAsmLineUml getFactoryAsmLineUml() {
    return factoryAsmLineUml;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LineUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, LineUml> getSrvPersistListAsmLineUmls() {
    return srvPersistListAsmLineUmls;
  }
}
