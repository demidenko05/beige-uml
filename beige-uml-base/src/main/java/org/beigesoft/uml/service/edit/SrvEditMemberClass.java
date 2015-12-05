package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.MemberClass;

public class SrvEditMemberClass<M extends MemberClass, DLI> extends SrvEditParameterMethod<M, DLI> {
  
  public SrvEditMemberClass(ISrvI18n i18nService,
      ISrvDialog<DLI> dialogService, SettingsGraphicUml gp) {
    super(i18nService, dialogService, gp);
  }

  @Override
  public boolean isEquals(M m1, M m2) {
    if(!super.isEquals(m1, m2)) {
      return false;
    }
    if(m1.getVisibilityKind() != m2.getVisibilityKind()) {
      return false;
    }
    return true;
  }

  @Override
  public void validate(M m, Set<String> result) {
    if(m.getItsName() == null) {
      result.add(getSrvI18n().getMsg("complete_name"));
    }
  }
}
