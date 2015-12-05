/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.ui;

import java.io.File;

import org.beigesoft.ui.pojo.IOpenable;
import org.beigesoft.uml.app.assembly.AsmProjectUml;
import org.beigesoft.uml.app.model.ProjectUml;

/**
 * <p>Abstraction of a interactive UML Project Pane</p>
 * 
 * 
 * @author Yury Demidenko
 */
public interface IPaneProjectUml {

  public void setProjectUmlAndRefreshGui(ProjectUml umlProject);

  public void openProjectUml();
  
  public void openProjectUmlEditor();

  public void newProjectUml();

  public void refreshGui();

  public IOpenable getSelectedTreePath();
  
  public void addFolderIntoSelectedFolder();

  public void addFileIntoSelectedFolder(File file);

  public String getSelectedFolderPath();

  public void refreshGuiAndShowFile(File file);

  public void openProjectUml(File file);
  
  public AsmProjectUml getAsmProjectUml();
}
