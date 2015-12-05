package org.beigesoft.uml.pojo;

import java.util.ArrayList;
import java.util.List;

import org.beigesoft.pojo.HasNameEditable;

public class UseCaseExtended extends UseCase {
 
  private List<HasNameEditable> extentionPoints;
    
  public UseCaseExtended() {
    extentionPoints = new ArrayList<HasNameEditable>();
  }

  @Override
  public UseCase clone() {
    UseCaseExtended clone = (UseCaseExtended) super.clone();
    clone.setExtentionPoints(new ArrayList<HasNameEditable>());
    for(HasNameEditable ep : extentionPoints) {
      clone.getExtentionPoints().add(ep.clone());
    }
    return clone;
  }

  public List<HasNameEditable> getExtentionPoints() {
    return extentionPoints;
  }

  public void setExtentionPoints(List<HasNameEditable> extentionPoints) {
    this.extentionPoints = extentionPoints;
  }
}
