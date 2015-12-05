package org.beigesoft.uml.service.persist.xmllight;

import java.util.ArrayList;

import org.beigesoft.factory.IFactorySimple;
import org.beigesoft.graphic.model.EMeasurementUnit;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.service.persist.xml.ASaxModelFiller;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.ClassRelationFull;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.diagram.assembly.AsmDiagramClass;
import org.beigesoft.uml.factory.FactoryClassRelationship;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.UtilsRectangleRelationship;

public class SaxDiagramClassFiller<DRI, SD extends ISettingsDraw, IMG> extends ASaxModelFiller<AsmDiagramClass<DRI, SD, IMG, FileAndWriter>> {

  private final IFactoryAsmElementUml<IAsmElementUml<ClassFull<ClassUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ClassFull<ClassUml>> factoryAsmClassFull;

  private final IFactoryAsmElementUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipBinary;

  private final IFactoryAsmElementUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipSelf;

  private final IFactoryAsmElementUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipPoly;

  private final IFactoryAsmElementUml<IAsmElementUml<CommentUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CommentUml> factoryAsmComment;

  private final IFactoryAsmElementUml<IAsmElementUml<TextUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, TextUml> factoryAsmText;
  private final SaxClassUmlFiller<ClassUml> saxClassUmlFiller;
  
  private final SaxRelationshipBinaryFiller<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> saxRelationshipBinaryFiller;
 
  private final SaxCommentFiller<CommentUml> saxCommentFiller;
  
  private final SaxTextFiller<TextUml> saxTextFiller;
  
  private final SaxRelationshipBinaryFiller<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> saxRelationshipSelfFiller;
  
  private final SaxRelationshipPolyClassFiller<ClassUml> saxRelationshipPolyFiller;

  private final IFactoryAsmElementUml<IAsmElementUml<FrameUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, FrameUml> factoryAsmFrame;

  private final SaxFrameFiller<FrameUml> saxFrameFiller;
  
  private final IFactoryAsmElementUml<IAsmElementUml<RectangleUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RectangleUml> factoryAsmRectangle;

  private final SaxRectangleFiller<RectangleUml> saxRectangleFiller;
  
  private final IFactoryAsmElementUml<IAsmElementUml<LineUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LineUml> factoryAsmLine;

  private final SaxLineUmlFiller<LineUml> saxLineFiller;
  
