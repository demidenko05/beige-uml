package org.beigesoft.uml.model;

import java.util.ArrayList;
import java.util.List;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class InstanceUml extends ShapeUmlWithName {

  private String nameClass;

  private String value;
  
  private List<HasNameEditable> structure = new ArrayList<HasNameEditable>();
  
  @Override
  public InstanceUml clone() {
    InstanceUml clone = (InstanceUml) super.clone();
    clone.setStructure(new ArrayList<HasNameEditable>());
    for(HasNameEditable member : structure) {
      clone.getStructure().add(member.clone());
    }
    return clone;
  }

  public String getNameClass() {
    return nameClass;
  }

  public void setNameClass(String nameClass) {
    this.nameClass = nameClass;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public List<HasNameEditable> getStructure() {
    return structure;
  }

  public void setStructure(List<HasNameEditable> structure) {
    this.structure = structure;
  }
}
