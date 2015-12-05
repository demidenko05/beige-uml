package org.beigesoft.graphic.service;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.model.IShape;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.pojo.SettingsFont;

/**
 * 
 * @author Yury Demidenko
 *
 * @param <M> model to draw
 * @param <DRI> draw instrument
 * @param <SD> draw settings
 */
public abstract class ASrvGraphicShape<M extends IShape, DRI, SD extends ISettingsDraw> implements ISrvGraphicElement<M, DRI, SD> {

  private SettingsFont settingsFont = new SettingsFont();
  
  @Override
  public void recalculate(M ge, double coefficient) {
    ge.setWidth(ge.getWidth() * coefficient);
    ge.setHeight(ge.getHeight() * coefficient);
    ge.getPointStart().setX(ge.getPointStart().getX() * coefficient);
    ge.getPointStart().setY(ge.getPointStart().getY() * coefficient);
  }

  @Override
  public Point2D evalMinimumScreenPoint(M ge) {
    Point2D result = new Point2D(UtilsGraphMath.toScreenX(getSettingsGraphic(), ge.getPointStart().getX()),
        UtilsGraphMath.toScreenY(getSettingsGraphic(), ge.getPointStart().getY()));
    return result;
  }

  @Override
  public Point2D evalMaximumScreenPoint(M ge) {
    Point2D result = new Point2D(UtilsGraphMath.toScreenX(getSettingsGraphic(), ge.getPointStart().getX() + ge.getWidth()),
        UtilsGraphMath.toScreenY(getSettingsGraphic(), ge.getPointStart().getY() + ge.getHeight()));
    return result;
  }

  @Override
  public boolean isContainsScreenPoint(M ge, int x, int y) {
    double realX = UtilsGraphMath.toRealX(getSettingsGraphic(), x);
    double realY = UtilsGraphMath.toRealY(getSettingsGraphic(), y);
    if(realX >= ge.getPointStart().getX() && realX <= ge.getPointStart().getX() + ge.getWidth() && 
        realY >= ge.getPointStart().getY() && realY <= ge.getPointStart().getY() + ge.getHeight()) {
      return true;
    }
    return false;
  }

  public SettingsFont getSettingsFont() {
    return settingsFont;
  }

  public void setSettingsFont(SettingsFont settingsFont) {
    this.settingsFont = settingsFont;
  }
}
