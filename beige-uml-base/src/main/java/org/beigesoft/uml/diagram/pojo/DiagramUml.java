package org.beigesoft.uml.diagram.pojo;

import org.beigesoft.graphic.model.EMeasurementUnit;

public class DiagramUml implements Cloneable {

  private String description;
  
  private EMeasurementUnit measurementUnit = EMeasurementUnit.INCH;

  public Object clone() {
    Object clone;
    try {
      clone = super.clone();
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public EMeasurementUnit getMeasurementUnit() {
    return measurementUnit;
  }

  public void setMeasurementUnit(EMeasurementUnit measurementUnit) {
    this.measurementUnit = measurementUnit;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
