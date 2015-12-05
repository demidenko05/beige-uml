package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.CombinedFragment;

public class SrvEditCombinedFragment<SH extends CombinedFragment, DLI> extends SrvEditInteractionUse<SH, DLI> {

  public SrvEditCombinedFragment(ISrvI18n srvI18n,
      ISrvDialog<DLI> srvDialog, SettingsGraphicUml settingsGraphic) {
    super(srvI18n, srvDialog, settingsGraphic);
  }

  @Override
  public void  validate(SH ge, Set<String> result) {
    // only indexZ
    if(ge.getIndexZ() == null) {
      result.add(getSrvI18n().getMsg("pl_fill_index_z"));
    }
  }

  @Override
  public boolean isEquals(SH o1, SH o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getInteractionOperator() != o2.getInteractionOperator()) {
      return false;
    }
    if(o1.getTracesY() == null ? o2.getTracesY() != null 
        : !o1.getTracesY().equals(o2.getTracesY())) {
      return false;
    }
    return true;
  }
}
