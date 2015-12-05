/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.app.model;
import java.util.Set;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.model.EMeasurementUnit;

/**
 * <p>UML settings</p>
 * 
 * @author Yury Demidenko
 */
public class SettingsGraphicUml extends SettingsGraphic implements Cloneable {

  private double heightHeadClass;
  
  public static final String HEIGHT_CLASS_HEAD = "heightHeadClass";

  private double heightAttributeClass;
  
  public static final String HEIGHT_ATTRIBUTE_CLASS = "heightAttributeClass";

  private double widthEndRelation;

  public static final String WIDTH_END_RELATION = "widthEndRelation";

  private double marginDiagram;

  public static final String MARGIN_DIAGRAM = "marginDiagram";

  private double marginElement;

  public static final String MARGIN_ELEMENT = "marginElement";

  private double gapDiagram;

  public static final String GAP_DIAGRAM = "gapDiagram";

  private double heightEmptyAttributes;

  public static final String HEIGHT_EMPTY_ATTRIBUTES = "heightEmptyAttributes";

  private double lengthSelfRelation;

  public static final String LENGTH_SELF_RELATION = "lengthSelfRelation";

  private double widthComment;

  public static final String WIDTH_COMMENT = "widthComment";

  private double heightMinComment;

  public static final String HEIGHT_MIN_COMMENT = "heightMinComment";
  
  private double widthMinActor;

  private double widthMinUserCase;
  
  private double heightMinUserCase;

  private double coefficientCircleInRectangle;

  private double widthMarkEllipse;

  private double widthMinLifeLine;

  private double offsetLifeLineFromBottom;

  private double widthMinStateInvContin;

  private double widthMessageCoregion;

  private double heightMessageCoregion;

  private double offsetMessageCoregion;

  private double lengthMinBetweenJointPoints;

  private double ownedRadius;

  private double lengthCornerBentComment;

  private double widthMinClass;

  private double minWidthRelationEnd;

  private double maxWidthRelationEnd;

  private double minMarginDiagram;

  private double maxMarginDiagram;

  private double minGapDiagram;

  private double maxGapDiagram;

  private double minMarginTopAttributes;

  private double maxMarginTopAttributes;

  private double minWidthDragRectangle;

  private double maxWidthDragRectangle;

  private double minHeightAttributeClass;

  private double maxHeightAttributeClass;

  private double minHeightHeadClass;

  private double maxHeightHeadClass;

  private double minHeightEmptyAttributes;

  private double maxHeightEmptyAttributes;

  private double minLenghtSelfRelation;

  private double maxLenghtSelfRelation;

  private double minWidthComment;

  private double maxWidthComment;

  private double minHeightMinComment;

  private double maxHeightMinComment;

  public SettingsGraphicUml() {
    setDefaultValues();
  }
  
