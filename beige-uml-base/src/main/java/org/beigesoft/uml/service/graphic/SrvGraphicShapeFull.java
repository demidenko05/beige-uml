package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvGraphicElement;
import org.beigesoft.graphic.service.ASrvGraphicShape;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.ShapeUml;

public class SrvGraphicShapeFull<SHF extends ShapeFull<SH>, DRI, SD extends ISettingsDraw, SH extends ShapeUml> 
  implements ISrvGraphicElement<SHF, DRI, SD> {
  
  private final ASrvGraphicShape<SH, DRI, SD> srvGraphicShape;

  public SrvGraphicShapeFull(ASrvGraphicShape<SH, DRI, SD> srvGraphicShape) {
    this.srvGraphicShape = srvGraphicShape;
  }

  @Override
  public void draw(SHF graphicElement, DRI drawInstrument, SD drawSettings) {
    srvGraphicShape.draw(graphicElement.getShape(), drawInstrument, drawSettings);
  }

  @Override
  public void recalculate(SHF graphicElement, double coefficient) {
    srvGraphicShape.recalculate(graphicElement.getShape(), coefficient);
  }

  @Override
  public Point2D evalMinimumScreenPoint(SHF ge) {
    return srvGraphicShape.evalMinimumScreenPoint(ge.getShape());
  }

  @Override
  public Point2D evalMaximumScreenPoint(SHF ge) {
    return srvGraphicShape.evalMaximumScreenPoint(ge.getShape());
  }

  @Override
  public boolean isContainsScreenPoint(SHF ge, int x, int y) {
    return srvGraphicShape.isContainsScreenPoint(ge.getShape(), x, y);
  }

  @Override
  public SettingsGraphicUml getSettingsGraphic() {
    return (SettingsGraphicUml) srvGraphicShape.getSettingsGraphic();
  }

  public ASrvGraphicShape<SH, DRI, SD> getSrvGraphicShape() {
    return srvGraphicShape;
  }
}
