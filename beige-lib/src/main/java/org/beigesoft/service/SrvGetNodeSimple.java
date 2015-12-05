package org.beigesoft.service;

import org.beigesoft.converter.IConverter;
import org.beigesoft.model.NodeTree;
import org.beigesoft.service.ISrvGetData;
/**
 * 
 * Do not use it in real application for a big tree. Otherwise it might be a cause of performance problem. 
 * 
 * @author Yury Demidenko
 * @param <ID>
 * @param <O>
 */
public class SrvGetNodeSimple<ID, O> implements ISrvGetData<String, NodeTree<ID, O>> {

  private final NodeTree<ID, O> tree;
  
  private final IConverter<String, ID> converterId;
  
  public SrvGetNodeSimple(NodeTree<ID, O> tree,
      IConverter<String, ID> converterId) {
    this.tree = tree;
    this.converterId = converterId;
  }

  @Override
  public NodeTree<ID, O> getData(String id) {
    NodeTree<ID, O> node = tree.findNode(converterId.convert(id));
    return node;
  }

  public NodeTree<ID, O> getTree() {
    return tree;
  }
}