  @Override
  public void setDefaultValues() {
    super.setDefaultValues();
    gapDiagram = fromInchToCurrentMeasure(0.6);
    marginElement = fromInchToCurrentMeasure(0.02);
    marginDiagram = fromInchToCurrentMeasure(0.2);
    widthEndRelation = fromInchToCurrentMeasure(0.1);
    heightAttributeClass = fromInchToCurrentMeasure(0.19);
    heightHeadClass = fromInchToCurrentMeasure(0.6);
    heightEmptyAttributes = fromInchToCurrentMeasure(0.2);
    lengthSelfRelation = fromInchToCurrentMeasure(0.25);
    widthComment = fromInchToCurrentMeasure(1);
    heightMinComment = fromInchToCurrentMeasure(0.6);    
    widthMinActor = fromInchToCurrentMeasure(0.4);
    widthMinUserCase = fromInchToCurrentMeasure(1);
    heightMinUserCase = fromInchToCurrentMeasure(0.6);
    coefficientCircleInRectangle = 1.3;
    widthMarkEllipse = fromInchToCurrentMeasure(0.17);
    widthMinLifeLine = fromInchToCurrentMeasure(0.3);
    offsetLifeLineFromBottom = fromInchToCurrentMeasure(0.25);
    widthMinStateInvContin = fromInchToCurrentMeasure(0.4);
    widthMessageCoregion = fromInchToCurrentMeasure(0.15);
    heightMessageCoregion = fromInchToCurrentMeasure(0.07);
    offsetMessageCoregion = fromInchToCurrentMeasure(0.1);
    lengthMinBetweenJointPoints = fromInchToCurrentMeasure(0.12);
    ownedRadius = fromInchToCurrentMeasure(0.035);
    lengthCornerBentComment = fromInchToCurrentMeasure(0.1);
    widthMinClass = fromInchToCurrentMeasure(1);
    minWidthRelationEnd = fromInchToCurrentMeasure(0.05);
    maxWidthRelationEnd = fromInchToCurrentMeasure(0.3);
    minMarginDiagram = fromInchToCurrentMeasure(0.1);
    maxMarginDiagram = fromInchToCurrentMeasure(1);
    minGapDiagram = fromInchToCurrentMeasure(0.2);
    maxGapDiagram = fromInchToCurrentMeasure(2);
    minMarginTopAttributes = fromInchToCurrentMeasure(0.01);
    maxMarginTopAttributes = fromInchToCurrentMeasure(0.07);
    minWidthDragRectangle = fromInchToCurrentMeasure(0.05);
    maxWidthDragRectangle = fromInchToCurrentMeasure(0.25);
    minHeightAttributeClass = fromInchToCurrentMeasure(0.10);
    maxHeightAttributeClass = fromInchToCurrentMeasure(0.40);
    minHeightHeadClass = fromInchToCurrentMeasure(0.3);
    maxHeightHeadClass = fromInchToCurrentMeasure(1);
    minHeightEmptyAttributes = fromInchToCurrentMeasure(0);
    maxHeightEmptyAttributes = fromInchToCurrentMeasure(1);
    minLenghtSelfRelation = fromInchToCurrentMeasure(0.1);
    maxLenghtSelfRelation = fromInchToCurrentMeasure(0.6);
    minWidthComment = fromInchToCurrentMeasure(0.4);
    maxWidthComment = fromInchToCurrentMeasure(2);
    minHeightMinComment = fromInchToCurrentMeasure(0.2);
    maxHeightMinComment = fromInchToCurrentMeasure(2);
  }

  public void setMeasurementUnitAndRecalculateIfNeed(EMeasurementUnit measurementUnit) {
    if(getMeasurementUnit() != measurementUnit) {
      double coef;
      if(getMeasurementUnit() == EMeasurementUnit.INCH) {
        coef = CENTEMETERS_IN_INCH;
      }
      else {
        coef = 1 / CENTEMETERS_IN_INCH;
      }
      setSelectApproximation(getSelectApproximation() * coef);
      gapDiagram *= coef;
      marginDiagram *= coef;
      heightAttributeClass *= coef;
      heightHeadClass *= coef;
      setWidthDragRectangle(getWidthDragRectangle() * coef);
      widthEndRelation *= coef;
      marginElement *= coef;
      heightEmptyAttributes *= coef;
      lengthSelfRelation *= coef;
      widthComment *= coef;
      heightMinComment *= coef;
      setMeasurementUnit(measurementUnit);
      widthMinActor *= coef;
      widthMinUserCase *= coef;
      heightMinUserCase *= coef;
      widthMinLifeLine *= coef;
      offsetLifeLineFromBottom *= coef;
      widthMinStateInvContin *= coef;
      widthMessageCoregion *= coef;
      heightMessageCoregion *= coef;
      offsetMessageCoregion *= coef;
      lengthMinBetweenJointPoints *= coef;
      ownedRadius *= coef;
      lengthCornerBentComment *= coef;
      widthMinClass *= coef;
      minWidthRelationEnd *= coef;
      maxWidthRelationEnd *= coef;
      minMarginDiagram *= coef;
      maxMarginDiagram *= coef;
      minGapDiagram *= coef;
      maxGapDiagram *= coef;
      minMarginTopAttributes *= coef;
      maxMarginTopAttributes *= coef;
      minWidthDragRectangle *= coef;
      maxWidthDragRectangle *= coef;
      minHeightAttributeClass *= coef;
      maxHeightAttributeClass *= coef;
      minHeightHeadClass *= coef;
      maxHeightHeadClass *= coef;
      minHeightEmptyAttributes *= coef;
      maxHeightEmptyAttributes *= coef;
      minLenghtSelfRelation *= coef;
      maxLenghtSelfRelation *= coef;
      minWidthComment *= coef;
      maxWidthComment *= coef;
      minHeightMinComment *= coef;
      maxHeightMinComment *= coef;
    }
  }

