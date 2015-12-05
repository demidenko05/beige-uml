package org.beigesoft.uml.service.edit;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.StateInvContin;

public class SrvEditStateInvContin<SH extends StateInvContin, DLI> extends SrvEditShapeWithName<SH, DLI> {

  public SrvEditStateInvContin(ISrvI18n srvI18n, ISrvDialog<DLI> srvDialog,
      SettingsGraphicUml settingsGraphic) {
    super(srvI18n, srvDialog, settingsGraphic);
  }

  @Override
  public boolean isEquals(SH o1, SH o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getIsBold() != o2.getIsBold()) {
      return false;
    }
    return true;
  }
}
