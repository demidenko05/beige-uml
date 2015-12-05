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
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.pojo.SettingsFont;

/**
 * <p>Abstraction of a drawing service to be implemented by any graphics means</p>
 *
 * ITD - abstraction for graphic draw instrument(e.itd. AWT.Graphic2D)
 * SD - abstraction for draw settings(contains of color, dashed...) 
 * 
 * @author Yury Demidenko
 */
public interface ISrvDraw <ITD, SD extends ISettingsDraw, IMG> {

  public void drawRectangle(ITD itd, SD ds, double x,
      double y, double width, double height, boolean isFill);

  public void drawRectangleRound(ITD itd, SD ds, double x,
      double y, double width, double height, double arcw,
      double arch, boolean isFill);

  public void drawString(ITD itd, SD ds, String name,
      double x, double y);

  public void drawPath(ITD itd, SD ds, double[] setX,
      double[] setY, int totalPoints, boolean isClose, boolean isFill);

  public void drawLine(ITD itd, SD ds, double x1,
      double y1, double x2, double y2);

  public void drawString(ITD itd, SD ds, String string, int xScreen, int yScreen);

  public void preparePaint(ITD itd, SD ds);

  public void drawEllipse(ITD itd, SD ds, double x,
      double y, double width, double height, boolean isFill);
  
  public int evalLengtStringInPixel(ITD itd, SD ds, String str);

  public void prepareFont(ITD itd, SD ds, SettingsFont settingsFont);
  
  public double evalLengthOfString(ITD itd, SD ds, String str);
  
  public double printStringInBox(ITD itd, SD ds, 
      String str, String separator, Point2D pointStart, double widthTextBox);

  public void drawCircle(ITD itd, SD ds, double centerX, double centerY,
      double radius, boolean isFill);

  public double[] printMultistring(ITD itd, SD ds,
      String str, String separator, Point2D pointStart, double lineSpacingCoef, double margin, boolean isCentreX);

  public double[] evalWidthHeightMultistring(ITD itd, SD ds,
      String str, String separator, double lineSpacingCoef, double margin);

  public void DrawImage(ITD itd, SD ds, IMG img, int screenX, int screenY);
  
  public SettingsGraphic getSettingsGraphic();

  //for zooming: TODO re-do
  public Float getFontSize();

  public Float getFontSizeDefault();

  public void setFontSize(Float fontSizeDefault);

  public Float getWidthLineDefault(ITD itd);

  public void setWidthLineDefault(Float widthLineDefault);
}
