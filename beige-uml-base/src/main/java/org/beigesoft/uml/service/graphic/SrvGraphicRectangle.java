package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.ColorRgb;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.RectangleUml;

public class SrvGraphicRectangle<M extends RectangleUml, DRI, SD extends ISettingsDraw> 
    extends ASrvGraphicShapeUml<M, DRI, SD> {

  public SrvGraphicRectangle(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(M ge, DRI di, SD ds) {
    evalHeight(di, ge);
    evalWidth(di, ge);
    if(!ge.getIsTransparent()) {
      ColorRgb colorWas = ds.getColor();
      ds.setColor(ColorRgb.WHITE);
      getSrvDraw().preparePaint(di, ds);
      getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
          ge.getWidth(), ge.getHeight(), true);
      ds.setColor(colorWas);
    }
    getSrvDraw().preparePaint(di, ds);
    getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
        ge.getWidth(), ge.getHeight(), false);
    if(ge.getIsSelected()) {
      double[] setX = new double[]{ge.getPointStart().getX(), ge.getPointStart().getX(), 
          ge.getPointStart().getX() + ge.getWidth()/2, ge.getPointStart().getX() + ge.getWidth()/2};
      double[] setY = new double[]{ge.getPointStart().getY(), 
          ge.getPointStart().getY() + getSettingsGraphic().getWidthDragRectangle()*2,
          ge.getPointStart().getY() + getSettingsGraphic().getWidthDragRectangle()*2,
          ge.getPointStart().getY()};
      getSrvDraw().drawPath(di, ds, setX, setY, 4, true, false);
      double widthDragRectangle = getSettingsGraphic().getWidthDragRectangle();
          getSrvDraw().drawRectangle(di, ds, 
          ge.getPointStart().getX() + ge.getWidth() - widthDragRectangle/2, 
          ge.getPointStart().getY() + ge.getHeight() - widthDragRectangle/2, widthDragRectangle,
          widthDragRectangle, false);
    }
  }
  
  public void evalHeight(DRI di, M ge) {
    double height = getSettingsGraphic().getWidthExecution();
    if(height > ge.getHeight()) {
      ge.setHeight(height);
    }
  }

  public void evalWidth(DRI di, M ge) {
    double width = getSettingsGraphic().getWidthExecution();
    if(width > ge.getWidth()) {
      ge.setWidth(width);
    }
  }
}
