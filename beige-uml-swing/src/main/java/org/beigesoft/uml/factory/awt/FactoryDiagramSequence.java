package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
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
import org.beigesoft.uml.ui.swing.PaletteDiagramSequence;

public class FactoryDiagramSequence extends AFactoryDiagramGeneral {

  private final ControllerDiagramSequencePersistLightXml<Frame> controllerDiagramSequence;
  
  private final PaletteDiagramSequence paletteDiagramSequence;
  
  private final AsmDiagramSequence<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> asmDiagramSequence;

  private final SrvPersistLightXmlAsmDiagramUml<DiagramUml> srvPersistDiagramSequence;
  
  private final FactoryAsmLifeLineFull factoryAsmLifeLineFull;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, LifeLineFull<ShapeUmlWithName>> srvPersistXmlListAsmLifeLineFull;

  private final FactoryAsmMessageFull factoryAsmMessageFull;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<MessageFull, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, MessageFull> srvPersistXmlListAsmMessageFull;

  private final FactoryAsmCoregionFull factoryAsmCoregionFull;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CoregionFull, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CoregionFull> srvPersistXmlListAsmCoregionFull;

  private final FactoryAsmStateInvContin factoryAsmStateInvContin;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<StateInvContin, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, StateInvContin> srvPersistXmlListAsmStateInvContin;

  private final FactoryAsmInteractionUse factoryAsmInteractionUse;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<InteractionUse, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, InteractionUse> srvPersistXmlListAsmInteractionUse;

  private final FactoryAsmCombinedFragment factoryAsmCombinedFragment;

  private final SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CombinedFragment, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CombinedFragment> srvPersistXmlListAsmCombinedFragment;

