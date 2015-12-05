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
import org.beigesoft.uml.controller.ControllerDiagramClassPersistLightXml;
import org.beigesoft.uml.diagram.assembly.AsmDiagramClassInteractive;
import org.beigesoft.uml.diagram.pojo.DiagramClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SaxDiagramClassInteractiveFiller;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlClassUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramClass;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipBinary;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipPoly;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipSelf;
import org.beigesoft.uml.ui.IGuiMainUml;

public class FactoryDiagramClass extends AFactoryDiagramGeneral {

  private final AsmDiagramClassInteractive<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> asmDiagramClass;
  
  private final ControllerDiagramClassPersistLightXml<Activity> controllerDiagramClass;
  
  private final ToolbarDiagram toolbarDiagramClass;
  
  private final FactoryAsmClassFull factoryAsmClassFull;
  
  private final FactoryAsmRelationshipBinaryClass factoryAsmRelationshipBinaryClass;
  
  private final FactoryAsmRelationshipSelfClass factoryAsmRelationshipSelfClass;
  
  private final FactoryAsmRelationshipPolyClass factoryAsmRelationshipPolyClass;
  
  private final SrvPersistLightXmlAsmDiagramUml<DiagramClass>   srvPersistDiagramClass;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ClassFull<ClassUml>> srvPersistListAsmClassFull;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsBinaryClass;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsSelfClass;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsPolyClass;

  private IEditor<DiagramClass> asmEditorPropertiesDiagramClass;

