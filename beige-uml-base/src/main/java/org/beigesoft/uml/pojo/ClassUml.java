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

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.uml.model.EClassKind;

/**
 * <p>Represent UML class model POJO</p>
 * 
 * @author Yury Demidenko
 */
public class ClassUml extends ShapeUmlWithName {

  private EClassKind classKind;
  
  private String packageName;
  
  private boolean isMain;
      
  private List<AttributeClass> attributes;
  
  private List<MethodClass> methods;
  
  //unpersistable:
  private Double levelXOnDiagram;

  private Double levelYOnDiagram;

  private boolean isPrepared;
      
  public ClassUml() {
    levelXOnDiagram = 0d;
    levelYOnDiagram = 0d;
    attributes = new ArrayList<AttributeClass>();
    methods = new ArrayList<MethodClass>();
    setPointStart(new Point2D(1, 1));
    this.classKind = EClassKind.CLASS;
    setHeight(1);
    setWidth(1);
  }

  public ClassUml(EClassKind classKind) {
    this();
    this.classKind = classKind;
  }

  @Override
  public ClassUml clone() {
    ClassUml clone;
    clone = (ClassUml) super.clone();
    clone.attributes = new ArrayList<AttributeClass>();
    for(AttributeClass attrClass : attributes) {
      clone.getAttributes().add(attrClass.clone());
    }
    clone.methods = new ArrayList<MethodClass>();
    for(MethodClass meth : methods) {
      clone.methods.add(meth.clone());
    }
    return clone;
  }

  //OGS:
  public List<AttributeClass> getAttributes() {
    return attributes;
  }

  public String getNamePackage() {
    return packageName;
  }

  public void setNamePackage(String namePackage) {
    this.packageName = namePackage;
  }

  public void setIsMain(boolean isMain) {
    this.isMain = isMain;
  }

  /**
   * It's means that class diagram based on this class surrounded by super/sub types 
   * @return is main class on class diagram
   */
  public boolean getIsMain() {
    return isMain;
  }

  public EClassKind getClassKind() {
    return classKind;
  }

  public void setClassKind(EClassKind classKind) {
    this.classKind = classKind;
  }

  public Double getLevelXOnDiagram() {
    return levelXOnDiagram;
  }

  public void setLevelXOnDiagram(Double levelXOnDiagram) {
    this.levelXOnDiagram = levelXOnDiagram;
  }

  public Double getLevelYOnDiagram() {
    return levelYOnDiagram;
  }

  public void setLevelYOnDiagram(Double levelYOnDiagram) {
    this.levelYOnDiagram = levelYOnDiagram;
  }

  public boolean getIsPrepared() {
    return isPrepared;
  }

  public void setIsPrepared(boolean isPrepared) {
    this.isPrepared = isPrepared;
  }

  public List<MethodClass> getMethods() {
    return methods;
  }

  public void setAttributes(List<AttributeClass> attributes) {
    this.attributes = attributes;
  }

  public void setMethods(List<MethodClass> methods) {
    this.methods = methods;
  }
}
