package org.beigesoft.uml.app.model;

import java.io.File;

import org.beigesoft.uml.controller.IControllerMainUml;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;

public class NodeDiagramSequence extends NodeFolderProject {
  
  private final IControllerMainUml<FileAndWriter> controllerMainUml;

  public NodeDiagramSequence(String path, String name, IControllerMainUml<FileAndWriter> controllerMainUml) {
    super(path, name);
    this.controllerMainUml = controllerMainUml;
  }

  @Override
  public void open() {
    FileAndWriter pi = new FileAndWriter();
    File file = new File(getPath());
    pi.setFile(file);
    controllerMainUml.openDiagramSequence(pi);
  }
}
