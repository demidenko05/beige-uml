package org.beigesoft.uml.service.persist.xmllight;

import java.util.ArrayList;

import org.beigesoft.graphic.model.EMeasurementUnit;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.service.persist.xml.ASaxModelFiller;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.diagram.assembly.AsmDiagramUmlInteractive;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.TextUml;

public abstract class ASaxDiagramUmlInteractiveFiller<ADUML extends AsmDiagramUmlInteractive<DUML, DRI, SD, IMG, FileAndWriter, DLI, CommentUml, IAsmElementUmlInteractive<CommentUml, DRI, SD, FileAndWriter>, TextUml, IAsmElementUmlInteractive<TextUml, DRI, SD, FileAndWriter>>, DUML extends DiagramUml, DRI, SD extends ISettingsDraw, IMG, DLI> extends ASaxModelFiller<ADUML> {

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<CommentUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CommentUml> factoryAsmComment;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<TextUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, TextUml> factoryAsmText;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ContainerFull<FrameUml>> factoryAsmFrameFull;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RectangleUml> factoryAsmRectangle;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LineUml> factoryAsmLine;

  private final SaxLineUmlFiller<LineUml> saxLineFiller;
  
  private final SaxRectangleFiller<RectangleUml> saxRectangleFiller;
  
  private final SaxContainerFullFiller<FrameUml> saxFrameFullFiller;
  
  private final SaxCommentFiller<CommentUml> saxCommentFiller;
  
  private final SaxTextFiller<TextUml> saxTextFiller;
    
