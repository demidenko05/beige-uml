package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.ColorRgb;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.StateInvContin;

public class SrvGraphicStateInvContin<M extends StateInvContin, DRI, SD extends ISettingsDraw> 
    extends ASrvGraphicShapeUml<M, DRI, SD> {

  public SrvGraphicStateInvContin(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(M ge, DRI di, SD ds) {
    double widthName = 0;
    double widthChar = 0;
    if(ge.getItsName() != null) {
      widthName = getSrvDraw().evalLengthOfString(di, ds, ge.getItsName());
      widthChar = widthName / ge.getItsName().length();
    }
    double width = Math.max(getSettingsGraphic().getWidthMinStateInvContin(), widthName + widthChar*2);
    if(width > ge.getWidth() || 
        (ge.getIsAdjustMinimumSize() && width < ge.getWidth())) {
      ge.setWidth(width);
    }
    double height = Math.max(getSettingsGraphic().getWidthMinStateInvContin()/1.6, widthChar*3);
    if(height > ge.getHeight() || (ge.getIsAdjustMinimumSize() && height < ge.getHeight())) {
      ge.setHeight(height);
    }
    ColorRgb paintColor = ds.getColor();
    ds.setColor(ColorRgb.WHITE);
    getSrvDraw().preparePaint(di, ds);
    getSrvDraw().drawRectangleRound(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
        ge.getWidth(), ge.getHeight(), ge.getHeight()*getSettingsGraphic().getCoefRoundRectangle(), 
        ge.getHeight()*getSettingsGraphic().getCoefRoundRectangle(), true);
    ds.setColor(paintColor);
    getSrvDraw().preparePaint(di, ds);
    getSrvDraw().drawRectangleRound(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
        ge.getWidth(), ge.getHeight(), ge.getHeight()*getSettingsGraphic().getCoefRoundRectangle(), 
        ge.getHeight()*getSettingsGraphic().getCoefRoundRectangle(), false);
    if(ge.getItsName() != null) {
      if(ge.getIsBold()) {
        makeFontBold(di, ds, true);
      }
      double offsetX = (ge.getWidth() - widthName)/2;
      double nameX = ge.getPointStart().getX() + offsetX;
      double offsetY = ge.getHeight()/1.5;
      double nameY = ge.getPointStart().getY() + offsetY;
      getSrvDraw().drawString(di, ds, ge.getItsName(), nameX, nameY);
      if(ge.getIsBold()) {
        makeFontBold(di, ds, false);
      }
    }
    if(ge.getIsSelected()) {
      double widthDragRectangle = getSettingsGraphic().getWidthDragRectangle();
          getSrvDraw().drawRectangle(di, ds, 
          ge.getPointStart().getX() + ge.getWidth() - widthDragRectangle/2, 
          ge.getPointStart().getY() + ge.getHeight() - widthDragRectangle/2, widthDragRectangle,
          widthDragRectangle, false);
    }
  }
}