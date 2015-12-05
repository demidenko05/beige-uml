/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Base model of a tree node
 * </p>
 * <p>
 * If children == null and it is folder then children is lazy initialized
 * </p>
 * @author Yury Demidenko
 */
public class NodeTree<ID, O> implements IPersistable<ID> {
  
  private ID id;
  
  private Integer idIcon;
  
  private O value;
  
  private int level;
  
  private boolean isOpened;
  
  private boolean isFolder;

  private NodeTree<ID, O> parent;
  
  private List<NodeTree<ID, O>> children;

  private boolean isNew;
  
  public NodeTree(ID id, O value) {
    this.id = id;
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    @SuppressWarnings("unchecked")
    NodeTree<ID, O> other = (NodeTree<ID, O>) obj;
    return getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
     return getId().hashCode();
  }

  public NodeTree<ID, O> findNode(ID id) {
    NodeTree<ID, O> node = null;
    if(getId().equals(id)) {
      node = this;
    }
    else {
      node = findNode(id, this);
    }
    return node;
  }
  
  public NodeTree<ID, O> findNode(ID id, NodeTree<ID, O> parent) {
    NodeTree<ID, O> node = null;
    if(parent.getChildren() != null) {
      for(NodeTree<ID, O> nodeChild : parent.getChildren()) {
        if(nodeChild.getId().equals(id)) {
          node = nodeChild;
          break;
        }
      }
      if(node == null) {
        for(NodeTree<ID, O> nodeChild : parent.getChildren()) {
          node = findNode(id, nodeChild);
          if(node != null) {
            break;
          }
        }
      }
    }
    return node;
  }

  public void addChild(NodeTree<ID, O> child) {
    if(children == null) {
      createEmptyChildren();
    }
    children.add(child);
    child.setLevel(level + 1);
    child.setParent(this);
    this.setIsFolder(true);
  }
  
  public void createEmptyChildren() {
    children = new ArrayList<NodeTree<ID, O>>();
  }

  public void removeChildren() {
    this.children = null;
  }

  @Override
  public String toString() {
    return value == null ? " " : value.toString();
  }
  
  @Override
  public ID getId() {
    return id;
  }

  @Override
  public void setId(ID id) {
    this.id = id;
  }

  @Override
  public boolean getIsNew() {
    return isNew;
  }

  @Override
  public void setIsNew(boolean isNew) {
    this.isNew = isNew;
  }

  public O getValue() {
    return value;
  }
  
  public void setValue(O value) {
    this.value = value;
  }
  
  public List<NodeTree<ID, O>> getChildren() {
    return children;
  }

  public NodeTree<ID, O> getParent() {
    return parent;
  }

  public void setParent(NodeTree<ID, O> parent) {
    this.parent = parent;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public Integer getIdIcon() {
    return idIcon;
  }

  public void setIdIcon(Integer idIcon) {
    this.idIcon = idIcon;
  }

  public boolean getIsOpened() {
    return isOpened;
  }

  public void setIsOpened(boolean isOpened) {
    this.isOpened = isOpened;
  }

  public boolean getIsFolder() {
    return isFolder;
  }

  public void setIsFolder(boolean isFolder) {
    this.isFolder = isFolder;
  }
}
