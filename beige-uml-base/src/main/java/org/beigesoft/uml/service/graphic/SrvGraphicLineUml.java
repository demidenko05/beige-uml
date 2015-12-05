package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.service.ISrvGraphicElement;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.model.ELineEndShape;
import org.beigesoft.uml.pojo.LineUml;

public class SrvGraphicLineUml<LN extends LineUml, DRI, SD extends ISettingsDraw> implements ISrvGraphicElement<LN, DRI, SD> {

  private final ISrvDraw<DRI, SD, ?> srvDraw;

  private final SettingsGraphicUml sg;
  
  public SrvGraphicLineUml(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    this.srvDraw = srvDraw;
    this.sg = sg;
  }

  @Override
  public void draw(LN ge, DRI di, SD ds) {
    if(ge.getPoint1() == null || ge.getPoint2() == null || (ge.getPoint1().getX() == ge.getPoint2().getX()
        && ge.getPoint1().getY() == ge.getPoint2().getY())) {
      return;
    }
    getSrvDraw().preparePaint(di, ds);
    drawEnd(ge.getLineEnd1Shape(), true, ge, di, ds);
    drawEnd(ge.getLineEnd2Shape(), false, ge, di, ds);
    if(ge.getIsSelected()) {
      getSrvDraw().drawRectangle(di, ds,
          ge.getPoint1().getX() - getSettingsGraphic().getWidthDragRectangle()/2, 
          ge.getPoint1().getY() - getSettingsGraphic().getWidthDragRectangle()/2, getSettingsGraphic().getWidthDragRectangle(),
          getSettingsGraphic().getWidthDragRectangle(), false);
      getSrvDraw().drawRectangle(di, ds,
          ge.getPoint2().getX() - getSettingsGraphic().getWidthDragRectangle()/2, 
          ge.getPoint2().getY() - getSettingsGraphic().getWidthDragRectangle()/2, getSettingsGraphic().getWidthDragRectangle(),
          getSettingsGraphic().getWidthDragRectangle(), false);
    }
    if(ge.getIsDashed()) {
      ds.setIsDashed(true);
      getSrvDraw().preparePaint(di, ds);
    }
    getSrvDraw().drawLine(di, ds, ge.getPoint1().getX(), ge.getPoint1().getY(), ge.getPoint2().getX(),
        ge.getPoint2().getY());
    if(ge.getIsDashed()) {
      ds.setIsDashed(false);
    }
  }

  protected void drawEnd(ELineEndShape end, boolean is1, LN ge, DRI di, SD ds) {
    if(end != null) {
      double[][] endData = null;
      double x1 = is1 ? ge.getPoint2().getX() : ge.getPoint1().getX();
      double x2 = is1 ? ge.getPoint1().getX() : ge.getPoint2().getX();
      double y1 = is1 ? ge.getPoint2().getY() : ge.getPoint1().getY();
      double y2 = is1 ? ge.getPoint1().getY() : ge.getPoint2().getY();
      boolean isClose = false;
      boolean isFill = false;
      int idxX = 0;
      if(end == ELineEndShape.ARROW) {
        endData = UtilsGraphMath.arrowEndForLineVectorAlgebra(x1, y1, x2, y2, sg.getWidthEndRelation(),
            sg.getWidthEndRelation());
      }
      else {
        endData = UtilsGraphMath.triangleEndForLineVectorAlgebra(x1, y1, x2, y2, sg.getWidthEndRelation(),
            sg.getWidthEndRelation());
        idxX= 1;
        isClose = true;
        isFill = end == ELineEndShape.TRIANGLE_FILLED;
      }
      getSrvDraw().drawPath(di, ds, endData[idxX], endData[idxX+1], 3, isClose, isFill);
    }
  }
  
  @Override
  public void recalculate(LN ge, double coefficient) {
    ge.getPoint1().setX(ge.getPoint1().getX() * coefficient);
    ge.getPoint1().setY(ge.getPoint1().getY() * coefficient);
    ge.getPoint2().setX(ge.getPoint2().getX() * coefficient);
    ge.getPoint2().setY(ge.getPoint2().getY() * coefficient);
  }

  @Override
  public Point2D evalMinimumScreenPoint(LN ge) {
    return new Point2D(Math.min(ge.getPoint1().getX(), ge.getPoint2().getX()), 
        Math.min(ge.getPoint1().getY(), ge.getPoint2().getY()));
  }

  @Override
  public Point2D evalMaximumScreenPoint(LN ge) {
    return new Point2D(Math.max(ge.getPoint1().getX(), ge.getPoint2().getX()), 
        Math.max(ge.getPoint1().getY(), ge.getPoint2().getY()));
  }

  @Override
  public boolean isContainsScreenPoint(LN ge, int x, int y) {
    double realX = UtilsGraphMath.toRealX(sg, x);
    double realY = UtilsGraphMath.toRealY(sg, y);
    if(ge.getIsSelected()) {
      if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), ge.getPoint1(), x, y)) {
        return true;
      }
      if(UtilsGraphMath.dragRentangleContainsOf(getSettingsGraphic(), ge.getPoint2(), x, y)) {
        return true;
      }
    }
    return UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), ge.getPoint1().getX(), 
        ge.getPoint1().getY(), ge.getPoint2().getX(), 
        ge.getPoint2().getY(), realX, realY);
  }

  @Override
  public SettingsGraphicUml getSettingsGraphic() {
    return sg;
  }

  //SGS:
  public ISrvDraw<DRI, SD, ?> getSrvDraw() {
    return srvDraw;
  }
}
