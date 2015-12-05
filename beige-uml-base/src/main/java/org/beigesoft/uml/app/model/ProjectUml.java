/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.app.model;

import java.io.File;

import org.beigesoft.model.IHasName;

import static org.beigesoft.uml.service.CreatorXsdUml.*;

/**
 * <p>UML project model</p>
 * 
 * @author Yury Demidenko
 */
public class ProjectUml implements IHasName {

  private String pathPre;
  
  private String itsName;
    
  private boolean isUseJavaLangObject;
  
  private boolean isRetrieveNonpublic;

  private String javaSourceFilePath;
  
  private boolean isUseGenericForGenerateFromJavaClass = true;

  /**
   * final reference to AppSettings.settingsGraphicUml;
   */
  private final SettingsGraphicUml settingsGraphicUml;
  
  public ProjectUml(SettingsGraphicUml settingsGraphicUml) {
    this.settingsGraphicUml = settingsGraphicUml;
  }
  
  public ProjectUml(SettingsGraphicUml settingsGraphicUml, String pathPre, String name) {
    this(settingsGraphicUml);
    this.pathPre = pathPre;
    this.itsName = name;
  }
  
  @Override
  public ProjectUml clone() {
    SettingsGraphicUml cloneGp = settingsGraphicUml.clone();
    ProjectUml clone = new ProjectUml(cloneGp, pathPre, itsName);
    clone.setJavaSourceFilePath(javaSourceFilePath);
    clone.setIsUseGenericForGenerateFromJavaClass(isUseGenericForGenerateFromJavaClass);
    return clone;
  }

  //Utilities:
  public boolean getIsNew() {
    String projectPath = getProjectPath();
    if(projectPath == null) {
      return true;
    }
    File projectDir = new File(projectPath);
    if(!projectDir.exists()) {
      return true;
    }
    File projectDescriptor = new File(projectPath + File.separator + DESCRIPTOR_FILE_NAME);
    return !projectDescriptor.exists();
  }

  public String getProjectPath() {
    if(itsName != null && pathPre != null) {
      return pathPre + File.separator + itsName;
    }
    return null;
  }
    
  //OGS:
  public void setPathPre(String pathPre) {
    this.pathPre = pathPre;
  }

  public void setItsName(String name) {
    this.itsName = name;
  }

  public String getPathPre() {
    return pathPre;
  }
  
  public String getItsName() {
    return itsName;
  }
  
  public String getJavaSourceFilePath() {
    return javaSourceFilePath;
  }
  
  public void setJavaSourceFilePath(String javaSourceFilePath) {
    this.javaSourceFilePath = javaSourceFilePath;
  }
  
  public SettingsGraphicUml getSettingsGraphicUml() {
    return settingsGraphicUml;
  }

  public boolean getIsUseJavaLangObject() {
    return isUseJavaLangObject;
  }

  public void setIsUseJavaLangObject(boolean isUseJavaLangObject) {
    this.isUseJavaLangObject = isUseJavaLangObject;
  }

  public boolean getIsRetrieveNonpublic() {
    return isRetrieveNonpublic;
  }

  public void setIsRetrieveNonpublic(boolean isRetrieveNonpublic) {
    this.isRetrieveNonpublic = isRetrieveNonpublic;
  }

  public boolean getIsUseGenericForGenerateFromJavaClass() {
    return isUseGenericForGenerateFromJavaClass;
  }

  public void setIsUseGenericForGenerateFromJavaClass(
      boolean isUseGenericForGenerateFromJavaClass) {
    this.isUseGenericForGenerateFromJavaClass = isUseGenericForGenerateFromJavaClass;
  }
}
