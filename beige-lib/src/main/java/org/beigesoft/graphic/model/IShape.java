package org.beigesoft.graphic.model;

import org.beigesoft.graphic.pojo.Point2D;


public interface IShape {

  public Point2D getPointStart();

  public void setPointStart(Point2D pointStart);

  public double getWidth();

  public void setWidth(double width);

  public double getHeight();

  public void setHeight(double height);
}
