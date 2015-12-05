/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.graphic;

import org.beigesoft.graphic.model.EMeasurementUnit;

/**
 * <p>Some graphics settings</p>
 * 
 * @author Yury Demidenko
 */
public class SettingsGraphic {
 
  public static double CENTEMETERS_IN_INCH = 2.54d;

  private EMeasurementUnit measurementUnit;
  
  public static final String MEASUREMENT_UNIT = "measurementUnit";

  private int screenWidthPixels;
  
  private int screenHeightPixels;
  
  private int canvasWidthPixels;
  
  private int canvasHeightPixels;
  
  private double screenResolutionDotsPerInch;
  
  private double marginLeft;

  private double marginRight;

  private double marginTop;

  private double marginBottom;

  private double selectApproximation;
  
  private double zoom;
  
  private double minZoom;
  
  private double maxZoom;
  
  private double offsetX;
  
  private double minOffsetX;
  
  private double maxOffsetX;
  
  private double offsetY;
  
  private double minOffsetY;
  
  private double maxOffsetY;
  
  private double draggingStep;
  
  public static final String SELECT_APPROXIMATION = "selectApproximation";
  
  private double widthDragRectangle;

  public static final String WIDTH_DRAG_RECTANGLE = "widthDragRectangle";

  private double lineSpacingCoefficient;

  //Constructors part:
  public SettingsGraphic() {
    setDefaultValues();
  }
  
  public void setDefaultValues() {
    measurementUnit = EMeasurementUnit.INCH;
    selectApproximation = fromInchToCurrentMeasure(0.05);
    widthDragRectangle = fromInchToCurrentMeasure(0.08);
    setZoom(1);
    setMinZoom(0.1);
    setMaxZoom(10);
    setMinOffsetX(-3000);
    setMaxOffsetX(3000);
    setMinOffsetY(-2000);
    setMaxOffsetY(2000);
    setDraggingStep(1);
    lineSpacingCoefficient = 2;
  }
  
  public void setMeasurementUnitAndRecalculateIfNeed(EMeasurementUnit measurementUnit) {
    if(this.measurementUnit != measurementUnit) {
      double coef;
      if(this.measurementUnit == EMeasurementUnit.INCH) {
        coef = CENTEMETERS_IN_INCH;
      }
      else {
        coef = 1 / CENTEMETERS_IN_INCH;
      }
      selectApproximation *= coef;
      this.measurementUnit = measurementUnit;
    }
  }

  //Pseudo-constants:

  public double getApproximationCalculation() {
    return fromInchToCurrentMeasure(0.01);
  }
  //Utilities:
  /**
   * 
   * @return resolution dots per INCH or CENTIMETRE
   */
  public double getScreenResolution() {
    if(measurementUnit == EMeasurementUnit.INCH)
      return screenResolutionDotsPerInch;
    else
      return screenResolutionDotsPerInch / CENTEMETERS_IN_INCH;
  }
    
  public double fromInchToCurrentMeasure(double valueInInche) {
    if(measurementUnit == EMeasurementUnit.INCH)
      return valueInInche;
    else
      return valueInInche * CENTEMETERS_IN_INCH;
  }

  public double fromCurrentMeasureToInch(double value) {
    if(measurementUnit == EMeasurementUnit.INCH)
      return value;
    else
      return value / CENTEMETERS_IN_INCH;
  }
  
  //Pseudo-constants:
  public double getMinSelectApproximation() {
    return fromInchToCurrentMeasure(0.005);
  }

  public double getMaxSelectApproximation() {
    return fromInchToCurrentMeasure(0.1);
  }
  
  //OGS:
  public EMeasurementUnit getMeasurementUnit() {
    return measurementUnit;
  }

  public void setMeasurementUnit(EMeasurementUnit measurementUnit) {
    this.measurementUnit = measurementUnit;
  }

  public int getScreenWidthPixels() {
    return screenWidthPixels;
  }

