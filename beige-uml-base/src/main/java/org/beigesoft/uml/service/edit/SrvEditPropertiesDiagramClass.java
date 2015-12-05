package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ASrvEdit;
import org.beigesoft.uml.diagram.pojo.DiagramClass;

public class SrvEditPropertiesDiagramClass<M extends DiagramClass, DLI> extends ASrvEdit<M, DLI> {

  public SrvEditPropertiesDiagramClass(ISrvI18n srvI18n, ISrvDialog<DLI> srvDialog,
      SettingsGraphic settingsGraphic) {
    super(srvI18n, srvDialog, settingsGraphic);
  }

  @Override
  public boolean isEquals(M m1, M m2) {
    if(!super.isEquals(m1, m2)) {
      return false;
    }
    if(m1.getIsAbleToChangeByDoclet() != m2.getIsAbleToChangeByDoclet()) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public M createClone(M m) {
    return (M) m.clone();
  }

  @Override
  public void validate(M m, Set<String> result) {
    //nothing
    
  }

  @Override
  public boolean getIsNew(M m) {
    return false;
  }

  @Override
  public void setIsNew(M m, boolean isNew) {
    //nothing
  }
}
