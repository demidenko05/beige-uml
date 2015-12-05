/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.graphic.awt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.font.TextAttribute;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.service.ASrvDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.pojo.SettingsFont;

/**
 * Implementation with AWT
 * 
 * @author Yury Demidenko
 */
public class SrvDraw extends ASrvDraw<Graphics2D, SettingsDraw, Image> {
  
  private Float widthLineDefault;
  
  private Paint paintDefault;
  
  public SrvDraw(SettingsGraphic sg) {
    super(sg);
  }
  
  @Override
  public void preparePaint(Graphics2D itd, SettingsDraw drawSettings) {
    BasicStroke basicStroke = (BasicStroke)itd.getStroke();
    if(paintDefault == null) {
      paintDefault = itd.getPaint();
    }
    if(drawSettings.getPathBackgroundImage() != null) {
      try {
        BufferedImage bimg = ImageIO.read(getClass().getResource(File.separator + drawSettings.getPathBackgroundImage()));
        TexturePaint texPaint = new TexturePaint(bimg, new Rectangle(0, 0, bimg.getWidth(), bimg.getHeight()));
        itd.setPaint(texPaint);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    else {
      itd.setPaint(paintDefault);
      itd.setColor(new Color(drawSettings.getColor().getRed(), drawSettings.getColor().getGreen()
          , drawSettings.getColor().getBlue()));
    }
    itd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    if(widthLineDefault == null) {
      widthLineDefault = basicStroke.getLineWidth();
    }
    if(drawSettings.getWidthLine() <= 0) {
      drawSettings.setWidthLine(widthLineDefault);
    }
    if(basicStroke.getLineWidth() != drawSettings.getWidthLine() ||
        (drawSettings.getIsDashed() && basicStroke.getDashArray() == null) ||
        (!drawSettings.getIsDashed() && basicStroke.getDashArray() != null)) {
      float[] arrDashed = drawSettings.getIsDashed() ? new float[]{6f, 4f} : null;
      itd.setStroke(new BasicStroke(drawSettings.getWidthLine(), 
          BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, arrDashed, 0f));
    }
    evalFontSizeAndZoom(itd, drawSettings);
  }
  
  @Override
  public void drawPath(Graphics2D itd, SettingsDraw drawSettings, double[] setX, double[] setY, int pointsCount
      , boolean isClose, boolean isFill) {
    GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, pointsCount);
    path.moveTo(UtilsGraphMath.toScreenX(getSettingsGraphic(), setX[0]), UtilsGraphMath.toScreenY(getSettingsGraphic(), setY[0]));
    for(int i=1; i < pointsCount; i++) {
      path.lineTo(UtilsGraphMath.toScreenX(getSettingsGraphic(), setX[i]), UtilsGraphMath.toScreenY(getSettingsGraphic(), setY[i]));
    }
    if(isClose) {
      path.closePath();
    }
    if(isFill) {
      itd.fill(path);
    }
    else {
      itd.draw(path);
    }
  }
  
  @Override
  public void drawString(Graphics2D itd, SettingsDraw ds, String string, int screenX, int screenY) {
    itd.drawString(string, screenX, screenY);
  }

  @Override
  public void drawString(Graphics2D itd, SettingsDraw ds, String string, double x, double y) {
    x = UtilsGraphMath.toScreenX(getSettingsGraphic(), x);
    y = UtilsGraphMath.toScreenY(getSettingsGraphic(), y);
    drawString(itd, ds, string, Double.valueOf(x).intValue(), Double.valueOf(y).intValue());
  }

  @Override
  public void drawLine(Graphics2D itd, SettingsDraw drawSettings, double x1, double y1, double x2, double y2) {
    x1 = UtilsGraphMath.toScreenX(getSettingsGraphic(), x1);
    y1 = UtilsGraphMath.toScreenY(getSettingsGraphic(), y1);
    x2 = UtilsGraphMath.toScreenX(getSettingsGraphic(), x2);
    y2 = UtilsGraphMath.toScreenY(getSettingsGraphic(), y2);
    if(Math.abs(x1-x2) < 1 && Math.abs(y1-y2) < 1) {
      return; 
    }
    itd.draw(new Line2D.Double(x1, y1, x2, y2)); 
  }

  @Override
  public void drawRectangle(Graphics2D itd, SettingsDraw ds, double x1, double y1, double w, double h, boolean isFill) {
    x1 = UtilsGraphMath.toScreenX(getSettingsGraphic(), x1);
    y1 = UtilsGraphMath.toScreenY(getSettingsGraphic(), y1);
    w = UtilsGraphMath.toScreenX(getSettingsGraphic(), w);
    h = UtilsGraphMath.toScreenY(getSettingsGraphic(), h);
    if(w < 1 && h < 1) {
      return; 
    }
    if(isFill) {
      itd.fill(new Rectangle2D.Double(x1, y1, w, h));
    }
    else {
      itd.draw(new Rectangle2D.Double(x1, y1, w, h));
    }
  }

  @Override
  public void drawRectangleRound(Graphics2D itd, SettingsDraw ds, double x1, double y1, double w, double h, double arcw,
      double arch, boolean isFill) {
    x1 = UtilsGraphMath.toScreenX(getSettingsGraphic(), x1);
    y1 = UtilsGraphMath.toScreenY(getSettingsGraphic(), y1);
    w = UtilsGraphMath.toScreenX(getSettingsGraphic(), w);
    h = UtilsGraphMath.toScreenY(getSettingsGraphic(), h);
    if(w < 1 && h < 1) {
      return; 
    }
    if(isFill) {
      itd.fill(new RoundRectangle2D.Double(x1, y1, w, h, arcw, arch));
    }
    else {
      itd.draw(new RoundRectangle2D.Double(x1, y1, w, h, arcw, arch));
    }
  }

  @Override
  public void drawEllipse(Graphics2D itd, SettingsDraw ds, double x,
      double y, double width, double height, boolean isFill) {
    width = UtilsGraphMath.toScreenX(getSettingsGraphic(), width);
    height = UtilsGraphMath.toScreenY(getSettingsGraphic(), height);
    x = UtilsGraphMath.toScreenX(getSettingsGraphic(), x);
    y = UtilsGraphMath.toScreenY(getSettingsGraphic(), y);
    if(width < 1 && height < 1) {
      return; 
    }
    Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, width, height);
    if(isFill) {
      itd.fill(ellipse);  
    }
    else {
      itd.draw(ellipse);  
    }
  }

