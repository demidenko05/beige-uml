package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.pojo.SettingsFont;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.service.ISrvGraphicElement;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.CoregionFull;

public class SrvGraphicCoregionFull<M extends CoregionFull, DRI, SD extends ISettingsDraw> implements ISrvGraphicElement<M, DRI, SD> {

  private final ISrvDraw<DRI, SD, ?> srvDraw;

  private final SettingsGraphicUml sg;
  
  private SettingsFont settingsFont = new SettingsFont();

  public SrvGraphicCoregionFull(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    this.srvDraw = srvDraw;
    this.sg = sg;
  }

  @Override
  public void draw(M ge , DRI di, SD ds) {
    if(ge.getAsmLifeLineFull() != null && ge.getAsmMessages().size() > 1) {
      ds.setWidthLine(1.5f);
      srvDraw.preparePaint(di, ds);
      ge.setX(ge.getAsmLifeLineFull().getElementUml().getPointStart().getX() + 
          ge.getAsmLifeLineFull().getElementUml().getWidth()/2);
      ge.setMinY(Math.min(ge.getAsmMessages().get(0).getElementUml().getY(),
          ge.getAsmMessages().get(1).getElementUml().getY()));
      ge.setMaxY(Math.max(ge.getAsmMessages().get(0).getElementUml().getY(),
          ge.getAsmMessages().get(1).getElementUml().getY()));
      if(ge.getAsmMessages().size() > 2) {
        for(int i=1; i<ge.getAsmMessages().size()-2; i++) {
          ge.setMinY(Math.min(ge.getAsmMessages().get(i).getElementUml().getY(),
              ge.getMinY()));
          ge.setMaxY(Math.max(ge.getAsmMessages().get(i).getElementUml().getY(),
              ge.getMaxY()));
        }
      }
      double[] coregionX = {ge.getX() - sg.getWidthMessageCoregion()/2, ge.getX() - sg.getWidthMessageCoregion()/2,
          ge.getX() + sg.getWidthMessageCoregion()/2, ge.getX() + sg.getWidthMessageCoregion()/2};
      double[] coregionButtomY = {ge.getMaxY() + sg.getOffsetMessageCoregion(), ge.getMaxY() + sg.getOffsetMessageCoregion() + sg.getHeightMessageCoregion(),
          ge.getMaxY() + sg.getOffsetMessageCoregion() + sg.getHeightMessageCoregion(), ge.getMaxY() + sg.getOffsetMessageCoregion()};
      double[] coregionTopY = {ge.getMinY() - sg.getOffsetMessageCoregion(), ge.getMinY() - sg.getOffsetMessageCoregion() - sg.getHeightMessageCoregion(),
          ge.getMinY() - sg.getOffsetMessageCoregion() - sg.getHeightMessageCoregion(), ge.getMinY() - sg.getOffsetMessageCoregion()};
      srvDraw.drawPath(di, ds, coregionX, coregionTopY, 4, false, false);
      srvDraw.drawPath(di, ds, coregionX, coregionButtomY, 4, false, false);
      sg.getWidthMessageCoregion();
      sg.getHeightMessageCoregion();
      ds.setWidthLine(srvDraw.getWidthLineDefault(di));
    }
  }

  @Override
  public void recalculate(M ge, double coefficient) {
    // nothing
  }

  @Override
  public Point2D evalMinimumScreenPoint(M ge) {//don't care
    return new Point2D(1, 1);
  }

  @Override
  public Point2D evalMaximumScreenPoint(M ge) {//don't care
    return new Point2D(1, 1);
  }

  @Override
  public boolean isContainsScreenPoint(M ge, int x, int y) {
    double realX = UtilsGraphMath.toRealX(sg, x);
    double realY = UtilsGraphMath.toRealY(sg, y);
    double[] coregionX = {ge.getX() - sg.getWidthMessageCoregion()/2,
        ge.getX() + sg.getWidthMessageCoregion()/2};
    double coregionTopY = ge.getMinY() - sg.getOffsetMessageCoregion();
    double coregionButtomY = ge.getMaxY() + sg.getOffsetMessageCoregion();
    if(realX >= coregionX[0] - sg.getSelectApproximation() && realX <= coregionX[1] + sg.getSelectApproximation()
        && ((realY >= coregionTopY - sg.getHeightMessageCoregion() - sg.getSelectApproximation()
        && realY <= coregionTopY + sg.getSelectApproximation()) || 
            (realY <= coregionButtomY + sg.getHeightMessageCoregion() + sg.getSelectApproximation()
        && realY >= coregionButtomY - sg.getSelectApproximation()))) {
      return true;
    }
    return false;
  }

  @Override
  public SettingsGraphicUml getSettingsGraphic() {
    return sg;
  }

  //SGS:
  public SettingsFont getSettingsFont() {
    return settingsFont;
  }

  public void setSettingsFont(SettingsFont settingsFont) {
    this.settingsFont = settingsFont;
  }

  public ISrvDraw<DRI, SD, ?> getSrvDraw() {
    return srvDraw;
  }
}
