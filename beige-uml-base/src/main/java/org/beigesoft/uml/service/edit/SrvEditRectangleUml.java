package org.beigesoft.uml.service.edit;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.RectangleUml;

public class SrvEditRectangleUml<O extends RectangleUml, DLI> extends ASrvEditElementUml<O, DLI> {
  
  public SrvEditRectangleUml(ISrvI18n i18nService,
      ISrvDialog<DLI> dialogService, SettingsGraphicUml graphicSettings) {
    super(i18nService, dialogService, graphicSettings);
  }
  
  @Override
  public boolean isEquals(O o1, O o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getIsTransparent() != o2.getIsTransparent()) {
      return false;
    }
    return true;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public O createClone(O m) {
    return (O) m.clone();
  }
}
