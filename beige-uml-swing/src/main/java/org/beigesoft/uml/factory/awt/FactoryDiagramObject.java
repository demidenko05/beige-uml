package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
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
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlInstance;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipBinaryVarious;
import org.beigesoft.uml.ui.IGuiMainUml;
import org.beigesoft.uml.ui.swing.PaletteDiagramObject;

public class FactoryDiagramObject extends AFactoryDiagramGeneral {

  private final ControllerDiagramObjectPersistLightXml<Frame> controllerDiagramObject;
  
  private final PaletteDiagramObject paletteDiagramObject;
  
  private final AsmDiagramObject<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> asmDiagramObject;

  private final SrvPersistLightXmlAsmDiagramUml<DiagramUml> srvPersistDiagramObject;
  
  private final FactoryAsmInstanceFullLight factoryAsmInstanceFull;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<InstanceUml>> srvPersistXmlListAsmObjectsFull;

  private final FactoryAsmRelationshipBinaryVariousLight factoryAsmRelationshipObject;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinaryVarious> srvPersistXmlListAsmRelationshipsObject;

  public FactoryDiagramObject(Frame frameMain,
      IGuiMainUml<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> guiMain) {
    super(frameMain, guiMain);
    factoryAsmInstanceFull = new FactoryAsmInstanceFullLight(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    factoryAsmRelationshipObject = new FactoryAsmRelationshipBinaryVariousLight(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    srvPersistXmlListAsmObjectsFull = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<InstanceUml>>(factoryAsmInstanceFull, SrvSaveXmlInstance.NAMEXML_INSTANCEUML);
    srvPersistXmlListAsmRelationshipsObject = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinaryVarious>(factoryAsmRelationshipObject, SrvSaveXmlRelationshipBinaryVarious.NAMEXML_RELATIONSHIPBINARYVARIOUS);
    paletteDiagramObject = new PaletteDiagramObject();
    SaxDiagramObjectFiller<Graphics2D, SettingsDraw, Image, Frame> saxDiagramObjectFiller = 
        new SaxDiagramObjectFiller<Graphics2D, SettingsDraw, Image, Frame>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_PACKAGE, 
            getFactoryAsmComment(), getFactoryAsmText(), getFactoryAsmFrame(), getFactoryAsmRectangle(), getFactoryAsmLineUml(),
            factoryAsmInstanceFull, factoryAsmRelationshipObject);
    SrvSaveXmlDiagramUml<DiagramUml> srvSaveXmlDiagramUml = new SrvSaveXmlDiagramUml<DiagramUml>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_PACKAGE);
    srvPersistDiagramObject = new SrvPersistLightXmlAsmDiagramUml<DiagramUml>(srvSaveXmlDiagramUml,
        saxDiagramObjectFiller, SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_PACKAGE);
    DiagramUml diagramClass = new DiagramUml();
    asmDiagramObject = new AsmDiagramObject<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame>(diagramClass, guiMain, 
        srvPersistDiagramObject, getSrvPersistListAsmComments(), getSrvPersistListAsmTexts(), getFactoryAsmComment(),
        getFactoryAsmText(), getSrvPersistListAsmFrames(), getFactoryAsmFrame(), getSrvPersistListAsmRectangles(), getFactoryAsmRectangle(),
        getSrvPersistListAsmLineUmls(), getFactoryAsmLineUml(),
        srvPersistXmlListAsmObjectsFull, factoryAsmInstanceFull, srvPersistXmlListAsmRelationshipsObject, factoryAsmRelationshipObject);
    getFactoryAsmRectangle().getFactoryEditorRectangleUml().setObserverRectangleUmlChanged(asmDiagramObject);
    getFactoryAsmLineUml().getFactoryEditorLineUml().setObserverLineUmlChanged(asmDiagramObject);
    getFactoryAsmText().getFactoryEditorTextUml().setContainerShapesUmlForTie(asmDiagramObject);
    getFactoryAsmText().getFactoryEditorTextUml().setObserverTextUmlChanged(asmDiagramObject);
    saxDiagramObjectFiller.getSaxTextFiller().setContainerShapesUmlForTie(asmDiagramObject);
    getFactoryAsmFrame().getFactoryEditorFrameFull().setObserverModelChanged(asmDiagramObject);
    getFactoryAsmFrame().getFactoryEditorFrameFull().setContainerInteractiveElementsUml(asmDiagramObject);
    saxDiagramObjectFiller.getSaxFrameFullFiller().setContainerInteractiveElementsUml(asmDiagramObject);
    getFactoryAsmComment().getFactoryEditorCommentUml().setObserverCommentUmlChanged(asmDiagramObject);
    factoryAsmRelationshipObject.getFactoryEditorRelationship().setObserverElementChanged(asmDiagramObject);
    factoryAsmRelationshipObject.getFactoryEditorRelationship().setContainerShapes(asmDiagramObject);
    factoryAsmRelationshipObject.getSrvInteractiveRelationship().setContainerShapesFull(asmDiagramObject);
    saxDiagramObjectFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipStartFiller().setContainerShapesFullVarious(asmDiagramObject);
    saxDiagramObjectFiller.getSaxRelationshipBinaryVariousFiller().getSaxShapeRelationshipEndFiller().setContainerShapesFullVarious(asmDiagramObject);
    factoryAsmInstanceFull.getFactoryEditorInstanceUmlFull().setObserverInstanceChanged(asmDiagramObject);
    controllerDiagramObject = new ControllerDiagramObjectPersistLightXml<Frame>(asmDiagramObject, paletteDiagramObject, guiMain);
  }

  //SGS:
  public PaletteDiagramObject getPaletteDiagramObject() {
    return paletteDiagramObject;
  }

  public ControllerDiagramObjectPersistLightXml<Frame> getControllerDiagramObject() {
    return controllerDiagramObject;
  }

  public AsmDiagramObject<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> getAsmDiagramObject() {
    return asmDiagramObject;
  }

  public SrvPersistLightXmlAsmDiagramUml<DiagramUml> getSrvPersistDiagramObject() {
    return srvPersistDiagramObject;
  }

  public FactoryAsmInstanceFullLight getFactoryAsmInstanceFull() {
    return factoryAsmInstanceFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, ShapeFullVarious<InstanceUml>> getSrvPersistXmlListAsmObjectsFull() {
    return srvPersistXmlListAsmObjectsFull;
  }

  public FactoryAsmRelationshipBinaryVariousLight getFactoryAsmRelationshipObject() {
    return factoryAsmRelationshipObject;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, RelationshipBinaryVarious> getSrvPersistXmlListAsmRelationshipsObject() {
    return srvPersistXmlListAsmRelationshipsObject;
  }
}
