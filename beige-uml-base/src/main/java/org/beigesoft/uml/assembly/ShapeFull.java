package org.beigesoft.uml.assembly;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import org.beigesoft.uml.model.ITextUml;
import org.beigesoft.uml.pojo.ShapeUml;

public class ShapeFull<SH extends ShapeUml> implements IShapeFullUml<SH>, Cloneable {

  private SH shape;
              
  /**
   * to move tied texts
   */
  private final Collection<ITextUml> textsTied;
    
  public ShapeFull() {
    textsTied = new HashSet<ITextUml>();
  }

  @Override
  public ShapeFull<SH> clone() {
    try {
      @SuppressWarnings("unchecked")
      ShapeFull<SH> clone = (ShapeFull<SH>) super.clone();
      @SuppressWarnings("unchecked")
      SH actorUmlClone = (SH) shape.clone();
      clone.setShape(actorUmlClone);
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return shape == null ? null : shape.toString();
  }

  @Override
  public boolean getIsSelected() {
    return shape.getIsSelected();
  }

  @Override
  public void setIsSelected(boolean isSelected) {
    shape.setIsSelected(isSelected);
  }

  @Override
  public UUID getId() {
    return shape.getId();
  }

  @Override
  public void setId(UUID id) {
    shape.setId(id);
  }

  @Override
  public boolean getIsNew() {
    return shape.getIsNew();
  }

  @Override
  public void setIsNew(boolean isNew) {
    shape.setIsNew(isNew);
  }
  
  @Override
  public SH getShape() {
    return shape;
  }

  @Override
  public void setShape(SH shapeUml) {
    this.shape = shapeUml;
  }

  @Override
  public Collection<ITextUml> getTextsTied() {
    return textsTied;
  }

  @Override
  public Integer getIndexZ() {
    return shape.getIndexZ();
  }

  @Override
  public void setIndexZ(Integer indexZ) {
    shape.setIndexZ(indexZ);
  }
}
