package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.diagram.pojo.DiagramClass;

public class SrvSaveXmlDiagramClass<P extends DiagramClass> extends SrvSaveXmlDiagramUml<P> {

  public static final String NAMEXML_ISABLETOCHANGEBYDOCLET = "isAbleToChangeByDoclet";

  public static final String NAMEXML_DIAGRAMCLASS = "diagramClass";

  public static final String DIAGRAM_FILE_EXTENSION = "dcl.xml";

  public SrvSaveXmlDiagramClass(String namePersistable) {
    super(namePersistable);
  }

  @Override
  public void persistModel(P p, BufferedWriter bf) throws Exception {
    super.persistModel(p, bf);
    bf.write(toStartElement(NAMEXML_ISABLETOCHANGEBYDOCLET) + 
        p.getIsAbleToChangeByDoclet() + 
        toEndElementAndNewLine(NAMEXML_ISABLETOCHANGEBYDOCLET));
  }
}
