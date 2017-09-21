package org.beigesoft.uml.ui.swing;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.beigesoft.model.IFilterFileWithChooseMode;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.pojo.IOpenable;
import org.beigesoft.ui.widget.swing.FileFilterNameIs;
import org.beigesoft.ui.widget.swing.FilterFileExtentionIsAndChooseModeAdapter;
import org.beigesoft.ui.widget.swing.FilterFileIsDirectoryAndChooseModeAdapter;
import org.beigesoft.uml.app.assembly.AsmProjectUml;
import org.beigesoft.uml.app.model.NodeDescriptorProject;
import org.beigesoft.uml.app.model.NodeDiagramClassLight;
import org.beigesoft.uml.app.model.NodeDiagramObject;
import org.beigesoft.uml.app.model.NodeDiagramPackage;
import org.beigesoft.uml.app.model.NodeDiagramSequence;
import org.beigesoft.uml.app.model.NodeDiagramUseCaseLight;
import org.beigesoft.uml.app.model.NodeFolderProject;
import org.beigesoft.uml.app.model.NodeRootProject;
import org.beigesoft.uml.app.model.ProjectUml;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.service.edit.SrvEditProject;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramClass;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.ui.EditorProject;
import org.beigesoft.uml.ui.IGuiMainUml;
import org.beigesoft.uml.ui.IPaneProjectUml;

import static org.beigesoft.uml.service.CreatorXsdUml.*;

/**
 * 
 * @author Yury Demidenko
 *
 */
public class PaneProjectLight extends JTree implements IPaneProjectUml {
  
  private static final long serialVersionUID = 2605703214134749974L;

  protected final AsmEditorProject<ProjectUml, EditorProject<ProjectUml, Frame, ActionEvent>> asmEditorProject;
  
  protected final DefaultMutableTreeNode rootNode;
  
  protected final DefaultTreeModel treeModel;
  
  protected final JFileChooser fileChooser;
  
  protected final IContainerSrvsGui<Frame> guiSrvs;
  
  protected final IGuiMainUml<?, ?, ?, FileAndWriter, Frame> guiMain;
  
  protected final Frame frameMain;
 