  @Override
  public void drawCircle(Graphics2D itd, SettingsDraw ds, double realCenterX,
      double realCenterY, double radius, boolean isFill) {
    double width = UtilsGraphMath.toScreenX(getSettingsGraphic(), radius*2);
    double height = UtilsGraphMath.toScreenY(getSettingsGraphic(), radius*2);
    double x = UtilsGraphMath.toScreenX(getSettingsGraphic(), realCenterX - radius);
    double y = UtilsGraphMath.toScreenY(getSettingsGraphic(), realCenterY - radius);
    if(width < 1 && height < 1) {
      return; 
    }
    Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, width, height);
    if(isFill) {
      itd.fill(ellipse);  
    }
    else {
      itd.draw(ellipse);  
    }
  }
  
  @Override
  public void DrawImage(Graphics2D itd, SettingsDraw ds, Image img, int screenX, int screenY) {
    itd.drawImage(img, screenX, screenY, null);
  }

  public void makeFontSize(Graphics2D itd, Float size) {
    String name = itd.getFont().getName();
    int style = itd.getFont().getStyle();
    itd.setFont(new Font(name, style, size.intValue()));
  }

  @Override
  public void prepareFont(Graphics2D itd, SettingsDraw ds, SettingsFont settingsFont) {
    String name = itd.getFont().getName();
    int style = settingsFont.getIsBold() ? (itd.getFont().isItalic() ? Font.BOLD & Font.ITALIC : Font.BOLD) : Font.PLAIN;
    int size = itd.getFont().getSize();
    itd.setFont(new Font(name, style, size));
    Hashtable<TextAttribute, Object> map =
        new Hashtable<TextAttribute, Object>();
    if(settingsFont.getIsUnderlining()) {
      map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
      itd.setFont(itd.getFont().deriveFont(map));
    }
    else if(itd.getFont().getAttributes() != null && 
        itd.getFont().getAttributes().get(TextAttribute.UNDERLINE) == TextAttribute.UNDERLINE_ON) {
      map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE);
      itd.setFont(itd.getFont().deriveFont(map));
    }
  }
  
  @Override
  public int evalLengtStringInPixel(Graphics2D itd, SettingsDraw ds, String string) {
    FontMetrics fontMetrics = itd.getFontMetrics();
    return fontMetrics.stringWidth(string);
  }

  protected void evalFontSizeAndZoom(Graphics2D itd, SettingsDraw ds) {
    if(getFontSizeDefault() == null) {
      setFontSizeDefault(Float.valueOf(itd.getFont().getSize()));
      setLengthControlStringFontDefault(evalLengtStringInPixel(itd, ds, controlString));
    } 
    if(getFontSize() == null) {
      setFontSize(getFontSizeDefault());
    }
    if(getFontSize() != itd.getFont().getSize()) {
      makeFontSize(itd, getFontSize());
      double lenthSpaceCurrent = evalLengtStringInPixel(itd, ds, controlString);
      getSettingsGraphic().setZoom(lenthSpaceCurrent/getLengthControlStringFontDefault());
    }
  }

  @Override
  public Float getWidthLineDefault(Graphics2D itd) {
    if(widthLineDefault == null) {
      BasicStroke basicStroke = (BasicStroke)itd.getStroke();
      widthLineDefault = basicStroke.getLineWidth();
    }
    return widthLineDefault;
  }

  @Override
  public void setWidthLineDefault(Float widthLineDefault) {
    this.widthLineDefault = widthLineDefault;
  }

  public Paint getPaintDefault() {
    return paintDefault;
  }

  public void setPaintDefault(Paint paintDefault) {
    this.paintDefault = paintDefault;
  }
}
