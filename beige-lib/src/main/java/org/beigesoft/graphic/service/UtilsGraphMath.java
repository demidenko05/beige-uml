/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.graphic.service;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.graphic.pojo.Joint2D;
import org.beigesoft.graphic.pojo.Point2D;

/**
 * <p>Some graphics methods</p>
 * @author Yury Demidenko
 */
public class UtilsGraphMath {//TODO redone as nonstatic
  
  /**
   * Calculate triangle end for given line with vector algebra:<br>
   * ---------------><br>
   * Triangle points:<br>
   * &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;D<br>
   * A---------------C>B<br>
   * &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;E<br>
   * 
   * Usage code:
   * <pre>
   * </pre>
   * @param x1 line start A X
   * @param y1 line start A Y
   * @param x2 line end B X
   * @param y2 line end B Y
   * @param width triangle
   * @param height of triangle
   * @return arrays of calculated triangle points:
   * <ul>
   * <li>line-triangle joint point [C x, C y]</li>
   * <li>
   * triangle's points:  
   * [D x, B x, E x]
   * [D y, B y, E y]</li>
   * </ul>
   */
  public static double[][] triangleEndForLineVectorAlgebra(double x1, double y1, 
      double x2, double y2, double width, double height) {
    double[][] result = new double[3][];
    result[0] = new double[2];
    result[1] = new double[3];
    result[2] = new double[3];
    double [] baseVector = {x2-x1, y2-y1};
    double baseVectorLenght = Math.sqrt(baseVector[0]*baseVector[0] + baseVector[1]*baseVector[1]);
    double [] baseUnitVector = {baseVector[0]/baseVectorLenght, baseVector[1]/baseVectorLenght};
    //point C:
    result[0][0] = x2 - baseUnitVector[0] * width;
    result[0][1] = y2 - baseUnitVector[1] * width;
    double [] normal = {-baseUnitVector[1], baseUnitVector[0]};
    //Point D:
    result[1][0] = result[0][0] + normal[0] * height/2;
    result[2][0] = result[0][1] + normal[1] * height/2;
    //Point B:
    result[1][1] = x2;
    result[2][1] = y2;
    //Point E:
    result[1][2] = result[0][0] - normal[0] * height/2;
    result[2][2] = result[0][1] - normal[1] * height/2;
    return result;
  }
  
  /**
   * Calculate circle end (center point) for given line (AB) with vector algebra:<br>
   * ---------------><br>
   * circle points:<br>
   * A---------------COB<br>
   * 
   * Usage code:
   * <pre>
   * </pre>
   * @param x1 line start A X
   * @param y1 line start A Y
   * @param x2 line end B X
   * @param y2 line end B Y
   * @param radius of circle
   * @return arrays of calculated circle points:
   * <ul>
   * <li>line-circle joint point [C x, C y]</li>
   * <li>
   * circle center point:  
   * [O x, O y]</li>
   * </ul>
   */
  public static double[][] circleEndForLineVectorAlgebra(double x1, double y1, 
      double x2, double y2, double radius) {
    double[][] result = new double[2][];
    result[0] = new double[2];
    result[1] = new double[2];
    double [] baseVector = {x2-x1, y2-y1};
    double baseVectorLenght = Math.sqrt(baseVector[0]*baseVector[0] + baseVector[1]*baseVector[1]);
    double [] baseUnitVector = {baseVector[0]/baseVectorLenght, baseVector[1]/baseVectorLenght};
    //point C:
    result[0][0] = x2 - baseUnitVector[0] * radius * 2;
    result[0][1] = y2 - baseUnitVector[1] * radius * 2;
    //Point O:
    result[1][0] = x2 - baseUnitVector[0] * radius;
    result[1][1] = y2 - baseUnitVector[1] * radius;
    return result;
  }
  
  /**
   * Calculate arrow end for given line with vector algebra:<br>
   * ---------------><br>
   * Arrow points:<br>
   * &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;D<br>
   * A--------------->B<br>
   * &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;E<br>
   * 
   * Usage code:
   * <pre>
   * </pre>
   * @param x1 line start A X
   * @param y1 line start A Y
   * @param x2 line end B X
   * @param y2 line end B Y
   * @param width arrow
   * @param height of arrow
   * @return arrays of calculated arrow points:
   * triangle's points:  
   * [D x, B x, E x]
   * [D y, B y, E y]</li>
   * </ul>
   */
  public static double[][] arrowEndForLineVectorAlgebra(double x1, double y1, 
      double x2, double y2, double width, double height) {
    double[][] result = new double[2][3];
    double [] baseVector = {x2-x1, y2-y1};
    double baseVectorLenght = Math.sqrt(baseVector[0]*baseVector[0] + baseVector[1]*baseVector[1]);
    double [] baseUnitVector = {baseVector[0]/baseVectorLenght, baseVector[1]/baseVectorLenght};
    double [] normal = {-baseUnitVector[1], baseUnitVector[0]};
    //Point D:
    result[0][0] = x2 - baseUnitVector[0] * width + normal[0] * height/2;
    result[1][0] = y2 - baseUnitVector[1] * width + normal[1] * height/2;
    //Point B:
    result[0][1] = x2;
    result[1][1] = y2;
    //Point E:
    result[0][2] = x2 - baseUnitVector[0] * width - normal[0] * height/2;
    result[1][2] = y2 - baseUnitVector[1] * width - normal[1] * height/2;
    return result;
  }
  
