package org.beigesoft.graphic.pojo;

import org.beigesoft.graphic.model.IShape;


public class Shape implements IShape {

  private Point2D pointStart = new Point2D(1, 1);

  private double width;

  private double height;
   
  @Override
  public Point2D getPointStart() {
    return pointStart;
  }

  @Override
  public void setPointStart(Point2D pointStart) {
    this.pointStart = pointStart;
  }

  @Override
  public double getWidth() {
    return width;
  }

  @Override
  public void setWidth(double width) {
    this.width = width;
  }

  @Override
  public double getHeight() {
    return height;
  }

  @Override
  public void setHeight(double height) {
    this.height = height;
  }
}
