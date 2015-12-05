package org.beigesoft.android.graphic.service;

import java.io.IOException;
import java.io.InputStream;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.pojo.SettingsFont;
import org.beigesoft.graphic.service.ASrvDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.DashPathEffect;

public class SrvDraw extends ASrvDraw<CanvasWithPaint, SettingsDraw, Bitmap> {

  private DashPathEffect dashPathEffect = new DashPathEffect(new float[] {22, 19}, 4);
  
  private Application application;

  private Float widthLineDefault = null;
  
  public SrvDraw(SettingsGraphic sg) {
    super(sg);
  }

  @Override
  public void DrawImage(CanvasWithPaint itd, SettingsDraw ds, Bitmap img, int screenX, int screenY) {
    itd.getCanvas().drawBitmap(img, screenX, screenY, itd.getPaint());
  }

  @Override
  public void drawCircle(CanvasWithPaint itd, SettingsDraw ds, double realX,
      double realY, double radius, boolean isFill) {
    float radiusScreen = Double.valueOf(UtilsGraphMath.toScreenLenghtX(getSettingsGraphic(), radius)).floatValue();
    float screenX = Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), realX)).floatValue();
    float screenY = Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), realY)).floatValue();
    if(radiusScreen < 1) {
      return;
    }
    if(isFill) {
      itd.getPaint().setStyle(Paint.Style.FILL);
    }
    itd.getCanvas().drawCircle(screenX, screenY, radiusScreen, itd.getPaint());
    if(isFill) {
      itd.getPaint().setStyle(Paint.Style.STROKE);
    }
  }

  @Override
  public void drawEllipse(CanvasWithPaint itd, SettingsDraw ds, double x,
      double y, double width, double height, boolean isFill) {
    float left = Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), x)).floatValue();
    float top = Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), y)).floatValue();
    float right = Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), x + width)).floatValue();
    float bottom = Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), y + height)).floatValue();;
    if(Math.abs(left - top) < 1  && Math.abs(right - bottom) < 1) {
      return;
    }
    RectF oval = new RectF(left, top, right, bottom);
    if(isFill) {
      itd.getPaint().setStyle(Paint.Style.FILL);
    }
    itd.getCanvas().drawOval(oval , itd.getPaint());
    if(isFill) {
      itd.getPaint().setStyle(Paint.Style.STROKE);
    }
  }

  @Override
  public void drawLine(CanvasWithPaint itd, SettingsDraw ds, double x1, double y1, double x2, double y2) {
    float startX = Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), x1)).floatValue();
    float startY = Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), y1)).floatValue();
    float stopX = Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), x2)).floatValue();
    float stopY = Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), y2)).floatValue();
    if(Math.abs(startX - stopX) < 1  && Math.abs(startY - stopY) < 1) {
      return;
    }
    itd.getCanvas().drawLine(startX, startY, stopX, stopY, itd.getPaint());
  }

  @Override
  public void drawPath(CanvasWithPaint itd, SettingsDraw ds, double[] setX, double[] setY, int pointsCount
      , boolean isClose, boolean isFill) {
    Path path = new Path();
    path.moveTo(Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), setX[0])).floatValue(), 
        Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), setY[0])).floatValue());
    for(int i=1; i < pointsCount; i++)
      path.lineTo(Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), setX[i])).floatValue(), 
          Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), setY[i])).floatValue());
    if(isClose) {
      path.close();
    }
    if(isFill) {
      itd.getPaint().setStyle(Paint.Style.FILL);
    }
    itd.getCanvas().drawPath(path, itd.getPaint());
    if(isFill) {
      itd.getPaint().setStyle(Paint.Style.STROKE);
    }
  }

  @Override
  public Float getWidthLineDefault(CanvasWithPaint itd) {
    if(widthLineDefault == null) {
      widthLineDefault = itd.getPaint().getStrokeWidth();
    }
    return widthLineDefault;
  }

  @Override
  public void setWidthLineDefault(Float widthLineDefault) {
    this.widthLineDefault = widthLineDefault;
  }

  @Override
  public void drawRectangle(CanvasWithPaint itd, SettingsDraw ds, double x1, double y1, double w, double h, boolean isFill) {
    float left = Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), x1)).floatValue();
    float top = Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), y1)).floatValue();
    float right = Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), x1 + w)).floatValue();
    float bottom = Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), y1 + h)).floatValue();
    if(Math.abs(left - top) < 1  && Math.abs(right - bottom) < 1) {
      return;
    }
    if(isFill && ds.getPathBackgroundImage() == null) {
      itd.getPaint().setStyle(Paint.Style.FILL);
    }
    if(ds.getPathBackgroundImage() != null) {
      itd.getPaint().setStyle(Paint.Style.STROKE);
      InputStream is = null;
      try {
        is = application.getAssets().open(ds.getPathBackgroundImage());
        BitmapDrawable bd = new BitmapDrawable(application.getResources(), is);
        bd.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
        bd.setBounds(Float.valueOf(left).intValue(), Float.valueOf(top).intValue(), 
            Float.valueOf(right).intValue(), Float.valueOf(bottom).intValue());
        bd.draw(itd.getCanvas());        
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if(is != null) {
          try {
            is.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    itd.getCanvas().drawRect(left, top, right, bottom, itd.getPaint());
    if(isFill && ds.getPathBackgroundImage() == null) {
      itd.getPaint().setStyle(Paint.Style.STROKE);
    }
  }

  @Override
  public void drawRectangleRound(CanvasWithPaint itd, SettingsDraw ds, double x1, double y1, double w, double h, double arcw,
      double arch, boolean isFill) {
    float left = Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), x1)).floatValue();
    float top = Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), y1)).floatValue();
    float right = Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), x1 + w)).floatValue();
    float bottom = Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), y1 + h)).floatValue();
    if(Math.abs(left - top) < 1  && Math.abs(right - bottom) < 1) {
      return;
    }
    if(isFill) {
      itd.getPaint().setStyle(Paint.Style.FILL);
    }
    RectF rectF = new RectF(left, top, right, bottom);
    itd.getCanvas().drawRoundRect(rectF,(float) arcw,(float) arch, itd.getPaint());
    if(isFill) {
      itd.getPaint().setStyle(Paint.Style.STROKE);
    }
  }

  @Override
  public void drawString(CanvasWithPaint itd, SettingsDraw ds, String string, double x, double y) {
    float screenX = Double.valueOf(UtilsGraphMath.toScreenX(getSettingsGraphic(), x)).floatValue();
    float screenY = Double.valueOf(UtilsGraphMath.toScreenY(getSettingsGraphic(), y)).floatValue();
    itd.getPaint().setStyle(Paint.Style.FILL);
    itd.getCanvas().drawText(string, screenX, screenY, itd.getPaint());
    itd.getPaint().setStyle(Paint.Style.STROKE);
  }

  @Override
  public void drawString(CanvasWithPaint itd, SettingsDraw ds, String string, int screenX, int screenY) {
    itd.getPaint().setStyle(Paint.Style.FILL);
    itd.getCanvas().drawText(string, screenX, screenY, itd.getPaint());
    itd.getPaint().setStyle(Paint.Style.STROKE);
  }

  @Override
  public int evalLengtStringInPixel(CanvasWithPaint itd, SettingsDraw ds, String str) {
    int result = (int) itd.getPaint().measureText(str);
    return result;
  }

  @Override
  public void prepareFont(CanvasWithPaint itd, SettingsDraw ds, SettingsFont sf) {
    if(sf.getIsBold() && (itd.getPaint().getTypeface() == null || !itd.getPaint().getTypeface().isBold())) {
      Typeface typeFaceOld = itd.getPaint().getTypeface();
      int style = typeFaceOld == null || typeFaceOld.isItalic() ? Typeface.BOLD_ITALIC : Typeface.BOLD;
      Typeface typeFaceNew = Typeface.create(typeFaceOld, style);
      itd.getPaint().setTypeface(typeFaceNew);
    }
    else if(!sf.getIsBold() && itd.getPaint().getTypeface() != null) {
      Typeface typeFaceOld = itd.getPaint().getTypeface();
      int style = typeFaceOld == null || typeFaceOld.isItalic() ? Typeface.ITALIC : Typeface.NORMAL;
      Typeface typeFaceNew = Typeface.create(typeFaceOld, style);
      itd.getPaint().setTypeface(typeFaceNew);
    }
    itd.getPaint().setUnderlineText(sf.getIsUnderlining());
  }

  public void makeFontSize(CanvasWithPaint itd, Float size) {
    if(itd.getPaint().getTextSize() != size) {
      itd.getPaint().setTextSize(size);
    }
  }

  @Override
  public void preparePaint(CanvasWithPaint itd, SettingsDraw drawSettings) {
    getWidthLineDefault(itd);
    itd.getPaint().setColor(Color.rgb(drawSettings.getColor().getRed(), 
        drawSettings.getColor().getGreen(), drawSettings.getColor().getBlue()));
    if(drawSettings.getIsDashed() && itd.getPaint().getPathEffect() != dashPathEffect) {
      itd.getPaint().setPathEffect(dashPathEffect);
    }
    else if(!drawSettings.getIsDashed() && itd.getPaint().getPathEffect() != null) {
      itd.getPaint().setPathEffect(null);
    }
    itd.getPaint().setStrokeWidth(drawSettings.getWidthLine());
    /*BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
    if(drawSettings.getPathBackgroundImage() != null) {
      InputStream is = null;
      try {
        is = application.getAssets().open(drawSettings.getPathBackgroundImage());
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        BitmapShader shader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        itd.getPaint().setShader(shader);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if(is != null) {
          try {
            is.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    else {
      itd.getPaint().setShader(null);
    }*/
    evalFontSizeAndZoom(itd, drawSettings);
  }

  protected void evalFontSizeAndZoom(CanvasWithPaint itd, SettingsDraw ds) {
    if(getFontSizeDefault() == null) {
      setFontSizeDefault(itd.getPaint().getTextSize());
      setLengthControlStringFontDefault(evalLengtStringInPixel(itd, ds, controlString));
    } 
    if(getFontSize() == null) {
      setFontSize(getFontSizeDefault());
    }
    if(getFontSize() != itd.getPaint().getTextSize()) {
      makeFontSize(itd, getFontSize());
      double lenthSpaceCurrent = evalLengtStringInPixel(itd, ds, controlString);
      getSettingsGraphic().setZoom(lenthSpaceCurrent/getLengthControlStringFontDefault());
    }
  }
  
  //SGS:
  public void setDashPathEffect(DashPathEffect dashPathEffect) {
    this.dashPathEffect = dashPathEffect;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }
}
