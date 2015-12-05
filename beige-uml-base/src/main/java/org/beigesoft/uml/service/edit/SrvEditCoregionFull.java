package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.CoregionFull;

public class SrvEditCoregionFull<CR extends CoregionFull, DLI> extends ASrvEditElementUml<CR, DLI> {

  public SrvEditCoregionFull(ISrvI18n i18nSrv,
      ISrvDialog<DLI> dialogSrv, SettingsGraphicUml graphicSettings) {
    super(i18nSrv, dialogSrv, graphicSettings);
  }

  @Override
  public void  validate(CR ge, Set<String> result) {
    super.validate(ge, result);
    if(ge.getAsmLifeLineFull() == null || ge.getAsmMessages().size() < 2) {
      result.add(getSrvI18n().getMsg("complete_lifeline_and_minimum_2msg"));
    }
  }

  @Override
  public boolean isEquals(CR o1, CR o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getAsmLifeLineFull() != o2.getAsmLifeLineFull()) {
      return false;
    }
    if(o1.getAsmMessages() == null ? o2.getAsmMessages() != null 
        : !o1.getAsmMessages().equals(o2.getAsmMessages())) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public CR createClone(CR m) {
    return (CR) m.clone();
  }

  @Override
  public boolean getIsNew(CR m) {
    return m.getIsNew();
  }

  @Override
  public void setIsNew(CR m, boolean isNew) {
    m.setIsNew(isNew);
  }
}
