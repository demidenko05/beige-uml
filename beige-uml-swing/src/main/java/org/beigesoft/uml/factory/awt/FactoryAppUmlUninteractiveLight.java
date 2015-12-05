package org.beigesoft.uml.factory.awt;

import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.container.IContainerAppUml;
import org.beigesoft.uml.diagram.assembly.AsmDiagramClass;
import org.beigesoft.uml.diagram.pojo.DiagramClass;
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
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SaxDiagramClassFiller;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlClassUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlComment;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramClass;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlFrame;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlLineUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRectangle;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipBinary;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipPoly;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipSelf;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlText;

public class FactoryAppUmlUninteractiveLight {

  private AsmDiagramClass<Graphics2D, SettingsDraw, Image, FileAndWriter> asmDiagramClass;
  
  private SrvPersistLightXmlAsmDiagramUml<DiagramClass> srvPersistXmlDiagramClass;
  
  private SrvPersistLightXmlListElementsUml<IAsmElementUml<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ClassFull<ClassUml>> srvPersistXmlListClassesFull;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistXmlListAsmRelationships;

  private final FactoryAsmClassFullUninteractiveLight factoryAsmClassFull;
  
  private final FactoryAsmRelationshipClassUninteractiveLight factoryAsmRelationshipBinaryClass;
  
  private final FactoryAsmRelationshipSelfClassLightUninteractive factoryAsmRelationshipSelf;
  
  private final FactoryAsmRelationshipPolyClassLightUninteractive factoryAsmRelationshipPoly;
  
  private final FactoryAsmCommentLightUninteractive factoryAsmComment;
  
  private final FactoryAsmTextLightUninteractive factoryAsmText;
  
  private final FactoryAsmFrameUninteractive factoryAsmFrame;
  
  private final IContainerAppUml<Graphics2D, SettingsDraw, Image> containerAppUml;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListRelationshipsPolyClass;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListRelationshipsSelfClass;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUml<CommentUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CommentUml> srvPersistListAsmComments;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUml<TextUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, TextUml> srvPersistListAsmTexts;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUml<FrameUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FrameUml> srvPersistListAsmFrames;

  private final FactoryAsmRectangleUninteractive factoryAsmRectangle;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUml<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RectangleUml> srvPersistListAsmRectangles;
  
  private final FactoryAsmLineUmlUninteractive factoryAsmLine;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUml<LineUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, LineUml> srvPersistListAsmLines;
  