  /**
   * Calculate cross end for given line with vector algebra:<br>
   * ---------------x--<br>
   * Cross points:<br>
   * &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;C&emsp;D<br>
   * A---------------x--B<br>
   * &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;E&emsp;F<br>
   * 
   * Usage code:
   * <pre>
   * </pre>
   * @param x1 line start A X
   * @param y1 line start A Y
   * @param x2 line end B X
   * @param y2 line end B Y
   * @param margin cross from B
   * @param width of cross
   * @return arrays of calculated cross points:
   * cross's points:  
   * [C x, D x, E x, F x]
   * [C y, D y, E y, F y]</li>
   * </ul>
   */
  public static double[][] crossEndForLineVectorAlgebra(double x1, double y1, 
      double x2, double y2, double margin, double width) {
    double[][] result = new double[2][4];
    double [] baseVector = {x2-x1, y2-y1};
    double baseVectorLenght = Math.sqrt(baseVector[0]*baseVector[0] + baseVector[1]*baseVector[1]);
    double [] baseUnitVector = {baseVector[0]/baseVectorLenght, baseVector[1]/baseVectorLenght};
    double [] normal = {-baseUnitVector[1], baseUnitVector[0]};
    //Point C:
    result[0][0] = x2 - baseUnitVector[0] * (margin+width/2) - normal[0] * width/2;
    result[1][0] = y2 - baseUnitVector[1] * (margin+width/2) - normal[1] * width/2;
    //Point D:
    result[0][1] = x2 - baseUnitVector[0] * (margin-width/2) - normal[0] * width/2;
    result[1][1] = y2 - baseUnitVector[1] * (margin-width/2) - normal[1] * width/2;
    //Point E:
    result[0][2] = x2 - baseUnitVector[0] * (margin+width/2) + normal[0] * width/2;
    result[1][2] = y2 - baseUnitVector[1] * (margin+width/2) + normal[1] * width/2;
    //Point F:
    result[0][3] = x2 - baseUnitVector[0] * (margin-width/2) + normal[0] * width/2;
    result[1][3] = y2 - baseUnitVector[1] * (margin-width/2) + normal[1] * width/2;
    return result;
  }
  
  /**
   * Calculate diamond end for given line with vector algebra:<br>
   * ---------------<><br>
   * Diamond points:<br>
   * &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;D<br>
   * A---------------C<>B<br>
   * &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;E<br>
   * 
   * Usage code:
   * <pre>
   * </pre>
   * @param x1 line start A X
   * @param y1 line start A Y
   * @param x2 line end B X
   * @param y2 line end B Y
   * @param width diamond
   * @param height of diamond
   * @return arrays of calculated diamond points:
   * [C x, D x, B x, E x]
   * [C y, D y, B y, E y]</li>
   * </ul>
   */
  public static double[][] diamondEndForLineVectorAlgebra(double x1,
      double y1, double x2, double y2, double width, double height) {
    double[][] result = new double[2][4];
    double [] baseVector = {x2-x1, y2-y1};
    double baseVectorLenght = Math.sqrt(baseVector[0]*baseVector[0] + baseVector[1]*baseVector[1]);
    double [] baseUnitVector = {baseVector[0]/baseVectorLenght, baseVector[1]/baseVectorLenght};
    //point C:
    result[0][0] = x2 - baseUnitVector[0] * width;
    result[1][0] = y2 - baseUnitVector[1] * width;
    double [] normal = {-baseUnitVector[1], baseUnitVector[0]};
    //Point D:
    result[0][1] = result[0][0] + baseUnitVector[0] * width/2 + normal[0] * height/2;
    result[1][1] = result[1][0] + baseUnitVector[1] * width/2 + normal[1] * height/2;
    //Point B:
    result[0][2] = x2;
    result[1][2] = y2;
    //Point E:
    result[0][3] = result[0][0] + baseUnitVector[0] * width/2 - normal[0] * height/2;
    result[1][3] = result[1][0] + baseUnitVector[1] * width/2 - normal[1] * height/2;
    return result;
  }

  public static double toScreenX(SettingsGraphic sg, double realX) {
    double screenX = sg.getOffsetX() + sg.getZoom() * sg.getScreenResolution() * (realX + sg.getMarginLeft());
    return screenX;
  }

  public static double toScreenLenghtX(SettingsGraphic sg, double realX) {
    double screenX = sg.getZoom() * sg.getScreenResolution() * realX;
    return screenX;
  }

  public static double toRealX(SettingsGraphic sg, double screenX) {
    double realX = (screenX - sg.getOffsetX() - sg.getMarginLeft() * sg.getScreenResolution() * sg.getZoom()) / (sg.getScreenResolution() * sg.getZoom());
    return realX;
  }

