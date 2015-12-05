/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.service.swing;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.io.nonstatic.SrvFile;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.uml.app.model.ProjectUml;
import org.beigesoft.uml.app.swing.GuiMainUmlUninteractive;
import org.beigesoft.uml.assembly.AsmElementUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.ClassRelationFull;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.model.EVisibilityKind;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.pojo.ParameterMethod;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.service.UtilsUml;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramClass;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.LanguageVersion;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.RootDoc;
import com.sun.tools.doclets.standard.Standard;

/**
 * Overriding standard Doclet with additional UML class diagrams
 * 
 * @author Yury Demidenko
 */
public class CreatorDoclet {
  
  private static final String JAVADOC_CONTENT_FOR_INSERT = "<div class=\"description\">";

  public static final String OPTION_PATH_UML_PROJECT = "-umlprojectpath";
  
  private static String pathUmlProject;
    
  private static String pathMavenProject;

  private static String pathJavaDoc;
  
  private static ProjectUml umlProject;
  
  private static GuiMainUmlUninteractive mainGui;
  
  protected static final SrvFile srvFile = new SrvFile();

  /**
   * Standard doclet start method
   * @param root
   * @return
   * @throws Exception 
   */
  public static boolean start(RootDoc root) throws Exception {
    umlProject = null;
    mainGui = null;
    Standard.start(root);
    setOptions(root);
    openOrCreateUmlProject(root);
    generateUml(root);
    return true;
  }

  /**
   * Standard doclet method
   * must be present!
   * @param option
   * @return
   */
  public static int optionLength(String option) {
    int result = Standard.optionLength(option);
    if(result == 0) {
      if(option.equals(OPTION_PATH_UML_PROJECT)) {
        result = 2;
      }
    }
    return result;
  }
  
  /**
   * Standard doclet method
   * must be present to support generics!
   * @return
   */
  public static LanguageVersion languageVersion() {
    return Standard.languageVersion();
  }
  
  protected static void generateUml(RootDoc root) throws Exception {
    mainGui.getFactoryGuiMain().getFrameMain().setVisible(true);
    for(ClassDoc classDoc : root.classes()) {
      alterHtmlForClass(classDoc, root);
    }
    mainGui.getFactoryGuiMain().getFrameMain().setVisible(false);
    mainGui.getFactoryGuiMain().getFrameMain().dispose();
  }