  public void setScreenWidthPixels(int screenWidthPixels) {
    this.screenWidthPixels = screenWidthPixels;
  }

  public int getScreenHeightPixels() {
    return screenHeightPixels;
  }

  public void setScreenHeightPixels(int screenHeightPixels) {
    this.screenHeightPixels = screenHeightPixels;
  }

  public double getScreenResolutionDotsPerInch() {
    return screenResolutionDotsPerInch;
  }

  public void setScreenResolutionDotsPerInch(double screenResolutionDotsPerInch) {
    this.screenResolutionDotsPerInch = screenResolutionDotsPerInch;
  }

  public double getMarginLeft() {
    return marginLeft;
  }

  public void setMarginLeft(double marginLeft) {
    this.marginLeft = marginLeft;
  }

  public double getMarginRight() {
    return marginRight;
  }

  public void setMarginRight(double marginRight) {
    this.marginRight = marginRight;
  }

  public double getMarginTop() {
    return marginTop;
  }

  public void setMarginTop(double marginTop) {
    this.marginTop = marginTop;
  }

  public double getMarginBottom() {
    return marginBottom;
  }

  public void setMarginBottom(double marginBottom) {
    this.marginBottom = marginBottom;
  }

  public double getSelectApproximation() {
    return selectApproximation ;
  }

  public void setSelectApproximation(double selectApproximation) {
    this.selectApproximation = selectApproximation;
  }
  public double getWidthDragRectangle() {
    return widthDragRectangle;
  }

  public void setWidthDragRectangle(double widthDragRectangle) {
    this.widthDragRectangle = widthDragRectangle;
  }

  public double getZoom() {
    return zoom;
  }

  public void setZoom(double zoom) {
    this.zoom = zoom;
  }

  public double getMinZoom() {
    return minZoom;
  }

  public void setMinZoom(double minZoom) {
    this.minZoom = minZoom;
  }

  public double getMaxZoom() {
    return maxZoom;
  }

  public void setMaxZoom(double maxZoom) {
    this.maxZoom = maxZoom;
  }

  public int getCanvasWidthPixels() {
    return canvasWidthPixels;
  }

  public void setCanvasWidthPixels(int canvasWidthPixels) {
    this.canvasWidthPixels = canvasWidthPixels;
  }

  public int getCanvasHeightPixels() {
    return canvasHeightPixels;
  }

  public void setCanvasHeightPixels(int canvasHeightPixels) {
    this.canvasHeightPixels = canvasHeightPixels;
  }

  public double getOffsetX() {
    return offsetX;
  }

  public void setOffsetX(double offsetX) {
    this.offsetX = offsetX;
  }

  public double getOffsetY() {
    return offsetY;
  }

  public void setOffsetY(double offsetY) {
    this.offsetY = offsetY;
  }

  public double getMinOffsetX() {
    return minOffsetX;
  }

  public void setMinOffsetX(double minOffsetX) {
    this.minOffsetX = minOffsetX;
  }

  public double getMaxOffsetX() {
    return maxOffsetX;
  }

  public void setMaxOffsetX(double maxOffsetX) {
    this.maxOffsetX = maxOffsetX;
  }

  public double getMinOffsetY() {
    return minOffsetY;
  }

  public void setMinOffsetY(double minOffsetY) {
    this.minOffsetY = minOffsetY;
  }

  public double getMaxOffsetY() {
    return maxOffsetY;
  }

  public void setMaxOffsetY(double maxOffsetY) {
    this.maxOffsetY = maxOffsetY;
  }

  public double getDraggingStep() {
    return draggingStep;
  }

  public void setDraggingStep(double draggingStep) {
    this.draggingStep = draggingStep;
  }

  public double getLineSpacingCoefficient() {
     return lineSpacingCoefficient;
  }

  public void setLineSpacingCoefficient(double lineSpacingCoefficient) {
    this.lineSpacingCoefficient = lineSpacingCoefficient;
  }
}
