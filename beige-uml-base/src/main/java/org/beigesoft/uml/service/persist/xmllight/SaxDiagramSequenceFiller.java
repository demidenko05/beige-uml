package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.diagram.assembly.AsmDiagramSequence;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.CombinedFragment;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.pojo.StateInvContin;
import org.beigesoft.uml.pojo.TextUml;

public class SaxDiagramSequenceFiller<DRI, SD extends ISettingsDraw, IMG, DLI> extends ASaxDiagramUmlInteractiveFiller<AsmDiagramSequence<DRI, SD, IMG, FileAndWriter, DLI>, DiagramUml, DRI, SD, IMG, DLI> {
  
  private final SaxLifeLineFullFiller<LifeLineFull<ShapeUmlWithName>> saxLifeLineFullFiller;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LifeLineFull<ShapeUmlWithName>> factoryAsmLifeLineFull;

  private final SaxMessageFullFiller<MessageFull> saxMessageFullFiller;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, MessageFull> factoryAsmMessageFull;

  private final SaxCoregionFullFiller<CoregionFull> saxCoregionFullFiller;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CoregionFull> factoryAsmCoregionFull;

  private final SaxStateInvContinFiller<StateInvContin> saxStateInvContinFiller;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, StateInvContin> factoryAsmStateInvContin;

  private final SaxInteractionUseFiller<InteractionUse> saxInteractionUseFiller;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, InteractionUse> factoryAsmInteractionUse;

  private final SaxCombinedFragmentFiller<CombinedFragment> saxCombinedFragmentFiller;
  
  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CombinedFragment> factoryAsmCombinedFragment;

