package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.service.ISrvGraphicElement;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.model.IRelationship;
import org.beigesoft.uml.model.IShapeRelationship;

public abstract class ASrvGraphicRelationship<RE extends IRelationship, DRI, SD extends ISettingsDraw> 
    implements ISrvGraphicElement<RE, DRI, SD> {

  private final ISrvDraw<DRI, SD, ?> srvDraw;

  private final SettingsGraphicUml sg;
  
  public ASrvGraphicRelationship(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    this.srvDraw = srvDraw;
    this.sg = sg;
  }


  @Override
  public void recalculate(RE ge, double coefficient) {
    if(ge.getSharedJoint() != null) {
      ge.getSharedJoint().setX(ge.getSharedJoint().getX() * coefficient);
      ge.getSharedJoint().setY(ge.getSharedJoint().getY() * coefficient);
    }
  }

  @Override
  public SettingsGraphicUml getSettingsGraphic() {
    return sg;
  }
    
  protected void drawRelationshipEnd(DRI di, SD ds, RE ge, Point2D pointStart,
      Point2D pointEndChangeable, IShapeRelationship shapeRelationEnd) {
    double[][] ownedEndData = null;
    if(shapeRelationEnd.getIsOwned()) {
      ownedEndData = UtilsGraphMath.circleEndForLineVectorAlgebra(pointStart.getX(),
          pointStart.getY(), pointEndChangeable.getX(), pointEndChangeable.getY(), 
          sg.getOwnedRadius());
      pointEndChangeable.setX(ownedEndData[0][0]);
      pointEndChangeable.setY(ownedEndData[0][1]);
      //draw owned:
      srvDraw.drawCircle(di, ds,
          ownedEndData[1][0], ownedEndData[1][1], sg.getOwnedRadius(), true);
    }
    if(shapeRelationEnd.getEndType() == ERelationshipEndType.END
        || shapeRelationEnd.getEndType() == ERelationshipEndType.ANOTHER_END) {
      double[][] pathData = null;
      int idxArrayX;
      int totalPoints;
      boolean isClose = true;
      boolean isFill = false;
      if(ge.getItsKind() == ERelationshipKind.GENERALIZATION || ge.getItsKind() == ERelationshipKind.INTERFACE_REALIZATION
        || ge.getItsKind() == ERelationshipKind.REALIZATION) {//triangle
        double heightEnd = ge.getItsKind() == ERelationshipKind.REALIZATION ? sg.getWidthEndRelation()*0.75 : 
          sg.getWidthEndRelation();
        pathData = UtilsGraphMath.triangleEndForLineVectorAlgebra(pointStart.getX(),
            pointStart.getY(), pointEndChangeable.getX(), pointEndChangeable.getY(), 
            sg.getWidthEndRelation(), heightEnd);
        isFill = false;
        idxArrayX = 1;
        totalPoints = 3;
        pointEndChangeable.setX(pathData[0][0]);
        pointEndChangeable.setY(pathData[0][1]);
      }
      else if(shapeRelationEnd.getEndType() != ERelationshipEndType.ANOTHER_END && (ge.getItsKind() == ERelationshipKind.AGGREGATION || 
          ge.getItsKind() == ERelationshipKind.COMPOSITION)) {//diamond
        pathData = UtilsGraphMath.diamondEndForLineVectorAlgebra(pointStart.getX(),
            pointStart.getY(), pointEndChangeable.getX(), pointEndChangeable.getY(), 
            sg.getWidthEndRelation()*2, sg.getWidthEndRelation());
        isFill = ge.getItsKind() == ERelationshipKind.COMPOSITION;
        idxArrayX = 0;
        totalPoints = 4;
        pointEndChangeable.setX(pathData[0][0]);
        pointEndChangeable.setY(pathData[1][0]);
      }
      else {//arrow
        pathData = UtilsGraphMath.arrowEndForLineVectorAlgebra(pointStart.getX(), pointStart.getY(), 
            pointEndChangeable.getX(), pointEndChangeable.getY(), 
            sg.getWidthEndRelation(), sg.getWidthEndRelation());
        totalPoints = 3;
        isClose = false;
        idxArrayX = 0;
       }
      //draw end shape:
      srvDraw.drawPath(di, ds,
          pathData[idxArrayX], pathData[idxArrayX+1], totalPoints, isClose, isFill);
    }
    else if(shapeRelationEnd.getEndType() == ERelationshipEndType.NON_NAVIGABLE) {
      double[][] pointsCross = UtilsGraphMath.crossEndForLineVectorAlgebra(pointStart.getX(), pointStart.getY(), 
          pointEndChangeable.getX(), pointEndChangeable.getY(), 
          sg.getWidthEndRelation(), sg.getWidthEndRelation());
      srvDraw.drawLine(di, ds, pointsCross[0][0], 
          pointsCross[1][0], pointsCross[0][3], pointsCross[1][3]);
      srvDraw.drawLine(di, ds, pointsCross[0][1], 
          pointsCross[1][1], pointsCross[0][2], pointsCross[1][2]);
    }
  }
  
  @Override
  public Point2D evalMinimumScreenPoint(RE re) {
    Point2D result = new Point2D(99999, 999999);
    if(re.getSharedJoint() != null) {
      result.setX(UtilsGraphMath.toScreenX(getSettingsGraphic(), re.getSharedJoint().getX()));
      result.setY( UtilsGraphMath.toScreenY(getSettingsGraphic(), re.getSharedJoint().getY()));
    }
    return result;
  }

  @Override
  public Point2D evalMaximumScreenPoint(RE re) {
    Point2D result = new Point2D(-99999, -999999);
    if(re.getSharedJoint() != null) {
      result.setX(UtilsGraphMath.toScreenX(getSettingsGraphic(), re.getSharedJoint().getX()));
      result.setY( UtilsGraphMath.toScreenY(getSettingsGraphic(), re.getSharedJoint().getY()));
    }
    return result;
  }

  //SGS:
  public ISrvDraw<DRI, SD, ?> getSrvDraw() {
    return srvDraw;
  }
}
