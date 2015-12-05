package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.MethodClass;

public class SrvGraphicClass<CL extends ClassUml, DRI, SD extends ISettingsDraw> extends ASrvGraphicShapeUml<CL, DRI, SD> {
  
  public SrvGraphicClass(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(CL ge, DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    evalHeightClass(di, ds, ge);
    evalWidthClass(di, ds, ge);
    double heightHeadClass = getHeightAttributesBox(ge) == 0 ? ge.getHeight() :
        getSettingsGraphic().getHeightHeadClass();
    getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY(), 
        ge.getWidth(), heightHeadClass, false);
    if(ge.getItsName() != null) {
      double nameY = ge.getPointStart().getY() + heightHeadClass/1.5;
      if(ge.getClassKind() == EClassKind.ENUM || ge.getClassKind() == EClassKind.INTERFACE) {
        double kindY = ge.getPointStart().getY() + heightHeadClass/2.5;
        double widthKind = getSrvDraw().evalLengthOfString(di, ds, ge.getClassKind().toString());
        double kindX = ge.getPointStart().getX() + (ge.getWidth()-widthKind)/2;
        getSrvDraw().drawString(di, ds, ge.getClassKind().toString(), kindX, kindY);
      }
      makeFontBold(di, ds, true);
      double widthName = getSrvDraw().evalLengthOfString(di, ds, ge.getItsName());
      double nameX = ge.getPointStart().getX() + (ge.getWidth()-widthName)/2;
      getSrvDraw().drawString(di, ds, ge.getItsName(), nameX, nameY);
      makeFontBold(di, ds, false);
    }
    double attrX = ge.getPointStart().getX() + getSrvDraw().evalLengthOfString(di, ds, " ");
    if(ge.getAttributes().size() > 0) {
      double attrsHeight = getHeightAttributesBox(ge);
      getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY() + 
          getSettingsGraphic().getHeightHeadClass(), ge.getWidth(), attrsHeight, false);
      for (int i= 0; i < ge.getAttributes().size(); i++) {
        double attrY = ge.getPointStart().getY() + getSettingsGraphic().getHeightHeadClass()
            + getSettingsGraphic().getMarginElement() + 
            getSettingsGraphic().getHeightAttributeClass() * i + getSettingsGraphic().getHeightAttributeClass()/1.5;
        getSrvDraw().drawString(di, ds, ge.getAttributes().get(i).toString() , attrX, attrY);
      }
    }
    if(ge.getMethods().size() > 0) {
      double attrsHeight = getHeightAttributesBox(ge);
      if(ge.getAttributes().size() == 0) {
        getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), ge.getPointStart().getY() + 
            getSettingsGraphic().getHeightHeadClass(), ge.getWidth(), attrsHeight, false);
      }
      double operHeight = getHeightOperationsBox(ge);
      getSrvDraw().drawRectangle(di, ds, ge.getPointStart().getX(), 
          ge.getPointStart().getY() + getSettingsGraphic().getHeightHeadClass() + attrsHeight, ge.getWidth(), operHeight, false);
      for (int i= 0; i < ge.getMethods().size(); i++) {
        double attrY = ge.getPointStart().getY() + getSettingsGraphic().getHeightHeadClass() + attrsHeight
            + getSettingsGraphic().getMarginElement() + 
            getSettingsGraphic().getHeightAttributeClass() * i + getSettingsGraphic().getHeightAttributeClass()/1.5;
        getSrvDraw().drawString(di, ds, ge.getMethods().get(i).toString() , attrX, attrY);
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

  //Utils:
  public void evalHeightClass(DRI di, SD ds, CL ge) {
    double heightAttrOper = getHeightAttributesBox(ge) + getHeightOperationsBox(ge);
    double height = getSettingsGraphic().getHeightHeadClass()
        + heightAttrOper;
    if(height > ge.getHeight() || 
        (ge.getIsAdjustMinimumSize() && height < ge.getHeight())
        || heightAttrOper >0 && height != ge.getHeight()) {
      ge.setHeight(height);
    }
  }

  public void evalWidthClass(DRI di, SD ds, ClassUml ge) {
    double width = getSettingsGraphic().getWidthMinClass();
    if(ge.getItsName() != null) {
      makeFontBold(di, ds, true);
      width = Math.max(width, getSrvDraw().evalLengthOfString(di, ds, ge.getItsName()+"  "));
      makeFontBold(di, ds, false);
    }
    if(ge.getClassKind() == EClassKind.ENUM || ge.getClassKind() == EClassKind.INTERFACE) {
      width = Math.max(width, getSrvDraw().evalLengthOfString(di, ds, ge.getClassKind().toString()));
    }
    if(ge.getClassKind() == EClassKind.INTERFACE) {
      width = Math.max(width, getSrvDraw().evalLengthOfString(di, ds, EClassKind.INTERFACE.toString()+"  "));
    }
    if(ge.getClassKind() == EClassKind.ENUM) {
      width = Math.max(width, getSrvDraw().evalLengthOfString(di, ds, EClassKind.ENUM.toString()+"  "));
    }
    for(AttributeClass attribute : ge.getAttributes()) {
      width = Math.max(width, getSrvDraw().evalLengthOfString(di, ds, attribute.toString() + "  "));
    }
    for(MethodClass operation : ge.getMethods()) {
      width = Math.max(width, getSrvDraw().evalLengthOfString(di, ds, operation.toString() + "  "));
    }
    if(width > ge.getWidth() || 
        (ge.getIsAdjustMinimumSize() && width < ge.getWidth())) {
      ge.setWidth(width);
    }
  }
  
  public double getHeightAttributesBox(CL ge) {
    if(ge.getAttributes().size() > 0) {
      return ge.getAttributes().size() * getSettingsGraphic().getHeightAttributeClass() 
          + getSettingsGraphic().getMarginElement();
    }
    else {
      return ge.getMethods().size() == 0 ? 0 : getSettingsGraphic().getHeightEmptyAttributes();
    }
  }
  
  public double getHeightOperationsBox(CL ge) {
    return ge.getMethods().size() == 0 ? 0 : ge.getMethods().size() * getSettingsGraphic().getHeightAttributeClass() 
        + getSettingsGraphic().getMarginElement();
  }
}
