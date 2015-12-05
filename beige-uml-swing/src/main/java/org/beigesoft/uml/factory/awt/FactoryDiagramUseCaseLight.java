package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.controller.ControllerDiagramUseCasePersistLightXml;
import org.beigesoft.uml.diagram.assembly.AsmDiagramUseCase;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.pojo.Actor;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.pojo.UseCase;
import org.beigesoft.uml.pojo.UseCaseExtended;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SaxDiagramUseCaseFiller;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlActor;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipBinaryVarious;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlUseCase;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlUseCaseExtended;
import org.beigesoft.uml.ui.IGuiMainUml;
import org.beigesoft.uml.ui.swing.PaletteDiagramUseCase;

public class FactoryDiagramUseCaseLight extends AFactoryDiagramGeneral {

  private final PaletteDiagramUseCase paletteDiagram;
  
  private final ControllerDiagramUseCasePersistLightXml<Frame> controllerDiagramUseCase;

  private final AsmDiagramUseCase<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> asmDiagramUseCase;
  
  private final SrvPersistLightXmlAsmDiagramUml<DiagramUml> srvPersistXmlDiagramUseCase;

  private final FactoryAsmUseCaseFullLight factoryAsmUseCaseFull;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<UseCase>> srvPersistXmlListAsmUseCasesFull;

  private final FactoryAsmActorFullLight factoryAsmActorFull;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<Actor>> srvPersistXmlListAsmActorsFull;

  private final FactoryAsmRelationshipBinaryVariousLight factoryAsmRelationshipActorUseCase;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinaryVarious> srvPersistXmlListAsmRelationshipsActorUseCase;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<UseCaseExtended>> srvPersistListAsmUseCasesExtendedFull;

  private final FactoryAsmUseCaseExtendedFullLight factoryAsmUseCaseExtendedFull;
  
