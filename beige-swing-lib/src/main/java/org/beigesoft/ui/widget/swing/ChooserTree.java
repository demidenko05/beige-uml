/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.ui.widget.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.Collection;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.beigesoft.model.NodeTree;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.pojo.NodeRootDescriptor;

/**
 * <p>Tree chooser implementation</p>
 * 
 * @param <O> chosen type
 * 
 * @author Yury Demidenko
 */
public class ChooserTree<ID, O> extends AChooser<NodeTree<ID, O>> {
  
  private static final long serialVersionUID = -4424734032584204728L;

  protected final DefaultMutableTreeNode rootNode;
  
  protected final DefaultTreeModel treeModel;
  
  protected final JTree tree;

  public ChooserTree(Frame frame, IContainerSrvsGui<Frame> factoryGuiSrvBase, String title) {
    super(frame, factoryGuiSrvBase, title);
    rootNode = new DefaultMutableTreeNode(new NodeRootDescriptor());
    treeModel = new DefaultTreeModel(rootNode);
    tree = new JTree(treeModel);
    JScrollPane treeView = new JScrollPane(tree);
    add(treeView, BorderLayout.CENTER);
    pack();
    setSize(new Dimension(factoryGuiSrvBase.getSettingsGraphic().getScreenWidthPixels()/2,
        factoryGuiSrvBase.getSettingsGraphic().getScreenHeightPixels()/2));
    setLocationRelativeTo(null);
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public void doOk() {
    TreePath selectedPath = tree.getSelectionPath();
    if(selectedPath != null && ((DefaultMutableTreeNode) selectedPath.getLastPathComponent() != rootNode)) {
      DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
      value =  (NodeTree<ID, O>) selectedNode.getUserObject();
      //dispose();
      setVisible(false);
      invalidate();
    }
    else {
      guiSrvs.getSrvDialog().warningMessage(frame, guiSrvs.getSrvI18n().
          getMsg("please_select_element"), "");
    }
  }
  
  @Override
  public void refillDataSource(Collection<NodeTree<ID, O>> dataSource) {
    if(rootNode.getChildCount() > 0) {
      rootNode.removeAllChildren();
    }
   treeModel.reload();
   fillTree(rootNode, dataSource);
  }
  
  public void setRootNodeDescription(String description) {
    ((NodeRootDescriptor) rootNode.getUserObject()).setDescription(description);
  }
  
  public String getRootNodeDescription() {
    return ((NodeRootDescriptor) rootNode.getUserObject()).getDescription();
  }
  
  protected void fillTree(DefaultMutableTreeNode parentNode, Collection<NodeTree<ID, O>> dataSource) {
    for(NodeTree<ID, O> node : dataSource) {
      DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(node);
      treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
      if(node.getChildren() != null)
        fillTree(childNode, node.getChildren());
    }
    tree.repaint();
  }

  @Override
  public void setSelectedValue(NodeTree<ID, O> value) {
    // TODO Auto-generated method stub
    
  }
}
