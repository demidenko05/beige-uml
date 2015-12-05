package org.beigesoft.ui.service.edit;

import java.util.Set;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;

public class SrvEditHasNameEditable<M extends HasNameEditable, DLI> extends ASrvEdit<M, DLI> {
  
  public SrvEditHasNameEditable(ISrvI18n srvI18n,
      ISrvDialog<DLI> srvDialog, SettingsGraphic settingsGraphic) {
    super(srvI18n, srvDialog, settingsGraphic);
  }
  
  @Override
  public boolean isEquals(M o1, M o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getItsName() == null ? o2.getItsName() != null : !o1.getItsName().equals(o2.getItsName())) {
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
    if(m.getItsName() == null || m.getItsName().trim().length() < 3) {
      result.add(getSrvI18n().getMsg("text_must_be"));
    }
  }

  @Override
  public boolean getIsNew(M m) {
    return m.getIsNew();
  }

  @Override
  public void setIsNew(M m, boolean isNew) {
    m.setIsNew(isNew);
  }

  @Override
  public SettingsGraphic getSettingsGraphic() {
    return super.getSettingsGraphic();
  }
}
