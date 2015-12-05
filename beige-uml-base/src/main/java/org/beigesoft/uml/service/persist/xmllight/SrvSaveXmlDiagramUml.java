package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.service.ISrvSaveModel;
import org.beigesoft.service.persist.xml.ASrvSaveXmlBase;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.diagram.pojo.DiagramUml;

public class SrvSaveXmlDiagramUml<P extends DiagramUml> extends ASrvSaveXmlBase implements ISrvSaveModel<P, BufferedWriter> {

  public final static String NAME_DIAGRAM_USECASE = "useCaseDiagram";

  public final static String NAME_EXTENTION_FILE_DIAGRAM_USECASE = "duc.xml";

  public final static String NAME_DIAGRAM_PACKAGE = "packageDiagram";

  public final static String NAME_EXTENTION_FILE_DIAGRAM_PACKAGE = "dpk.xml";

  public final static String NAME_DIAGRAM_OBJECT = "objectDiagram";

  public final static String NAME_EXTENTION_FILE_DIAGRAM_OBJECT = "dob.xml";

  public final static String NAME_DIAGRAM_SEQUENCE = "sequenceDiagram";

  public final static String NAME_EXTENTION_FILE_DIAGRAM_SEQUENCE = "dsq.xml";

  public SrvSaveXmlDiagramUml(String namePersistable) {
    super(namePersistable);
  }

  @Override
  public void persistModel(P p, BufferedWriter bf) throws Exception {
    bf.write(toStartElement(SettingsGraphicUml.MEASUREMENT_UNIT) + 
        p.getMeasurementUnit().name() + 
        toEndElementAndNewLine(SettingsGraphicUml.MEASUREMENT_UNIT));
  }
}
