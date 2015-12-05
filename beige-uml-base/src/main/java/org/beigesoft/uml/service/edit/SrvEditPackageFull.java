package org.beigesoft.uml.service.edit;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.PackageUml;

public class SrvEditPackageFull<O extends PackageUml, DLI> extends SrvEditShapeWithNameFull<ShapeFullVarious<O>, DLI, O> {

  public SrvEditPackageFull(ISrvI18n i18nSrv,
      ISrvDialog<DLI> dialogSrv, SettingsGraphicUml graphicSettings) {
    super(i18nSrv, dialogSrv, graphicSettings);
  }

  @Override
  public boolean isEquals(ShapeFullVarious<O> o1, ShapeFullVarious<O> o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getShape().getIsNameInHead() != o2.getShape().getIsNameInHead()) {
      return false;
    }
    if(o1.getShape().getComment() == null ? o2.getShape().getComment() != null : 
      !o1.getShape().getComment().equals(o2.getShape().getComment())) {
      return false;
    }
    return true;
  }
}
