package org.beigesoft.ui.service.edit;

import org.beigesoft.pojo.HasNameEditable;

public class SrvIsEqualsHasNameEditable<M extends HasNameEditable> extends ASrvIsEquals<M> {
  
  @Override
  public boolean getIsEquals(M o1, M o2) {
    if(!super.getIsEquals(o1, o2)) {
      return false;
    }
    if(o1.getItsName() == null ? o2.getItsName() != null : !o1.getItsName().equals(o2.getItsName())) {
      return false;
    }
    return true;
  }
}
