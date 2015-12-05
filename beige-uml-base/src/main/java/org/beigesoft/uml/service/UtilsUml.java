/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.graphic.pojo.Joint2D;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.ClassRelationFull;
import org.beigesoft.uml.model.EJointSide;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.model.EVisibilityKind;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.pojo.ParameterMethod;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.service.comparator.ComparatorClassFullLevel;
import org.beigesoft.uml.service.comparator.ComparatorClassRelationshipFullLevelX;

/**
 * <p>Static library for UML diagram</p>
 * 
 * @author Yury Demidenko
 */
public class UtilsUml {//TODO re-do as non-static
    
  public static String evalUmlAppearanceOfVisibility(EVisibilityKind visibilityKind) {
    if(visibilityKind == EVisibilityKind.PACKAGE)
      return "";
    if(visibilityKind == EVisibilityKind.PRIVATE)
      return "-";
    if(visibilityKind == EVisibilityKind.PROTECTED)
      return "#";
    if(visibilityKind == EVisibilityKind.PUBLIC)
      return "+";
    throw new NullPointerException();
  }
  
  public static <CL extends ClassUml> 
      void fillClassUml(ClassFull<CL> clsRel, Class<?> clazz, boolean isUseGeneric) {
    clsRel.getShape().setItsName(clazz.getSimpleName());
    clsRel.getShape().setNamePackage(clazz.getName().replace("." + clazz.getSimpleName(), ""));
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      AttributeClass attributeClass = new AttributeClass();
      attributeClass.setItsName(field.getName());
      if(!clazz.isEnum()) {
        String typeName = isUseGeneric ? field.getGenericType().toString() : field.getType().getSimpleName();
        attributeClass.setItsType(typeName);
        attributeClass.setVisibilityKind(evalVisibilityKindByModifier(field.getModifiers()));
      }
      else {
        attributeClass.setVisibilityKind(EVisibilityKind.PACKAGE);//Default to prevent print sign [+]
      }
      clsRel.getShape().getAttributes().add(attributeClass);
    }
    Method[] methods = clazz.getDeclaredMethods();
    for(Method meth : methods) {
      MethodClass oper = new MethodClass();
      oper.setItsName(meth.getName());
      String returnType = isUseGeneric ? meth.getGenericReturnType().toString() : meth.getReturnType().getSimpleName();
      oper.setItsType(returnType);
      oper.setVisibilityKind(UtilsUml.evalVisibilityKindByModifier(meth.getModifiers()));
      if(meth.getGenericParameterTypes() != null) {
         for(int i = 0; i<meth.getGenericParameterTypes().length; i++) {
          ParameterMethod param = new ParameterMethod();
          param.setItsName("arg"+(i+1));
          String paramTypeName = isUseGeneric ? meth.getGenericParameterTypes()[i].toString() :
            meth.getParameterTypes()[i].getSimpleName();
          param.setItsType(paramTypeName);
          oper.getParameters().add(param);
        }
      }
      clsRel.getShape().getMethods().add(oper);
    }
  }
  
  public static EVisibilityKind evalVisibilityKindByModifier(int modifiers) {
    if ((modifiers & Modifier.PRIVATE) == Modifier.PRIVATE) {
      return EVisibilityKind.PRIVATE;
    }
    if ((modifiers & Modifier.PUBLIC) == Modifier.PUBLIC) {
      return EVisibilityKind.PUBLIC;
    }
    if ((modifiers & Modifier.PROTECTED) == Modifier.PROTECTED) {
      return EVisibilityKind.PROTECTED;
    }
    return EVisibilityKind.PACKAGE;
  }

  /**
   * Algorithm#1:
   *  1. Find out set of 0-level super-classes, i.e. they has no super-classes. 
   *  All other classes level is settled to 999
   *  2. For each of 0-level(Y) class go through its sub-classes to last-level sub-class and evaluate count of levels(Y).
   *  In this phase also conduct level of classes that has no extends|implements relations
   *  3. Sort classes according levelX-levelY, then go through them and set its startPoint 
   *  4. Adjust relations joint points 
   * @param classList
   * @param umlGraphParams
   */
  public static <CL extends ClassUml> void arrangeClassDiagram(List<ClassFull<CL>> classList,
      SettingsGraphicUml gp, int algorithmId) {
    //1. Find out set of 0-level super-classes, i.e. they has no super-classes. 
    //  All other classes level is settled to 999:
    List<ClassFull<CL>> topLevelSuperClasses = new ArrayList<ClassFull<CL>>();
    double levelX = 0;
    double fakeLevel = -999d;
    for(ClassFull<CL> clsRel : classList) {
      clsRel.getShape().setIsPrepared(false);
      clsRel.getShape().setLevelXOnDiagram(fakeLevel);
      clsRel.getShape().setLevelYOnDiagram(fakeLevel);
    }
    for(ClassFull<CL> clsRel : classList) {
      int relationCountAsSuperclass = 0;
      for(ClassRelationFull<CL> classRelation : clsRel.getRelationshipsBinary()) {
        if(classRelation.getClassRelationship().getEndType() == ERelationshipEndType.END && 
            (classRelation.getRelationship().getItsKind() == ERelationshipKind.GENERALIZATION ||
            classRelation.getRelationship().getItsKind() == ERelationshipKind.REALIZATION ||
            classRelation.getRelationship().getItsKind() == ERelationshipKind.INTERFACE_REALIZATION)) {
          relationCountAsSuperclass++;
        }
      }
      if(relationCountAsSuperclass == clsRel.getRelationshipsBinary().size()) {//there is only subclasses
        clsRel.getShape().setIsPrepared(true);
        clsRel.getShape().setLevelXOnDiagram(levelX++);
        clsRel.getShape().setLevelYOnDiagram(0d);
        topLevelSuperClasses.add(clsRel);
      }
    }
    //2. For each of 0-level(Y) class go through its sub-classes to last-level sub-class and evaluate count of levels(Y).
    //  In this phase also conduct level of classes that has no extends|implements relations 
    Double countLevelY = 1d; 
    for(ClassFull<CL> clsRel : topLevelSuperClasses) {
      countLevelY = Math.max(countLevelY, calculateUmlClassRelationsLevels(clsRel, classList));
    }
    //correction:
    levelX = 0;
    setIsPreparedForClasses(classList, false);
    for(ClassFull<CL> clsRel : classList) {
      boolean isWhereFake = false;
      if(clsRel.getShape().getLevelXOnDiagram().equals(fakeLevel)) {
        clsRel.getShape().setLevelXOnDiagram(levelX++);
        isWhereFake = true;
      }
      if(clsRel.getShape().getLevelYOnDiagram().equals(fakeLevel)) {
        clsRel.getShape().setLevelYOnDiagram(countLevelY);
        isWhereFake = true;
      }
      if(isWhereFake) {
        countLevelY = Math.max(countLevelY, calculateUmlClassRelationsLevels(clsRel, classList));
      }
    }
    //3. Sort classes according levelX-levelY, then go through them and set its startPoint:
    Collections.sort(classList, new ComparatorClassFullLevel<CL>());
    //location parameters:
    double[] bottomOfLevelY = new double[countLevelY.intValue()];
    double[] rightOfLevelY = new double[countLevelY.intValue()];
    double[] countClassOnLevelY = new double[countLevelY.intValue()];
    //a: first cycle arrange and remember location parameters
    ClassFull<CL> prevCre = null;
    double x;
    double y;
    for(ClassFull<CL> clsRel : classList) {
      if(prevCre == null) {
        x =  y = gp.getMarginDiagram();
      }
      else {
        if(clsRel.getShape().getLevelYOnDiagram().equals(prevCre.getShape().getLevelYOnDiagram())) {
          if(!clsRel.getShape().getLevelXOnDiagram().equals(prevCre.getShape().getLevelXOnDiagram())) {
            x = prevCre.getShape().getPointStart().getX() + prevCre.getShape().getWidth() + gp.getGapDiagram();
          }
          else {//e.g. more than two associations
            x = prevCre.getShape().getPointStart().getX();
          }
        }
        else {
          x =  gp.getMarginDiagram();
        }
        if(prevCre != null  && clsRel.getShape().getLevelYOnDiagram().equals(prevCre.getShape().getLevelYOnDiagram())
            && clsRel.getShape().getLevelXOnDiagram().equals(prevCre.getShape().getLevelXOnDiagram())) {//e.g. more than two associations
          y = prevCre.getShape().getPointStart().getY() + prevCre.getShape().getHeight() + gp.getGapDiagram()/2;
        }
        else if(clsRel.getShape().getLevelYOnDiagram().intValue() == 0) {
          y = gp.getMarginDiagram();
        }
        else {
          y = bottomOfLevelY[clsRel.getShape().getLevelYOnDiagram().intValue() - 1] + gp.getGapDiagram();
        }
      }
      clsRel.getShape().getPointStart().setX(x);
      clsRel.getShape().getPointStart().setY(y);
      evalJointPoints(clsRel);
      prevCre = clsRel;
      bottomOfLevelY[clsRel.getShape().getLevelYOnDiagram().intValue()] = Math.max
          (bottomOfLevelY[clsRel.getShape().getLevelYOnDiagram().intValue()], y + clsRel.getShape().getHeight());
      rightOfLevelY[clsRel.getShape().getLevelYOnDiagram().intValue()] = Math.max
          (rightOfLevelY[clsRel.getShape().getLevelYOnDiagram().intValue()], x +clsRel.getShape().getWidth());
      countClassOnLevelY[clsRel.getShape().getLevelYOnDiagram().intValue()]++;
    }
    //b: second cycle adjust position according location parameters
    double maxX = 0;
    double levelYwithMaxX = 0;
    for(int i=0; i<rightOfLevelY.length; i++) {
      if(maxX < rightOfLevelY[i]) {
        maxX = rightOfLevelY[i];
        levelYwithMaxX = i;
      }
    }
    for(ClassFull<CL> clsRel : classList) {
      if(clsRel.getShape().getLevelYOnDiagram().intValue() == 0) {
        //Adjust y to be in the bottom of first row: 
        y = clsRel.getShape().getPointStart().getY();
        double rowHeight = bottomOfLevelY[clsRel.getShape().getLevelYOnDiagram().intValue()] - y;
        y = y + rowHeight - clsRel.getShape().getHeight();
        clsRel.getShape().getPointStart().setY(y);
      }
      else if (clsRel.getShape().getLevelYOnDiagram().intValue() != bottomOfLevelY.length - 1) {
        //Adjust y to be in the middle of its row:
        y = clsRel.getShape().getPointStart().getY();
        double rowHeight = bottomOfLevelY[clsRel.getShape().getLevelYOnDiagram().intValue()] - y;
        y = y + (rowHeight - clsRel.getShape().getHeight()) / 2;
        clsRel.getShape().getPointStart().setY(y);
      }
      if(clsRel.getShape().getLevelYOnDiagram() != levelYwithMaxX) {
        //Adjust x to be row center
        double lenthToAdjust = (maxX - rightOfLevelY[clsRel.getShape().getLevelYOnDiagram().intValue()])/2;
        clsRel.getShape().getPointStart().setX(clsRel.getShape().getPointStart().getX() + lenthToAdjust);
      }
      evalJointPoints(clsRel);
    }
    //4 Adjust relations joint points:
    setIsPreparedForClasses(classList, false);
    adjustRelationPointsSideAndToCenter(gp, classList);
    setIsPreparedForClasses(classList, false);
    for(ClassFull<CL> clsRel : classList) {
      readjustRelationPoints(gp, clsRel);
    }
    setIsPreparedForClasses(classList, false);
  }

  public static <CL extends ClassUml> void evalJointPoints(ClassFull<CL> clsRel) {
    for(ClassRelationFull<CL> rel : clsRel.getRelationshipsBinary()) {
      UtilsRectangleRelationship.evalPointJoint(rel.getClassRelationship());
    }
  }

  public static <CL extends ClassUml> 
      void setIsPreparedForClasses(List<ClassFull<CL>> classList, boolean isPrepared) {
    for(ClassFull<CL> clsRel : classList) {
      for(ClassRelationFull<CL> thisClassRelation : clsRel.getRelationshipsBinary()) {
        thisClassRelation.getClassRelationship().getShapeFull().getShape().setIsPrepared(isPrepared); 
      }
    }
  }

  public static <CL extends ClassUml>
      void readjustRelationPoints(SettingsGraphicUml gp, ClassFull<CL> clsRel) {
    List<ClassRelationFull<CL>> topPoints = new ArrayList<ClassRelationFull<CL>>();
    List<ClassRelationFull<CL>> bottomPoints = new ArrayList<ClassRelationFull<CL>>();
    for(ClassRelationFull<CL> relClrel : clsRel.getRelationshipsBinary()) {
      if(relClrel.getClassRelationship().getSideJoint() == EJointSide.TOP) {
        topPoints.add(relClrel);
      }
      else if(relClrel.getClassRelationship().getSideJoint() == EJointSide.BOTTOM) {
        bottomPoints.add(relClrel);
      }
    }
    readjustTopOrBottom(gp, clsRel, bottomPoints);
    readjustTopOrBottom(gp, clsRel, topPoints);
  }
  
  protected static <CL extends ClassUml>
      void readjustTopOrBottom(SettingsGraphicUml gp, ClassFull<CL> clsRel, 
      List<ClassRelationFull<CL>> bottomTopPoints) {
    if(bottomTopPoints.size() > 1) {
      double lenghtBetweenPoints = 0;
      int topPointsWingToSide = -1;
      Collections.sort(bottomTopPoints, new ComparatorClassRelationshipFullLevelX<CL>());
      //1. Move "too many" points into sides
      do {
        topPointsWingToSide++;
        lenghtBetweenPoints = clsRel.getShape().getWidth()/(bottomTopPoints.size() - topPointsWingToSide*2 + 1);
      } while (lenghtBetweenPoints < gp.getLengthMinBetweenJointPoints());
      //2. Move "too acute angle" points into sides
      for(int i=topPointsWingToSide; i<bottomTopPoints.size()/2 ; i++) {// from left wing
        ClassRelationFull<CL> relClrel = bottomTopPoints.get(i);
        //move to border:
        if(relClrel.getClassRelationship().getSideJoint() == EJointSide.BOTTOM) {
          relClrel.getClassRelationship().setSideLength(relClrel.getClassRelationship().
              getShape().getWidth() - lenghtBetweenPoints);
          UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
          if(isTooAcuteAngle(gp, relClrel)) {
            relClrel.getClassRelationship().setSideLength(relClrel.getClassRelationship().
                getShape().getWidth() - gp.getLengthMinBetweenJointPoints());
            UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
          }
        }
        else {
          relClrel.getClassRelationship().setSideLength(lenghtBetweenPoints);
          UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
          if(isTooAcuteAngle(gp, relClrel)) {
            relClrel.getClassRelationship().setSideLength(gp.getLengthMinBetweenJointPoints());
            UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
          }
        }
        if(isTooAcuteAngle(gp, relClrel)) {
          topPointsWingToSide++;
          lenghtBetweenPoints = clsRel.getShape().getWidth()/(bottomTopPoints.size() - topPointsWingToSide*2 + 1);
        }
      }
      //3. rearrange left wing
      double lastPosition = 0;
      for(int i=topPointsWingToSide; i<bottomTopPoints.size()/2 ; i++) {
        ClassRelationFull<CL> relClrel = bottomTopPoints.get(i);
        if(relClrel.getClassRelationship().getSideJoint() == EJointSide.BOTTOM) {
          relClrel.getClassRelationship().setSideLength(clsRel.getShape().getWidth() - lastPosition -
              lenghtBetweenPoints);
        }
        else {
          relClrel.getClassRelationship().setSideLength(lastPosition + lenghtBetweenPoints);
        }
        UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
        if(isTooAcuteAngle(gp, relClrel)) {
          if(relClrel.getClassRelationship().getSideJoint() == EJointSide.BOTTOM) {
            relClrel.getClassRelationship().setSideLength(clsRel.getShape().getWidth() - lastPosition -
                gp.getLengthMinBetweenJointPoints());
          }
          else {
            relClrel.getClassRelationship().setSideLength(lastPosition +
                gp.getLengthMinBetweenJointPoints());
          }
          UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
        }
        lastPosition = clsRel.getShape().getWidth() - relClrel.getClassRelationship().getSideLength();
      }
      //4. rearrange right wing
      lastPosition = 0;
      double sizeD = bottomTopPoints.size();
      for(int i=bottomTopPoints.size()-topPointsWingToSide-1; i>=Long.valueOf(Math.round(sizeD/2)).intValue() ; i--) {
        ClassRelationFull<CL> relClrel = bottomTopPoints.get(i);
        if(relClrel.getClassRelationship().getSideJoint() == EJointSide.TOP) {
          relClrel.getClassRelationship().setSideLength(clsRel.getShape().getWidth() - lastPosition -
              lenghtBetweenPoints);
        }
        else {
          relClrel.getClassRelationship().setSideLength(lastPosition + lenghtBetweenPoints);
        }
        UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
        if(isTooAcuteAngle(gp, relClrel)) {
          if(relClrel.getClassRelationship().getSideJoint() == EJointSide.TOP) {
            relClrel.getClassRelationship().setSideLength(clsRel.getShape().getWidth() - lastPosition -
                gp.getLengthMinBetweenJointPoints());
          }
          else {
            relClrel.getClassRelationship().setSideLength(lastPosition +
                gp.getLengthMinBetweenJointPoints());
          }
          UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
        }
        lastPosition = relClrel.getClassRelationship().getSideLength();
      }
      //5. rearrange points moved into left side:
      for(int i=0; i<topPointsWingToSide ; i++) {
        ClassRelationFull<CL> relClrel = bottomTopPoints.get(i);
        if(relClrel.getClassRelationship().getSideJoint() == EJointSide.BOTTOM) {
          relClrel.getClassRelationship().setSideLength(gp.getLengthMinBetweenJointPoints()*(topPointsWingToSide-i));
        }
        else {
          relClrel.getClassRelationship().setSideLength(clsRel.getShape().getHeight() -
              gp.getLengthMinBetweenJointPoints()*(topPointsWingToSide-i));
        }
        relClrel.getClassRelationship().setSideJoint(EJointSide.LEFT);
        UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
      }
      //6. rearrange moved into right side:
      for(int i=bottomTopPoints.size()-topPointsWingToSide; i<bottomTopPoints.size() ; i++) {
        ClassRelationFull<CL> relClrel = bottomTopPoints.get(i);
        if(relClrel.getClassRelationship().getSideJoint() == EJointSide.TOP) {
          relClrel.getClassRelationship().setSideLength(gp.getLengthMinBetweenJointPoints()*
              (i-bottomTopPoints.size()+topPointsWingToSide+1));
        }
        else {
          relClrel.getClassRelationship().setSideLength(clsRel.getShape().getHeight() -
              gp.getLengthMinBetweenJointPoints()*(i-bottomTopPoints.size()+topPointsWingToSide+1));
        }
        relClrel.getClassRelationship().setSideJoint(EJointSide.RIGHT);
        UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
      }
    }
    else if(bottomTopPoints.size() == 1) {
      if(!(bottomTopPoints.get(0).getClassRelationship().getEndType() == null ||
          bottomTopPoints.get(0).getClassRelationship().getEndType() == ERelationshipEndType.UNSPECIFIED)) {
        adjustAngleIfTooAcuteAndFor90Degree(gp, bottomTopPoints.get(0));
      }
    }
  }

  public static <CL extends ClassUml>
      boolean adjustAngleFor90Degree(SettingsGraphicUml gp, ClassRelationFull<CL> relClrel) {
    Point2D[] lines = evalTwoLinesForAngle(gp, relClrel);
    if(lines != null) {
      return adjustAngleFor90Degree(gp, relClrel, lines);
    }
    return false;
  }
  
  protected static <CL extends ClassUml>
      boolean adjustAngleFor90Degree(SettingsGraphicUml gp, 
          ClassRelationFull<CL> relClrel, Point2D[] lines) {
    double angleRd = UtilsGraphMath.angleBetween2LinesVectorAlgebra(lines[0].getX(), lines[0].getY(), lines[1].getX(), lines[1].getY(),
        lines[0].getX(), lines[0].getY(), lines[2].getX(), lines[2].getY());
    if(Math.abs(Math.toDegrees(angleRd) - 90) <= gp.getAngleToAdjust90Degree()) {
      if(relClrel.getClassRelationship().getSideJoint() == EJointSide.BOTTOM || relClrel.getClassRelationship().getSideJoint() == EJointSide.TOP) {
        double corr = lines[0].getX() - lines[2].getX();
        relClrel.getClassRelationship().setSideLength(relClrel.getClassRelationship().getSideLength() + 
            (relClrel.getClassRelationship().getSideJoint() == EJointSide.BOTTOM ? corr : -corr));
      }
      else {
        double corr = lines[0].getY() - lines[2].getY();
        relClrel.getClassRelationship().setSideLength(relClrel.getClassRelationship().getSideLength() + 
            (relClrel.getClassRelationship().getSideJoint() == EJointSide.RIGHT ? -corr : corr));
      }
      UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
      return true;
    }
    return false;
  }
  
  public static <CL extends ClassUml>
      boolean adjustAngleIfTooAcuteAndFor90Degree(SettingsGraphicUml gp, 
          ClassRelationFull<CL> relClrel) {
    Point2D[] lines = evalTwoLinesForAngle(gp, relClrel);
    boolean isChanged = false;
    if(lines != null) {
      if(adjustAngleIfTooAcute(gp, relClrel, lines)) {
        isChanged = true;
      }
      if(adjustAngleFor90Degree(gp, relClrel, lines)) {
        isChanged = true;
      }
    }
    return isChanged;
  }
  
  protected static <CL extends ClassUml>
      boolean adjustAngleIfTooAcute(SettingsGraphicUml gp, 
          ClassRelationFull<CL> relClrel, Point2D[] lines) {
    if(lines != null) {
      double angleRd = UtilsGraphMath.angleBetween2LinesVectorAlgebra(lines[0].getX(), lines[0].getY(), lines[1].getX(), lines[1].getY(),
          lines[0].getX(), lines[0].getY(), lines[2].getX(), lines[2].getY());
      if(Math.toDegrees(angleRd) < gp.getAngleMinInDegreeToBeAcute()) {
        if(relClrel.getClassRelationship().getSideJoint() == EJointSide.BOTTOM || relClrel.getClassRelationship().getSideJoint() == EJointSide.TOP) {
          relClrel.getClassRelationship().setSideLength(evalSideLength(gp, relClrel, 
              lines[0], lines[2], relClrel.getClassRelationship().getShape().getWidth()/3));
        }
        else {
          relClrel.getClassRelationship().setSideLength(evalSideLength(gp, relClrel, 
              lines[0], lines[2], relClrel.getClassRelationship().getShape().getHeight()/3));
        }
        UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
        return true;
      }
    }
    return false;
  }
  
  protected static <CL extends ClassUml>
      double evalSideLength(SettingsGraphicUml gp, ClassRelationFull<CL> relClrel, 
      Point2D point1, Point2D point2, double ln) {
    double result = 0;
    if(relClrel.getClassRelationship().getSideJoint() == EJointSide.BOTTOM || relClrel.getClassRelationship().getSideJoint() == EJointSide.TOP) {
      if((point2.getX() > point1.getX() && relClrel.getClassRelationship().getSideJoint() == EJointSide.BOTTOM) ||
          (point2.getX() < point1.getX() && relClrel.getClassRelationship().getSideJoint() == EJointSide.TOP)) {
        result = ln;
      }
      else {
        result = relClrel.getClassRelationship().getShape().getWidth() - ln;
      }
    }
    else if(relClrel.getClassRelationship().getSideJoint() == EJointSide.RIGHT || relClrel.getClassRelationship().getSideJoint() == EJointSide.LEFT) {
      if((point2.getY() < point1.getY() && relClrel.getClassRelationship().getSideJoint() == EJointSide.RIGHT) ||
          (point2.getY() > point1.getY() && relClrel.getClassRelationship().getSideJoint() == EJointSide.LEFT)) {
        result = ln;
      }
      else {
        result = relClrel.getClassRelationship().getShape().getHeight() - ln;
      }
    }
    return result;
  }

  public static <CL extends ClassUml>
      boolean isTooAcuteAngle(SettingsGraphicUml gp, ClassRelationFull<CL> relClrel) {
    Point2D[] lines = evalTwoLinesForAngle(gp, relClrel);
    if(lines != null) {
      double angleRd = UtilsGraphMath.angleBetween2LinesVectorAlgebra(lines[0].getX(), lines[0].getY(), lines[1].getX(), lines[1].getY(),
          lines[0].getX(), lines[0].getY(), lines[2].getX(), lines[2].getY());
      if(Math.toDegrees(angleRd) < gp.getAngleMinInDegreeToBeAcute()) {
        return true;
      }
    }    
    return false;
  }
  
  /**
   * <p>Evaluate 3 points of 2 lines</p>
   * @param gp
   * @param relClrel
   * @return [pointShared, pointEndLine1, pointEndLine2]
   */
  public static <CL extends ClassUml>
      Point2D[] evalTwoLinesForAngle(SettingsGraphicUml gp, ClassRelationFull<CL> relClrel) {
    Point2D pointEnd2 = null;
    if(relClrel.getRelationship().getSharedJoint() == null) {//from this to across relClrel
      RectangleRelationship<ClassFull<CL>, CL> nextRelationClass = relClrel.getRelationship().getShapeRelationshipStart() == relClrel.getClassRelationship() ?
          relClrel.getRelationship().getShapeRelationshipEnd() : 
            relClrel.getRelationship().getShapeRelationshipStart();
      pointEnd2 = nextRelationClass.getPointJoint();      
    }
    else {//from this to interJoint
      pointEnd2 = new Point2D(relClrel.getRelationship().getSharedJoint().getX(), 
          relClrel.getRelationship().getSharedJoint().getY());
    }
    Point2D pointEnd1 = new Point2D(relClrel.getClassRelationship().getPointJoint().getX(), relClrel.getClassRelationship().getPointJoint().getY());
    if(relClrel.getClassRelationship().getSideJoint() == EJointSide.BOTTOM || relClrel.getClassRelationship().getSideJoint() == EJointSide.TOP) {
      pointEnd1.setX(pointEnd2.getX());
    }
    else {
      pointEnd1.setY(pointEnd2.getY());
    }
    return new Point2D[]{relClrel.getClassRelationship().getPointJoint(), pointEnd1, pointEnd2};
  }
  
  public static <CL extends ClassUml>
      void adjustRelationPointsSideAndToCenter(SettingsGraphicUml gp, List<ClassFull<CL>> classList) {
    Set<RelationshipBinary<RectangleRelationship<ClassFull<CL>, CL>, ClassFull<CL>, CL>> relationSet = new HashSet<RelationshipBinary<RectangleRelationship<ClassFull<CL>, CL>, ClassFull<CL>, CL>>();
    for(ClassFull<CL> clsRel : classList) {
      for(ClassRelationFull<CL> classRelation : clsRel.getRelationshipsBinary()) {
        relationSet.add(classRelation.getRelationship());
      }
    }
    for(RelationshipBinary<RectangleRelationship<ClassFull<CL>, CL>, ClassFull<CL>, CL> relationUml : relationSet) {
      if(relationUml.getSharedJoint() == null) {
        adjustRelationPointsSideAndToCenter(new ClassRelationFull<CL>(relationUml.getShapeRelationshipStart(), relationUml), 
            new ClassRelationFull<CL>(relationUml.getShapeRelationshipEnd(), relationUml));
      }
    }
  }

  public static <CL extends ClassUml>
      void adjustRelationPointsSideAndToCenter(ClassRelationFull<CL> relCl1,
      ClassRelationFull<CL> relCl2) {
    if(relCl1.getRelationship().getItsKind() == ERelationshipKind.GENERALIZATION
        || relCl1.getRelationship().getItsKind() == ERelationshipKind.INTERFACE_REALIZATION
        || relCl1.getRelationship().getItsKind() == ERelationshipKind.REALIZATION) {
      //Top or bottom:
      boolean isBelow = isBelowClass2ToClass1(relCl1.getClassRelationship().getShapeFull().getShape(), relCl2.getClassRelationship().getShapeFull().getShape());
      adjustJointPointToCenterAndTopOrBottom(relCl1, !isBelow);
      adjustJointPointToCenterAndTopOrBottom(relCl2, isBelow);
    }
    else {//Right or left:
      boolean isLeft = isRightClass2ToClass1(relCl1.getClassRelationship().getShapeFull().getShape(), relCl2.getClassRelationship().getShapeFull().getShape());
      adjustJointPointToCenterAndRightOrLeft(relCl1, isLeft);
      adjustJointPointToCenterAndRightOrLeft(relCl2, !isLeft);
    }
  }
  
  public static <CL extends ClassUml>
      void adjustRelationPointsSideAndToCenter(SettingsGraphicUml gp, ClassRelationFull<CL> relClrel) {
    Joint2D interJoint = relClrel.getRelationship().getSharedJoint();
    if(interJoint.getTypeJoint() == EJoint2DType.BUS_X) {//below or above
      adjustJointPointToCenterAndTopOrBottom(relClrel, 
          relClrel.getClassRelationship().getShape().getPointStart().getY() > interJoint.getY());
    }
    else if(interJoint.getTypeJoint() == EJoint2DType.BUS_Y) {//left or right
      adjustJointPointToCenterAndRightOrLeft(relClrel, 
          relClrel.getClassRelationship().getShape().getPointStart().getX() < interJoint.getX());
    }
    else if(interJoint.getTypeJoint() == EJoint2DType.POINT) {//below or above or left or right
      adjustJointPointToCenterAndTopOrBottom(relClrel, 
          relClrel.getClassRelationship().getShape().getPointStart().getY() > interJoint.getY());
      if(isTooAcuteAngle(gp, relClrel)) {
        return;
      }
      adjustJointPointToCenterAndRightOrLeft(relClrel, 
          relClrel.getClassRelationship().getShape().getPointStart().getX() > interJoint.getX());
    }
  }
  
  protected static <CL extends ClassUml>
      void adjustJointPointToCenterAndTopOrBottom(ClassRelationFull<CL> relClrel, boolean isBelow) {
    relClrel.getClassRelationship().setSideLength(relClrel.getClassRelationship().getShape().getWidth()/2);
    if(isBelow) {
      relClrel.getClassRelationship().setSideJoint(EJointSide.TOP);
    }
    else {
      relClrel.getClassRelationship().setSideJoint(EJointSide.BOTTOM);
    }
    UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
  }
  
  protected static <CL extends ClassUml>
      void adjustJointPointToCenterAndRightOrLeft(ClassRelationFull<CL> relClrel, boolean isLeft) {
    relClrel.getClassRelationship().setSideLength(relClrel.getClassRelationship().getShape().getHeight()/2);
    if(isLeft) {
      relClrel.getClassRelationship().setSideJoint(EJointSide.RIGHT);
    }
    else {
      relClrel.getClassRelationship().setSideJoint(EJointSide.LEFT);
    }
    UtilsRectangleRelationship.evalPointJoint(relClrel.getClassRelationship());
  }
  
  
  public static <CL extends ClassUml> boolean isRightClass2ToClass1(CL classUml1, CL classUml2) {
    return classUml2.getPointStart().getX() > classUml1.getPointStart().getX() + 
        classUml1.getWidth();
  }

  public static <CL extends ClassUml> boolean isLeftClass2ToClass1(CL classUml1, CL classUml2) {
    return classUml2.getPointStart().getX() + classUml2.getWidth() < classUml1.getPointStart().getX();
  }

  public static <CL extends ClassUml> boolean isAboveClass2ToClass1(CL classUml1, CL classUml2) {
    return classUml2.getPointStart().getY() + classUml2.getHeight() < classUml1.getPointStart().getY();
  }

  public static <CL extends ClassUml> boolean isBelowClass2ToClass1(CL classUml1, CL classUml2) {
    return classUml2.getPointStart().getY() > classUml1.getPointStart().getY() + classUml1.getHeight();
  }

  public static <CL extends ClassUml>
  ClassFull<CL> findUmlClassRelationsLevels(List<ClassFull<CL>> listClsRel, CL classUml) {
    ClassFull<CL> result = null;
    for(ClassFull<CL> cre : listClsRel) {
      if(cre.getShape() == classUml) {
        result = cre;
        break;
      }
    }
    return result;
  }

  public static <CL extends ClassUml>
      double calculateUmlClassRelationsLevels(ClassFull<CL> clsRel, List<ClassFull<CL>> listClsRel) {
    double levelX = 0;
    double countLevelY = 0;
    for(ClassRelationFull<CL> thisClassRelation : clsRel.getRelationshipsBinary()) {
      RelationshipBinary<RectangleRelationship<ClassFull<CL>, CL>, ClassFull<CL>, CL> relUml = thisClassRelation.getRelationship();
      RectangleRelationship<ClassFull<CL>, CL> nextClassRelation = relUml.getShapeRelationshipStart().getShapeFull() == clsRel ?
          relUml.getShapeRelationshipEnd() : relUml.getShapeRelationshipStart();
      if(!nextClassRelation.getShapeFull().getShape().getIsPrepared()) {
        if(relUml.getItsKind() == ERelationshipKind.GENERALIZATION ||
            relUml.getItsKind() == ERelationshipKind.REALIZATION ||
                relUml.getItsKind() == ERelationshipKind.INTERFACE_REALIZATION) {
          nextClassRelation.getShapeFull().getShape().setLevelXOnDiagram(levelX++);
          nextClassRelation.getShapeFull().getShape().setLevelYOnDiagram(clsRel.getShape().getLevelYOnDiagram() + 1);
          clsRel.getShape().setIsPrepared(true);
          countLevelY = Math.max(countLevelY, nextClassRelation.getShapeFull().getShape().getLevelYOnDiagram() +1);
          ClassFull<CL> anotherCre = findUmlClassRelationsLevels(listClsRel, nextClassRelation.getShapeFull().getShape());
          if(anotherCre != null) {//hasn't been deleted by user
            countLevelY = Math.max(countLevelY, calculateUmlClassRelationsLevels(anotherCre, listClsRel));
          }
        }
        else {//e.g. association to this class
          nextClassRelation.getShapeFull().getShape().setLevelXOnDiagram(clsRel.getShape().getLevelXOnDiagram() + 0.5);
          nextClassRelation.getShapeFull().getShape().setLevelYOnDiagram(clsRel.getShape().getLevelYOnDiagram());
          clsRel.getShape().setIsPrepared(true);
          countLevelY = Math.max(countLevelY, nextClassRelation.getShapeFull().getShape().getLevelYOnDiagram() +1);
          ClassFull<CL> anotherCre = findUmlClassRelationsLevels(listClsRel, nextClassRelation.getShapeFull().getShape());
          countLevelY = Math.max(countLevelY, calculateUmlClassRelationsLevels(anotherCre, listClsRel));
        }
      }
    }
    return countLevelY;
  }
  
  
  public static boolean isRelationDashed(ERelationshipKind relationKind) {
    return relationKind == ERelationshipKind.INTERFACE_REALIZATION || relationKind == ERelationshipKind.REALIZATION
        || relationKind == ERelationshipKind.USAGE  || relationKind == ERelationshipKind.SUBSTITUTION  || relationKind == ERelationshipKind.IMPORT
            || relationKind == ERelationshipKind.MERGE || relationKind == ERelationshipKind.INCLUDE || relationKind == ERelationshipKind.EXTEND
                || relationKind == ERelationshipKind.PACKAGE_ACCESS || relationKind == ERelationshipKind.PACKAGE_IMPORT || relationKind == ERelationshipKind.PACKAGE_MERGE;
  }  
}
