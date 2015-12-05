package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.UseCaseExtended;

public class SrvGraphicUseCaseExtended<UC extends UseCaseExtended, DRI, SD extends ISettingsDraw> 
    extends ASrvGraphicShapeUml<UC, DRI, SD>{

  public SrvGraphicUseCaseExtended(ISrvDraw<DRI, SD, ?> srvDraw,
      SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(UC ge, DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    makeFontBold(di, ds, true);
    String strExtPointsTitle = "extension points";
    double widthExtPointsTitle = getSrvDraw().evalLengthOfString(di, ds, strExtPointsTitle);
    double widthBoldChar = getSrvDraw().evalLengthOfString(di, ds, "a");
    double[] widthHeightNameBox = null;
    Point2D pointNameBox = null;
    double heightMarkEllipse = getSettingsGraphic().getWidthMarkEllipse() * 0.6;
    double marginTop = widthBoldChar;
    if(ge.getItsName() != null && ge.getIsRectangle()) {
      marginTop = widthBoldChar + heightMarkEllipse;
      widthHeightNameBox = getSrvDraw().evalWidthHeightMultistring(di, ds, ge.getItsName(), "\n", 
          getSettingsGraphic().getLineSpacingCoefficient(), getSettingsGraphic().getMarginElement());
    }
    double widthExtPoints = 0;
    double extPointMargins = widthBoldChar*2;
    double heightExtPoints = extPointMargins;
    for(HasNameEditable ep : ge.getExtentionPoints()) {
      widthExtPoints = Math.max(widthExtPoints, getSrvDraw().evalLengthOfString(di, ds, ep.getItsName()));
      heightExtPoints += widthBoldChar*getSettingsGraphic().getLineSpacingCoefficient();
    }
    double width = Math.max(widthExtPointsTitle, widthExtPoints);
    double height = marginTop + heightExtPoints + widthBoldChar;
    if(ge.getItsName() != null && ge.getIsRectangle()) {
      width = Math.max(width, widthHeightNameBox[0]);
      height += widthHeightNameBox[1];
    }
    width = Math.max(getSettingsGraphic().getWidthMinUserCase(), width);
    height = Math.max(getSettingsGraphic().getHeightMinUserCase(), height);
    width = width * getSettingsGraphic().getCoefficientCircleInRectangle();
    if(!ge.getIsRectangle()) {
      height = height * getSettingsGraphic().getCoefficientCircleInRectangle();
    }
    else {
      height = height + heightMarkEllipse + getSettingsGraphic().getMarginElement();
    }
    if(width > ge.getWidth()  ||
        (width < ge.getWidth() && ge.getIsAdjustMinimumSize())) {
      ge.setWidth(width);
    }
    if(height > ge.getHeight() ||
        (height < ge.getHeight() && ge.getIsAdjustMinimumSize())) {
      ge.setHeight(height);
    }
    double extPntTitleX = ge.getPointStart().getX() + (ge.getWidth() - widthExtPointsTitle)/2;
    double lineY;
    if(ge.getItsName() != null && ge.getIsRectangle()) {
      pointNameBox = new Point2D(ge.getPointStart().getX() + (ge.getWidth() - widthHeightNameBox[0])/2, 
          ge.getPointStart().getY() + marginTop);
      getSrvDraw().printMultistring(di, ds, ge.getItsName(), "\n", pointNameBox,
          getSettingsGraphic().getLineSpacingCoefficient(), getSettingsGraphic().getMarginElement(), true);
      lineY = pointNameBox.getY() + widthHeightNameBox[1];
      getSrvDraw().drawLine(di, ds, ge.getPointStart().getX(), lineY, ge.getPointStart().getX() + ge.getWidth(), lineY);
      getSrvDraw().drawEllipse(di, ds,
          ge.getPointStart().getX() + ge.getWidth() - getSettingsGraphic().getWidthMarkEllipse() - getSettingsGraphic().getMarginElement()*2,
          ge.getPointStart().getY() + getSettingsGraphic().getMarginElement()*2, 
          getSettingsGraphic().getWidthMarkEllipse(), heightMarkEllipse, false);
      getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
        ge.getWidth(), ge.getHeight(), false);
    }
    else {
      getSrvDraw().drawEllipse(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
          ge.getWidth(), ge.getHeight(), false);
      lineY = ge.getPointStart().getY() + ge.getHeight()/3.5;
      //ellipse equation (origin x=0,y=0): x^2/a^2 + y^2/b^2 = 1
      //x = +-sqrt(a^2 * (1 - y^2/b^2))
      double a = ge.getWidth()/2;
      double b = ge.getHeight()/2;
      double y = lineY - ge.getPointStart().getY() - b;
      double x = Math.sqrt(a*a*(1-(y*y)/(b*b)));
      getSrvDraw().drawLine(di, ds, ge.getPointStart().getX() + a - x, lineY, 
          ge.getPointStart().getX() + a + x, lineY);
    }
    double extPntTitleY = lineY + getSettingsGraphic().getMarginElement() + widthBoldChar*1.5;
    getSrvDraw().drawString(di, ds, strExtPointsTitle, extPntTitleX, extPntTitleY);
    makeFontBold(di, ds, false);
    int i = 0;
    for(HasNameEditable ep : ge.getExtentionPoints()) {
      double widthEp = getSrvDraw().evalLengthOfString(di, ds, ep.getItsName());
      double x = ge.getPointStart().getX() + (ge.getWidth() - widthEp)/2;
      Double y = extPntTitleY + extPointMargins + widthBoldChar * getSettingsGraphic().getLineSpacingCoefficient() * i++;
      getSrvDraw().drawString(di, ds, ep.getItsName(), x, y);
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
