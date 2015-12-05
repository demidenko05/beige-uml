package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.graphics.Bitmap;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.ToolbarDiagram;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.controller.ControllerDiagramPackagePersistLightXml;
import org.beigesoft.uml.diagram.assembly.AsmDiagramPackage;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.PackageUml;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SaxDiagramPackageFiller;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlClassUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipBinary;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipBinaryVarious;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipPoly;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipSelf;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlPackage;
import org.beigesoft.uml.ui.IGuiMainUml;

public class FactoryDiagramPackage extends AFactoryDiagramGeneral {

  private final AsmDiagramPackage<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> asmDiagramPackage;
  
  private final ControllerDiagramPackagePersistLightXml<Activity> controllerDiagramPackage;
  
  private final ToolbarDiagram toolbarDiagramPackage;
  
  private final FactoryAsmClassFull factoryAsmClassFull;
  
  private final FactoryAsmRelationshipBinaryClass factoryAsmRelationshipBinaryClass;
  
  private final FactoryAsmRelationshipSelfClass factoryAsmRelationshipSelfClass;
  
  private final FactoryAsmRelationshipPolyClass factoryAsmRelationshipPolyClass;
  
  private final SrvPersistLightXmlAsmDiagramUml<DiagramUml>   srvPersistDiagramPackage;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ClassFull<ClassUml>> srvPersistListAsmClassFull;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsBinaryClass;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsSelfClass;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsPolyClass;

  private IEditor<DiagramUml> asmEditorPropertiesDiagramPackage;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<PackageUml>> srvPersistListAsmPackage;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinaryVarious> srvPersistListAsmRelationshipsBinaryVarious;

  private final FactoryAsmPackageFull factoryAsmPackageFull;
  
  private final FactoryAsmRelationshipBinaryVarious factoryAsmRelationshipBinaryVarious;
    
