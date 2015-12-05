package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.graphics.Bitmap;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.ToolbarDiagram;
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.controller.ControllerDiagramSequencePersistLightXml;
import org.beigesoft.uml.diagram.assembly.AsmDiagramSequence;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.pojo.CombinedFragment;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.pojo.StateInvContin;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SaxDiagramSequenceFiller;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlCombinedFragment;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlCoregionFull;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlInteractionUse;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlLifeLineFull;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlMessageFull;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlStateInvContin;
import org.beigesoft.uml.ui.IGuiMainUml;

public class FactoryDiagramSequence extends AFactoryDiagramGeneral {

  private final AsmDiagramSequence<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> asmDiagramSequence;
  
  private final ControllerDiagramSequencePersistLightXml<Activity> controllerDiagramSequence;
  
  private final ToolbarDiagram toolbarDiagramSequence;
  
  private final SrvPersistLightXmlAsmDiagramUml<DiagramUml>   srvPersistDiagramSequence;
  
  private IEditor<DiagramUml> asmEditorPropertiesDiagramSequence;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, LifeLineFull<ShapeUmlWithName>> srvPersistListAsmLifeLine;

  private final FactoryAsmLifeLineFull factoryAsmLifeLineFull;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<MessageFull, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, MessageFull> srvPersistListAsmMessage;

  private final FactoryAsmMessageFull factoryAsmMessageFull;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CoregionFull, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, CoregionFull> srvPersistListAsmCoregion;

  private final FactoryAsmCoregionFull factoryAsmCoregionFull;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<StateInvContin, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, StateInvContin> srvPersistListAsmStateInvContin;

  private final FactoryAsmStateInvContin factoryAsmStateInvContin;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<InteractionUse, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, InteractionUse> srvPersistListAsmInteractionUse;

  private final FactoryAsmInteractionUse factoryAsmInteractionUse;
  
  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CombinedFragment, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, CombinedFragment> srvPersistListAsmCombinedFragment;

  private final FactoryAsmCombinedFragment factoryAsmCombinedFragment;
  
