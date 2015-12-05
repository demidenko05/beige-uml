package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.uml.diagram.assembly.IAsmDiagramUml;
import org.beigesoft.uml.diagram.pojo.DiagramUml;

public interface ISrvPersistAsmDiagramUml<DUML extends DiagramUml, PRI> {
  
  public void persist(IAsmDiagramUml<DUML, ?, ?, ?, ?, PRI> assemblyDiagramUml) throws Exception;
  
  public void restore(IAsmDiagramUml<DUML, ?, ?, ?, ?, PRI> assemblyDiagramUml) throws Exception;
  
  public String getNameUmlDiagram();

  public String getDiagramFileExtension();
}
