/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.graphic.model.EMeasurementUnit;
import org.beigesoft.graphic.pojo.Joint2D;
import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.graphic.service.persist.SrvSaveXmlJoint2D;
import org.beigesoft.graphic.service.persist.SrvSaveXmlPoint2D;
import org.beigesoft.uml.app.assembly.AsmProjectUml;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.model.EJointSide;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.model.EVisibilityKind;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.MultiplicityElement;
import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.pojo.ParameterMethod;
import org.beigesoft.uml.pojo.CommentRelationship;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.persist.xmllight.ASrvSaveXmlRelationship;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlProjectUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlAttributeClass;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlClassUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlComment;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlCommentRelationship;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramClass;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlMultiplicityElement;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlRelationshipBinary;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlShapeRelationship;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlText;

/**
 * <p>UML XSD scheme creator</p>
 * 
 * @author Yury Demidenko
 */
public class CreatorXsdUml {//TODO re-do
  
  private static final String UML_PREFIX = "bsuml";

  public static final String DESCRIPTOR_FILE_NAME = "bs-uml.xml";
    
  public static final String UML_PROJECT_XMLSCHEMA_FILENAME = "bs-uml-project.xsd";

  public static final String URI_UML_PROJECT_XMLSCHEMA = "http://www.beigesoft.org/xsd/" + UML_PROJECT_XMLSCHEMA_FILENAME;
  
  public static final String UML_XMLSCHEMA_FILENAME = "bs-uml.xsd";

  public static final String URI_UML_XMLSCHEMA = "http://www.beigesoft.org/xsd/" + UML_XMLSCHEMA_FILENAME;
  
  public static final String NAMEXML_DIAGRAMCLASS = "diagramClass";
  
  public static final String DIAGRAM_FILE_EXTENSION = "dcl.xml";
  
  public static void main(String[] args) {
    createUmlProjectChema();
    createUmlDiagramChema();
  }

