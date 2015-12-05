package org.beigesoft.uml.app.model;

import java.io.File;

import org.beigesoft.graphic.service.persist.InstrumentPersistXml;
import org.beigesoft.uml.controller.IControllerMainUml;

public class NodeDiagramUseCase extends NodeFolderProject {
  
  private final IControllerMainUml<InstrumentPersistXml> controllerMainUml;

  public NodeDiagramUseCase(String path, String name, IControllerMainUml<InstrumentPersistXml> controllerMainUml) {
    super(path, name);
    this.controllerMainUml = controllerMainUml;
  }

  @Override
  public void open() {
    InstrumentPersistXml pi = new InstrumentPersistXml();
    File file = new File(getPath());
    pi.setFile(file);
    controllerMainUml.openDiagramUseCase(pi);
  }
}
