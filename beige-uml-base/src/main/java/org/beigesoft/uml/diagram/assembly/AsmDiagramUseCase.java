package org.beigesoft.uml.diagram.assembly;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.container.IContainerShapesFullVariousInteractive;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.pojo.Actor;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.pojo.UseCase;
import org.beigesoft.uml.pojo.UseCaseExtended;
import org.beigesoft.uml.service.comparator.ComparatorAsmListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistListElementsUml;
import org.beigesoft.uml.ui.IGuiMainUml;

public class AsmDiagramUseCase<DRI, SD extends ISettingsDraw, IMG, PRI, DLI> 
    extends AsmDiagramUmlInteractive<DiagramUml, DRI, SD, IMG, PRI, DLI, 
    CommentUml, IAsmElementUmlInteractive<CommentUml, DRI, SD, PRI>, 
    TextUml, IAsmElementUmlInteractive<TextUml, DRI, SD, PRI>> 
    implements IAsmDiagramUseCase<DiagramUml, DRI, SD, IMG, PRI>, IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> {

  private final IAsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<UseCase>> asmListAsmUseCasesFull;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCase>> srvPersistListAsmUseCasesFull;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCase>> factoryAsmUseCaseFull;
  
  private final IAsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<UseCaseExtended>> asmListAsmUseCasesExtendedFull;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCaseExtended>> srvPersistListAsmUseCasesExtendedFull;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCaseExtended>> factoryAsmUseCaseExtendedFull;
  
  private final IAsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<Actor>> asmListAsmActorsFull;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<Actor>> srvPersistListAsmActorsFull;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<Actor>> factoryAsmActorFull;
  
  private final IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipBinaryVarious> asmListAsmRelationshipsBinVar;
  
  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> srvPersistListAsmRelationshipsBinVar;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> factoryAsmRelationshipBinVar;
  
  public AsmDiagramUseCase(
      DiagramUml diagramUml,
      IGuiMainUml<DRI, SD, IMG, PRI, DLI> guiApp,
      ISrvPersistAsmDiagramUml<DiagramUml, PRI> persistDiagramUmlSrv,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<CommentUml, DRI, SD, PRI>, DRI, SD, PRI, CommentUml> persistListCommentsUmlSrv,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<TextUml, DRI, SD, PRI>, DRI, SD, PRI, TextUml> persistListTextsUmlSrv,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<CommentUml, DRI, SD, PRI>, DRI, SD, PRI, CommentUml> factoryCommentUml,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<TextUml, DRI, SD, PRI>, DRI, SD, PRI, TextUml> factoryTextUml,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI>, DRI, SD, PRI, ContainerFull<FrameUml>> srvPersistXmlListFrames,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI>, DRI, SD, PRI, ContainerFull<FrameUml>> factoryFrame,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> srvPersistXmlListRectangles,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> factoryAsmRectangle,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> srvPersistXmlListLines,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> factoryAsmLine,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCase>> srvPersistListAsmUseCasesUml,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCase>> factoryAsmUseCasesUml,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<Actor>> srvPersistListAsmActorsFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<Actor>> factoryAsmActorFull,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> srvPersistListAsmRelationshipBinVarsUml,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> factoryAsmRelationshipBinVarsUml,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCaseExtended>> srvPersistListAsmUseCasesExtendedFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCaseExtended>> factoryAsmUseCaseExtendedFull) {
    super(diagramUml, guiApp, persistDiagramUmlSrv, persistListCommentsUmlSrv,
        persistListTextsUmlSrv, factoryCommentUml, factoryTextUml, srvPersistXmlListFrames, factoryFrame, 
        srvPersistXmlListRectangles, factoryAsmRectangle, srvPersistXmlListLines, factoryAsmLine);
    this.srvPersistListAsmUseCasesFull = srvPersistListAsmUseCasesUml;
    this.factoryAsmUseCaseFull = factoryAsmUseCasesUml;
    this.asmListAsmUseCasesFull = new AsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<UseCase>>(guiApp, srvPersistListAsmUseCasesUml);
    asmListAsmUseCasesFull.setWeight(0);
    getAssembliesListElementsUml().add(asmListAsmUseCasesFull);
    this.srvPersistListAsmActorsFull = srvPersistListAsmActorsFull;
    this.factoryAsmActorFull = factoryAsmActorFull;
    this.asmListAsmActorsFull = new AsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<Actor>>(guiApp, srvPersistListAsmActorsFull);
    asmListAsmActorsFull.setWeight(1);
    getAssembliesListElementsUml().add(asmListAsmActorsFull);
    this.srvPersistListAsmUseCasesExtendedFull = srvPersistListAsmUseCasesExtendedFull;
    this.factoryAsmUseCaseExtendedFull = factoryAsmUseCaseExtendedFull;
    this.asmListAsmUseCasesExtendedFull = new AsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<UseCaseExtended>>(guiApp, srvPersistListAsmUseCasesExtendedFull);
    asmListAsmUseCasesExtendedFull.setWeight(2);
    getAssembliesListElementsUml().add(asmListAsmUseCasesExtendedFull);
    this.srvPersistListAsmRelationshipsBinVar = srvPersistListAsmRelationshipBinVarsUml;
    this.factoryAsmRelationshipBinVar = factoryAsmRelationshipBinVarsUml;
    this.asmListAsmRelationshipsBinVar = new AsmListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipBinaryVarious>(guiApp, srvPersistListAsmRelationshipBinVarsUml);
    asmListAsmRelationshipsBinVar.setWeight(10);
    getAssembliesListElementsUml().add(asmListAsmRelationshipsBinVar);
    ComparatorAsmListElementsUml comparatorAssemblyListElementsUml = new ComparatorAsmListElementsUml();
    Collections.sort(getAssembliesListElementsUml(), comparatorAssemblyListElementsUml);
  }

  @Override
  public void createUseCaseAt(int screenX, int screenY) {
    double realX = UtilsGraphMath.toRealX(getHolderApp().getSettingsGraphicUml(), screenX);
    double realY = UtilsGraphMath.toRealY(getHolderApp().getSettingsGraphicUml(), screenY);
    IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI> asmUseCaseUml = factoryAsmUseCaseFull.createAsmElementUml();
    asmUseCaseUml.getElementUml().getShape().getPointStart().setX(realX);
    asmUseCaseUml.getElementUml().getShape().getPointStart().setY(realY);
    asmUseCaseUml.getElementUml().getShape().setHeight(getHolderApp().getSettingsGraphicUml().getHeightMinUserCase());
    asmUseCaseUml.getElementUml().getShape().setWidth(getHolderApp().getSettingsGraphicUml().getWidthMinUserCase());
    makeElementSelected(asmUseCaseUml);
    tryToAddIntoContainer(asmUseCaseUml, screenX, screenY);
    asmListAsmUseCasesFull.addElementUml(asmUseCaseUml);
    asmUseCaseUml.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public void createUseCaseExtendedAt(int screenX, int screenY) {
    double realX = UtilsGraphMath.toRealX(getHolderApp().getSettingsGraphicUml(), screenX);
    double realY = UtilsGraphMath.toRealY(getHolderApp().getSettingsGraphicUml(), screenY);
    IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI> asmUseCaseUml = factoryAsmUseCaseExtendedFull.createAsmElementUml();
    asmUseCaseUml.getElementUml().getShape().getPointStart().setX(realX);
    asmUseCaseUml.getElementUml().getShape().getPointStart().setY(realY);
    asmUseCaseUml.getElementUml().getShape().setHeight(getHolderApp().getSettingsGraphicUml().getHeightMinUserCase());
    asmUseCaseUml.getElementUml().getShape().setWidth(getHolderApp().getSettingsGraphicUml().getWidthMinUserCase());
    makeElementSelected(asmUseCaseUml);
    tryToAddIntoContainer(asmUseCaseUml, screenX, screenY);
    asmListAsmUseCasesExtendedFull.addElementUml(asmUseCaseUml);
    asmUseCaseUml.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public void createActorAt(int screenX, int screenY) {
    double realX = UtilsGraphMath.toRealX(getHolderApp().getSettingsGraphicUml(), screenX);
    double realY = UtilsGraphMath.toRealY(getHolderApp().getSettingsGraphicUml(), screenY);
    IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI> asmActorUml = factoryAsmActorFull.createAsmElementUml();
    asmActorUml.getElementUml().getShape().getPointStart().setX(realX);
    asmActorUml.getElementUml().getShape().getPointStart().setY(realY);
    makeElementSelected(asmActorUml);
    tryToAddIntoContainer(asmActorUml, screenX, screenY);
    asmListAsmActorsFull.addElementUml(asmActorUml);
    asmActorUml.startEdit();
    setIsChanged(true);
    refreshGui();
  }


  @Override
  public boolean tryCreateRelationshipBinVarAt(int screenX, int screenY,
      ERelationshipKind relationshipKind) {
    ShapeFullVarious<?> shapeVarFull = getShapeFullAt(screenX, screenY);
    if(shapeVarFull != null) {
      double realX = UtilsGraphMath.toRealX(getHolderApp().getSettingsGraphicUml(), screenX);
      double realY = UtilsGraphMath.toRealY(getHolderApp().getSettingsGraphicUml(), screenY);
      IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI> relBinVar = 
          factoryAsmRelationshipBinVar.createAsmElementUml();
      relBinVar.getElementUml().setKind(relationshipKind);
      relBinVar.getElementUml().getShapeRelationshipStart().setShapeFull(shapeVarFull);
      shapeVarFull.getRelationshipsVariousStart().add(relBinVar.getElementUml().getShapeRelationshipStart());
      relBinVar.getElementUml().getShapeRelationshipStart().getPointJoint().setX(realX);
      relBinVar.getElementUml().getShapeRelationshipStart().getPointJoint().setY(realY);
      relBinVar.getElementUml().getShapeRelationshipEnd().getPointJoint().setX(realX + 
          getHolderApp().getSettingsGraphicUml().fromInchToCurrentMeasure(1));
      relBinVar.getElementUml().getShapeRelationshipEnd().getPointJoint().setY(realY);
      makeElementSelected(relBinVar);
      tryToAddIntoContainer(relBinVar, screenX, screenY);
      asmListAsmRelationshipsBinVar.addElementUml(relBinVar);
      setIsChanged(true);
      refreshGui();
      return true;
    }
    return false;
  }

  @Override
  public ShapeFullVarious<?> getTiedShapeById(UUID id) {
    ShapeFullVarious<?> shapeFull = asmListAsmUseCasesFull.getElementUmlById(id);
    if(shapeFull == null) {
      shapeFull = asmListAsmActorsFull.getElementUmlById(id);
    }
    return shapeFull;
  }

  @Override
  public Collection<ShapeFull<?>> getShapesForTie() {
    Set<ShapeFull<?>> result = new HashSet<ShapeFull<?>>();
    for(IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI> asmUseCaseUml : asmListAsmUseCasesFull.getListElementsUml()) {
      result.add(asmUseCaseUml.getElementUml());
    }
    for(IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI ,SD ,PRI> asmActorUml : asmListAsmActorsFull.getListElementsUml()) {
      result.add(asmActorUml.getElementUml());
    }
    return result;
  }

  @Override
  public long getVersionShapesForTie() {
    return Math.max(asmListAsmUseCasesFull.getVersionElementsUml(), asmListAsmActorsFull.getVersionElementsUml());
  }

  @Override
  public ShapeFullVarious<?> getShapeFullVarById(UUID id) {
    ShapeFullVarious<?> shape = asmListAsmActorsFull.getElementUmlById(id);
    if(shape == null) {
      shape = asmListAsmUseCasesFull.getElementUmlById(id);
    }
    if(shape == null) {
      shape = asmListAsmUseCasesExtendedFull.getElementUmlById(id);
    }
    return shape;
  }

  @Override
  public ShapeFullVarious<?> getShapeFullAt(int screenX, int screenY) {
    for(IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI> asmActorUml : asmListAsmActorsFull.getListElementsUml()) {
      if(asmActorUml.isContainsScreenPoint(screenX, screenY)) {
        return asmActorUml.getElementUml();
      }
    }
    for(IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI> asmUseCaseUml : asmListAsmUseCasesFull.getListElementsUml()) {
      if(asmUseCaseUml.isContainsScreenPoint(screenX, screenY)) {
        return asmUseCaseUml.getElementUml();
      }
    }
    for(IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI> asmUseCaseUml : asmListAsmUseCasesExtendedFull.getListElementsUml()) {
      if(asmUseCaseUml.isContainsScreenPoint(screenX, screenY)) {
        return asmUseCaseUml.getElementUml();
      }
    }
    return null;
  }

  @Override
  public Collection<ShapeFullVarious<?>> getShapesFull() {
    Set<ShapeFullVarious<?>> result = new HashSet<ShapeFullVarious<?>>();
    for(IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI> asmActorUml : asmListAsmActorsFull.getListElementsUml()) {
      result.add(asmActorUml.getElementUml());
    }
    for(IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI> asmUc : asmListAsmUseCasesFull.getListElementsUml()) {
      result.add(asmUc.getElementUml());
    }
    for(IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI> asmUc : asmListAsmUseCasesExtendedFull.getListElementsUml()) {
      result.add(asmUc.getElementUml());
    }
    return result;
  }

  @Override
  public long getVersionShapesFull() {
    long version = Math.max(asmListAsmActorsFull.getVersionElementsUml(), asmListAsmUseCasesFull.getVersionElementsUml());
    return Math.max(asmListAsmUseCasesExtendedFull.getVersionElementsUml(), version);
  }
  
  //SGS:
  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCase>> getSrvPersistListAsmUseCasesFull() {
    return srvPersistListAsmUseCasesFull;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCase>> getFactoryAsmUseCaseFull() {
    return factoryAsmUseCaseFull;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCaseExtended>> getSrvPersistListAsmUseCasesExtendedFull() {
    return srvPersistListAsmUseCasesExtendedFull;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<UseCaseExtended>> getFactoryAsmUseCaseExtendedFull() {
    return factoryAsmUseCaseExtendedFull;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<UseCase>> getAsmListAsmUseCasesFull() {
    return asmListAsmUseCasesFull;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<UseCaseExtended>> getAsmListAsmUseCasesExtendedFull() {
    return asmListAsmUseCasesExtendedFull;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<Actor>> getAsmListAsmActorsFull() {
    return asmListAsmActorsFull;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<Actor>> getSrvPersistListAsmActorsFull() {
    return srvPersistListAsmActorsFull;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<Actor>> getFactoryAsmActorFull() {
    return factoryAsmActorFull;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipBinaryVarious> getAsmListAsmRelationshipsBinVar() {
    return asmListAsmRelationshipsBinVar;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> getSrvPersistListAsmRelationshipsBinVar() {
    return srvPersistListAsmRelationshipsBinVar;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> getFactoryAsmRelationshipBinVar() {
    return factoryAsmRelationshipBinVar;
  }
}
