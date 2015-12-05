package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public class SrvEditShapeWithNameFull<SHF extends ShapeFull<SH>, DLI, SH extends ShapeUmlWithName> extends ASrvEditElementUml<SHF, DLI> {

  public SrvEditShapeWithNameFull(ISrvI18n i18nSrv,
      ISrvDialog<DLI> dialogSrv, SettingsGraphicUml graphicSettings) {
    super(i18nSrv, dialogSrv, graphicSettings);
  }

  @Override
  public void  validate(SHF ge, Set<String> result) {
    super.validate(ge, result);
    if(ge.getShape().getItsName() == null || ge.getShape().getItsName().trim().length() < 1) {
      result.add(getSrvI18n().getMsg("complete_name"));
    }
  }

  @Override
  public boolean isEquals(SHF o1, SHF o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getShape().getIsAdjustMinimumSize() != o2.getShape().getIsAdjustMinimumSize()) {
      return false;
    }
    if(o1.getShape().getItsName() == null ? o2.getShape().getItsName() != null 
        : !o1.getShape().getItsName().equals(o2.getShape().getItsName())) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public SHF createClone(SHF m) {
    return (SHF) m.clone();
  }
}
