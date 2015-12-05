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
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.model.InstanceUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.comparator.ComparatorAsmListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistListElementsUml;
import org.beigesoft.uml.ui.IGuiMainUml;

public class AsmDiagramObject<DRI, SD extends ISettingsDraw, IMG, PRI, DLI> 
    extends AsmDiagramUmlInteractive<DiagramUml, DRI, SD, IMG, PRI, DLI, 
    CommentUml, IAsmElementUmlInteractive<CommentUml, DRI, SD, PRI>, 
    TextUml, IAsmElementUmlInteractive<TextUml, DRI, SD, PRI>>
    implements IAsmDiagramObject<DiagramUml, DRI, SD, IMG, PRI>, 
    IContainerShapesFullVariousInteractive<ShapeFullVarious<?>>  {

  private final IAsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<InstanceUml>> asmListAsmInstancesFull;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<InstanceUml>> srvPersistListAsmInstancesFull;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<InstanceUml>> factoryAsmInstanceFull;
  
  private final IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipBinaryVarious> asmListAsmRelationshipsBinVar;
  
  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> srvPersistListAsmRelationshipsBinVar;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> factoryAsmRelationshipBinVar;
  
  public AsmDiagramObject(
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
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<InstanceUml>> srvPersistListAsmInstancesUml,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<InstanceUml>> factoryAsmInstancesUml,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> srvPersistListAsmRelationshipBinVarsUml,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> factoryAsmRelationshipBinVarsUml) {
    super(diagramUml, guiApp, persistDiagramUmlSrv, persistListCommentsUmlSrv,
        persistListTextsUmlSrv, factoryCommentUml, factoryTextUml, srvPersistXmlListFrames, factoryFrame, srvPersistXmlListRectangles, factoryAsmRectangle, srvPersistXmlListLines, factoryAsmLine);
    this.srvPersistListAsmInstancesFull = srvPersistListAsmInstancesUml;
    this.factoryAsmInstanceFull = factoryAsmInstancesUml;
    this.asmListAsmInstancesFull = new AsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<InstanceUml>>(guiApp, srvPersistListAsmInstancesUml);
    asmListAsmInstancesFull.setWeight(0);
    getAssembliesListElementsUml().add(asmListAsmInstancesFull);
    this.srvPersistListAsmRelationshipsBinVar = srvPersistListAsmRelationshipBinVarsUml;
    this.factoryAsmRelationshipBinVar = factoryAsmRelationshipBinVarsUml;
    this.asmListAsmRelationshipsBinVar = new AsmListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipBinaryVarious>(guiApp, srvPersistListAsmRelationshipBinVarsUml);
    asmListAsmRelationshipsBinVar.setWeight(10);
    getAssembliesListElementsUml().add(asmListAsmRelationshipsBinVar);
    ComparatorAsmListElementsUml comparatorAssemblyListElementsUml = new ComparatorAsmListElementsUml();
    Collections.sort(getAssembliesListElementsUml(), comparatorAssemblyListElementsUml);
  }

  @Override
  public Collection<ShapeFull<?>> getShapesForTie() {
    Set<ShapeFull<?>> result = new HashSet<ShapeFull<?>>();
    for(IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI> acl : asmListAsmInstancesFull.getListElementsUml()) {
      result.add(acl.getElementUml());
    }
    return result;
  }

  @Override
  public ShapeFull<?> getTiedShapeById(UUID id) {
    ShapeFullVarious<?> tiedShape = asmListAsmInstancesFull.getElementUmlById(id);
    return tiedShape;
  }

  @Override
  public long getVersionShapesForTie() {
    return asmListAsmInstancesFull.getVersionElementsUml();
  }
  
  @Override
  public void createInstanceAt(int screenX, int screenY) throws Exception {
    IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI> asmInstance = getFactoryAsmInstanceFull().createAsmElementUml();
    asmInstance.getElementUml().getShape().getPointStart().setX(UtilsGraphMath.toRealX(getGuiApp().getSettingsGraphicUml(), screenX));
    asmInstance.getElementUml().getShape().getPointStart().setY(UtilsGraphMath.toRealY(getGuiApp().getSettingsGraphicUml(), screenY));
    makeElementSelected(asmInstance);
    tryToAddIntoContainer(asmInstance, screenX, screenY);
    asmListAsmInstancesFull.addElementUml(asmInstance);
    asmInstance.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public boolean tryCreateAssociationAt(int screenX, int screenY) {
    ShapeFullVarious<?> shapeVarFull = getShapeFullAt(screenX, screenY);
    if(shapeVarFull != null) {
      double realX = UtilsGraphMath.toRealX(getHolderApp().getSettingsGraphicUml(), screenX);
      double realY = UtilsGraphMath.toRealY(getHolderApp().getSettingsGraphicUml(), screenY);
      IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI> relBinVar = 
          factoryAsmRelationshipBinVar.createAsmElementUml();
      relBinVar.getElementUml().setKind(ERelationshipKind.ASSOCIATION);
      relBinVar.getElementUml().getShapeRelationshipStart().setShapeFull(shapeVarFull);
      shapeVarFull.getRelationshipsVariousStart().add(relBinVar.getElementUml().getShapeRelationshipStart());
      relBinVar.getElementUml().getShapeRelationshipStart().setEndType(ERelationshipEndType.END);
      relBinVar.getElementUml().getShapeRelationshipStart().getPointJoint().setX(realX);
      relBinVar.getElementUml().getShapeRelationshipStart().getPointJoint().setY(realY);
      relBinVar.getElementUml().getShapeRelationshipEnd().getPointJoint().setX(shapeVarFull.getShape().getPointStart().getX() +
          shapeVarFull.getShape().getWidth() +
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
  public ShapeFullVarious<?> getShapeFullVarById(UUID id) {
    ShapeFullVarious<?> shape = asmListAsmInstancesFull.getElementUmlById(id);
    return shape;
  }

  @Override
  public ShapeFullVarious<?> getShapeFullAt(int screenX, int screenY) {
    for(IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI> asmInstanceUml : asmListAsmInstancesFull.getListElementsUml()) {
      if(asmInstanceUml.isContainsScreenPoint(screenX, screenY)) {
        return asmInstanceUml.getElementUml();
      }
    }
    return null;
  }

  @Override
  public Collection<ShapeFullVarious<?>> getShapesFull() {
    Set<ShapeFullVarious<?>> result = new HashSet<ShapeFullVarious<?>>();
    for(IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI> asmActorUml : asmListAsmInstancesFull.getListElementsUml()) {
      result.add(asmActorUml.getElementUml());
    }
    return result;
  }

  @Override
  public long getVersionShapesFull() {
    return asmListAsmInstancesFull.getVersionElementsUml();
  }
  
  //Utils:
  

  //SGS:
  public IAsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<InstanceUml>> getAsmListAsmInstancesFull() {
    return asmListAsmInstancesFull;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<InstanceUml>> getSrvPersistListAsmInstancesFull() {
    return srvPersistListAsmInstancesFull;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<InstanceUml>> getFactoryAsmInstanceFull() {
    return factoryAsmInstanceFull;
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
