package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.diagram.assembly.AsmDiagramUseCase;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.Actor;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.pojo.UseCase;
import org.beigesoft.uml.pojo.UseCaseExtended;

public class SaxDiagramUseCaseFiller<DRI, SD extends ISettingsDraw, IMG, DLI> extends ASaxDiagramUmlInteractiveFiller<AsmDiagramUseCase<DRI, SD, IMG, FileAndWriter, DLI>, DiagramUml, DRI, SD, IMG, DLI> {

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ShapeFullVarious<Actor>> factoryAsmActorFull;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ShapeFullVarious<UseCase>> factoryAsmUseCaseFull;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ShapeFullVarious<UseCaseExtended>> factoryAsmUseCaseExtendedFull;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipBinaryVarious> factoryAsmRelationshipBinaryVarious;

  private final SaxActorFiller<Actor> saxActorFiller;
  
  private final SaxUseCaseFiller<UseCase> saxUseCaseFiller;
  
  private final SaxUseCaseExtendedFiller<UseCaseExtended> saxUseCaseExtendedFiller;
  
  private final SaxRelationshipBinaryVariousFiller<RelationshipBinaryVarious> saxRelationshipBinaryVariousFiller;
  
  public SaxDiagramUseCaseFiller(String namePersistable, IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ShapeFullVarious<Actor>> factoryAsmActorFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ShapeFullVarious<UseCase>> factoryAsmUseCaseFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ShapeFullVarious<UseCaseExtended>> factoryAsmUseCaseExtendedFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipBinaryVarious> factoryAsmRelationshipBinaryVarious,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<CommentUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CommentUml> factoryAsmComment,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<TextUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, TextUml> factoryAsmText,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ContainerFull<FrameUml>> factoryAsmFrame,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RectangleUml> factoryAsmRectangle,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LineUml> factoryAsmLine) {
    super(namePersistable, factoryAsmComment, factoryAsmText, factoryAsmFrame, factoryAsmRectangle, factoryAsmLine);
    this.factoryAsmActorFull = factoryAsmActorFull;
    this.factoryAsmUseCaseFull = factoryAsmUseCaseFull;
    this.factoryAsmUseCaseExtendedFull = factoryAsmUseCaseExtendedFull;
    this.factoryAsmRelationshipBinaryVarious = factoryAsmRelationshipBinaryVarious;
    saxActorFiller = new SaxActorFiller<Actor>(SrvSaveXmlActor.NAMEXML_ACTORUML, getPathCurrent());
    saxUseCaseFiller = new SaxUseCaseFiller<UseCase>(SrvSaveXmlUseCase.NAMEXML_USECASE, getPathCurrent());
    saxUseCaseExtendedFiller = new SaxUseCaseExtendedFiller<UseCaseExtended>(SrvSaveXmlUseCaseExtended.NAMEXML_USECASEEXTENDED, getPathCurrent());
    saxRelationshipBinaryVariousFiller = new SaxRelationshipBinaryVariousFiller<RelationshipBinaryVarious>(SrvSaveXmlRelationshipBinaryVarious.NAMEXML_RELATIONSHIPBINARYVARIOUS, getPathCurrent());
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(elementName == null || elementValue.equals("\\n")) {
      return false;
    }
    else if(super.fillModel(elementName, elementValue)) {
      return true;
    }
    else if(saxActorFiller.getModel() != null) {
      if(saxActorFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxUseCaseFiller.getModel() != null) {
      if(saxUseCaseFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxUseCaseExtendedFiller.getModel() != null) {
      if(saxUseCaseExtendedFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxRelationshipBinaryVariousFiller.getModel() != null) {
      if(saxRelationshipBinaryVariousFiller.fillModel(elementName, elementValue)) {
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
    else if(super.fillModel(elementName, attrName, attrValue)) {
      return true;
    }
    else if(saxActorFiller.getModel() != null) {
      if(saxActorFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxUseCaseFiller.getModel() != null) {
      if(saxUseCaseFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxUseCaseExtendedFiller.getModel() != null) {
      if(saxUseCaseExtendedFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxRelationshipBinaryVariousFiller.getModel() != null) {
      if(saxRelationshipBinaryVariousFiller.fillModel(elementName, attrName, attrValue)) {
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
    if(saxActorFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, FileAndWriter> asmActorFull = factoryAsmActorFull.createAsmElementUml();
      getModel().getAsmListAsmActorsFull().addElementUml(asmActorFull);
      asmActorFull.getElementUml().setIsNew(false);
      saxActorFiller.setModelAndPrepare(asmActorFull.getElementUml().getShape());
      return true;
    }
    if(saxUseCaseFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, FileAndWriter> asmUseCaseFull = factoryAsmUseCaseFull.createAsmElementUml();
      getModel().getAsmListAsmUseCasesFull().addElementUml(asmUseCaseFull);
      asmUseCaseFull.getElementUml().setIsNew(false);
      saxUseCaseFiller.setModelAndPrepare(asmUseCaseFull.getElementUml().getShape());
      return true;
    }
    if(saxUseCaseExtendedFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, FileAndWriter> asmUseCaseExtendedFull = factoryAsmUseCaseExtendedFull.createAsmElementUml();
      getModel().getAsmListAsmUseCasesExtendedFull().addElementUml(asmUseCaseExtendedFull);
      asmUseCaseExtendedFull.getElementUml().setIsNew(false);
      saxUseCaseExtendedFiller.setModelAndPrepare(asmUseCaseExtendedFull.getElementUml().getShape());
      return true;
    }
    if(saxRelationshipBinaryVariousFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, FileAndWriter> asmRelVar = factoryAsmRelationshipBinaryVarious.createAsmElementUml();
      getModel().getAsmListAsmRelationshipsBinVar().addElementUml(asmRelVar);
      asmRelVar.getElementUml().setIsNew(false);
      saxRelationshipBinaryVariousFiller.setModelAndPrepare(asmRelVar.getElementUml());
      return true;
    }
    if(saxActorFiller.getModel() != null && saxActorFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxUseCaseFiller.getModel() != null && saxUseCaseFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxUseCaseExtendedFiller.getModel() != null && saxUseCaseExtendedFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxRelationshipBinaryVariousFiller.getModel() != null && saxRelationshipBinaryVariousFiller.handleStartElement(localName)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean handleEndElement(String localName) throws Exception {
    if(super.handleEndElement(localName)) {
      return true;
    }
    if(saxActorFiller.getNamePersistable().equals(localName)) {
      saxActorFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxUseCaseFiller.getNamePersistable().equals(localName)) {
      saxUseCaseFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxUseCaseExtendedFiller.getNamePersistable().equals(localName)) {
      saxUseCaseExtendedFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxRelationshipBinaryVariousFiller.getNamePersistable().equals(localName)) {
      ShapeFullVarious<?> shapeFullStart = saxRelationshipBinaryVariousFiller.getModel().getShapeRelationshipStart().getShapeFull();
      shapeFullStart.getRelationshipsVariousStart().add(saxRelationshipBinaryVariousFiller.getModel().getShapeRelationshipStart());
      ShapeFullVarious<?> shapeFullEnd = saxRelationshipBinaryVariousFiller.getModel().getShapeRelationshipEnd().getShapeFull();
      shapeFullEnd.getRelationshipsVariousEnd().add(saxRelationshipBinaryVariousFiller.getModel().getShapeRelationshipEnd());
      saxRelationshipBinaryVariousFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxActorFiller.getModel() != null && saxActorFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxUseCaseFiller.getModel() != null && saxUseCaseFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxUseCaseExtendedFiller.getModel() != null && saxUseCaseExtendedFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxRelationshipBinaryVariousFiller.getModel() != null && saxRelationshipBinaryVariousFiller.handleEndElement(localName)) {
      return true;
    }
    return false;
  }

  //SGS:
  public SaxActorFiller<Actor> getSaxActorFiller() {
    return saxActorFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ShapeFullVarious<Actor>> getFactoryAsmActorFull() {
    return factoryAsmActorFull;
  }

  public SaxUseCaseFiller<UseCase> getSaxUseCaseFiller() {
    return saxUseCaseFiller;
  }

  public SaxUseCaseExtendedFiller<UseCaseExtended> getSaxUseCaseExtendedFiller() {
    return saxUseCaseExtendedFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ShapeFullVarious<UseCaseExtended>> getFactoryAsmUseCaseExtendedFull() {
    return factoryAsmUseCaseExtendedFull;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ShapeFullVarious<UseCase>> getFactoryAsmUseCaseFull() {
    return factoryAsmUseCaseFull;
  }

  public SaxRelationshipBinaryVariousFiller<RelationshipBinaryVarious> getSaxRelationshipBinaryVariousFiller() {
    return saxRelationshipBinaryVariousFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipBinaryVarious> getFactoryAsmRelationshipBinaryVarious() {
    return factoryAsmRelationshipBinaryVarious;
  }
}
