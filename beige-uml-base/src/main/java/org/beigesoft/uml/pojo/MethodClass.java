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

import java.util.ArrayList;
import java.util.List;
import org.beigesoft.uml.service.UtilsUml;

/**
 * <p>Represent UML operation of class model</p>
 * 
 * @author Yury Demidenko
 */
public class MethodClass extends MemberClass {
    
  private List<ParameterMethod> parameters;
  
  public MethodClass() {
    parameters = new ArrayList<ParameterMethod>();
  }
  
  @Override
  public String toString() {
    String typeStr = getItsType() == null ? "" : ": " + getItsType();
    String paramsStr = "";
    for(ParameterMethod po : parameters) {
      paramsStr += paramsStr.length() == 0 ? ""  + po: ", " + po;
    }
    paramsStr = "(" + paramsStr +")";
    return UtilsUml.evalUmlAppearanceOfVisibility(getVisibilityKind()) + " " + getItsName() + paramsStr + typeStr;
  }
  
  @Override
  public MethodClass clone() { 
    MethodClass clone = (MethodClass) super.clone();
    clone.parameters = new ArrayList<ParameterMethod>();
    for(ParameterMethod po : parameters) {
      clone.parameters.add(po.clone());
    }
    return clone;
  }
  
  //SGS:
  public List<ParameterMethod> getParameters() {
    return parameters;
  }

  public void setParameters(List<ParameterMethod> parameters) {
    this.parameters = parameters;
  }
}
