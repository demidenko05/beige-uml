package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.ColorRgb;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.CombinedFragment;

public class SrvGraphicCombinedFragment<M extends CombinedFragment, DRI, SD extends ISettingsDraw> 
    extends ASrvGraphicShapeUml<M, DRI, SD> {

  public SrvGraphicCombinedFragment(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(M ge, DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    makeFontBold(di, ds, true);
    evalHeight(di, ds, ge);
    evalWidth(di, ds, ge);
    String strName = ge.getInteractionOperator().toString();
    if(ge.getDescription() != null) {
      strName += " " + ge.getDescription();
    }
    ge.setWidthHead(getSrvDraw().evalLengthOfString(di, ds, "aaa" + strName));
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
        ge.getWidthHead(), ge.getHeightHead(), true);
    ds.setColor(paintColor);
    getSrvDraw().preparePaint(di, ds);
    getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
        ge.getWidth(), ge.getHeight(), false);
    getSrvDraw().drawPath(di, ds, setX, setY, 4, false, false);
    double widthDragRectangle = getSettingsGraphic().getWidthDragRectangle();
    ds.setIsDashed(true);
    getSrvDraw().preparePaint(di, ds);
    for(Double traceY : ge.getTracesY()) {
      double y = ge.getPointStart().getY() + traceY;
      getSrvDraw().drawLine(di, ds, ge.getPointStart().getX(), y, ge.getPointStart().getX() + ge.getWidth(), y);
      if(ge.getIsSelected()) {
        getSrvDraw().drawRectangle(di, ds,
        ge.getPointStart().getX() + ge.getWidth()/1.5 - widthDragRectangle*2, 
        y - widthDragRectangle/2, widthDragRectangle,
        widthDragRectangle, false);
      }
    }
    ds.setIsDashed(false);
    getSrvDraw().drawString(di, ds, strName, ge.getPointStart().getX() + ge.getHeightHead()/2, 
        ge.getPointStart().getY() + ge.getHeightHead()/1.3);
    if(ge.getIsSelected()) {
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
        ge.getInteractionOperator() + ge.getDescription()));
    if(width > ge.getWidth()) {
      ge.setWidth(width);
    }
  }
}
