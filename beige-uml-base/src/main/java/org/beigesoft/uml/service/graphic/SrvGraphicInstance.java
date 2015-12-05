package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.model.InstanceUml;

public class SrvGraphicInstance<M extends InstanceUml, DRI, SD extends ISettingsDraw> 
    extends ASrvGraphicShapeUml<M, DRI, SD> {

  public SrvGraphicInstance(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(M ge, DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    double widthNameFull = 0;
    double widthChar = 0;
    String nameFull = null;
    if(ge.getItsName() != null) {
      nameFull = ge.getItsName();
      if(ge.getNameClass() != null) {
        nameFull += ": " + ge.getNameClass();
      }
      widthNameFull = getSrvDraw().evalLengthOfString(di, ds, nameFull);
      widthChar = getSrvDraw().evalLengthOfString(di, ds, "a");
    }
    double widthStructure = 0;
    double heightStructure = 0;
    if(ge.getStructure() != null && ge.getStructure().size() > 0) {
      for(HasNameEditable member : ge.getStructure()) {
        widthStructure = Math.max(widthStructure, getSrvDraw().evalLengthOfString(di, ds, member.getItsName()));
      }
      heightStructure = ge.getStructure().size() * widthChar * getSettingsGraphic().getLineSpacingCoefficient()
          + widthChar * 2;
    }
    double width = Math.max(getSettingsGraphic().getWidthMinClass(), widthNameFull + widthChar*2);
    width = Math.max(widthStructure, width);
    if(width > ge.getWidth() || 
        (ge.getIsAdjustMinimumSize() && width < ge.getWidth())) {
      ge.setWidth(width);
    }
    double heightHead = heightStructure == 0 ? getSettingsGraphic().getHeightHeadClass() :
      getSettingsGraphic().getHeightHeadClass()*0.6;
    double height = heightStructure == 0 ? getSettingsGraphic().getHeightHeadClass() :
      getSettingsGraphic().getHeightHeadClass()*0.6 + heightStructure;
    if(height > ge.getHeight() || (ge.getIsAdjustMinimumSize() && height < ge.getHeight())) {
      ge.setHeight(height);
    }
    if(ge.getItsName() != null) {
      double offsetX = (ge.getWidth() - widthNameFull)/2;
      double nameX = ge.getPointStart().getX() + offsetX;
      double offsetY = heightHead/2;
      double nameY = ge.getPointStart().getY() + offsetY;
      getSettingsFont().setIsUnderlining(true);
      getSrvDraw().prepareFont(di, ds, getSettingsFont());
      getSrvDraw().drawString(di, ds, nameFull, nameX, nameY);
      getSettingsFont().setIsUnderlining(false);
      getSrvDraw().prepareFont(di, ds, getSettingsFont());
      if(ge.getStructure() == null || ge.getStructure().size() == 0) {
        if(ge.getValue() != null) {
          double widthValue = getSrvDraw().evalLengthOfString(di, ds, ge.getValue());
          offsetX = (ge.getWidth() - widthValue)/2;
          double valueX = ge.getPointStart().getX() + offsetX;
          offsetY = heightHead/2 + widthChar*3;
          double valueY = ge.getPointStart().getY() + offsetY;
          getSrvDraw().drawString(di, ds, ge.getValue(), valueX, valueY);
        }
      }
    }
    if(ge.getStructure() != null && ge.getStructure().size() > 0) {
      double offsetX = (ge.getWidth() - widthStructure)/2;
      double valueX = ge.getPointStart().getX() + offsetX;
      double offsetY = heightHead + widthChar*2;
      double valueY = ge.getPointStart().getY() + offsetY;
      for(HasNameEditable member : ge.getStructure()) {
        getSrvDraw().drawString(di, ds, member.getItsName(), valueX, valueY);
        valueY += widthChar*getSettingsGraphic().getLineSpacingCoefficient();
      }
    }
    if(ge.getStructure() != null && ge.getStructure().size() > 0) {
      getSrvDraw().drawLine(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY() + heightHead, 
          ge.getPointStart().getX() + ge.getWidth(), ge.getPointStart().getY() + heightHead);
    }
    getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
        ge.getWidth(), ge.getHeight(), false);
    if(ge.getIsSelected()) {
      double widthDragRectangle = getSettingsGraphic().getWidthDragRectangle();
          getSrvDraw().drawRectangle(di, ds,
          ge.getPointStart().getX() + ge.getWidth() - widthDragRectangle/2, 
          ge.getPointStart().getY() + ge.getHeight() - widthDragRectangle/2, widthDragRectangle,
          widthDragRectangle, false);
    }
  }
}