package org.beigesoft.uml.pojo;

import org.beigesoft.uml.model.EVisibilityKind;

public class MemberClass extends ParameterMethod {

  private EVisibilityKind visibilityKind = EVisibilityKind.PROTECTED;

  //SGS:
  public EVisibilityKind getVisibilityKind() {
    return visibilityKind;
  }

  public void setVisibilityKind(EVisibilityKind visibilityKind) {
    this.visibilityKind = visibilityKind;
  }
}
