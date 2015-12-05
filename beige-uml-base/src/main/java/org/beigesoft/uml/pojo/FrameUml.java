package org.beigesoft.uml.pojo;

import org.beigesoft.uml.model.IContainerUml;
import org.beigesoft.uml.model.IFragment;

public class FrameUml extends ShapeUmlWithName implements IContainerUml, IFragment {
  
  private String itsKind;
  
  private String parameters;
  
  //unpersistable:
  private double widthHead;
  
  private double heightHead;
  
  private boolean isNotMoveChildren;
  
  public FrameUml() {
    setIndexZ(0);
  }
  
  @Override
  public boolean getIsNotMoveChildren() {
    return isNotMoveChildren;
  }

  @Override
  public void setIsNotMoveChildren(boolean isNotMoveChildren) {
    this.isNotMoveChildren = isNotMoveChildren;
  }


  @Override
  public double getWidthHead() {
    return widthHead;
  }

  @Override
  public void setWidthHead(double widthHead) {
    this.widthHead = widthHead;
  }

  @Override
  public double getHeightHead() {
    return heightHead;
  }

  @Override
  public void setHeightHead(double heightHead) {
    this.heightHead = heightHead;
  }

  //SGS:
  public String getItsKind() {
    return itsKind;
  }

  public void setItsKind(String itsKind) {
    this.itsKind = itsKind;
  }

  public String getParameters() {
    return parameters;
  }

  public void setParameters(String parameters) {
    this.parameters = parameters;
  }
}
