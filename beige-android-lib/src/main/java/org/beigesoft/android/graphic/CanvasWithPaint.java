package org.beigesoft.android.graphic;

import android.graphics.Canvas;
import android.graphics.Paint;

public class CanvasWithPaint {

  private Canvas canvas;
  
  private Paint paint;

  public CanvasWithPaint(Canvas canvas, Paint paint) {
    this.canvas = canvas;
    this.paint = paint;
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public void setCanvas(Canvas canvas) {
    this.canvas = canvas;
  }

  public Paint getPaint() {
    return paint;
  }

  public void setPaint(Paint paint) {
    this.paint = paint;
  }
}
