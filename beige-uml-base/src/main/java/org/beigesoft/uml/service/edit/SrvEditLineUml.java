package org.beigesoft.uml.service.edit;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.LineUml;

public class SrvEditLineUml<O extends LineUml, DLI> extends ASrvEditElementUml<O, DLI> {
  
  public SrvEditLineUml(ISrvI18n i18nService,
      ISrvDialog<DLI> dialogService, SettingsGraphicUml graphicSettings) {
    super(i18nService, dialogService, graphicSettings);
  }
  
  @Override
  public boolean isEquals(O o1, O o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getLineEnd1Shape() == null ? o2.getLineEnd1Shape() != null : !o1.getLineEnd1Shape().equals(o2.getLineEnd1Shape())) {
      return false;
    }
    if(o1.getLineEnd2Shape() == null ? o2.getLineEnd2Shape() != null : !o1.getLineEnd2Shape().equals(o2.getLineEnd2Shape())) {
      return false;
    }
    if(o1.getPoint1() == null ? o2.getPoint1() != null : (o1.getPoint1().getX() !=o2.getPoint1().getX()
        || o1.getPoint1().getY() !=o2.getPoint1().getY())) {
      return false;
    }
    if(o1.getPoint2() == null ? o2.getPoint2() != null : (o1.getPoint2().getX() !=o2.getPoint2().getX()
        || o1.getPoint2().getY() !=o2.getPoint2().getY())) {
      return false;
    }
    if(o1.getIsDashed() != o2.getIsDashed()) {
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