  public FactoryDiagramSequence(Frame frameMain,
      IGuiMainUml<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> guiMain) {
    super(frameMain, guiMain);
    factoryAsmLifeLineFull = new FactoryAsmLifeLineFull(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    srvPersistXmlListAsmLifeLineFull = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, LifeLineFull<ShapeUmlWithName>>(factoryAsmLifeLineFull, SrvSaveXmlLifeLineFull.NAMEXML_LIFELINEUML);
    factoryAsmMessageFull = new FactoryAsmMessageFull(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    srvPersistXmlListAsmMessageFull = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<MessageFull, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, MessageFull>(factoryAsmMessageFull, SrvSaveXmlMessageFull.NAMEXML_MESSAGEUML);
    factoryAsmCoregionFull = new FactoryAsmCoregionFull(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    srvPersistXmlListAsmCoregionFull = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CoregionFull, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CoregionFull>(factoryAsmCoregionFull, SrvSaveXmlCoregionFull.NAMEXML_COREGIONUML);
    factoryAsmStateInvContin = new FactoryAsmStateInvContin(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    srvPersistXmlListAsmStateInvContin = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<StateInvContin, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, StateInvContin>(factoryAsmStateInvContin, SrvSaveXmlStateInvContin.NAMEXML_STATEINVCONTIN);
    factoryAsmInteractionUse = new FactoryAsmInteractionUse(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    srvPersistXmlListAsmInteractionUse = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<InteractionUse, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, InteractionUse>(factoryAsmInteractionUse, SrvSaveXmlInteractionUse.NAMEXML_INTERACTIONUSE);
    factoryAsmCombinedFragment = new FactoryAsmCombinedFragment(guiMain.getSrvDraw(), 
        guiMain.getGuiSrvs().getSrvI18n(), guiMain.getGuiSrvs().getSrvDialog(), guiMain.getSettingsGraphicUml(), frameMain);
    srvPersistXmlListAsmCombinedFragment = new SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CombinedFragment, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CombinedFragment>(factoryAsmCombinedFragment, SrvSaveXmlCombinedFragment.NAMEXML_COMBINEDFRAGMENT);
    paletteDiagramSequence = new PaletteDiagramSequence();
    SaxDiagramSequenceFiller<Graphics2D, SettingsDraw, Image, Frame> saxDiagramSequenceFiller = 
        new SaxDiagramSequenceFiller<Graphics2D, SettingsDraw, Image, Frame>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_SEQUENCE, 
            getFactoryAsmComment(), getFactoryAsmText(), getFactoryAsmFrame(), getFactoryAsmRectangle(), getFactoryAsmLineUml(),
            factoryAsmLifeLineFull, factoryAsmMessageFull, factoryAsmCoregionFull, factoryAsmStateInvContin, factoryAsmInteractionUse, factoryAsmCombinedFragment);
    SrvSaveXmlDiagramUml<DiagramUml> srvSaveXmlDiagramUml = new SrvSaveXmlDiagramUml<DiagramUml>(SrvSaveXmlDiagramUml.NAME_DIAGRAM_SEQUENCE);
    srvPersistDiagramSequence = new SrvPersistLightXmlAsmDiagramUml<DiagramUml>(srvSaveXmlDiagramUml,
        saxDiagramSequenceFiller, SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_SEQUENCE);
    DiagramUml diagramClass = new DiagramUml();
    asmDiagramSequence = new AsmDiagramSequence<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame>(diagramClass, guiMain, 
        srvPersistDiagramSequence, getSrvPersistListAsmComments(), getSrvPersistListAsmTexts(), getFactoryAsmComment(),
        getFactoryAsmText(), getSrvPersistListAsmFrames(), getFactoryAsmFrame(), getSrvPersistListAsmRectangles(), getFactoryAsmRectangle(),
        getSrvPersistListAsmLineUmls(), getFactoryAsmLineUml(),
        srvPersistXmlListAsmLifeLineFull, factoryAsmLifeLineFull, srvPersistXmlListAsmMessageFull, factoryAsmMessageFull, srvPersistXmlListAsmCoregionFull, factoryAsmCoregionFull, srvPersistXmlListAsmStateInvContin, factoryAsmStateInvContin,
        srvPersistXmlListAsmInteractionUse, factoryAsmInteractionUse, srvPersistXmlListAsmCombinedFragment, factoryAsmCombinedFragment);
    getFactoryAsmRectangle().getFactoryEditorRectangleUml().setObserverRectangleUmlChanged(asmDiagramSequence);
    getFactoryAsmLineUml().getFactoryEditorLineUml().setObserverLineUmlChanged(asmDiagramSequence);
    getFactoryAsmText().getFactoryEditorTextUml().setObserverTextUmlChanged(asmDiagramSequence);
    getFactoryAsmText().getFactoryEditorTextUml().setObserverTextUmlChanged(asmDiagramSequence);
    getFactoryAsmFrame().getFactoryEditorFrameFull().setObserverModelChanged(asmDiagramSequence);
    getFactoryAsmFrame().getFactoryEditorFrameFull().setContainerInteractiveElementsUml(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxFrameFullFiller().setContainerInteractiveElementsUml(asmDiagramSequence);
    getFactoryAsmComment().getFactoryEditorCommentUml().setObserverCommentUmlChanged(asmDiagramSequence);
    factoryAsmLifeLineFull.getFactoryEditorLifeLineUmlFull().setObserverModelChanged(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxLifeLineFullFiller().setContainerFramesUml(asmDiagramSequence);
    factoryAsmMessageFull.getFactoryEditorMessageUmlFull().setObserverModelChanged(asmDiagramSequence);
    factoryAsmMessageFull.getFactoryEditorMessageUmlFull().setContainerInteractionUses(asmDiagramSequence);
    factoryAsmMessageFull.getFactoryEditorMessageUmlFull().setContainerAsmLifeLinesFull(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxMessageFullFiller().setContainerFramesUml(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxMessageFullFiller().setContainerAsmLifeLinesFull(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxMessageFullFiller().setContainerInteractionUses(asmDiagramSequence);
    factoryAsmCoregionFull.getFactoryEditorCoregionFull().setObserverModelChanged(asmDiagramSequence);
    factoryAsmCoregionFull.getFactoryEditorCoregionFull().setContainerAsmMessagesFull(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxCoregionFullFiller().setContainerAsmLifeLinesFull(asmDiagramSequence);
    saxDiagramSequenceFiller.getSaxCoregionFullFiller().setContainerAsmMessagesFull(asmDiagramSequence);
    factoryAsmStateInvContin.getFactoryEditorStateInvContin().setObserverStateInvContinChanged(asmDiagramSequence);
    factoryAsmInteractionUse.getFactoryEditorInteractionUse().setObserverInteractionUseChanged(asmDiagramSequence);
    factoryAsmCombinedFragment.getFactoryEditorCombinedFragment().setObserverCombinedFragmentChanged(asmDiagramSequence);
    controllerDiagramSequence = new ControllerDiagramSequencePersistLightXml<Frame>(asmDiagramSequence, paletteDiagramSequence, guiMain);
  }

  //SGS:
  public PaletteDiagramSequence getPaletteDiagramSequence() {
    return paletteDiagramSequence;
  }

  public ControllerDiagramSequencePersistLightXml<Frame> getControllerDiagramSequence() {
    return controllerDiagramSequence;
  }

  public AsmDiagramSequence<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> getAsmDiagramSequence() {
    return asmDiagramSequence;
  }

  public SrvPersistLightXmlAsmDiagramUml<DiagramUml> getSrvPersistDiagramSequence() {
    return srvPersistDiagramSequence;
  }

  public FactoryAsmLifeLineFull getFactoryAsmLifeLineFull() {
    return factoryAsmLifeLineFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, LifeLineFull<ShapeUmlWithName>> getSrvPersistXmlListAsmLifeLineFull() {
    return srvPersistXmlListAsmLifeLineFull;
  }

  public FactoryAsmMessageFull getFactoryAsmMessageFull() {
    return factoryAsmMessageFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<MessageFull, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, MessageFull> getSrvPersistXmlListAsmMessageFull() {
    return srvPersistXmlListAsmMessageFull;
  }

  public FactoryAsmStateInvContin getFactoryAsmStateInvContin() {
    return factoryAsmStateInvContin;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<StateInvContin, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, StateInvContin> getSrvPersistXmlListAsmStateInvContin() {
    return srvPersistXmlListAsmStateInvContin;
  }

  public FactoryAsmInteractionUse getFactoryAsmInteractionUse() {
    return factoryAsmInteractionUse;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<InteractionUse, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, InteractionUse> getSrvPersistXmlListAsmInteractionUse() {
    return srvPersistXmlListAsmInteractionUse;
  }

  public FactoryAsmCombinedFragment getFactoryAsmCombinedFragment() {
    return factoryAsmCombinedFragment;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CombinedFragment, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CombinedFragment> getSrvPersistXmlListAsmCombinedFragment() {
    return srvPersistXmlListAsmCombinedFragment;
  }

  public FactoryAsmCoregionFull getFactoryAsmCoregionFull() {
    return factoryAsmCoregionFull;
  }

  public SrvPersistLightXmlListElementsUml<IAsmElementUmlInteractive<CoregionFull, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, CoregionFull> getSrvPersistXmlListAsmCoregionFull() {
    return srvPersistXmlListAsmCoregionFull;
  }
}
