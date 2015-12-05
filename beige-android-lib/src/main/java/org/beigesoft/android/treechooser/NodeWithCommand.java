package org.beigesoft.android.treechooser;

import org.beigesoft.model.NodeTree;

public class NodeWithCommand<ID, O> {
  
  public static final int CMD_FROM_PARENTS_LIST = 1;

  public static final int CMD_FROM_NODES_LIST = 2;

  private final NodeTree<ID, O> node;
  
  private final int command;

  public NodeWithCommand(NodeTree<ID, O> node, int command) {
    this.node = node;
    this.command = command;
  }

  public int getCommand() {
    return command;
  }

  public NodeTree<ID, O> getNode() {
    return node;
  }
  
}
