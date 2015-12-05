package org.beigesoft.uml.assembly;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.uml.model.IShapeUmlWithName;
import org.beigesoft.uml.pojo.Execution;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class LifeLineFull<LL extends ShapeUmlWithName> implements IShapeUmlWithName, Cloneable {
  
  private LL lifeLine;
  
  private Double destructionOccurenceY;
  
  private List<Execution> executions = new ArrayList<Execution>();
  
  //because cross-references and below frame
  private IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> asmFrameFull;
  
  //unpersistable:
  private Double lineEndY;

  public LifeLineFull(LL lifeLine) {
    this.lifeLine = lifeLine;
    lifeLine.setIndexZ(800);
  }
  
  @Override
  public LifeLineFull<LL> clone() {
    try {
      @SuppressWarnings("unchecked")
      LifeLineFull<LL> clone= (LifeLineFull<LL>) super.clone();
      clone.executions = new ArrayList<Execution>(executions);
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return lifeLine == null ? null : lifeLine.toString();
  }

  @Override
  public void setItsName(String itsName) {
    lifeLine.setItsName(itsName);
  }

  @Override
  public String getItsName() {
    return lifeLine.getItsName();
  }

  @Override
  public UUID getId() {
    return lifeLine.getId();
  }

  @Override
  public void setId(UUID id) {
    lifeLine.setId(id);
  }

  @Override
  public boolean getIsNew() {
    return lifeLine.getIsNew();
  }

  @Override
  public void setIsNew(boolean isNew) {
    lifeLine.setIsNew(isNew);
  }

  @Override
  public boolean getIsSelected() {
    return lifeLine.getIsSelected();
  }

  @Override
  public void setIsSelected(boolean isSelected) {
    lifeLine.setIsSelected(isSelected);
  }

  @Override
  public Point2D getPointStart() {
    return lifeLine.getPointStart();
  }

  @Override
  public void setPointStart(Point2D pointStart) {
    lifeLine.setPointStart(pointStart);
  }

  @Override
  public double getWidth() {
    return lifeLine.getWidth();
  }

  @Override
  public void setWidth(double width) {
    lifeLine.setWidth(width);
  }

  @Override
  public double getHeight() {
    return lifeLine.getHeight();
  }

  @Override
  public void setHeight(double height) {
    lifeLine.setHeight(height);
  }

  @Override
  public boolean getIsAdjustMinimumSize() {
    return lifeLine.getIsAdjustMinimumSize();
  }

  @Override
  public void setIsAdjustMinimumSize(boolean isAdjustMinimumSize) {
    lifeLine.setIsAdjustMinimumSize(isAdjustMinimumSize);
  }

  @Override
  public Integer getIndexZ() {
    return lifeLine.getIndexZ();
  }

  @Override
  public void setIndexZ(Integer indexZ) {
    lifeLine.setIndexZ(indexZ);
  }

  //SGS:
  public IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> getAsmFrameFull() {
    return asmFrameFull;
  }

  public void setAsmFrameFull(IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> asmFrameFull) {
    this.asmFrameFull = asmFrameFull;
  }

  public LL getLifeLine() {
    return lifeLine;
  }

  public void setLifeLine(LL lifeLine) {
    this.lifeLine = lifeLine;
  }

  public Double getDestructionOccurenceY() {
    return destructionOccurenceY;
  }

  public void setDestructionOccurenceY(Double destructionOccurenceY) {
    this.destructionOccurenceY = destructionOccurenceY;
  }

  public List<Execution> getExecutions() {
    return executions;
  }

  public void setExecutions(List<Execution> executions) {
    this.executions = executions;
  }

  public Double getLineEndY() {
    return lineEndY;
  }

  public void setLineEndY(Double lineEndY) {
    this.lineEndY = lineEndY;
  }
}
