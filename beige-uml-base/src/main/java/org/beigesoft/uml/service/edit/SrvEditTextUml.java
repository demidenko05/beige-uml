package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.TextUml;

public class SrvEditTextUml<O extends TextUml, DLI> extends ASrvEditElementUml<O, DLI> {
  
  public SrvEditTextUml(ISrvI18n i18nService,
      ISrvDialog<DLI> dialogService, SettingsGraphicUml graphicSettings) {
    super(i18nService, dialogService, graphicSettings);
  }
  
  @Override
  public boolean isEquals(O o1, O o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getItsText() == null ? o2.getItsText() != null : !o1.getItsText().equals(o2.getItsText())) {
      return false;
    }
    if(o1.getIsBold() != o2.getIsBold()) {
      return false;
    }
    if(o1.getIsTransparent() != o2.getIsTransparent()) {
      return false;
    }
    if(o1.getTiedShape() == null ? o2.getTiedShape() != null : 
      !o1.getTiedShape().getId().equals(o2.getTiedShape().getId())) {
      return false;
    }
    return true;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public O createClone(O m) {
    return (O) m.clone();
  }

  @Override
  public void validate(O m, Set<String> result) {
    super.validate(m, result);
    if(m.getItsText() == null || m.getItsText().length() == 0) {
      result.add(getSrvI18n().getMsg("text_must_be"));
    }
  }
}
