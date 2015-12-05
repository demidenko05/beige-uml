package org.beigesoft.ui.service.edit;

import java.util.Collection;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;

public abstract class ASrvEdit<M, DLI> implements ISrvEdit<M, DLI> {

  private final ISrvI18n srvI18n;

  private final ISrvDialog<DLI> srvDialog;
  
  private final SettingsGraphic settingsGraphic;

  public ASrvEdit(ISrvI18n srvI18n, ISrvDialog<DLI> srvDialog, SettingsGraphic settingsGraphic) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
  }

  @Override
  public boolean isEquals(M m1, M m2) {
    return (m1 == null && m2 == null) || (m1 != null && m2 != null);
  }

  @Override
  public ISrvI18n getSrvI18n() {
    return srvI18n;
  }

  @Override
  public ISrvDialog<DLI> getSrvDialog() {
    return srvDialog;
  }

  @Override
  public SettingsGraphic getSettingsGraphic() {
    return settingsGraphic;
  }
  
  //Utils:
  /**
   * This is alternative
   * to avoid problems of overriding Object.equals(Object other)
   * @param coll1
   * @param coll2
   * @param isOrdered
   * @param srvIsEquals
   * @return
   */
  @SuppressWarnings("unchecked")
  public <E> boolean isEqualsCollections(Collection<E> coll1, Collection<E> coll2, boolean isOrdered,
      ISrvIsEquals<E> srvIsEquals) {
    if((coll1 == null && coll2 != null) ||
        (coll1 != null && coll2 == null)) {
      return false;
    }
    if(coll1 != null && coll2 != null) {
      if(coll1.size() != coll2.size()) {
        return false;
      }
      if(isOrdered) {
        Object[] arr1 = coll1.toArray();
        Object[] arr2 = coll2.toArray();
        for(int i=0; i < coll1.size(); i++) {
          if(!srvIsEquals.getIsEquals((E)arr1[i], (E)arr2[i])) {
            return false;
          }
        }
      }
      else {
        outer:
        for(E e1 : coll1) {
          for(E e2 : coll2) {
            if(srvIsEquals.getIsEquals(e1, e2)) {
              continue outer;
            }
          }
          return false;
        }
      }
    }
    return true;  
  }
}
