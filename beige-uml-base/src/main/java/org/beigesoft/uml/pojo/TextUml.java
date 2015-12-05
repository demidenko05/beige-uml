/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.pojo;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.ElementUml;
import org.beigesoft.uml.model.ITextUml;

/**
 * <p>Represent UML itsText model (single string, no border or relationships)</p>
 * 
 * @author Yury Demidenko
 */
public class TextUml extends ElementUml implements Cloneable, ITextUml {
  
  private Point2D pointStart;
  
  private String itsText;
  
  private boolean isBold;
  
  private boolean isTransparent = true;
    
  private ShapeFull<?> tiedShape;

  //unpersistable:
  /**
   * to calculate graphic size
   */
  private double heightText;

  /**
   * to calculate graphic size
   */
  private double lengthText;

  public TextUml() {
    setIndexZ(1000);
  }
  
  @Override
  public TextUml clone() {
    TextUml clone;
    clone = (TextUml) super.clone();
    return clone;
  }

  @Override
  public String toString() {
    return itsText;
  }

  @Override
  public Point2D getPointStart() {
    return pointStart;
  }

  public double getHeightText() {
    return heightText;
  }

  public double getLengthText() {
    return lengthText;
  }

  @Override
  public String getItsText() {
    return itsText;
  }

  @Override
  public void setItsText(String itsText) {
    this.itsText = itsText;
  }

  public void setPointStart(Point2D pointStart) {
    this.pointStart = pointStart;
  }

  @Override
  public ShapeFull<?> getTiedShape() {
    return tiedShape;
  }

  @Override
  public void setTiedShape(ShapeFull<?> tiedShape) {
    this.tiedShape = tiedShape;
  }

  public void setHeightText(double heightText) {
    this.heightText = heightText;
  }

  public void setLengthText(double lengthText) {
    this.lengthText = lengthText;
  }

  public boolean getIsBold() {
    return isBold;
  }

  public void setIsBold(boolean isBold) {
    this.isBold = isBold;
  }

  public boolean getIsTransparent() {
    return isTransparent;
  }

  public void setIsTransparent(boolean isTransparent) {
    this.isTransparent = isTransparent;
  }
}
