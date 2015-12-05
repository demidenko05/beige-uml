package org.beigesoft.uml.app.model;

import org.beigesoft.uml.ui.IPaneProjectUml;

import static org.beigesoft.uml.service.CreatorXsdUml.*;

public class NodeDescriptorProject extends NodeRootProject {
  
  public NodeDescriptorProject(IPaneProjectUml umlProjectPane) {
    super(umlProjectPane);
  }

  @Override
  public void open() {
    getProjectUmlPane().openProjectUmlEditor();
  }

  @Override
  public String toString() {
    return DESCRIPTOR_FILE_NAME;
  }
}
