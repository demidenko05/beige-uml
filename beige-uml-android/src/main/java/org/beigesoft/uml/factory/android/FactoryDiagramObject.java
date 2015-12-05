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
import org.beigesoft.uml.controller.ControllerDiagramObjectPersistLightXml;
import org.beigesoft.uml.diagram.assembly.AsmDiagramObject;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.model.InstanceUml;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SaxDiagramObjectFiller;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipBinaryVarious;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlInstance;
import org.beigesoft.uml.ui.IGuiMainUml;

public class FactoryDiagramObject extends AFactoryDiagramGeneral {

  private final AsmDiagramObject<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> asmDiagramObject;
  
  private final ControllerDiagramObjectPersistLightXml<Activity> controllerDiagramObject;
  
  private final ToolbarDiagram toolbarDiagramObject;
  
  private final SrvPersistLightXmlAsmDiagramUml<DiagramUml>   srvPersistDiagramObject;
  
  private IEditor<DiagramUml> asmEditorPropertiesDiagramObject;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<InstanceUml>> srvPersistListAsmInstance;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinaryVarious> srvPersistListAsmRelationshipsBinaryVarious;

  private final FactoryAsmInstanceFull factoryAsmInstanceFull;
  
  private final FactoryAsmRelationshipBinaryVarious factoryAsmRelationshipBinaryVarious;
    
  public FactoryDiagramObject(ToolbarDiagram toolbarDiagramObject, SrvDraw srvDraw, 
      IContainerSrvsGui<Activity> containerGui, Activity activity, IGuiMainUml<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> guiMain) {
    super(srvDraw, containerGui, activity);
    this.toolbarDiagramObject = toolbarDiagramObject;
    factoryAsmInstanceFull = new FactoryAsmInstanceFull(srvDraw, containerGui, activity);
    factoryAsmRelationshipBinaryVarious = new FactoryAsmRelationshipBinaryVarious(srvDraw, containerGui, activity);
    SrvSaveXmlDiagramUml<DiagramUml> srvSaveXmlDiagramUml = new SrvSaveXmlDiagramUml<DiagramUml>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_OBJECT);
    SaxDiagramObjectFiller<CanvasWithPaint, SettingsDraw, Bitmap, Activity> saxDiagramObjectFiller =
        new SaxDiagramObjectFiller<CanvasWithPaint, SettingsDraw, Bitmap, Activity>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_OBJECT, 
            getFactoryAsmComment(), getFactoryAsmText(), getFactoryAsmFrame(), getFactoryAsmRectangle(),
            getFactoryAsmLine(), factoryAsmInstanceFull, factoryAsmRelationshipBinaryVarious);
    srvPersistDiagramObject = new SrvPersistLightXmlAsmDiagramUml<DiagramUml>(srvSaveXmlDiagramUml,
        saxDiagramObjectFiller, SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_OBJECT);
    srvPersistListAsmInstance = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<InstanceUml>>(factoryAsmInstanceFull, SrvSaveXmlInstance.NAMEXML_INSTANCEUML);
    srvPersistListAsmRelationshipsBinaryVarious = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinaryVarious>(factoryAsmRelationshipBinaryVarious, SrvSaveXmlRelationshipBinaryVarious.NAMEXML_RELATIONSHIPBINARYVARIOUS);
    DiagramUml diagramClass = new DiagramUml();
    asmDiagramObject = new AsmDiagramObject<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity>(diagramClass, 
        guiMain, srvPersistDiagramObject, getSrvPersistListAsmComments(), getSrvPersistListAsmTexts(), getFactoryAsmComment(), 
        getFactoryAsmText(), getSrvPersistListAsmFrames(), getFactoryAsmFrame(), getSrvPersistListAsmRectangles(), getFactoryAsmRectangle(),
        getSrvPersistListAsmLines(), getFactoryAsmLine(),
        srvPersistListAsmInstance, factoryAsmInstanceFull, srvPersistListAsmRelationshipsBinaryVarious, factoryAsmRelationshipBinaryVarious);
    getFactoryAsmRectangle().getFactoryEditorRectangle().setObserverRectangleUmlChanged(asmDiagramObject);
    getFactoryAsmLine().getFactoryEditorLine().setObserverLineUmlChanged(asmDiagramObject);
    getFactoryAsmText().getFactoryEditorText().setContainerShapesUmlForTie(asmDiagramObject);
    getFactoryAsmText().getFactoryEditorText().setObserverTextUmlChanged(asmDiagramObject);
    saxDiagramObjectFiller.getSaxTextFiller().setContainerShapesUmlForTie(asmDiagramObject);
    getFactoryAsmComment().getFactoryEditorComment().setObserverCommentUmlChanged(asmDiagramObject);
    getFactoryAsmFrame().getFactoryEditorFrame().setObserverModelChanged(asmDiagramObject);
    getFactoryAsmFrame().getFactoryEditorFrame().setContainerInteractiveElementsUml(asmDiagramObject);
    saxDiagramObjectFiller.getSaxFrameFullFiller().setContainerInteractiveElementsUml(asmDiagramObject);
    factoryAsmRelationshipBinaryVarious.getFactoryEditorRelationshipBinaryVarious().setObserverRelationshipBinaryClassUmlChanged(asmDiagramObject);
    factoryAsmRelationshipBinaryVarious.getFactoryEditorRelationshipBinaryVarious().setContainerShapes(asmDiagramObject);
    factoryAsmRelationshipBinaryVarious.getSrvInteractiveRelationshipBinaryVarious().setContainerShapesFull(asmDiagramObject);
    saxDiagramObjectFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipStartFiller().setContainerShapesFullVarious(asmDiagramObject);
    saxDiagramObjectFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipEndFiller().setContainerShapesFullVarious(asmDiagramObject);
    factoryAsmInstanceFull.getFactoryEditorInstanceFull().setObserverInstanceFullUmlChanged(asmDiagramObject);
    controllerDiagramObject = new ControllerDiagramObjectPersistLightXml<Activity>(asmDiagramObject, toolbarDiagramObject,
        guiMain);
  }

  //SGS:
  public ControllerDiagramObjectPersistLightXml<Activity> getControllerDiagramObject() {
    return controllerDiagramObject;
  }

  public ToolbarDiagram getToolbarDiagramObject() {
    return toolbarDiagramObject;
  }

  public SrvPersistLightXmlAsmDiagramUml<DiagramUml> getSrvPersistDiagramObject() {
    return srvPersistDiagramObject;
  }

  public AsmDiagramObject<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> getAsmDiagramObject() {
    return asmDiagramObject;
  }

  public IEditor<DiagramUml> getAsmEditorPropertiesDiagramObject() {
    return asmEditorPropertiesDiagramObject;
  }

  public void setAsmEditorPropertiesDiagramObject(
      IEditor<DiagramUml> asmEditorPropertiesDiagramObject) {
    this.asmEditorPropertiesDiagramObject = asmEditorPropertiesDiagramObject;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, ShapeFullVarious<InstanceUml>> getSrvPersistListAsmInstance() {
    return srvPersistListAsmInstance;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, RelationshipBinaryVarious> getSrvPersistListAsmRelationshipsBinaryVarious() {
    return srvPersistListAsmRelationshipsBinaryVarious;
  }

  public FactoryAsmInstanceFull getFactoryAsmInstanceFull() {
    return factoryAsmInstanceFull;
  }

  public FactoryAsmRelationshipBinaryVarious getFactoryAsmRelationshipBinaryVarious() {
    return factoryAsmRelationshipBinaryVarious;
  }
}