  public PaneProjectLight(Frame frame, IGuiMainUml<?, ?, ?, FileAndWriter, Frame> guiMain,  SrvEditProject<ProjectUml, Frame> editProjectUmlSrv) {
    this.guiSrvs = editProjectUmlSrv;
    this.frameMain = frame;
    this.guiMain = guiMain;
    String currDir = null;
    try {
      currDir = PaneProjectLight.class.getProtectionDomain()
        .getCodeSource().getLocation().toURI().getPath();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (currDir != null) {
      fileChooser = new JFileChooser(currDir);
    } else {
      fileChooser = new JFileChooser();
    }
    IFilterFileWithChooseMode filterAnyDirectory = new FilterFileIsDirectoryAndChooseModeAdapter();
    IFilterFileWithChooseMode filterJarOrWar = new FilterFileExtentionIsAndChooseModeAdapter(new String[]{"jar", "war"});
    EditorProject<ProjectUml, Frame, ActionEvent> editorProject = new EditorProject<ProjectUml, Frame, ActionEvent>(frame, 
        editProjectUmlSrv, editProjectUmlSrv.getSrvI18n().getMsg("project"), guiMain, filterAnyDirectory, filterJarOrWar);
    asmEditorProject = new AsmEditorProject<ProjectUml, EditorProject<ProjectUml, Frame, ActionEvent>>(frame, editorProject);
    asmEditorProject.doPostConstruct();
    rootNode = new DefaultMutableTreeNode(new NodeRootProject(this));
    treeModel = new DefaultTreeModel(rootNode);
    setModel(treeModel);
    addMouseListener(mouselistener);
    setCellRenderer(cellRenderer);
  }

  @Override
  public void newProjectUml() {
    ProjectUml projectUml = new ProjectUml((SettingsGraphicUml) guiSrvs.getSettingsGraphic());
    guiMain.getAsmProjectUml().setProjectUml(projectUml);
    asmEditorProject.startEdit(guiMain.getAsmProjectUml().getProjectUml());
    Set<String> result = new HashSet<String>();
    asmEditorProject.getEditor().getSrvEdit().validate(guiMain.getAsmProjectUml().getProjectUml(), result);
    if(result.size() > 0) {
      guiMain.getAsmProjectUml().setProjectUml(null);
    }
    refreshGui();
    guiMain.refreshGui();
  }
  
  @Override
  public void openProjectUml() {
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setFileFilter(new FileFilterNameIs(DESCRIPTOR_FILE_NAME));
    int retVal = fileChooser.showOpenDialog(frameMain);
    if(retVal == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      openProjectUml(file);
    }
  }
  
  @Override
  public void openProjectUml(File file) {
    String projectPath = file.getAbsolutePath().replace(File.separatorChar+DESCRIPTOR_FILE_NAME, "");
    if(guiMain.getAsmProjectUml().getProjectUml() != null && 
        guiMain.getAsmProjectUml().getProjectUml().getProjectPath().equals(projectPath)) {
      return;
    }
    String projectName = projectPath.substring(projectPath.lastIndexOf(File.separatorChar) + 1);
    int idxPn = projectPath.lastIndexOf(projectName);
    String pathPre = projectPath.substring(0, idxPn - 1);
    ProjectUml anotherProject = new ProjectUml(guiMain.getSettingsGraphicUml(), 
        pathPre, projectName);
    guiMain.getAsmProjectUml().setProjectUml(anotherProject);
    try {
      guiMain.getAsmProjectUml().restore();
    } catch (Exception e) {
      guiMain.getAsmProjectUml().setProjectUml(null);//TODO
      guiSrvs.getSrvDialog().errorMessage(guiMain.getDialogInstrument(), e.getMessage(), "Open project error!");
      e.printStackTrace();
    }
    setProjectUmlAndRefreshGui(anotherProject);
  }
  
  @Override
  public void addFileIntoSelectedFolder(File file) {
    DefaultMutableTreeNode parentNode = getSelectedTreeNode();
    DefaultMutableTreeNode childNode = null;
    if(file.getName().endsWith(SrvSaveXmlDiagramClass.DIAGRAM_FILE_EXTENSION)) {
      childNode = new DefaultMutableTreeNode(
          new NodeDiagramClassLight(file.getAbsolutePath(), file.getName(), guiMain));
    }
    else if(file.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_USECASE)) {
      childNode = new DefaultMutableTreeNode(
          new NodeDiagramUseCaseLight(file.getAbsolutePath(), file.getName(), guiMain));
    }
    else if(file.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_PACKAGE)) {
      childNode = new DefaultMutableTreeNode(
          new NodeDiagramPackage(file.getAbsolutePath(), file.getName(), guiMain));
    }
    else if(file.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_OBJECT)) {
      childNode = new DefaultMutableTreeNode(
          new NodeDiagramObject(file.getAbsolutePath(), file.getName(), guiMain));
    }
    else if(file.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_SEQUENCE)) {
      childNode = new DefaultMutableTreeNode(
          new NodeDiagramSequence(file.getAbsolutePath(), file.getName(), guiMain));
    }
    treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
    scrollPathToVisible(new TreePath(childNode.getPath()));
  }

  @Override
  public void addFolderIntoSelectedFolder() {
    DefaultMutableTreeNode parentNode = getSelectedTreeNode();
    String path = getSelectedFolderPath();
    if(path == null) {
      guiSrvs.getSrvDialog().errorMessage(frameMain, guiSrvs.getSrvI18n().getMsg("please_choose_folder_first_msg"),
          guiSrvs.getSrvI18n().getMsg("error"));
      return;
    }
    String folderName = JOptionPane.showInputDialog(frameMain, 
        guiSrvs.getSrvI18n().getMsg("enter_folder_name"));
    if(folderName == null || folderName.trim().length() == 0) {
      return;
    }
    path += folderName;
    File folder = new File(path);
    if(!folder.exists())
      folder.mkdir();
    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new NodeFolderProject(folder.getAbsolutePath(), folder.getName()));
    treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
    scrollPathToVisible(new TreePath(childNode.getPath()));
  }
  
  @Override
  public String getSelectedFolderPath() {
    DefaultMutableTreeNode parentNode = getSelectedTreeNode();
    IOpenable selNode = null;
    if(parentNode != null) {
      selNode = (IOpenable) parentNode.getUserObject();
    }
    if(parentNode != null && selNode.getClass() == NodeFolderProject.class) {
      return ((NodeFolderProject) selNode).getPath() +File.separator;
    }
    else if(parentNode != null && selNode.getClass() == NodeRootProject.class) {
      return guiMain.getAsmProjectUml().getProjectUml().getProjectPath()+File.separator;
    }
    else {
      return null;
    }
  }
   
  @Override
  public void openProjectUmlEditor() {
    if(asmEditorProject.isActive())
      return;
    asmEditorProject.startEdit(guiMain.getAsmProjectUml().getProjectUml());
  }

  /**
   * refresh tree and expand to file that usually newly created
   */
  @Override
  public void refreshGuiAndShowFile(File file) {
    if(rootNode.getChildCount() > 0) {
      rootNode.removeAllChildren();
      treeModel.reload();
    }
    if(guiMain.getAsmProjectUml().getProjectUml() != null) {
      addTreeNodes(file);
      guiMain.getMenuMain().setVisibleProjectMenu(true);
    }
    else {
      guiMain.getMenuMain().setVisibleProjectMenu(false);
    }
  }

  @Override
  public void refreshGui() {
    refreshGuiAndShowFile(null);
  }

  protected void addTreeNodes(File file) {
    URI uri;
    String uriStr = null;
    try {
      String projPath = guiMain.getAsmProjectUml()
        .getProjectUml().getProjectPath();
      if (projPath.contains("\\")) {
        uriStr = "file:/" + projPath.replace('\\', '/');
      } else {
        uriStr = "file:" + projPath;
      }
      uri = new URI(uriStr);
      File dir = new File(uri);
      addToTreeNode(dir, rootNode, file);
    } catch (Exception e) {
      System.out.println(uriStr);
      e.printStackTrace();
    }    
  }
  
  protected void addToTreeNode(File dir, DefaultMutableTreeNode parentNode, File file) throws IOException {
    String[] files = dir.list();
    for(String fileName : files) {
      File fileOrDir = new File(dir.getPath()+File.separator+fileName);
      DefaultMutableTreeNode childNode = null;
      if(fileOrDir.isDirectory()) {
        childNode = new DefaultMutableTreeNode(new NodeFolderProject(fileOrDir.getAbsolutePath(), fileOrDir.getName()));
        treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
        addToTreeNode(fileOrDir, childNode, file);
      }
      else if(fileOrDir.getName().equals(DESCRIPTOR_FILE_NAME)) {
        childNode = new DefaultMutableTreeNode(new NodeDescriptorProject(this));
        treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
        scrollPathToVisible(new TreePath(childNode.getPath()));        
      }
      else if(fileOrDir.getName().endsWith(SrvSaveXmlDiagramClass.DIAGRAM_FILE_EXTENSION)) {
        childNode = new DefaultMutableTreeNode(new NodeDiagramClassLight(fileOrDir.getAbsolutePath(), 
            fileOrDir.getName(), guiMain));
        treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
        if(fileOrDir.equals(file)) {
          scrollPathToVisible(new TreePath(childNode.getPath()));
        }
      }
      else if(fileOrDir.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_USECASE)) {
        childNode = new DefaultMutableTreeNode(new NodeDiagramUseCaseLight(fileOrDir.getAbsolutePath(), 
            fileOrDir.getName(), guiMain));
        treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
        if(fileOrDir.equals(file)) {
          scrollPathToVisible(new TreePath(childNode.getPath()));
        }
      }
      else if(fileOrDir.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_PACKAGE)) {
        childNode = new DefaultMutableTreeNode(new NodeDiagramPackage(fileOrDir.getAbsolutePath(), 
            fileOrDir.getName(), guiMain));
        treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
        if(fileOrDir.equals(file)) {
          scrollPathToVisible(new TreePath(childNode.getPath()));
        }
      }
      else if(fileOrDir.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_OBJECT)) {
        childNode = new DefaultMutableTreeNode(new NodeDiagramObject(fileOrDir.getAbsolutePath(), 
            fileOrDir.getName(), guiMain));
        treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
        if(fileOrDir.equals(file)) {
          scrollPathToVisible(new TreePath(childNode.getPath()));
        }
      }
      else if(fileOrDir.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_SEQUENCE)) {
        childNode = new DefaultMutableTreeNode(new NodeDiagramSequence(fileOrDir.getAbsolutePath(), 
            fileOrDir.getName(), guiMain));
        treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
        if(fileOrDir.equals(file)) {
          scrollPathToVisible(new TreePath(childNode.getPath()));
        }
      }
    }
  }
  
  @Override
  public void setProjectUmlAndRefreshGui(ProjectUml umlProject) {
    guiMain.getAsmProjectUml().setProjectUml(umlProject);
    refreshGui();
    guiMain.refreshGui();
  }

  @Override
  public IOpenable getSelectedTreePath() {
    IOpenable selectedNode = null;
    DefaultMutableTreeNode selectedTreeNode = getSelectedTreeNode();
    if(selectedTreeNode != null)
      selectedNode = (IOpenable) selectedTreeNode.getUserObject();
    return selectedNode;
  }

  protected DefaultMutableTreeNode getSelectedTreeNode() {
    DefaultMutableTreeNode selectedNode = null;
    TreePath selectedPath = getSelectionPath();
    if(selectedPath != null)
      selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
    return selectedNode;
  }

  //Inner classes:
  protected final DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
    
    private static final long serialVersionUID = -7838974243078343035L;

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, 
        boolean expanded, boolean leaf, int row, boolean hasFocus) {
      super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
      if(value != null) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        IOpenable nodeVal = (IOpenable) node.getUserObject();
        if(nodeVal.getClass() == NodeFolderProject.class) {
          Icon iconFolder = UIManager.getIcon("FileView.directoryIcon");
          if(iconFolder != null)
            setIcon(iconFolder);
        }
      }
      return this;
    }
  };

  protected final MouseListener mouselistener = new MouseAdapter() {
    
    @Override
    public void mousePressed(MouseEvent e) {
      if(e.getClickCount() == 2  && !e.isConsumed()) {
        e.consume();
        TreePath selPath = getPathForLocation(e.getX(), e.getY());
        if(selPath != null) {
          IOpenable selectedNode = (IOpenable) ((DefaultMutableTreeNode) selPath.getLastPathComponent()).getUserObject();
          selectedNode.open();
        }
        return;
      }
    }
  };

  //Delegates:
  @Override
  public AsmProjectUml getAsmProjectUml() {
    return guiMain.getAsmProjectUml();
  }

  public AsmEditorProject<ProjectUml, EditorProject<ProjectUml, Frame, ActionEvent>> getEditorProject() {
    return asmEditorProject;
  }
}
