package org.beigesoft.uml.assembly;

import java.util.Collection;

import org.beigesoft.uml.model.IElementUml;
import org.beigesoft.uml.model.ITextUml;
import org.beigesoft.uml.pojo.ShapeUml;

public interface IShapeFullUml<SH extends ShapeUml> extends IElementUml {

  public SH getShape();

  public void setShape(SH shapeUml);

  public Collection<ITextUml> getTextsTied();
}