  public FactoryAppUmlUninteractiveLight(IContainerAppUml<Graphics2D, SettingsDraw, Image> containerAppUml) {
    this.containerAppUml = containerAppUml;
    factoryAsmLine = new FactoryAsmLineUmlUninteractive(containerAppUml.getSrvDraw(), containerAppUml.getSettingsGraphicUml());
    factoryAsmRectangle = new FactoryAsmRectangleUninteractive(containerAppUml.getSrvDraw(), containerAppUml.getSettingsGraphicUml());
    factoryAsmFrame = new FactoryAsmFrameUninteractive(containerAppUml.getSrvDraw(), containerAppUml.getSettingsGraphicUml());
    factoryAsmText = new FactoryAsmTextLightUninteractive(containerAppUml.getSrvDraw(), containerAppUml.getSettingsGraphicUml());
    factoryAsmComment = new FactoryAsmCommentLightUninteractive(containerAppUml.getSrvDraw(), containerAppUml.getSettingsGraphicUml());
    factoryAsmRelationshipPoly = new FactoryAsmRelationshipPolyClassLightUninteractive(containerAppUml.getSrvDraw(), containerAppUml.getSettingsGraphicUml());
    factoryAsmRelationshipSelf = new FactoryAsmRelationshipSelfClassLightUninteractive(containerAppUml.getSrvDraw(), containerAppUml.getSettingsGraphicUml());
    factoryAsmClassFull = new FactoryAsmClassFullUninteractiveLight(containerAppUml.getSrvDraw(), containerAppUml.getSettingsGraphicUml());
    factoryAsmRelationshipBinaryClass = new FactoryAsmRelationshipClassUninteractiveLight(containerAppUml.getSrvDraw(), containerAppUml.getSettingsGraphicUml());
    SaxDiagramClassFiller<Graphics2D, SettingsDraw, Image> saxDiagramClassFiller = new SaxDiagramClassFiller<Graphics2D, SettingsDraw, Image>(SrvSaveXmlDiagramClass.NAMEXML_DIAGRAMCLASS, 
        factoryAsmClassFull, factoryAsmRelationshipBinaryClass, factoryAsmRelationshipSelf, factoryAsmRelationshipPoly, factoryAsmComment, factoryAsmText, factoryAsmFrame, factoryAsmRectangle, factoryAsmLine);
    SrvSaveXmlDiagramClass<DiagramClass> srvSaveXmlDiagramUml = new SrvSaveXmlDiagramClass<DiagramClass>(SrvSaveXmlDiagramClass.NAMEXML_DIAGRAMCLASS);
    srvPersistXmlDiagramClass = new SrvPersistLightXmlAsmDiagramUml<DiagramClass>(srvSaveXmlDiagramUml,
        saxDiagramClassFiller, SrvSaveXmlDiagramClass.DIAGRAM_FILE_EXTENSION);
    srvPersistXmlListClassesFull = new SrvPersistLightXmlListElementsUml<IAsmElementUml<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ClassFull<ClassUml>>
          (factoryAsmClassFull, SrvSaveXmlClassUml.NAMEXML_CLASSUML);
    srvPersistXmlListAsmRelationships = new SrvPersistLightXmlListElementsUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>
          (factoryAsmRelationshipBinaryClass, SrvSaveXmlRelationshipBinary.NAMEXML_RELATIONSHIPBINARY);
    srvPersistListRelationshipsSelfClass = new SrvPersistLightXmlListElementsUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,ClassFull<ClassUml>,ClassUml>>
        (factoryAsmRelationshipSelf, SrvSaveXmlRelationshipSelf.NAMEXML_RELATIONSHIPSELF);
    srvPersistListRelationshipsPolyClass = new SrvPersistLightXmlListElementsUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,ClassFull<ClassUml>,ClassUml>>
        (factoryAsmRelationshipPoly, SrvSaveXmlRelationshipPoly.NAMEXML_RELATIONSHIPPOLY);
    srvPersistListAsmComments = new SrvPersistLightXmlListElementsUml<IAsmElementUml<CommentUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CommentUml>
        (factoryAsmComment, SrvSaveXmlComment.NAMEXML_COMMENTUML);
    srvPersistListAsmTexts = new SrvPersistLightXmlListElementsUml<IAsmElementUml<TextUml,Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, TextUml>
        (factoryAsmText, SrvSaveXmlText.NAMEXML_TEXTUML);
    srvPersistListAsmFrames = new SrvPersistLightXmlListElementsUml<IAsmElementUml<FrameUml,Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FrameUml>
        (factoryAsmFrame, SrvSaveXmlFrame.NAMEXML_FRAMEUML);
    srvPersistListAsmRectangles = new SrvPersistLightXmlListElementsUml<IAsmElementUml<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RectangleUml>
    (factoryAsmRectangle, SrvSaveXmlRectangle.NAMEXML_RECTANGLE);
    srvPersistListAsmLines = new SrvPersistLightXmlListElementsUml<IAsmElementUml<LineUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, LineUml>
    (factoryAsmLine, SrvSaveXmlLineUml.NAMEXML_LINEUML);
    DiagramClass diagramClass = new DiagramClass();
    diagramClass.setAlgorithmId(1);
    diagramClass.setIsAbleToChangeByDoclet(true);
    diagramClass.setMeasurementUnit(containerAppUml.getSettingsGraphicUml().getMeasurementUnit());
    asmDiagramClass = new AsmDiagramClass<Graphics2D, SettingsDraw, Image, FileAndWriter>(diagramClass, 
        containerAppUml, srvPersistXmlDiagramClass, srvPersistXmlListClassesFull, srvPersistXmlListAsmRelationships, 
          srvPersistListRelationshipsSelfClass, factoryAsmRelationshipSelf, srvPersistListRelationshipsPolyClass, factoryAsmRelationshipPoly, 
          srvPersistListAsmComments, factoryAsmComment, srvPersistListAsmTexts, factoryAsmText, srvPersistListAsmFrames, factoryAsmFrame, 
          srvPersistListAsmRectangles, factoryAsmRectangle, srvPersistListAsmLines, factoryAsmLine);
    saxDiagramClassFiller.getSaxRelationshipBinaryFiller().getSaxRectangleRelationshipStartFiller().setContainerShapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipBinaryFiller().getSaxRectangleRelationshipEndFiller().setContainerShapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipSelfFiller().getSaxRectangleRelationshipStartFiller().setContainerShapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipSelfFiller().getSaxRectangleRelationshipEndFiller().setContainerShapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipPolyFiller().getSaxShapeRelationshipFiller().setContainerShapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxTextFiller().setContainerShapesUmlForTie(asmDiagramClass);
  }

