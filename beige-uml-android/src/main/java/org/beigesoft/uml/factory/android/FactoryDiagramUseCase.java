package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.graphics.Bitmap;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.ToolbarDiagram;
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

public class FactoryDiagramUseCase extends AFactoryDiagramGeneral {

  private final AsmDiagramUseCase<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> asmDiagramUseCase;
  
  private final ControllerDiagramUseCasePersistLightXml<Activity> controllerDiagramUseCase;
  
  private final ToolbarDiagram toolbarDiagramUseCase;
  
  private final FactoryAsmActorFull factoryAsmActorFull;
  
  private final FactoryAsmUseCaseFull factoryAsmUseCaseFull;
  
  private final FactoryAsmUseCaseExtendedFull factoryAsmUseCaseExtendedFull;
  
  private final FactoryAsmRelationshipBinaryVarious factoryAsmRelationshipBinaryVarious;
    
  private final SrvPersistLightXmlAsmDiagramUml<DiagramUml>   srvPersistDiagramUseCase;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<Actor>> srvPersistListAsmActors;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<UseCase>> srvPersistListAsmUseCases;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<UseCaseExtended>> srvPersistListAsmUseCasesExtended;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinaryVarious> srvPersistListAsmRelationshipsBinaryVarious;

  private IEditor<DiagramUml> asmEditorPropertiesDiagramUseCase;

