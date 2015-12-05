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

/**
 * <p>Represent UML multiplicity element model</p>
 * 
 * @author Yury Demidenko
 */
public class MultiplicityElement implements Cloneable {
  
  private boolean isOrdered;
  
  private boolean isUnique;
  
  private Integer lower;

  private Integer upper;

  @Override
  public MultiplicityElement clone() {
    try {
      MultiplicityElement clone = (MultiplicityElement) super.clone();
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean getIsOrdered() {
    return isOrdered;
  }

  public void setIsOrdered(boolean isOrdered) {
    this.isOrdered = isOrdered;
  }

  public boolean getIsUnique() {
    return isUnique;
  }

  public void setIsUnique(boolean isUnique) {
    this.isUnique = isUnique;
  }

  public Integer getLower() {
    return lower;
  }

  public void setLower(Integer lower) {
    this.lower = lower;
  }

  public Integer getUpper() {
    return upper;
  }

  public void setUpper(Integer upper) {
    this.upper = upper;
  }
}
