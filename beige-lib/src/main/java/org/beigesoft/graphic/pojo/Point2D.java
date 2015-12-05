/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.graphic.pojo;

/**
 * <p>Represent base model of 2d point</p>
 * 
 * @author Yury Demidenko
 */
public class Point2D implements Cloneable {
  
  private double x;
  
  private double y;

  public Point2D() {  
  }
  
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public Point2D(Point2D point) {
    this.x = point.getX();
    this.y = point.getY();
  }

  @Override
  public Point2D clone() {
    try {
      Point2D clone = (Point2D) super.clone();
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  //OGS:
  public double getY() {
    return y;
  }
  
  public void setY(double y) {
    this.y = y;
  }
  
  public double getX() {
    return x;
  }
  
  public void setX(double x) {
    this.x = x;
  }
}
