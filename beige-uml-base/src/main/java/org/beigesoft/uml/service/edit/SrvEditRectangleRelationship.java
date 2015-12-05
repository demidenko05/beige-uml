package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ASrvEdit;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.ShapeUml;

public class SrvEditRectangleRelationship <O extends RectangleRelationship<SHF, SH>, DLI, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends ASrvEdit<O, DLI> {

  public SrvEditRectangleRelationship(ISrvI18n i18nService,
      ISrvDialog<DLI> dialogService, SettingsGraphicUml graphicSettings) {
    super(i18nService, dialogService, graphicSettings);
  }

  @Override
  public boolean isEquals(O o1, O o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getShapeFull() == null ? o2.getShapeFull() != null :
      !o1.getShapeFull().getShape().getId().equals(o2.getShapeFull().getShape().getId())) {
      return false;
    }
    return o1.getSideJoint() == o2.getSideJoint() && o1.getEndType() == o2.getEndType() && o1.getIsOwned() == o2.getIsOwned();
  }

  @Override
  public SettingsGraphicUml getSettingsGraphic() {
    return (SettingsGraphicUml) super.getSettingsGraphic();
  }

  @SuppressWarnings("unchecked")
  @Override
  public O createClone(O m) {
    return (O) m.clone();
  }

  @Override
  public void validate(O m, Set<String> result) {
    if(m.getShapeFull() == null) {
      result.add(getSrvI18n().getMsg("complete_shape"));
    }
  }

  @Override
  public boolean getIsNew(O m) {
    return m.getIsNew();
  }

  @Override
  public void setIsNew(O m, boolean isNew) {
    m.setIsNew(isNew);
  }
}
