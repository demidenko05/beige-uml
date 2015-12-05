package org.beigesoft.uml.service.edit;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.Actor;

public class SrvEditActorFull<O extends Actor, DLI> extends SrvEditShapeWithNameFull<ShapeFullVarious<O>, DLI, O> {

  public SrvEditActorFull(ISrvI18n i18nSrv,
      ISrvDialog<DLI> dialogSrv, SettingsGraphicUml graphicSettings) {
    super(i18nSrv, dialogSrv, graphicSettings);
  }

  @Override
  public boolean isEquals(ShapeFullVarious<O> o1, ShapeFullVarious<O> o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getShape().getPathImage() == null ? o2.getShape().getPathImage() != null : !o1.getShape().getPathImage().equals(o2.getShape().getPathImage())) {
      return false;
    }
    return true;
  }
}
