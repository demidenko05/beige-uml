package org.beigesoft.graphic;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.beigesoft.delegate.IDelegate;
import org.beigesoft.graphic.IDrawable;
import org.beigesoft.graphic.IPaneDrawing;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.handler.IObserverRepaint;

public class SrvPaneDrawing<ITD, SD extends ISettingsDraw, IMG> {
  
  private final List<IDrawable<ITD>> drawableList;
  
  private int widthArea;
  
  private int heigthArea;
  
  private Point2D screenPointMinimum = new Point2D(30, 30);
    
  private int margin = 30;
    
  private final ISrvDraw<ITD, SD, IMG> srvDraw;
  
  private final Set<IObserverRepaint> observersRepaint;
  
  protected ComparatorDrawbleIndexZ comparatorDrawbleIndexZ = new ComparatorDrawbleIndexZ();
  
  private IDelegate<Boolean> delegatorSetStuffVisibility = new IDelegate<Boolean>() {
    
    @Override
    public void makeWith(Boolean m) {
      // stub for Swing
    }
  };
  
  private IPaneDrawing<ITD> paneDrawing;
    
  public SrvPaneDrawing(ISrvDraw<ITD, SD, IMG> srvDraw) {
    this.srvDraw = srvDraw;
    observersRepaint = new HashSet<IObserverRepaint>();
    drawableList = new ArrayList<IDrawable<ITD>>();
  }

  public void paint(ITD itd) {
    if(drawableList != null) {
      widthArea = 200;
      heigthArea = 200;
      screenPointMinimum.setX(400);
      screenPointMinimum.setY(400);
      synchronized (drawableList) {
        for(IDrawable<ITD> drawable : drawableList) {
          if(drawable.getIsVisible()) {
            drawable.draw(itd);
            Point2D maxScrPoint = drawable.evalMaximumScreenPoint();
            if(maxScrPoint.getX() + margin > widthArea) {
              widthArea = Double.valueOf(maxScrPoint.getX()).intValue() + margin;
            }
            if(maxScrPoint.getY() + margin > heigthArea) {
              heigthArea = Double.valueOf(maxScrPoint.getY()).intValue() + margin;
            }
            Point2D minScrPoint = drawable.evalMinimumScreenPoint();
            if(minScrPoint.getX() < screenPointMinimum.getX()) {
              screenPointMinimum.setX(minScrPoint.getX());
            }
            if(minScrPoint.getY() < screenPointMinimum.getY()) {
              screenPointMinimum.setY(minScrPoint.getY());
            }
          }
        }
        for(IObserverRepaint obs : observersRepaint) {
          obs.notifyRepaintDone();
        }
      }
    }
  }

  public String saveCanvasAsImage(String absolutePath) {//TODO re-do
    delegatorSetStuffVisibility.makeWith(false);
    File outputfile = new File(absolutePath+".png");
    Float currentFontSize = null;
    Double currentOffsetX = srvDraw.getSettingsGraphic().getOffsetX() == 0 ? null : 
      srvDraw.getSettingsGraphic().getOffsetX();
    Double currentOffsetY = srvDraw.getSettingsGraphic().getOffsetY() == 0 ? null : 
      srvDraw.getSettingsGraphic().getOffsetY();
    if(currentOffsetX != null) {
      srvDraw.getSettingsGraphic().setOffsetX(0);
    }
    if(currentOffsetY != null) {
      srvDraw.getSettingsGraphic().setOffsetY(0);
    }
    double currentZoom = 0;
    if(srvDraw.getSettingsGraphic().getZoom() != 1d) {
      currentFontSize = srvDraw.getFontSize();
      currentZoom = srvDraw.getSettingsGraphic().getZoom();
      srvDraw.getSettingsGraphic().setZoom(1);
      if(srvDraw.getFontSize() != null) {
        srvDraw.setFontSize(srvDraw.getFontSizeDefault());
      }
    }
    int width = widthArea;
    int height = heigthArea;
    if(currentFontSize != null) {
      width = Double.valueOf(width/currentZoom).intValue();
      height = Double.valueOf(height/currentZoom).intValue();
    }
    try {
      paneDrawing.paintAndSaveImageFor(outputfile, width, height);
    } catch (Exception e) {
      e.printStackTrace();
      return("error_save_png");
    }
    if(currentFontSize != null) {
      srvDraw.getSettingsGraphic().setZoom(currentZoom);
      srvDraw.setFontSize(currentFontSize);
    }
    if(currentOffsetX != null) {
      srvDraw.getSettingsGraphic().setOffsetX(currentOffsetX);
    }
    if(currentOffsetY != null) {
      srvDraw.getSettingsGraphic().setOffsetY(currentOffsetY);
    }
    if(currentFontSize != null || currentOffsetX != null || currentOffsetY != null) {
      paneDrawing.repaint();
    }
    delegatorSetStuffVisibility.makeWith(true);
    return null;
  }
  
  public void handleChangesIndexZ() {
    Collections.sort(drawableList, comparatorDrawbleIndexZ);
  }
  
  public void addObserverRepaint(IObserverRepaint observerRepaint) {
    this.observersRepaint.add(observerRepaint);
  }

  //SGS:
  public int getWidthArea() {
    return widthArea;
  }

  public void setWidthArea(int widthArea) {
    this.widthArea = widthArea;
  }

  public int getHeigthArea() {
    return heigthArea;
  }

  public void setHeigthArea(int heigthArea) {
    this.heigthArea = heigthArea;
  }

  public Point2D getScreenPointMinimum() {
    return screenPointMinimum;
  }

  public void setScreenPointMinimum(Point2D screenPointMinimum) {
    this.screenPointMinimum = screenPointMinimum;
  }

  public int getMargin() {
    return margin;
  }

  public void setMargin(int margin) {
    this.margin = margin;
  }

  public List<IDrawable<ITD>> getDrawableList() {
    return drawableList;
  }

  public ISrvDraw<ITD, ?, ?> getSrvDraw() {
    return srvDraw;
  }

  public Set<IObserverRepaint> getObserversRepaint() {
    return observersRepaint;
  }

  public IPaneDrawing<ITD> getPaneDrawing() {
    return paneDrawing;
  }

  public void setPaneDrawing(IPaneDrawing<ITD> paneDrawing) {
    this.paneDrawing = paneDrawing;
  }

  public IDelegate<Boolean> getDelegatorSetStuffVisibility() {
    return delegatorSetStuffVisibility;
  }

  public void setDelegatorSetStuffVisibility(
      IDelegate<Boolean> delegatorSetStuffVisibility) {
    this.delegatorSetStuffVisibility = delegatorSetStuffVisibility;
  }
}
