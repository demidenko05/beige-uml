package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.service.ISrvGraphicElement;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.ColorRgb;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.pojo.SettingsFont;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.TextUml;

public class SrvGraphicText<TX extends TextUml, DRI, SD extends ISettingsDraw> implements ISrvGraphicElement<TX, DRI, SD> {

  private final ISrvDraw<DRI, SD, ?> srvDraw;

  private final SettingsGraphicUml sg;
  
  private SettingsFont settingsFont = new SettingsFont();

  public SrvGraphicText(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    this.srvDraw = srvDraw;
    this.sg = sg;
  }

  @Override
  public void draw(TX ge, DRI di, SD ds) {
    if(ge.getItsText() != null) {
      ge.setHeightText(srvDraw.evalLengthOfString(di, ds, "a") * 2);
      ge.setLengthText(srvDraw.evalLengthOfString(di, ds, ge.getItsText()));
      srvDraw.preparePaint(di, ds);
      if(ge.getIsBold()) {
        makeFontBold(di, ds, true);
      }
      if(!ge.getIsTransparent()) {
        ColorRgb colorWas = ds.getColor();
        ds.setColor(ColorRgb.WHITE);
        srvDraw.preparePaint(di, ds);
        srvDraw.drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY() - ge.getHeightText()/1.3, 
            ge.getLengthText(), ge.getHeightText(), true);
        ds.setColor(colorWas);
        srvDraw.preparePaint(di, ds);
      }
      srvDraw.drawString(di, ds, ge.getItsText(),
          ge.getPointStart().getX(), ge.getPointStart().getY());
      if(ge.getIsBold()) {
        makeFontBold(di, ds, false);
      }
    }
  }

  @Override
  public void recalculate(TX ge, double coefficient) {
    ge.getPointStart().setX(ge.getPointStart().getX() * coefficient);
    ge.getPointStart().setY(ge.getPointStart().getY() * coefficient);
  }

  @Override
  public Point2D evalMinimumScreenPoint(TX ge) {
    return new Point2D(ge.getPointStart().getX(), ge.getPointStart().getY());
  }

  @Override
  public Point2D evalMaximumScreenPoint(TX ge) {
    return new Point2D(ge.getPointStart().getX() + ge.getLengthText(), ge.getPointStart().getY() + ge.getHeightText());
  }

  @Override
  public boolean isContainsScreenPoint(TX ge, int x, int y) {
    double realX = UtilsGraphMath.toRealX(sg, x);
    double realY = UtilsGraphMath.toRealY(sg, y);
    if(realX >= ge.getPointStart().getX() - sg.getSelectApproximation() && realX <= ge.getPointStart().getX() + ge.getLengthText() + sg.getSelectApproximation()
        && realY <= ge.getPointStart().getY() + sg.getSelectApproximation() && realY >= ge.getPointStart().getY() - ge.getHeightText()/1.2 - sg.getSelectApproximation()) {
      return true;
    }
    return false;
  }

  @Override
  public SettingsGraphicUml getSettingsGraphic() {
    return sg;
  }

  protected void makeFontBold(DRI di, SD ds, boolean isbold) {
    getSettingsFont().setIsBold(isbold);
    srvDraw.prepareFont(di, ds, getSettingsFont());
  }

  //SGS:
  public ISrvDraw<DRI, SD, ?> getDrawService() {
    return srvDraw;
  }

  public SettingsFont getSettingsFont() {
    return settingsFont;
  }

  public void setSettingsFont(SettingsFont settingsFont) {
    this.settingsFont = settingsFont;
  }
}
