package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ASrvEdit;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.model.IElementUml;

public abstract class ASrvEditElementUml<EU extends IElementUml, DLI> extends ASrvEdit<EU, DLI> {

  public ASrvEditElementUml(ISrvI18n srvI18n, ISrvDialog<DLI> srvDialog,
      SettingsGraphicUml settingsGraphic) {
    super(srvI18n, srvDialog, settingsGraphic);
  }

  @Override
  public boolean isEquals(EU o1, EU o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getIndexZ() == null ? o2.getIndexZ() != null : !o1.getIndexZ().equals(o2.getIndexZ())) {
      return false;
    }
    return true;
  }

  @Override
  public boolean getIsNew(EU m) {
     return m.getIsNew();
  }

  @Override
  public void setIsNew(EU m, boolean isNew) {
    m.setIsNew(isNew);
  }

  @Override
  public void validate(EU m, Set<String> result) {
    if(m.getIndexZ() == null) {
      result.add(getSrvI18n().getMsg("pl_fill_index_z"));
    }
  }

  @Override
  public SettingsGraphicUml getSettingsGraphic() {
    return (SettingsGraphicUml) super.getSettingsGraphic();
  }
}