  public static double toRealLenghtX(SettingsGraphic sg, double screenX) {
    double realX = screenX / (sg.getScreenResolution() * sg.getZoom());
    return realX;
  }

  public static double toScreenY(SettingsGraphic sg, double realY) {
    double screenY = sg.getOffsetY() + sg.getZoom() * sg.getScreenResolution() * (realY + sg.getMarginTop());
    return screenY;
  }

  public static double toScreenLenghtY(SettingsGraphic sg, double realY) {
    double screenY = sg.getZoom() * sg.getScreenResolution() * realY;
    return screenY;
  }

  public static double toRealY(SettingsGraphic sg, double screenY) {
    double realY = (screenY - sg.getOffsetY() - sg.getMarginLeft() * sg.getScreenResolution() * sg.getZoom()) / (sg.getScreenResolution() * sg.getZoom());
    return realY;
  }
  
  public static double toRealLenghtY(SettingsGraphic sg, double screenY) {
    double realY = screenY / (sg.getScreenResolution() * sg.getZoom());
    return realY;
  }
  
  public static boolean isLineContainsPoint(SettingsGraphic sg, double x1,double y1, double x2, double y2, 
      double pointX, double pointY) {
    if(pointX + sg.getSelectApproximation() < Math.min(x1, x2) || pointX - sg.getSelectApproximation() > Math.max(x1, x2)) {
      return false;
    }
    if(pointY + sg.getSelectApproximation() < Math.min(y1, y2) || pointY - sg.getSelectApproximation() > Math.max(y1, y2)) {
      return false;
    }
    if(Math.abs(x2-x1) <= sg.getSelectApproximation()) {
      if(pointY + sg.getSelectApproximation() >= Math.min(y1, y2) && pointY - sg.getSelectApproximation() <= Math.max(y1, y2)) {
        return true;
      }
    }
    else {
      double y;
      if(Math.abs(y2-y1) <= sg.getSelectApproximation()) {
        y = y1;
      }
      else {
        /*
         * (y-y1)/(y2-y1)=(x-x1)/(x2-x1)
         * y=(x-x1)*(y2-y1)/(x2-x1)+y1
         */
        y = (pointX-x1)*(y2-y1)/(x2-x1)+y1;
      }
      if((Math.abs(y - pointY)) <= sg.getSelectApproximation()) {
        return true;
      }
    }
    return false;
  }

  public static double evalMiddlePoint(double point1, double point2) {
    double middle = point2 > point1 ? point1 + 
        (point2 - point1)/2 : point2 + 
        (point1 - point2)/2;
    return middle; 
  }
  
  public static boolean dragRentangleContainsOf(SettingsGraphic sg, Point2D point,
      int screenWasX, int screenWasY) {
    double realWasX = toRealX(sg, screenWasX);
    double realWasY = toRealY(sg, screenWasY);
    double widthDragRectangle = sg.getWidthDragRectangle();
    if(realWasX >= point.getX() - widthDragRectangle/2 && realWasX <= point.getX() + widthDragRectangle/2
        && realWasY >= point.getY() - widthDragRectangle/2  
        && realWasY <= point.getY() + widthDragRectangle/2)
      return true;
    return false;
  }

  public static double angleBetween2LinesVectorAlgebra(double x11, double y11, double x12,
      double y12, double x21, double y21, double x22, double y22) {
    /*
     * U(u1,u2) - vector line1
     * V(v1,v2) - vector line2
     * cosa = abs(u1*v1+u2*v2)/sqrt((u1*u1+u2*u2)*(v1*v1+v2*v2))
     */
    double [] u = {x12-x11, y12-y11};
    double [] v = {x22-x21, y22-y21};
    double cosa = Math.abs(u[0]*v[0] + u[1]*v[1])/Math.sqrt((u[0]*u[0]+u[1]*u[1])*(v[0]*v[0]+v[1]*v[1]));
    return Math.acos(cosa);
  }
  
  
  public static Point2D[] evalPointsBus(Joint2D sharedJoint, Point2D pointStart, Point2D pointEnd) {
    Point2D[] pointsBus = new Point2D[]{new Point2D(), new Point2D()};
    if(sharedJoint.getTypeJoint() == EJoint2DType.BUS_X) {
      pointsBus[0].setX(pointStart.getX());
      pointsBus[0].setY(sharedJoint.getY());
      pointsBus[1].setX(pointEnd.getX());
      pointsBus[1].setY(sharedJoint.getY());
    }
    else {//BUS Y
      pointsBus[0].setX(sharedJoint.getX());
      pointsBus[0].setY(pointStart.getY());
      pointsBus[1].setX(sharedJoint.getX());
      pointsBus[1].setY(pointEnd.getY());
    }
    return pointsBus;
  }
  
  public static double evalMinMax(double val1, double val2, EMinMax minMax) {
    if(minMax == EMinMax.MINIMUM) {
      return Math.min(val1, val2);
    }
    else {
      return Math.max(val1, val2);

    }
  }
}
