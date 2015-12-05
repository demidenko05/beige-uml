package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
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

public abstract class AFactoryDiagramGeneral {

  private final FactoryAsmText factoryAsmText;
  
  private final FactoryAsmComment factoryAsmComment;
  
  private final FactoryAsmFrameFull factoryAsmFrame;
  
  private final FactoryAsmRectangle factoryAsmRectangle;
  
  private final FactoryAsmLine factoryAsmLine;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CommentUml, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, CommentUml> srvPersistListAsmComments;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<TextUml, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, TextUml> srvPersistListAsmTexts;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ContainerFull<FrameUml>> srvPersistListAsmFrames;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RectangleUml, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RectangleUml> srvPersistListAsmRectangles;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LineUml, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, LineUml> srvPersistListAsmLines;
  
  public AFactoryDiagramGeneral(SrvDraw srvDraw, 
      IContainerSrvsGui<Activity> containerGui, Activity activity) {
    factoryAsmRectangle = new FactoryAsmRectangle(srvDraw, containerGui, activity);
    factoryAsmLine = new FactoryAsmLine(srvDraw, containerGui, activity);
    factoryAsmText = new FactoryAsmText(srvDraw, containerGui, activity);
    factoryAsmComment = new FactoryAsmComment(srvDraw, containerGui, activity);
    factoryAsmFrame = new FactoryAsmFrameFull(srvDraw, containerGui, activity);
    srvPersistListAsmComments = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CommentUml,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, CommentUml>(factoryAsmComment, SrvSaveXmlComment.NAMEXML_COMMENTUML);
    srvPersistListAsmTexts = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<TextUml,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, TextUml>(factoryAsmText, SrvSaveXmlText.NAMEXML_TEXTUML);
    srvPersistListAsmFrames = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ContainerFull<FrameUml>>(factoryAsmFrame, SrvSaveXmlFrame.NAMEXML_FRAMEUML);
    srvPersistListAsmRectangles = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RectangleUml,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, RectangleUml>(factoryAsmRectangle, SrvSaveXmlRectangle.NAMEXML_RECTANGLE); 
    srvPersistListAsmLines = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LineUml,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, LineUml>(factoryAsmLine, SrvSaveXmlLineUml.NAMEXML_LINEUML); 
  }

  //SGS:
  public FactoryAsmText getFactoryAsmText() {
    return factoryAsmText;
  }

  public FactoryAsmComment getFactoryAsmComment() {
    return factoryAsmComment;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CommentUml, CanvasWithPaint, SettingsDraw, FileAndWriter>,CanvasWithPaint, SettingsDraw, CommentUml> getSrvPersistListAsmComments() {
    return srvPersistListAsmComments;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<TextUml, CanvasWithPaint, SettingsDraw, FileAndWriter>,CanvasWithPaint, SettingsDraw, TextUml> getSrvPersistListAsmTexts() {
    return srvPersistListAsmTexts;
  }

  public FactoryAsmFrameFull getFactoryAsmFrame() {
    return factoryAsmFrame;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ContainerFull<FrameUml>> getSrvPersistListAsmFrames() {
    return srvPersistListAsmFrames;
  }

  public FactoryAsmRectangle getFactoryAsmRectangle() {
    return factoryAsmRectangle;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RectangleUml, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RectangleUml> getSrvPersistListAsmRectangles() {
    return srvPersistListAsmRectangles;
  }

  public FactoryAsmLine getFactoryAsmLine() {
    return factoryAsmLine;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LineUml, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, LineUml> getSrvPersistListAsmLines() {
    return srvPersistListAsmLines;
  }
}
