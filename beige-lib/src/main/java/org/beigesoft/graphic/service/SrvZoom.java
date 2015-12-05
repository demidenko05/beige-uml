package org.beigesoft.graphic.service;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.service.ISrvZoom;

public class SrvZoom implements ISrvZoom {

  private final ASrvDraw<?, ?, ?> srvDraw;
  
  private float stepZoom;
  
  public SrvZoom(ASrvDraw<?, ?, ?> srvDraw) {
    this.srvDraw = srvDraw;
    stepZoom = 1f;
  }

  @Override
  public void makeZoomOut(SettingsGraphic sg) {
    if(sg.getZoom() > sg.getMinZoom()) {
      if(srvDraw.getFontSize() != null && srvDraw.getFontSize() > srvDraw.getFontSizeMin()) {
        srvDraw.setFontSize(srvDraw.getFontSize() - stepZoom);
      }
    }
  }

  @Override
  public void makeZoomIn(SettingsGraphic sg) {
    if(sg.getZoom() < sg.getMaxZoom()) {
      if(srvDraw.getFontSize() != null && srvDraw.getFontSize() < srvDraw.getFontSizeMax()) {
        srvDraw.setFontSize(srvDraw.getFontSize() + stepZoom);
      }
    }
  }

  @Override
  public void makeZoom11(SettingsGraphic sg) {
    sg.setZoom(1);
    sg.setOffsetX(0);
    sg.setOffsetY(0);
    if(srvDraw.getFontSize() != null) {
      srvDraw.setFontSize(srvDraw.getFontSizeDefault());
    }
  }

  public ASrvDraw<?, ?, ?> getSrvDraw() {
    return srvDraw;
  }

  public float getStepZoom() {
    return stepZoom;
  }

  public void setStepZoom(float stepZoom) {
    this.stepZoom = stepZoom;
  }
}
