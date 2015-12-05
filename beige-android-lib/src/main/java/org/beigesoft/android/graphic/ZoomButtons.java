package org.beigesoft.android.graphic;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.beigesoft.android.R;
import org.beigesoft.graphic.IDrawable;
import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvZoom;

public class ZoomButtons implements IDrawable<CanvasWithPaint> {

  protected Bitmap btZoomIn;
  
  protected Bitmap btZoomOut;
  
  protected Bitmap btZoom11;
  
  private final Activity activity;

  protected float xZoomIn;

  protected float xZoomOut;

  protected float xZoom11;

  protected float yZoomBt;
  
  private float gap = 14;
  
  private float selectApproximatiom = 4;
  
  private float sizeButton = 24;

  private final SettingsGraphic settingsGraphic;
  
  private final ISrvZoom srvZoom;
  
  boolean isVisible = true;

  private Integer indexZ = 2000;
  
  public ZoomButtons(Activity activity, SettingsGraphic settingsGraphic, ISrvZoom srvZoom) {
    this.activity = activity;
    this.settingsGraphic = settingsGraphic;
    this.srvZoom = srvZoom;
    btZoomIn = BitmapFactory.decodeResource(activity.getResources(), R.drawable.zoom_in);
    btZoomOut = BitmapFactory.decodeResource(activity.getResources(), R.drawable.zoom_out);
    btZoom11 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.zoom_11);
    sizeButton = btZoomIn.getHeight();
  }

  @Override
  public void draw(CanvasWithPaint di) {
    yZoomBt = di.getCanvas().getHeight() - sizeButton - gap;
    xZoomOut = di.getCanvas().getWidth() - sizeButton * 3 - gap * 3;
    xZoom11 = xZoomOut + sizeButton + gap;
    xZoomIn = xZoom11 + sizeButton + gap;
    di.getCanvas().drawBitmap(btZoomOut, xZoomOut, yZoomBt, null);
    di.getCanvas().drawBitmap(btZoom11, xZoom11, yZoomBt, null);
    di.getCanvas().drawBitmap(btZoomIn, xZoomIn, yZoomBt, null);
  }

  @Override
  public boolean getIsVisible() {
    // TODO Auto-generated method stub
    return isVisible;
  }

  @Override
  public void setIsVisible(boolean isVisible) {
    this.isVisible = isVisible;
  }

  public boolean isZoomButtonPressed(float x, float y) {
    if(y >= yZoomBt - selectApproximatiom && y <= yZoomBt + sizeButton + selectApproximatiom) {
      if(x >= xZoomOut - selectApproximatiom && x <= xZoomOut + sizeButton + selectApproximatiom) {
        srvZoom.makeZoomOut(settingsGraphic);
        return true;
      }
      else if(x >= xZoom11 - selectApproximatiom && x <= xZoom11 + sizeButton + selectApproximatiom) {
        srvZoom.makeZoom11(settingsGraphic);
        return true;
      }
      else if(x >= xZoomIn - selectApproximatiom && x <= xZoomIn + sizeButton + selectApproximatiom) {
        srvZoom.makeZoomIn(settingsGraphic);
        return true;
      }
    }
    return false;
  }

  //stubs:
  @Override
  public Point2D evalMaximumScreenPoint() {
    return new Point2D(1, 1);
  }

  @Override
  public Point2D evalMinimumScreenPoint() {
    return new Point2D(1, 1);
  }

  @Override
  public boolean isContainsScreenPoint(int x, int y) {
    return false;
  }

  @Override
  public void recalculate(double coef) {
    // stub  
  }

  public Activity getActivity() {
    return activity;
  }

  public SettingsGraphic getSettingsGraphic() {
    return settingsGraphic;
  }

  public ISrvZoom getSrvZoom() {
    return srvZoom;
  }

  public float getGap() {
    return gap;
  }

  public void setGap(float gap) {
    this.gap = gap;
  }

  public float getSizeButton() {
    return sizeButton;
  }

  public void setSizeButton(float sizeButton) {
    this.sizeButton = sizeButton;
  }

  public float getSelectApproximatiom() {
    return selectApproximatiom;
  }

  public void setSelectApproximatiom(float selectApproximatiom) {
    this.selectApproximatiom = selectApproximatiom;
  }

  @Override
  public Integer getIndexZ() {
    return indexZ;
  }

  @Override
  public void setIndexZ(Integer indexZ) {
    this.indexZ = indexZ;
  }
}
