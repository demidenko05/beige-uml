package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.PackageUml;

public class SrvGraphicPackage<M extends PackageUml, DRI, SD extends ISettingsDraw> 
    extends ASrvGraphicShapeUml<M, DRI, SD> {

  public SrvGraphicPackage(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(M ge, DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    double margin = getSettingsGraphic().getMarginElement();
    double widthName = 0;
    double widthChar = 0;
    ge.setHeightHead(getSettingsGraphic().getHeightHeadClass()/3);
    if(ge.getItsName() != null) {
      makeFontBold(di, ds, true);
      widthName = getSrvDraw().evalLengthOfString(di, ds, ge.getItsName());
      widthChar = getSrvDraw().evalLengthOfString(di, ds, "a");
      if(ge.getIsNameInHead()) {
        ge.setWidthHead(Math.max(widthName + widthChar*3, ge.getWidthHead()));
      }
    }
    double width = getSettingsGraphic().getWidthMinClass();
    width = Math.max(width, ge.getWidthHead()*1.4);
    if(width > ge.getWidth() || 
        (ge.getIsAdjustMinimumSize() && width < ge.getWidth())) {
      ge.setWidth(width);
    }
    ge.setWidthHead(Math.max(ge.getWidth()/3, ge.getWidthHead()));
    double[] widthHeightTextBox = null;
    if(ge.getComment() != null) {
      makeFontBold(di, ds, false);
      widthHeightTextBox = getSrvDraw().evalWidthHeightMultistring(di, ds, ge.getComment(), "\n", 
          getSettingsGraphic().getLineSpacingCoefficient(), margin);
      if(ge.getIsNameInHead()) {
        ge.setWidthHead(Math.max(widthHeightTextBox[0], ge.getWidthHead()));
        ge.setHeightHead(Math.max(widthHeightTextBox[1] + getSettingsGraphic().getHeightHeadClass()/3, ge.getHeightHead()));
      }
    }
    double height = getSettingsGraphic().getHeightHeadClass() + ge.getHeightHead();
    if(height > ge.getHeight() || (ge.getIsAdjustMinimumSize() && height < ge.getHeight())) {
      ge.setHeight(height);
    }
    double[] setX = new double[]{ge.getPointStart().getX(), ge.getPointStart().getX(), 
        ge.getPointStart().getX() + ge.getWidthHead(), ge.getPointStart().getX() + ge.getWidthHead()};
    double[] setY = new double[]{ge.getPointStart().getY() + ge.getHeightHead(), 
        ge.getPointStart().getY(), ge.getPointStart().getY(), ge.getPointStart().getY() + ge.getHeightHead()};
    getSrvDraw().drawPath(di, ds, setX, setY, 4, false, false);
    if(ge.getItsName() != null) {
      makeFontBold(di, ds, true);
      double offsetX = ge.getIsNameInHead() ? (ge.getWidthHead() - widthName)/2 : 
        (ge.getWidth() - widthName)/2;
      double nameX = ge.getPointStart().getX() + offsetX;
      double offsetY = ge.getIsNameInHead() ? widthChar*1.6 : 
        ge.getHeight()/2;
      double nameY = ge.getPointStart().getY() + offsetY;
      getSrvDraw().drawString(di, ds, ge.getItsName(), nameX, nameY);
      makeFontBold(di, ds, false);
    }
    if(ge.getComment() != null) {
      double offsetX = ge.getIsNameInHead() ? (ge.getWidthHead() - widthHeightTextBox[0])/2 : 
        (ge.getWidth() - widthHeightTextBox[0])/2;
      double offsetY = ge.getIsNameInHead() ? widthChar*3 : 
        ge.getHeight()/1.6;
      Point2D pointStart = new Point2D(ge.getPointStart().getX() + offsetX, 
          ge.getPointStart().getY() + offsetY);
      getSrvDraw().printMultistring(di, ds, ge.getComment(), "\n", pointStart,
          getSettingsGraphic().getLineSpacingCoefficient(), getSettingsGraphic().getMarginElement(), true);
    }
    getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY() + ge.getHeightHead(), 
        ge.getWidth(), ge.getHeight() - ge.getHeightHead(), false);
    if(ge.getIsSelected()) {
      double widthDragRectangle = getSettingsGraphic().getWidthDragRectangle();
          getSrvDraw().drawRectangle(di, ds, 
          ge.getPointStart().getX() + ge.getWidth() - widthDragRectangle/2, 
          ge.getPointStart().getY() + ge.getHeight() - widthDragRectangle/2, widthDragRectangle,
          widthDragRectangle, false);
    }
  }
}