  protected static void alterHtmlForClass(ClassDoc classDoc, RootDoc root) throws Exception {
    String classFileName = classDoc.containingPackage().name().replace(".", "/") +
        File.separator + classDoc.name();
    File diagramPictureInUmlProject = new File(pathUmlProject, classFileName + ".png");
    //1. get or make UML diagram:
    File umlDiagramFile = new File(pathUmlProject, classFileName + "." + SrvSaveXmlDiagramClass.DIAGRAM_FILE_EXTENSION);
    FileAndWriter pi = new FileAndWriter();
    pi.setFile(umlDiagramFile);
    if(umlDiagramFile.exists()) {
      mainGui.getFactoryAppUml().getAsmDiagramClass().openDiagram(pi);
    }
    else {
      File dir = umlDiagramFile.getParentFile();
      if(dir != null && !dir.exists()) {
        dir.mkdirs();
      }
    }
    if(mainGui.getFactoryAppUml().getAsmDiagramClass().getDiagramUml().getIsAbleToChangeByDoclet() || 
        !diagramPictureInUmlProject.exists()) {
      mainGui.getFactoryAppUml().getAsmDiagramClass().newDiagramUml(pi);
      EClassKind classKind = classDoc.isEnum() ? EClassKind.ENUM : 
        (classDoc.isInterface() ? EClassKind.INTERFACE : EClassKind.CLASS);
      AsmElementUml<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> mainAsmClassUml = mainGui.getFactoryAppUml().
          getFactoryAsmClassFull().createAsmElementUml();
      mainAsmClassUml.getElementUml().getShape().setClassKind(classKind);
      mainAsmClassUml.getElementUml().getShape().setIsMain(true);
      fillUmlClass(classDoc, mainAsmClassUml.getElementUml());
      mainGui.getFactoryAppUml().getAsmDiagramClass().getAsmListAsmClassesFull().addElementUml(mainAsmClassUml);
      if(classDoc.superclass() != null) {
        addRelatedClass(mainAsmClassUml.getElementUml(), classDoc.superclass(), ERelationshipKind.GENERALIZATION, false);
      }
      if(classDoc.interfaces() != null) {
        for(ClassDoc docletInterface : classDoc.interfaces()) {
          addRelatedClass(mainAsmClassUml.getElementUml(), docletInterface, ERelationshipKind.INTERFACE_REALIZATION, false);
        }
      }
      fillSubclasses(mainAsmClassUml.getElementUml(), classDoc, root);
      mainGui.getFactoryAppUml().getAsmDiagramClass().rearrange();
      mainGui.getPaneDrawing().repaintForced();
      mainGui.getFactoryAppUml().getAsmDiagramClass().saveDiagram();
    }
    //2. alter javadoc:
    File diagramPictureInDoclet = new File(pathJavaDoc, classFileName + ".png");
    srvFile.copyFile(diagramPictureInUmlProject, diagramPictureInDoclet);
    File original = new File(pathJavaDoc, classFileName + ".html");
    File altered = new File(pathJavaDoc, classFileName + ".alt");
    BufferedReader readerOriginal = new BufferedReader(new InputStreamReader(
        new FileInputStream(original)));
    BufferedWriter writerAltered = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(altered)));  
    String line;
    boolean itWasAltered = false;
    File umlDiagramMapFile = new File(pathUmlProject, classFileName + ".map");
    while ((line = readerOriginal.readLine()) != null) {
      writerAltered.write(line);
      writerAltered.newLine();
      if(line.contains(JAVADOC_CONTENT_FOR_INSERT)) {
        if(umlDiagramMapFile.exists()) {
          insertUmlDiagramMap(writerAltered, umlDiagramMapFile);
        }
        String strPicture = "<p align=\"center\"><img src=\"" + 
            diagramPictureInDoclet.getName() + "\" usemap=\"#umlclassmap\" /></p>";
        writerAltered.write(strPicture);
        writerAltered.newLine();
        itWasAltered = true;
      }
    }
    readerOriginal.close();
    writerAltered.close();
    original.delete();
    altered.renameTo(original);
    if(!itWasAltered) {
      throw new Exception("Can't alter javadoc! Can't find entry " + JAVADOC_CONTENT_FOR_INSERT);
    }
  }

  protected static void insertUmlDiagramMap(BufferedWriter writerAltered,
      File umlDiagramMapFile) throws Exception {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(
          new FileInputStream(umlDiagramMapFile)));
      String line;
      while ((line = reader.readLine()) != null) {
        writerAltered.write(line);
        writerAltered.newLine();
      }
    } finally {
      if(reader != null) {
        reader.close();
      }
    }
  }

  protected static void fillSubclasses(ClassFull<ClassUml> mainClass, 
      ClassDoc classDoc, RootDoc root) {
    for(ClassDoc docletClass : root.classes()) {
      if(docletClass.superclass() == classDoc) {
        addRelatedClass(mainClass, docletClass, ERelationshipKind.GENERALIZATION, true);
      }
      else if(docletClass.interfaces() != null) {
        for(ClassDoc docletInterface : docletClass.interfaces()) {
          if(docletInterface == classDoc) {
            addRelatedClass(mainClass, docletClass, ERelationshipKind.INTERFACE_REALIZATION, true);
            break;
          }
        }
      }
    }   
  }

  protected static void addRelatedClass(ClassFull<ClassUml> mainAsmClassUml,
      ClassDoc relatedClassDoc, ERelationshipKind relationKind, boolean isMainClassEnd) {
    EClassKind classKind = relatedClassDoc.isEnum() ? EClassKind.ENUM : 
      (relatedClassDoc.isInterface() ? EClassKind.INTERFACE : EClassKind.CLASS);
    AsmElementUml<ClassFull<ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> relatedAsmClassUml = mainGui.getFactoryAppUml().
          getFactoryAsmClassFull().createAsmElementUml();
    relatedAsmClassUml.getElementUml().getShape().setClassKind(classKind);
    fillUmlClass(relatedClassDoc, relatedAsmClassUml.getElementUml());
    mainGui.getFactoryAppUml().getAsmDiagramClass().getAsmListAsmClassesFull().addElementUml(relatedAsmClassUml);
    AsmElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Graphics2D, SettingsDraw, FileAndWriter> asmRelationUml = mainGui.getFactoryAppUml().getFactoryAsmRelationshipBinaryClass().createAsmElementUml();
    asmRelationUml.getElementUml().setKind(relationKind);
    asmRelationUml.getElementUml().getShapeRelationshipStart().setShapeFull(mainAsmClassUml);
    if(isMainClassEnd) {
      asmRelationUml.getElementUml().getShapeRelationshipStart().setEndType(ERelationshipEndType.END);
    }
    mainAsmClassUml.getRelationshipsBinary().add(new ClassRelationFull<ClassUml>(asmRelationUml.getElementUml().getShapeRelationshipStart(), asmRelationUml.getElementUml()));
    asmRelationUml.getElementUml().getShapeRelationshipEnd().setShapeFull(relatedAsmClassUml.getElementUml());
    if(!isMainClassEnd) {
      asmRelationUml.getElementUml().getShapeRelationshipEnd().setEndType(ERelationshipEndType.END);
    }
    relatedAsmClassUml.getElementUml().getRelationshipsBinary().add(new ClassRelationFull<ClassUml>(asmRelationUml.getElementUml().getShapeRelationshipEnd(), asmRelationUml.getElementUml()));
    mainGui.getFactoryAppUml().getAsmDiagramClass().getAsmListAsmRelationshipsBinaryClass().addElementUml(asmRelationUml);
    UtilsUml.evalJointPoints(mainAsmClassUml);
    UtilsUml.evalJointPoints(relatedAsmClassUml.getElementUml());
  }

  protected static void fillUmlClass(ClassDoc classDoc,
      ClassFull<ClassUml> classUml) {
    classUml.getShape().setItsName(classDoc.name());
    classUml.getShape().setNamePackage(classDoc.containingPackage().name());
    if(classDoc.isEnum()) {
      for(FieldDoc docletField : classDoc.enumConstants()) {
        AttributeClass attributeClass = new AttributeClass();
        attributeClass.setItsName(docletField.name());
        attributeClass.setVisibilityKind(EVisibilityKind.PACKAGE);//Default to prevent print sign [+]
        classUml.getShape().getAttributes().add(attributeClass);
      }
    }
    for(FieldDoc docletField : classDoc.fields()) {
      AttributeClass attributeClass = new AttributeClass();
      attributeClass.setItsName(docletField.name());
      attributeClass.setItsType(docletField.type().typeName());
      attributeClass.setVisibilityKind(UtilsUml.evalVisibilityKindByModifier(docletField.modifierSpecifier()));
      classUml.getShape().getAttributes().add(attributeClass);
    }
    for(MethodDoc methDoc : classDoc.methods()) {
      MethodClass oper = new MethodClass();
      oper.setItsName(methDoc.name());
      oper.setItsType(toGoodTypeName(methDoc.returnType().toString()));
      oper.setVisibilityKind(UtilsUml.evalVisibilityKindByModifier(methDoc.modifierSpecifier()));
      if(methDoc.parameters() != null) {
        for(Parameter paramDoc : methDoc.parameters()) {
          ParameterMethod param = new ParameterMethod();
          param.setItsName(paramDoc.name());
          param.setItsType(toGoodTypeName(paramDoc.type().toString()));
          oper.getParameters().add(param);
        }
      }
      classUml.getShape().getMethods().add(oper);
    }
  }

  /**
   * e.g. to convert java.lang.String[] to String[]
   * @param typeName
   * @return
   */
  protected static String toGoodTypeName(String typeName) {
    int lastDotPosition = typeName.lastIndexOf(".");
    if(lastDotPosition == -1) {
      return typeName;
    }
    else {
      return typeName.substring(lastDotPosition + 1);
    }
  }

  protected static void openOrCreateUmlProject(RootDoc root) throws Exception {
    File umlProjectDir = new File(pathUmlProject);
    if(!umlProjectDir.exists()) {
      umlProjectDir.mkdirs();
    }
    if(!umlProjectDir.exists()) {
      throw new Exception("Can not create UML project dir " + umlProjectDir.getAbsolutePath());
    }
    mainGui = new GuiMainUmlUninteractive();
    umlProject = new ProjectUml(mainGui.getSettingsGraphicUml(), 
        umlProjectDir.getAbsolutePath().
        replace(File.separator + umlProjectDir.getName(), ""),
        umlProjectDir.getName());
    mainGui.getAsmProjectUml().setProjectUml(umlProject);
    if(umlProject.getIsNew()) {
      mainGui.getAsmProjectUml().persist();
    }
    else {
      mainGui.getAsmProjectUml().restore();
    }
  }

  protected static void setOptions(RootDoc root) throws Exception {
    pathUmlProject = null;
    pathMavenProject = null;
    pathJavaDoc = null;
    for(String[] option : root.options()) {
      if(option[0].equals(OPTION_PATH_UML_PROJECT) && option.length == 2) {
        pathUmlProject = option[1].trim();
        root.printNotice("Uml project path is " + pathUmlProject);
      }
      else if(option[0].equals("-d") && option.length == 2) {
        pathJavaDoc = option[1].trim();
        root.printNotice("Javadoc path is " + pathJavaDoc);
      }
      else if(option[0].equals("-sourcepath") && option.length == 2) {
        String pathJavaSource = option[1].trim();
        int firstSeparator = pathJavaSource.indexOf(File.pathSeparatorChar);
        if(firstSeparator != -1) {
          pathJavaSource = pathJavaSource.substring(0, firstSeparator).trim();
        }
        root.printNotice("Firts java source path is " + pathJavaSource);
        int mvnSrcIdx = pathJavaSource.indexOf("/src/test/");
        if(mvnSrcIdx == -1) {
          mvnSrcIdx = pathJavaSource.indexOf("/src/main/");
        }
        if(mvnSrcIdx != -1) {//maven project
          pathMavenProject = pathJavaSource.substring(0, mvnSrcIdx) + File.separator + "src";
          root.printNotice("Maven project path is " + pathMavenProject);
        }
      }
      if(pathUmlProject != null && pathMavenProject != null && pathJavaDoc != null) {
        break;
      }
    }
    if(pathJavaDoc == null) {
      root.printError("Can not find javadoc path!");
    }
    if(pathUmlProject == null) {
      if(pathMavenProject != null) {
        pathUmlProject = pathMavenProject + File.separator + "uml";
        root.printNotice("Uml project path is " + pathUmlProject);
      }
      else {
        root.printError("Doclet UML project path option: [" + OPTION_PATH_UML_PROJECT + "] must be settled!");
      }
    }
    if(pathUmlProject != null) {
      File file = new File(pathUmlProject);
      if(!file.isAbsolute()) {
        throw new Exception("Doclet UML project path option: [" + OPTION_PATH_UML_PROJECT + "] must be settled as absolute path "
            + "or nothing (assume path is [mvnproject]/src/uml)!");
      }
    }
  }

  //SGS:
  public static String getPathUmlProject() {
    return pathUmlProject;
  }

  public static String getPathMavenProject() {
    return pathMavenProject;
  }

  public static String getPathJavaDoc() {
    return pathJavaDoc;
  }

  public static ProjectUml getUmlProject() {
    return umlProject;
  }

  public static GuiMainUmlUninteractive getMainGui() {
    return mainGui;
  }
}
