package org.beigesoft.android.graphic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.beigesoft.graphic.IDrawable;
import org.beigesoft.graphic.IPaneDrawing;
import org.beigesoft.graphic.SrvPaneDrawing;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.handler.IHandlerEvent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class PaneDrawingAndroid extends SurfaceView implements SurfaceHolder.Callback, IPaneDrawing<CanvasWithPaint>{

  private SrvPaneDrawing<CanvasWithPaint, SettingsDraw, Bitmap> srvPaneDrawing;
  
  private int backgroundColor = Color.WHITE;

  private Paint paint;
  
  private CanvasWithPaint canvasWithPaint;

  private IHandlerEvent<MotionEvent> handlerMotionEvent;  

  public PaneDrawingAndroid(Context context, AttributeSet attrs) {
    super(context, attrs);
    getHolder().addCallback(this);
    paint = new Paint();
    paint.setStyle(Paint.Style.STROKE);
    canvasWithPaint = new CanvasWithPaint(null, paint);
    this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//set hardware acceleration to false as it gives inconsistent results
    setWillNotDraw(false); // Allows for use of invalidate() to call onDraw()
  }

  @Override
  protected void onDraw(Canvas canvas) {
    canvas.drawColor(backgroundColor);
    canvasWithPaint.setCanvas(canvas);
    srvPaneDrawing.paint(canvasWithPaint);
  }
  
  @Override
  public boolean performClick() {
    return super.performClick();
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    performClick();
    if(handlerMotionEvent != null && handlerMotionEvent.handleEvent(ev)) {
      return true;
    }
    return false;
  }

  @Override
  public List<IDrawable<CanvasWithPaint>> getDrawableList() {
    return srvPaneDrawing.getDrawableList();
  }

  @Override
  public void paintAndSaveImageFor(File outputfile, int width, int height)
      throws Exception {
    Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas c = new Canvas(b);
    draw(c);
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    b.compress(CompressFormat.PNG, 100, stream);
    OutputStream out = new FileOutputStream(outputfile);
    stream.writeTo(out);
    out.close();
    stream.close();
  }

  @Override
  public void repaint() {
    invalidate();
  }

  @Override
  public void repaintForced() {
    Bitmap b = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);//just to recalculate width height TODO re-do
    Canvas c = new Canvas(b);
    draw(c);
  }

  @Override
  public String saveCanvasAsImage(String absolutePath) {
    return srvPaneDrawing.saveCanvasAsImage(absolutePath);
  }

  @Override
  public void setMargin(int margin) {
    srvPaneDrawing.setMargin(margin);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    // nothing
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width,
      int height) {
    // nothing
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    // nothing   
  }

  @Override
  public SrvPaneDrawing<CanvasWithPaint, SettingsDraw, Bitmap> getSrvPaneDrawing() {
    return srvPaneDrawing;
  }

  //SGS:
  public void setSrvPaneDrawing(
      SrvPaneDrawing<CanvasWithPaint, SettingsDraw, Bitmap> srvPaneDrawing) {
    this.srvPaneDrawing = srvPaneDrawing;
  }

  public int getBackgroundColor() {
    return backgroundColor;
  }

  public IHandlerEvent<MotionEvent> getHandlerMotionEvent() {
    return handlerMotionEvent;
  }

  public void setHandlerMotionEvent(IHandlerEvent<MotionEvent> handlerMotionEvent) {
    this.handlerMotionEvent = handlerMotionEvent;
  }

  public Paint getPaint() {
    return paint;
  }

  public void setPaint(Paint paint) {
    this.paint = paint;
  }

  public CanvasWithPaint getCanvasWithPaint() {
    return canvasWithPaint;
  }

  public void setCanvasWithPaint(CanvasWithPaint canvasWithPaint) {
    this.canvasWithPaint = canvasWithPaint;
  }
}