  //SGS:
  public AsmDiagramClass<Graphics2D, SettingsDraw, Image, FileAndWriter> getAsmDiagramClass() {
    return asmDiagramClass;
  }

  public void setAsmDiagramClass(
      AsmDiagramClass<Graphics2D, SettingsDraw, Image, FileAndWriter> asmDiagramClass) {
    this.asmDiagramClass = asmDiagramClass;
  }

  public SrvPersistLightXmlAsmDiagramUml<DiagramClass> getSrvPersistXmlDiagramClass() {
    return srvPersistXmlDiagramClass;
  }

  public void setSrvPersistXmlDiagramClass(
      SrvPersistLightXmlAsmDiagramUml<DiagramClass> srvPersistXmlDiagramClass) {
    this.srvPersistXmlDiagramClass = srvPersistXmlDiagramClass;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUml<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ClassFull<ClassUml>> getSrvPersistXmlListClassesFull() {
    return srvPersistXmlListClassesFull;
  }

  public void setSrvPersistXmlListClassesFull(
      SrvPersistLightXmlListElementsUml<IAsmElementUml<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ClassFull<ClassUml>> srvPersistXmlListClassesFull) {
    this.srvPersistXmlListClassesFull = srvPersistXmlListClassesFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistXmlListAsmRelationships() {
    return srvPersistXmlListAsmRelationships;
  }

  public FactoryAsmClassFullUninteractiveLight getFactoryAsmClassFull() {
    return factoryAsmClassFull;
  }

  public FactoryAsmRelationshipClassUninteractiveLight getFactoryAsmRelationshipBinaryClass() {
    return factoryAsmRelationshipBinaryClass;
  }

  public IContainerAppUml<Graphics2D, SettingsDraw, Image> getContainerAppUml() {
    return containerAppUml;
  }

  public FactoryAsmRelationshipSelfClassLightUninteractive getFactoryAsmRelationshipSelf() {
    return factoryAsmRelationshipSelf;
  }

  public FactoryAsmRelationshipPolyClassLightUninteractive getFactoryAsmRelationshipPoly() {
    return factoryAsmRelationshipPoly;
  }

  public FactoryAsmCommentLightUninteractive getFactoryAsmComment() {
    return factoryAsmComment;
  }

  public FactoryAsmTextLightUninteractive getFactoryAsmText() {
    return factoryAsmText;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListRelationshipsPolyClass() {
    return srvPersistListRelationshipsPolyClass;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListRelationshipsSelfClass() {
    return srvPersistListRelationshipsSelfClass;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUml<CommentUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CommentUml> getSrvPersistListAsmComments() {
    return srvPersistListAsmComments;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUml<TextUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, TextUml> getSrvPersistListAsmTexts() {
    return srvPersistListAsmTexts;
  }

  public FactoryAsmFrameUninteractive getFactoryAsmFrame() {
    return factoryAsmFrame;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUml<FrameUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FrameUml> getSrvPersistListAsmFrames() {
    return srvPersistListAsmFrames;
  }

  public FactoryAsmRectangleUninteractive getFactoryAsmRectangle() {
    return factoryAsmRectangle;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUml<RectangleUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RectangleUml> getSrvPersistListAsmRectangles() {
    return srvPersistListAsmRectangles;
  }

  public FactoryAsmLineUmlUninteractive getFactoryAsmLine() {
    return factoryAsmLine;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUml<LineUml, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, LineUml> getSrvPersistListAsmLines() {
    return srvPersistListAsmLines;
  }
}