  public FactoryDiagramUseCase(ToolbarDiagram toolbarDiagramUseCase, SrvDraw srvDraw, 
      IContainerSrvsGui<Activity> containerGui, Activity activity, IGuiMainUml<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> guiMain) {
    super(srvDraw, containerGui, activity);
    this.toolbarDiagramUseCase = toolbarDiagramUseCase;
    factoryAsmActorFull = new FactoryAsmActorFull(srvDraw, containerGui, activity);
    factoryAsmUseCaseFull = new FactoryAsmUseCaseFull(srvDraw, containerGui, activity);
    factoryAsmUseCaseExtendedFull = new FactoryAsmUseCaseExtendedFull(srvDraw, containerGui, activity);
    factoryAsmRelationshipBinaryVarious = new FactoryAsmRelationshipBinaryVarious(srvDraw, containerGui, activity);
    SrvSaveXmlDiagramUml<DiagramUml> srvSaveXmlDiagramUml = new SrvSaveXmlDiagramUml<DiagramUml>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_USECASE);
    SaxDiagramUseCaseFiller<CanvasWithPaint, SettingsDraw, Bitmap, Activity> saxDiagramUseCaseFiller =
        new SaxDiagramUseCaseFiller<CanvasWithPaint, SettingsDraw, Bitmap, Activity>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_USECASE, 
            factoryAsmActorFull, factoryAsmUseCaseFull, factoryAsmUseCaseExtendedFull, factoryAsmRelationshipBinaryVarious, 
            getFactoryAsmComment(), getFactoryAsmText(), getFactoryAsmFrame(), getFactoryAsmRectangle(), getFactoryAsmLine());
    srvPersistDiagramUseCase = new SrvPersistLightXmlAsmDiagramUml<DiagramUml>(srvSaveXmlDiagramUml,
        saxDiagramUseCaseFiller, SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_USECASE);
    srvPersistListAsmRelationshipsBinaryVarious = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinaryVarious>(factoryAsmRelationshipBinaryVarious, SrvSaveXmlRelationshipBinaryVarious.NAMEXML_RELATIONSHIPBINARYVARIOUS);
    srvPersistListAsmActors = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<Actor>>(factoryAsmActorFull, SrvSaveXmlActor.NAMEXML_ACTORUML);
    srvPersistListAsmUseCases = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<UseCase>>(factoryAsmUseCaseFull, SrvSaveXmlUseCase.NAMEXML_USECASE);
    srvPersistListAsmUseCasesExtended = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<UseCaseExtended>>(factoryAsmUseCaseExtendedFull, SrvSaveXmlUseCaseExtended.NAMEXML_USECASEEXTENDED);
    DiagramUml diagramClass = new DiagramUml();
    asmDiagramUseCase = new AsmDiagramUseCase<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity>(diagramClass, 
        guiMain, srvPersistDiagramUseCase, getSrvPersistListAsmComments(), getSrvPersistListAsmTexts(), getFactoryAsmComment(), 
        getFactoryAsmText(), getSrvPersistListAsmFrames(), getFactoryAsmFrame(), getSrvPersistListAsmRectangles(), getFactoryAsmRectangle(), 
        getSrvPersistListAsmLines(), getFactoryAsmLine(), srvPersistListAsmUseCases, factoryAsmUseCaseFull, srvPersistListAsmActors, factoryAsmActorFull,
        srvPersistListAsmRelationshipsBinaryVarious, factoryAsmRelationshipBinaryVarious,
        srvPersistListAsmUseCasesExtended, factoryAsmUseCaseExtendedFull);
    factoryAsmRelationshipBinaryVarious.getFactoryEditorRelationshipBinaryVarious().setContainerShapes(asmDiagramUseCase);
    factoryAsmRelationshipBinaryVarious.getFactoryEditorRelationshipBinaryVarious().setObserverRelationshipBinaryClassUmlChanged(asmDiagramUseCase);
    factoryAsmRelationshipBinaryVarious.getSrvInteractiveRelationshipBinaryVarious().setContainerShapesFull(asmDiagramUseCase);
    saxDiagramUseCaseFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipStartFiller().setContainerShapesFullVarious(asmDiagramUseCase);
    saxDiagramUseCaseFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipEndFiller().setContainerShapesFullVarious(asmDiagramUseCase);
    factoryAsmActorFull.getFactoryEditorActorFull().setObserverActorFullUmlChanged(asmDiagramUseCase);
    factoryAsmUseCaseFull.getFactoryEditorUseCaseFull().setObserverUseCaseFullUmlChanged(asmDiagramUseCase);
    factoryAsmUseCaseExtendedFull.getFactoryEditorUseCaseExtendedFull().setObserverUseCaseExtendedFullUmlChanged(asmDiagramUseCase);
    getFactoryAsmRectangle().getFactoryEditorRectangle().setObserverRectangleUmlChanged(asmDiagramUseCase);
    getFactoryAsmLine().getFactoryEditorLine().setObserverLineUmlChanged(asmDiagramUseCase);
    getFactoryAsmText().getFactoryEditorText().setContainerShapesUmlForTie(asmDiagramUseCase);
    getFactoryAsmText().getFactoryEditorText().setObserverTextUmlChanged(asmDiagramUseCase);
    saxDiagramUseCaseFiller.getSaxTextFiller().setContainerShapesUmlForTie(asmDiagramUseCase);
    getFactoryAsmComment().getFactoryEditorComment().setObserverCommentUmlChanged(asmDiagramUseCase);
    getFactoryAsmFrame().getFactoryEditorFrame().setObserverModelChanged(asmDiagramUseCase);
    getFactoryAsmFrame().getFactoryEditorFrame().setContainerInteractiveElementsUml(asmDiagramUseCase);
    saxDiagramUseCaseFiller.getSaxFrameFullFiller().setContainerInteractiveElementsUml(asmDiagramUseCase);
    controllerDiagramUseCase = new ControllerDiagramUseCasePersistLightXml<Activity>(asmDiagramUseCase, toolbarDiagramUseCase,
        guiMain);
  }

  //SGS:
  public ControllerDiagramUseCasePersistLightXml<Activity> getControllerDiagramUseCase() {
    return controllerDiagramUseCase;
  }

  public ToolbarDiagram getToolbarDiagramUseCase() {
    return toolbarDiagramUseCase;
  }

  public FactoryAsmActorFull getFactoryAsmActorFull() {
    return factoryAsmActorFull;
  }

  public FactoryAsmRelationshipBinaryVarious getFactoryAsmRelationshipBinaryVarious() {
    return factoryAsmRelationshipBinaryVarious;
  }

  public SrvPersistLightXmlAsmDiagramUml<DiagramUml> getSrvPersistDiagramUseCase() {
    return srvPersistDiagramUseCase;
  }

  public AsmDiagramUseCase<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> getAsmDiagramUseCase() {
    return asmDiagramUseCase;
  }

  public IEditor<DiagramUml> getAsmEditorPropertiesDiagramUseCase() {
    return asmEditorPropertiesDiagramUseCase;
  }

  public void setAsmEditorPropertiesDiagramUseCase(
      IEditor<DiagramUml> asmEditorPropertiesDiagramUseCase) {
    this.asmEditorPropertiesDiagramUseCase = asmEditorPropertiesDiagramUseCase;
  }

  public FactoryAsmUseCaseFull getFactoryAsmUseCaseFull() {
    return factoryAsmUseCaseFull;
  }

  public FactoryAsmUseCaseExtendedFull getFactoryAsmUseCaseExtendedFull() {
    return factoryAsmUseCaseExtendedFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<Actor>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<Actor>> getSrvPersistListAsmActors() {
    return srvPersistListAsmActors;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCase>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<UseCase>> getSrvPersistListAsmUseCases() {
    return srvPersistListAsmUseCases;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<UseCaseExtended>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<UseCaseExtended>> getSrvPersistListAsmUseCasesExtended() {
    return srvPersistListAsmUseCasesExtended;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinaryVarious> getSrvPersistListAsmRelationshipsBinaryVarious() {
    return srvPersistListAsmRelationshipsBinaryVarious;
  }
}
