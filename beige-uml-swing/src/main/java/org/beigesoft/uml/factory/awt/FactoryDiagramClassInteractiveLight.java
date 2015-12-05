package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;

import org.beigesoft.graphic.pojo.SettingsDraw;
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
import org.beigesoft.uml.service.edit.SrvEditPropertiesDiagramClass;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SaxDiagramClassInteractiveFiller;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlClassUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramClass;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipBinaryVarious;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipPoly;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipSelf;
import org.beigesoft.uml.ui.EditorPropertiesDiagramClass;
import org.beigesoft.uml.ui.IGuiMainUml;
import org.beigesoft.uml.ui.swing.AsmEditorPropertiesDiagramClass;
import org.beigesoft.uml.ui.swing.PaletteDiagramClass;

public class FactoryDiagramClassInteractiveLight extends AFactoryDiagramGeneral {

  private final ControllerDiagramClassPersistLightXml<Frame> controllerDiagramClass;
  
  private final PaletteDiagramClass paletteDiagramClass;
  
  private final AsmDiagramClassInteractive<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> asmDiagramClass;

  private final FactoryAsmRelationshipBinaryClassLight factoryAsmRelationshipBinaryClass;

  private final FactoryAsmClassFullLight factoryAsmClassFull;

  private final SrvPersistLightXmlAsmDiagramUml<DiagramClass>   srvPersistDiagramClass;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ClassFull<ClassUml>> persistListClassesUmlSrv;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsBinaruClass;
       
  private final AsmEditorPropertiesDiagramClass<DiagramClass, EditorPropertiesDiagramClass<DiagramClass, Frame, ActionEvent>> asmEditorPropertiesDiagramClass;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsSelf;

  private final FactoryAsmRelationshipSelfClassLight factoryAsmRelationshipSelf;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsPoly;

  private final FactoryAsmRelationshipPolyClassLight factoryAsmRelationshipPoly;