  public ASaxDiagramUmlInteractiveFiller(String namePersistable,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<CommentUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CommentUml> factoryAsmComment,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<TextUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, TextUml> factoryAsmText,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ContainerFull<FrameUml>> factoryAsmFrameFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RectangleUml> factoryAsmRectangle,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LineUml> factoryAsmLine) {
    super(namePersistable, new ArrayList<String>());
    this.factoryAsmRectangle = factoryAsmRectangle;
    this.factoryAsmLine = factoryAsmLine;
    this.factoryAsmFrameFull = factoryAsmFrameFull;
    this.factoryAsmComment = factoryAsmComment;
    this.factoryAsmText = factoryAsmText;
    saxLineFiller = new SaxLineUmlFiller<LineUml>(SrvSaveXmlLineUml.NAMEXML_LINEUML, getPathCurrent());
    saxRectangleFiller = new SaxRectangleFiller<RectangleUml>(SrvSaveXmlRectangle.NAMEXML_RECTANGLE, getPathCurrent());
    SaxFrameFiller<FrameUml> saxFrameFiller = new SaxFrameFiller<FrameUml>(SrvSaveXmlFrame.NAMEXML_FRAMEUML, getPathCurrent());
    saxFrameFullFiller = new SaxContainerFullFiller<FrameUml>(saxFrameFiller);
    saxCommentFiller = new SaxCommentFiller<CommentUml>(SrvSaveXmlComment.NAMEXML_COMMENTUML, getPathCurrent());
    saxTextFiller = new SaxTextFiller<TextUml>(SrvSaveXmlText.NAMEXML_TEXTUML, getPathCurrent());
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(elementName == null || elementValue.equals("\\n")) {
      return false;
    }
    if(isConsumableForElement(elementName)) {
      if(SettingsGraphicUml.MEASUREMENT_UNIT.equals(elementName)) {
        getModel().getDiagramUml().setMeasurementUnit(EMeasurementUnit.valueOf(elementValue));
        return false;
      }
    }
    else if(saxCommentFiller.getModel() != null) {
      if(saxCommentFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxFrameFullFiller.getModel() != null) {
      if(saxFrameFullFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxRectangleFiller.getModel() != null) {
      if(saxRectangleFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxLineFiller.getModel() != null) {
      if(saxLineFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxTextFiller.getModel() != null) {
      if(saxTextFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(elementName == null || attrName == null || attrValue.equals("\\n")) {
      return false;
    }
    else if(saxCommentFiller.getModel() != null) {
      if(saxCommentFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxRectangleFiller.getModel() != null) {
      if(saxRectangleFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxLineFiller.getModel() != null) {
      if(saxLineFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxFrameFullFiller.getModel() != null) {
      if(saxFrameFullFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxTextFiller.getModel() != null) {
      if(saxTextFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }
 
  @Override
  public boolean handleStartElement(String localName) {
    if(saxCommentFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<CommentUml, DRI, SD, FileAndWriter> asmComment = factoryAsmComment.createAsmElementUml();
      asmComment.getElementUml().setIsNew(false);
      getModel().getAssemblyListCommentsUml().addElementUml(asmComment);
      saxCommentFiller.setModelAndPrepare(asmComment.getElementUml());
      return true;
    }
    if(saxFrameFullFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, FileAndWriter> asmFrame = factoryAsmFrameFull.createAsmElementUml();
      getModel().getAsmListFrames().addElementUml(asmFrame);
      asmFrame.getElementUml().setIsNew(false);
      saxFrameFullFiller.setModelAndPrepare(asmFrame.getElementUml());
      return true;
    }
    if(saxRectangleFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<RectangleUml, DRI, SD, FileAndWriter> asmRectangle = factoryAsmRectangle.createAsmElementUml();
      getModel().getAsmListRectangles().addElementUml(asmRectangle);
      asmRectangle.getElementUml().setIsNew(false);
      saxRectangleFiller.setModelAndPrepare(asmRectangle.getElementUml());
      return true;
    }
    if(saxLineFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<LineUml, DRI, SD, FileAndWriter> asmLine = factoryAsmLine.createAsmElementUml();
      getModel().getAsmListAsmLines().addElementUml(asmLine);
      asmLine.getElementUml().setIsNew(false);
      saxLineFiller.setModelAndPrepare(asmLine.getElementUml());
      return true;
    }
    if(saxTextFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<TextUml, DRI, SD, FileAndWriter> asmText = factoryAsmText.createAsmElementUml();
      asmText.getElementUml().setIsNew(false);
      getModel().getAsmListAsmTexts().addElementUml(asmText);
      saxTextFiller.setModelAndPrepare(asmText.getElementUml());
      return true;
    }
    if(saxCommentFiller.getModel() != null && saxCommentFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxFrameFullFiller.getModel() != null && saxFrameFullFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxRectangleFiller.getModel() != null && saxRectangleFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxLineFiller.getModel() != null && saxLineFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxTextFiller.getModel() != null && saxTextFiller.handleStartElement(localName)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean handleEndElement(String localName) throws Exception {
    if(saxCommentFiller.getNamePersistable().equals(localName)) {
      saxCommentFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxFrameFullFiller.getNamePersistable().equals(localName)) {
      saxFrameFullFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxRectangleFiller.getNamePersistable().equals(localName)) {
      saxRectangleFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxLineFiller.getNamePersistable().equals(localName)) {
      saxLineFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxTextFiller.getNamePersistable().equals(localName)) {
      saxTextFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxFrameFullFiller.getModel() != null && saxFrameFullFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxCommentFiller.getModel() != null && saxCommentFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxRectangleFiller.getModel() != null && saxRectangleFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxLineFiller.getModel() != null && saxLineFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxTextFiller.getModel() != null && saxTextFiller.handleEndElement(localName)) {
      return true;
    }
    return false;
  }

  //SGS:
  public SaxCommentFiller<CommentUml> getSaxCommentFiller() {
    return saxCommentFiller;
  }

  public SaxTextFiller<TextUml> getSaxTextFiller() {
    return saxTextFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<CommentUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CommentUml> getFactoryAsmComment() {
    return factoryAsmComment;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<TextUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, TextUml> getFactoryAsmText() {
    return factoryAsmText;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RectangleUml> getFactoryAsmRectangle() {
    return factoryAsmRectangle;
  }

  public SaxRectangleFiller<RectangleUml> getSaxRectangleFiller() {
    return saxRectangleFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ContainerFull<FrameUml>> getFactoryAsmFrameFull() {
    return factoryAsmFrameFull;
  }

  public SaxContainerFullFiller<FrameUml> getSaxFrameFullFiller() {
    return saxFrameFullFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LineUml> getFactoryAsmLine() {
    return factoryAsmLine;
  }

  public SaxLineUmlFiller<LineUml> getSaxLineFiller() {
    return saxLineFiller;
  }
}
