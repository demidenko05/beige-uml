package org.beigesoft.uml.diagram.assembly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.container.IContainerAppUml;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.diagram.pojo.DiagramClass;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.UtilsUml;
import org.beigesoft.uml.service.comparator.ComparatorAsmListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistListElementsUml;

public class AsmDiagramClass<DRI, SD extends ISettingsDraw, IMG, PRI> 
    extends AsmDiagramUml<DiagramClass, IAsmElementUml<?, DRI, ?, PRI>, DRI, SD, IMG, PRI> 
    implements IAsmDiagramClass<DiagramClass, IAsmElementUml<?, DRI, ?, PRI>, DRI, SD, IMG, PRI, ClassUml>,
    IContainerShapesForTie {

  private final IAsmListElementsUml<IAsmElementUml<ClassFull<ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ClassFull<ClassUml>> assemblyListClassesUml;
  
  private final IAsmListElementsUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, 
  DRI, SD, IMG, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> assemblyListRelationsUml;

  private final IAsmListElementsUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> asmListAsmRelationshipsSelfClass;

  private final ISrvPersistListElementsUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsSelfClass;
  
  private final IFactoryAsmElementUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipSelfClass;  

  private final IAsmListElementsUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> asmListAsmRelationshipsPolyClass;
  
  private final ISrvPersistListElementsUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsPolyClass;
  
  private final IFactoryAsmElementUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipPolyClass;  

  private final ISrvPersistListElementsUml<IAsmElementUml<CommentUml, DRI, SD, PRI>, DRI, SD, PRI, CommentUml> srvPersistListAsmComments;

  private final IAsmListElementsUml<IAsmElementUml<CommentUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, CommentUml> assemblyListCommentsUml;

  private final ISrvPersistListElementsUml<IAsmElementUml<TextUml, DRI, SD, PRI>, DRI, SD, PRI, TextUml> srvPersistListAsmTexts;
  
  private final IAsmListElementsUml<IAsmElementUml<TextUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, TextUml> assemblyListTextsUml;
  
  private final IFactoryAsmElementUml<IAsmElementUml<CommentUml, DRI, SD, PRI>, DRI, SD, PRI, CommentUml> factoryCommentUml;

  private final IFactoryAsmElementUml<IAsmElementUml<TextUml, DRI, SD, PRI>, DRI, SD, PRI, TextUml> factoryTextUml;

  private final ISrvPersistListElementsUml<IAsmElementUml<FrameUml, DRI, SD, PRI>, DRI, SD, PRI, FrameUml> srvPersistListAsmFrames;

  private final IFactoryAsmElementUml<IAsmElementUml<FrameUml, DRI, SD, PRI>, DRI, SD, PRI, FrameUml> factoryAsmFrame;

  private final IAsmListElementsUml<IAsmElementUml<FrameUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, FrameUml> asmListAsmFrames;

  private final IAsmListElementsUml<IAsmElementUml<RectangleUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, RectangleUml> asmListAsmRectangles;

  private final ISrvPersistListElementsUml<IAsmElementUml<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> srvPersistListAsmRectangles;

  private final IFactoryAsmElementUml<IAsmElementUml<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> factoryAsmRectangle;

  private final IAsmListElementsUml<IAsmElementUml<LineUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, LineUml> asmListAsmLines;

  private final ISrvPersistListElementsUml<IAsmElementUml<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> srvPersistListAsmLines;

  private final IFactoryAsmElementUml<IAsmElementUml<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> factoryAsmLine;

  public AsmDiagramClass(DiagramClass diagramUml, IContainerAppUml<DRI, SD, IMG> holderApp,
      ISrvPersistAsmDiagramUml<DiagramClass, PRI> persistDiagramUmlSrv, 
      ISrvPersistListElementsUml<IAsmElementUml<ClassFull<ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, 
      ClassFull<ClassUml>> persistListClassesUmlSrv, 
      ISrvPersistListElementsUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>,
      DRI, SD, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> persistListRelationsUmlSrv,
      ISrvPersistListElementsUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListRelationshipsSelfClass,
      IFactoryAsmElementUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipSelfClass,
      ISrvPersistListElementsUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListRelationshipsPolyClass,
      IFactoryAsmElementUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> factoryAsmRelationshipPolyClass,
      ISrvPersistListElementsUml<IAsmElementUml<CommentUml, DRI, SD, PRI>, DRI, SD, PRI, CommentUml> srvPersistListAsmComments,
      IFactoryAsmElementUml<IAsmElementUml<CommentUml, DRI, SD, PRI>, DRI, SD, PRI, CommentUml> factoryCommentUml,
      ISrvPersistListElementsUml<IAsmElementUml<TextUml, DRI, SD, PRI>, DRI, SD, PRI, TextUml> srvPersistListAsmTexts,
      IFactoryAsmElementUml<IAsmElementUml<TextUml, DRI, SD, PRI>, DRI, SD, PRI, TextUml> factoryTextUml,
      ISrvPersistListElementsUml<IAsmElementUml<FrameUml, DRI, SD, PRI>, DRI, SD, PRI, FrameUml> srvPersistListAsmFrames,
      IFactoryAsmElementUml<IAsmElementUml<FrameUml, DRI, SD, PRI>, DRI, SD, PRI, FrameUml> factoryAsmFrame,
      ISrvPersistListElementsUml<IAsmElementUml<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> srvPersistListAsmRectangles,
      IFactoryAsmElementUml<IAsmElementUml<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> factoryAsmRectangle,
      ISrvPersistListElementsUml<IAsmElementUml<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> srvPersistListAsmLines,
      IFactoryAsmElementUml<IAsmElementUml<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> factoryAsmLine) {
    super(diagramUml, holderApp, persistDiagramUmlSrv);
    this.srvPersistListAsmRectangles = srvPersistListAsmRectangles;
    this.factoryAsmRectangle = factoryAsmRectangle;
    this.srvPersistListAsmFrames = srvPersistListAsmFrames;
    this.factoryAsmFrame = factoryAsmFrame;
    this.factoryAsmLine = factoryAsmLine;
    this.srvPersistListAsmLines = srvPersistListAsmLines;
    assemblyListClassesUml = new AsmListElementsUml<IAsmElementUml<ClassFull<ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, 
        ClassFull<ClassUml>>(holderApp, persistListClassesUmlSrv);
    assemblyListClassesUml.setWeight(1);
    getAssembliesListElementsUml().add(assemblyListClassesUml);
    assemblyListRelationsUml = new AsmListElementsUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, 
        DRI, SD, IMG, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(holderApp, persistListRelationsUmlSrv);
    getAssembliesListElementsUml().add(assemblyListRelationsUml);
    assemblyListRelationsUml.setWeight(2);
    this.srvPersistListAsmRelationshipsSelfClass = srvPersistListRelationshipsSelfClass;
    this.srvPersistListAsmRelationshipsPolyClass = srvPersistListRelationshipsPolyClass;
    this.factoryAsmRelationshipSelfClass = factoryAsmRelationshipSelfClass;
    this.factoryAsmRelationshipPolyClass = factoryAsmRelationshipPolyClass;
    this.srvPersistListAsmComments = srvPersistListAsmComments;
    this.factoryCommentUml = factoryCommentUml;
    this.srvPersistListAsmTexts = srvPersistListAsmTexts;
    this.factoryTextUml = factoryTextUml;
    asmListAsmRelationshipsSelfClass = new AsmListElementsUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD , PRI>, DRI, SD, IMG, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(holderApp, srvPersistListRelationshipsSelfClass);
    getAssembliesListElementsUml().add(asmListAsmRelationshipsSelfClass);
    asmListAsmRelationshipsSelfClass.setWeight(3);
    asmListAsmRelationshipsPolyClass = new AsmListElementsUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD , PRI>, DRI, SD, IMG, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(holderApp, srvPersistListRelationshipsPolyClass);
    getAssembliesListElementsUml().add(asmListAsmRelationshipsPolyClass);
    asmListAsmRelationshipsPolyClass.setWeight(4);
    this.assemblyListCommentsUml = new AsmListElementsUml<IAsmElementUml<CommentUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, CommentUml>(holderApp, srvPersistListAsmComments);
    getAssembliesListElementsUml().add(assemblyListCommentsUml);
    assemblyListCommentsUml.setWeight(99);
    this.assemblyListTextsUml = new AsmListElementsUml<IAsmElementUml<TextUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, TextUml>(holderApp, srvPersistListAsmTexts);
    getAssembliesListElementsUml().add(assemblyListTextsUml);
    assemblyListTextsUml.setWeight(100);
    asmListAsmFrames = new AsmListElementsUml<IAsmElementUml<FrameUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, FrameUml>(holderApp, srvPersistListAsmFrames);
    getAssembliesListElementsUml().add(asmListAsmFrames);
    asmListAsmFrames.setWeight(101);
    asmListAsmRectangles = new AsmListElementsUml<IAsmElementUml<RectangleUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, RectangleUml>(holderApp, srvPersistListAsmRectangles);
    getAssembliesListElementsUml().add(asmListAsmRectangles);
    asmListAsmRectangles.setWeight(102);
    asmListAsmLines = new AsmListElementsUml<IAsmElementUml<LineUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, LineUml>(holderApp, srvPersistListAsmLines);
    getAssembliesListElementsUml().add(asmListAsmLines);
    asmListAsmLines.setWeight(103);
    ComparatorAsmListElementsUml comparatorAssemblyListElementsUml = new ComparatorAsmListElementsUml();
    Collections.sort(getAssembliesListElementsUml(), comparatorAssemblyListElementsUml);
  }

  public ShapeUml getTiedShapeUmlById(UUID id) {
    ClassFull<ClassUml>
      classUmlWithRelations = assemblyListClassesUml.getElementUmlById(id);
    ShapeUml result = classUmlWithRelations.getShape();
    return result;
  }

  @Override
  public void rearrange() {
    getHolderApp().getPaneDrawing().repaintForced();//to recalculate width and height of classes
    List<ClassFull<ClassUml>> classList = 
        new ArrayList<ClassFull<ClassUml>>();
    for(IAsmElementUml<ClassFull<ClassUml>, DRI, SD, PRI> acl : assemblyListClassesUml.getListElementsUml()) {
      classList.add(acl.getElementUml());
    }
    UtilsUml.arrangeClassDiagram(classList, getHolderApp().getSettingsGraphicUml(), getDiagramUml().getAlgorithmAd());
  }

  @Override
  public ClassFull<ClassUml> getShapeFullById(UUID id) {
    ClassFull<ClassUml> asmClasRel = assemblyListClassesUml.getElementUmlById(id);
    return asmClasRel;
  }

  @Override
  public ShapeFull<?> getTiedShapeById(UUID id) {
    ClassFull<ClassUml> asmClassFull = assemblyListClassesUml.getElementUmlById(id);
    if(asmClassFull == null) {
      throw new RuntimeException("There is no asm class full for id " + id);
    }
    return asmClassFull;
  }

  @Override
  public Collection<ShapeFull<?>> getShapesForTie() {
    Set<ShapeFull<?>> result = new HashSet<ShapeFull<?>>();
    for(IAsmElementUml<ClassFull<ClassUml>, DRI, SD, PRI> acl : assemblyListClassesUml.getListElementsUml()) {
      result.add(acl.getElementUml());
    }
    return result;
  }

  @Override
  public long getVersionShapesForTie() {
    return assemblyListClassesUml.getVersionElementsUml();
  }

  //SGS:
  public IAsmListElementsUml<IAsmElementUml<ClassFull<ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ClassFull<ClassUml>> 
      getAsmListAsmClassesFull() {
    return assemblyListClassesUml;
  }

  public IAsmListElementsUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getAsmListAsmRelationshipsBinaryClass() {
    return assemblyListRelationsUml;
  }

  public IAsmListElementsUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getAsmListAsmRelationshipsSelfClass() {
    return asmListAsmRelationshipsSelfClass;
  }

  public ISrvPersistListElementsUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsSelfClass() {
    return srvPersistListAsmRelationshipsSelfClass;
  }

  public IFactoryAsmElementUml<IAsmElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getFactoryAsmRelationshipSelfClass() {
    return factoryAsmRelationshipSelfClass;
  }

  public IAsmListElementsUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getAsmListAsmRelationshipsPolyClass() {
    return asmListAsmRelationshipsPolyClass;
  }

  public ISrvPersistListElementsUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsPolyClass() {
    return srvPersistListAsmRelationshipsPolyClass;
  }

  public IFactoryAsmElementUml<IAsmElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, PRI, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getFactoryAsmRelationshipPolyClass() {
    return factoryAsmRelationshipPolyClass;
  }

  public IAsmListElementsUml<IAsmElementUml<ClassFull<ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ClassFull<ClassUml>> getAssemblyListClassesUml() {
    return assemblyListClassesUml;
  }

  public IAsmListElementsUml<IAsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getAssemblyListRelationsUml() {
    return assemblyListRelationsUml;
  }

  public ISrvPersistListElementsUml<IAsmElementUml<CommentUml, DRI, SD, PRI>, DRI, SD, PRI, CommentUml> getSrvPersistListAsmComments() {
    return srvPersistListAsmComments;
  }

  public ISrvPersistListElementsUml<IAsmElementUml<TextUml, DRI, SD, PRI>, DRI, SD, PRI, TextUml> getSrvPersistListAsmTexts() {
    return srvPersistListAsmTexts;
  }

  public IAsmListElementsUml<IAsmElementUml<CommentUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, CommentUml> getAssemblyListCommentsUml() {
    return assemblyListCommentsUml;
  }

  public IAsmListElementsUml<IAsmElementUml<TextUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, TextUml> getAssemblyListTextsUml() {
    return assemblyListTextsUml;
  }

  public IFactoryAsmElementUml<IAsmElementUml<CommentUml, DRI, SD, PRI>, DRI, SD, PRI, CommentUml> getFactoryCommentUml() {
    return factoryCommentUml;
  }

  public IFactoryAsmElementUml<IAsmElementUml<TextUml, DRI, SD, PRI>, DRI, SD, PRI, TextUml> getFactoryTextUml() {
    return factoryTextUml;
  }

  public IAsmListElementsUml<IAsmElementUml<FrameUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, FrameUml> getAsmListFrames() {
    return asmListAsmFrames;
  }

  public ISrvPersistListElementsUml<IAsmElementUml<FrameUml, DRI, SD, PRI>, DRI, SD, PRI, FrameUml> getSrvPersistListAsmFrames() {
    return srvPersistListAsmFrames;
  }

  public IFactoryAsmElementUml<IAsmElementUml<FrameUml, DRI, SD, PRI>, DRI, SD, PRI, FrameUml> getFactoryAsmFrame() {
    return factoryAsmFrame;
  }

  public IAsmListElementsUml<IAsmElementUml<RectangleUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, RectangleUml> getAsmListRectangles() {
    return asmListAsmRectangles;
  }

  public ISrvPersistListElementsUml<IAsmElementUml<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> getSrvPersistListAsmRectangles() {
    return srvPersistListAsmRectangles;
  }

  public IFactoryAsmElementUml<IAsmElementUml<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> getFactoryAsmRectangle() {
    return factoryAsmRectangle;
  }

  public IAsmListElementsUml<IAsmElementUml<LineUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, LineUml> getAsmListAsmLines() {
    return asmListAsmLines;
  }

  public ISrvPersistListElementsUml<IAsmElementUml<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> getSrvPersistListAsmLines() {
    return srvPersistListAsmLines;
  }

  public IFactoryAsmElementUml<IAsmElementUml<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> getFactoryAsmLine() {
    return factoryAsmLine;
  }
}