  public static void createUmlProjectChema() {
    File xsdFile = new File(UML_PROJECT_XMLSCHEMA_FILENAME);
    FileOutputStream fos = null;
    try {
      if(!xsdFile.exists())
        xsdFile.createNewFile();
      fos = new FileOutputStream(xsdFile);
      XMLOutputFactory xmlOutFact = XMLOutputFactory.newInstance();
      XMLStreamWriter writer = xmlOutFact.createXMLStreamWriter(fos);
      writer.writeStartDocument();
      writer.writeCharacters("\n");
      writer.writeStartElement("schema");
      writer.writeDefaultNamespace("http://www.w3.org/2001/XMLSchema");
      writer.writeNamespace("html", "http://www.w3.org/1999/xhtml");
      writer.writeAttribute("targetNamespace", URI_UML_PROJECT_XMLSCHEMA);
      writer.writeNamespace(UML_PREFIX, URI_UML_PROJECT_XMLSCHEMA);
      writer.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
      writer.writeAttribute("xsi:schemaLocation", "http://www.w3.org/1999/xhtml \n"
          + " http://www.w3.org/MarkUp/SCHEMA/xhtml11.xsd");
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeEmptyElement("import");
      writer.writeAttribute("namespace", "http://www.w3.org/1999/xhtml");
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");

      writer.writeStartElement("annotation");
      writer.writeCharacters("\n");
      writer.writeStartElement("documentation");
      writer.writeCharacters("\n");
      writer.writeStartElement("html:p");
      writer.writeCharacters("Beigesoft UML project descriptor");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeStartElement("element");
      writer.writeAttribute("name", AsmProjectUml.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvPersistLightXmlProjectUml.JAVA_SOURCE_PATH);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvPersistLightXmlProjectUml.IS_USE_JAVA_LANG_OBJECT);
      writer.writeAttribute("type", "boolean");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvPersistLightXmlProjectUml.IS_RETRIEVE_NONPUBLIC);
      writer.writeAttribute("type", "boolean");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.MARGIN_DIAGRAM);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.GAP_DIAGRAM);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.WIDTH_END_RELATION);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.SELECT_APPROXIMATION);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.WIDTH_DRAG_RECTANGLE);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.HEIGHT_CLASS_HEAD);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.HEIGHT_ATTRIBUTE_CLASS);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.MARGIN_ELEMENT);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.HEIGHT_EMPTY_ATTRIBUTES);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.WIDTH_COMMENT);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.HEIGHT_MIN_COMMENT);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.MEASUREMENT_UNIT);
      writer.writeAttribute("type", UML_PREFIX + ":" + EMeasurementUnit.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeStartElement("simpleType");
      writer.writeAttribute("name", EMeasurementUnit.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("restriction");
      writer.writeAttribute("base", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EMeasurementUnit.INCH.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EMeasurementUnit.CENTIMETRE.name());
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
     
     writer.writeEndElement();
      writer.flush();
      System.out.println("It's created file - " + xsdFile.getAbsolutePath());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if(fos != null)
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }       
  }

  public static void createUmlDiagramChema() {
    File xsdFile = new File(UML_XMLSCHEMA_FILENAME);
    FileOutputStream fos = null;
    try {
      if(!xsdFile.exists())
        xsdFile.createNewFile();
      fos = new FileOutputStream(xsdFile);
      XMLOutputFactory xmlOutFact = XMLOutputFactory.newInstance();
      XMLStreamWriter writer = xmlOutFact.createXMLStreamWriter(fos);
      writer.writeStartDocument();
      writer.writeCharacters("\n");
      writer.writeStartElement("schema");
      writer.writeDefaultNamespace("http://www.w3.org/2001/XMLSchema");
      writer.writeNamespace("html", "http://www.w3.org/1999/xhtml");
      writer.writeAttribute("targetNamespace", URI_UML_XMLSCHEMA);
      writer.writeNamespace(UML_PREFIX, URI_UML_XMLSCHEMA);
      writer.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
      writer.writeAttribute("xsi:schemaLocation", "http://www.w3.org/1999/xhtml \n"
          + " http://www.w3.org/MarkUp/SCHEMA/xhtml11.xsd");
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeEmptyElement("import");
      writer.writeAttribute("namespace", "http://www.w3.org/1999/xhtml");
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");

      writer.writeStartElement("annotation");
      writer.writeCharacters("\n");
      writer.writeStartElement("documentation");
      writer.writeCharacters("\n");
      writer.writeStartElement("html:p");
      writer.writeCharacters("Beigesoft UML diagram descriptor");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeStartElement("element");
      writer.writeAttribute("name", SrvSaveXmlDiagramClass.NAMEXML_DIAGRAMCLASS);
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("ref", UML_PREFIX + ":" + SrvSaveXmlClassUml.class.getSimpleName());
      writer.writeAttribute("minOccurs", "0");
      writer.writeAttribute("maxOccurs", "unbounded");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("ref", UML_PREFIX + ":" + RelationshipBinary.class.getSimpleName());
      writer.writeAttribute("minOccurs", "0");
      writer.writeAttribute("maxOccurs", "unbounded");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("ref", UML_PREFIX + ":" + SrvSaveXmlComment.class.getSimpleName());
      writer.writeAttribute("minOccurs", "0");
      writer.writeAttribute("maxOccurs", "unbounded");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("ref", UML_PREFIX + ":" + TextUml.class.getSimpleName());
      writer.writeAttribute("minOccurs", "0");
      writer.writeAttribute("maxOccurs", "unbounded");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlDiagramClass.NAMEXML_ISABLETOCHANGEBYDOCLET);
      writer.writeAttribute("type", "boolean");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SettingsGraphicUml.MEASUREMENT_UNIT);
      writer.writeAttribute("type", UML_PREFIX + ":" + EMeasurementUnit.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeStartElement("simpleType");
      writer.writeAttribute("name", EMeasurementUnit.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("restriction");
      writer.writeAttribute("base", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EMeasurementUnit.INCH.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EMeasurementUnit.CENTIMETRE.name());
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
     
      writer.writeStartElement("element");
      writer.writeAttribute("name", SrvSaveXmlClassUml.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", ASrvSaveXmlShape.POINT_START);
      writer.writeAttribute("type", UML_PREFIX + ":" + SrvSaveXmlPoint2D.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", ASrvSaveXmlShape.WIDTH);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", ASrvSaveXmlShape.HEIGHT);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlClassUml.NAMEXML_ISMAIN);
      writer.writeAttribute("type", "boolean");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("ref", UML_PREFIX + ":" + AttributeClass.class.getSimpleName());
      writer.writeAttribute("minOccurs", "0");
      writer.writeAttribute("maxOccurs", "unbounded");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("ref", UML_PREFIX + ":" + MethodClass.class.getSimpleName());
      writer.writeAttribute("minOccurs", "0");
      writer.writeAttribute("maxOccurs", "unbounded");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlClassUml.NAMEXML_ITSNAME);
      writer.writeAttribute("type", "string");
      writer.writeAttribute("use", "required");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlClassUml.NAMEXML_NAMEPACKAGE);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
           
      writer.writeStartElement("element");
      writer.writeAttribute("name", TextUml.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", ASrvSaveXmlShape.POINT_START);
      writer.writeAttribute("type", UML_PREFIX + ":" + SrvSaveXmlPoint2D.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlText.NAMEXML_ITSTEXT);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", ASrvSaveXmlShape.TIED_SHAPE);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
                 
      writer.writeStartElement("element");
      writer.writeAttribute("name", SrvSaveXmlComment.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", ASrvSaveXmlShape.POINT_START);
      writer.writeAttribute("type", UML_PREFIX + ":" + SrvSaveXmlPoint2D.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", ASrvSaveXmlShape.WIDTH);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", ASrvSaveXmlShape.HEIGHT);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlText.NAMEXML_ITSTEXT);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlComment.NAMEXML_ISDASHEDRELATIONS);
      writer.writeAttribute("type", "boolean");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlComment.NAMEXML_HASBORDER);
      writer.writeAttribute("type", "boolean");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", ASrvSaveXmlShape.TIED_SHAPE);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("ref", UML_PREFIX + ":" + CommentRelationship.class.getSimpleName());
      writer.writeAttribute("minOccurs", "0");
      writer.writeAttribute("maxOccurs", "unbounded");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
           
      writer.writeStartElement("element");
      writer.writeAttribute("name", CommentRelationship.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", UtilsRectangleRelationship.NAMEXML_SIDEJOINT);
      writer.writeAttribute("type", UML_PREFIX+":" + EJointSide.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", UtilsRectangleRelationship.NAMEXML_SIDELENGTH);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlCommentRelationship.POINT_END);
      writer.writeAttribute("type", UML_PREFIX + ":" + SrvSaveXmlPoint2D.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeStartElement("complexType");
      writer.writeAttribute("name", SrvSaveXmlPoint2D.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlPoint2D.X);
      writer.writeAttribute("type", "double");
      writer.writeAttribute("use", "required");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlPoint2D.Y);
      writer.writeAttribute("type", "double");
      writer.writeAttribute("use", "required");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
     
      writer.writeStartElement("element");
      writer.writeAttribute("name", MethodClass.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlClassUml.NAMEXML_VISIBILITYKIND);
      writer.writeAttribute("type", UML_PREFIX + ":" + EVisibilityKind.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlAttributeClass.NAMEXML_ITSTYPE);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("ref", UML_PREFIX + ":" + ParameterMethod.class.getSimpleName());
      writer.writeAttribute("minOccurs", "0");
      writer.writeAttribute("maxOccurs", "unbounded");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlClassUml.NAMEXML_ITSNAME);
      writer.writeAttribute("type", "string");
      writer.writeAttribute("use", "required");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeStartElement("element");
      writer.writeAttribute("name", ParameterMethod.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlAttributeClass.NAMEXML_ITSTYPE);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlClassUml.NAMEXML_ITSNAME);
      writer.writeAttribute("type", "string");
      writer.writeAttribute("use", "required");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeStartElement("element");
      writer.writeAttribute("name", AttributeClass.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlClassUml.NAMEXML_VISIBILITYKIND);
      writer.writeAttribute("type", UML_PREFIX + ":" + EVisibilityKind.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlAttributeClass.NAMEXML_ITSTYPE);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlAttributeClass.NAMEXML_DEFAULTVALUE);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlAttributeClass.NAMEXML_CONSTRAINTSVALUE);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("ref", UML_PREFIX+":"+MultiplicityElement.class.getSimpleName());
      writer.writeAttribute("minOccurs", "0");
      writer.writeAttribute("maxOccurs", "1");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlClassUml.NAMEXML_ITSNAME);
      writer.writeAttribute("type", "string");
      writer.writeAttribute("use", "required");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeStartElement("element");
      writer.writeAttribute("name", MultiplicityElement.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlMultiplicityElement.NAMEXML_ISORDERED);
      writer.writeAttribute("type", "boolean");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlMultiplicityElement.NAMEXML_ISUNIQUE);
      writer.writeAttribute("type", "boolean");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlMultiplicityElement.NAMEXML_LOWER);
      writer.writeAttribute("type", "integer");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlMultiplicityElement.NAMEXML_UPPER);
      writer.writeAttribute("type", "integer");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeStartElement("simpleType");
      writer.writeAttribute("name", EVisibilityKind.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("restriction");
      writer.writeAttribute("base", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EVisibilityKind.PACKAGE.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EVisibilityKind.PRIVATE.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EVisibilityKind.PROTECTED.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EVisibilityKind.PUBLIC.name());
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
     
      writer.writeStartElement("element");
      writer.writeAttribute("name", RelationshipBinary.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", ASrvSaveXmlRelationship.NAMEXML_KINDRELATIONSHIP);
      writer.writeAttribute("type", UML_PREFIX+":" + ERelationshipKind.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", ASrvSaveXmlRelationship.NAMEXML_SHAREDJOINT);
      writer.writeAttribute("type", UML_PREFIX+":" + Joint2D.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("ref", UML_PREFIX+":" + RectangleRelationship.class.getSimpleName());
      writer.writeAttribute("minOccurs", "0");
      writer.writeAttribute("maxOccurs", "unbounded");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeStartElement("complexType");
      writer.writeAttribute("name", Joint2D.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlPoint2D.X);
      writer.writeAttribute("type", "double");
      writer.writeAttribute("use", "required");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlPoint2D.Y);
      writer.writeAttribute("type", "double");
      writer.writeAttribute("use", "required");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlJoint2D.JOINT_TYPE);
      writer.writeAttribute("type", UML_PREFIX + ":" + EJoint2DType.class.getSimpleName());
      writer.writeAttribute("use", "required");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
     
      writer.writeStartElement("simpleType");
      writer.writeAttribute("name", EJoint2DType.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("restriction");
      writer.writeAttribute("base", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EJoint2DType.POINT.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EJoint2DType.BUS_X.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EJoint2DType.BUS_Y.name());
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
     
      writer.writeStartElement("simpleType");
      writer.writeAttribute("name", ERelationshipKind.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("restriction");
      writer.writeAttribute("base", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipKind.ASSOCIATION.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipKind.AGGREGATION.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipKind.COMPOSITION.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipKind.GENERALIZATION.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipKind.IMPORT.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipKind.INTERFACE_REALIZATION.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipKind.IMPORT.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipKind.MERGE.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipKind.REALIZATION.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipKind.SUBSTITUTION.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipKind.USAGE.name());
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
     
      writer.writeStartElement("element");
      writer.writeAttribute("name", RectangleRelationship.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("complexType");
      writer.writeCharacters("\n");
      writer.writeStartElement("sequence");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", UtilsRectangleRelationship.NAMEXML_SIDEJOINT);
      writer.writeAttribute("type", UML_PREFIX+":" + EJointSide.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", UtilsRectangleRelationship.NAMEXML_SIDELENGTH);
      writer.writeAttribute("type", "double");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlRelationshipBinary.NAMEXML_CLASSRELATIONSHIPEND);
      writer.writeAttribute("type", UML_PREFIX+":" + ERelationshipEndType.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("element");
      writer.writeAttribute("name", SrvSaveXmlShapeRelationship.NAMEXML_ISOWNED);
      writer.writeAttribute("type", "boolean");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlClassUml.NAMEXML_ITSNAME);
      writer.writeAttribute("type", "string");
      writer.writeAttribute("use", "required");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("attribute");
      writer.writeAttribute("name", SrvSaveXmlClassUml.NAMEXML_NAMEPACKAGE);
      writer.writeAttribute("type", "string");
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
      
      writer.writeStartElement("simpleType");
      writer.writeAttribute("name", ERelationshipEndType.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("restriction");
      writer.writeAttribute("base", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipEndType.END.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipEndType.NON_NAVIGABLE.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipEndType.UNSPECIFIED.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", ERelationshipEndType.ANOTHER_END.name());
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
     
      writer.writeStartElement("simpleType");
      writer.writeAttribute("name", EJointSide.class.getSimpleName());
      writer.writeCharacters("\n");
      writer.writeStartElement("restriction");
      writer.writeAttribute("base", "string");
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EJointSide.LEFT.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EJointSide.TOP.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EJointSide.RIGHT.name());
      writer.writeCharacters("\n");
      writer.writeEmptyElement("enumeration");
      writer.writeAttribute("value", EJointSide.BOTTOM.name());
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeCharacters("\n");
     
      writer.writeEndElement();
      writer.flush();
      System.out.println("It's created file - " + xsdFile.getAbsolutePath());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if(fos != null)
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }   
  }
  
  public static void writeAttributeOrNothingIfNull(XMLStreamWriter writer,
      String attrName, String attrValue) throws XMLStreamException {
    if(attrValue == null || attrValue.trim().length() == 0)
      return;
    writer.writeAttribute(attrName, attrValue);
  }

  public static void writeValueOrNull(XMLStreamWriter writer, Object value) throws XMLStreamException {
    if(value == null)
      writer.writeEmptyElement("Null");
    else
      writer.writeCharacters(value.toString());
  }

  public static void writeEnumNameOrNull(XMLStreamWriter writer,
      Enum<?> value) throws XMLStreamException {
    if(value == null)
      writer.writeEmptyElement("Null");
    else
      writer.writeCharacters(value.name());    
  }
}
