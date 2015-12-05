package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.UseCase;

public class SrvGraphicUseCase<UC extends UseCase, DRI, SD extends ISettingsDraw> 
    extends ASrvGraphicShapeUml<UC, DRI, SD>{

  public SrvGraphicUseCase(ISrvDraw<DRI, SD, ?> srvDraw,
      SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(UC ge, DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    double heightMarkEllipse = getSettingsGraphic().getWidthMarkEllipse() * 0.6;
    if(ge.getItsName() != null) {
      makeFontBold(di, ds, true);
      double[] widthHeightTextBox = getSrvDraw().evalWidthHeightMultistring(di, ds, ge.getItsName(), "\n", 
          getSettingsGraphic().getLineSpacingCoefficient(), getSettingsGraphic().getMarginElement());
      double width;
      double height;
      width = widthHeightTextBox[0] * getSettingsGraphic().getCoefficientCircleInRectangle();
      if(!ge.getIsRectangle()) {
        height = widthHeightTextBox[1] * getSettingsGraphic().getCoefficientCircleInRectangle();
      }
      else {
        height = widthHeightTextBox[1] + heightMarkEllipse + getSettingsGraphic().getMarginElement();
      }
      width = Math.max(getSettingsGraphic().getWidthMinUserCase(), width);
      height = Math.max(getSettingsGraphic().getHeightMinUserCase(), height);
      if(width > ge.getWidth()  ||
          (width < ge.getWidth() && ge.getIsAdjustMinimumSize())) {
        ge.setWidth(width);
      }
      if(height > ge.getHeight() ||
          (height < ge.getHeight() && ge.getIsAdjustMinimumSize())) {
        ge.setHeight(height);
      }
      double widthChar = getSrvDraw().evalLengthOfString(di, ds, " ");
      double marginTop = widthChar + (!ge.getIsRectangle() ? 0 : heightMarkEllipse);
      Point2D pointStart = new Point2D(ge.getPointStart().getX() + (ge.getWidth() - widthHeightTextBox[0])/2, 
          ge.getPointStart().getY() + (ge.getHeight() - widthHeightTextBox[1])/2 + marginTop);
      getSrvDraw().printMultistring(di, ds, ge.getItsName(), "\n", pointStart,
          getSettingsGraphic().getLineSpacingCoefficient(), getSettingsGraphic().getMarginElement(), true);
      makeFontBold(di, ds, false);
    }
    else {
      ge.setWidth(getSettingsGraphic().getWidthMinUserCase());
      ge.setHeight(getSettingsGraphic().getHeightMinUserCase());
    }
    if(!ge.getIsRectangle()) {
      getSrvDraw().drawEllipse(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
        ge.getWidth(), ge.getHeight(), false);
    }
    else {      
      getSrvDraw().drawEllipse(di, ds, 
          ge.getPointStart().getX() + ge.getWidth() - getSettingsGraphic().getWidthMarkEllipse() - getSettingsGraphic().getMarginElement()*2,
          ge.getPointStart().getY() + getSettingsGraphic().getMarginElement()*2, 
          getSettingsGraphic().getWidthMarkEllipse(), heightMarkEllipse, false);
      getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
        ge.getWidth(), ge.getHeight(), false);
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