  public FactoryDiagramClass(ToolbarDiagram toolbarDiagramClass, SrvDraw srvDraw, 
      IContainerSrvsGui<Activity> containerGui, Activity activity, IGuiMainUml<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> guiMain) {
    super(srvDraw, containerGui, activity);
    this.toolbarDiagramClass = toolbarDiagramClass;
    factoryAsmClassFull = new FactoryAsmClassFull(srvDraw, containerGui, activity);
    factoryAsmRelationshipBinaryClass = new FactoryAsmRelationshipBinaryClass(srvDraw, containerGui, activity);
    factoryAsmRelationshipSelfClass = new FactoryAsmRelationshipSelfClass(srvDraw, containerGui, activity);
    factoryAsmRelationshipPolyClass = new FactoryAsmRelationshipPolyClass(srvDraw, containerGui, activity);
    SrvSaveXmlDiagramClass<DiagramClass> srvSaveXmlDiagramUml = new SrvSaveXmlDiagramClass<DiagramClass>(SrvSaveXmlDiagramClass.NAMEXML_DIAGRAMCLASS);
    SaxDiagramClassInteractiveFiller<CanvasWithPaint, SettingsDraw, Bitmap, Activity> saxDiagramClassFiller =
        new SaxDiagramClassInteractiveFiller<CanvasWithPaint, SettingsDraw, Bitmap, Activity>(SrvSaveXmlDiagramClass.NAMEXML_DIAGRAMCLASS, 
            factoryAsmClassFull, factoryAsmRelationshipBinaryClass, factoryAsmRelationshipSelfClass, factoryAsmRelationshipPolyClass, getFactoryAsmComment(), getFactoryAsmText(), 
            getFactoryAsmFrame(), getFactoryAsmRectangle(), getFactoryAsmLine());
    srvPersistDiagramClass = new SrvPersistLightXmlAsmDiagramUml<DiagramClass>(srvSaveXmlDiagramUml,
        saxDiagramClassFiller, SrvSaveXmlDiagramClass.DIAGRAM_FILE_EXTENSION);
    srvPersistListAsmClassFull = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, ClassFull<ClassUml>>(factoryAsmClassFull, SrvSaveXmlClassUml.NAMEXML_CLASSUML);
    srvPersistListAsmRelationshipsBinaryClass = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(factoryAsmRelationshipBinaryClass, SrvSaveXmlRelationshipBinary.NAMEXML_RELATIONSHIPBINARY);
    srvPersistListAsmRelationshipsSelfClass = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(factoryAsmRelationshipSelfClass, SrvSaveXmlRelationshipSelf.NAMEXML_RELATIONSHIPSELF);
    srvPersistListAsmRelationshipsPolyClass = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(factoryAsmRelationshipPolyClass, SrvSaveXmlRelationshipPoly.NAMEXML_RELATIONSHIPPOLY);
    DiagramClass diagramClass = new DiagramClass();
    asmDiagramClass = new AsmDiagramClassInteractive<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity>(diagramClass, 
        guiMain, srvPersistDiagramClass, getSrvPersistListAsmComments(), getSrvPersistListAsmTexts(), getFactoryAsmComment(), 
        getFactoryAsmText(), getSrvPersistListAsmFrames(), getFactoryAsmFrame(), getSrvPersistListAsmRectangles(), getFactoryAsmRectangle(), 
        getSrvPersistListAsmLines(), getFactoryAsmLine(), srvPersistListAsmClassFull, factoryAsmClassFull, srvPersistListAsmRelationshipsBinaryClass,
        factoryAsmRelationshipBinaryClass, srvPersistListAsmRelationshipsSelfClass, factoryAsmRelationshipSelfClass,
        srvPersistListAsmRelationshipsPolyClass, factoryAsmRelationshipPolyClass);
    factoryAsmRelationshipBinaryClass.getFactoryEditorRelationshipBinaryClass().setContainerShapes(asmDiagramClass);
    factoryAsmRelationshipBinaryClass.getFactoryEditorRelationshipBinaryClass().setObserverRelationshipBinaryClassUmlChanged(asmDiagramClass);
    factoryAsmRelationshipBinaryClass.getSrvInteractiveRelationshipBinaryClass().setContainerShapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipBinaryFiller().getSaxRectangleRelationshipStartFiller().setContainerShapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipBinaryFiller().getSaxRectangleRelationshipEndFiller().setContainerShapesFull(asmDiagramClass);
    factoryAsmRelationshipSelfClass.getFactoryEditorRelationshipSelfClass().setObserverRelationshipSelfClassUmlChanged(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipSelfFiller().getSaxRectangleRelationshipStartFiller().setContainerShapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipSelfFiller().getSaxRectangleRelationshipEndFiller().setContainerShapesFull(asmDiagramClass);
    factoryAsmRelationshipPolyClass.getFactoryEditorRelationshipPolyClass().setObserverRelationshipPolyClassUmlChanged(asmDiagramClass);
    factoryAsmRelationshipPolyClass.getFactoryEditorRelationshipPolyClass().getEditorShapeRelationship().setContainerChapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipPolyFiller().getSaxShapeRelationshipFiller().setContainerShapesFull(asmDiagramClass);
    factoryAsmClassFull.getFactoryEditorClassFull().setObserverClassFullUmlChanged(asmDiagramClass);
    getFactoryAsmRectangle().getFactoryEditorRectangle().setObserverRectangleUmlChanged(asmDiagramClass);
    getFactoryAsmLine().getFactoryEditorLine().setObserverLineUmlChanged(asmDiagramClass);
    getFactoryAsmText().getFactoryEditorText().setContainerShapesUmlForTie(asmDiagramClass);
    getFactoryAsmText().getFactoryEditorText().setObserverTextUmlChanged(asmDiagramClass);
    saxDiagramClassFiller.getSaxTextFiller().setContainerShapesUmlForTie(asmDiagramClass);
    getFactoryAsmComment().getFactoryEditorComment().setObserverCommentUmlChanged(asmDiagramClass);
    getFactoryAsmFrame().getFactoryEditorFrame().setObserverModelChanged(asmDiagramClass);
    getFactoryAsmFrame().getFactoryEditorFrame().setContainerInteractiveElementsUml(asmDiagramClass);
    saxDiagramClassFiller.getSaxFrameFullFiller().setContainerInteractiveElementsUml(asmDiagramClass);
    controllerDiagramClass = new ControllerDiagramClassPersistLightXml<Activity>(asmDiagramClass, toolbarDiagramClass,
        guiMain, asmEditorPropertiesDiagramClass);
  }

  //SGS:
  public ControllerDiagramClassPersistLightXml<Activity> getControllerDiagramClass() {
    return controllerDiagramClass;
  }

  public ToolbarDiagram getToolbarDiagramClass() {
    return toolbarDiagramClass;
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

  public SrvPersistLightXmlAsmDiagramUml<DiagramClass> getSrvPersistDiagramClass() {
    return srvPersistDiagramClass;
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

  public AsmDiagramClassInteractive<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> getAsmDiagramClass() {
    return asmDiagramClass;
  }

  public IEditor<DiagramClass> getAsmEditorPropertiesDiagramClass() {
    return asmEditorPropertiesDiagramClass;
  }

  public void setAsmEditorPropertiesDiagramClass(
      IEditor<DiagramClass> asmEditorPropertiesDiagramClass) {
    this.asmEditorPropertiesDiagramClass = asmEditorPropertiesDiagramClass;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsPolyClass() {
    return srvPersistListAsmRelationshipsPolyClass;
  }
}
