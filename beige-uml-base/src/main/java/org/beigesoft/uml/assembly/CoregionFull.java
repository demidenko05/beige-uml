package org.beigesoft.uml.assembly;

import java.util.ArrayList;
import java.util.List;

import org.beigesoft.uml.model.ElementUml;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class CoregionFull extends ElementUml implements Cloneable {

  //final
  private IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLineFull;

  /**
   * more suitable is Set.
   * But editor UI list consume only List.
   */
  private List<IAsmElementUmlInteractive<MessageFull, ?, ?, ?>> asmMessages = new ArrayList<IAsmElementUmlInteractive<MessageFull,?,?,?>>();

  //non-persistable:
  private double x;
  
  private double minY;
  
  private double maxY;
  
  public CoregionFull() {
    setIndexZ(600);
  }
  
  @Override
  public CoregionFull clone() {
    CoregionFull clone = (CoregionFull) super.clone();
    clone.asmMessages = new ArrayList<IAsmElementUmlInteractive<MessageFull,?,?,?>>(asmMessages);
    return clone;
  }

  @Override
  public String toString() {
    String ll = asmLifeLineFull == null ? "" : "LL" + asmLifeLineFull.getElementUml().getItsName();
    String msgs = "";
    for(IAsmElementUmlInteractive<MessageFull, ?, ?, ?> asmMsg : asmMessages) {
      msgs += " msg " + asmMsg.getElementUml().getItsName();
    }
    return ll + msgs + " " + hashCode();
  }

  //SGS:
  public IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> getAsmLifeLineFull() {
    return asmLifeLineFull;
  }

  public void setAsmLifeLineFull(
      IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> asmLifeLineFull) {
    this.asmLifeLineFull = asmLifeLineFull;
  }

  public List<IAsmElementUmlInteractive<MessageFull, ?, ?, ?>> getAsmMessages() {
    return asmMessages;
  }

  public void setAsmMessages(
      List<IAsmElementUmlInteractive<MessageFull, ?, ?, ?>> asmMessages) {
    this.asmMessages = asmMessages;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getMinY() {
    return minY;
  }

  public void setMinY(double minY) {
    this.minY = minY;
  }

  public double getMaxY() {
    return maxY;
  }

  public void setMaxY(double maxY) {
    this.maxY = maxY;
  }
}
