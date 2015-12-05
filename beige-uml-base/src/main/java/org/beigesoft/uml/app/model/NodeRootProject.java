package org.beigesoft.uml.app.model;

import org.beigesoft.ui.pojo.IOpenable;
import org.beigesoft.uml.ui.IPaneProjectUml;

public class NodeRootProject implements IOpenable {
  
  protected final IPaneProjectUml umlProjectPane;

  public NodeRootProject(IPaneProjectUml projectPane) {
    this.umlProjectPane = projectPane;
  }

  @Override
  public void open() {
    //nothing
  }

  @Override
  public String toString() {
    return umlProjectPane.getAsmProjectUml().getProjectUml() == null || umlProjectPane.getAsmProjectUml().getProjectUml().getItsName() == null ?
        "empty" : umlProjectPane.getAsmProjectUml().getProjectUml().getItsName();
  }

  public IPaneProjectUml getProjectUmlPane() {
    return umlProjectPane;
  }
}