  public SaxDiagramSequenceFiller(String namePersistable,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<CommentUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CommentUml> factoryAsmComment,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<TextUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, TextUml> factoryAsmText,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ContainerFull<FrameUml>> factoryAsmFrame,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RectangleUml> factoryAsmRectangle,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LineUml> factoryAsmLine,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LifeLineFull<ShapeUmlWithName>> factoryAsmLifeLineFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, MessageFull> factoryAsmMessageFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CoregionFull> factoryAsmCoregionFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, StateInvContin> factoryAsmStateInvContin,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, InteractionUse> factoryAsmInteractionUse,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CombinedFragment> factoryAsmCombinedFragment) {
    super(namePersistable, factoryAsmComment, factoryAsmText, factoryAsmFrame, factoryAsmRectangle, factoryAsmLine);
    this.factoryAsmLifeLineFull = factoryAsmLifeLineFull;
    saxLifeLineFullFiller = new SaxLifeLineFullFiller<LifeLineFull<ShapeUmlWithName>>(SrvSaveXmlLifeLineFull.NAMEXML_LIFELINEUML, getPathCurrent());
    this.factoryAsmMessageFull = factoryAsmMessageFull;
    saxMessageFullFiller = new SaxMessageFullFiller<MessageFull>(SrvSaveXmlMessageFull.NAMEXML_MESSAGEUML, getPathCurrent());
    this.factoryAsmCoregionFull = factoryAsmCoregionFull;
    saxCoregionFullFiller = new SaxCoregionFullFiller<CoregionFull>(SrvSaveXmlCoregionFull.NAMEXML_COREGIONUML, getPathCurrent());
    this.factoryAsmStateInvContin = factoryAsmStateInvContin;
    saxStateInvContinFiller = new SaxStateInvContinFiller<StateInvContin>(SrvSaveXmlStateInvContin.NAMEXML_STATEINVCONTIN, getPathCurrent());
    this.factoryAsmInteractionUse = factoryAsmInteractionUse;
    saxInteractionUseFiller = new SaxInteractionUseFiller<InteractionUse>(SrvSaveXmlInteractionUse.NAMEXML_INTERACTIONUSE, getPathCurrent());
    this.factoryAsmCombinedFragment = factoryAsmCombinedFragment;
    saxCombinedFragmentFiller = new SaxCombinedFragmentFiller<CombinedFragment>(SrvSaveXmlCombinedFragment.NAMEXML_COMBINEDFRAGMENT, getPathCurrent());
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(elementName == null || elementValue.equals("\\n")) {
      return false;
    }
    if(super.fillModel(elementName, elementValue)) {
      return true;
    }
    else if(saxLifeLineFullFiller.getModel() != null) {
      if(saxLifeLineFullFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxMessageFullFiller.getModel() != null) {
      if(saxMessageFullFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxStateInvContinFiller.getModel() != null) {
      if(saxStateInvContinFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxInteractionUseFiller.getModel() != null) {
      if(saxInteractionUseFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxCombinedFragmentFiller.getModel() != null) {
      if(saxCombinedFragmentFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(elementName == null || attrName == null || attrValue.equals("\\n")) {
      return false;
    }
    if(super.fillModel(elementName, attrName, attrValue)) {
      return true;
    }
    else if(saxLifeLineFullFiller.getModel() != null) {
      if(saxLifeLineFullFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxMessageFullFiller.getModel() != null) {
      if(saxMessageFullFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxCoregionFullFiller.getModel() != null) {
      if(saxCoregionFullFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxStateInvContinFiller.getModel() != null) {
      if(saxStateInvContinFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxInteractionUseFiller.getModel() != null) {
      if(saxInteractionUseFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxCombinedFragmentFiller.getModel() != null) {
      if(saxCombinedFragmentFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }
 
  @Override
  public boolean handleStartElement(String localName) {
    if(super.handleStartElement(localName)) {
      return true;
    }
    if(saxLifeLineFullFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, FileAndWriter> asmLifeLineFull = factoryAsmLifeLineFull.createAsmElementUml();
      getModel().getAsmListAsmLifeLine().addElementUml(asmLifeLineFull);
      asmLifeLineFull.getElementUml().setIsNew(false);
      saxLifeLineFullFiller.setModelAndPrepare(asmLifeLineFull.getElementUml());
      saxLifeLineFullFiller.setAsmLifeLineFull(asmLifeLineFull);
      return true;
    }
    if(saxMessageFullFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<MessageFull, DRI, SD, FileAndWriter> asmMessageFull = factoryAsmMessageFull.createAsmElementUml();
      getModel().getAsmListAsmMessage().addElementUml(asmMessageFull);
      asmMessageFull.getElementUml().setIsNew(false);
      saxMessageFullFiller.setModelAndPrepare(asmMessageFull.getElementUml());
      saxMessageFullFiller.setAsmMessageFull(asmMessageFull);
      return true;
    }
    if(saxCoregionFullFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<CoregionFull, DRI, SD, FileAndWriter> asmCoregionFull = factoryAsmCoregionFull.createAsmElementUml();
      getModel().getAsmListAsmCoregionFull().addElementUml(asmCoregionFull);
      asmCoregionFull.getElementUml().setIsNew(false);
      saxCoregionFullFiller.setModelAndPrepare(asmCoregionFull.getElementUml());
      saxCoregionFullFiller.setAsmCoregionFull(asmCoregionFull);
      return true;
    }
    if(saxStateInvContinFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<StateInvContin, DRI, SD, FileAndWriter> asmStateInvContin = factoryAsmStateInvContin.createAsmElementUml();
      getModel().getAsmListAsmStateInvContin().addElementUml(asmStateInvContin);
      asmStateInvContin.getElementUml().setIsNew(false);
      saxStateInvContinFiller.setModelAndPrepare(asmStateInvContin.getElementUml());
      return true;
    }
    if(saxInteractionUseFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<InteractionUse, DRI, SD, FileAndWriter> asmInteractionUse = factoryAsmInteractionUse.createAsmElementUml();
      getModel().getAsmListAsmInteractionUse().addElementUml(asmInteractionUse);
      asmInteractionUse.getElementUml().setIsNew(false);
      saxInteractionUseFiller.setModelAndPrepare(asmInteractionUse.getElementUml());
      return true;
    }
    if(saxCombinedFragmentFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<CombinedFragment, DRI, SD, FileAndWriter> asmCombinedFragment = factoryAsmCombinedFragment.createAsmElementUml();
      getModel().getAsmListAsmCombinedFragment().addElementUml(asmCombinedFragment);
      asmCombinedFragment.getElementUml().setIsNew(false);
      saxCombinedFragmentFiller.setModelAndPrepare(asmCombinedFragment.getElementUml());
      return true;
    }
    if(saxLifeLineFullFiller.getModel() != null && saxLifeLineFullFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxMessageFullFiller.getModel() != null && saxMessageFullFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxCoregionFullFiller.getModel() != null && saxCoregionFullFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxStateInvContinFiller.getModel() != null && saxStateInvContinFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxInteractionUseFiller.getModel() != null && saxInteractionUseFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxCombinedFragmentFiller.getModel() != null && saxCombinedFragmentFiller.handleStartElement(localName)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean handleEndElement(String localName) throws Exception {
    if(super.handleEndElement(localName)) {
      return true;
    }
    if(saxLifeLineFullFiller.getNamePersistable().equals(localName)) {
      saxLifeLineFullFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxMessageFullFiller.getNamePersistable().equals(localName)) {
      saxMessageFullFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxCoregionFullFiller.getNamePersistable().equals(localName)) {
      saxCoregionFullFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxStateInvContinFiller.getNamePersistable().equals(localName)) {
      saxStateInvContinFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxInteractionUseFiller.getNamePersistable().equals(localName)) {
      saxInteractionUseFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxCombinedFragmentFiller.getNamePersistable().equals(localName)) {
      saxCombinedFragmentFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxLifeLineFullFiller.getModel() != null && saxLifeLineFullFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxMessageFullFiller.getModel() != null && saxMessageFullFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxCoregionFullFiller.getModel() != null && saxCoregionFullFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxStateInvContinFiller.getModel() != null && saxStateInvContinFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxInteractionUseFiller.getModel() != null && saxInteractionUseFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxCombinedFragmentFiller.getModel() != null && saxCombinedFragmentFiller.handleEndElement(localName)) {
      return true;
    }
    return false;
  }

  //SGS:
  public SaxLifeLineFullFiller<LifeLineFull<ShapeUmlWithName>> getSaxLifeLineFullFiller() {
    return saxLifeLineFullFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LifeLineFull<ShapeUmlWithName>> getFactoryAsmLifeLineFull() {
    return factoryAsmLifeLineFull;
  }

  public SaxMessageFullFiller<MessageFull> getSaxMessageFullFiller() {
    return saxMessageFullFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<MessageFull, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, MessageFull> getFactoryAsmMessageFull() {
    return factoryAsmMessageFull;
  }

  public SaxStateInvContinFiller<StateInvContin> getSaxStateInvContinFiller() {
    return saxStateInvContinFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<StateInvContin, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, StateInvContin> getFactoryAsmStateInvContin() {
    return factoryAsmStateInvContin;
  }

  public SaxInteractionUseFiller<InteractionUse> getSaxInteractionUseFiller() {
    return saxInteractionUseFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<InteractionUse, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, InteractionUse> getFactoryAsmInteractionUse() {
    return factoryAsmInteractionUse;
  }

  public SaxCombinedFragmentFiller<CombinedFragment> getSaxCombinedFragmentFiller() {
    return saxCombinedFragmentFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<CombinedFragment, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CombinedFragment> getFactoryAsmCombinedFragment() {
    return factoryAsmCombinedFragment;
  }

  public SaxCoregionFullFiller<CoregionFull> getSaxCoregionFullFiller() {
    return saxCoregionFullFiller;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<CoregionFull, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CoregionFull> getFactoryAsmCoregionFull() {
    return factoryAsmCoregionFull;
  }
}
