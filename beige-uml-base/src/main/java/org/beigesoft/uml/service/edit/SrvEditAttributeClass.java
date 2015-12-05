package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.AttributeClass;

public class SrvEditAttributeClass<M extends AttributeClass, DLI> extends SrvEditMemberClass<M, DLI> {

  public SrvEditAttributeClass(ISrvI18n srvI18n, ISrvDialog<DLI> srvDialog,
      SettingsGraphicUml settingsGraphic) {
    super(srvI18n, srvDialog, settingsGraphic);
  }

  @Override
  public boolean isEquals(M m1, M m2) {
    if(!super.isEquals(m1, m2)) {
      return false;
    }
    if(m1.getDefaultValue() == null ? m2.getDefaultValue() != null :
      !m1.getDefaultValue().equals(m2.getDefaultValue())) {
      return false;
    }
    if(m1.getConstraintsValue() == null ? m2.getConstraintsValue() != null :
      !m1.getConstraintsValue().equals(m2.getConstraintsValue())) {
      return false;
    }
    if((m1.getMultiplicityElement() == null && m2.getMultiplicityElement() != null)
        ||(m1.getMultiplicityElement() != null && m2.getMultiplicityElement() == null)) {
      return false;
    }
    if(m1.getMultiplicityElement() != null && m2.getMultiplicityElement() != null) {
      if(m1.getMultiplicityElement().getIsOrdered() != m2.getMultiplicityElement().getIsOrdered()) {
        return false;
      }
      if(m1.getMultiplicityElement().getIsUnique() != m2.getMultiplicityElement().getIsUnique()) {
        return false;
      }
      if(m1.getMultiplicityElement().getLower() != m2.getMultiplicityElement().getLower()) {
        return false;
      }
      if(m1.getMultiplicityElement().getUpper() != m2.getMultiplicityElement().getUpper()) {
        return false;
      }
    }
   return true;
  }

  @Override
  public void validate(M m, Set<String> result) {
    super.validate(m, result);
    /**
     * Editor dependent logic
     * lower == null means MultiplicityElement is null
     * therefore isOrdered and isUnique must be false 
     */
    if(m.getMultiplicityElement().getLower() == null && 
        (m.getMultiplicityElement().getIsOrdered() || m.getMultiplicityElement().getIsUnique())) {
      result.add(getSrvI18n().getMsg("multiplicityEditRule"));
    }
  }
}
