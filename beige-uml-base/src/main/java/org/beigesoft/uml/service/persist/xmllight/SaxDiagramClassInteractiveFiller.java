package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.factory.IFactorySimple;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.ClassRelationFull;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.diagram.assembly.AsmDiagramClassInteractive;
import org.beigesoft.uml.diagram.pojo.DiagramClass;
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

public class SaxDiagramClassInteractiveFiller<DRI, SD extends ISettingsDraw, IMG, DLI> extends ASaxDiagramUmlInteractiveFiller<AsmDiagramClassInteractive<DRI, SD, IMG, FileAndWriter, DLI>, DiagramClass, DRI, SD, IMG, DLI> {

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ClassFull<ClassUml>> factoryAsmClassFull;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipBinary;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipSelf;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipPoly;

  private final SaxClassUmlFiller<ClassUml> saxClassUmlFiller;
  
  private final SaxRelationshipBinaryFiller<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> saxRelationshipBinaryFiller;
  
  private final SaxRelationshipBinaryFiller<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> saxRelationshipSelfFiller;
  
  private final SaxRelationshipPolyClassFiller<ClassUml> saxRelationshipPolyFiller;
  
  public SaxDiagramClassInteractiveFiller(String namePersistable, IFactoryAsmElementUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ClassFull<ClassUml>> factoryAsmClassFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
      DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipBinary,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
      DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipSelf,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
      DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipPoly,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<CommentUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CommentUml> factoryAsmComment,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<TextUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, TextUml> factoryAsmText,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ContainerFull<FrameUml>> factoryAsmFrame,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RectangleUml> factoryAsmRectangle,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LineUml> factoryAsmLine) {
    super(namePersistable, factoryAsmComment, factoryAsmText, factoryAsmFrame, factoryAsmRectangle, factoryAsmLine);
    this.factoryAsmClassFull = factoryAsmClassFull;
    this.factoryAsmRelationshipBinary = factoryAsmRelationshipBinary;
    this.factoryAsmRelationshipSelf = factoryAsmRelationshipSelf;
    this.factoryAsmRelationshipPoly = factoryAsmRelationshipPoly;
    saxClassUmlFiller = 
        new SaxClassUmlFiller<ClassUml>(SrvSaveXmlClassUml.NAMEXML_CLASSUML, getPathCurrent());
    saxRelationshipBinaryFiller = 
        new SaxRelationshipBinaryFiller<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(SrvSaveXmlRelationshipBinary.NAMEXML_RELATIONSHIPBINARY, 
            getPathCurrent());
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
    if(super.fillModel(elementName, elementValue)) {
      return true;
    }
    else if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlDiagramClass.NAMEXML_ISABLETOCHANGEBYDOCLET.equals(elementName)) {
        getModel().getDiagramUml().setIsAbleToChangeByDoclet(Boolean.valueOf(elementValue));
        return true;
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
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(elementName == null || attrName == null || attrValue.equals("\\n")) {
      return false;
    }
    if(super.fillModel(elementName, attrName, attrValue)) {
      return true;
    }
    else if(saxClassUmlFiller.getModel() != null) {
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
    return false;
  }
 
  @Override
  public boolean handleStartElement(String localName) {
    if(super.handleStartElement(localName)) {
      return true;
    }
    if(saxClassUmlFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, FileAndWriter> asmClassFullCurr = factoryAsmClassFull.createAsmElementUml();
      asmClassFullCurr.getElementUml().setIsNew(false);
      getModel().getAsmListAsmClassesFull().addElementUml(asmClassFullCurr);
      saxClassUmlFiller.setModelAndPrepare(asmClassFullCurr.getElementUml().getShape());
      return true;
    }
    if(saxRelationshipBinaryFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, FileAndWriter> asmRelationshipBinaryCurr = factoryAsmRelationshipBinary.createAsmElementUml();
      asmRelationshipBinaryCurr.getElementUml().setIsNew(false);
      getModel().getAsmListAsmRelationshipsBinaryClass().addElementUml(asmRelationshipBinaryCurr);
      saxRelationshipBinaryFiller.setModelAndPrepare(asmRelationshipBinaryCurr.getElementUml());
      return true;
    }
    if(saxRelationshipSelfFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, FileAndWriter> asmRelationshipSelfCurr = factoryAsmRelationshipSelf.createAsmElementUml();
      asmRelationshipSelfCurr.getElementUml().setIsNew(false);
      getModel().getAsmListAsmRelationshipsSelfClass().addElementUml(asmRelationshipSelfCurr);
      saxRelationshipSelfFiller.setModelAndPrepare(asmRelationshipSelfCurr.getElementUml());
      return true;
    }
    if(saxRelationshipPolyFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, FileAndWriter> asmRelationshipPoly = factoryAsmRelationshipPoly.createAsmElementUml();
      asmRelationshipPoly.getElementUml().setIsNew(false);
      getModel().getAsmListAsmRelationshipsPolyClass().addElementUml(asmRelationshipPoly);
      saxRelationshipPolyFiller.setModelAndPrepare(asmRelationshipPoly.getElementUml());
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
    return false;
  }

  @Override
  public boolean handleEndElement(String localName) throws Exception {
    if(super.handleEndElement(localName)) {
      return true;
    }
    if(saxClassUmlFiller.getNamePersistable().equals(localName)) {
      saxClassUmlFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxRelationshipBinaryFiller.getNamePersistable().equals(localName)) {
      RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> relationshipBinaryCurr = saxRelationshipBinaryFiller.getModel();
      ClassRelationFull<ClassUml> crfStart = new ClassRelationFull<ClassUml>(relationshipBinaryCurr.getShapeRelationshipStart(), 
          relationshipBinaryCurr);
      relationshipBinaryCurr.getShapeRelationshipStart().getShapeFull().getRelationshipsBinary().add(crfStart);
      ClassRelationFull<ClassUml> crfEnd = new ClassRelationFull<ClassUml>(relationshipBinaryCurr.getShapeRelationshipEnd(), 
          relationshipBinaryCurr);
      relationshipBinaryCurr.getShapeRelationshipEnd().getShapeFull().getRelationshipsBinary().add(crfEnd);
      saxRelationshipBinaryFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxRelationshipSelfFiller.getNamePersistable().equals(localName)) {
      RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> relationshipSelfCurr = saxRelationshipSelfFiller.getModel();
      UtilsRectangleRelationship.evalPointsJointAndShared(relationshipSelfCurr);
      relationshipSelfCurr.getShapeRelationshipStart().getShapeFull().getRelationshipsSelf().add(relationshipSelfCurr);
      saxRelationshipSelfFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxRelationshipPolyFiller.getNamePersistable().equals(localName)) {
      saxRelationshipPolyFiller.setModelAndPrepare(null);
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
    return false;
  }

  //SGS:
  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ClassFull<ClassUml>> getFactoryAsmClassFull() {
    return factoryAsmClassFull;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getFactoryAsmRelationshipBinary() {
    return factoryAsmRelationshipBinary;
  }

  public SaxClassUmlFiller<ClassUml> getSaxClassUmlFiller() {
    return saxClassUmlFiller;
  }

  public SaxRelationshipBinaryFiller<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSaxRelationshipBinaryFiller() {
    return saxRelationshipBinaryFiller;
  }

  public SaxRelationshipBinaryFiller<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSaxRelationshipSelfFiller() {
    return saxRelationshipSelfFiller;
  }

  public SaxRelationshipPolyClassFiller<ClassUml> getSaxRelationshipPolyFiller() {
    return saxRelationshipPolyFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getFactoryAsmRelationshipSelf() {
    return factoryAsmRelationshipSelf;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getFactoryAsmRelationshipPoly() {
    return factoryAsmRelationshipPoly;
  }
}