  public FactoryDiagramClassInteractiveLight(Frame frameMain,
      IGuiMainUml<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> guiMain) {
    super(frameMain, guiMain);
    factoryAsmClassFull = new FactoryAsmClassFullLight(guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), 
        guiMain.getSrvDraw(), guiMain.getSettingsGraphicUml(), frameMain); 
    factoryAsmRelationshipBinaryClass = new FactoryAsmRelationshipBinaryClassLight(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), 
        guiMain.getSettingsGraphicUml(), frameMain);
    factoryAsmRelationshipSelf = new FactoryAsmRelationshipSelfClassLight(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), 
        guiMain.getSettingsGraphicUml(), frameMain);
    factoryAsmRelationshipPoly = new FactoryAsmRelationshipPolyClassLight(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), 
        guiMain.getSettingsGraphicUml(), frameMain);
    paletteDiagramClass = new PaletteDiagramClass();
    SaxDiagramClassInteractiveFiller<Graphics2D, SettingsDraw, Image, Frame> saxDiagramClassFiller = 
        new SaxDiagramClassInteractiveFiller<Graphics2D, SettingsDraw, Image, Frame>(SrvSaveXmlDiagramClass.NAMEXML_DIAGRAMCLASS, factoryAsmClassFull, 
            factoryAsmRelationshipBinaryClass, factoryAsmRelationshipSelf, factoryAsmRelationshipPoly, getFactoryAsmComment(), getFactoryAsmText(), 
            getFactoryAsmFrame(), getFactoryAsmRectangle(), getFactoryAsmLineUml());
    SrvSaveXmlDiagramClass<DiagramClass> srvSaveXmlDiagramUml = new SrvSaveXmlDiagramClass<DiagramClass>(SrvSaveXmlDiagramClass.NAMEXML_DIAGRAMCLASS);
    srvPersistDiagramClass = new SrvPersistLightXmlAsmDiagramUml<DiagramClass>(srvSaveXmlDiagramUml,
        saxDiagramClassFiller, SrvSaveXmlDiagramClass.DIAGRAM_FILE_EXTENSION);
    persistListClassesUmlSrv = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ClassFull<ClassUml>>(factoryAsmClassFull, SrvSaveXmlClassUml.NAMEXML_CLASSUML);
    srvPersistListAsmRelationshipsBinaruClass = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>
          (factoryAsmRelationshipBinaryClass, SrvSaveXmlRelationshipBinaryVarious.NAMEXML_RELATIONSHIPBINARYVARIOUS);
    srvPersistListAsmRelationshipsSelf = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,ClassFull<ClassUml>,ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,ClassFull<ClassUml>,ClassUml>>(factoryAsmRelationshipSelf, SrvSaveXmlRelationshipSelf.NAMEXML_RELATIONSHIPSELF);
    srvPersistListAsmRelationshipsPoly = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,ClassFull<ClassUml>,ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(factoryAsmRelationshipPoly, SrvSaveXmlRelationshipPoly.NAMEXML_RELATIONSHIPPOLY);
    DiagramClass diagramClass = new DiagramClass();
    asmDiagramClass = new AsmDiagramClassInteractive<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame>(diagramClass, guiMain, 
        srvPersistDiagramClass, getSrvPersistListAsmComments(), getSrvPersistListAsmTexts(), getFactoryAsmComment(),
        getFactoryAsmText(), getSrvPersistListAsmFrames(), getFactoryAsmFrame(), getSrvPersistListAsmRectangles(), getFactoryAsmRectangle(), 
        getSrvPersistListAsmLineUmls(), getFactoryAsmLineUml(), persistListClassesUmlSrv, factoryAsmClassFull, srvPersistListAsmRelationshipsBinaruClass, 
        factoryAsmRelationshipBinaryClass, srvPersistListAsmRelationshipsSelf, factoryAsmRelationshipSelf, srvPersistListAsmRelationshipsPoly, factoryAsmRelationshipPoly);
    factoryAsmRelationshipBinaryClass.getFactoryEditorRelationship().setContainerShapes(asmDiagramClass);
    factoryAsmRelationshipBinaryClass.getFactoryEditorRelationship().setObserverElementChanged(asmDiagramClass);
    factoryAsmRelationshipBinaryClass.getSrvInteractiveRelationship().setContainerShapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipBinaryFiller().getSaxRectangleRelationshipStartFiller().setContainerShapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipBinaryFiller().getSaxRectangleRelationshipEndFiller().setContainerShapesFull(asmDiagramClass);
    factoryAsmRelationshipSelf.getFactoryEditorRelationship().setObserverElementChanged(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipSelfFiller().getSaxRectangleRelationshipStartFiller().setContainerShapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipSelfFiller().getSaxRectangleRelationshipEndFiller().setContainerShapesFull(asmDiagramClass);
    factoryAsmRelationshipPoly.getFactoryEditorRelationship().setObserverElementChanged(asmDiagramClass);
    factoryAsmRelationshipPoly.getFactoryEditorRelationship().getEditorShapeRelationship().setContainerChapesFull(asmDiagramClass);
    saxDiagramClassFiller.getSaxRelationshipPolyFiller().getSaxShapeRelationshipFiller().setContainerShapesFull(asmDiagramClass);
    factoryAsmClassFull.getFactoryEditorClassUml().setObserverClassUmlChanged(asmDiagramClass);
    getFactoryAsmRectangle().getFactoryEditorRectangleUml().setObserverRectangleUmlChanged(asmDiagramClass);
    getFactoryAsmLineUml().getFactoryEditorLineUml().setObserverLineUmlChanged(asmDiagramClass);
    getFactoryAsmText().getFactoryEditorTextUml().setContainerShapesUmlForTie(asmDiagramClass);
    getFactoryAsmText().getFactoryEditorTextUml().setObserverTextUmlChanged(asmDiagramClass);
    saxDiagramClassFiller.getSaxTextFiller().setContainerShapesUmlForTie(asmDiagramClass);
    getFactoryAsmFrame().getFactoryEditorFrameFull().setObserverModelChanged(asmDiagramClass);
    getFactoryAsmFrame().getFactoryEditorFrameFull().setContainerInteractiveElementsUml(asmDiagramClass);
    saxDiagramClassFiller.getSaxFrameFullFiller().setContainerInteractiveElementsUml(asmDiagramClass);
    getFactoryAsmComment().getFactoryEditorCommentUml().setObserverCommentUmlChanged(asmDiagramClass);
    SrvEditPropertiesDiagramClass<DiagramClass, Frame> srvEditPropDiagCl = new SrvEditPropertiesDiagramClass<DiagramClass, Frame>(guiMain.getGuiSrvs().getSrvI18n(), 
        guiMain.getGuiSrvs().getSrvDialog(), guiMain.getGuiSrvs().getSettingsGraphic());
    EditorPropertiesDiagramClass<DiagramClass, Frame, ActionEvent> editorPropertiesDiagramClass = new EditorPropertiesDiagramClass<DiagramClass, Frame, ActionEvent>(frameMain, srvEditPropDiagCl, guiMain.getGuiSrvs().getSrvI18n().getMsg("properties_diagram"));
    asmEditorPropertiesDiagramClass = new AsmEditorPropertiesDiagramClass<DiagramClass, EditorPropertiesDiagramClass<DiagramClass, Frame, ActionEvent>>(frameMain, editorPropertiesDiagramClass);
    asmEditorPropertiesDiagramClass.doPostConstruct();
    controllerDiagramClass = new ControllerDiagramClassPersistLightXml<Frame>(asmDiagramClass, paletteDiagramClass, guiMain, asmEditorPropertiesDiagramClass);
  }

  //SGS:
  public PaletteDiagramClass getPaletteDiagramClass() {
    return paletteDiagramClass;
  }

  public ControllerDiagramClassPersistLightXml<Frame> getControllerDiagramClass() {
    return controllerDiagramClass;
  }

  public AsmDiagramClassInteractive<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> getAsmDiagramClass() {
    return asmDiagramClass;
  }

  public FactoryAsmRelationshipBinaryClassLight getFactoryAsmRelationshipBinaryClass() {
    return factoryAsmRelationshipBinaryClass;
  }

  public FactoryAsmClassFullLight getFactoryAsmClassFull() {
    return factoryAsmClassFull;
  }

  public SrvPersistLightXmlAsmDiagramUml<DiagramClass> getSrvPersistDiagramClass() {
    return srvPersistDiagramClass;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ClassFull<ClassUml>> getPersistListClassesUmlSrv() {
    return persistListClassesUmlSrv;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsBinaruClass() {
    return srvPersistListAsmRelationshipsBinaruClass;
  }

  public AsmEditorPropertiesDiagramClass<DiagramClass, EditorPropertiesDiagramClass<DiagramClass, Frame, ActionEvent>> getAsmEditorPropertiesDiagramClass() {
    return asmEditorPropertiesDiagramClass;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsSelf() {
    return srvPersistListAsmRelationshipsSelf;
  }

  public FactoryAsmRelationshipSelfClassLight getFactoryAsmRelationshipSelf() {
    return factoryAsmRelationshipSelf;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsPoly() {
    return srvPersistListAsmRelationshipsPoly;
  }

  public FactoryAsmRelationshipPolyClassLight getFactoryAsmRelationshipPoly() {
    return factoryAsmRelationshipPoly;
  }
}
