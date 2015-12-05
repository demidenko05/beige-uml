package org.beigesoft.uml.service.edit;

import java.io.File;
import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ASrvEdit;
import org.beigesoft.uml.app.model.ProjectUml;
import org.beigesoft.uml.app.model.SettingsGraphicUml;

public class SrvEditProject<PR extends ProjectUml, DLI> extends ASrvEdit<PR, DLI> {

  public SrvEditProject(ISrvI18n i18nService, ISrvDialog<DLI> dialogService, SettingsGraphicUml graphicSettings) {
    super(i18nService, dialogService, graphicSettings);
  }

  @SuppressWarnings("unchecked")
  @Override
  public PR createClone(PR m) {
    PR clone = (PR) m.clone();
    return clone;
  }

  @Override
  public void validate(PR m, Set<String> result) {
    if(m.getItsName() == null || m.getItsName().length() ==0 || m.getPathPre() == null || m.getPathPre().length() ==0) {
      result.add("pls_complete_proj_name_path");
    }
    if(m.getJavaSourceFilePath() != null) {
      File file = new File(m.getJavaSourceFilePath());
      if(!file.exists()) {
        result.add("there_is_no_java_file");
      }
    }
    m.getSettingsGraphicUml().validate(result);
  }

  @Override
  public boolean isEquals(PR m1, PR m2) {
    if(!super.isEquals(m1, m2)) {
      return false;
    }
    if(m1.getIsUseGenericForGenerateFromJavaClass() != m2.getIsUseGenericForGenerateFromJavaClass()) {
      return false;
    }
    if(m1.getPathPre() == null ? m2.getPathPre() != null : !m1.getPathPre().equals(m2.getPathPre())) {
      return false;
    }
    if(m1.getItsName() == null ? m2.getItsName() != null : !m1.getItsName().equals(m2.getItsName())) {
      return false;
    }
    if(m1.getJavaSourceFilePath() == null ? m2.getJavaSourceFilePath() != null :
      !m1.getJavaSourceFilePath().equals(m2.getJavaSourceFilePath())) {
      return false;
    }
    if(!m1.getSettingsGraphicUml().equals(m2.getSettingsGraphicUml())) {
      return false;
    }
    return true;
  }

  @Override
  public boolean getIsNew(PR m) {
    return m.getIsNew();
  }

  @Override
  public void setIsNew(PR m, boolean isNew) {
    //nothing
  }

  @Override
  public SettingsGraphicUml getSettingsGraphic() {
    return (SettingsGraphicUml) super.getSettingsGraphic();
  }
}
