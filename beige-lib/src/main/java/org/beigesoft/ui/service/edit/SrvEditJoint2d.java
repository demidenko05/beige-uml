package org.beigesoft.ui.service.edit;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.pojo.Joint2D;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;

public class SrvEditJoint2d<O extends Joint2D, DLI> extends SrvEditPoint2d<O, DLI> {

  public SrvEditJoint2d(ISrvI18n i18nService,
      ISrvDialog<DLI> dialogService, SettingsGraphic graphicSettings) {
    super(i18nService, dialogService, graphicSettings);
  }

  @Override
  public boolean isEquals(O o1, O o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    return o1.getTypeJoint() == o2.getTypeJoint();
  }
}
