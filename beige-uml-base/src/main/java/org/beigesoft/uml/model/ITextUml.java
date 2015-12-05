package org.beigesoft.uml.model;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.uml.assembly.ShapeFull;

public interface ITextUml {

  public Point2D getPointStart();

  public ShapeFull<?> getTiedShape();

  public void setTiedShape(ShapeFull<?> tiedShape);

  public String getItsText();

  public void setItsText(String text);
}
