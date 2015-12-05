package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.Actor;

public class SrvGraphicActor<AC extends Actor, DRI, SD extends ISettingsDraw, IMG> 
    extends ASrvGraphicShapeUml<AC, DRI, SD> {

  public SrvGraphicActor(ISrvDraw<DRI, SD, IMG> srvDraw,
      SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(AC ge, DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    double width = getSettingsGraphic().getWidthMinActor();
    double margin = getSettingsGraphic().fromInchToCurrentMeasure(0.02);
    if(ge.getPathImage() == null) {
      double radius = width/3;
      double centerX = ge.getPointStart().getX() + width/2;
      double centerY = ge.getPointStart().getY() + radius + margin;
      getSrvDraw().drawCircle(di, ds, centerX, centerY, radius, false); // head
      double lengthLeg = radius*2;
      double lengthTrunk = radius*3;
      getSrvDraw().drawLine(di, ds, centerX, centerY + radius, centerX, centerY + lengthTrunk); // trunk
      getSrvDraw().drawLine(di, ds, ge.getPointStart().getX(), centerY + radius*1.5, 
          ge.getPointStart().getX() + width, centerY + radius*1.5); // hands
      getSrvDraw().drawLine(di, ds, centerX, centerY + lengthTrunk, ge.getPointStart().getX(),
          centerY + lengthTrunk + lengthLeg); // left leg
      getSrvDraw().drawLine(di, ds, centerX, centerY + lengthTrunk, ge.getPointStart().getX() + width,
          centerY + lengthTrunk + lengthLeg); // right leg
      double height = margin + radius*2 + lengthTrunk + lengthLeg;
      if(ge.getItsName() != null) {
        makeFontBold(di, ds, true);
         double[] widthHeightTextBox = getSrvDraw().evalWidthHeightMultistring(di, ds, ge.getItsName(), "\n", 
            getSettingsGraphic().getLineSpacingCoefficient(), margin);
        Point2D pointStart = new Point2D(ge.getPointStart().getX() + (width - widthHeightTextBox[0])/2, 
            ge.getPointStart().getY() + height + margin);
        getSrvDraw().printMultistring(di, ds, ge.getItsName(), "\n", pointStart,
            getSettingsGraphic().getLineSpacingCoefficient(), getSettingsGraphic().getMarginElement(), true);
        makeFontBold(di, ds, false);
        height += widthHeightTextBox[1];
      }
      ge.setWidth(width);
      ge.setHeight(height);
    }
    else {
      //TODO image
    }
  }
}
