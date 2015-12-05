/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.graphic.service;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;

/**
 * 
 * @author Yury Demidenko
 */
public abstract class ASrvDraw<DRI, SD extends ISettingsDraw, IMG>  implements ISrvDraw<DRI, SD, IMG> {
  
  public final static String controlString = "control string";

  //base font size depended of zoom
  private Float fontSize;
  
  private Float fontSizeMin = 4f;
  
  private Float fontSizeMax = 30f;
  
//base font size for zoom 1:1
  private Float fontSizeDefault;
  
  private double lengthControlStringFontDefault;
  
  private final SettingsGraphic sg;
  
  public ASrvDraw(SettingsGraphic sg) {
    this.sg = sg;
  }

  @Override
  public double printStringInBox(DRI dri, SD ds, 
      String string, String separator, Point2D pointStart, double widthTextBox) {
    int countRows = 1;
    double lengthString = 0;
    double widthChar = evalLengthOfString(dri, ds, "a");
    for(String wordStr : string.split(separator)) {
      double lengthWord = evalLengthOfString(dri, ds, wordStr);
      if(lengthString + widthChar + lengthWord >= widthTextBox) {
        if(lengthString > 0) {
          countRows++;
          lengthString = 0;
        }
      }
      Double x = pointStart.getX() + lengthString + widthChar;
      Double y = pointStart.getY() + widthChar*2.3 + (countRows-1) * widthChar * sg.getLineSpacingCoefficient();
      drawString(dri, ds, wordStr, x, y);
      lengthString += widthChar + lengthWord;
    }
    return widthChar*2 + countRows * widthChar * sg.getLineSpacingCoefficient();
  }

  @Override
  public double[] printMultistring(DRI dri, SD ds, String string, String separator, 
      Point2D pointStart, double lineSpacingCoef, double margin, boolean isCentreX) {
    double[] widthHeight = evalWidthHeightMultistring(dri, ds, string, separator, lineSpacingCoef, margin);
    double widthChar = evalLengthOfString(dri, ds, "a");
    int i = 0;
    for(String str : string.split(separator)) {
      Double x;
      if(isCentreX) {
        double widtwStr = evalLengthOfString(dri, ds, str);
        x = pointStart.getX() + (widthHeight[0] - widtwStr)/2;
      }
      else {
        x = pointStart.getX() + margin;
      }
      Double y = pointStart.getY() + margin + widthChar * lineSpacingCoef * i++;
      drawString(dri, ds, str, x, y);
    }
    return widthHeight;
  }
  
  @Override
  public double evalLengthOfString(DRI dri, SD ds, String string) {
    int widthInPixel = evalLengtStringInPixel(dri, ds, string);
    return UtilsGraphMath.toRealLenghtX(sg, widthInPixel);
  }

  @Override
  public double[] evalWidthHeightMultistring(DRI dri, SD ds, String string, String separator, 
      double lineSpacingCoef, double margin) {
    double width = 0;
    double height = margin * 2;
    double widthChar = evalLengthOfString(dri, ds, "a");
    for(String str : string.split(separator)) {
      width = Math.max(width, evalLengthOfString(dri, ds, str) + 2 * margin);
      height += widthChar * lineSpacingCoef;
    }
    return new double[]{width, height};
  }

  @Override
  public SettingsGraphic getSettingsGraphic() {
    return sg;
  }

  //SGS:
  public Float getFontSizeDefault() {
    return fontSizeDefault;
  }

  public void setFontSizeDefault(Float fontSizeDefault) {
    this.fontSizeDefault = fontSizeDefault;
  }

  public Float getFontSize() {
    return fontSize;
  }

  public void setFontSize(Float fontSize) {
    this.fontSize = fontSize;
  }

  public Float getFontSizeMin() {
    return fontSizeMin;
  }

  public void setFontSizeMin(Float fontSizeMin) {
    this.fontSizeMin = fontSizeMin;
  }

  public Float getFontSizeMax() {
    return fontSizeMax;
  }

  public void setFontSizeMax(Float fontSizeMax) {
    this.fontSizeMax = fontSizeMax;
  }

  public double getLengthControlStringFontDefault() {
    return lengthControlStringFontDefault;
  }

  public void setLengthControlStringFontDefault(
      double lengthControlStringFontDefault) {
    this.lengthControlStringFontDefault = lengthControlStringFontDefault;
  }
}
