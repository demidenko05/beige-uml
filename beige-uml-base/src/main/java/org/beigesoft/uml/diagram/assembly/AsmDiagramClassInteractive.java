package org.beigesoft.uml.diagram.assembly;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
import org.beigesoft.uml.diagram.pojo.DiagramClass;
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
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.UtilsRectangleRelationship;
import org.beigesoft.uml.service.UtilsUml;
import org.beigesoft.uml.service.comparator.ComparatorAsmListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistListElementsUml;
import org.beigesoft.uml.ui.IGuiMainUml;

public class AsmDiagramClassInteractive<DRI, SD extends ISettingsDraw, IMG, PRI, DLI> 
    extends AsmDiagramUmlInteractive<DiagramClass, DRI, SD, IMG, PRI, DLI, 
    CommentUml, IAsmElementUmlInteractive<CommentUml, DRI, SD, PRI>, 
    TextUml, IAsmElementUmlInteractive<TextUml, DRI, SD, PRI>>
    implements IAsmDiagramClassInteractive<DiagramClass, DRI, SD, IMG, PRI, ClassUml> {

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

  public AsmDiagramClassInteractive(
      DiagramClass diagramUml,
      IGuiMainUml<DRI, SD, IMG, PRI, DLI> guiApp,
      ISrvPersistAsmDiagramUml<DiagramClass, PRI> persistDiagramUmlSrv,
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
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipPolyClass) {
    super(diagramUml, guiApp, persistDiagramUmlSrv, persistListCommentsUmlSrv,
        persistListTextsUmlSrv, factoryCommentUml, factoryTextUml, srvPersistXmlListFrames, factoryFrame, srvPersistXmlListRectangles, factoryAsmRectangle, srvPersistXmlListLines, factoryAsmLine);
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
  public void notifyModelChanged() {
    getDiagramUml().setIsAbleToChangeByDoclet(false);
    super.notifyModelChanged();
  }

  @Override
  public boolean tryToDragging(int x, int y) {
    if(super.tryToDragging(x, y)) {
      getDiagramUml().setIsAbleToChangeByDoclet(false);
      return true;
    }
    return false;
  }

  @Override
  public void deleteSelectedElement() {
    getDiagramUml().setIsAbleToChangeByDoclet(false);
    super.deleteSelectedElement();
  }

  @Override
  public void createTextUmlAt(int x, int y) {
    getDiagramUml().setIsAbleToChangeByDoclet(false);
    super.createTextUmlAt(x, y);
  }

  @Override
  public void createCommentUmlAt(int x, int y) {
    getDiagramUml().setIsAbleToChangeByDoclet(false);
    super.createCommentUmlAt(x, y);
  }

  @Override
  public void addClassUmlForClass(Class<?> clazz) {
    EClassKind classKind = clazz.isEnum() ? EClassKind.ENUM : 
      (clazz.isInterface() ? EClassKind.INTERFACE : EClassKind.CLASS);
    IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI> 
      asmClassUml = factoryAsmClassFull.createAsmElementUml();
    asmClassUml.getElementUml().getShape().setClassKind(classKind);
    UtilsUml.fillClassUml(asmClassUml.getElementUml(), clazz, getHolderApp().getAsmProjectUml().getProjectUml().getIsUseGenericForGenerateFromJavaClass());
    asmClassUml.getElementUml().getShape().setPointStart(new Point2D(1, 1));
    asmListAsmClassesFull.addElementUml(asmClassUml);
    getGuiApp().getPaneDrawing().getSrvPaneDrawing().handleChangesIndexZ();
    setIsChanged(true);
    getDiagramUml().setIsAbleToChangeByDoclet(false);
    refreshGui();
  }

  @Override
  public void addClassesUmlForClass(Class<?> clazz) throws Exception {
    EClassKind classKind = clazz.isEnum() ? EClassKind.ENUM : 
      (clazz.isInterface() ? EClassKind.INTERFACE : EClassKind.CLASS);
    IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI> mainAsmClassUml = factoryAsmClassFull.createAsmElementUml();
    mainAsmClassUml.getElementUml().getShape().setClassKind(classKind);
    mainAsmClassUml.getElementUml().getShape().setIsMain(true);
    UtilsUml.fillClassUml(mainAsmClassUml.getElementUml(), clazz, getHolderApp().getAsmProjectUml().getProjectUml().getIsUseGenericForGenerateFromJavaClass());
    asmListAsmClassesFull.addElementUml(mainAsmClassUml);
    if(clazz.getSuperclass() != null) { 
      addRelatedClass(mainAsmClassUml.getElementUml(), clazz.getSuperclass(), ERelationshipKind.GENERALIZATION, false);
    }
    Class<?>[] interfaces = clazz.getInterfaces();
    for(Class<?> interfaceClazz: interfaces)
      addRelatedClass(mainAsmClassUml.getElementUml(), interfaceClazz, ERelationshipKind.INTERFACE_REALIZATION, false);
    Set<Class<?>> subClasses = org.beigesoft.util.UtilsMisc.getSubclassesOf(clazz, 
        new File(getHolderApp().getAsmProjectUml().getProjectUml().getJavaSourceFilePath()),
        getHolderApp().getAsmProjectUml().getClassLoader());
    if(subClasses != null) {
      for(Class<?> subClazz : subClasses) {
        addRelatedClass(mainAsmClassUml.getElementUml(), subClazz, ERelationshipKind.GENERALIZATION, true);
      }
    }
    getGuiApp().getPaneDrawing().getSrvPaneDrawing().handleChangesIndexZ();
    rearrange();
  }
  
  protected void addRelatedClass(ClassFull<ClassUml> mainAsmClassUml, Class<?> clazzRelated,
      ERelationshipKind relationKind, boolean isMainClassEnd) throws ClassNotFoundException {
    Class<?> clazz = getHolderApp().getAsmProjectUml().getClassLoader().
       loadClass(clazzRelated.getName());
    EClassKind classKind = clazz.isEnum() ? EClassKind.ENUM : 
      (clazz.isInterface() ? EClassKind.INTERFACE : EClassKind.CLASS);
    IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI> relatedAsmClassUml = factoryAsmClassFull.createAsmElementUml();
    relatedAsmClassUml.getElementUml().getShape().setClassKind(classKind);
    UtilsUml.fillClassUml(relatedAsmClassUml.getElementUml(), clazz, getHolderApp().getAsmProjectUml().getProjectUml().getIsUseGenericForGenerateFromJavaClass());
    asmListAsmClassesFull.addElementUml(relatedAsmClassUml);
    IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI> asmRelationUml = 
        factoryAsmRelationshipBinaryClass.createAsmElementUml();
    asmRelationUml.getElementUml().setKind(relationKind);
    asmRelationUml.getElementUml().getShapeRelationshipStart().setShapeFull(mainAsmClassUml);
    if(isMainClassEnd) {
      asmRelationUml.getElementUml().getShapeRelationshipStart().setEndType(ERelationshipEndType.END);
    }
    mainAsmClassUml.getRelationshipsBinary().add(new ClassRelationFull<ClassUml>(asmRelationUml.getElementUml().getShapeRelationshipStart(), asmRelationUml.getElementUml()));
    asmRelationUml.getElementUml().getShapeRelationshipEnd().setShapeFull(relatedAsmClassUml.getElementUml());
    if(!isMainClassEnd) {
      asmRelationUml.getElementUml().getShapeRelationshipEnd().setEndType(ERelationshipEndType.END);
    }
    relatedAsmClassUml.getElementUml().getRelationshipsBinary().add(new ClassRelationFull<ClassUml>(asmRelationUml.getElementUml().getShapeRelationshipEnd(), asmRelationUml.getElementUml()));
    asmListAsmRelationshipsBinaryClass.addElementUml(asmRelationUml);
  }
  
  @Override
  public void rearrange() {
    getHolderApp().getPaneDrawing().repaintForced();//to recalculate width and height of classes
    List<ClassFull<ClassUml>> classList = new ArrayList<ClassFull<ClassUml>>();
    for(IAsmElementUmlInteractive<ClassFull<ClassUml>, DRI, SD, PRI> acl : asmListAsmClassesFull.getListElementsUml()) {
      classList.add(acl.getElementUml());
    }
    UtilsUml.arrangeClassDiagram(classList, getHolderApp().getSettingsGraphicUml(), getDiagramUml().getAlgorithmAd());
    setIsChanged(true);
    getDiagramUml().setIsAbleToChangeByDoclet(false);
    refreshGui();
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
    getDiagramUml().setIsAbleToChangeByDoclet(false);
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
    return result;
  }

  @Override
  public ShapeFull<?> getTiedShapeById(UUID id) {
    return asmListAsmClassesFull.getElementUmlById(id);
  }

  @Override
  public long getVersionShapesForTie() {
    return asmListAsmClassesFull.getVersionElementsUml();
  }
  
  @Override
  public void tryToCreateRelationshipAt(ERelationshipKind relationKind, int screenX,
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
        getDiagramUml().setIsAbleToChangeByDoclet(false);
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
        getDiagramUml().setIsAbleToChangeByDoclet(false);
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
        getDiagramUml().setIsAbleToChangeByDoclet(false);
        refreshGui();
        break;
      }
    }
  }

  @Override
  public void adjustRelationsFor90Degree() throws Exception {
    for(IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI> asmRelUml : asmListAsmRelationshipsBinaryClass.getListElementsUml()) {
      if(asmRelUml.getElementUml().getSharedJoint() == null) {
        ClassRelationFull<ClassUml> relClassRelUml = new ClassRelationFull<ClassUml>(asmRelUml.getElementUml().getShapeRelationshipStart(),
            asmRelUml.getElementUml());
        if(UtilsUml.adjustAngleFor90Degree(getHolderApp().getSettingsGraphicUml(),
            relClassRelUml)) {
          setIsChanged(true);
        }
        relClassRelUml = new ClassRelationFull<ClassUml>(asmRelUml.getElementUml().getShapeRelationshipEnd(),
            asmRelUml.getElementUml());
        if(UtilsUml.adjustAngleFor90Degree(getHolderApp().getSettingsGraphicUml(),
            relClassRelUml)) {
          setIsChanged(true);
        }
      }
    }
    if(getIsChanged()) {
      getDiagramUml().setIsAbleToChangeByDoclet(false);
      refreshGui();
    }
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
}
