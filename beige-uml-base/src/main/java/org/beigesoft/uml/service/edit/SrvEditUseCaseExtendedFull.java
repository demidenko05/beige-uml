package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.UseCaseExtended;

public class SrvEditUseCaseExtendedFull<UC extends UseCaseExtended, DLI> extends SrvEditUseCaseFull<UC, DLI> {

  public SrvEditUseCaseExtendedFull(ISrvI18n srvI18n, ISrvDialog<DLI> srvDialog,
      SettingsGraphicUml settingsGraphic) {
    super(srvI18n, srvDialog, settingsGraphic);
  }

  @Override
  public void validate(ShapeFullVarious<UC> ge, Set<String> result) {
    super.validate(ge, result);
    if(ge.getShape().getExtentionPoints().size() == 0) {
      result.add(getSrvI18n().getMsg("complete_extention_points"));
    }
    for(HasNameEditable ep : ge.getShape().getExtentionPoints()) {
      if(ep.getItsName() == null || ep.getItsName().length() < 1) {
        result.add(getSrvI18n().getMsg("complete_extention_points"));
      }
    }
  }

  @Override
  public boolean isEquals(ShapeFullVarious<UC> o1, ShapeFullVarious<UC> o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getShape().getExtentionPoints().size() != o2.getShape().getExtentionPoints().size()) {
      return false;
    }
    else if(o1.getShape().getExtentionPoints().size() > 0) {
      for(HasNameEditable ep1 : o1.getShape().getExtentionPoints()) {
        boolean isThereEp1InO2 = false;
        for(HasNameEditable ep2 : o2.getShape().getExtentionPoints()) {
          if(!ep2.getItsName().equals(ep1.getItsName())) {
            continue;
          }
          isThereEp1InO2 = true;
        }
        if(!isThereEp1InO2) {
          return false;
        }
      }
    }
    return true;
  }
}
