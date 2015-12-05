package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.ColorRgb;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.pojo.SettingsFont;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.service.ISrvGraphicElement;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.model.EFrameRoleForMessage;
import org.beigesoft.uml.model.EMessageKind;
import org.beigesoft.uml.pojo.Execution;

public class SrvGraphicMessageFull<M extends MessageFull, DRI, SD extends ISettingsDraw> implements ISrvGraphicElement<M, DRI, SD> {

  private final ISrvDraw<DRI, SD, ?> srvDraw;

  private final SettingsGraphicUml sg;
  
  private SettingsFont settingsFont = new SettingsFont();

  public SrvGraphicMessageFull(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    this.srvDraw = srvDraw;
    this.sg = sg;
  }

  @Override
  public void draw(M ge , DRI di, SD ds) {
    if(ge.getItsName() != null || ge.getAsmInteractionUseEnd() != null  || ge.getAsmInteractionUseStart() != null
        || ge.getAsmLifeLineFullStart() != null || ge.getAsmLifeLineFullEnd() != null) {
      srvDraw.preparePaint(di, ds);
      ge.setStartX(null);
      if(ge.getAsmLifeLineFullStart() != null) {
        double offsetX = ge.getAsmLifeLineFullStart().getElementUml().getWidth()/2;
        for(Execution exec : ge.getAsmLifeLineFullStart().getElementUml().getExecutions()) {
          if(ge.getY() >= exec.getStartY() + ge.getAsmLifeLineFullStart().getElementUml().getHeight() +
              ge.getAsmLifeLineFullStart().getElementUml().getPointStart().getY() &&
              ge.getY() <= exec.getEndY() + ge.getAsmLifeLineFullStart().getElementUml().getHeight() +
              ge.getAsmLifeLineFullStart().getElementUml().getPointStart().getY()) {
            offsetX = ge.getAsmLifeLineFullStart().getElementUml().getWidth()/2 + sg.getWidthExecution()/2;
            break;
          }
        }
        if(ge.getY() >= ge.getAsmLifeLineFullStart().getElementUml().getPointStart().getY() &&
            ge.getY() <= ge.getAsmLifeLineFullStart().getElementUml().getPointStart().getY() + ge.getAsmLifeLineFullStart().getElementUml().getHeight()) {
          offsetX = ge.getAsmLifeLineFullStart().getElementUml().getWidth();
        }
        ge.setStartX(ge.getAsmLifeLineFullStart().getElementUml().getPointStart().getX()  + offsetX);
      }
      else if(ge.getAsmInteractionUseStart() != null) {
        double startX = ge.getIsLeftSideOfInteractionUseStart() ? ge.getAsmInteractionUseStart().getElementUml().getPointStart().getX()
            : ge.getAsmInteractionUseStart().getElementUml().getPointStart().getX() +
            ge.getAsmInteractionUseStart().getElementUml().getWidth();
        ge.setStartX(startX);
      }
      else if(ge.getFrameRole() == EFrameRoleForMessage.IS_START) {
        double startX = ge.getIsRightSideOfFrame() ? ge.getItsFrame().getElementUml().getContainer().getPointStart().getX() +
            ge.getItsFrame().getElementUml().getContainer().getWidth()
            : ge.getItsFrame().getElementUml().getContainer().getPointStart().getX();
        ge.setStartX(startX);
      }
      ge.setEndX(null);
      if(ge.getAsmLifeLineFullEnd() != null) {
        double offsetX = ge.getAsmLifeLineFullEnd().getElementUml().getWidth()/2;
        for(Execution exec : ge.getAsmLifeLineFullEnd().getElementUml().getExecutions()) {
          if(ge.getY() >= exec.getStartY() + ge.getAsmLifeLineFullEnd().getElementUml().getHeight() +
              ge.getAsmLifeLineFullEnd().getElementUml().getPointStart().getY() &&
              ge.getY() <= exec.getEndY() + ge.getAsmLifeLineFullEnd().getElementUml().getHeight() +
              ge.getAsmLifeLineFullEnd().getElementUml().getPointStart().getY()) {
            offsetX = ge.getAsmLifeLineFullEnd().getElementUml().getWidth()/2 - sg.getWidthExecution()/2;
            break;
          }
        }
        if(ge.getY() >= ge.getAsmLifeLineFullEnd().getElementUml().getPointStart().getY() &&
            ge.getY() <= ge.getAsmLifeLineFullEnd().getElementUml().getPointStart().getY() + ge.getAsmLifeLineFullEnd().getElementUml().getHeight()) {
          offsetX = 0;
        }
        ge.setEndX(ge.getAsmLifeLineFullEnd().getElementUml().getPointStart().getX() + 
            + offsetX);
      }
      else if(ge.getAsmInteractionUseEnd() != null) {        
        double endX = ge.getIsRightSideOfInteractionUseEnd() ? ge.getAsmInteractionUseEnd().getElementUml().getPointStart().getX() +
            ge.getAsmInteractionUseEnd().getElementUml().getWidth()
            : ge.getAsmInteractionUseEnd().getElementUml().getPointStart().getX();
        ge.setEndX(endX);
      }
      else if(ge.getFrameRole() == EFrameRoleForMessage.IS_END) {
        double endX = ge.getIsRightSideOfFrame() ? ge.getItsFrame().getElementUml().getContainer().getPointStart().getX() +
            ge.getItsFrame().getElementUml().getContainer().getWidth()
            : ge.getItsFrame().getElementUml().getContainer().getPointStart().getX();
        ge.setEndX(endX);
      }
      if(ge.getEndX() == null && ge.getStartX() != null) {
        ge.setEndX(ge.getStartX() + 1);
      }
      if(ge.getStartX() == null && ge.getEndX() != null) {
        ge.setStartX(ge.getEndX() - 1);
      }
      if(ge.getStartX() != null && ge.getEndX() != null) {
        double[][] pathData = null;
        if(ge.getIsReversed()) {
          pathData = UtilsGraphMath.triangleEndForLineVectorAlgebra(ge.getEndX(), ge.getY(), ge.getStartX(), ge.getY(), 
              sg.getWidthEndRelation(), sg.getWidthEndRelation());
        }
        else {
          pathData = UtilsGraphMath.triangleEndForLineVectorAlgebra(ge.getStartX(), ge.getY(), ge.getEndX(), ge.getY(), 
              sg.getWidthEndRelation(), sg.getWidthEndRelation());
        }
        boolean isClosed = false;
        boolean isFill = false;
        if(ge.getItsKind() == EMessageKind.CALL) {
          isClosed = isFill = true;
        }
        srvDraw.drawPath(di, ds,
            pathData[1], pathData[2], 3, isClosed, isFill);
        if(ge.getItsKind() == EMessageKind.REPLY) {
          ds.setIsDashed(true);
          srvDraw.preparePaint(di, ds);
        }
        srvDraw.drawLine(di, ds, ge.getStartX(), ge.getY(), ge.getEndX(), ge.getY());        
        if(ge.getItsKind() == EMessageKind.REPLY) {
          ds.setIsDashed(false);
        }
      }
      if(ge.getItsName() != null) {
        ge.setWidthName(srvDraw.evalLengthOfString(di, ds, ge.getItsName()));
        ge.setNameX(1d);
        if(ge.getStartX() != null && ge.getEndX() != null) {
          ge.setNameX(ge.getStartX() + ((ge.getEndX() - ge.getStartX()) - ge.getWidthName())/2);
        }
        else if(ge.getStartX() != null) {
          ge.setNameX(ge.getStartX());
        }
        else if(ge.getEndX() != null) {
          ge.setNameX(ge.getEndX());
        }
        ColorRgb colorWas = ds.getColor();
        ds.setColor(ColorRgb.WHITE);
        srvDraw.preparePaint(di, ds);
        double heightText = srvDraw.evalLengthOfString(di, ds, "a") * 2;
        srvDraw.drawRectangle(di, ds, ge.getNameX(), ge.getY() - ge.getWidthName()/ge.getItsName().length() - heightText/1.3, 
            ge.getWidthName(), heightText, true);
        ds.setColor(colorWas);
        srvDraw.preparePaint(di, ds);
        srvDraw.drawString(di, ds, ge.getItsName(), ge.getNameX(), ge.getY() - ge.getWidthName()/ge.getItsName().length());
      }
    }
  }

