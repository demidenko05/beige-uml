package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.pojo.Execution;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class SrvGraphicLifeLineFull<M extends LifeLineFull<ShapeUmlWithName>, DRI, SD extends ISettingsDraw> extends ASrvGraphicShapeUml<M, DRI, SD> {

  public SrvGraphicLifeLineFull(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(M ge , DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    double width = getSettingsGraphic().getWidthMinLifeLine();
    double height = width;
    double widthName = 0;
    double widthChar = 0;
    if(ge.getLifeLine().getItsName() != null) {
      widthChar = getSrvDraw().evalLengthOfString(di, ds, "a");
      widthName = getSrvDraw().evalLengthOfString(di, ds, ge.getLifeLine().getItsName());
      width = Math.max(width, widthName + widthChar*2);
    }
    if(width > ge.getLifeLine().getWidth()  ||
        (width < ge.getLifeLine().getWidth() && ge.getLifeLine().getIsAdjustMinimumSize())) {
      ge.getLifeLine().setWidth(width);
    }
    if(height > ge.getLifeLine().getHeight() ||
        (height < ge.getLifeLine().getHeight() && ge.getLifeLine().getIsAdjustMinimumSize())) {
      ge.getLifeLine().setHeight(height);
    }
    getSrvDraw().drawRectangle(di, ds, ge.getLifeLine().getPointStart().getX(), ge.getLifeLine().getPointStart().getY(), ge.getLifeLine().getWidth(), ge.getLifeLine().getHeight(), false);
    if(ge.getLifeLine().getItsName() != null) {
      double x = ge.getLifeLine().getPointStart().getX() + (ge.getLifeLine().getWidth() - widthName)/2;
      double y = ge.getLifeLine().getPointStart().getY() + widthChar*2.7;
      getSrvDraw().drawString(di, ds, ge.getLifeLine().getItsName(), x, y);
    }
    double widthDragRectangle = getSettingsGraphic().getWidthDragRectangle();
    if(ge.getIsSelected()) {
      getSrvDraw().drawRectangle(di, ds,
          ge.getPointStart().getX() + ge.getWidth() - widthDragRectangle/2, 
          ge.getPointStart().getY() + ge.getHeight() - widthDragRectangle/2, widthDragRectangle,
          widthDragRectangle, false);
    }
    double execX1 = ge.getPointStart().getX() + ge.getWidth()/2 - getSettingsGraphic().getWidthExecution()/2;
    if(ge.getExecutions().size() > 0) {
      ds.setPathBackgroundImage("img/bg-dot-gray.png");
      getSrvDraw().preparePaint(di, ds);
    }
    for(Execution exec : ge.getExecutions()) {
      getSrvDraw().drawRectangle(di, ds, execX1, ge.getPointStart().getY() + ge.getHeight() + exec.getStartY(),
          getSettingsGraphic().getWidthExecution(), exec.getEndY() - exec.getStartY(), true);
    }
    if(ge.getExecutions().size() > 0) {
      ds.setPathBackgroundImage(null);
      getSrvDraw().preparePaint(di, ds);
    }
    for(Execution exec : ge.getExecutions()) {
      double yStart = ge.getPointStart().getY() + ge.getHeight() + exec.getStartY();
      double yEnd = ge.getPointStart().getY() + ge.getHeight() + exec.getEndY();
      getSrvDraw().drawRectangle(di, ds, execX1, yStart,
          getSettingsGraphic().getWidthExecution(), exec.getEndY() - exec.getStartY(), false);
      if(ge.getIsSelected()) {
        getSrvDraw().drawRectangle(di, ds,
            ge.getPointStart().getX() + ge.getWidth()/2 - widthDragRectangle/2, 
            yStart - widthDragRectangle/2, widthDragRectangle,
            widthDragRectangle, false);
        getSrvDraw().drawRectangle(di, ds,
            ge.getPointStart().getX() + ge.getWidth()/2 - widthDragRectangle/2, 
            yEnd - widthDragRectangle/2, widthDragRectangle,
            widthDragRectangle, false);
      }
    }
    double x1 = ge.getLifeLine().getWidth()/2 + ge.getLifeLine().getPointStart().getX();
    double y1 = ge.getLifeLine().getHeight() + ge.getLifeLine().getPointStart().getY();
    if(ge.getDestructionOccurenceY() != null) {
      ge.setLineEndY(ge.getDestructionOccurenceY());
      double widthCross = getSettingsGraphic().fromInchToCurrentMeasure(0.10);
      ds.setWidthLine(1.8f);
      getSrvDraw().preparePaint(di, ds);
      getSrvDraw().drawLine(di, ds, x1-widthCross, ge.getLineEndY()-widthCross, 
          x1+widthCross, ge.getLineEndY()+widthCross);
      getSrvDraw().drawLine(di, ds, x1-widthCross, ge.getLineEndY()+widthCross, 
          x1+widthCross, ge.getLineEndY()-widthCross);
      ds.setWidthLine(getSrvDraw().getWidthLineDefault(di));
      getSrvDraw().preparePaint(di, ds);
      if(ge.getIsSelected()) {
        getSrvDraw().drawRectangle(di, ds,
            ge.getPointStart().getX() + ge.getWidth()/2 - widthDragRectangle/2, 
            ge.getLineEndY() - widthDragRectangle/2, widthDragRectangle,
            widthDragRectangle, false);
      }
    }
    else if(ge.getAsmFrameFull() != null) {
      ge.setLineEndY(ge.getAsmFrameFull().getElementUml().getContainer().getPointStart().getY() + ge.getAsmFrameFull().getElementUml().getContainer().getHeight() 
          - getSettingsGraphic().getOffsetLifeLineFromBottom());
    }
    if(ge.getLineEndY() != null) {
      ds.setIsDashed(true);
      getSrvDraw().preparePaint(di, ds);
      getSrvDraw().drawLine(di, ds, x1, y1, x1, ge.getLineEndY());
      ds.setIsDashed(false);
    }
    ds.setPathBackgroundImage(null);
  }
}
