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

import org.beigesoft.pojo.HasNameEditable;

/**
 * <p>Represent UML parameter of operation of class model</p>
 * 
 * @author Yury Demidenko
 */
public class ParameterMethod extends HasNameEditable {

  private String itsType;
      
  @Override
  public String toString() {
    return getItsName() + ": " +itsType;
  }

  @Override
  public ParameterMethod clone() { 
    ParameterMethod clone = (ParameterMethod) super.clone();
    return clone;
  }

  //SGS:
  public String getItsType() {
    return itsType;
  }

  public void setItsType(String itsType) {
    this.itsType = itsType;
  }
}