  public SaxDiagramClassFiller(String namePersistable, IFactoryAsmElementUml<IAsmElementUml<ClassFull<ClassUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ClassFull<ClassUml>> factoryAsmClassFull,
      IFactoryAsmElementUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
      DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipBinary,
      IFactoryAsmElementUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
      DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipSelf,
      IFactoryAsmElementUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
      DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipPoly,
      IFactoryAsmElementUml<IAsmElementUml<CommentUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CommentUml> factoryAsmComment,
      IFactoryAsmElementUml<IAsmElementUml<TextUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, TextUml> factoryAsmText,
      IFactoryAsmElementUml<IAsmElementUml<FrameUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, FrameUml> factoryAsmFrame,
      IFactoryAsmElementUml<IAsmElementUml<RectangleUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RectangleUml> factoryAsmRectangle,
      IFactoryAsmElementUml<IAsmElementUml<LineUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LineUml> factoryAsmLine) {
    super(namePersistable, new ArrayList<String>());
    this.factoryAsmRectangle = factoryAsmRectangle;
    this.factoryAsmLine = factoryAsmLine;
    this.factoryAsmFrame = factoryAsmFrame;
    this.factoryAsmClassFull = factoryAsmClassFull;
    this.factoryAsmRelationshipBinary = factoryAsmRelationshipBinary;
    this.factoryAsmRelationshipSelf = factoryAsmRelationshipSelf;
    this.factoryAsmRelationshipPoly = factoryAsmRelationshipPoly;
    this.factoryAsmComment = factoryAsmComment;
    this.factoryAsmText = factoryAsmText;
    saxFrameFiller = new SaxFrameFiller<FrameUml>(SrvSaveXmlFrame.NAMEXML_FRAMEUML, getPathCurrent());
    saxRectangleFiller = new SaxRectangleFiller<RectangleUml>(SrvSaveXmlRectangle.NAMEXML_RECTANGLE, getPathCurrent());
    saxLineFiller = new SaxLineUmlFiller<LineUml>(SrvSaveXmlLineUml.NAMEXML_LINEUML, getPathCurrent());
    saxClassUmlFiller = 
        new SaxClassUmlFiller<ClassUml>(SrvSaveXmlClassUml.NAMEXML_CLASSUML, getPathCurrent());
    saxRelationshipBinaryFiller = 
        new SaxRelationshipBinaryFiller<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipBinary.NAMEXML_RELATIONSHIPBINARY, 
            getPathCurrent());
    saxCommentFiller = new SaxCommentFiller<CommentUml>(SrvSaveXmlComment.NAMEXML_COMMENTUML, getPathCurrent());
    saxTextFiller = new SaxTextFiller<TextUml>(SrvSaveXmlText.NAMEXML_TEXTUML, getPathCurrent());
    saxRelationshipSelfFiller = 
        new SaxRelationshipBinaryFiller<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,ClassFull<ClassUml>,ClassUml>, ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipSelf.NAMEXML_RELATIONSHIPSELF, getPathCurrent());
    SaxRectangleRelationshipFiller<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> saxShapeRelationshipFiller = new SaxRectangleRelationshipFiller<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipPoly.NAMEXML_SHAPERELATIONSHIP, getPathCurrent());
    IFactorySimple<RectangleRelationship<ClassFull<ClassUml>, ClassUml>> factoryShapeRelationship = new FactoryClassRelationship();
    saxRelationshipPolyFiller = new SaxRelationshipPolyClassFiller<ClassUml>(SrvSaveXmlRelationshipPoly.NAMEXML_RELATIONSHIPPOLY, getPathCurrent(), saxShapeRelationshipFiller, factoryShapeRelationship);
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
      else if(SrvSaveXmlDiagramClass.NAMEXML_ISABLETOCHANGEBYDOCLET.equals(elementName)) {
        getModel().getDiagramUml().setIsAbleToChangeByDoclet(Boolean.valueOf(elementValue));
        return false;
      }
    }
    else if(saxClassUmlFiller.getModel() != null) {
      if(saxClassUmlFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxRelationshipBinaryFiller.getModel() != null) {
      if(saxRelationshipBinaryFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxRelationshipSelfFiller.getModel() != null) {
      if(saxRelationshipSelfFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxRelationshipPolyFiller.getModel() != null) {
      if(saxRelationshipPolyFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxCommentFiller.getModel() != null) {
      if(saxCommentFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxFrameFiller.getModel() != null) {
      if(saxFrameFiller.fillModel(elementName, elementValue)) {
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
    if(saxClassUmlFiller.getModel() != null) {
      if(saxClassUmlFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxRelationshipBinaryFiller.getModel() != null) {
      if(saxRelationshipBinaryFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxRelationshipSelfFiller.getModel() != null) {
      if(saxRelationshipSelfFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxRelationshipPolyFiller.getModel() != null) {
      if(saxRelationshipPolyFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxCommentFiller.getModel() != null) {
      if(saxCommentFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxFrameFiller.getModel() != null) {
      if(saxFrameFiller.fillModel(elementName, attrName, attrValue)) {
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
    else if(saxTextFiller.getModel() != null) {
      if(saxTextFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }
 
  @Override
  public boolean handleStartElement(String localName) {
    if(saxClassUmlFiller.getNamePersistable().equals(localName)) {
      IAsmElementUml<ClassFull<ClassUml>, DRI, SD, FileAndWriter> asmClassFullCurr = factoryAsmClassFull.createAsmElementUml();
      asmClassFullCurr.getElementUml().setIsNew(false);
      getModel().getAsmListAsmClassesFull().addElementUml(asmClassFullCurr);
      saxClassUmlFiller.setModelAndPrepare(asmClassFullCurr.getElementUml().getShape());
      return true;
    }
    if(saxRelationshipBinaryFiller.getNamePersistable().equals(localName)) {
      IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, FileAndWriter> asmRelationshipBinaryCurr = factoryAsmRelationshipBinary.createAsmElementUml();
      asmRelationshipBinaryCurr.getElementUml().setIsNew(false);
      getModel().getAsmListAsmRelationshipsBinaryClass().addElementUml(asmRelationshipBinaryCurr);
      saxRelationshipBinaryFiller.setModelAndPrepare(asmRelationshipBinaryCurr.getElementUml());
      return true;
    }
    if(saxRelationshipSelfFiller.getNamePersistable().equals(localName)) {
      IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, FileAndWriter> asmRelationshipSelfCurr = factoryAsmRelationshipSelf.createAsmElementUml();
      asmRelationshipSelfCurr.getElementUml().setIsNew(false);
      getModel().getAsmListAsmRelationshipsSelfClass().addElementUml(asmRelationshipSelfCurr);
      saxRelationshipSelfFiller.setModelAndPrepare(asmRelationshipSelfCurr.getElementUml());
      return true;
    }
    if(saxRelationshipPolyFiller.getNamePersistable().equals(localName)) {
      IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, FileAndWriter> asmRelationshipPoly = factoryAsmRelationshipPoly.createAsmElementUml();
      asmRelationshipPoly.getElementUml().setIsNew(false);
      getModel().getAsmListAsmRelationshipsPolyClass().addElementUml(asmRelationshipPoly);
      saxRelationshipPolyFiller.setModelAndPrepare(asmRelationshipPoly.getElementUml());
      return true;
    }
    if(saxCommentFiller.getNamePersistable().equals(localName)) {
      IAsmElementUml<CommentUml, DRI, SD, FileAndWriter> asmComment = factoryAsmComment.createAsmElementUml();
      asmComment.getElementUml().setIsNew(false);
      getModel().getAssemblyListCommentsUml().addElementUml(asmComment);
      saxCommentFiller.setModelAndPrepare(asmComment.getElementUml());
      return true;
    }
    if(saxFrameFiller.getNamePersistable().equals(localName)) {
      IAsmElementUml<FrameUml, DRI, SD, FileAndWriter> asmFrame = factoryAsmFrame.createAsmElementUml();
      getModel().getAsmListFrames().addElementUml(asmFrame);
      saxFrameFiller.setModelAndPrepare(asmFrame.getElementUml());
      return true;
    }
    if(saxRectangleFiller.getNamePersistable().equals(localName)) {
      IAsmElementUml<RectangleUml, DRI, SD, FileAndWriter> asmRectangle = factoryAsmRectangle.createAsmElementUml();
      getModel().getAsmListRectangles().addElementUml(asmRectangle);
      saxRectangleFiller.setModelAndPrepare(asmRectangle.getElementUml());
      return true;
    }
    if(saxLineFiller.getNamePersistable().equals(localName)) {
      IAsmElementUml<LineUml, DRI, SD, FileAndWriter> asmLine = factoryAsmLine.createAsmElementUml();
      getModel().getAsmListAsmLines().addElementUml(asmLine);
      saxLineFiller.setModelAndPrepare(asmLine.getElementUml());
      return true;
    }
    if(saxTextFiller.getNamePersistable().equals(localName)) {
      IAsmElementUml<TextUml, DRI, SD, FileAndWriter> asmText = factoryAsmText.createAsmElementUml();
      asmText.getElementUml().setIsNew(false);
      getModel().getAssemblyListTextsUml().addElementUml(asmText);
      saxTextFiller.setModelAndPrepare(asmText.getElementUml());
      return true;
    }
    if(saxClassUmlFiller.getModel() != null && saxClassUmlFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxRelationshipBinaryFiller.getModel() != null && saxRelationshipBinaryFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxRelationshipSelfFiller.getModel() != null && saxRelationshipSelfFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxRelationshipPolyFiller.getModel() != null && saxRelationshipPolyFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxCommentFiller.getModel() != null && saxCommentFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxFrameFiller.getModel() != null && saxFrameFiller.handleStartElement(localName)) {
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
    if(saxClassUmlFiller.getNamePersistable().equals(localName)) {
      saxClassUmlFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxRelationshipBinaryFiller.getNamePersistable().equals(localName)) {
      RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> relationshipBinaryCurr = saxRelationshipBinaryFiller.getModel();
      ClassRelationFull<ClassUml> crfStart = new ClassRelationFull<ClassUml>(relationshipBinaryCurr.getShapeRelationshipStart(), 
          relationshipBinaryCurr);
      ClassFull<ClassUml> shapeFullStart = relationshipBinaryCurr.getShapeRelationshipStart().getShapeFull();
      shapeFullStart.getRelationshipsBinary().add(crfStart);
      ClassRelationFull<ClassUml> crfEnd = new ClassRelationFull<ClassUml>(relationshipBinaryCurr.getShapeRelationshipEnd(), 
          relationshipBinaryCurr);
      relationshipBinaryCurr.getShapeRelationshipEnd().getShapeFull().getRelationshipsBinary().add(crfEnd);
      saxRelationshipBinaryFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxRelationshipSelfFiller.getNamePersistable().equals(localName)) {
      RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> relationshipSelfCurr = saxRelationshipSelfFiller.getModel();
      relationshipSelfCurr.getShapeRelationshipStart().getShapeFull().getRelationshipsSelf().add(relationshipSelfCurr);
      UtilsRectangleRelationship.evalPointsJointAndShared(relationshipSelfCurr);
      saxRelationshipSelfFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxRelationshipPolyFiller.getNamePersistable().equals(localName)) {
      saxRelationshipPolyFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxFrameFiller.getNamePersistable().equals(localName)) {
      saxFrameFiller.setModelAndPrepare(null);
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
    if(saxCommentFiller.getNamePersistable().equals(localName)) {
      saxCommentFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxTextFiller.getNamePersistable().equals(localName)) {
      saxTextFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxClassUmlFiller.getModel() != null && saxClassUmlFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxRelationshipBinaryFiller.getModel() != null && saxRelationshipBinaryFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxRelationshipSelfFiller.getModel() != null && saxRelationshipSelfFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxRelationshipPolyFiller.getModel() != null && saxRelationshipPolyFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxFrameFiller.getModel() != null && saxFrameFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxRectangleFiller.getModel() != null && saxRectangleFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxLineFiller.getModel() != null && saxLineFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxCommentFiller.getModel() != null && saxCommentFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxTextFiller.getModel() != null && saxTextFiller.handleEndElement(localName)) {
      return true;
    }
    return false;
  }

  //SGS:
  public IFactoryAsmElementUml<IAsmElementUml<ClassFull<ClassUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ClassFull<ClassUml>> getFactoryAsmClassFull() {
    return factoryAsmClassFull;
  }

  public IFactoryAsmElementUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getFactoryAsmRelationshipBinary() {
    return factoryAsmRelationshipBinary;
  }

  public SaxClassUmlFiller<ClassUml> getSaxClassUmlFiller() {
    return saxClassUmlFiller;
  }

  public SaxRelationshipBinaryFiller<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSaxRelationshipBinaryFiller() {
    return saxRelationshipBinaryFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getFactoryAsmRelationshipSelf() {
    return factoryAsmRelationshipSelf;
  }

  public IFactoryAsmElementUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getFactoryAsmRelationshipPoly() {
    return factoryAsmRelationshipPoly;
  }

  public IFactoryAsmElementUml<IAsmElementUml<CommentUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CommentUml> getFactoryAsmComment() {
    return factoryAsmComment;
  }

  public IFactoryAsmElementUml<IAsmElementUml<TextUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, TextUml> getFactoryAsmText() {
    return factoryAsmText;
  }

  public SaxCommentFiller<CommentUml> getSaxCommentFiller() {
    return saxCommentFiller;
  }

  public SaxTextFiller<TextUml> getSaxTextFiller() {
    return saxTextFiller;
  }

  public SaxRelationshipBinaryFiller<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSaxRelationshipSelfFiller() {
    return saxRelationshipSelfFiller;
  }

  public SaxRelationshipPolyClassFiller<ClassUml> getSaxRelationshipPolyFiller() {
    return saxRelationshipPolyFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUml<FrameUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, FrameUml> getFactoryAsmFrame() {
    return factoryAsmFrame;
  }

  public SaxFrameFiller<FrameUml> getSaxFrameFiller() {
    return saxFrameFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUml<RectangleUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RectangleUml> getFactoryAsmRectangle() {
    return factoryAsmRectangle;
  }

  public SaxRectangleFiller<RectangleUml> getSaxRectangleFiller() {
    return saxRectangleFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUml<LineUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LineUml> getFactoryAsmLine() {
    return factoryAsmLine;
  }

  public SaxLineUmlFiller<LineUml> getSaxLineFiller() {
    return saxLineFiller;
  }
}
