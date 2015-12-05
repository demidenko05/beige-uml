package org.beigesoft.uml.pojo;

import java.util.ArrayList;
import java.util.List;

import org.beigesoft.uml.model.EInteractionOperator;

public class CombinedFragment extends InteractionUse {

  private EInteractionOperator interactionOperator = EInteractionOperator.ALTTERNATIVES;
  
  private List<Double> tracesY = new ArrayList<Double>();

  @Override
  public CombinedFragment clone() {
    CombinedFragment clone = (CombinedFragment) super.clone();
    clone.tracesY = new ArrayList<Double>();
    for(Double tr : tracesY) {
      clone.tracesY.add(new Double(tr));
    }
    return clone;
  }

  public EInteractionOperator getInteractionOperator() {
    return interactionOperator;
  }

  public void setInteractionOperator(EInteractionOperator interactionOperator) {
    this.interactionOperator = interactionOperator;
  }

  public List<Double> getTracesY() {
    return tracesY;
  }

  public void setTracesY(List<Double> tracesY) {
    this.tracesY = tracesY;
  }
}
