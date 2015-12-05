package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.SrvIsEqualsHasNameEditable;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.model.InstanceUml;

public class SrvEditInstanceFull<O extends InstanceUml, DLI> extends SrvEditShapeWithNameFull<ShapeFullVarious<O>, DLI, O> {

  public SrvIsEqualsHasNameEditable<HasNameEditable> srvIsEqualsHasNameEditable = new SrvIsEqualsHasNameEditable<HasNameEditable>();

  public SrvEditInstanceFull(ISrvI18n i18nSrv,
      ISrvDialog<DLI> dialogSrv, SettingsGraphicUml graphicSettings) {
    super(i18nSrv, dialogSrv, graphicSettings);
  }

  @Override
  public boolean isEquals(ShapeFullVarious<O> o1, ShapeFullVarious<O> o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getShape().getNameClass() == null ? o2.getShape().getNameClass() != null : 
      !o1.getShape().getNameClass().equals(o2.getShape().getNameClass())) {
      return false;
    }
    if(o1.getShape().getValue() == null ? o2.getShape().getValue() != null : 
      !o1.getShape().getValue().equals(o2.getShape().getValue())) {
      return false;
    }
    if(!isEqualsCollections(o1.getShape().getStructure(), o2.getShape().getStructure(), 
        true, srvIsEqualsHasNameEditable)) {
      return false;
    }
    
    return true;
  }

  @Override
  public void validate(ShapeFullVarious<O> ge, Set<String> result) {
    if(ge.getShape().getNameClass() == null) {
      result.add(getSrvI18n().getMsg("fill_class_name"));
    }
    super.validate(ge, result);
  }
}
