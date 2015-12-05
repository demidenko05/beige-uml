package org.beigesoft.ui.service.edit;

import java.util.Set;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;

public class SrvEditPoint2d<O extends Point2D, DLI> extends ASrvEdit<O, DLI> {

  public SrvEditPoint2d(ISrvI18n i18nService,
      ISrvDialog<DLI> dialogService, SettingsGraphic graphicSettings) {
    super(i18nService, dialogService, graphicSettings);
  }

  @Override
  public boolean isEquals(O o1, O o2) {
     if(!super.isEquals(o1, o2)) {
       return false;
     }
     return o1.getX() == o2.getX() && o1.getY() == o2.getY();
  }

  @SuppressWarnings("unchecked")
  @Override
  public O createClone(O m) {
    return (O) m.clone();
  }

  @Override
  public void validate(O m, Set<String> result) {
    //nothing
  }

  @Override
  public boolean getIsNew(O m) {
    return false;
  }

  @Override
  public void setIsNew(O m, boolean isNew) {
    //nothing
  }
}
