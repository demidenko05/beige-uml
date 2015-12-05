package org.beigesoft.uml.service.edit;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.UseCase;

public class SrvEditUseCaseFull<UC extends UseCase, DLI> extends SrvEditShapeWithNameFull<ShapeFullVarious<UC>, DLI, UC> {

  public SrvEditUseCaseFull(ISrvI18n srvI18n,
      ISrvDialog<DLI> srvDialog, SettingsGraphicUml settingsGraphic) {
    super(srvI18n, srvDialog, settingsGraphic);
  }

  @Override
  public boolean isEquals(ShapeFullVarious<UC> o1, ShapeFullVarious<UC> o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getShape().getIsAdjustMinimumSize() != o2.getShape().getIsAdjustMinimumSize()) {
      return false;
    }
    if(o1.getShape().getIsRectangle() != o2.getShape().getIsRectangle()) {
      return false;
    }
    return true;
  }
}
