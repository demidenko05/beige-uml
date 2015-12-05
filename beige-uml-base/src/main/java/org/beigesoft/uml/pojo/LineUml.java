package org.beigesoft.uml.pojo;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.uml.model.ELineEndShape;
import org.beigesoft.uml.model.ElementUml;

public class LineUml extends ElementUml {
  
  private Point2D point1 = new Point2D();
  
  private Point2D point2 = new Point2D();
  
  private ELineEndShape lineEnd1Shape;
  
  private ELineEndShape lineEnd2Shape;
  
  private boolean isDashed;

  public LineUml() {
    setIndexZ(200);
  }
  
  @Override
  public String toString() {
    String strPoint1 = point1 == null ? "?" : 
      String.format("X1=%1$.3f, Y1=%2$.3f", point1.getX(), point1.getY());
    String strPoint2 = point2 == null ? "?" : 
      String.format("X2=%1$.3f, Y2=%2$.3f", point2.getX(), point2.getY());

    return "ln from " + strPoint1 + " to " + strPoint2;
  }

  //SGS:
  public Point2D getPoint1() {
    return point1;
  }

  public void setPoint1(Point2D point1) {
    this.point1 = point1;
  }

  public Point2D getPoint2() {
    return point2;
  }

  public void setPoint2(Point2D point2) {
    this.point2 = point2;
  }

  public ELineEndShape getLineEnd1Shape() {
    return lineEnd1Shape;
  }

  public void setLineEnd1Shape(ELineEndShape lineEnd1Shape) {
    this.lineEnd1Shape = lineEnd1Shape;
  }

  public ELineEndShape getLineEnd2Shape() {
    return lineEnd2Shape;
  }

  public void setLineEnd2Shape(ELineEndShape lineEnd2Shape) {
    this.lineEnd2Shape = lineEnd2Shape;
  }

  public boolean getIsDashed() {
    return isDashed;
  }

  public void setIsDashed(boolean isDashed) {
    this.isDashed = isDashed;
  }
}
