package org.beigesoft.uml.ui.android;

import org.beigesoft.uml.ui.IMenuUml;

import android.view.MenuItem;

public class MenuMain implements IMenuUml {

  private MenuItem menuItemEditProject;

  private MenuItem menuItemSaveDiagram;
  
  private MenuItem menuItemOpenDiagram;
  
  private MenuItem menuItemNewDiagramClass;
  
  private MenuItem menuItemNewDiagramPackage;
  
  private MenuItem menuItemNewDiagramObject;
  
  private MenuItem menuItemNewDiagramSequence;
  
  private MenuItem menuItemToolbar;
  
  private MenuItem menuItemDeleteSelectedelement;

  private MenuItem menuItemNewDiagramUseCase;
  
  @Override
  public void setAddClassDiagramFromJavaSourceEnabled(boolean isVisible) {
    // stub
  }

  @Override
  public void setAddClassFromJavaSourceEnabled(boolean isVisible) {
    // TODO Auto-generated method stub
  }

  @Override
  public void setAdjustRelationsFor90DegreeEnabled(boolean isVisible) {
    // stub  
  }

  @Override
  public void setDeleteSelectedElementEnabled(boolean isVisible) {
    menuItemDeleteSelectedelement.setVisible(isVisible);
  }

  @Override
  public void setEditDiagramEnabled(boolean isVisible) {
    // stub
  }

  @Override
  public void setRearrangeEnabled(boolean isVisible) {
    // stub
  }

  @Override
  public void setSaveDiagramEnabled(boolean isEnabled) {
    menuItemSaveDiagram.setVisible(isEnabled);
  }

  @Override
  public void setVisibleProjectMenu(boolean isVisible) {
    menuItemOpenDiagram.setVisible(isVisible);
    menuItemNewDiagramClass.setVisible(isVisible);
    menuItemNewDiagramObject.setVisible(isVisible);
    menuItemNewDiagramPackage.setVisible(isVisible);
    menuItemNewDiagramSequence.setVisible(isVisible);
    menuItemNewDiagramUseCase.setVisible(isVisible);
  }

  //SGS:
  public MenuItem getMenuItemEditProject() {
    return menuItemEditProject;
  }

  public void setMenuItemEditProject(MenuItem menuItemEditProject) {
    this.menuItemEditProject = menuItemEditProject;
  }

  public MenuItem getMenuItemSaveDiagram() {
    return menuItemSaveDiagram;
  }

  public void setMenuItemSaveDiagram(MenuItem menuItemSaveDiagram) {
    this.menuItemSaveDiagram = menuItemSaveDiagram;
  }

  public MenuItem getMenuItemOpenDiagram() {
    return menuItemOpenDiagram;
  }

  public void setMenuItemOpenDiagram(MenuItem menuItemOpenDiagram) {
    this.menuItemOpenDiagram = menuItemOpenDiagram;
  }

  public MenuItem getMenuItemToolbar() {
    return menuItemToolbar;
  }

  public void setMenuItemToolbar(MenuItem menuItemToolbar) {
    this.menuItemToolbar = menuItemToolbar;
  }

  public MenuItem getMenuItemDeleteSelectedelement() {
    return menuItemDeleteSelectedelement;
  }

  public void setMenuItemDeleteSelectedelement(
      MenuItem menuItemDeleteSelectedelement) {
    this.menuItemDeleteSelectedelement = menuItemDeleteSelectedelement;
  }

  public MenuItem getMenuItemNewDiagramClass() {
    return menuItemNewDiagramClass;
  }

  public void setMenuItemNewDiagramClass(MenuItem menuItemNewDiagramClass) {
    this.menuItemNewDiagramClass = menuItemNewDiagramClass;
  }

  public void setMenuItemNewDiagramUseCase(MenuItem menuItemNewDiagramUseCase) {
    this.menuItemNewDiagramUseCase = menuItemNewDiagramUseCase;
  }

  public MenuItem getMenuItemNewDiagramUseCase() {
    return menuItemNewDiagramUseCase;
  }

  public MenuItem getMenuItemNewDiagramPackage() {
    return menuItemNewDiagramPackage;
  }

  public void setMenuItemNewDiagramPackage(MenuItem menuItemNewDiagramPackage) {
    this.menuItemNewDiagramPackage = menuItemNewDiagramPackage;
  }

  public MenuItem getMenuItemNewDiagramObject() {
    return menuItemNewDiagramObject;
  }

  public void setMenuItemNewDiagramObject(MenuItem menuItemNewDiagramObject) {
    this.menuItemNewDiagramObject = menuItemNewDiagramObject;
  }

  public MenuItem getMenuItemNewDiagramSequence() {
    return menuItemNewDiagramSequence;
  }

  public void setMenuItemNewDiagramSequence(MenuItem menuItemNewDiagramSequence) {
    this.menuItemNewDiagramSequence = menuItemNewDiagramSequence;
  }
}
