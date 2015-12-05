package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.controller.ControllerDiagramPackagePersistLightXml;
import org.beigesoft.uml.diagram.assembly.AsmDiagramPackage;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.pojo.PackageUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SaxDiagramPackageFiller;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlPackage;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlClassUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipBinaryVarious;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipPoly;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipSelf;
import org.beigesoft.uml.ui.IGuiMainUml;
import org.beigesoft.uml.ui.swing.PaletteDiagramPackage;

public class FactoryDiagramPackage extends AFactoryDiagramGeneral {

  private final ControllerDiagramPackagePersistLightXml<Frame> controllerDiagramPackage;
  
  private final PaletteDiagramPackage paletteDiagramPackage;
  
  private final AsmDiagramPackage<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> asmDiagramPackage;

  private final FactoryAsmRelationshipBinaryClassLight factoryAsmRelationshipBinaryClass;

  private final FactoryAsmClassFullLight factoryAsmClassFull;

  private final SrvPersistLightXmlAsmDiagramUml<DiagramUml> srvPersistDiagramPackage;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ClassFull<ClassUml>> persistListClassesUmlSrv;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsBinaruClass;
       
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsSelf;

  private final FactoryAsmRelationshipSelfClassLight factoryAsmRelationshipSelf;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> srvPersistListAsmRelationshipsPoly;

  private final FactoryAsmRelationshipPolyClassLight factoryAsmRelationshipPoly;

  private final FactoryAsmPackageFullLight factoryAsmPackageFull;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<PackageUml>> srvPersistXmlListAsmPackagesFull;

  private final FactoryAsmRelationshipBinaryVariousLight factoryAsmRelationshipPackage;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinaryVarious> srvPersistXmlListAsmRelationshipsPackage;

