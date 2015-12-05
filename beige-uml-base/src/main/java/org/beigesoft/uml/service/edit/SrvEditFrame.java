package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.FrameUml;

public class SrvEditFrame<O extends FrameUml, DLI> extends ASrvEditElementUml<O, DLI>  {

  public SrvEditFrame(ISrvI18n srvI18n, ISrvDialog<DLI> srvDialog,
      SettingsGraphicUml settingsGraphic) {
    super(srvI18n, srvDialog, settingsGraphic);
  }

  @Override
  public boolean isEquals(O o1, O o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getItsName() == null ? o2.getItsName() != null : !o1.getItsName().equals(o2.getItsName())) {
      return false;
    }
    if(o1.getItsKind() == null ? o2.getItsKind() != null : !o1.getItsKind().equals(o2.getItsKind())) {
      return false;
    }
    if(o1.getParameters() == null ? o2.getParameters() != null : !o1.getParameters().equals(o2.getParameters())) {
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
    if(m.getItsName() == null || m.getItsName().trim().length() < 1) {
      result.add(getSrvI18n().getMsg("complete_name"));
    }
  }
}
