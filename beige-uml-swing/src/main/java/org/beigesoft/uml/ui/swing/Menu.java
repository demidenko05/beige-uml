package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.beigesoft.uml.ui.IGuiMainUml;
import org.beigesoft.uml.ui.IMenuUml;

public class Menu extends JMenuBar implements IMenuUml, ActionListener {
  
  private static final long serialVersionUID = 918207250926381834L;
  
  protected JMenu menuFile;
  
  protected JMenuItem itemNewProject;
  
  protected JMenuItem itemOpenProject;

  protected JMenu menuProject;

  protected JMenuItem itemAddClassDiagram;
  
  protected JMenuItem itemAddUseCaseDiagram;
  
  protected JMenuItem itemAddPackageDiagram;
  
  protected JMenuItem itemAddObjectDiagram;
  
  protected JMenuItem itemAddSequenceDiagram;
  
  protected JMenuItem itemAddClassDiagramFromJavaSource;

  protected JMenuItem itemSaveDiagram;

  protected JMenuItem itemEditDiagramProperties;

  protected JMenuItem itemNewFolder;

  protected JMenuItem itemDeleteSelectedElement;

  protected JMenuItem itemAddClassFromJavaSource;

  protected JMenuItem itemRearrange;

  protected JMenuItem itemAdjustRelationsFor90Degree;

  protected final IGuiMainUml<?, ?, ?, ?, Frame> mainGui;
  
