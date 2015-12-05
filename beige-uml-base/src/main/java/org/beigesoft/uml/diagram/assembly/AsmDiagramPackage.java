package org.beigesoft.uml.diagram.assembly;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.ClassRelationFull;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.container.IContainerShapesFullVariousInteractive;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.model.EJointSide;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.pojo.PackageUml;
import org.beigesoft.uml.service.UtilsRectangleRelationship;
import org.beigesoft.uml.service.comparator.ComparatorAsmListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistListElementsUml;
import org.beigesoft.uml.ui.IGuiMainUml;

public class AsmDiagramPackage<DRI, SD extends ISettingsDraw, IMG, PRI, DLI> 
    extends AsmDiagramUmlInteractive<DiagramUml, DRI, SD, IMG, PRI, DLI, 
    CommentUml, IAsmElementUmlInteractive<CommentUml, DRI, SD, PRI>, 
    TextUml, IAsmElementUmlInteractive<TextUml, DRI, SD, PRI>>
    implements IAsmDiagramPackage<DiagramUml, DRI, SD, IMG, PRI, ClassUml>, 
    IContainerShapesFullVariousInteractive<ShapeFullVarious<?>>  {

  private final IAsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<PackageUml>> asmListAsmPackagesFull;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<PackageUml>> srvPersistListAsmPackagesFull;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<PackageUml>> factoryAsmPackageFull;
  
  private final IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipBinaryVarious> asmListAsmRelationshipsBinVar;
  
  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> srvPersistListAsmRelationshipsBinVar;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> factoryAsmRelationshipBinVar;
  
  private final IAsmListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI , SD, PRI>, DRI, SD, IMG, PRI,
    ClassFull<ClassUml>> asmListAsmClassesFull;
  
  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, ClassFull<ClassUml>> srvPersistListAsmClassesFull;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI , SD, PRI>, DRI, SD, PRI,
    ClassFull<ClassUml>> factoryAsmClassFull;

  private final IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> asmListAsmRelationshipsBinaryClass;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsBinaryClass;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipBinaryClass;
    
  private IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> asmListAsmRelationshipsSelfClass;

  private ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsSelfClass;
  
  private IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipSelfClass;  

  private IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> asmListAsmRelationshipsPolyClass;
  
  private ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsPolyClass;
  
  private IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipPolyClass;  

  public AsmDiagramPackage(
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
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, ClassFull<ClassUml>> srvPersistListAsmClassesFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI , SD, PRI>, DRI, SD, PRI, ClassFull<ClassUml>> factoryAsmClassFull,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListRelationshipsBinaryClass,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipBinaryClass,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListRelationshipsSelfClass,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipSelfClass,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListRelationshipsPolyClass,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipPolyClass,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<PackageUml>> srvPersistListAsmPackagesUml,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<PackageUml>> factoryAsmPackagesUml,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> srvPersistListAsmRelationshipBinVarsUml,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinaryVarious> factoryAsmRelationshipBinVarsUml) {
    super(diagramUml, guiApp, persistDiagramUmlSrv, persistListCommentsUmlSrv,
        persistListTextsUmlSrv, factoryCommentUml, factoryTextUml, srvPersistXmlListFrames, factoryFrame, 
        srvPersistXmlListRectangles, factoryAsmRectangle, srvPersistXmlListLines, factoryAsmLine);
    this.srvPersistListAsmPackagesFull = srvPersistListAsmPackagesUml;
    this.factoryAsmPackageFull = factoryAsmPackagesUml;
    this.asmListAsmPackagesFull = new AsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<PackageUml>>(guiApp, srvPersistListAsmPackagesUml);
    asmListAsmPackagesFull.setWeight(0);
    getAssembliesListElementsUml().add(asmListAsmPackagesFull);
    this.srvPersistListAsmRelationshipsBinVar = srvPersistListAsmRelationshipBinVarsUml;
    this.factoryAsmRelationshipBinVar = factoryAsmRelationshipBinVarsUml;
    this.asmListAsmRelationshipsBinVar = new AsmListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipBinaryVarious>(guiApp, srvPersistListAsmRelationshipBinVarsUml);
    asmListAsmRelationshipsBinVar.setWeight(10);
    getAssembliesListElementsUml().add(asmListAsmRelationshipsBinVar);
    this.srvPersistListAsmClassesFull = srvPersistListAsmClassesFull;
    this.srvPersistListAsmRelationshipsBinaryClass = srvPersistListRelationshipsBinaryClass;
    this.srvPersistListAsmRelationshipsSelfClass = srvPersistListRelationshipsSelfClass;
    this.setSrvPersistListAsmRelationshipsPolyClass(srvPersistListRelationshipsPolyClass);
    this.factoryAsmClassFull = factoryAsmClassFull;
    this.factoryAsmRelationshipBinaryClass = factoryAsmRelationshipBinaryClass;
    this.factoryAsmRelationshipSelfClass = factoryAsmRelationshipSelfClass;
    this.setFactoryAsmRelationshipPolyClass(factoryAsmRelationshipPolyClass);
    asmListAsmClassesFull = new AsmListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI , SD, PRI>, DRI, SD, IMG, PRI, ClassFull<ClassUml>>(guiApp, srvPersistListAsmClassesFull);
    getAssembliesListElementsUml().add(asmListAsmClassesFull);
    asmListAsmClassesFull.setWeight(0);
    asmListAsmRelationshipsBinaryClass = new AsmListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD , PRI>, DRI, SD, IMG, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(guiApp, srvPersistListRelationshipsBinaryClass);
    getAssembliesListElementsUml().add(asmListAsmRelationshipsBinaryClass);
    asmListAsmRelationshipsBinaryClass.setWeight(1);
    asmListAsmRelationshipsSelfClass = new AsmListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD , PRI>, DRI, SD, IMG, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(guiApp, srvPersistListRelationshipsSelfClass);
    getAssembliesListElementsUml().add(asmListAsmRelationshipsSelfClass);
    asmListAsmRelationshipsSelfClass.setWeight(2);
    asmListAsmRelationshipsPolyClass = new AsmListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD , PRI>, DRI, SD, IMG, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(guiApp, srvPersistListRelationshipsPolyClass);
    getAssembliesListElementsUml().add(asmListAsmRelationshipsPolyClass);
    asmListAsmRelationshipsPolyClass.setWeight(3);
    ComparatorAsmListElementsUml comparatorAssemblyListElementsUml = new ComparatorAsmListElementsUml();
    Collections.sort(getAssembliesListElementsUml(), comparatorAssemblyListElementsUml);
  }

  @Override
  public void createClassAt(EClassKind classKind, int screenX, int screenY)
      throws Exception {
    IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI> asmClassUml = factoryAsmClassFull.createAsmElementUml();
    asmClassUml.getElementUml().getShape().setClassKind(classKind);
    asmClassUml.getElementUml().getShape().setPointStart(new Point2D(UtilsGraphMath.toRealX(getHolderApp().getSettingsGraphicUml(), screenX),
        UtilsGraphMath.toRealY(getHolderApp().getSettingsGraphicUml(), screenY)));
    makeElementSelected(asmClassUml);
    tryToAddIntoContainer(asmClassUml, screenX, screenY);
    asmListAsmClassesFull.addElementUml(asmClassUml);
    asmClassUml.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public ClassFull<ClassUml> getShapeFullById(UUID id) {
    return asmListAsmClassesFull.getElementUmlById(id);
  }

  @Override
  public ClassFull<ClassUml> getShapeAt(int screenX, int screenY) {
    ClassFull<ClassUml> result = null;
    for(IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI> asmClassFull : asmListAsmClassesFull.getListElementsUml()) {
      if(asmClassFull.isContainsScreenPoint(screenX, screenY)) {
        result = asmClassFull.getElementUml();
        break;
      }
    }
    return result;
  }


  @Override
  public Collection<ClassFull<ClassUml>> getShapesUml() {
    Set<ClassFull<ClassUml>> result = new HashSet<ClassFull<ClassUml>>();
    for(IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI> asmClassFull : asmListAsmClassesFull.getListElementsUml()) {
      result.add(asmClassFull.getElementUml());
    }
    return result;
  }

  @Override
  public long getVersionShapesUml() {
    return asmListAsmClassesFull.getVersionElementsUml();
  }

  @Override
  public Collection<ShapeFull<?>> getShapesForTie() {
    Set<ShapeFull<?>> result = new HashSet<ShapeFull<?>>();
    for(IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI> acl : asmListAsmClassesFull.getListElementsUml()) {
      result.add(acl.getElementUml());
    }
    for(IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI> acl : asmListAsmPackagesFull.getListElementsUml()) {
      result.add(acl.getElementUml());
    }
    return result;
  }

  @Override
  public ShapeFull<?> getTiedShapeById(UUID id) {
    ShapeFull<?> tiedShape = asmListAsmClassesFull.getElementUmlById(id);
    if(tiedShape == null) {
      tiedShape = asmListAsmPackagesFull.getElementUmlById(id);
    }
    return tiedShape;
  }

  @Override
  public long getVersionShapesForTie() {
    return Math.max(asmListAsmClassesFull.getVersionElementsUml(), asmListAsmPackagesFull.getVersionElementsUml());
  }
  
  @Override
  public void tryToCreateRelationshipBinaryAt(ERelationshipKind relationKind, int screenX,
      int screenY) throws Exception {
    for(IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI> asmClassUml : asmListAsmClassesFull.getListElementsUml()) {
      if(asmClassUml.isContainsScreenPoint(screenX, screenY)) {
        double realX = UtilsGraphMath.toRealX(getHolderApp().getSettingsGraphicUml(), screenX);
        double realY = UtilsGraphMath.toRealY(getHolderApp().getSettingsGraphicUml(), screenY);
        IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI> asmRelUml = 
            factoryAsmRelationshipBinaryClass.createAsmElementUml();
        asmRelUml.getElementUml().setKind(relationKind);
        asmRelUml.getElementUml().getShapeRelationshipStart().setEndType(ERelationshipEndType.END);
        asmRelUml.getElementUml().getShapeRelationshipStart().setShapeFull(asmClassUml.getElementUml());
        UtilsRectangleRelationship.setPointJointAt(asmRelUml.getElementUml().getShapeRelationshipStart(),
            getGuiApp().getSettingsGraphicUml(), screenX, screenY);
        asmRelUml.getElementUml().getShapeRelationshipEnd().getPointJoint().setX(realX + 
            getHolderApp().getSettingsGraphicUml().fromInchToCurrentMeasure(1));
        asmRelUml.getElementUml().getShapeRelationshipEnd().getPointJoint().setY(realY);
        asmClassUml.getElementUml().getRelationshipsBinary().add(new ClassRelationFull<ClassUml>(asmRelUml.getElementUml().getShapeRelationshipStart(), asmRelUml.getElementUml()));
        makeElementSelected(asmRelUml);
        tryToAddIntoContainer(asmRelUml, screenX, screenY);
        asmListAsmRelationshipsBinaryClass.addElementUml(asmRelUml);
        setIsChanged(true);
        refreshGui();
        break;
      }
    }
  }

  @Override
  public void tryToCreateRelationshipSelfAt(int screenX, int screenY) {
    for(IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI> asmClassUml : asmListAsmClassesFull.getListElementsUml()) {
      if(asmClassUml.isContainsScreenPoint(screenX, screenY)) {
        IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI> asmRelationshipSelf = factoryAsmRelationshipSelfClass.createAsmElementUml();
        asmRelationshipSelf.getElementUml().setKind(ERelationshipKind.ASSOCIATION);
        asmRelationshipSelf.getElementUml().getShapeRelationshipStart().setShapeFull(asmClassUml.getElementUml());
        asmRelationshipSelf.getElementUml().getShapeRelationshipStart().setSideJoint(EJointSide.LEFT);
        asmRelationshipSelf.getElementUml().getShapeRelationshipStart().setSideLength(asmClassUml.getElementUml().getShape().getHeight()/2);
        asmRelationshipSelf.getElementUml().getShapeRelationshipEnd().setShapeFull(asmClassUml.getElementUml());
        asmRelationshipSelf.getElementUml().getShapeRelationshipEnd().setSideJoint(EJointSide.TOP);
        asmRelationshipSelf.getElementUml().getShapeRelationshipEnd().setSideLength(asmClassUml.getElementUml().getShape().getWidth()/2);
        asmRelationshipSelf.getElementUml().getShapeRelationshipEnd().setEndType(ERelationshipEndType.END);
        asmRelationshipSelf.getElementUml().getSharedJoint().setX(asmClassUml.getElementUml().getShape().getPointStart().getX() - asmClassUml.getElementUml().getShape().getWidth()/4);
        asmRelationshipSelf.getElementUml().getSharedJoint().setY(asmClassUml.getElementUml().getShape().getPointStart().getY() - asmClassUml.getElementUml().getShape().getHeight()/4);
        UtilsRectangleRelationship.evalPointsJointAndShared(asmRelationshipSelf.getElementUml());
        asmClassUml.getElementUml().getRelationshipsSelf().add(asmRelationshipSelf.getElementUml());
        makeElementSelected(asmRelationshipSelf);
        tryToAddIntoContainer(asmRelationshipSelf, screenX, screenY);
        asmListAsmRelationshipsSelfClass.addElementUml(asmRelationshipSelf);
        setIsChanged(true);
        refreshGui();
        break;
      }
    }
  }


  @Override
  public void tryToCreateRelationshipPolyAt(int screenX, int screenY) {
    for(IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI> asmClassUml : asmListAsmClassesFull.getListElementsUml()) {
      if(asmClassUml.isContainsScreenPoint(screenX, screenY)) {
        IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI> asmRelationshipPoly = factoryAsmRelationshipPolyClass.createAsmElementUml();
        asmRelationshipPoly.getElementUml().setKind(ERelationshipKind.ASSOCIATION);
        RectangleRelationship<ClassFull<ClassUml>, ClassUml> rectangleRelationship = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
        rectangleRelationship.setShapeFull(asmClassUml.getElementUml());
        rectangleRelationship.setSideJoint(EJointSide.BOTTOM);
        rectangleRelationship.setSideLength(asmClassUml.getElementUml().getShape().getWidth()/2);
        rectangleRelationship.setEndType(ERelationshipEndType.END);
        asmRelationshipPoly.getElementUml().getShapesRelationship().add(rectangleRelationship);
        asmRelationshipPoly.getElementUml().getSharedJoint().setTypeJoint(EJoint2DType.BUS_X);
        asmRelationshipPoly.getElementUml().getSharedJoint().setX(asmClassUml.getElementUml().getShape().getPointStart().getX() + asmClassUml.getElementUml().getShape().getWidth()/2);
        asmRelationshipPoly.getElementUml().getSharedJoint().setY(asmClassUml.getElementUml().getShape().getPointStart().getY() + asmClassUml.getElementUml().getShape().getHeight()*1.5);
        asmClassUml.getElementUml().getClassRelationsPoly().add(asmRelationshipPoly.getElementUml().getShapesRelationship().get(0));
        UtilsRectangleRelationship.evalPointJoint(asmRelationshipPoly.getElementUml().getShapesRelationship().get(0));
        makeElementSelected(asmRelationshipPoly);
        tryToAddIntoContainer(asmRelationshipPoly, screenX, screenY);
        asmListAsmRelationshipsPolyClass.addElementUml(asmRelationshipPoly);
        setIsChanged(true);
        asmRelationshipPoly.startEdit();
        refreshGui();
        break;
      }
    }
  }

  @Override
  public void createPackageAt(int screenX, int screenY) throws Exception {
    IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI> asmPackage = getFactoryAsmPackageFull().createAsmElementUml();
    asmPackage.getElementUml().getShape().getPointStart().setX(UtilsGraphMath.toRealX(getGuiApp().getSettingsGraphicUml(), screenX));
    asmPackage.getElementUml().getShape().getPointStart().setY(UtilsGraphMath.toRealY(getGuiApp().getSettingsGraphicUml(), screenY));
    makeElementSelected(asmPackage);
    tryToAddIntoContainer(asmPackage, screenX, screenY);
    asmListAsmPackagesFull.addElementUml(asmPackage);
    asmPackage.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public boolean tryCreatePackageImportAt(int screenX, int screenY) {
    return tryCreatePackageRelationshipAt(screenX, screenY, ERelationshipKind.PACKAGE_IMPORT, "«import»");
  }

  @Override
  public boolean tryCreatePackageAccessAt(int screenX, int screenY) {
    return tryCreatePackageRelationshipAt(screenX, screenY, ERelationshipKind.PACKAGE_ACCESS, "«access»");
  }

  @Override
  public boolean tryCreatePackageMergeAt(int screenX, int screenY) {
    return tryCreatePackageRelationshipAt(screenX, screenY, ERelationshipKind.PACKAGE_MERGE, "«merge»");
  }

  @Override
  public ShapeFullVarious<?> getShapeFullVarById(UUID id) {
    ShapeFullVarious<?> shape = asmListAsmPackagesFull.getElementUmlById(id);
    return shape;
  }

  @Override
  public ShapeFullVarious<?> getShapeFullAt(int screenX, int screenY) {
    for(IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI> asmPackageUml : asmListAsmPackagesFull.getListElementsUml()) {
      if(asmPackageUml.isContainsScreenPoint(screenX, screenY)) {
        return asmPackageUml.getElementUml();
      }
    }
    return null;
  }

  @Override
  public Collection<ShapeFullVarious<?>> getShapesFull() {
    Set<ShapeFullVarious<?>> result = new HashSet<ShapeFullVarious<?>>();
    for(IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI> asmActorUml : asmListAsmPackagesFull.getListElementsUml()) {
      result.add(asmActorUml.getElementUml());
    }
    return result;
  }

  @Override
  public long getVersionShapesFull() {
    return asmListAsmPackagesFull.getVersionElementsUml();
  }
  
  //Utils:
  
  public boolean tryCreatePackageRelationshipAt(int screenX, int screenY,
      ERelationshipKind relationshipKind, String kind) {
    ShapeFullVarious<?> shapeVarFull = getShapeFullAt(screenX, screenY);
    if(shapeVarFull != null) {
      double realX = UtilsGraphMath.toRealX(getHolderApp().getSettingsGraphicUml(), screenX);
      double realY = UtilsGraphMath.toRealY(getHolderApp().getSettingsGraphicUml(), screenY);
      IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, PRI> relBinVar = 
          factoryAsmRelationshipBinVar.createAsmElementUml();
      relBinVar.getElementUml().setKind(relationshipKind);
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
      IAsmElementUmlInteractive<TextUml, DRI, SD, PRI> asmText = getFactoryAsmText().createAsmElementUml();
      asmText.getElementUml().setItsText(kind);
      asmText.getElementUml().getPointStart().setX(shapeVarFull.getShape().getPointStart().getX() +
          shapeVarFull.getShape().getWidth() +
          getHolderApp().getSettingsGraphicUml().fromInchToCurrentMeasure(1));
      asmText.getElementUml().getPointStart().setY(realY);
      tryToAddIntoContainer(asmText, screenX, screenY);
      getAsmListAsmTexts().addElementUml(asmText);
      setIsChanged(true);
      refreshGui();
      return true;
    }
    return false;
  }


  //SGS:
  public IAsmListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ClassFull<ClassUml>> getAsmListAsmClassesFull() {
    return asmListAsmClassesFull;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getAsmListAsmRelationshipsSelfClass() {
    return asmListAsmRelationshipsSelfClass;
  }

  public void setAsmListAsmRelationshipsSelfClass(
      IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> asmListAsmRelationshipsSelfClass) {
    this.asmListAsmRelationshipsSelfClass = asmListAsmRelationshipsSelfClass;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsSelfClass() {
    return srvPersistListAsmRelationshipsSelfClass;
  }

  public void setSrvPersistListAsmRelationshipsSelfClass(
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsSelfClass) {
    this.srvPersistListAsmRelationshipsSelfClass = srvPersistListAsmRelationshipsSelfClass;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getFactoryAsmRelationshipSelfClass() {
    return factoryAsmRelationshipSelfClass;
  }

  public void setFactoryAsmRelationshipSelfClass(
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipSelfClass) {
    this.factoryAsmRelationshipSelfClass = factoryAsmRelationshipSelfClass;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getAsmListAsmRelationshipsPolyClass() {
    return asmListAsmRelationshipsPolyClass;
  }

  public void setAsmListAsmRelationshipsPolyClass(
      IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> asmListAsmRelationshipsPolyClass) {
    this.asmListAsmRelationshipsPolyClass = asmListAsmRelationshipsPolyClass;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsPolyClass() {
    return srvPersistListAsmRelationshipsPolyClass;
  }

  public void setSrvPersistListAsmRelationshipsPolyClass(
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsPolyClass) {
    this.srvPersistListAsmRelationshipsPolyClass = srvPersistListAsmRelationshipsPolyClass;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getFactoryAsmRelationshipPolyClass() {
    return factoryAsmRelationshipPolyClass;
  }

  public void setFactoryAsmRelationshipPolyClass(
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipPolyClass) {
    this.factoryAsmRelationshipPolyClass = factoryAsmRelationshipPolyClass;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, ClassFull<ClassUml>> getSrvPersistListAsmClassesFull() {
    return srvPersistListAsmClassesFull;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, ClassFull<ClassUml>> getFactoryAsmClassFull() {
    return factoryAsmClassFull;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getAsmListAsmRelationshipsBinaryClass() {
    return asmListAsmRelationshipsBinaryClass;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsBinaryClass() {
    return srvPersistListAsmRelationshipsBinaryClass;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getFactoryAsmRelationshipBinaryClass() {
    return factoryAsmRelationshipBinaryClass;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ShapeFullVarious<PackageUml>> getAsmListAsmPackagesFull() {
    return asmListAsmPackagesFull;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<PackageUml>> getSrvPersistListAsmPackagesFull() {
    return srvPersistListAsmPackagesFull;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, DRI, SD, PRI>, DRI, SD, PRI, ShapeFullVarious<PackageUml>> getFactoryAsmPackageFull() {
    return factoryAsmPackageFull;
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
