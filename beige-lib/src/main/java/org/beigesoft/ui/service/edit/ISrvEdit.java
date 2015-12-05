package org.beigesoft.ui.service.edit;

import java.util.Set;

import org.beigesoft.ui.container.IContainerSrvsGui;

/**
 * 
 * @author Yury Demidenko
 *
 * @param <DLI> dialog instrument (e.g. Frame for SWING, Activity for Android)
 * @param <M> model to edit
 */
public interface ISrvEdit<M, DLI> extends IContainerSrvsGui<DLI> {

  public M createClone(M m);
  
  public void validate(M m, Set<String> result);

  public boolean getIsNew(M m);
  
  public void setIsNew(M m, boolean isNew);
  
  public boolean isEquals(M o1, M o2);
}