  @Override
  public boolean equals(Object obj) {
    SettingsGraphicUml other = (SettingsGraphicUml) obj;
    if(getSelectApproximation() != other.getSelectApproximation()) {
      return false;
    }
    if(getGapDiagram() != other.getGapDiagram()) {
      return false;
    }
    if(getMarginDiagram() != other.getMarginDiagram()) {
      return false;
    }
    if(marginElement != other.marginElement) {
      return false;
    }
    if(getWidthDragRectangle() != other.getWidthDragRectangle()) {
      return false;
    }
    if(heightAttributeClass != other.heightAttributeClass) {
      return false;
    }
    if(heightHeadClass != other.heightHeadClass) {
      return false;
    }
    if(widthEndRelation != other.widthEndRelation) {
      return false;
    }
    if(heightEmptyAttributes != other.heightEmptyAttributes) {
      return false;
    }
    if(lengthSelfRelation != other.lengthSelfRelation) {
      return false;
    }
    if(widthComment != other.widthComment) {
      return false;
    }
    if(heightMinComment != other.heightMinComment) {
      return false;
    }
    if(getMeasurementUnit() != other.getMeasurementUnit()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return evalStringForHashCode().hashCode();
  }
  
  public String evalStringForHashCode() {
    String allTogather = getMeasurementUnit().toString() + getSelectApproximation() +
        getGapDiagram() + getMarginDiagram() + marginElement + getWidthDragRectangle() +
        heightAttributeClass + heightHeadClass + widthEndRelation + heightEmptyAttributes + 
        lengthSelfRelation + widthComment + heightMinComment;
    return allTogather;
  }

  @Override
  public SettingsGraphicUml clone() {
    try {
      SettingsGraphicUml clone = (SettingsGraphicUml) super.clone();
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean getIsNew() {
    return false;
  }

  public void validate(Set<String> result) {
    if(gapDiagram < getMinGapDiagram() 
        || gapDiagram > getMaxGapDiagram()) {
      result.add("Gap must be from " + getMinGapDiagram() + 
          " to " + getMaxGapDiagram());
    }
    if(getSelectApproximation() < getMinSelectApproximation()
        || getSelectApproximation() > getMaxSelectApproximation()) {
      result.add("Select approximation must be from " + getMinSelectApproximation()
          + " to " + getMaxSelectApproximation());
    }
    if(widthEndRelation < getMinWidthRelationEnd()
        || widthEndRelation > getMaxWidthRelationEnd()) {
      result.add("Relation end width must be from " + getMinWidthRelationEnd()
          + " to " + getMaxWidthRelationEnd());
    }
    if(marginDiagram < getMinMarginDiagram()
        || marginDiagram > getMaxMarginDiagram()) {
      result.add("Diagram margin end width must be from " + getMinMarginDiagram()
          + " to " + getMaxMarginDiagram());
    }
    if(marginElement < getMinMarginTopAttributes()
        || marginElement > getMaxMarginTopAttributes()) {
      result.add("Top attributes margin must be from " + getMinMarginTopAttributes()
          + " to " + getMaxMarginTopAttributes());
    }
    if(getWidthDragRectangle() < getMinWidthDragRectangle()
        || getWidthDragRectangle() > getMaxWidthDragRectangle()) {
      result.add("Drag rectangle width  must be from " + getMinWidthDragRectangle()
          + " to " + getMaxWidthDragRectangle());
    }
    if(heightAttributeClass < getMinHeightAttributeClass()
        || heightAttributeClass > getMaxHeightAttributeClass()) {
      result.add("Class attribute heght  must be from " + getMinHeightAttributeClass()
          + " to " + getMaxHeightAttributeClass());
    }
    if(heightHeadClass < getMinHeightHeadClass()
        || heightHeadClass > getMaxHeightHeadClass()) {
      result.add("Class head heght  must be from " + getMinHeightHeadClass()
          + " to " + getMaxHeightHeadClass());
    }
    if(heightEmptyAttributes < getMinHeightEmptyAttributes()
        || heightEmptyAttributes > getMaxHeightEmptyAttributes()) {
      result.add("Height empty attributes  must be from " + getMinHeightEmptyAttributes()
          + " to " + getMaxHeightEmptyAttributes());
    }
    if(lengthSelfRelation < getMinLenghtSelfRelation()
        || lengthSelfRelation > getMaxLenghtSelfRelation()) {
      result.add("Lenght self relation  must be from " + getMinLenghtSelfRelation()
          + " to " + getMaxLenghtSelfRelation());
    }
    if(widthComment < getMinWidthComment()
        || widthComment > getMaxWidthComment()) {
      result.add("Comment width must be from " + getMinWidthComment()
          + " to " + getMaxWidthComment());
    }
    if(heightMinComment < getMinHeightMinComment()
        || heightMinComment > getMaxHeightMinComment()) {
      result.add("Comment height min must be from " + getMinHeightMinComment()
          + " to " + getMaxHeightMinComment());
    }
  }

  //Utilities:
  public int calculateDiagramMarginInPixel() {
    return Double.valueOf(fromCurrentMeasureToInch(marginDiagram)
        * getScreenResolutionDotsPerInch()).intValue();
  }

  //Pseudo-constants:
  public double getAngleMinInDegreeToBeAcute() {
    return 30;
  }

  public double getAngleToAdjust90Degree() {
    return 3;
  }

  public double getCoefRoundRectangle() {
    return fromInchToCurrentMeasure(100);
  }

  public double getWidthExecution() {
    return fromInchToCurrentMeasure(0.1);
  }

  //SGS:
  public double getHeightHeadClass() {
    return heightHeadClass;
  }

  public void setHeightHeadClass(double heightHeadClass) {
    this.heightHeadClass = heightHeadClass;
  }

  public double getHeightAttributeClass() {
    return heightAttributeClass;
  }

  public void setHeightAttributeClass(double heightAttributeClass) {
    this.heightAttributeClass = heightAttributeClass;
  }
  
  public double getWidthEndRelation() {
    return widthEndRelation;
  }

  public void setWidthEndRelation(double relationEndWidth) {
    this.widthEndRelation = relationEndWidth;
  }

  public double getMarginDiagram() {
    return marginDiagram;
  }

  public void setMarginDiagram(double marginDiagram) {
    this.marginDiagram = marginDiagram;
  }

  public double getMarginElement() {
    return marginElement;
  }

  public void setMarginElement(double marginTopAttributes) {
    this.marginElement = marginTopAttributes;
  }

  public double getGapDiagram() {
    return gapDiagram;
  }

  public void setGapDiagram(double gap) {
    this.gapDiagram = gap;
  }

  public double getHeightEmptyAttributes() {
    return heightEmptyAttributes;
  }

  public void setHeightEmptyAttributes(double heightEmptyAttributes) {
    this.heightEmptyAttributes = heightEmptyAttributes;
  }

  public double getLengthSelfRelation() {
    return lengthSelfRelation;
  }

  public void setLengthSelfRelation(double lenghtSelfRelation) {
    this.lengthSelfRelation = lenghtSelfRelation;
  }

  public double getWidthComment() {
    return widthComment;
  }

  public void setWidthComment(double widthComment) {
    this.widthComment = widthComment;
  }

  public double getHeightMinComment() {
    return heightMinComment;
  }

  public void setHeightMinComment(double heightComment) {
    this.heightMinComment = heightComment;
  }

  public double getWidthMinActor() {//n
    return widthMinActor;
  }

  public void setWidthMinActor(double widthMinActor) {
    this.widthMinActor = widthMinActor;
  }

  public double getWidthMinUserCase() {
    return widthMinUserCase;
  }

  public void setWidthMinUserCase(double widthMinUserCase) {
    this.widthMinUserCase = widthMinUserCase;
  }

  public double getHeightMinUserCase() {
    return heightMinUserCase;
  }

  public void setHeightMinUserCase(double heightMinUserCase) {
    this.heightMinUserCase = heightMinUserCase;
  }

  public double getCoefficientCircleInRectangle() {
    return coefficientCircleInRectangle;
  }

  public void setCoefficientCircleInRectangle(double coefficientCircleInRectangle) {
    this.coefficientCircleInRectangle = coefficientCircleInRectangle;
  }

  public double getWidthMarkEllipse() {
    return widthMarkEllipse;
  }

  public void setWidthMarkEllipse(double widthMarkEllipse) {
    this.widthMarkEllipse = widthMarkEllipse;
  }

  public double getWidthMinLifeLine() {
    return widthMinLifeLine;
  }

  public void setWidthMinLifeLine(double widthMinLifeLine) {
    this.widthMinLifeLine = widthMinLifeLine;
  }

  public double getOffsetLifeLineFromBottom() {
    return offsetLifeLineFromBottom;
  }

  public void setOffsetLifeLineFromBottom(double offsetLifeLineFromBottom) {
    this.offsetLifeLineFromBottom = offsetLifeLineFromBottom;
  }

  public double getWidthMinStateInvContin() {
    return widthMinStateInvContin;
  }

  public void setWidthMinStateInvContin(double widthMinStateInvContin) {
    this.widthMinStateInvContin = widthMinStateInvContin;
  }

  public void setWidthMessageCoregion(double widthMessageCoregion) {
    this.widthMessageCoregion = widthMessageCoregion;
  }

  public void setOffsetMessageCoregion(double offsetMessageCoregion) {
    this.offsetMessageCoregion = offsetMessageCoregion;
  }

  public double getWidthMessageCoregion() {
    return widthMessageCoregion;
  }

  public double getHeightMessageCoregion() {
    return heightMessageCoregion;
  }

  public void setHeightMessageCoregion(double heightMessageCoregion) {
    this.heightMessageCoregion = heightMessageCoregion;
  }

  public double getOffsetMessageCoregion() {
    return offsetMessageCoregion;
  }
  
  public double getLengthMinBetweenJointPoints() {
    return lengthMinBetweenJointPoints;    
  }

  public double getOwnedRadius() {
    return ownedRadius;
  }

  public double getLengthCornerBentComment() {
    return lengthCornerBentComment;
  }

  public double getWidthMinClass() {
    return widthMinClass;
  }

  public double getMinWidthRelationEnd() {
    return minWidthRelationEnd;
  }

  public double getMaxWidthRelationEnd() {
    return maxWidthRelationEnd;
  }

  public double getMinMarginDiagram() {
    return minMarginDiagram;
  }

  public double getMaxMarginDiagram() {
    return maxMarginDiagram;
  }

  public double getMinGapDiagram() {
    return minGapDiagram;
  }

  public double getMaxGapDiagram() {
    return maxGapDiagram;
  }
  
  public double getMinMarginTopAttributes() {
    return minMarginTopAttributes;
  }

  public double getMaxMarginTopAttributes() {
    return maxMarginTopAttributes;
  }

  public double getMinWidthDragRectangle() {
    return minWidthDragRectangle;
  }
  
  public double getMaxWidthDragRectangle() {
    return maxWidthDragRectangle;
  }
  
  public double getMinHeightAttributeClass() {
    return minHeightAttributeClass;
  }
  
  public double getMaxHeightAttributeClass() {
    return maxHeightAttributeClass;
  }
  
  public double getMinHeightHeadClass() {
    return minHeightHeadClass;
  }
  
  public double getMaxHeightHeadClass() {
    return maxHeightHeadClass;
  }
  
  public double getMinHeightEmptyAttributes() {
    return minHeightEmptyAttributes;
  }
  
  public double getMaxHeightEmptyAttributes() {
    return maxHeightEmptyAttributes;
  }
  
  public double getMinLenghtSelfRelation() {
    return minLenghtSelfRelation;
  }
    
  public double getMaxLenghtSelfRelation() {
    return maxLenghtSelfRelation;
  }
  
  public double getMinWidthComment() {
    return minWidthComment;
  }
  
  public double getMaxWidthComment() {
    return maxWidthComment;
  }
  
  public double getMinHeightMinComment() {
    return minHeightMinComment;
  }
  
  public double getMaxHeightMinComment() {
    return maxHeightMinComment;
  }
    
  public void setLengthMinBetweenJointPoints(double lengthMinBetweenJointPoints) {
    this.lengthMinBetweenJointPoints = lengthMinBetweenJointPoints;
  }

  public void setOwnedRadius(double ownedRadius) {
    this.ownedRadius = ownedRadius;
  }

  public void setLengthCornerBentComment(double lengthCornerBentComment) {
    this.lengthCornerBentComment = lengthCornerBentComment;
  }

  public void setWidthMinClass(double widthMinClass) {
    this.widthMinClass = widthMinClass;
  }

  public void setMinWidthRelationEnd(double minWidthRelationEnd) {
    this.minWidthRelationEnd = minWidthRelationEnd;
  }

  public void setMaxWidthRelationEnd(double maxWidthRelationEnd) {
    this.maxWidthRelationEnd = maxWidthRelationEnd;
  }

  public void setMinMarginDiagram(double minMarginDiagram) {
    this.minMarginDiagram = minMarginDiagram;
  }

  public void setMaxMarginDiagram(double maxMarginDiagram) {
    this.maxMarginDiagram = maxMarginDiagram;
  }

  public void setMinGapDiagram(double minGapDiagram) {
    this.minGapDiagram = minGapDiagram;
  }

  public void setMaxGapDiagram(double maxGapDiagram) {
    this.maxGapDiagram = maxGapDiagram;
  }

  public void setMinMarginTopAttributes(double minMarginTopAttributes) {
    this.minMarginTopAttributes = minMarginTopAttributes;
  }

  public void setMaxMarginTopAttributes(double maxMarginTopAttributes) {
    this.maxMarginTopAttributes = maxMarginTopAttributes;
  }

  public void setMinWidthDragRectangle(double minWidthDragRectangle) {
    this.minWidthDragRectangle = minWidthDragRectangle;
  }

  public void setMaxWidthDragRectangle(double maxWidthDragRectangle) {
    this.maxWidthDragRectangle = maxWidthDragRectangle;
  }

  public void setMinHeightAttributeClass(double minHeightAttributeClass) {
    this.minHeightAttributeClass = minHeightAttributeClass;
  }

  public void setMaxHeightAttributeClass(double maxHeightAttributeClass) {
    this.maxHeightAttributeClass = maxHeightAttributeClass;
  }

  public void setMinHeightHeadClass(double minHeightHeadClass) {
    this.minHeightHeadClass = minHeightHeadClass;
  }

  public void setMaxHeightHeadClass(double maxHeightHeadClass) {
    this.maxHeightHeadClass = maxHeightHeadClass;
  }

  public void setMinHeightEmptyAttributes(double minHeightEmptyAttributes) {
    this.minHeightEmptyAttributes = minHeightEmptyAttributes;
  }

  public void setMaxHeightEmptyAttributes(double maxHeightEmptyAttributes) {
    this.maxHeightEmptyAttributes = maxHeightEmptyAttributes;
  }

  public void setMinLenghtSelfRelation(double minLenghtSelfRelation) {
    this.minLenghtSelfRelation = minLenghtSelfRelation;
  }

  public void setMaxLenghtSelfRelation(double maxLenghtSelfRelation) {
    this.maxLenghtSelfRelation = maxLenghtSelfRelation;
  }

  public void setMinWidthComment(double minWidthComment) {
    this.minWidthComment = minWidthComment;
  }

  public void setMaxWidthComment(double maxWidthComment) {
    this.maxWidthComment = maxWidthComment;
  }

  public void setMinHeightMinComment(double minHeightMinComment) {
    this.minHeightMinComment = minHeightMinComment;
  }

  public void setMaxHeightMinComment(double maxHeightMinComment) {
    this.maxHeightMinComment = maxHeightMinComment;
  }
}
