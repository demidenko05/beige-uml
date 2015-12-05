package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.CommentUml;

public class SrvEditComment<O extends CommentUml, DLI> extends ASrvEditElementUml<O, DLI> {

  public SrvEditComment(ISrvI18n i18nService,
      ISrvDialog<DLI> dialogService, SettingsGraphicUml gp) {
    super(i18nService, dialogService, gp);
  }

  @Override
  public void  validate(O ge, Set<String> result) {
    super.validate(ge, result);
    if(ge.getItsText() == null || ge.getItsText().trim().length() < 3) {
      result.add(getSrvI18n().getMsg("complete_comment"));
    }
  }

  @Override
  public boolean isEquals(O o1, O o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getItsText() == null ? o2.getItsText() != null : !o1.getItsText().equals(o2.getItsText())) {
      return false;
    }
    if(o1.getHasBorder() != o2.getHasBorder()) {
      return false;
    }
    if((o1.getRelationships() == null && o2.getRelationships() != null) ||
        (o1.getRelationships() != null && o2.getRelationships() == null)) {
      return false;
    }
    else if(o1.getRelationships() != null && o2.getRelationships() != null) {
      if(o1.getRelationships().size() != o2.getRelationships().size()) {
        return false;
      }
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public O createClone(O m) {
    return (O) m.clone();
  }
}
