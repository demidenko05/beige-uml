package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.model.IContainerUml;

public class SrvEditContainerFull<M extends IContainerUml, DLI> extends ASrvEditElementUml<ContainerFull<M>, DLI> {

  private final ASrvEditElementUml<M, DLI> srvEditContainer; 
  
  public SrvEditContainerFull(ISrvI18n srvI18n, ISrvDialog<DLI> srvDialog,
      SettingsGraphicUml settingsGraphic, ASrvEditElementUml<M, DLI> srvEditContainer) {
    super(srvI18n, srvDialog, settingsGraphic);
    this.srvEditContainer = srvEditContainer;
  }

  @Override
  public boolean isEquals(ContainerFull<M> m1, ContainerFull<M> m2) {
    if(!super.isEquals(m1, m2)) {
      return false;
    }
    if(!srvEditContainer.isEquals(m1.getContainer(), m2.getContainer())) {
      return false;
    }
    if(m1.getElements() != null && m2.getElements() != null) {
      if(m1.getElements().size() != m2.getElements().size()) {
        return false;
      }
      outer:
      for(IAsmElementUmlInteractive<?, ?, ?, ?> asmEl1 : m1.getElements()) {
        for(IAsmElementUmlInteractive<?, ?, ?, ?> asmEl2 : m2.getElements()) {
          if(asmEl1.getElementUml().getId().equals(asmEl2.getElementUml().getId())) {
            continue outer;
          }
        }
        return false;
      }
    }
    return true;
  }

  @Override
  public ContainerFull<M> createClone(ContainerFull<M> cf) {
    ContainerFull<M> clone = cf.clone();
    //because Object.clone() and a "Interface".clone() not ease 
    clone.setContainer(srvEditContainer.createClone(cf.getContainer()));
    return clone;
  }

  @Override
  public void validate(ContainerFull<M> cf, Set<String> result) {
    srvEditContainer.validate(cf.getContainer(), result);
  }

  public ASrvEditElementUml<M, DLI> getSrvEditContainer() {
    return srvEditContainer;
  }
}