  @Override
  public void recalculate(M ge, double coefficient) {
    ge.setY(ge.getY() * coefficient);
  }

  @Override
  public Point2D evalMinimumScreenPoint(M ge) {//don't care
    return new Point2D(1, ge.getY());
  }

  @Override
  public Point2D evalMaximumScreenPoint(M ge) {//don't care
    return new Point2D(1, ge.getY());
  }

  @Override
  public boolean isContainsScreenPoint(M ge, int x, int y) {
    double realX = UtilsGraphMath.toRealX(sg, x);
    double realY = UtilsGraphMath.toRealY(sg, y);
    if(ge.getItsName() != null) {
      if(realX >= ge.getNameX() && realX <= ge.getNameX() + ge.getWidthName() &&
          realY >= ge.getY() - (ge.getWidthName()/ge.getItsName().length())*2 &&
          realY <= ge.getY()) {
        return true;
      }
    }
    if(ge.getEndX() != null && ge.getStartX() != null) {
      if(realX >= ge.getStartX() - sg.getSelectApproximation() && realX <= ge.getEndX() + sg.getSelectApproximation() &&
          realY >= ge.getY() - sg.getSelectApproximation() &&
          realY <= ge.getY() + sg.getSelectApproximation()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public SettingsGraphicUml getSettingsGraphic() {
    return sg;
  }

  //SGS:
  public SettingsFont getSettingsFont() {
    return settingsFont;
  }

  public void setSettingsFont(SettingsFont settingsFont) {
    this.settingsFont = settingsFont;
  }

  public ISrvDraw<DRI, SD, ?> getSrvDraw() {
    return srvDraw;
  }
}
