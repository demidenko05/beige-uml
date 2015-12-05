/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.beigesoft.service.ISrvPersistSimple;
import org.beigesoft.service.persist.xml.ASrvSaveXmlBase;
import org.beigesoft.service.persist.xml.SaxReader;
import org.beigesoft.uml.app.model.ProjectUml;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import static org.beigesoft.uml.service.CreatorXsdUml.*;

/**
 * <p>Light-weight(memory friendly) persist XML service.
 * It use simple java File writer to persist
 * and SAX to restore</p>
 * 
 * @author Yury Demidenko
 */
public class SrvPersistLightXmlProjectUml<M extends ProjectUml> extends ASrvSaveXmlBase implements ISrvPersistSimple<M> {

  public static final String NAMEXML_PROJECTDESCRIPTOR = "projectDescriptor";
  
  public static final String IS_USE_JAVA_LANG_OBJECT = "isUseJavaLangObject";
  
  public static final String IS_RETRIEVE_NONPUBLIC = "isRetrieveNonpublic";

  public static final String JAVA_SOURCE_PATH = "javaSourceFilePath";
  
  public static final String IS_USE_GENERICS_FOR_GENERATE_FROM_CLASS = "isUseGenericForGenerateFromJavaClass";
  
  private final SaxProjectUmlFiller<M> saxModelFiller;
  
  private final SaxReader saxReader;
  
  public SrvPersistLightXmlProjectUml(String namePersistable, SaxProjectUmlFiller<M> saxModelFiller) {
    super(namePersistable);
    this.saxModelFiller = saxModelFiller;
    saxReader = new SaxReader(saxModelFiller);
  }

  @Override
  public void persist(M pr)
      throws Exception {
    File projectDir = new File(pr.getPathPre() + File.separator + pr.getItsName());
    if(!projectDir.exists()) { 
      if(!projectDir.mkdir()) {
        throw new Exception("Can not create dir " + projectDir.getAbsolutePath());
      }
    }
    File projectDescriptor = new File(pr.getPathPre() + File.separator + pr.getItsName() + File.separator+DESCRIPTOR_FILE_NAME);
    if(!projectDescriptor.exists()) {
      projectDescriptor.createNewFile();
    }
    BufferedWriter bf = null;
    try {
      bf = new BufferedWriter(new FileWriter(projectDescriptor));
      bf.write(getStartXmlAndNewLine());
      bf.write(toStartElementOpened(getNamePersistable()) + "xmlns=\"" + URI_UML_PROJECT_XMLSCHEMA + "\">\n");
      bf.write(toStartElement(SettingsGraphicUml.MEASUREMENT_UNIT) +
          pr.getSettingsGraphicUml().getMeasurementUnit().name() +
          toEndElementAndNewLine(SettingsGraphicUml.MEASUREMENT_UNIT));
      bf.write(toStartElement(JAVA_SOURCE_PATH) + toStringOrNull(pr.getJavaSourceFilePath()) + 
          toEndElementAndNewLine(JAVA_SOURCE_PATH));
      bf.write(toStartElement(IS_USE_JAVA_LANG_OBJECT) + 
          Boolean.valueOf(pr.getIsUseJavaLangObject()).toString() + toEndElementAndNewLine(IS_USE_JAVA_LANG_OBJECT));
      bf.write(toStartElement(IS_USE_GENERICS_FOR_GENERATE_FROM_CLASS) +
          Boolean.valueOf(pr.getIsUseGenericForGenerateFromJavaClass()).toString() +
          toEndElementAndNewLine(IS_USE_GENERICS_FOR_GENERATE_FROM_CLASS));
      bf.write(toStartElement(IS_RETRIEVE_NONPUBLIC) +
          Boolean.valueOf(pr.getIsRetrieveNonpublic()).toString() + toEndElementAndNewLine(IS_RETRIEVE_NONPUBLIC));
      bf.write(toStartElement(SettingsGraphicUml.MARGIN_DIAGRAM) +
          Double.valueOf(pr.getSettingsGraphicUml().getMarginDiagram()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.MARGIN_DIAGRAM));
      bf.write(toStartElement(SettingsGraphicUml.GAP_DIAGRAM) +
          Double.valueOf(pr.getSettingsGraphicUml().getGapDiagram()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.GAP_DIAGRAM));
      bf.write(toStartElement(SettingsGraphicUml.WIDTH_END_RELATION) +
          Double.valueOf(pr.getSettingsGraphicUml().getWidthEndRelation()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.WIDTH_END_RELATION));
      bf.write(toStartElement(SettingsGraphicUml.SELECT_APPROXIMATION) +
          Double.valueOf(pr.getSettingsGraphicUml().getSelectApproximation()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.SELECT_APPROXIMATION));
      bf.write(toStartElement(SettingsGraphicUml.WIDTH_DRAG_RECTANGLE) +
          Double.valueOf(pr.getSettingsGraphicUml().getWidthDragRectangle()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.WIDTH_DRAG_RECTANGLE));
      bf.write(toStartElement(SettingsGraphicUml.HEIGHT_CLASS_HEAD) +
          Double.valueOf(pr.getSettingsGraphicUml().getHeightHeadClass()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.HEIGHT_CLASS_HEAD));
      bf.write(toStartElement(SettingsGraphicUml.HEIGHT_ATTRIBUTE_CLASS) +
          Double.valueOf(pr.getSettingsGraphicUml().getHeightAttributeClass()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.HEIGHT_ATTRIBUTE_CLASS));
      bf.write(toStartElement(SettingsGraphicUml.MARGIN_ELEMENT) +
          Double.valueOf(pr.getSettingsGraphicUml().getMarginElement()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.MARGIN_ELEMENT));
      bf.write(toStartElement(SettingsGraphicUml.HEIGHT_EMPTY_ATTRIBUTES) +
          Double.valueOf(pr.getSettingsGraphicUml().getHeightEmptyAttributes()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.HEIGHT_EMPTY_ATTRIBUTES));
      bf.write(toStartElement(SettingsGraphicUml.LENGTH_SELF_RELATION) +
          Double.valueOf(pr.getSettingsGraphicUml().getLengthSelfRelation()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.LENGTH_SELF_RELATION));
      bf.write(toStartElement(SettingsGraphicUml.WIDTH_COMMENT) +
          Double.valueOf(pr.getSettingsGraphicUml().getWidthComment()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.WIDTH_COMMENT));
      bf.write(toStartElement(SettingsGraphicUml.HEIGHT_MIN_COMMENT) +
          Double.valueOf(pr.getSettingsGraphicUml().getHeightMinComment()).toString() +
          toEndElementAndNewLine(SettingsGraphicUml.HEIGHT_MIN_COMMENT));
      bf.write(toEndElement(getNamePersistable()));
      bf.flush();
      bf.close();
    } 
    catch (Exception e) {
      if(bf != null) {
        bf.close();
      }
      throw new Exception(e);
    }
  }

  @Override
  public void restore(M pr) throws Exception {
    File projectDescriptor = new File(pr.getPathPre() + File.separator + pr.getItsName() + 
        File.separator + DESCRIPTOR_FILE_NAME);
    XMLReader xr = XMLReaderFactory.createXMLReader();
    getSaxModelFiller().setModelAndPrepare(pr);
    xr.setContentHandler(getSaxReader());
    xr.setErrorHandler(getSaxReader());
    FileReader fr = new FileReader(projectDescriptor);
    xr.parse(new InputSource(fr));
  }

  public SaxProjectUmlFiller<M> getSaxModelFiller() {
    return saxModelFiller;
  }

  public SaxReader getSaxReader() {
    return saxReader;
  }
}
