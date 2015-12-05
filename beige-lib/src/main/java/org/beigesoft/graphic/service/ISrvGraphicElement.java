package org.beigesoft.graphic.service;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;

/**
 * 
 * @author Yury Demidenko
 *
 * @param <M> model to draw
 * @param <DRI> draw instrument
 * @param <SD> draw settings
 */
public interface ISrvGraphicElement <M, DRI, SD extends ISettingsDraw> {
  
  public void draw(M graphicElement, DRI drawInstrument, SD drawSettings);

  public void recalculate(M graphicElement, double coefficient);

  public Point2D evalMinimumScreenPoint(M graphicElement);

  public Point2D evalMaximumScreenPoint(M graphicElement);

  public boolean isContainsScreenPoint(M graphicElement, int x, int y);
  
  public SettingsGraphic getSettingsGraphic();
}
