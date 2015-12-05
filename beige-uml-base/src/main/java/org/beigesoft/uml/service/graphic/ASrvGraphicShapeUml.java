package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.service.ASrvGraphicShape;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.model.IShapeUml;

public abstract class ASrvGraphicShapeUml<M extends IShapeUml, DRI, SD extends ISettingsDraw> 
    extends ASrvGraphicShape<M, DRI, SD> {

  private final ISrvDraw<DRI, SD, ?> srvDraw;

  private final SettingsGraphicUml sg;
  
  public ASrvGraphicShapeUml(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    this.srvDraw = srvDraw;
    this.sg = sg;
  }

  @Override
  public boolean isContainsScreenPoint(M ge, int x, int y) {
    if(super.isContainsScreenPoint(ge, x, y)) {
      return true;
    }
    if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), 
        new Point2D(ge.getPointStart().getX() + ge.getWidth(), ge.getPointStart().getY() + ge.getHeight()) , x, y)) {
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
  public ISrvDraw<DRI, SD, ?> getSrvDraw() {
    return srvDraw;
  }
}
