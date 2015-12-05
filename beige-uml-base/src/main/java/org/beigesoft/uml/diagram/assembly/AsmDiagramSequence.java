package org.beigesoft.uml.diagram.assembly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.model.EFrameRoleForMessage;
import org.beigesoft.uml.pojo.CombinedFragment;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.pojo.StateInvContin;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.comparator.ComparatorAsmListElementsUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistListElementsUml;
import org.beigesoft.uml.ui.IGuiMainUml;

public class AsmDiagramSequence<DRI, SD extends ISettingsDraw, IMG, PRI, DLI> 
    extends AsmDiagramUmlInteractive<DiagramUml, DRI, SD, IMG, PRI, DLI, 
    CommentUml, IAsmElementUmlInteractive<CommentUml, DRI, SD, PRI>, 
    TextUml, IAsmElementUmlInteractive<TextUml, DRI, SD, PRI>>
    implements IAsmDiagramSequence<DiagramUml, DRI, SD, IMG, PRI> {

  private final IAsmListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI>, DRI, SD, IMG, PRI, LifeLineFull<ShapeUmlWithName>> asmListAsmLifeLine;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI>, DRI, SD, PRI, LifeLineFull<ShapeUmlWithName>> srvPersistListAsmLifeLine;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI>, DRI, SD, PRI, LifeLineFull<ShapeUmlWithName>> factoryAsmLifeLineFull;

  private final IAsmListElementsUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI>, DRI, SD, IMG, PRI, MessageFull> asmListAsmMessage;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI>, DRI, SD, PRI, MessageFull> srvPersistListAsmMessage;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI>, DRI, SD, PRI, MessageFull> factoryAsmMessageFull;

  private final IAsmListElementsUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, PRI>, DRI, SD, IMG, PRI, CoregionFull> asmListAsmCoregionFull;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, PRI>, DRI, SD, PRI, CoregionFull> srvPersistListAsmCoregionFull;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, PRI>, DRI, SD, PRI, CoregionFull> factoryAsmCoregionFull;

  private final IAsmListElementsUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, PRI>, DRI, SD, IMG, PRI, StateInvContin> asmListAsmStateInvContin;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, PRI>, DRI, SD, PRI, StateInvContin> srvPersistListAsmStateInvContin;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, PRI>, DRI, SD, PRI, StateInvContin> factoryAsmStateInvContin;

  private final IAsmListElementsUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, PRI>, DRI, SD, IMG, PRI, InteractionUse> asmListAsmInteractionUse;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, PRI>, DRI, SD, PRI, InteractionUse> srvPersistListAsmInteractionUse;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, PRI>, DRI, SD, PRI, InteractionUse> factoryAsmInteractionUse;

  private final IAsmListElementsUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, PRI>, DRI, SD, IMG, PRI, CombinedFragment> asmListAsmCombinedFragment;

  private final ISrvPersistListElementsUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, PRI>, DRI, SD, PRI, CombinedFragment> srvPersistListAsmCombinedFragment;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, PRI>, DRI, SD, PRI, CombinedFragment> factoryAsmCombinedFragment;

  public AsmDiagramSequence(
      DiagramUml diagramUml,
      IGuiMainUml<DRI, SD, IMG, PRI, DLI> guiApp,
      ISrvPersistAsmDiagramUml<DiagramUml, PRI> persistDiagramUmlSrv,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<CommentUml, DRI, SD, PRI>, DRI, SD, PRI, CommentUml> persistListCommentsUmlSrv,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<TextUml, DRI, SD, PRI>, DRI, SD, PRI, TextUml> persistListTextsUmlSrv,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<CommentUml, DRI, SD, PRI>, DRI, SD, PRI, CommentUml> factoryCommentUml,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<TextUml, DRI, SD, PRI>, DRI, SD, PRI, TextUml> factoryTextUml,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI>, DRI, SD, PRI, ContainerFull<FrameUml>> srvPersistXmlListFrames,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI>, DRI, SD, PRI, ContainerFull<FrameUml>> factoryFrame,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> srvPersistXmlListRectangles,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> factoryAsmRectangle,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> srvPersistXmlListLines,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> factoryAsmLine,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI>, DRI, SD, PRI, LifeLineFull<ShapeUmlWithName>> srvPersistListAsmLifeLinesUml,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI>, DRI, SD, PRI, LifeLineFull<ShapeUmlWithName>> factoryAsmLifeLinesUml,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI>, DRI, SD, PRI, MessageFull> srvPersistListAsmMessagesUml,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI>, DRI, SD, PRI, MessageFull> factoryAsmMessagesUml,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, PRI>, DRI, SD, PRI, CoregionFull> srvPersistListAsmCoregionFullsUml,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, PRI>, DRI, SD, PRI, CoregionFull> factoryAsmCoregionFullsUml,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, PRI>, DRI, SD, PRI, StateInvContin> srvPersistListAsmStateInvContins,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, PRI>, DRI, SD, PRI, StateInvContin> factoryAsmStateInvContins,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, PRI>, DRI, SD, PRI, InteractionUse> srvPersistListAsmInteractionUses,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, PRI>, DRI, SD, PRI, InteractionUse> factoryAsmInteractionUses,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, PRI>, DRI, SD, PRI, CombinedFragment> srvPersistListAsmCombinedFragments,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, PRI>, DRI, SD, PRI, CombinedFragment> factoryAsmCombinedFragments) {
    super(diagramUml, guiApp, persistDiagramUmlSrv, persistListCommentsUmlSrv,
        persistListTextsUmlSrv, factoryCommentUml, factoryTextUml, srvPersistXmlListFrames, factoryFrame, 
        srvPersistXmlListRectangles, factoryAsmRectangle, srvPersistXmlListLines, factoryAsmLine);
    this.srvPersistListAsmLifeLine = srvPersistListAsmLifeLinesUml;
    this.factoryAsmLifeLineFull = factoryAsmLifeLinesUml;
    this.asmListAsmLifeLine = new AsmListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI>, DRI, SD, IMG, PRI, LifeLineFull<ShapeUmlWithName>>(guiApp, srvPersistListAsmLifeLinesUml);
    asmListAsmLifeLine.setWeight(1000);//cause cross-reference to frame
    getAssembliesListElementsUml().add(asmListAsmLifeLine);
    this.srvPersistListAsmMessage = srvPersistListAsmMessagesUml;
    this.factoryAsmMessageFull = factoryAsmMessagesUml;
    this.asmListAsmMessage = new AsmListElementsUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI>, DRI, SD, IMG, PRI, MessageFull>(guiApp, srvPersistListAsmMessagesUml);
    asmListAsmMessage.setWeight(1003);//cause may contain frame and lifeline
    getAssembliesListElementsUml().add(asmListAsmMessage);
    this.srvPersistListAsmCoregionFull = srvPersistListAsmCoregionFullsUml;
    this.factoryAsmCoregionFull = factoryAsmCoregionFullsUml;
    this.asmListAsmCoregionFull = new AsmListElementsUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, PRI>, DRI, SD, IMG, PRI, CoregionFull>(guiApp, srvPersistListAsmCoregionFullsUml);
    asmListAsmCoregionFull.setWeight(1103);//cause contain frame and lifeline and message
    getAssembliesListElementsUml().add(asmListAsmCoregionFull);
    this.srvPersistListAsmStateInvContin = srvPersistListAsmStateInvContins;
    this.factoryAsmStateInvContin = factoryAsmStateInvContins;
    this.asmListAsmStateInvContin = new AsmListElementsUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, PRI>, DRI, SD, IMG, PRI, StateInvContin>(guiApp, srvPersistListAsmStateInvContins);
    asmListAsmStateInvContin.setWeight(15);
    getAssembliesListElementsUml().add(asmListAsmStateInvContin);
    this.srvPersistListAsmInteractionUse = srvPersistListAsmInteractionUses;
    this.factoryAsmInteractionUse = factoryAsmInteractionUses;
    this.asmListAsmInteractionUse = new AsmListElementsUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, PRI>, DRI, SD, IMG, PRI, InteractionUse>(guiApp, srvPersistListAsmInteractionUses);
    asmListAsmInteractionUse.setWeight(16);
    getAssembliesListElementsUml().add(asmListAsmInteractionUse);
    this.srvPersistListAsmCombinedFragment = srvPersistListAsmCombinedFragments;
    this.factoryAsmCombinedFragment = factoryAsmCombinedFragments;
    this.asmListAsmCombinedFragment = new AsmListElementsUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, PRI>, DRI, SD, IMG, PRI, CombinedFragment>(guiApp, srvPersistListAsmCombinedFragments);
    asmListAsmCombinedFragment.setWeight(18);
    getAssembliesListElementsUml().add(asmListAsmCombinedFragment);
    ComparatorAsmListElementsUml comparatorAssemblyListElementsUml = new ComparatorAsmListElementsUml();
    Collections.sort(getAssembliesListElementsUml(), comparatorAssemblyListElementsUml);
  }

  @Override
  public void tryToCreateLifeLineAt(int screenX, int screenY) throws Exception {
    IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> asmFrameFull = findFrameAt(screenX, screenY);
    if(asmFrameFull == null) {
      getGuiApp().getGuiSrvs().getSrvDialog().errorMessage(getGuiApp().getDialogInstrument(), "Please click inside a frame!", "Error!");
    }
    else {
      IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI> asmLifeLine = getFactoryAsmLifeLineFull().createAsmElementUml();
      asmLifeLine.getElementUml().getLifeLine().getPointStart().setX(UtilsGraphMath.toRealX(getGuiApp().getSettingsGraphicUml(), screenX));
      asmLifeLine.getElementUml().getLifeLine().getPointStart().setY(UtilsGraphMath.toRealY(getGuiApp().getSettingsGraphicUml(), screenY));
      asmLifeLine.getElementUml().setAsmFrameFull(asmFrameFull);
      asmLifeLine.getElementUml().setLineEndY(asmLifeLine.getElementUml().getAsmFrameFull().getElementUml().getContainer().getPointStart().getY() + 
          asmLifeLine.getElementUml().getAsmFrameFull().getElementUml().getContainer().getHeight() 
          - getGuiApp().getSettingsGraphicUml().getOffsetLifeLineFromBottom());
      asmFrameFull.getElementUml().getElements().add(asmLifeLine);
      makeElementSelected(asmLifeLine);
      asmListAsmLifeLine.addElementUml(asmLifeLine);
      asmLifeLine.startEdit();
      setIsChanged(true);
      refreshGui();
    }
  }

  @Override
  public void tryToCreateMessageAt(int screenX, int screenY) throws Exception {
    IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> asmFrameFull = findFrameAt(screenX, screenY);
    if(asmFrameFull == null) {
      getGuiApp().getGuiSrvs().getSrvDialog().errorMessage(getGuiApp().getDialogInstrument(), "Please click inside a frame!", "Error!");
    }
    else {
      double realX = UtilsGraphMath.toRealX(getGuiApp().getSettingsGraphicUml(), screenX);
      IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI> asmLifeLineStart = null;
      IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI> asmLifeLineEnd = null;
      for(IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI> asmLifeLineFull : asmListAsmLifeLine.getListElementsUml()) {
        if(asmLifeLineFull.getElementUml().getAsmFrameFull() == asmFrameFull && asmLifeLineFull.getElementUml().getPointStart().getX() < realX) {
          asmLifeLineStart = asmLifeLineFull;
        }
        else if(asmLifeLineEnd == null && 
            asmLifeLineFull.getElementUml().getAsmFrameFull() == asmFrameFull && 
            asmLifeLineFull.getElementUml().getPointStart().getX() > realX) {
          asmLifeLineEnd = asmLifeLineFull;
        }
      }
      if(asmLifeLineEnd != null) {
        IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI> asmMessage = getFactoryAsmMessageFull().createAsmElementUml();
        asmMessage.getElementUml().setY(UtilsGraphMath.toRealY(getGuiApp().getSettingsGraphicUml(), screenY));
        asmMessage.getElementUml().setAsmLifeLineFullEnd(asmLifeLineEnd);
        if(asmLifeLineStart != null) {
          asmMessage.getElementUml().setAsmLifeLineFullStart(asmLifeLineStart);
        }
        else {
          asmMessage.getElementUml().setFrameRole(EFrameRoleForMessage.IS_START);
        }
        asmFrameFull.getElementUml().getElements().add(asmMessage);
        asmMessage.getElementUml().setItsFrame(asmFrameFull);
        makeElementSelected(asmMessage);
        asmListAsmMessage.addElementUml(asmMessage);
        asmMessage.startEdit();
        setIsChanged(true);
        refreshGui();
      }
      else if(asmLifeLineStart != null) {
        IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI> asmMessage = getFactoryAsmMessageFull().createAsmElementUml();
        asmMessage.getElementUml().setY(UtilsGraphMath.toRealY(getGuiApp().getSettingsGraphicUml(), screenY));
        asmMessage.getElementUml().setAsmLifeLineFullStart(asmLifeLineStart);
        asmMessage.getElementUml().setFrameRole(EFrameRoleForMessage.IS_END);
        asmFrameFull.getElementUml().getElements().add(asmMessage);
        asmMessage.getElementUml().setItsFrame(asmFrameFull);
        makeElementSelected(asmMessage);
        asmListAsmMessage.addElementUml(asmMessage);
        asmMessage.startEdit();
        setIsChanged(true);
        refreshGui();
      }
      else {
        getGuiApp().getGuiSrvs().getSrvDialog().errorMessage(getGuiApp().getDialogInstrument(), 
            "Please click between two lifelines or before a lifelene!", "Error!");
      }
    }
  }

  @Override
  public void tryToCreateCoregionMessagesAt(int screenX, int screenY) throws Exception {
    IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLine = findLifeLineFullAt(screenX, screenY);
    if(asmLifeLine == null) {
      getGuiApp().getGuiSrvs().getSrvDialog().errorMessage(getGuiApp().getDialogInstrument(), 
          "Please click inside a lifeline!", "Error!");
      return;
    }
    IAsmElementUmlInteractive<CoregionFull, DRI, SD, PRI> asmCor = factoryAsmCoregionFull.createAsmElementUml();
    asmCor.getElementUml().setAsmLifeLineFull(asmLifeLine);
    makeElementSelected(asmCor);
    asmListAsmCoregionFull.addElementUml(asmCor);
    tryToAddIntoContainer(asmCor, screenX, screenY);
    asmCor.startEdit();
    setIsChanged(true);
    refreshGui();
  }
  
  @Override
  public void createStateInvContAt(int screenX, int screenY) throws Exception {
    IAsmElementUmlInteractive<StateInvContin, DRI, SD, PRI> asmStContin = factoryAsmStateInvContin.createAsmElementUml();
    asmStContin.getElementUml().getPointStart().setX(UtilsGraphMath.toRealX(getGuiApp().getSettingsGraphicUml(), screenX));
    asmStContin.getElementUml().getPointStart().setY(UtilsGraphMath.toRealY(getGuiApp().getSettingsGraphicUml(), screenY));
    makeElementSelected(asmStContin);
    asmListAsmStateInvContin.addElementUml(asmStContin);
    tryToAddIntoContainer(asmStContin, screenX, screenY);
    asmStContin.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public void createInteractionUseAt(int screenX, int screenY) {
    IAsmElementUmlInteractive<InteractionUse, DRI, SD, PRI> asmStContin = factoryAsmInteractionUse.createAsmElementUml();
    asmStContin.getElementUml().getPointStart().setX(UtilsGraphMath.toRealX(getGuiApp().getSettingsGraphicUml(), screenX));
    asmStContin.getElementUml().getPointStart().setY(UtilsGraphMath.toRealY(getGuiApp().getSettingsGraphicUml(), screenY));
    makeElementSelected(asmStContin);
    asmListAsmInteractionUse.addElementUml(asmStContin);
    tryToAddIntoContainer(asmStContin, screenX, screenY);
    asmStContin.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public void createCombinedFragmentAt(int screenX, int screenY) {
    IAsmElementUmlInteractive<CombinedFragment, DRI, SD, PRI> asmStContin = factoryAsmCombinedFragment.createAsmElementUml();
    asmStContin.getElementUml().getPointStart().setX(UtilsGraphMath.toRealX(getGuiApp().getSettingsGraphicUml(), screenX));
    asmStContin.getElementUml().getPointStart().setY(UtilsGraphMath.toRealY(getGuiApp().getSettingsGraphicUml(), screenY));
    makeElementSelected(asmStContin);
    asmListAsmCombinedFragment.addElementUml(asmStContin);
    tryToAddIntoContainer(asmStContin, screenX, screenY);
    asmStContin.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> findLifeLineFullById(
      UUID id) {
    for(IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI> asmLifeLine : asmListAsmLifeLine.getListElementsUml()) {
      if(asmLifeLine.getElementUml().getId().equals(id)) {
        return asmLifeLine;
      }
    }
    return null;
  }

  @Override
  public IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> findLifeLineFullAt(int screenX,
      int screenY) {
    for(IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI> asmLifeLine : asmListAsmLifeLine.getListElementsUml()) {
      if(asmLifeLine.isContainsScreenPoint(screenX, screenY)) {
        return asmLifeLine;
      }
    }
    return null;
  }

  @Override
  public Collection<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>> getLifeLinesFullExcept(
      UUID id) {
    List<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>> result = new ArrayList<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>,?,?,?>>();
    for(IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI> asmLifeLine : asmListAsmLifeLine.getListElementsUml()) {
      if(!asmLifeLine.getElementUml().getId().equals(id)) {
        result.add(asmLifeLine);
      }
    }
    return result;
  }

  @Override
  public IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> findAsmInteractionUseById(
      UUID id) {
    for(IAsmElementUmlInteractive<? extends InteractionUse, DRI, SD, PRI> asmIntUse : asmListAsmInteractionUse.getListElementsUml()) {
      if(asmIntUse.getElementUml().getId().equals(id)) {
        return asmIntUse;
      }
    }
    for(IAsmElementUmlInteractive<? extends InteractionUse, DRI, SD, PRI> asmIntUse : asmListAsmCombinedFragment.getListElementsUml()) {
      if(asmIntUse.getElementUml().getId().equals(id)) {
        return asmIntUse;
      }
    }
    return null;
  }

  @Override
  public Collection<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>> getAsmInteractionUsesExcept(
      UUID id) {
    List<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>> result = new ArrayList<IAsmElementUmlInteractive<? extends InteractionUse,?,?,?>>();
    for(IAsmElementUmlInteractive<? extends InteractionUse, DRI, SD, PRI> asmIntUse : asmListAsmInteractionUse.getListElementsUml()) {
      if(!asmIntUse.getElementUml().getId().equals(id)) {
        result.add(asmIntUse);
      }
    }
    for(IAsmElementUmlInteractive<? extends InteractionUse, DRI, SD, PRI> asmIntUse : asmListAsmCombinedFragment.getListElementsUml()) {
      if(!asmIntUse.getElementUml().getId().equals(id)) {
        result.add(asmIntUse);
      }
    }
    return result;
  }

  @Override
  public long getVersionAsmInteractionUses() {
    return Math.max(asmListAsmInteractionUse.getVersionElementsUml(), 
        asmListAsmCombinedFragment.getVersionElementsUml());
  }

  @Override
  public long getVersionAsmLifeLinesFull() {
    return asmListAsmLifeLine.getVersionElementsUml();
  }

  @Override
  public IAsmElementUmlInteractive<MessageFull, ?, ?, ?> findMessageFullById(UUID id) {
    for(IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI> asmMsg : asmListAsmMessage.getListElementsUml()) {
      if(asmMsg.getElementUml().getId().equals(id)) {
        return asmMsg;
      }
    }
    return null;
  }

  @Override
  public long getVersionAsmMessagesFull() {
    return asmListAsmMessage.getVersionElementsUml();
  }

  @Override
  public Collection<IAsmElementUmlInteractive<MessageFull, ?, ?, ?>> getMessagesFullForLifeLine(UUID id) {
    List<IAsmElementUmlInteractive<MessageFull, ?, ?, ?>> result = new ArrayList<IAsmElementUmlInteractive<MessageFull,?,?,?>>();
    for(IAsmElementUmlInteractive<MessageFull, ?, ?, ?> asmMsg : asmListAsmMessage.getListElementsUml()) {
      if((asmMsg.getElementUml().getAsmLifeLineFullEnd() != null && 
          asmMsg.getElementUml().getAsmLifeLineFullEnd().getElementUml().getId().equals(id)) ||
          (asmMsg.getElementUml().getAsmLifeLineFullStart() != null && 
          asmMsg.getElementUml().getAsmLifeLineFullStart().getElementUml().getId().equals(id))) {
        result.add(asmMsg);
      }
    }
    return result;
  }

  //SGS:
  public IAsmListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI>, DRI, SD, IMG, PRI, LifeLineFull<ShapeUmlWithName>> getAsmListAsmLifeLine() {
    return asmListAsmLifeLine;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI>, DRI, SD, PRI, LifeLineFull<ShapeUmlWithName>> getSrvPersistListAsmLifeLine() {
    return srvPersistListAsmLifeLine;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, PRI>, DRI, SD, PRI, LifeLineFull<ShapeUmlWithName>> getFactoryAsmLifeLineFull() {
    return factoryAsmLifeLineFull;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI>, DRI, SD, IMG, PRI, MessageFull> getAsmListAsmMessage() {
    return asmListAsmMessage;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI>, DRI, SD, PRI, MessageFull> getSrvPersistListAsmMessage() {
    return srvPersistListAsmMessage;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, PRI>, DRI, SD, PRI, MessageFull> getFactoryAsmMessageFull() {
    return factoryAsmMessageFull;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, PRI>, DRI, SD, IMG, PRI, StateInvContin> getAsmListAsmStateInvContin() {
    return asmListAsmStateInvContin;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, PRI>, DRI, SD, PRI, StateInvContin> getSrvPersistListAsmStateInvContin() {
    return srvPersistListAsmStateInvContin;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, PRI>, DRI, SD, PRI, StateInvContin> getFactoryAsmStateInvContin() {
    return factoryAsmStateInvContin;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, PRI>, DRI, SD, IMG, PRI, InteractionUse> getAsmListAsmInteractionUse() {
    return asmListAsmInteractionUse;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, PRI>, DRI, SD, PRI, InteractionUse> getSrvPersistListAsmInteractionUse() {
    return srvPersistListAsmInteractionUse;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, PRI>, DRI, SD, PRI, InteractionUse> getFactoryAsmInteractionUse() {
    return factoryAsmInteractionUse;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, PRI>, DRI, SD, IMG, PRI, CombinedFragment> getAsmListAsmCombinedFragment() {
    return asmListAsmCombinedFragment;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, PRI>, DRI, SD, PRI, CombinedFragment> getSrvPersistListAsmCombinedFragment() {
    return srvPersistListAsmCombinedFragment;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, PRI>, DRI, SD, PRI, CombinedFragment> getFactoryAsmCombinedFragment() {
    return factoryAsmCombinedFragment;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, PRI>, DRI, SD, IMG, PRI, CoregionFull> getAsmListAsmCoregionFull() {
    return asmListAsmCoregionFull;
  }

  public ISrvPersistListElementsUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, PRI>, DRI, SD, PRI, CoregionFull> getSrvPersistListAsmCoregionFull() {
    return srvPersistListAsmCoregionFull;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, PRI>, DRI, SD, PRI, CoregionFull> getFactoryAsmCoregionFull() {
    return factoryAsmCoregionFull;
  }
}
