package org.beigesoft.uml.assembly;

import org.beigesoft.model.IHasName;
import org.beigesoft.uml.model.EFrameRoleForMessage;
import org.beigesoft.uml.model.EMessageKind;
import org.beigesoft.uml.model.ElementUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class MessageFull extends ElementUml implements IHasName, Cloneable {

  private double y;

  private String itsName;
  
  private boolean isReversed;
  
  private EMessageKind itsKind = EMessageKind.ASYNCHRONOUS;
  
  private IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> itsFrame;//cause below lifeLine and frame
  
  private EFrameRoleForMessage frameRole;

  private boolean isRightSideOfFrame;
  
  private IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLineFullStart;

  private IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLineFullEnd;

  private IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> asmInteractionUseStart;

  private boolean isLeftSideOfInteractionUseStart;
  
  private IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> asmInteractionUseEnd;

  private boolean isRightSideOfInteractionUseEnd;

  //non-persistable:
  private Double startX;
  
  private Double endX;
  
  private Double nameX;
  
  private Double widthName;
    
  public MessageFull() {
    setIndexZ(1000);
  }
  
  @Override
  public String toString() {
    String str = itsName == null ? getClass().getSimpleName() + hashCode() : 
      itsName + " " + hashCode() + " " + getClass().getSimpleName();
    return str;
  }

  @Override
  public MessageFull clone() {
    MessageFull clone;
    clone = (MessageFull) super.clone();
    return clone;
  }

  @Override
  public void setItsName(String itsName) {
    this.itsName = itsName;
  }

  @Override
  public String getItsName() {
    return itsName;
  }

  //SGS:
  public IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> getAsmLifeLineFullStart() {
    return asmLifeLineFullStart;
  }

  public void setAsmLifeLineFullStart(
      IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLineFullStart) {
    this.asmLifeLineFullStart = asmLifeLineFullStart;
  }

  public IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> getAsmLifeLineFullEnd() {
    return asmLifeLineFullEnd;
  }

  public void setAsmLifeLineFullEnd(
      IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLineFullEnd) {
    this.asmLifeLineFullEnd = asmLifeLineFullEnd;
  }

  public IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> getAsmInteractionUseStart() {
    return asmInteractionUseStart;
  }

  public void setAsmInteractionUseStart(
      IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> asmInteractionUseStart) {
    this.asmInteractionUseStart = asmInteractionUseStart;
  }

  public IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> getAsmInteractionUseEnd() {
    return asmInteractionUseEnd;
  }

  public void setAsmInteractionUseEnd(IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> asmInteractionUseEnd) {
    this.asmInteractionUseEnd = asmInteractionUseEnd;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public boolean getIsReversed() {
    return isReversed;
  }

  public void setIsReversed(boolean isReversed) {
    this.isReversed = isReversed;
  }

  public Double getStartX() {
    return startX;
  }

  public void setStartX(Double startX) {
    this.startX = startX;
  }

  public Double getEndX() {
    return endX;
  }

  public void setEndX(Double endX) {
    this.endX = endX;
  }

  public Double getWidthName() {
    return widthName;
  }

  public void setWidthName(Double widthName) {
    this.widthName = widthName;
  }

  public Double getNameX() {
    return nameX;
  }

  public void setNameX(Double nameX) {
    this.nameX = nameX;
  }

  public EMessageKind getItsKind() {
    return itsKind;
  }

  public void setItsKind(EMessageKind itsKind) {
    this.itsKind = itsKind;
  }

  public IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> getItsFrame() {
    return itsFrame;
  }

  public void setItsFrame(IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> itsFrame) {
    this.itsFrame = itsFrame;
  }

  public EFrameRoleForMessage getFrameRole() {
    return frameRole;
  }

  public void setFrameRole(EFrameRoleForMessage frameRole) {
    this.frameRole = frameRole;
  }

  public boolean getIsRightSideOfFrame() {
    return isRightSideOfFrame;
  }

  public void setIsRightSideOfFrame(boolean isRightSideOfFrame) {
    this.isRightSideOfFrame = isRightSideOfFrame;
  }

  public boolean getIsLeftSideOfInteractionUseStart() {
    return isLeftSideOfInteractionUseStart;
  }

  public void setIsLeftSideOfInteractionUseStart(
      boolean isLeftSideOfInteractionUseStart) {
    this.isLeftSideOfInteractionUseStart = isLeftSideOfInteractionUseStart;
  }

  public boolean getIsRightSideOfInteractionUseEnd() {
    return isRightSideOfInteractionUseEnd;
  }

  public void setIsRightSideOfInteractionUseEnd(boolean isRightSideOfInteractionUseEnd) {
    this.isRightSideOfInteractionUseEnd = isRightSideOfInteractionUseEnd;
  }
}
