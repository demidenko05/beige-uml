package org.beigesoft.uml.service.persist.xmllight;

import static org.beigesoft.uml.service.CreatorXsdUml.URI_UML_PROJECT_XMLSCHEMA;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.beigesoft.service.persist.xml.ASaxModelFiller;
import org.beigesoft.service.persist.xml.ASrvSaveXmlBase;
import org.beigesoft.service.persist.xml.SaxReader;
import org.beigesoft.uml.diagram.assembly.IAsmDiagramUml;
import org.beigesoft.uml.diagram.assembly.IAsmListElementsUml;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SrvPersistLightXmlAsmDiagramUml<DUML extends DiagramUml> extends ASrvSaveXmlBase implements 
    ISrvPersistAsmDiagramUml<DUML, FileAndWriter>  {

  private final String diagramFileExtension;

  private final SaxReader saxReader;
  
  @SuppressWarnings("rawtypes")
  private final ASaxModelFiller saxModelFiller;
  
  private final SrvSaveXmlDiagramUml<DUML> srvSaveXmlDiagramUml;

  public SrvPersistLightXmlAsmDiagramUml(SrvSaveXmlDiagramUml<DUML> srvSaveXmlDiagramUml, 
      @SuppressWarnings("rawtypes") ASaxModelFiller saxModelFiller, String diagramFileExtension) {
    super(srvSaveXmlDiagramUml.getNamePersistable());
    this.srvSaveXmlDiagramUml = srvSaveXmlDiagramUml;
    this.diagramFileExtension = diagramFileExtension;
    this.saxModelFiller = saxModelFiller;
    saxReader = new SaxReader(saxModelFiller);
  }

  @Override
  public void persist(
      IAsmDiagramUml<DUML, ?, ?, ?, ?, FileAndWriter> asmDiagramUml)
      throws Exception {
    BufferedWriter bf = null;
    try {
      bf = new BufferedWriter(new FileWriter(asmDiagramUml.getPersistInstrument().getFile()));
      asmDiagramUml.getPersistInstrument().setBufferedWriter(bf);
      bf.write(getStartXmlAndNewLine());
      bf.write(toStartElementOpened(getNamePersistable()) + "xmlns=\"" + URI_UML_PROJECT_XMLSCHEMA + "\">\n\n");
      srvSaveXmlDiagramUml.persistModel(asmDiagramUml.getDiagramUml(), bf);
      bf.write("\n");
      for(IAsmListElementsUml<?, ?, ?, ?, FileAndWriter, ?> asmListElementsUml : asmDiagramUml.getAssembliesListElementsUml()) {
        asmListElementsUml.persist(asmDiagramUml.getPersistInstrument());
      }
      bf.write(toEndElement(getNamePersistable()));
      bf.flush();
      bf.close();
      asmDiagramUml.getHolderApp().getPaneDrawing().saveCanvasAsImage(asmDiagramUml.getPersistInstrument().getFile().getAbsolutePath()
          .replace("."+getDiagramFileExtension(), ""));
    } 
    catch (Exception e) {
      if(bf != null) {
        bf.close();
      }
      throw new Exception(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void restore(
      IAsmDiagramUml<DUML, ?, ?, ?, ?, FileAndWriter> assemblyDiagramUml)
      throws Exception {
    try {
      XMLReader xr = XMLReaderFactory.createXMLReader();
      getSaxModelFiller().setModelAndPrepare(assemblyDiagramUml);
      xr.setContentHandler(getSaxReader());
      xr.setErrorHandler(getSaxReader());
      FileReader fr = new FileReader(assemblyDiagramUml.getPersistInstrument().getFile());
      xr.parse(new InputSource(fr));
      assemblyDiagramUml.getHolderApp().getPaneDrawing().getSrvPaneDrawing().handleChangesIndexZ();
    } catch (Exception e) {
      System.err.println("!!!Can't parse XML " + assemblyDiagramUml.getPersistInstrument().getFile());
      throw new Exception(e);
    }
  }

  @Override
  public String getNameUmlDiagram() {
    return getNamePersistable();
  }

  @Override
  public String getDiagramFileExtension() {
    return diagramFileExtension;
  }
  
  @SuppressWarnings("rawtypes")
  public ASaxModelFiller getSaxModelFiller() {
    return saxModelFiller;
  }

  public SaxReader getSaxReader() {
    return saxReader;
  }

  public SrvSaveXmlDiagramUml<DUML> getSrvSaveXmlDiagramUml() {
    return srvSaveXmlDiagramUml;
  }
}