  public FactoryDiagramSequence(ToolbarDiagram toolbarDiagramSequence, SrvDraw srvDraw, 
      IContainerSrvsGui<Activity> containerGui, Activity activity, IGuiMainUml<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> guiMain) {
    super(srvDraw, containerGui, activity);
    this.toolbarDiagramSequence = toolbarDiagramSequence;
    factoryAsmLifeLineFull = new FactoryAsmLifeLineFull(srvDraw, containerGui, activity);
    factoryAsmMessageFull = new FactoryAsmMessageFull(srvDraw, containerGui, activity);
    factoryAsmCoregionFull = new FactoryAsmCoregionFull(srvDraw, containerGui, activity);
    factoryAsmStateInvContin = new FactoryAsmStateInvContin(srvDraw, containerGui, activity);
    factoryAsmInteractionUse = new FactoryAsmInteractionUse(srvDraw, containerGui, activity);
    factoryAsmCombinedFragment = new FactoryAsmCombinedFragment(srvDraw, containerGui, activity);
    SrvSaveXmlDiagramUml<DiagramUml> srvSaveXmlDiagramUml = new SrvSaveXmlDiagramUml<DiagramUml>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_SEQUENCE);
    SaxDiagramSequenceFiller<CanvasWithPaint, SettingsDraw, Bitmap, Activity> saxDiagramSequenceFiller =
        new SaxDiagramSequenceFiller<CanvasWithPaint, SettingsDraw, Bitmap, Activity>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_SEQUENCE, 
            getFactoryAsmComment(), getFactoryAsmText(), getFactoryAsmFrame(), getFactoryAsmRectangle(), 
            getFactoryAsmLine(), factoryAsmLifeLineFull, factoryAsmMessageFull, factoryAsmCoregionFull, factoryAsmStateInvContin,
            factoryAsmInteractionUse, factoryAsmCombinedFragment);
    srvPersistDiagramSequence = new SrvPersistLightXmlAsmDiagramUml<DiagramUml>(srvSaveXmlDiagramUml,
        saxDiagramSequenceFiller, SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_OBJECT);
    srvPersistListAsmLifeLine = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, LifeLineFull<ShapeUmlWithName>>(factoryAsmLifeLineFull, SrvSaveXmlLifeLineFull.NAMEXML_LIFELINEUML);
    srvPersistListAsmMessage = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<MessageFull,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, MessageFull>(factoryAsmMessageFull, SrvSaveXmlMessageFull.NAMEXML_MESSAGEUML);
    srvPersistListAsmCoregion = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CoregionFull,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, CoregionFull>(factoryAsmCoregionFull, SrvSaveXmlCoregionFull.NAMEXML_COREGIONUML);
    srvPersistListAsmStateInvContin = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<StateInvContin,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, StateInvContin>(factoryAsmStateInvContin, SrvSaveXmlStateInvContin.NAMEXML_STATEINVCONTIN);
    srvPersistListAsmInteractionUse = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<InteractionUse,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, InteractionUse>(factoryAsmInteractionUse, SrvSaveXmlInteractionUse.NAMEXML_INTERACTIONUSE);
    srvPersistListAsmCombinedFragment = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CombinedFragment,CanvasWithPaint,SettingsDraw,FileAndWriter>, CanvasWithPaint, SettingsDraw, CombinedFragment>(factoryAsmCombinedFragment, SrvSaveXmlCombinedFragment.NAMEXML_COMBINEDFRAGMENT);
    DiagramUml diagramSeq = new DiagramUml();
    asmDiagramSequence = new AsmDiagramSequence<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity>(diagramSeq, 
        guiMain, srvPersistDiagramSequence, getSrvPersistListAsmComments(), getSrvPersistListAsmTexts(), getFactoryAsmComment(), 
        getFactoryAsmText(), getSrvPersistListAsmFrames(), getFactoryAsmFrame(), getSrvPersistListAsmRectangles(), getFactoryAsmRectangle(),
        getSrvPersistListAsmLines(), getFactoryAsmLine(),
        srvPersistListAsmLifeLine, factoryAsmLifeLineFull, srvPersistListAsmMessage, factoryAsmMessageFull, srvPersistListAsmCoregion, factoryAsmCoregionFull,
        srvPersistListAsmStateInvContin, factoryAsmStateInvContin,
        srvPersistListAsmInteractionUse, factoryAsmInteractionUse, srvPersistListAsmCombinedFragment, factoryAsmCombinedFragment);
    getFactoryAsmRectangle().getFactoryEditorRectangle().setObserverRectangleUmlChanged(asmDiagramSequence);
    getFactoryAsmLine().getFactoryEditorLine().setObserverLineUmlChanged(asmDiagramSequence);
    getFactoryAsmText().getFactoryEditorText().setObserverTextUmlChanged(asmDiagramSequence);
    getFactoryAsmComment().getFactoryEditorComment().setObserverCommentUmlChanged(asmDiagramSequence);
    getFactoryAsmFrame().getFactoryEditorFrame().setObserverModelChanged(asmDiagramSequence);
    getFactoryAsmFrame().getFactoryEditorFrame().setContainerInteractiveElementsUml(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxFrameFullFiller().setContainerInteractiveElementsUml(asmDiagramSequence);
    factoryAsmLifeLineFull.getFactoryEditorLifeLineFull().setObserverLifeLineFullUmlChanged(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxLifeLineFullFiller().setContainerFramesUml(asmDiagramSequence);
    factoryAsmMessageFull.getFactoryEditorMessageFull().setObserverMessageFullUmlChanged(asmDiagramSequence);
    factoryAsmMessageFull.getFactoryEditorMessageFull().setContainerInteractionUses(asmDiagramSequence);
    factoryAsmMessageFull.getFactoryEditorMessageFull().setContainerAsmLifeLinesFull(asmDiagramSequence);
    factoryAsmCoregionFull.getFactoryEditorCoregionFull().setObserverCoregionFullChanged(asmDiagramSequence);
    factoryAsmCoregionFull.getFactoryEditorCoregionFull().setContainerAsmMessagesFull(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxCoregionFullFiller().setContainerAsmLifeLinesFull(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxCoregionFullFiller().setContainerAsmMessagesFull(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxMessageFullFiller().setContainerFramesUml(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxMessageFullFiller().setContainerAsmLifeLinesFull(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxMessageFullFiller().setContainerInteractionUses(asmDiagramSequence);
    factoryAsmStateInvContin.getFactoryEditorStateInvContin().setObserverStateInvContinUmlChanged(asmDiagramSequence);
    factoryAsmInteractionUse.getFactoryEditorInteractionUse().setObserverInteractionUseUmlChanged(asmDiagramSequence);
    factoryAsmCombinedFragment.getFactoryEditorCombinedFragment().setObserverCombinedFragmentUmlChanged(asmDiagramSequence);
    controllerDiagramSequence = new ControllerDiagramSequencePersistLightXml<Activity>(asmDiagramSequence, toolbarDiagramSequence,
        guiMain);
  }

  //SGS:
  public ControllerDiagramSequencePersistLightXml<Activity> getControllerDiagramSequence() {
    return controllerDiagramSequence;
  }

  public ToolbarDiagram getToolbarDiagramSequence() {
    return toolbarDiagramSequence;
  }

  public SrvPersistLightXmlAsmDiagramUml<DiagramUml> getSrvPersistDiagramSequence() {
    return srvPersistDiagramSequence;
  }

  public AsmDiagramSequence<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity> getAsmDiagramSequence() {
    return asmDiagramSequence;
  }

  public IEditor<DiagramUml> getAsmEditorPropertiesDiagramSequence() {
    return asmEditorPropertiesDiagramSequence;
  }

  public void setAsmEditorPropertiesDiagramSequence(
      IEditor<DiagramUml> asmEditorPropertiesDiagramSequence) {
    this.asmEditorPropertiesDiagramSequence = asmEditorPropertiesDiagramSequence;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, LifeLineFull<ShapeUmlWithName>> getSrvPersistListAsmLifeLine() {
    return srvPersistListAsmLifeLine;
  }

  public FactoryAsmLifeLineFull getFactoryAsmLifeLineFull() {
    return factoryAsmLifeLineFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<MessageFull, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, MessageFull> getSrvPersistListAsmMessage() {
    return srvPersistListAsmMessage;
  }

  public FactoryAsmMessageFull getFactoryAsmMessageFull() {
    return factoryAsmMessageFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<StateInvContin, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, StateInvContin> getSrvPersistListAsmStateInvContin() {
    return srvPersistListAsmStateInvContin;
  }

  public FactoryAsmStateInvContin getFactoryAsmStateInvContin() {
    return factoryAsmStateInvContin;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<InteractionUse, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, InteractionUse> getSrvPersistListAsmInteractionUse() {
    return srvPersistListAsmInteractionUse;
  }

  public FactoryAsmInteractionUse getFactoryAsmInteractionUse() {
    return factoryAsmInteractionUse;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CombinedFragment, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, CombinedFragment> getSrvPersistListAsmCombinedFragment() {
    return srvPersistListAsmCombinedFragment;
  }

  public FactoryAsmCombinedFragment getFactoryAsmCombinedFragment() {
    return factoryAsmCombinedFragment;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CoregionFull, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, CoregionFull> getSrvPersistListAsmCoregion() {
    return srvPersistListAsmCoregion;
  }

  public FactoryAsmCoregionFull getFactoryAsmCoregionFull() {
    return factoryAsmCoregionFull;
  }
}
