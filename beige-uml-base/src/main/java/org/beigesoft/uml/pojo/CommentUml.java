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

/**
 * <p>Represent UML comment model. Also can be used as text in box with no border.</p>
 * 
 * @author Yury Demidenko
 */
public class CommentUml extends ShapeUml implements Cloneable {

  private String itsText;
    
  private boolean hasBorder;
  
  private boolean isDashedRelations;
  
  private List<CommentRelationship> relationships;
  
  public CommentUml(double width, double height) {
    hasBorder = true;
    isDashedRelations = true;
    relationships = new ArrayList<CommentRelationship>();
    setPointStart(new Point2D(1, 1));
    setWidth(width);
    setHeight(height);
  }

  @Override
  public String toString() {
     return itsText == null ? "comment?"+hashCode() : itsText;
  }

  @Override
  public CommentUml clone() {
    CommentUml clone;
    clone = (CommentUml) super.clone();
    clone.relationships = new ArrayList<CommentRelationship>();
    for(CommentRelationship relation : relationships) {
      clone.relationships.add(relation.clone());
    }
    return clone;
  }
    
  //OGS:
  public void setItsText(String comment) {
    this.itsText = comment;
  }

  public String getItsText() {
    return itsText;
  }

  public List<CommentRelationship> getRelationships() {
    return relationships;
  }

  public boolean getHasBorder() {
    return hasBorder;
  }

  public void setHasBorder(boolean hasBorder) {
    this.hasBorder = hasBorder;
  }

  public boolean getIsDashedRelationships() {
    return isDashedRelations;
  }

  public void setIsDashedRelationships(boolean isDashedRelations) {
    this.isDashedRelations = isDashedRelations;
  }
}
