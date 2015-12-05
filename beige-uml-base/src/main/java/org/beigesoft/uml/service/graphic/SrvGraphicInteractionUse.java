package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.ColorRgb;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.InteractionUse;

public class SrvGraphicInteractionUse<M extends InteractionUse, DRI, SD extends ISettingsDraw> 
    extends ASrvGraphicShapeUml<M, DRI, SD> {

  public SrvGraphicInteractionUse(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(M ge, DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    makeFontBold(di, ds, true);
    evalHeight(di, ds, ge);
    evalWidth(di, ds, ge);
    ge.setWidthHead(getSrvDraw().evalLengthOfString(di, ds, "aaaref"));
    ge.setHeightHead(getSrvDraw().evalLengthOfString(di, ds, "a") * 3);
    double[] setX = new double[]{ge.getPointStart().getX(), ge.getPointStart().getX() + ge.getWidthHead(),
        ge.getPointStart().getX() + ge.getWidthHead() + ge.getHeightHead()/2, 
        ge.getPointStart().getX() + ge.getWidthHead() + ge.getHeightHead()/2};
    double[] setY = new double[]{ge.getPointStart().getY() + ge.getHeightHead(), 
        ge.getPointStart().getY() + ge.getHeightHead(),
        ge.getPointStart().getY() + ge.getHeightHead()/2, ge.getPointStart().getY()};
    ColorRgb paintColor = ds.getColor();
    ds.setColor(ColorRgb.WHITE);
    getSrvDraw().preparePaint(di, ds);
    getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
        ge.getWidth(), ge.getHeight(), true);
    ds.setColor(paintColor);
    getSrvDraw().preparePaint(di, ds);
    getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
        ge.getWidth(), ge.getHeight(), false);
    getSrvDraw().drawPath(di, ds, setX, setY, 4, false, false);
    getSrvDraw().drawString(di, ds, "ref", ge.getPointStart().getX() + ge.getHeightHead()/2, 
        ge.getPointStart().getY() + ge.getHeightHead()/1.3);
    if(ge.getDescription() != null) {
      double widtfDesc = getSrvDraw().evalLengthOfString(di, ds, ge.getDescription());
      getSrvDraw().drawString(di, ds, ge.getDescription(), ge.getPointStart().getX() + (ge.getWidth() - widtfDesc)/2, 
          ge.getPointStart().getY() + ge.getHeight()/2 + widtfDesc/ge.getDescription().length());
    }
    if(ge.getIsSelected()) {
      double widthDragRectangle = getSettingsGraphic().getWidthDragRectangle();
          getSrvDraw().drawRectangle(di, ds,
          ge.getPointStart().getX() + ge.getWidth() - widthDragRectangle/2, 
          ge.getPointStart().getY() + ge.getHeight() - widthDragRectangle/2, widthDragRectangle,
          widthDragRectangle, false);
    }
    makeFontBold(di, ds, false);
  }
  
  public void evalHeight(DRI di, SD ds, M ge) {
    double height = getSettingsGraphic().getHeightHeadClass();
    if(height > ge.getHeight()) {
      ge.setHeight(height);
    }
  }

  public void evalWidth(DRI di, SD ds, M ge) {
    double width = getSettingsGraphic().getWidthMinClass();
    width = Math.max(width, getSrvDraw().evalLengthOfString(di, ds, "aaa" + 
        ge.getDescription()));
    if(width > ge.getWidth()) {
      ge.setWidth(width);
    }
  }
}
