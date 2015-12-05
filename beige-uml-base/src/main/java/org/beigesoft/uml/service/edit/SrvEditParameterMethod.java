package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ASrvEdit;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.ParameterMethod;

public class SrvEditParameterMethod<M extends ParameterMethod, DLI> extends ASrvEdit<M, DLI> {
  
  public SrvEditParameterMethod(ISrvI18n srvI18n,
      ISrvDialog<DLI> srvDialog, SettingsGraphicUml settingsGraphic) {
    super(srvI18n, srvDialog, settingsGraphic);
  }

  @SuppressWarnings("unchecked")
  @Override
  public M createClone(M m) {
    return (M) m.clone();
  }

  @Override
  public boolean isEquals(M m1, M m2) {
    if(!super.isEquals(m1, m2)) {
      return false;
    }
    if(m1.getItsName() == null ? m2.getItsName() != null : 
        !m1.getItsName().equals(m2.getItsName())) {
      return false;
    }
    if(m1.getItsType() == null ? m2.getItsType() != null :
        !m1.getItsType().equals(m2.getItsType())) {
      return false;
    }
    return true;
  }

  @Override
  public SettingsGraphicUml getSettingsGraphic() {
    return (SettingsGraphicUml) super.getSettingsGraphic();
  }

  @Override
  public void validate(M m, Set<String> result) {
    if(m.getItsName() == null) {
      result.add(getSrvI18n().getMsg("complete_name"));
    }
    if(m.getItsType() == null) {
      result.add(getSrvI18n().getMsg("complete_type"));
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
}