  public FactoryDiagramPackage(Frame frameMain,
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
    factoryAsmPackageFull = new FactoryAsmPackageFullLight(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    factoryAsmRelationshipPackage = new FactoryAsmRelationshipBinaryVariousLight(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    srvPersistXmlListAsmPackagesFull = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<PackageUml>>(factoryAsmPackageFull, SrvSaveXmlPackage.NAMEXML_PACKAGEUML);
    srvPersistXmlListAsmRelationshipsPackage = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinaryVarious>(factoryAsmRelationshipPackage, SrvSaveXmlRelationshipBinaryVarious.NAMEXML_RELATIONSHIPBINARYVARIOUS);
    paletteDiagramPackage = new PaletteDiagramPackage();
    SaxDiagramPackageFiller<Graphics2D, SettingsDraw, Image, Frame> saxDiagramPackageFiller = 
        new SaxDiagramPackageFiller<Graphics2D, SettingsDraw, Image, Frame>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_PACKAGE, factoryAsmClassFull, 
            factoryAsmRelationshipBinaryClass, factoryAsmRelationshipSelf, factoryAsmRelationshipPoly, 
            getFactoryAsmComment(), getFactoryAsmText(), getFactoryAsmFrame(), getFactoryAsmRectangle(), 
            getFactoryAsmLineUml(), factoryAsmPackageFull, factoryAsmRelationshipPackage);
    SrvSaveXmlDiagramUml<DiagramUml> srvSaveXmlDiagramUml = new SrvSaveXmlDiagramUml<DiagramUml>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_PACKAGE);
    srvPersistDiagramPackage = new SrvPersistLightXmlAsmDiagramUml<DiagramUml>(srvSaveXmlDiagramUml,
        saxDiagramPackageFiller, SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_PACKAGE);
    persistListClassesUmlSrv = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ClassFull<ClassUml>>(factoryAsmClassFull, SrvSaveXmlClassUml.NAMEXML_CLASSUML);
    srvPersistListAsmRelationshipsBinaruClass = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>
          (factoryAsmRelationshipBinaryClass, SrvSaveXmlRelationshipBinaryVarious.NAMEXML_RELATIONSHIPBINARYVARIOUS);
    srvPersistListAsmRelationshipsSelf = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,ClassFull<ClassUml>,ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,ClassFull<ClassUml>,ClassUml>>(factoryAsmRelationshipSelf, SrvSaveXmlRelationshipSelf.NAMEXML_RELATIONSHIPSELF);
    srvPersistListAsmRelationshipsPoly = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,ClassFull<ClassUml>,ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>>(factoryAsmRelationshipPoly, SrvSaveXmlRelationshipPoly.NAMEXML_RELATIONSHIPPOLY);
    DiagramUml diagramClass = new DiagramUml();
    asmDiagramPackage = new AsmDiagramPackage<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame>(diagramClass, guiMain, 
        srvPersistDiagramPackage, getSrvPersistListAsmComments(), getSrvPersistListAsmTexts(), getFactoryAsmComment(),
        getFactoryAsmText(), getSrvPersistListAsmFrames(), getFactoryAsmFrame(), getSrvPersistListAsmRectangles(), getFactoryAsmRectangle(), 
        getSrvPersistListAsmLineUmls(), getFactoryAsmLineUml(), persistListClassesUmlSrv, factoryAsmClassFull, srvPersistListAsmRelationshipsBinaruClass, 
        factoryAsmRelationshipBinaryClass, srvPersistListAsmRelationshipsSelf, factoryAsmRelationshipSelf, srvPersistListAsmRelationshipsPoly, factoryAsmRelationshipPoly, 
        srvPersistXmlListAsmPackagesFull, factoryAsmPackageFull, srvPersistXmlListAsmRelationshipsPackage, factoryAsmRelationshipPackage);
    factoryAsmRelationshipBinaryClass.getFactoryEditorRelationship().setContainerShapes(asmDiagramPackage);
    factoryAsmRelationshipBinaryClass.getFactoryEditorRelationship().setObserverElementChanged(asmDiagramPackage);
    factoryAsmRelationshipBinaryClass.getSrvInteractiveRelationship().setContainerShapesFull(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipBinaryFiller().getSaxRectangleRelationshipStartFiller().setContainerShapesFull(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipBinaryFiller().getSaxRectangleRelationshipEndFiller().setContainerShapesFull(asmDiagramPackage);
    factoryAsmRelationshipSelf.getFactoryEditorRelationship().setObserverElementChanged(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipSelfFiller().getSaxRectangleRelationshipStartFiller().setContainerShapesFull(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipSelfFiller().getSaxRectangleRelationshipEndFiller().setContainerShapesFull(asmDiagramPackage);
    factoryAsmRelationshipPoly.getFactoryEditorRelationship().setObserverElementChanged(asmDiagramPackage);
    factoryAsmRelationshipPoly.getFactoryEditorRelationship().getEditorShapeRelationship().setContainerChapesFull(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipPolyFiller().getSaxShapeRelationshipFiller().setContainerShapesFull(asmDiagramPackage);
    factoryAsmClassFull.getFactoryEditorClassUml().setObserverClassUmlChanged(asmDiagramPackage);
    getFactoryAsmRectangle().getFactoryEditorRectangleUml().setObserverRectangleUmlChanged(asmDiagramPackage);
    getFactoryAsmLineUml().getFactoryEditorLineUml().setObserverLineUmlChanged(asmDiagramPackage);
    getFactoryAsmText().getFactoryEditorTextUml().setContainerShapesUmlForTie(asmDiagramPackage);
    getFactoryAsmText().getFactoryEditorTextUml().setObserverTextUmlChanged(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxTextFiller().setContainerShapesUmlForTie(asmDiagramPackage);
    getFactoryAsmFrame().getFactoryEditorFrameFull().setObserverModelChanged(asmDiagramPackage);
    getFactoryAsmFrame().getFactoryEditorFrameFull().setContainerInteractiveElementsUml(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxFrameFullFiller().setContainerInteractiveElementsUml(asmDiagramPackage);
    getFactoryAsmComment().getFactoryEditorCommentUml().setObserverCommentUmlChanged(asmDiagramPackage);
    factoryAsmRelationshipPackage.getFactoryEditorRelationship().setObserverElementChanged(asmDiagramPackage);
    factoryAsmRelationshipPackage.getFactoryEditorRelationship().setContainerShapes(asmDiagramPackage);
    factoryAsmRelationshipPackage.getSrvInteractiveRelationship().setContainerShapesFull(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipStartFiller().setContainerShapesFullVarious(asmDiagramPackage);
    saxDiagramPackageFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipEndFiller().setContainerShapesFullVarious(asmDiagramPackage);
    factoryAsmPackageFull.getFactoryEditorPackageUmlFull().setObserverModelChanged(asmDiagramPackage);
    controllerDiagramPackage = new ControllerDiagramPackagePersistLightXml<Frame>(asmDiagramPackage, paletteDiagramPackage, guiMain);
  }

  //SGS:
  public PaletteDiagramPackage getPaletteDiagramPackage() {
    return paletteDiagramPackage;
  }

  public ControllerDiagramPackagePersistLightXml<Frame> getControllerDiagramPackage() {
    return controllerDiagramPackage;
  }

  public FactoryAsmRelationshipBinaryClassLight getFactoryAsmRelationshipBinaryClass() {
    return factoryAsmRelationshipBinaryClass;
  }

  public FactoryAsmClassFullLight getFactoryAsmClassFull() {
    return factoryAsmClassFull;
  }

  public AsmDiagramPackage<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> getAsmDiagramPackage() {
    return asmDiagramPackage;
  }

  public SrvPersistLightXmlAsmDiagramUml<DiagramUml> getSrvPersistDiagramPackage() {
    return srvPersistDiagramPackage;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ClassFull<ClassUml>> getPersistListClassesUmlSrv() {
    return persistListClassesUmlSrv;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> getSrvPersistListAsmRelationshipsBinaruClass() {
    return srvPersistListAsmRelationshipsBinaruClass;
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

  public FactoryAsmPackageFullLight getFactoryAsmPackageFull() {
    return factoryAsmPackageFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<PackageUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<PackageUml>> getSrvPersistXmlListAsmPackagesFull() {
    return srvPersistXmlListAsmPackagesFull;
  }

  public FactoryAsmRelationshipBinaryVariousLight getFactoryAsmRelationshipPackage() {
    return factoryAsmRelationshipPackage;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinaryVarious> getSrvPersistXmlListAsmRelationshipsPackage() {
    return srvPersistXmlListAsmRelationshipsPackage;
  }
}
