/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.app.assembly;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import org.beigesoft.service.ISrvPersistSimple;
import org.beigesoft.uml.app.model.ProjectUml;

/**
 * <p>UML project model</p>
 * 
 * @author Yury Demidenko
 */
public class AsmProjectUml {

  private final ISrvPersistSimple<ProjectUml> srvPersist;

  private ProjectUml projectUml;

  private URLClassLoader classLoader;

  public AsmProjectUml(ISrvPersistSimple<ProjectUml> srvPersist) {
    this.srvPersist = srvPersist;
  }

  public void makeClassloader() throws Exception {
    if(projectUml.getJavaSourceFilePath() != null) {
      File file = new File(projectUml.getJavaSourceFilePath());
      if(file.exists()) {
        URL urlFile = file.toURI().toURL();
        URL[] urls = { urlFile };
        if(classLoader == null || classLoader != null && !classLoader.getURLs()[0].equals(urlFile)) {
          classLoader = new URLClassLoader(urls);
        }
      }
      else {
        throw new Exception("There is no file "+ projectUml.getJavaSourceFilePath());
      }
    }
  }

  public ISrvPersistSimple<ProjectUml> getSrvPersist() {
    return srvPersist;
  }

  public ProjectUml getProjectUml() {
    return projectUml;
  }
  
  public void persist() throws Exception {
    srvPersist.persist(projectUml);
    makeClassloader();
  }
  
  public void restore() throws Exception {
    srvPersist.restore(projectUml);
    makeClassloader();
  }

  public URLClassLoader getClassLoader() {
    return classLoader;
  }

  public void setClassLoader(URLClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  public void setProjectUml(ProjectUml projectUml) {
    this.projectUml = projectUml;
  }
}