  public Menu(IGuiMainUml<?, ?, ?, ?, Frame> mainGui) {
    this.mainGui = mainGui;
    menuFile = new JMenu(mainGui.getGuiSrvs().getSrvI18n().getMsg("file"));
    itemOpenProject = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("open_project"));
    itemOpenProject.addActionListener(this);
    menuFile.add(itemOpenProject);
    add(menuFile);
    itemNewProject = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("new_project"));
    itemNewProject.addActionListener(this);
    menuFile.add(itemNewProject);
    add(menuFile);
    add(menuFile);
    menuProject = new JMenu(mainGui.getGuiSrvs().getSrvI18n().getMsg("project"));
    menuProject.setVisible(false);
    itemNewFolder = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("new_folder"));
    itemNewFolder.addActionListener(this);
    menuProject.add(itemNewFolder);
    itemAddClassDiagram = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("add_class_diagram"));
    itemAddClassDiagram.addActionListener(this);
    menuProject.add(itemAddClassDiagram);
    itemAddUseCaseDiagram = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("add_use_case_diagram"));
    itemAddUseCaseDiagram.addActionListener(this);
    menuProject.add(itemAddUseCaseDiagram);
    itemAddPackageDiagram = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("add_package_diagram"));
    itemAddPackageDiagram.addActionListener(this);
    menuProject.add(itemAddPackageDiagram);
    itemAddObjectDiagram = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("add_object_diagram"));
    itemAddObjectDiagram.addActionListener(this);
    menuProject.add(itemAddObjectDiagram);
    itemAddSequenceDiagram = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("add_sequence_diagram"));
    itemAddSequenceDiagram.addActionListener(this);
    menuProject.add(itemAddSequenceDiagram);
    itemAddClassDiagramFromJavaSource = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("add_class_diagram_from_java_source"));
    itemAddClassDiagramFromJavaSource.addActionListener(this);
    itemAddClassDiagramFromJavaSource.setEnabled(false);
    menuProject.add(itemAddClassDiagramFromJavaSource);
    itemSaveDiagram = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("save_diagram"), KeyEvent.VK_S);
    itemSaveDiagram.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    itemSaveDiagram.addActionListener(this);
    itemSaveDiagram.setEnabled(false);
    menuProject.add(itemSaveDiagram);
    itemEditDiagramProperties = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("edit_diagram_properties"), KeyEvent.VK_E);
    itemEditDiagramProperties.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_E, ActionEvent.CTRL_MASK));
    itemEditDiagramProperties.addActionListener(this);
    itemEditDiagramProperties.setEnabled(false);
    menuProject.add(itemEditDiagramProperties);
    menuProject.addSeparator();
    itemDeleteSelectedElement = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("delete_selected_element"), KeyEvent.VK_DELETE);
    itemDeleteSelectedElement.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
    itemDeleteSelectedElement.addActionListener(this);
    itemDeleteSelectedElement.setEnabled(false);
    menuProject.add(itemDeleteSelectedElement);
    menuProject.addSeparator();
    itemAddClassFromJavaSource = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("add_class_from_java_source"));
    itemAddClassFromJavaSource.addActionListener(this);
    itemAddClassFromJavaSource.setEnabled(false);
    menuProject.add(itemAddClassFromJavaSource);
    itemAdjustRelationsFor90Degree = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("adjust_relations_90"));
    itemAdjustRelationsFor90Degree.addActionListener(this);
    itemAdjustRelationsFor90Degree.setEnabled(false);
    menuProject.add(itemAdjustRelationsFor90Degree);
    menuProject.addSeparator();
    itemRearrange = new JMenuItem(mainGui.getGuiSrvs().getSrvI18n().getMsg("rearrange"));
    itemRearrange.addActionListener(this);
    itemRearrange.setEnabled(false);
    menuProject.add(itemRearrange);
    add(menuProject);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == itemNewProject) {
      mainGui.newProjectUml();
    }
    else if(e.getSource() == itemOpenProject) {
      mainGui.openProjectUml();
    }
    //new diagram:
    else if(e.getSource() == itemAddPackageDiagram) {
      mainGui.newPackageDiagram();
    }
    else if(e.getSource() == itemAddObjectDiagram) {
      mainGui.newDiagramObject();
    }
    else if(e.getSource() == itemAddSequenceDiagram) {
      mainGui.newDiagramSequence();
    }
    else if(e.getSource() == itemAddClassDiagram) {
      mainGui.newDiagramClass();
    }
    else if(e.getSource() == itemAddUseCaseDiagram) {
      mainGui.newUseCaseDiagram();
    }
    else if(e.getSource() == itemAddClassDiagramFromJavaSource) {
      mainGui.newDiagramClassFromJavaSource();
    }
    //generic action on diagram
    else if(e.getSource() == itemSaveDiagram) {
      mainGui.getActiveControllerDiagramUml().saveDiagram();
    }
    else if(e.getSource() == itemEditDiagramProperties) {
      mainGui.getActiveControllerDiagramUml().editDiagramProperties();
    }
    else if(e.getSource() == itemNewFolder) {
      mainGui.addFolderIntoSelectedFolderProjectTree();
    }
    else if(e.getSource() == itemDeleteSelectedElement) {
      mainGui.getActiveControllerDiagramUml().deleteSelectedElement();
    }
    //class diagram actions:
    else if(e.getSource() == itemAddClassFromJavaSource) {
      mainGui.getControllerDiagramClass().addClassFromJavaSource();
    }    
    else if(e.getSource() == itemRearrange) {
      mainGui.getControllerDiagramClass().rearrange();
    }
    else if(e.getSource() == itemAdjustRelationsFor90Degree) {
      mainGui.getControllerDiagramClass().adjustRelationsFor90Degree();
    }
  }
  
  @Override
  public void setVisibleProjectMenu(boolean isVisible) {
    menuProject.setVisible(isVisible);
  }

  @Override
  public void setSaveDiagramEnabled(boolean isEnabled) {
    itemSaveDiagram.setEnabled(isEnabled);
  }

  @Override
  public void setEditDiagramEnabled(boolean isEnabled) {
    itemEditDiagramProperties.setEnabled(isEnabled);
  }

  @Override
  public void setDeleteSelectedElementEnabled(boolean isEnabled) {
    itemDeleteSelectedElement.setEnabled(isEnabled);
  }

  @Override
  public void setAddClassFromJavaSourceEnabled(boolean isEnabled) {
    itemAddClassFromJavaSource.setEnabled(isEnabled);
  }

  @Override
  public void setAddClassDiagramFromJavaSourceEnabled(boolean isEnabled) {
    itemAddClassDiagramFromJavaSource.setEnabled(isEnabled);
  }

  @Override
  public void setRearrangeEnabled(boolean isEnabled) {
    itemRearrange.setEnabled(isEnabled);
  }

  @Override
  public void setAdjustRelationsFor90DegreeEnabled(boolean isEnabled) {
    itemAdjustRelationsFor90Degree.setEnabled(isEnabled);
  }
}