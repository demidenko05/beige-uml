package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.MethodClass;

public class SrvEditClassFull<CL extends ClassUml, DLI> 
    extends SrvEditShapeWithNameFull<ClassFull<CL>, DLI, CL> {
  
  private final SrvEditMethodClass<MethodClass, DLI> srvEditOperationClass;
  
  private final SrvEditAttributeClass<AttributeClass, DLI> srvEditAttributeClass;

  public SrvEditClassFull(ISrvI18n i18nService,
      ISrvDialog<DLI> dialogService, SettingsGraphicUml gp, SrvEditMethodClass<MethodClass, DLI> srvEditOperationClass,
      SrvEditAttributeClass<AttributeClass, DLI> srvEditAttributeClass) {
    super(i18nService, dialogService, gp);
    this.srvEditOperationClass = srvEditOperationClass;
    this.srvEditAttributeClass = srvEditAttributeClass;
  }

  @Override
  public void  validate(ClassFull<CL> ge, Set<String> result) {
    super.validate(ge, result);
    for(AttributeClass attrClass : ge.getShape().getAttributes()) {
      srvEditAttributeClass.validate(attrClass, result);
    }
    for(MethodClass oper : ge.getShape().getMethods()) {
      srvEditOperationClass.validate(oper, result);
    }
  }

  @Override
  public boolean isEquals(ClassFull<CL> m1, ClassFull<CL> m2) {
    if(!super.isEquals(m1, m2)) {
      return false;
    }
    if(m1.getShape().getNamePackage() == null ? m2.getShape().getNamePackage() != null :
      !m1.getShape().getNamePackage().equals(m2.getShape().getNamePackage())) {
      return false;
    }
    if(m1.getShape().getClassKind() != m2.getShape().getClassKind()) {
      return false;
    }
    if((m1.getShape().getAttributes() == null && m2.getShape().getAttributes() != null) ||
        (m1.getShape().getAttributes() != null && m2.getShape().getAttributes() == null)) {
      return false;
    }
    if(m1.getShape().getAttributes() != null && m2.getShape().getAttributes() != null) {
      if(m1.getShape().getAttributes().size() != m2.getShape().getAttributes().size()) {
        return false;
      }
      outer:
      for(AttributeClass attrCl1 : m1.getShape().getAttributes()) {
        for(AttributeClass attrCl2 : m2.getShape().getAttributes()) {
          if(srvEditAttributeClass.isEquals(attrCl1, attrCl2)) {
            continue outer;
          }
        }
        return false;
      }
    }
    if((m1.getShape().getMethods() == null && m2.getShape().getMethods() != null) ||
        (m1.getShape().getMethods() != null && m2.getShape().getMethods() == null)) {
      return false;
    }
    if(m1.getShape().getMethods() != null && m2.getShape().getMethods() != null) {
      if(m1.getShape().getMethods().size() != m2.getShape().getMethods().size()) {
        return false;
      }
      outer:
      for(MethodClass operCl1 : m1.getShape().getMethods()) {
        for(MethodClass operCl2 : m2.getShape().getMethods()) {
          if(srvEditOperationClass.isEquals(operCl1, operCl2)) {
            continue outer;
          }
        }
        return false;
      }
    }    
    return true;
  }

  @Override
  public ClassFull<CL> createClone(ClassFull<CL> m) {
    return (ClassFull<CL>) m.clone();
  }

  public SrvEditMethodClass<MethodClass, DLI> getSrvEditOperationClass() {
    return srvEditOperationClass;
  }

  public SrvEditAttributeClass<AttributeClass, DLI> getSrvEditAttributeClass() {
    return srvEditAttributeClass;
  }
}
