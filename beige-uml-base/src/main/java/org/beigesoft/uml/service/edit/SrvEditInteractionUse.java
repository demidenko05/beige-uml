package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.InteractionUse;

public class SrvEditInteractionUse<SH extends InteractionUse, DLI> extends ASrvEditElementUml<SH, DLI> {

  public SrvEditInteractionUse(ISrvI18n srvI18n,
      ISrvDialog<DLI> srvDialog, SettingsGraphicUml settingsGraphic) {
    super(srvI18n, srvDialog, settingsGraphic);
  }

  @Override
  public void  validate(SH ge, Set<String> result) {
    super.validate(ge, result);
    if(ge.getDescription() == null || ge.getDescription().trim().length() < 1) {
      result.add(getSrvI18n().getMsg("complete_name"));
    }
  }

  @Override
  public boolean isEquals(SH o1, SH o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getIsAdjustMinimumSize() != o2.getIsAdjustMinimumSize()) {
      return false;
    }
    if(o1.getDescription() == null ? o2.getDescription() != null 
        : !o1.getDescription().equals(o2.getDescription())) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public SH createClone(SH m) {
    return (SH) m.clone();//TODO re-do to IFactory
  }
}
