/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.pojo;

import org.beigesoft.uml.service.UtilsUml;

/**
 * <p>Represent an attribute of a class model</p>
 * 
 * @author Yury Demidenko
 */
public class AttributeClass extends MemberClass {
  
  private String defaultValue;
  
  private String constraintsValue;
  
  /**
   * for the editor it must be not null
   */
  private MultiplicityElement multiplicityElement;
  
  public AttributeClass() {
    multiplicityElement = new MultiplicityElement();
  }
  
  @Override
  public String toString() {
    String defVal = defaultValue == null ? "" : " = " + defaultValue;
    String multiplStr = "";
    String restrStr = constraintsValue == null ?  "" : constraintsValue;
    if(multiplicityElement.getIsOrdered()) {
      restrStr = restrStr.length() == 0 ? "ORDERED" : restrStr + ", ORDERED";
    }
    if(multiplicityElement.getIsUnique()) {
      restrStr = restrStr.length() == 0 ? "UNIQUE" : restrStr + ", UNIQUE";
    }
    if(multiplicityElement.getLower() != null) {
      if(multiplicityElement.getLower() == 0 && multiplicityElement.getUpper() == null) {
        multiplStr = "[*]";
      }
      else if(multiplicityElement.getLower() == multiplicityElement.getUpper()) {
        multiplStr = "[" + multiplicityElement.getLower() + "]";
      }
      else {
        String upperStr = multiplicityElement.getUpper() == null ? "*" :
          multiplicityElement.getUpper().toString();
        multiplStr = "[" + multiplicityElement.getLower() + ".." + upperStr + "]";
      }
    }
    if(restrStr.length() > 0) {
      restrStr = "{" + restrStr + "}";
    }
    String typeStr = getItsType() == null ? "" : ": " + getItsType();
    return UtilsUml.evalUmlAppearanceOfVisibility(getVisibilityKind()) + " " + getItsName() + typeStr
        + defVal + multiplStr + restrStr;
  }

  @Override
  public AttributeClass clone() { 
    AttributeClass clone = (AttributeClass) super.clone();
    clone.setMultiplicityElement(multiplicityElement.clone());
    return clone;
  }

  //OGS:
  public String getDefaultValue() {
    return defaultValue;
  }
  
  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public String getConstraintsValue() {
    return constraintsValue;
  }

  public void setConstraintsValue(String restrictionsValue) {
    this.constraintsValue = restrictionsValue;
  }

  public MultiplicityElement getMultiplicityElement() {
    return multiplicityElement;
  }

  public void setMultiplicityElement(MultiplicityElement multiplicityElement) {
    this.multiplicityElement = multiplicityElement;
  }
}
