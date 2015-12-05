package org.beigesoft.uml.service.edit;

import java.util.ArrayList;
import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.pojo.Execution;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class SrvEditLifeLineFull<SH extends LifeLineFull<ShapeUmlWithName>, DLI> extends ASrvEditElementUml<SH, DLI> {

  public SrvEditLifeLineFull(ISrvI18n i18nSrv,
      ISrvDialog<DLI> dialogSrv, SettingsGraphicUml graphicSettings) {
    super(i18nSrv, dialogSrv, graphicSettings);
  }

  @Override
  public void  validate(SH ge, Set<String> result) {
    super.validate(ge, result);
    if(ge.getLifeLine().getItsName() == null || ge.getLifeLine().getItsName().trim().length() < 1) {
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
    if(o1.getDestructionOccurenceY() != o2.getDestructionOccurenceY()) {
      return false;
    }
    if(o1.getExecutions() != null && o2.getExecutions() != null) {
      if(o1.getExecutions().size() != o2.getExecutions().size()) {
        return false;
      }
      outer:
      for(Execution exec1 : o1.getExecutions()) {
        for(Execution exec2 : o2.getExecutions()) {
          if(exec1.getStartY() == exec2.getStartY() && exec1.getEndY() == exec2.getEndY()) {
            continue outer;
          }
        }
        return false;
      }
    }
    if(o1.getLifeLine().getItsName() == null ? o2.getLifeLine().getItsName() != null 
        : !o1.getLifeLine().getItsName().equals(o2.getLifeLine().getItsName())) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public SH createClone(SH m) {
    SH clone = (SH) m.clone();//TODO re-do to IFactory
    clone.setLifeLine((ShapeUmlWithName) m.getLifeLine().clone());
    clone.setExecutions(new ArrayList<Execution>(m.getExecutions()));
    return clone;
  }
}
