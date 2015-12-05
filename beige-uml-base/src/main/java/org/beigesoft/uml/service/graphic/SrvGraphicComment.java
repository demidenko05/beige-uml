package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.CommentRelationship;
import org.beigesoft.uml.service.UtilsRectangleRelationship;

public class SrvGraphicComment<CM extends CommentUml, DRI, SD extends ISettingsDraw> 
    extends ASrvGraphicShapeUml<CM, DRI, SD> {

  public SrvGraphicComment(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void draw(CM ge, DRI di, SD ds) {
    getSrvDraw().preparePaint(di, ds);
    if(ge.getItsText() != null) {
      double heightTextBox = getSrvDraw().printStringInBox(di,ds, ge.getItsText(),
          " ", ge.getPointStart(), ge.getWidth() - getSettingsGraphic().getLengthCornerBentComment());
      if(heightTextBox > ge.getHeight()) {
        ge.setHeight(heightTextBox);
      }
    }
    if(ge.getHasBorder()) {
      double[] setX = new double[8];
      double[] setY = new double[8];
      setX[0] = ge.getPointStart().getX() + ge.getWidth();
      setY[0] = ge.getPointStart().getY() + getSettingsGraphic().getLengthCornerBentComment();
      setX[1] = ge.getPointStart().getX() + ge.getWidth() - getSettingsGraphic().getLengthCornerBentComment();
      setY[1] = ge.getPointStart().getY() + getSettingsGraphic().getLengthCornerBentComment();
      setX[2] = ge.getPointStart().getX() + ge.getWidth() - getSettingsGraphic().getLengthCornerBentComment();
      setY[2] = ge.getPointStart().getY();
      setX[3] = ge.getPointStart().getX() + ge.getWidth();
      setY[3] = ge.getPointStart().getY() + getSettingsGraphic().getLengthCornerBentComment();
      setX[4] = ge.getPointStart().getX() + ge.getWidth();
      setY[4] = ge.getPointStart().getY() + ge.getHeight();
      setX[5] = ge.getPointStart().getX();
      setY[5] = ge.getPointStart().getY() + ge.getHeight();
      setX[6] = ge.getPointStart().getX();
      setY[6] = ge.getPointStart().getY();
      setX[7] = ge.getPointStart().getX() + ge.getWidth() - getSettingsGraphic().getLengthCornerBentComment();
      setY[7] = ge.getPointStart().getY();
      getSrvDraw().drawPath(di, ds, setX, setY, 8, false, false);
    }
    if(ge.getRelationships().size() > 0) {
      if(ge.getIsDashedRelationships()) {
        ds.setIsDashed(true);
        getSrvDraw().preparePaint(di, ds);
      }
      for(CommentRelationship relation : ge.getRelationships()) {
        getSrvDraw().drawLine(di, ds, relation.getPointJoint().getX(), 
            relation.getPointJoint().getY(), relation.getPointEnd().getX(), relation.getPointEnd().getY());
      }
      if(ds.getIsDashed()) {
        ds.setIsDashed(false);
        getSrvDraw().preparePaint(di, ds);
      }
    }
    if(ge.getIsSelected()) {
      double widthDragRectangle = getSettingsGraphic().getWidthDragRectangle();
          getSrvDraw().drawRectangle(di, ds,
          ge.getPointStart().getX() + ge.getWidth() - widthDragRectangle/2, 
          ge.getPointStart().getY() + ge.getHeight() - widthDragRectangle/2, widthDragRectangle,
          widthDragRectangle, false);
      for(CommentRelationship relation : ge.getRelationships()) {
        Point2D pointEnd = relation.getPointEnd();
        getSrvDraw().drawRectangle(di, ds,
            pointEnd.getX() - widthDragRectangle/2, pointEnd.getY() - widthDragRectangle/2, widthDragRectangle,
            widthDragRectangle, false);
        getSrvDraw().drawRectangle(di, ds,
            relation.getPointJoint().getX() - widthDragRectangle/2, relation.getPointJoint().getY() - widthDragRectangle/2, widthDragRectangle,
            widthDragRectangle, false);
      }
    }
  }

  @Override
  public void recalculate(CM ge, double coefficient) {
    super.recalculate(ge, coefficient);
    for(CommentRelationship relation : ge.getRelationships()) {
      UtilsRectangleRelationship.recalculate(relation, coefficient);
      relation.getPointEnd().setX(relation.getPointEnd().getX() * coefficient);
      relation.getPointEnd().setY(relation.getPointEnd().getY() * coefficient);
    }
  }

  @Override
  public Point2D evalMinimumScreenPoint(CM ge) {
    Point2D result = super.evalMinimumScreenPoint(ge);
    for(CommentRelationship relation : ge.getRelationships()) {
      result.setX(Math.min(result.getX(), relation.getPointEnd().getX()));
      result.setY(Math.min(result.getY(), relation.getPointEnd().getY()));
    }
    return result;
  }

  @Override
  public Point2D evalMaximumScreenPoint(CM ge) {
    Point2D result = super.evalMaximumScreenPoint(ge);
    for(CommentRelationship relation : ge.getRelationships()) {
      result.setX(Math.max(result.getX(), relation.getPointEnd().getX()));
      result.setY(Math.max(result.getY(), relation.getPointEnd().getY()));
    }
    return result;
  }

  @Override
  public boolean isContainsScreenPoint(CM ge, int x, int y) {
    if(super.isContainsScreenPoint(ge, x, y)) {
      return true;
    }
    double realX = UtilsGraphMath.toRealX(getSettingsGraphic(), x);
    double realY = UtilsGraphMath.toRealY(getSettingsGraphic(), y);
    for(CommentRelationship relation : ge.getRelationships()) {
       if(UtilsGraphMath.isLineContainsPoint(getSettingsGraphic(), relation.getPointJoint().getX(), 
           relation.getPointJoint().getY(), relation.getPointEnd().getX(), relation.getPointEnd().getY(), realX, realY)) {
        return true;
      }
    }
    return false;
  }
}
