package org.beigesoft.ui.service.edit;

public abstract class ASrvIsEquals<M> implements ISrvIsEquals<M> {

  @Override
  public boolean getIsEquals(M m1, M m2) {
    return (m1 == null && m2 == null) || (m1 != null && m2 != null);
  }
}