  public FactoryDiagramUseCaseLight(
      IGuiMainUml<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> guiMain,
      Frame frameMain) {
    super(frameMain, guiMain);
    factoryAsmActorFull = new FactoryAsmActorFullLight(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    factoryAsmUseCaseFull = new FactoryAsmUseCaseFullLight(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    factoryAsmUseCaseExtendedFull = new FactoryAsmUseCaseExtendedFullLight(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    factoryAsmRelationshipActorUseCase = new FactoryAsmRelationshipBinaryVariousLight(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    SrvSaveXmlDiagramUml<DiagramUml> srvSaveXmlDiagramUml = new SrvSaveXmlDiagramUml<DiagramUml>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_USECASE);
    SaxDiagramUseCaseFiller<Graphics2D, SettingsDraw, Image, Frame> saxDiagramFiller = new SaxDiagramUseCaseFiller<Graphics2D, SettingsDraw, Image, Frame>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_USECASE, 
        factoryAsmActorFull, factoryAsmUseCaseFull, factoryAsmUseCaseExtendedFull, factoryAsmRelationshipActorUseCase,
        getFactoryAsmComment(), getFactoryAsmText(), getFactoryAsmFrame(), getFactoryAsmRectangle(), getFactoryAsmLineUml());
    srvPersistXmlDiagramUseCase = new SrvPersistLightXmlAsmDiagramUml<DiagramUml>(srvSaveXmlDiagramUml, saxDiagramFiller, SrvSaveXmlDiagramUml.NAME_DIAGRAM_USECASE);
    srvPersistXmlListAsmUseCasesFull = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<UseCase>>(factoryAsmUseCaseFull, SrvSaveXmlUseCase.NAMEXML_USECASE);
    srvPersistXmlListAsmActorsFull = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<Actor>>(factoryAsmActorFull, SrvSaveXmlActor.NAMEXML_ACTORUML);
    srvPersistXmlListAsmRelationshipsActorUseCase = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinaryVarious>(factoryAsmRelationshipActorUseCase, SrvSaveXmlRelationshipBinaryVarious.NAMEXML_RELATIONSHIPBINARYVARIOUS);
    srvPersistListAsmUseCasesExtendedFull = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<UseCaseExtended>>(factoryAsmUseCaseExtendedFull, SrvSaveXmlUseCaseExtended.NAMEXML_USECASEEXTENDED);
    paletteDiagram = new PaletteDiagramUseCase();
    DiagramUml diagramUml = new DiagramUml();
    asmDiagramUseCase = new AsmDiagramUseCase<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame>(diagramUml, guiMain, 
        srvPersistXmlDiagramUseCase, getSrvPersistListAsmComments(), getSrvPersistListAsmTexts(), getFactoryAsmComment(), 
          getFactoryAsmText(), getSrvPersistListAsmFrames(), getFactoryAsmFrame(), getSrvPersistListAsmRectangles(), getFactoryAsmRectangle(), 
          getSrvPersistListAsmLineUmls(), getFactoryAsmLineUml(),
          srvPersistXmlListAsmUseCasesFull, factoryAsmUseCaseFull, srvPersistXmlListAsmActorsFull, factoryAsmActorFull,
          srvPersistXmlListAsmRelationshipsActorUseCase, factoryAsmRelationshipActorUseCase,
          srvPersistListAsmUseCasesExtendedFull, factoryAsmUseCaseExtendedFull);
    controllerDiagramUseCase = new ControllerDiagramUseCasePersistLightXml<Frame>(asmDiagramUseCase, paletteDiagram, guiMain);
    factoryAsmRelationshipActorUseCase.getFactoryEditorRelationship().setObserverElementChanged(asmDiagramUseCase);
    factoryAsmRelationshipActorUseCase.getFactoryEditorRelationship().setContainerShapes(asmDiagramUseCase);
    factoryAsmRelationshipActorUseCase.getSrvInteractiveRelationship().setContainerShapesFull(asmDiagramUseCase);
    saxDiagramFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipStartFiller().setContainerShapesFullVarious(asmDiagramUseCase);
    saxDiagramFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipEndFiller().setContainerShapesFullVarious(asmDiagramUseCase);
    factoryAsmUseCaseFull.getFactoryEditorUseCaseUmlFull().setObserverUseCaseChanged(asmDiagramUseCase);
    factoryAsmUseCaseExtendedFull.getFactoryEditorUseCaseUmlFull().setObserverUseCaseChanged(asmDiagramUseCase);
    factoryAsmActorFull.getFactoryEditorActorUmlFull().setObserverActorUmlChanged(asmDiagramUseCase);
    getFactoryAsmRectangle().getFactoryEditorRectangleUml().setObserverRectangleUmlChanged(asmDiagramUseCase);
    getFactoryAsmLineUml().getFactoryEditorLineUml().setObserverLineUmlChanged(asmDiagramUseCase);
    getFactoryAsmText().getFactoryEditorTextUml().setObserverTextUmlChanged(asmDiagramUseCase);
    getFactoryAsmText().getFactoryEditorTextUml().setContainerShapesUmlForTie(asmDiagramUseCase);
    getFactoryAsmFrame().getFactoryEditorFrameFull().setObserverModelChanged(asmDiagramUseCase);
    getFactoryAsmFrame().getFactoryEditorFrameFull().setContainerInteractiveElementsUml(asmDiagramUseCase);
    saxDiagramFiller.getSaxFrameFullFiller().setContainerInteractiveElementsUml(asmDiagramUseCase);
    saxDiagramFiller.getSaxTextFiller().setContainerShapesUmlForTie(asmDiagramUseCase);
    getFactoryAsmComment().getFactoryEditorCommentUml().setObserverCommentUmlChanged(asmDiagramUseCase);
  }

  //SGS:
  public ControllerDiagramUseCasePersistLightXml<Frame> getControllerDiagramUseCase() {
    return controllerDiagramUseCase;
  }

  public PaletteDiagramUseCase getPaletteDiagram() {
    return paletteDiagram;
  }

  public AsmDiagramUseCase<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> getAsmDiagramUseCase() {
    return asmDiagramUseCase;
  }

  public SrvPersistLightXmlAsmDiagramUml<DiagramUml> getSrvPersistXmlDiagramUseCase() {
    return srvPersistXmlDiagramUseCase;
  }

  public FactoryAsmUseCaseFullLight getFactoryAsmUseCaseFull() {
    return factoryAsmUseCaseFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<UseCase>> getSrvPersistXmlListAsmUseCasesFull() {
    return srvPersistXmlListAsmUseCasesFull;
  }

  public FactoryAsmActorFullLight getFactoryAsmActorFull() {
    return factoryAsmActorFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<Actor>> getSrvPersistXmlListAsmActorsFull() {
    return srvPersistXmlListAsmActorsFull;
  }

  public FactoryAsmRelationshipBinaryVariousLight getFactoryAsmRelationshipActorUseCase() {
    return factoryAsmRelationshipActorUseCase;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinaryVarious> getSrvPersistXmlListAsmRelationshipsActorUseCase() {
    return srvPersistXmlListAsmRelationshipsActorUseCase;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<UseCaseExtended>> getSrvPersistListAsmUseCasesExtendedFull() {
    return srvPersistListAsmUseCasesExtendedFull;
  }

  public FactoryAsmUseCaseExtendedFullLight getFactoryAsmUseCaseExtendedFull() {
    return factoryAsmUseCaseExtendedFull;
  }
}
