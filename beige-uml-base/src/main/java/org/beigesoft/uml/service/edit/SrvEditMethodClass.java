package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.pojo.ParameterMethod;

public class SrvEditMethodClass<M extends MethodClass, DLI> extends SrvEditMemberClass<M, DLI> {
  
  private final SrvEditParameterMethod<ParameterMethod, DLI> srvEditParameterMethod;
  
  public SrvEditMethodClass(ISrvI18n srvI18n,
      ISrvDialog<DLI> srvDialog, SettingsGraphicUml settingsGraphic, 
      SrvEditParameterMethod<ParameterMethod, DLI> srvEditParameterMethod) {
    super(srvI18n, srvDialog, settingsGraphic);
    this.srvEditParameterMethod = srvEditParameterMethod;
  }

  @Override
  public boolean isEquals(M m1, M m2) {
    if(!super.isEquals(m1, m2)) {
      return false;
    }
    if((m1.getParameters() == null && m2.getParameters() != null) ||
        (m1.getParameters() != null && m2.getParameters() == null)) {
      return false;
    }
    if(m1.getParameters() != null && m2.getParameters() != null) {
      if(m1.getParameters().size() != m2.getParameters().size()) {
        return false;
      }
      outer:
      for(ParameterMethod paramOper1 : m1.getParameters()) {
        for(ParameterMethod paramOper2 : m2.getParameters()) {
          if(srvEditParameterMethod.isEquals(paramOper1, paramOper2)) {
            continue outer;
          }
        }
        return false;
      }
    }
    return true;
  }

  @Override
  public void validate(M m, Set<String> result) {
    super.validate(m, result);
    for(ParameterMethod po : m.getParameters()) {
      srvEditParameterMethod.validate(po, result);
    }
  }

  public SrvEditParameterMethod<ParameterMethod, DLI> getSrvEditParameterMethod() {
    return srvEditParameterMethod;
  }
}