  public FactoryDiagramPackage(ToolbarDiagram toolbarDiagramPackage, SrvDraw srvDraw, 
      IContainerSrvsGui<Activity> containerGui, Activity activity, IGuiMainUml<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> guiMain) {
    super(srvDraw, containerGui, activity);
    this.toolbarDiagramPackage = toolbarDiagramPackage;
    factoryAsmClassFull = new FactoryAsmClassFull(srvDraw, containerGui, activity);
    factoryAsmRelationshipBinaryClass = new FactoryAsmRelationshipBinaryClass(srvDraw, containerGui, activity);
    factoryAsmRelationshipSelfClass = new FactoryAsmRelationshipSelfClass(srvDraw, containerGui, activity);
    factoryAsmRelationshipPolyClass = new FactoryAsmRelationshipPolyClass(srvDraw, containerGui, activity);
    factoryAsmPackageFull = new FactoryAsmPackageFull(srvDraw, containerGui, activity);
    factoryAsmRelationshipBinaryVarious = new FactoryAsmRelationshipBinaryVarious(srvDraw, containerGui, activity);
    SrvSaveXmlDiagramUml<DiagramUml> srvSaveXmlDiagramUml = new SrvSaveXmlDiagramUml<DiagramUml>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_PACKAGE);
    SaxDiagramPackageFiller<CanvasWithPaint, SettingsDraw, Bitmap, Activity> saxDiagramPackageFiller =
        new SaxDiagramPackageFiller<CanvasWithPaint, SettingsDraw, Bitmap, Activity>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_PACKAGE, 
            factoryAsmClassFull, factoryAsmRelationshipBinaryClass, factoryAsmRelationshipSelfClass, factoryAsmRelationshipPolyClass, 
            getFactoryAsmComment(), getFactoryAsmText(), getFactoryAsmFrame(), getFactoryAsmRectangle(), 
            getFactoryAsmLine(), factoryAsmPackageFull, factoryAsmRelationshipBinaryVarious);
    srvPersistDiagramPackage = new SrvPersistLightXmlAsmDiagramUml<DiagramUml>(srvSaveXmlDiagramUml,
        saxDiagramPackageFiller, SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_PACKAGE);
    srvPersistListAsmClassFull = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, ClassFull<ClassUml>>(factoryAsmClassFull, SrvSaveXmlClassUml.NAMEXML_CLASSUML);
    srvPersistListAsmRelationshipsBinaryClass = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(factoryAsmRelationshipBinaryClass, SrvSaveXmlRelationshipBinary.NAMEXML_RELATIONSHIPBINARY);
    srvPersistListAsmRelationshipsSelfClass = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(factoryAsmRelationshipSelfClass, SrvSaveXmlRelationshipSelf.NAMEXML_RELATIONSHIPSELF);
    srvPersistListAsmRelationshipsPolyClass = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(factoryAsmRelationshipPolyClass, SrvSaveXmlRelationshipPoly.NAMEXML_RELATIONSHIPPOLY);
    srvPersistListAsmPackage = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<PackageUml>>(factoryAsmPackageFull, SrvSaveXmlPackage.NAMEXML_PACKAGEUML);
    srvPersistListAsmRelationshipsBinaryVarious = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinaryVarious>(factoryAsmRelationshipBinaryVarious, SrvSaveXmlRelationshipBinaryVarious.NAMEXML_RELATIONSHIPBINARYVARIOUS);
    DiagramUml diagramClass = new DiagramUml();
    asmDiagramPackage = new AsmDiagramPackage<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity>(diagramClass, 
        guiMain, srvPersistDiagramPackage, getSrvPersistListAsmComments(), getSrvPersistListAsmTexts(), getFactoryAsmComment(), 
        getFactoryAsmText(), getSrvPersistListAsmFrames(), getFactoryAsmFrame(), getSrvPersistListAsmRectangles(), getFactoryAsmRectangle(), 
        getSrvPersistListAsmLines(), getFactoryAsmLine(),
        srvPersistListAsmClassFull, factoryAsmClassFull, srvPersistListAsmRelationshipsBinaryClass,
        factoryAsmRelationshipBinaryClass, srvPersistListAsmRelationshipsSelfClass, factoryAsmRelationshipSelfClass,
        srvPersistListAsmRelationshipsPolyClass, factoryAsmRelationshipPolyClass,
        srvPersistListAsmPackage, factoryAsmPackageFull, srvPersistListAsmRelationshipsBinaryVarious, factoryAsmRelationshipBinaryVarious);
    factoryAsmRelationshipBinaryClass.getFactoryEditorRelationshipBinaryClass().setContainerShapes(asmDiagramPackage);
    factoryAsmRelationshipBinaryClass.getFactoryEditorRelationshipBinaryClass().setObserverRelationshipBinaryClassUmlChanged(asmDiagramPackage);
    factoryAsmRelationshipBinaryClass.getSrvInteractiveRelationshipBinaryClass().setContainerShapesFull(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipBinaryFiller().getSaxRectangleRelationshipStartFiller().setContainerShapesFull(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipBinaryFiller().getSaxRectangleRelationshipEndFiller().setContainerShapesFull(asmDiagramPackage);
    factoryAsmRelationshipSelfClass.getFactoryEditorRelationshipSelfClass().setObserverRelationshipSelfClassUmlChanged(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipSelfFiller().getSaxRectangleRelationshipStartFiller().setContainerShapesFull(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipSelfFiller().getSaxRectangleRelationshipEndFiller().setContainerShapesFull(asmDiagramPackage);
    factoryAsmRelationshipPolyClass.getFactoryEditorRelationshipPolyClass().setObserverRelationshipPolyClassUmlChanged(asmDiagramPackage);
    factoryAsmRelationshipPolyClass.getFactoryEditorRelationshipPolyClass().getEditorShapeRelationship().setContainerChapesFull(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipPolyFiller().getSaxShapeRelationshipFiller().setContainerShapesFull(asmDiagramPackage);
    factoryAsmClassFull.getFactoryEditorClassFull().setObserverClassFullUmlChanged(asmDiagramPackage);
    getFactoryAsmRectangle().getFactoryEditorRectangle().setObserverRectangleUmlChanged(asmDiagramPackage);
    getFactoryAsmLine().getFactoryEditorLine().setObserverLineUmlChanged(asmDiagramPackage);
    getFactoryAsmText().getFactoryEditorText().setContainerShapesUmlForTie(asmDiagramPackage);
    getFactoryAsmText().getFactoryEditorText().setObserverTextUmlChanged(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxTextFiller().setContainerShapesUmlForTie(asmDiagramPackage);
    getFactoryAsmComment().getFactoryEditorComment().setObserverCommentUmlChanged(asmDiagramPackage);
    getFactoryAsmFrame().getFactoryEditorFrame().setObserverModelChanged(asmDiagramPackage);
    getFactoryAsmFrame().getFactoryEditorFrame().setContainerInteractiveElementsUml(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxFrameFullFiller().setContainerInteractiveElementsUml(asmDiagramPackage);
    factoryAsmRelationshipBinaryVarious.getFactoryEditorRelationshipBinaryVarious().setObserverRelationshipBinaryClassUmlChanged(asmDiagramPackage);
    factoryAsmRelationshipBinaryVarious.getFactoryEditorRelationshipBinaryVarious().setContainerShapes(asmDiagramPackage);
    factoryAsmRelationshipBinaryVarious.getSrvInteractiveRelationshipBinaryVarious().setContainerShapesFull(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipStartFiller().setContainerShapesFullVarious(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipEndFiller().setContainerShapesFullVarious(asmDiagramPackage);
    factoryAsmPackageFull.getFactoryEditorPackageFull().setObserverPackageFullUmlChanged(asmDiagramPackage);
    controllerDiagramPackage = new ControllerDiagramPackagePersistLightXml<Activity>(asmDiagramPackage, toolbarDiagramPackage,
        guiMain);
  }

  //SGS:
  public ControllerDiagramPackagePersistLightXml<Activity> getControllerDiagramPackage() {
    return controllerDiagramPackage;
  }

  public ToolbarDiagram getToolbarDiagramPackage() {
    return toolbarDiagramPackage;
  }

  public FactoryAsmClassFull getFactoryAsmClassFull() {
    return factoryAsmClassFull;
  }

  public FactoryAsmRelationshipBinaryClass getFactoryAsmRelationshipBinaryClass() {
    return factoryAsmRelationshipBinaryClass;
  }

  public FactoryAsmRelationshipSelfClass getFactoryAsmRelationshipSelfClass() {
    return factoryAsmRelationshipSelfClass;
  }

  public FactoryAsmRelationshipPolyClass getFactoryAsmRelationshipPolyClass() {
    return factoryAsmRelationshipPolyClass;
  }

  public SrvPersistLightXmlAsmDiagramUml<DiagramUml> getSrvPersistDiagramPackage() {
    return srvPersistDiagramPackage;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ClassFull<ClassUml>> getSrvPersistListAsmClassFull() {
    return srvPersistListAsmClassFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsBinaryClass() {
    return srvPersistListAsmRelationshipsBinaryClass;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsSelfClass() {
    return srvPersistListAsmRelationshipsSelfClass;
  }

  public AsmDiagramPackage<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> getAsmDiagramPackage() {
    return asmDiagramPackage;
  }

  public IEditor<DiagramUml> getAsmEditorPropertiesDiagramPackage() {
    return asmEditorPropertiesDiagramPackage;
  }

  public void setAsmEditorPropertiesDiagramPackage(
      IEditor<DiagramUml> asmEditorPropertiesDiagramPackage) {
    this.asmEditorPropertiesDiagramPackage = asmEditorPropertiesDiagramPackage;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsPolyClass() {
    return srvPersistListAsmRelationshipsPolyClass;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<PackageUml>> getSrvPersistListAsmPackage() {
    return srvPersistListAsmPackage;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinaryVarious> getSrvPersistListAsmRelationshipsBinaryVarious() {
    return srvPersistListAsmRelationshipsBinaryVarious;
  }

  public FactoryAsmPackageFull getFactoryAsmPackageFull() {
    return factoryAsmPackageFull;
  }

  public FactoryAsmRelationshipBinaryVarious getFactoryAsmRelationshipBinaryVarious() {
    return factoryAsmRelationshipBinaryVarious;
  }
}
